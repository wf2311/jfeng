package com.wf2311.jfeng.mybatis.generator.plugin;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.Plugin;
import org.mybatis.generator.api.ShellRunner;
import org.mybatis.generator.api.dom.java.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wf2311
 */
public class CustomMapperPlugin extends MapperPlugin {
    protected static Map<String, Class> annotations = new HashMap<>();
    static final String ANNOTATION_JSON_FIELD = "com.alibaba.fastjson.annotation.JSONField";


    public static void generate() {

        String config = CustomMapperPlugin.class.getClassLoader().getResource(
                "generatorConfig.xml").getFile();
        String[] arg = {"-configfile", config, "-overwrite"};
        ShellRunner.main(arg);
    }

    private void setFiledAnnotation(Field field, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn) {
        String remarks = introspectedColumn.getRemarks();
        annotations.keySet().stream().filter(remarks::contains).forEach(key -> {
            field.addAnnotation(key);
            topLevelClass.addImportedType(annotations.get(key).getName());
        });

    }

    @Override
    public boolean modelFieldGenerated(Field field, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        setFiledAnnotation(field, topLevelClass, introspectedColumn);
        return super.modelFieldGenerated(field, topLevelClass, introspectedColumn, introspectedTable, modelClassType);
    }

    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        generateToString(introspectedTable, topLevelClass);
        return super.modelBaseRecordClassGenerated(topLevelClass, introspectedTable);
    }

    @Override
    public boolean modelRecordWithBLOBsClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        generateToString(introspectedTable, topLevelClass);
        return super.modelBaseRecordClassGenerated(topLevelClass, introspectedTable);
    }

    @Override
    public boolean modelPrimaryKeyClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        generateToString(introspectedTable, topLevelClass);
        return super.modelBaseRecordClassGenerated(topLevelClass, introspectedTable);
    }

    @Override
    public boolean modelGetterMethodGenerated(Method method,
                                              TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn,
                                              IntrospectedTable introspectedTable,
                                              Plugin.ModelClassType modelClassType) {
        return false;
    }

    @Override
    public boolean modelSetterMethodGenerated(Method method,
                                              TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn,
                                              IntrospectedTable introspectedTable,
                                              Plugin.ModelClassType modelClassType) {
        return false;
    }


    protected void generateToString(IntrospectedTable introspectedTable, TopLevelClass topLevelClass) {

        topLevelClass.addImportedType(new FullyQualifiedJavaType("lombok.ToString"));
        topLevelClass.addImportedType(new FullyQualifiedJavaType("lombok.Data"));
        topLevelClass.addAnnotation("@Data");
        topLevelClass.addAnnotation("@ToString");
        topLevelClass.addImportedType(new FullyQualifiedJavaType("java.io.Serializable"));
        topLevelClass.addSuperInterface(new FullyQualifiedJavaType("java.io.Serializable"));
        List<IntrospectedColumn> columns = introspectedTable.getAllColumns();
        Map<String, Field> map = new HashMap<>();
        List<Field> fields = topLevelClass.getFields();
        for (Field field : fields) {
            map.put(field.getName(), field);
        }
        for (IntrospectedColumn column : columns) {
            Field f = map.get(column.getJavaProperty());
            if (f != null) {
                if (column.getJdbcTypeName().equalsIgnoreCase("tinyint")) {
                    if (column.getLength() == 1) {
                        f.setType(PrimitiveTypeWrapper.getBooleanInstance());
                        topLevelClass.addImportedType(PrimitiveTypeWrapper.getBooleanInstance());
                    } else {
                        f.setType(PrimitiveTypeWrapper.getIntegerInstance());
                        topLevelClass.addImportedType(PrimitiveTypeWrapper.getIntegerInstance());
                    }
                }
                resolveColumn(column, f, topLevelClass);

            }
        }
    }

    protected void resolveColumn(IntrospectedColumn column, Field f, TopLevelClass topLevelClass) {
    }
}
