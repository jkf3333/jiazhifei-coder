package com.jkf.util.enumeration.core;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author jkf
 * @date 2023-01-15 21:57:00
 * @description
 */
public class EnumBuilder {
    /**
     * 枚举项
     */
    private List<EnumConfig> configs = new ArrayList<>();
    /**
     * 枚举项的类型
     */
    private KeyTypeEnum keyType;

    public static StringEnumBuilder stringBuilder() {
        return new StringEnumBuilder();
    }

    public static IntegerEnumBuilder integerBuilder() {
        return new IntegerEnumBuilder();
    }

    public static LongEnumBuilder longBuilder() {
        return new LongEnumBuilder();
    }

    public void enumConfig(String key, Object code, String name, String desc) {
        EnumConfig enumConfig = new EnumConfig(key, code, name, desc);
        validateConfig(enumConfig);
        configs.add(enumConfig);
    }

    public void print(String className, String desc) {
        print(className, desc, "");
    }

    public void print(String className, String desc, String author) {
        validateAllConfig(className, desc);
        StringBuilder enumSb = new StringBuilder();
        int size = configs.size();
        //组装枚举项
        for (int i = 0; i < size; i++) {
            EnumConfig enumConfig = configs.get(i);
            if (i == size - 1) {
                //最后一项
                enumSb.append(enumConfig.toEnumString() + ";");
            } else {
                enumSb.append(enumConfig.toEnumString() + ",\n");
            }
        }
        //组装枚举类
        String classInfo = EnumTemplateConstant.ENUM_TEMPLATE
                .replaceAll("\\$\\{desc\\}", desc)
                .replaceAll("\\$\\{author\\}", author)
                .replaceAll("\\$\\{dateTime\\}", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()))
                .replaceAll("\\$\\{className\\}", className)
                .replaceAll("\\$\\{enumList\\}", enumSb.toString())
                .replaceAll("\\$\\{keyType\\}", keyType.getName());

        System.out.println(classInfo);
        System.out.println();
        System.out.println();
        System.out.println();

    }


    public EnumBuilder(KeyTypeEnum keyType) {
        this.keyType = keyType;
    }

    private void validateAllConfig(String className, String desc) {
        if (isEmpty(className)) {
            throw new IllegalArgumentException("枚举类的类名必填");
        }
        if (isEmpty(desc)) {
            throw new IllegalArgumentException("枚举类[" + className + "]的描述必填");
        }
        if (configs.isEmpty()) {
            throw new IllegalArgumentException("枚举类[" + className + "]的枚举项必填必填");
        }
        Set<String> keys = new HashSet<>();
        Set<Object> codes = new HashSet<>();
        Set<String> names = new HashSet<>();
        for (EnumConfig config : configs) {
            validateConfig(config);
            if (!keys.add(config.getKey())) {
                throw new IllegalArgumentException("枚举类[" + className + "]的枚举key="
                        + config.getKey() + "已经存在");
            }
            if (!codes.add(config.getCode())) {
                throw new IllegalArgumentException("枚举类[" + className + "]的枚举code="
                        + config.getCode() + "已经存在");
            }
            if (!names.add(config.getName())) {
                throw new IllegalArgumentException("枚举类[" + className + "]的枚举name="
                        + config.getName() + "已经存在");
            }
        }
    }

    private void validateConfig(EnumConfig config) {
        if (isEmpty(config.getKey())) {
            throw new IllegalArgumentException("枚举项的key必填");
        }
        if (config.getCode() == null) {
            throw new IllegalArgumentException("枚举项的code必填");
        }
        if (isEmpty(config.getName())) {
            throw new IllegalArgumentException("枚举项的name必填");
        }
    }

    private boolean isEmpty(String value) {
        return value == null || "".equals(value);
    }

    /**
     * String类型的枚举类
     */
    public static class StringEnumBuilder extends EnumBuilder {

        public StringEnumBuilder() {
            super(KeyTypeEnum.STRING);
        }

        /**
         * 提供code、value、desc生成枚举项。
         */
        public StringEnumBuilder enumConfig(String code, String name, String desc) {
            super.enumConfig(code.toUpperCase(), code, name, desc);
            return this;
        }

        public StringEnumBuilder enumConfig(String code, String name) {
            return enumConfig(code, name, "");
        }
    }


    /**
     * Integer类型的枚举类
     */
    public static class IntegerEnumBuilder extends EnumBuilder {

        public IntegerEnumBuilder() {
            super(KeyTypeEnum.INTEGER);
        }

        /**
         * 提供code、value、desc生成枚举项。
         */
        public IntegerEnumBuilder enumConfig(String key, Integer code, String name, String desc) {
            super.enumConfig(key, code, name, desc);
            return this;
        }

        public IntegerEnumBuilder enumConfig(String key, Integer code, String name) {
            return enumConfig(key, code, name,"");
        }
    }

    /**
     * Integer类型的枚举类
     */
    public static class LongEnumBuilder extends EnumBuilder {

        public LongEnumBuilder() {
            super(KeyTypeEnum.LONG);
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
            super.enumConfig(key, code, name, desc);
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
            return enumConfig(key, code, name,"");
        }
    }

    /**
     * 枚举项的配置
     */
    class EnumConfig {
        private String key;
        private Object code;
        private String name;
        private String desc;

        public EnumConfig(String key, Object code, String name, String desc) {
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

        /**
         * 枚举项的配置，转化成一行行的代码
         */
        public String toEnumString() {
            return String.format(EnumTemplateConstant.ENUM_LINE_TEMPLATE, key, keyType.format(code), name, desc);
        }
    }
}
