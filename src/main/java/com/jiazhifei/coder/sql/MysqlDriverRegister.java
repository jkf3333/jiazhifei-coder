package com.jiazhifei.coder.sql;

import java.net.URL;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author jkf
 * @date 2023-02-23 22:47:00
 * @description
 */
public class MysqlDriverRegister {
    public static void main(String[] args) {
        registryDriver("F:\\repositories\\mysql\\mysql-connector-java\\5.0.8/mysql-connector-java-5.0.8.jar","com.mysql.jdbc.Driver");
    }
    public static void registryDriver(String jarPath, String className) {
        try {
            ClassLoader sourceLoader = ClassLoader.getSystemClassLoader();
            URL jarUrl = sourceLoader.getResource(jarPath);
            ClassLoader classLoader = new MyClassLoader(jarUrl, sourceLoader);
            Driver mysqlDriver = (Driver) classLoader.loadClass(className).newInstance();
            DriverManager.registerDriver(mysqlDriver);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
