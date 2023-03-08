package com.jiazhifei.coder.core.sql;

import com.jiazhifei.coder.core.util.CoderUtil;

/**
 * mysql构造器
 *
 * @author jkf
 */
public class MysqlConfigBuilder {
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


    /**
     * mysql 账号
     *
     * @param name mysql账号
     */
    public MysqlConfigBuilder name(String name) {
        this.name = name;
        return this;
    }

    /**
     * mysql的密码
     *
     * @param password mysql密码
     */
    public MysqlConfigBuilder password(String password) {
        this.password = password;
        return this;
    }

    /**
     * mysql ip地址
     *
     * @param ip mysql的ip地址
     */
    public MysqlConfigBuilder ip(String ip) {
        this.ip = ip;
        return this;
    }

    /**
     * mysql端口
     *
     * @param port
     */
    public MysqlConfigBuilder port(String port) {
        this.port = port;
        return this;
    }

    /**
     * 数据库名称
     *
     * @param db 数据库名称
     */
    public MysqlConfigBuilder db(String db) {
        this.db = db;
        return this;
    }

    /**
     * 数据表名称
     *
     * @param table 数据表名称
     */
    public MysqlConfigBuilder table(String table) {
        this.table = table;
        return this;
    }

    /**
     * 数据表的字段名称
     *
     * @param column 数据表的字段名称
     */
    public MysqlConfigBuilder column(String column) {
        this.column = column;
        return this;
    }

    public MysqlConfigBuilder driverPath(String driverPath) {
        this.driverPath = driverPath;
        return this;
    }

    public MysqlConfigBuilder driverClass(String driverClass) {
        this.driverClass = driverClass;
        return this;
    }

    /**
     * 根据url生成MysqlConfigBuilder
     *
     * @param url 例如：jdbc:mysql://localhost:3306/jykj?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC&useSSL=true
     * @return MysqlConfigBuilder
     */
    public MysqlConfigBuilder url(String url) {
        SqlUtil.dbConfigFromUrl(this, url);
        return this;
    }

    public MysqlConfig build() {
        MysqlConfig config = new MysqlConfig();
        validateConfig();
        config.setPassword(password);
        config.setName(name);
        config.setIp(ip);
        config.setPort(port);
        config.setDb(db);
        config.setTable(table);
        config.setColumn(column);

        return config;
    }

    private void validateConfig() {
        if (CoderUtil.isEmpty(name)) {
            throw new IllegalArgumentException("mysql 账号name必填");
        }
        if (CoderUtil.isEmpty(password)) {
            throw new IllegalArgumentException("mysql password必填");
        }

        if (CoderUtil.isEmpty(ip)) {
            throw new IllegalArgumentException("mysql ip必填");
        }
        if (CoderUtil.isEmpty(port)) {
            throw new IllegalArgumentException("mysql 端口必填");
        }
        if (CoderUtil.isEmpty(db)) {
            throw new IllegalArgumentException("mysql 数据库信息必填");
        }
        if (CoderUtil.isEmpty(table)) {
            throw new IllegalArgumentException("mysql 表名必填");
        }
        if (CoderUtil.isEmpty(column)) {
            throw new IllegalArgumentException("mysql column名必填");
        }
    }
}
