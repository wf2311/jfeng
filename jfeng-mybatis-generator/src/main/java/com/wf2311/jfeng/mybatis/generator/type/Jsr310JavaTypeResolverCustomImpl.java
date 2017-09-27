package com.wf2311.jfeng.mybatis.generator.type;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.internal.types.JavaTypeResolverDefaultImpl;

import java.sql.Types;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author agwlvssainokuni
 * @time 2017/09/27 09:38.
 * @see <a href="https://github.com/agwlvssainokuni/mybatis-generator-custom/blob/master/src/main/java/cherry/mybatis/generator/custom/JavaTypeResolverCustomImpl.java">
 * https://github.com/agwlvssainokuni/mybatis-generator-custom/blcdob/master/src/main/java/cherry/mybatis/generator/custom/JavaTypeResolverCustomImpl.java</a>
 */
public class Jsr310JavaTypeResolverCustomImpl extends JavaTypeResolverDefaultImpl {

    private static final String PROP_USE_JODA_TIME = "useJava8Time";

    private static final String PROP_JAVA_TYPE_BY_COLUMN_NAME_PREFIX = "javaTypeByColumnName.";

    private Map<String, FullyQualifiedJavaType> javaTypeByColumnName = new HashMap<>();

    @Override
    public void addConfigurationProperties(Properties properties) {
        super.addConfigurationProperties(properties);

        if ("true".equalsIgnoreCase(properties.getProperty(PROP_USE_JODA_TIME))) {
            typeMap.put(Types.DATE, new JdbcTypeInformation("DATE",
                    new FullyQualifiedJavaType(LocalDate.class.getName())));
            typeMap.put(Types.TIME, new JdbcTypeInformation("TIME",
                    new FullyQualifiedJavaType(LocalTime.class.getName())));
            typeMap.put(Types.TIMESTAMP, new JdbcTypeInformation("TIMESTAMP",
                    new FullyQualifiedJavaType(LocalDateTime.class.getName())));
        }

        for (String propName : properties.stringPropertyNames()) {
            if (propName.startsWith(PROP_JAVA_TYPE_BY_COLUMN_NAME_PREFIX)) {
                String columnName = propName
                        .substring(PROP_JAVA_TYPE_BY_COLUMN_NAME_PREFIX
                                .length());
                String propValue = properties.getProperty(propName);
                javaTypeByColumnName.put(columnName,
                        new FullyQualifiedJavaType(propValue));
            }
        }
    }

    @Override
    public FullyQualifiedJavaType calculateJavaType(
            IntrospectedColumn introspectedColumn) {
        String columnName = introspectedColumn.getActualColumnName();
        if (javaTypeByColumnName.containsKey(columnName)) {
            return javaTypeByColumnName.get(columnName);
        }
        return super.calculateJavaType(introspectedColumn);
    }
}
