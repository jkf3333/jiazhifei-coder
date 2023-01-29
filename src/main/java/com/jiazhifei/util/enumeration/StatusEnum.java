package com.jiazhifei.util.enumeration;

/**
 * @author jkf
 * @date 2023-01-29 22:07:05
 * @description 用户状态枚举
 */
public enum StatusEnum {
    NORMAL(1, "正常", ""),
    DELETE(-1, "删除", "");

    private final Integer code;
    private final String name;
    private final String desc;

    /**
     * main自动生成更新字段的comment信息，当然如果需要的话
     */
    public static void main(String[] args) {
        //例如：tableName=user_info，columnInfo = name varchar(32) not null default ''
        //生成sql=alter table user_info modify column name varchar(32) not null default '' comment "枚举变量的name";
        String sql = mkSql("", "");
        System.out.println(sql);
    }

    /**
     * 根据code获取枚举
     */
    public static StatusEnum of(Integer code) {
        for (StatusEnum codeEnum : StatusEnum.values()) {
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
    public static boolean isExist(Integer code) {
        return of(code) != null;
    }

    /**
     * 根据code获取name
     */
    public static String format(Integer code) {
        StatusEnum codeEnum = of(code);
        if (codeEnum == null) {
            return "";
        } else {
            return codeEnum.getName();
        }
    }

    StatusEnum(Integer code, String name, String desc) {
        this.name = name;
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    /**
     * 生成sql
     */
    private static String mkSql(String tableName, String columnInfo) {
        StringBuilder sb = new StringBuilder();
        for (StatusEnum codeEnum : StatusEnum.values()) {
            sb.append("," + codeEnum.getCode() + "=" + codeEnum.getName());
            if (codeEnum.getDesc() != null && !"".equals(codeEnum.getDesc())) {
                sb.append("(" + codeEnum.getDesc() + ")");
            }
        }
        return "alter table " + tableName + " modify column " + columnInfo + " comment \"用户状态枚举:" + sb.substring(1) + "\";";
    }

}



