package com.jiazhifei.coder.enumeration.config;

import com.jiazhifei.coder.core.config.JavaConfig;
import com.jiazhifei.coder.enumeration.base.CodeTypeEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * 枚举配置
 *
 * @author jiakunfeng
 */
public class EnumConfig extends JavaConfig {

    /**
     * 枚举项的类型
     */
    private CodeTypeEnum codeType;

    /**
     * 枚举项
     */
    private List<EnumItemConfig> configs = new ArrayList<>();

    public CodeTypeEnum getCodeType() {
        return codeType;
    }

    public void setCodeType(CodeTypeEnum codeType) {
        this.codeType = codeType;
    }

    public List<EnumItemConfig> getConfigs() {
        return configs;
    }

    public void setConfigs(List<EnumItemConfig> configs) {
        this.configs = configs;
    }

    /**
     * 创建String类型的枚举
     */
    public static EnumConfigBuilder.StringEnumBuilder stringBuilder() {
        return new EnumConfigBuilder.StringEnumBuilder();
    }

    /**
     * 创建Integer类型的枚举
     */
    public static EnumConfigBuilder.IntegerEnumBuilder integerBuilder() {
        return new EnumConfigBuilder.IntegerEnumBuilder();
    }

    /**
     * 创建Long类型的枚举
     */
    public static EnumConfigBuilder.LongEnumBuilder longBuilder() {
        return new EnumConfigBuilder.LongEnumBuilder();
    }

}
