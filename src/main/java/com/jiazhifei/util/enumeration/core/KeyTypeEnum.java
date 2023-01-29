package com.jiazhifei.util.enumeration.core;

/**
 * @author jkf
 * @date 2023-01-15 21:52:00
 * @description 枚举类的key类型
 */
public enum KeyTypeEnum implements KeyTypeFormat {
    //Long 类型
    LONG("Long") {
        @Override
        public String format(Object value) {
            return value + "L";
        }
    },
    //Integer 类型
    INTEGER("Integer") {
        @Override
        public String format(Object value) {
            return value + "";
        }
    },
    //String 类型
    STRING("String") {
        @Override
        public String format(Object value) {
            return "\"" + value + "\"";
        }
    };
    private final String name;

    KeyTypeEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }


}

interface KeyTypeFormat {
    String format(Object value);
}