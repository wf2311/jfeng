package com.wf2311.jfeng.mybatis.generator.plugin;

import com.wf2311.jfeng.mybatis.generator.config.ExtFullyQualifiedJava8Type;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wf2311
 */
public class Java8WfMapperPlugin extends WfMapperPlugin {

    protected void generateToString(IntrospectedTable introspectedTable, TopLevelClass topLevelClass) {
        super.generateToString(introspectedTable, topLevelClass);
        List<Field> fields = topLevelClass.getFields();
        Map<String, Field> map = new HashMap<>();
        for (Field field : fields) {
            map.put(field.getName(), field);
        }
        List<IntrospectedColumn> columns = introspectedTable.getAllColumns();
        for (IntrospectedColumn column : columns) {
            Field f = map.get(column.getJavaProperty());
            if (f != null) {
                if (column.getJdbcTypeName().equalsIgnoreCase("date")) {
                    f.setType(ExtFullyQualifiedJava8Type.getLocalDateInstance());
                    f.addAnnotation("@JSONField(format = \"yyyy-MM-dd\")");
                    topLevelClass.addImportedType(ANNOTATION_JSON_FIELD);
                }
                if (column.getJdbcTypeName().equalsIgnoreCase("TIMESTAMP")) {
                    f.setType(ExtFullyQualifiedJava8Type.getLocalDateTimeInstance());
                    topLevelClass.addImportedType("java.time.LocalDateTime");
                    f.addAnnotation("@JSONField(format = \"yyyy-MM-dd HH:mm:ss\")");
                    topLevelClass.addImportedType(ANNOTATION_JSON_FIELD);
                }
                if (column.getJdbcTypeName().equalsIgnoreCase("time")) {
                    f.setType(ExtFullyQualifiedJava8Type.getLocalTimeInstance());
                    topLevelClass.addImportedType("java.time.LocalTime");
                    f.addAnnotation("@JSONField(format = \"HH:mm:ss\")");
                    topLevelClass.addImportedType(ANNOTATION_JSON_FIELD);
                }
            }
        }

    }
}
