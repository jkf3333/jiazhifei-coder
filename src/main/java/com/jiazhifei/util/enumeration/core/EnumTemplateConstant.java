package com.jiazhifei.util.enumeration.core;

/**
 * @author jkf
 * @date 2023-01-15 22:17:00
 * @description
 */
public class EnumTemplateConstant {
    /**
     * enum的模板常量
     */
    public static final String ENUM_LINE_TEMPLATE = "    %s(%s, \"%s\", \"%s\")";
    /**
     * 枚举项的模板常量
     */
    public static final String ENUM_TEMPLATE =
            "/**\n" +
                    " * @author ${author}\n" +
                    " * @date ${dateTime}\n" +
                    " * @description ${desc}\n" +
                    " */\n" +
                    "public enum ${className} {\n" +
                    "${enumList}\n" +
                    "\n" +
                    "    private final ${keyType} code;\n" +
                    "    private final String name;\n" +
                    "    private final String desc;\n" +
                    "\n" +
                    "   /**\n" +
                    "     * main自动生成更新字段的comment信息，当然如果需要的话\n" +
                    "     */\n" +
                    "    public static void main(String[] args) {\n" +
                    "        //例如：tableName=user_info，columnInfo = name varchar(32) not null default ''\n" +
                    "        //生成sql=alter table user_info modify column name varchar(32) not null default '' comment \"枚举变量的name\";\n" +
                    "        String sql = mkSql(\"\", \"\");\n" +
                    "        System.out.println(sql);\n" +
                    "    }\n" +
                    "\n" +
                    "    /**\n" +
                    "     * 根据code获取枚举\n" +
                    "     */\n" +
                    "    public static ${className} of(${keyType} code) {\n" +
                    "        for (${className} codeEnum : ${className}.values()) {\n" +
                    "            if (codeEnum.getCode().equals(code)) {\n" +
                    "                return codeEnum;\n" +
                    "            }\n" +
                    "        }\n" +
                    "        return null;\n" +
                    "    }\n" +
                    "\n" +
                    "    /**\n" +
                    "     * 判断code是否存在\n" +
                    "     *\n" +
                    "     * @return true=存在\n" +
                    "     */\n" +
                    "    public static boolean isExist(${keyType} code) {\n" +
                    "        return of(code) != null;\n" +
                    "    }\n" +
                    "\n" +
                    "    /**\n" +
                    "     * 根据code获取name\n" +
                    "     */\n" +
                    "    public static String format(${keyType} code) {\n" +
                    "        ${className} codeEnum = of(code);\n" +
                    "        if (codeEnum == null) {\n" +
                    "            return \"\";\n" +
                    "        } else {\n" +
                    "            return codeEnum.getName();\n" +
                    "        }\n" +
                    "    }\n" +
                    "\n" +
                    "    ${className}(${keyType} code, String name, String desc) {\n" +
                    "        this.name = name;\n" +
                    "        this.code = code;\n" +
                    "        this.desc = desc;\n" +
                    "    }\n" +
                    "\n" +
                    "    public ${keyType} getCode() {\n" +
                    "        return code;\n" +
                    "    }\n" +
                    "\n" +
                    "    public String getName() {\n" +
                    "        return name;\n" +
                    "    }\n" +
                    "\n" +
                    "    public String getDesc() {\n" +
                    "        return desc;\n" +
                    "    }\n" +
                    "\n" +
                    "    /**\n" +
                    "     * 生成sql\n" +
                    "     */\n" +
                    "    private static String mkSql(String tableName, String columnInfo) {\n" +
                    "        StringBuilder sb = new StringBuilder();\n" +
                    "        for (${className} codeEnum : ${className}.values()) {\n" +
                    "            sb.append(\",\" + codeEnum.getCode() + \"=\" + codeEnum.getName());\n" +
                    "            if (codeEnum.getDesc() != null && !\"\".equals(codeEnum.getDesc())) {\n" +
                    "                sb.append(\"(\" + codeEnum.getDesc() + \")\");\n" +
                    "            }\n" +
                    "        }\n" +
                    "        return \"alter table \" + tableName + \" modify column \" + columnInfo + \" comment \\\"${desc}:\" + sb.substring(1) + \"\\\";\";\n" +
                    "    }\n" +
                    "\n" +
                    "}";

}
