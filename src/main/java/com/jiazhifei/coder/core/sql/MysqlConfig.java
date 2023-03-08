package com.jiazhifei.coder.core.sql;

import java.io.Serializable;

/**
 * mysql配置
 *
 * @author jkf
 */
public class MysqlConfig implements Serializable {
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
     * 驱动地址
     */
    private String driverPath;
    /**
     * 驱动名称
     */
    private String driverClass;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public static MysqlConfigBuilder builder() {
        return new MysqlConfigBuilder();
    }
}
