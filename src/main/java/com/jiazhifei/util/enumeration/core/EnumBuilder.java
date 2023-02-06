package com.jiazhifei.util.enumeration.core;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
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

    /**
     * 枚举类的名称
     */
    private String className;

    /**
     * 日期注释，默认@datetime
     */
    private String datetimeAnnotation = "@datetime";

    /**
     * 作者注释，默认@author
     */
    private String authorAnnotation = "@author";

    /**
     * 作者
     */
    private String author = "";

    /**
     * 描述的注释，默认@description
     */
    private String descAnnotation = "@description";

    /**
     * 枚举类的说明
     */
    private String classDesc = "";


    /**
     * 作者，用于注释
     */
    public EnumBuilder author(String author) {
        this.author = author;
        return this;
    }

    /**
     * 作者注释，默认@author，也可以自定义@Author
     */
    public EnumBuilder authorAnnotation(String authorAnnotation) {
        this.authorAnnotation = authorAnnotation;
        return this;
    }

    /**
     * 描述注释，默认@description，也可以自定义
     */
    public EnumBuilder descAnnotation(String descAnnotation) {
        this.descAnnotation = descAnnotation;
        return this;
    }

    /**
     * 时间注释，默认datetime，也可以自定义
     */
    public EnumBuilder datetimeAnnotation(String datetimeAnnotation) {
        this.datetimeAnnotation = datetimeAnnotation;
        return this;
    }


    /**
     * 创建String类型的枚举
     *
     * @param className 枚举类的名称，例如：EnvironmentEnum
     * @param classDesc 枚举类的说明，例如：环境枚举
     */
    public static StringEnumBuilder stringBuilder(String className, String classDesc) {
        return new StringEnumBuilder(className, classDesc);
    }

    /**
     * 创建Integer类型的枚举
     *
     * @param className 枚举类的名称，例如：EnvironmentEnum
     * @param classDesc 枚举类的说明，例如：环境枚举
     */
    public static IntegerEnumBuilder integerBuilder(String className, String classDesc) {
        return new IntegerEnumBuilder(className, classDesc);
    }

    /**
     * 创建Long类型的枚举
     *
     * @param className 枚举类的名称，例如：EnvironmentEnum
     * @param classDesc 枚举类的说明，例如：环境枚举
     */
    public static LongEnumBuilder longBuilder(String className, String classDesc) {
        return new LongEnumBuilder(className, classDesc);
    }


    public void enumConfig(String key, Object code, String name, String desc) {
        EnumConfig enumConfig = new EnumConfig(key, code, name, desc);
        validateConfig(enumConfig);
        configs.add(enumConfig);
    }


    public void print() {
        System.out.println(createClassInfo());
        System.out.println();
        System.out.println();
        System.out.println();

    }

    /**
     * 创建对应的java文件，如果存在，重写
     *
     * @param projectAbsolutePath 项目的绝对路径，例如：F:\IdeaProjects\common-jkf-util
     *                            注意：common-jkf-util是项目的位置
     * @param packageInfo         包路径，例如：com.apache.common
     */
    public void rewrite(String projectAbsolutePath, String packageInfo) {
        write(projectAbsolutePath, packageInfo, true);
    }

    /**
     * 创建对应的java文件，如果存在，不重写
     *
     * @param projectAbsolutePath 项目的绝对路径，例如：F:\IdeaProjects\common-jkf-util
     *                            注意：common-jkf-util是项目的位置
     * @param packageInfo         包路径，例如：com.apache.common
     */
    public void write(String projectAbsolutePath, String packageInfo) {
        write(projectAbsolutePath, packageInfo, false);
    }

    /**
     * 创建对应的java文件
     *
     * @param projectAbsolutePath 项目的绝对路径，例如：F:\IdeaProjects\common-jkf-util
     *                            注意：common-jkf-util是项目的位置
     * @param packageInfo         包路径，例如：com.apache.common
     * @param rewrite             如果文件存在是否，重写
     */
    private void write(String projectAbsolutePath, String packageInfo, boolean rewrite) {
        //添加package
        String classInfo = "package " + packageInfo + ";"
                //换行符
                + System.getProperty("line.separator")
                + System.getProperty("line.separator")
                + createClassInfo();
        String javaFile = formatClassFile(projectAbsolutePath, packageInfo, className);
        File file = new File(javaFile);
        if (file.exists() && !rewrite) {
            throw new IllegalArgumentException(className + " 已经存在，请查看，具体路径：" + javaFile);
        }
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(classInfo.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("创建：" + javaFile + " 成功");
    }

    /**
     * 根据项目的绝对路径+包路径，生成java类
     */
    private String formatClassFile(String projectAbsolutePath, String packageInfo, String className) {
        //追加src\main\java，同时包路径转文件路径
        return projectAbsolutePath +
                File.separator + "src" + File.separator + "main" + File.separator + "java"
                + File.separator + packageInfo.replace(".", File.separator)
                + File.separator + className + ".java";
    }

    /**
     * 生成class信息
     */
    private String createClassInfo() {
        validateAllConfig();
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
        return EnumTemplateConstant.ENUM_TEMPLATE
                //描述注释
                .replaceAll("\\$\\{descriptionAnnotation\\}", this.descAnnotation)
                //描述
                .replaceAll("\\$\\{desc\\}", classDesc)
                //作者注释
                .replaceAll("\\$\\{authorAnnotation\\}", this.authorAnnotation)
                //作者
                .replaceAll("\\$\\{author\\}", author)
                //时间注释
                .replaceAll("\\$\\{datetimeAnnotation\\}", this.datetimeAnnotation)
                //时间
                .replaceAll("\\$\\{dateTime\\}", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()))
                //枚举类名称
                .replaceAll("\\$\\{className\\}", className)
                //枚举值
                .replaceAll("\\$\\{enumList\\}", enumSb.toString())
                //枚举类型
                .replaceAll("\\$\\{keyType\\}", keyType.getName());

    }


    public EnumBuilder(KeyTypeEnum keyType, String className, String classDesc) {
        this.keyType = keyType;
        this.classDesc = classDesc;
        this.className = className;
    }

    private void validateAllConfig() {
        if (isEmpty(className)) {
            throw new IllegalArgumentException("枚举类的类名必填");
        }
        if (isEmpty(classDesc)) {
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

        public StringEnumBuilder(String className, String classDesc) {
            super(KeyTypeEnum.STRING, className, classDesc);
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

        public IntegerEnumBuilder(String className, String classDesc) {
            super(KeyTypeEnum.INTEGER, className, classDesc);
        }

        /**
         * 提供code、value、desc生成枚举项。
         */
        public IntegerEnumBuilder enumConfig(String key, Integer code, String name, String desc) {
            super.enumConfig(key, code, name, desc);
            return this;
        }

        public IntegerEnumBuilder enumConfig(String key, Integer code, String name) {
            return enumConfig(key, code, name, "");
        }
    }

    /**
     * Integer类型的枚举类
     */
    public static class LongEnumBuilder extends EnumBuilder {

        public LongEnumBuilder(String className, String classDesc) {
            super(KeyTypeEnum.LONG, className, classDesc);
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
            return enumConfig(key, code, name, "");
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
