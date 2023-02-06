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
                    " * ${authorAnnotation} ${author}\n" +
                    " * ${datetimeAnnotation} ${dateTime}\n" +
                    " * ${descriptionAnnotation} ${desc}\n" +
                    " */\n" +
                    "public enum ${className} {\n" +
                    "${enumList}\n" +
                    "\n" +
                    "    private final ${keyType} code;\n" +
                    "    private final String name;\n" +
                    "    private final String desc;\n" +
                    "\n" +
                    "    /**\n" +
                    "     * 输出枚举类的格式化信息\n" +
                    "     */\n" +
                    "    public static void main(String[] args) {\n" +
                    "        //输出枚举类的格式化信息\n" +
                    "        System.out.println(toFormatString());\n" +
                    "    }\n" +
                    "\n" +
                    "    /**\n" +
                    "     * 根据code获取枚举\n" +
                    "     */\n" +
                    "    public static ${className} parse(${keyType} code) {\n" +
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
                    "        return parse(code) != null;\n" +
                    "    }\n" +
                    "\n" +
                    "    /**\n" +
                    "     * 根据code获取name\n" +
                    "     */\n" +
                    "    public static String format(${keyType} code) {\n" +
                    "        ${className} codeEnum = parse(code);\n" +
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
                    "     * 枚举类信息格式化\n" +
                    "     *\n" +
                    "     * @return 枚举类格式化后的信息\n" +
                    "     */\n" +
                    "    public static String toFormatString() {\n" +
                    "        StringBuilder sb = new StringBuilder();\n" +
                    "        for (${className} codeEnum : ${className}.values()) {\n" +
                    "            sb.append(\",\" + codeEnum.getCode() + \"=\" + codeEnum.getName());\n" +
                    "            if (codeEnum.getDesc() != null && !\"\".equals(codeEnum.getDesc())) {\n" +
                    "                sb.append(\"(\" + codeEnum.getDesc() + \")\");\n" +
                    "            }\n" +
                    "        }\n" +
                    "        return \"${desc}:\" + sb.substring(1);\n" +
                    "    }\n" +
                    "\n" +
                    "}";

}
