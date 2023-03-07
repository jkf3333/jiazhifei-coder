package com.jiazhifei.coder.core;

import java.io.*;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Locale;

/**
 * 工具类
 * 其中部分工具类 copy Hutool工具类，
 * 目的：为了保证pom的干净，copy部分相关源码
 *
 * @author jkf
 */
public class CoderUtil {
    /**
     * classpath路径的常量
     */
    private final static String CLASS_PATH_PREFIX = "classpath:";
    /**
     * 缓冲大小
     */
    public static final int DEFAULT_BUFFER_SIZE = 2 << 12;

    public static boolean isEmpty(String str) {
        return str == null || "".equals(str.trim());
    }

    public static boolean isEmpty(Collection coll) {
        return coll == null || coll.isEmpty();
    }

    public static boolean isNotEmpty(String str) {
        return str != null && !"".equals(str.trim());
    }

    /**
     * 根据包路径和类名，获取该类在项目中的绝对位置
     *
     * @param packageInfo 包信息，例如：com.jiazhifei.coder
     * @param className   类名，例如：Test.java
     * @return 类的绝对路径，例如：D:/user/common-jiazhifei-coder/src/main/java/com/jaizhifei/coder/Test.java
     */
    public static String javaProjectPath(String packageInfo, String className) {
        String rootPath = CoderUtil.class.getResource("/").getPath().substring(1);
        return rootPath.replace("/target/classes",
                "/src/main/java") +
                packageInfo.replace(".", File.separator)
                + File.separator + className + ".java";
    }

    /**
     * 根据编码规则，读取流，转化为字符串
     * 默认编码utf-8
     *
     * @param is 字节流
     * @return 字符串
     */
    public static String readUtf8(InputStream is) throws IOException {
        final StringBuilder builder = new StringBuilder();
        final CharBuffer buffer = CharBuffer.allocate(DEFAULT_BUFFER_SIZE);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, Charset.defaultCharset()));
        try {
            while (-1 != reader.read(buffer)) {
                builder.append(buffer.flip());
            }
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return builder.toString();
    }

    /**
     * 移除路径中带有的classpath，
     *
     * @param path 路径 例如:classpath:/MET-INF/enum.txt
     * @return 去除后的路径，例如：MET-INF/enum.txt
     */
    public static String removeClassPath(String path) {
        if (isEmpty(path)) {
            return path;
        }
        if (path.startsWith(CLASS_PATH_PREFIX)) {
            return path.substring(CLASS_PATH_PREFIX.length());
        }
        if (path.startsWith(CLASS_PATH_PREFIX.toUpperCase(Locale.ROOT))) {
            return path.substring(CLASS_PATH_PREFIX.length());
        }
        return path;
    }

    public static void main(String[] args) {
        System.out.println(removeClassPath("classpath:/MET-INF/enum.txt"));
    }
}
