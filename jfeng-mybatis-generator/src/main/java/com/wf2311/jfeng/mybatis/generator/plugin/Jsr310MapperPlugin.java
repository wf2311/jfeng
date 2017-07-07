package com.wf2311.jfeng.mybatis.generator.plugin;

import com.wf2311.jfeng.mybatis.generator.config.Jsr310QualifiedJavaType;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.TopLevelClass;

/**
 * @author wf2311
 */
public class Jsr310MapperPlugin extends CustomMapperPlugin {

    @Override
    protected void resolveColumn(IntrospectedColumn column, Field f, TopLevelClass topLevelClass) {
        super.resolveColumn(column, f, topLevelClass);
        if (column.getJdbcTypeName().equalsIgnoreCase("date")) {
            f.setType(Jsr310QualifiedJavaType.getLocalDateInstance());
            f.addAnnotation("@JSONField(format = \"yyyy-MM-dd\")");
            topLevelClass.addImportedType(ANNOTATION_JSON_FIELD);
        }
        if (column.getJdbcTypeName().equalsIgnoreCase("timestamp")) {
            f.setType(Jsr310QualifiedJavaType.getLocalDateTimeInstance());
            topLevelClass.addImportedType("java.time.LocalDateTime");
            f.addAnnotation("@JSONField(format = \"yyyy-MM-dd HH:mm:ss\")");
            topLevelClass.addImportedType(ANNOTATION_JSON_FIELD);
        }
        if (column.getJdbcTypeName().equalsIgnoreCase("time")) {
            f.setType(Jsr310QualifiedJavaType.getLocalTimeInstance());
            topLevelClass.addImportedType("java.time.LocalTime");
            f.addAnnotation("@JSONField(format = \"HH:mm:ss\")");
            topLevelClass.addImportedType(ANNOTATION_JSON_FIELD);
        }
    }
}
