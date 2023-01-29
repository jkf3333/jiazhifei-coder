package com.jiazhifei.util.enumeration.core;

/**
 * @author
 * @date 2023-01-15 22:40:00
 * @description
 */
public enum TestEnum {
    NAME("USER", "用户", "用户信息");

    private final String code;
    private final String name;
    private final String desc;

    /**
     * 根据code获取枚举
     */
    public static TestEnum of(String code) {
        for (TestEnum codeEnum : TestEnum.values()) {
            if (codeEnum.getCode().equals(code)) {
                return codeEnum;
            }
        }
        return null;
    }

    /**
     * 判断code是否存在
     *
     * @return true=存在
     */
    public static boolean isExist(String code) {
        TestEnum codeEnum = of(code);
        return codeEnum != null;
    }

    /**
     * 根据code获取name
     */
    public static String format(String code) {
        TestEnum codeEnum = of(code);
        if (codeEnum == null) {
            return "";
        } else {
            return codeEnum.getName();
        }
    }

    TestEnum(String code, String name, String desc) {
        this.name = name;
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    private String mkSql(String tableName, String columnInfo) {
        StringBuilder sb = new StringBuilder();
        for (TestEnum codeEnum : TestEnum.values()) {
            sb.append("," + codeEnum.getCode() + "=" + codeEnum.getName());
            if (codeEnum.getDesc() != null && !"".equals(codeEnum.getDesc())) {
                sb.append("(" + codeEnum.getDesc() + ")");
            }
        }
        return "alter table " + tableName + " modify " + columnInfo + " comment \" 测试:" + sb.toString() + "\" ";
    }

}
