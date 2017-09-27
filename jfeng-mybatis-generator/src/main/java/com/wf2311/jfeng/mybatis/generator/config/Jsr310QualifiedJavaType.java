package com.wf2311.jfeng.mybatis.generator.config;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;

/**
 * @author wf2311
 */
@Deprecated
public class Jsr310QualifiedJavaType extends FullyQualifiedJavaType {

    /**
     * The localDate instance.
     */
    private static FullyQualifiedJavaType localDateInstance = null;

    /**
     * The localDateTime instance.
     */
    private static FullyQualifiedJavaType localDateTimeInstance = null;

    /**
     * The localTime instance.
     */
    private static FullyQualifiedJavaType localTimeInstance = null;

    public Jsr310QualifiedJavaType(String fullTypeSpecification) {
        super(fullTypeSpecification);
    }

    public static FullyQualifiedJavaType getLocalDateInstance() {
        if (localDateInstance == null) {
            localDateInstance = new FullyQualifiedJavaType("LocalDate");
        }
        return localDateInstance;
    }

    public static FullyQualifiedJavaType getLocalDateTimeInstance() {
        if (localDateTimeInstance == null) {
            localDateTimeInstance = new FullyQualifiedJavaType("LocalDateTime");
        }
        return localDateTimeInstance;
    }

    public static FullyQualifiedJavaType getLocalTimeInstance() {
        if (localTimeInstance == null) {
            localTimeInstance = new FullyQualifiedJavaType("LocalTime");
        }
        return localTimeInstance;
    }
}
