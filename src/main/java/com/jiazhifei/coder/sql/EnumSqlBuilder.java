package com.jiazhifei.coder.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author jkf
 * @date 2023-02-06 23:18:00
 * @description Enum工具类
 */
public class EnumSqlBuilder {
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

    /**
     * mysql账户
     */
    private String name;
    /**
     * mysql账户密码
     */
    private String password;
    /**
     * mysql ip地址
     */
    private String ip;
    /**
     * mysql 端口，默认值3306
     */
    private String port = "3306";

    /**
     * 数据库名称
     */
    private String db;

    /**
     * 表名
     */
    private String table;
    /**
     * 字段名称
     */
    private String column;


    /**
     * mysql 账号
     *
     * @param name mysql账号
     */
    public EnumSqlBuilder name(String name) {
        this.name = name;
        return this;
    }

    /**
     * mysql的密码
     *
     * @param password mysql密码
     */
    public EnumSqlBuilder password(String password) {
        this.password = password;
        return this;
    }

    /**
     * mysql ip地址
     *
     * @param ip mysql的ip地址
     */
    public EnumSqlBuilder ip(String ip) {
        this.ip = ip;
        return this;
    }

    /**
     * mysql端口
     *
     * @param port
     */
    public EnumSqlBuilder port(String port) {
        this.port = port;
        return this;
    }

    /**
     * 数据库名称
     *
     * @param db 数据库名称
     */
    public EnumSqlBuilder db(String db) {
        this.db = db;
        return this;
    }

    /**
     * 数据表名称
     *
     * @param table 数据表名称
     */
    public EnumSqlBuilder table(String table) {
        this.table = table;
        return this;
    }

    /**
     * 数据表的字段名称
     *
     * @param column 数据表的字段名称
     */
    public EnumSqlBuilder column(String column) {
        this.column = column;
        return this;
    }

    public String onlySql() {
        try {

//            Class.forName(DRIVE_CLASS);
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    String.format(URL, this.ip, this.port, this.db),
                    this.name,
                    this.password);
            String sql = "show create table " + this.table + ";";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            return "";
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return "";

    }

    public static void main(String[] args) {
        EnumSqlBuilder.builder()
                .ip("localhost")
                .name("root")
                .password("jkf3333")
                .db("jykj")
                .table("user")
                .column("status")
                .onlySql();
    }

    private static EnumSqlBuilder builder() {
        return new EnumSqlBuilder();
    }


}
