package com.jiazhifei.coder.enumeration.config;

import com.jiazhifei.coder.core.util.CoderUtil;
import com.jiazhifei.coder.enumeration.base.CodeTypeEnum;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author jkf
 * @date 2023-03-06 22:39:00
 * @description
 */
public class EnumConfigBuilder<T extends EnumConfigBuilder<T>> {
    /**
     * 作者
     */
    private String author;
    /**
     * 描述
     */
    private String desc;
    /**
     * java类名称
     */
    private String javaName;
    /**
     * 包路径
     */
    private String packagePath;

    /**
     * 是否覆盖
     */
    private boolean override;

    /**
     * 枚举项的类型
     */
    private CodeTypeEnum codeType;

    /**
     * 枚举项
     */
    private List<EnumItemConfig> configs = new ArrayList<>();

    public EnumConfigBuilder(CodeTypeEnum codeType) {
        this.codeType = codeType;

    }

    public T override(Boolean override) {
        this.override = override;
        return (T) this;
    }

    public T packagePath(String packagePath) {
        this.packagePath = packagePath;
        return (T) this;
    }

    public T author(String author) {
        this.author = author;
        return (T) this;
    }

    public T author(CodeTypeEnum codeType) {
        this.codeType = codeType;
        return (T) this;
    }

    public EnumConfig build(String javaName, String desc) {
        this.javaName = javaName;
        this.desc = desc;
        validateAllConfig();
        EnumConfig enumConfig = new EnumConfig();
        enumConfig.setCodeType(codeType);
        enumConfig.setAuthor(author);
        enumConfig.setDesc(desc);
        enumConfig.setOverride(override);
        enumConfig.setJavaName(javaName);
        enumConfig.setPackagePath(packagePath);
        enumConfig.setConfigs(configs);
        return enumConfig;
    }

    protected void enumConfig(CodeTypeEnum codeType, String key, Object code, String name, String desc) {
        EnumItemConfig enumConfig = new EnumItemConfig(codeType, key, code, name, desc);
        validateConfig(enumConfig);
        configs.add(enumConfig);
    }


    /**
     * String类型的枚举类
     */
    public static class StringEnumBuilder extends EnumConfigBuilder<EnumConfigBuilder.StringEnumBuilder> {

        public StringEnumBuilder() {
            super(CodeTypeEnum.STRING);
        }

        /**
         * 提供code、value、desc生成枚举项。
         */
        public StringEnumBuilder enumConfig(String code, String name, String desc) {
            super.enumConfig(CodeTypeEnum.STRING, code.toUpperCase(), code, name, desc);
            return this;
        }

        public StringEnumBuilder enumConfig(String code, String name) {
            return enumConfig(code, name, "");
        }
    }


    /**
     * Integer类型的枚举类
     */
    public static class IntegerEnumBuilder extends EnumConfigBuilder<EnumConfigBuilder.IntegerEnumBuilder> {

        public IntegerEnumBuilder() {
            super(CodeTypeEnum.INTEGER);
        }

        /**
         * 提供code、value、desc生成枚举项。
         */
        public IntegerEnumBuilder enumConfig(String key, Integer code, String name, String desc) {
            super.enumConfig(CodeTypeEnum.INTEGER, key, code, name, desc);
            return this;
        }

        public IntegerEnumBuilder enumConfig(String key, Integer code, String name) {
            return enumConfig(key, code, name, "");
        }
    }

    /**
     * Integer类型的枚举类
     */
    public static class LongEnumBuilder extends EnumConfigBuilder<EnumConfigBuilder.LongEnumBuilder> {

        public LongEnumBuilder() {
            super(CodeTypeEnum.LONG);
        }

        /**
         * 提供key、code、value、desc生成枚举项。
         * 例如：SUCCESS(200L,"成功","成功了")
         *
         * @param key  = SUCCESS
         * @param code = 200L
         * @param name = 成功
         * @param desc = 成功了
         */
        public LongEnumBuilder enumConfig(String key, Long code, String name, String desc) {
            super.enumConfig(CodeTypeEnum.LONG, key, code, name, desc);
            return this;
        }

        /**
         * 提供key、code、value、desc生成枚举项。
         * 例如：SUCCESS(200L,"成功","")
         *
         * @param key  = SUCCESS
         * @param code = 200L
         * @param name = 成功
         */
        public LongEnumBuilder enumConfig(String key, Long code, String name) {
            return enumConfig(key, code, name, "");
        }
    }


    private void validateConfig(EnumItemConfig config) {
        if (CoderUtil.isEmpty(config.getKey())) {
            throw new IllegalArgumentException("枚举项的key必填");
        }
        if (config.getCode() == null) {
            throw new IllegalArgumentException("枚举项的code必填");
        }
        if (CoderUtil.isEmpty((config.getName()))) {
            throw new IllegalArgumentException("枚举项的name必填");
        }
    }

    private void validateAllConfig() {
        if (CoderUtil.isEmpty(javaName)) {
            throw new IllegalArgumentException("枚举类的类名必填");
        }
        if (CoderUtil.isEmpty((desc))) {
            throw new IllegalArgumentException("枚举类[" + javaName + "]的描述必填");
        }
        if (configs.isEmpty()) {
            throw new IllegalArgumentException("枚举类[" + javaName + "]的枚举项必填必填");
        }
        Set<String> keys = new HashSet<>();
        Set<Object> codes = new HashSet<>();
        Set<String> names = new HashSet<>();
        for (EnumItemConfig config : configs) {
            validateConfig(config);
            if (!keys.add(config.getKey())) {
                throw new IllegalArgumentException("枚举类[" + javaName + "]的枚举key="
                        + config.getKey() + "已经存在");
            }
            if (!codes.add(config.getCode())) {
                throw new IllegalArgumentException("枚举类[" + javaName + "]的枚举code="
                        + config.getCode() + "已经存在");
            }
            if (!names.add(config.getName())) {
                throw new IllegalArgumentException("枚举类[" + javaName + "]的枚举name="
                        + config.getName() + "已经存在");
            }
        }
    }

}
