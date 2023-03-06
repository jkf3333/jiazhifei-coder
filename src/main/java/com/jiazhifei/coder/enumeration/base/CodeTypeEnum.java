package com.jiazhifei.coder.enumeration.base;

/**
 * 枚举类的code类型
 *
 * @author jkf
 */
public enum CodeTypeEnum implements CodeTypeFormat {
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

    CodeTypeEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }


}

