package com.jiazhifei.coder.core.sql.config;

import com.jiazhifei.coder.core.sql.SqlUtil;

import java.io.Serializable;

/**
 * mysql配置
 *
 * @author jkf
 */
public class MysqlConfig implements Serializable {
    /**
     * mysql 8的驱动路径
     */
    public static final String DRIVER_PATH_8 = "/META-INF/coder/lib/mysql-connector-java-8.0.11.jar";
    /**
     * mysql 8的驱动类
     */
    public static final String DRIVER_CLASS_8 = "com.mysql.cj.jdbc.Driver";
    /**
     * mysql 5的驱动路径
     */
    public static final String DRIVER_PATH_5 = "/META-INF/coder/lib/mysql-connector-java-5.0.8.jar";
    /**
     * mysql 5的驱动类
     */
    public static final String DRIVER_CLASS_5 = "com.mysql.jdbc.Driver";
    /**
     * mysql默认的ip
     */
    public static final String DEFAULT_MYSQL_IP = "127.0.0.1";
    /**
     * mysql默认的端口
     */
    public static final String DEFAULT_MYSQL_PORT = "3306";

    /**
     * 数据库链接，url的模板
     */
    private static final String URL_TEMPLATE = "jdbc:mysql://%s:%s/%s?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC&useSSL=true";


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

    public String getDriverPath() {
        return driverPath;
    }

    public void setDriverPath(String driverPath) {
        this.driverPath = driverPath;
    }

    public String getDriverClass() {
        return driverClass;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public static MysqlConfigBuilder builder() {
        return new MysqlConfigBuilder();
    }

    /**
     * 根据url生成MysqlConfigBuilder
     *
     * @param url 例如：jdbc:mysql://localhost:3306/jykj?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC&useSSL=true
     * @return MysqlConfigBuilder
     */
    public static MysqlConfigBuilder builder(String url) {
        MysqlConfigBuilder builder = new MysqlConfigBuilder();
        SqlUtil.dbConfigFromUrl(builder, url);
        return builder;
    }

    /**
     * 格式化mysql的链接
     *
     * @return 例如：jdbc:mysql://localhost:3306/db_name?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC&useSSL=true
     */
    public String toUrl() {
        return String.format(URL_TEMPLATE, ip, port, db);
    }
}
