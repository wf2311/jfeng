package com.wf2311.jfeng.mybatis.generator.plugin;

import com.wf2311.repository.annoation.*;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.Plugin;
import org.mybatis.generator.api.ShellRunner;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import tk.mybatis.mapper.generator.MapperPlugin;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wf2311
 */
public class WfMapperPlugin extends MapperPlugin {
    private static Map<String, Class> annotations = new HashMap<>();
    static final String ANNOTATION_JSON_FIELD = "com.alibaba.fastjson.annotation.JSONField";

    static {
        annotations.put("@Primary", Primary.class);
        annotations.put("@Creator", Creator.class);
        annotations.put("@CreatedTime", CreatedTime.class);
        annotations.put("@Modifier", Modifier.class);
        annotations.put("@ModifiedTime", ModifiedTime.class);
        annotations.put("@AutoIncrement", AutoIncrement.class);
    }

    public static void generate() {

        String config = WfMapperPlugin.class.getClassLoader().getResource(
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

//        topLevelClass.addImportedType(new FullyQualifiedJavaType("com.wf2311.commons.persist.annotation.Domain"));
//        topLevelClass.addAnnotation("@Domain");
        topLevelClass.addImportedType(new FullyQualifiedJavaType("lombok.ToString"));
        topLevelClass.addImportedType(new FullyQualifiedJavaType("lombok.Data"));
        topLevelClass.addAnnotation("@Data");
        topLevelClass.addAnnotation("@ToString");
        topLevelClass.addImportedType(new FullyQualifiedJavaType("java.io.Serializable"));
        topLevelClass.addSuperInterface(new FullyQualifiedJavaType("java.io.Serializable"));
    }
}
