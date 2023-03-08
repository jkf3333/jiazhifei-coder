package com.jiazhifei.coder.core;

/**
 * @author jkf
 * @date 2023-03-07 23:17:00
 * @description
 */
public class CoderConstant {
    /**
     * 数据库链接的模板
     */
    private final static String URL = "jdbc:mysql://%s:%s/%s?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC&useSSL=true";

    /**
     * 数据库驱动路径
     */
    private final static String DRIVE_CLASS_PATH = "/META-INF/coder/lib/mysql-connector-java-6.0.2-bin.jar";

    /**
     * mysql驱动，采用8
     */
    private final static String DRIVE_CLASS = "com.mysql.cj.jdbc.Driver";

}
