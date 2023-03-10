package com.jiazhifei.coder.core.sql;

import com.jiazhifei.coder.core.exception.CoderException;
import com.jiazhifei.coder.core.sql.config.MysqlColumnInfo;
import com.jiazhifei.coder.core.sql.config.MysqlConfig;
import com.jiazhifei.coder.core.sql.config.MysqlConfigBuilder;
import com.jiazhifei.coder.core.sql.config.MysqlTableInfo;
import com.jiazhifei.coder.core.util.CoderUtil;

import java.sql.*;
import java.util.Properties;

/**
 * @author jkf
 * @date 2023-03-07 23:20:00
 * @description
 */
public class SqlUtil {
    /**
     * 查询sql
     */
    private final static String SELECT_TABLE_SQL = "select * from %s where 1=2 limit 1;";

    /**
     * 从url中，提取MysqlConfigBuilder
     * url参数：jdbc:mysql://localhost:3306/jykj?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC&useSSL=true
     *
     * @param url url链接
     * @return MysqlConfigBuilder
     */
    public static void dbConfigFromUrl(MysqlConfigBuilder builder, String url) {
        url = url.substring(13);
        int idxQuestionMark = url.indexOf("?");
        url = url.substring(0, idxQuestionMark);
        int idxColon = url.indexOf(":");
        builder.ip(url.substring(0, idxColon));
        int idxBias = url.indexOf("/");
        builder.db(url.substring(idxBias + 1));
        builder.port(url.substring(idxColon + 1, idxBias));
    }

    public static Connection getConnection(MysqlConfig config) {
        MyClassLoader classLoader = new MyClassLoader();
        classLoader.loadJar(config.getDriverPath());
        Driver mysqlDriver = null;
        try {
            mysqlDriver = (Driver) classLoader.loadClass(config.getDriverClass()).newInstance();
            Properties info = new Properties();
            info.setProperty("user", config.getName());
            info.setProperty("password", config.getPassword());
            return mysqlDriver.connect(config.toUrl(), info);
        } catch (InstantiationException e) {
            throw new CoderException("创建mysql driver对象失败，请检查jar是否正确", e);
        } catch (IllegalAccessException e) {
            throw new CoderException("创建mysql driver失败，请检查jar是否正确", e);
        } catch (ClassNotFoundException e) {
            throw new CoderException("没有找到mysql驱动类，请检查，mysql的驱动版本与驱动类是否一致");
        } catch (SQLException sqlException) {
            throw new CoderException("获取数据库连接失败，请检查相关url路径,url=" + config.toUrl(), sqlException);
        }
    }

    /**
     * 获取column信息
     *
     * @param config mysql配置信息
     * @param table  表名
     * @param column 字段
     * @return 字段信息
     */
    public static MysqlColumnInfo columnInfo(MysqlConfig config, String table, String column) {
        MysqlTableInfo tableInfo = tableInfo(config, table);
        if (tableInfo == null) {
            throw new CoderException("表不存在，table=" + table);
        }
        if (CoderUtil.isEmpty(tableInfo.getColumns())) {
            for (MysqlColumnInfo columnInfo : tableInfo.getColumns()) {
                if (column.equals(columnInfo.getName())) {
                    return columnInfo;
                }
            }
        }
        throw CoderException.error("表中字段不存在，请检查，table=" + table + ",column=" + column);

    }

    /**
     * 返回table信息
     *
     * @param config mysql配置信息
     * @param table  表名
     * @return 表信息
     */
    public static MysqlTableInfo tableInfo(MysqlConfig config, String table) {
        String sql = String.format(SELECT_TABLE_SQL, table);
        Connection connection = getConnection(config);
        try {
            ResultSet resultSet = connection.createStatement().executeQuery(sql);
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                MysqlColumnInfo column = new MysqlColumnInfo();
                column.setName(metaData.getColumnName(i));
                column.setComment("");
                System.out.println("getColumnName(i): " + metaData.getColumnName(i));
                System.out.println("getColumnLabel(i): " + metaData.getColumnLabel(i));
                System.out.println("getColumnClassName(i): " + metaData.getColumnClassName(i));
                System.out.println("getColumnType(i): " + metaData.getColumnType(i));
                System.out.println("getColumnTypeName(i): " + metaData.getColumnTypeName(i));
                System.out.println("getScale(i): " + metaData.getScale(i));
                System.out.println("isNullable(i): " + metaData.isNullable(i));
                System.out.println("isAutoIncrement(i): " + metaData.isAutoIncrement(i));
                System.out.println("getTableName(i): " + metaData.getTableName(i));
                System.out.println();


            }
            return new MysqlTableInfo();
        } catch (SQLException sqlException) {
            throw new CoderException("查询失败", sqlException);
        }
    }

    public static void main(String[] args) {
        MysqlConfig config = MysqlConfig
                .builder("jdbc:mysql://localhost:3306/jykj?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC&useSSL=true")
                .name("root")
                .password("jkf3333")
                .build();
        columnInfo(config, "test_jkf_rdw     ", "id");
    }
}
