package com.jiazhifei.coder.enumeration.config;

import com.jiazhifei.coder.enumeration.base.EnumTemplateConstant;
import com.jiazhifei.coder.enumeration.base.CodeTypeEnum;

import java.io.Serializable;

/**
 * 枚举项的配置
 *
 * @author jiakunfeng
 */
public class EnumItemConfig implements Serializable {
    private String key;
    private Object code;
    private String name;
    private String desc;
    private CodeTypeEnum codeType;

    public EnumItemConfig(CodeTypeEnum codeType, String key, Object code, String name, String desc) {
        this.codeType = codeType;
        this.key = key;
        this.code = code;
        this.name = name;
        this.desc = desc;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getCode() {
        return code;
    }

    public void setCode(Object code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public CodeTypeEnum getKeyType() {
        return codeType;
    }

    public void setKeyType(CodeTypeEnum codeType) {
        this.codeType = codeType;
    }

    /**
     * 枚举项的配置，转化成一行行的代码
     */
    public String toEnumString() {
        return String.format(EnumTemplateConstant.ENUM_LINE_TEMPLATE, key, codeType.format(code), name, desc);
    }
}