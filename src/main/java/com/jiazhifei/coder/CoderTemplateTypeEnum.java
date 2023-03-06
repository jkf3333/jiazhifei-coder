package com.jiazhifei.coder;

/**
 * 模板枚举
 *
 * @author jkf
 */
public enum CoderTemplateTypeEnum {

    ENUM_TEMPLATE("ENUM", "枚举", "META-INF/coder/template/EnumTemplate.java", "枚举类");

    private final String code;
    private final String name;
    private final String templatePath;
    private final String desc;


    CoderTemplateTypeEnum(String code, String name, String templatePath, String desc) {
        this.code = code;
        this.name = name;
        this.templatePath = templatePath;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getTemplatePath() {
        return templatePath;
    }

    public String getDesc() {
        return desc;
    }
}
