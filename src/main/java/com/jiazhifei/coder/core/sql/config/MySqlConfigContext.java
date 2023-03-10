package com.jiazhifei.coder.core.sql.config;

/**
 * mysqlConfig 上下文
 * @author jkf
 */
public class MySqlConfigContext {
    private static ThreadLocal<MysqlConfig> TL = new ThreadLocal<>();

    public static void set(MysqlConfig config) {
        TL.set(config);
    }

    public static MysqlConfig get() {
        return TL.get();
    }

    public static void remove() {
        TL.remove();
    }

}
