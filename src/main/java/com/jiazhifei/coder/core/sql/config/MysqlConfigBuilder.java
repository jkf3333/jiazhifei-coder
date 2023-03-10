package com.jiazhifei.coder.core.sql.config;

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
     * 驱动路径
     */
    public MysqlConfigBuilder driverPath(String driverPath) {
        this.driverPath = driverPath;
        return this;
    }

    /**
     * 驱动类
     */
    public MysqlConfigBuilder driverClass(String driverClass) {
        this.driverClass = driverClass;
        return this;
    }

    /**
     * 构建参数
     */
    public MysqlConfig build() {
        if (CoderUtil.isEmpty(driverPath)) {
            driverPath = MysqlConfig.DRIVER_PATH_8;
        }
        if (CoderUtil.isEmpty(driverClass)) {
            driverClass = MysqlConfig.DRIVER_CLASS_8;
        }
        if (CoderUtil.isEmpty(ip)) {
            ip = MysqlConfig.DEFAULT_MYSQL_IP;
        }
        if (CoderUtil.isEmpty(port)) {
            port = MysqlConfig.DEFAULT_MYSQL_PORT;
        }
        MysqlConfig config = new MysqlConfig();
        validateConfig();
        config.setPassword(password);
        config.setName(name);
        config.setIp(ip);
        config.setPort(port);
        config.setDb(db);
        config.setDriverClass(driverClass);
        config.setDriverPath(driverPath);
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
        if (CoderUtil.isEmpty(driverPath)) {
            throw new IllegalArgumentException("mysql 驱动路径必填");
        }
        if (CoderUtil.isEmpty(driverClass)) {
            throw new IllegalArgumentException("mysql 驱动类必填");
        }
    }
}
