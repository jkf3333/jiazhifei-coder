package com.jiazhifei.coder.core.util;

import java.io.File;
import java.util.Locale;

/**
 * 文件工具类
 *
 * @author jkf
 */
public class FileUtil {
    /**
     * classpath路径的常量
     */
    public final static String CLASS_PATH_PREFIX = "classpath:";
    /**
     * 文件路径中src的路径
     */
    private final static String SRC_PATH = "/src/main/java";
    /**
     * 文件路径中target路径
     */
    private final static String TARGET_PATH = "/target/classes";
    /**
     * java文件的后缀
     */
    private final static String JAVA_FILE_SUFFIX = ".java";

    /**
     * 根据包路径和类名，获取该类在项目中的绝对位置
     *
     * @param packageInfo 包信息，例如：com.jiazhifei.coder
     * @param javaName    类名，例如：Test.java
     * @return 类的绝对路径，例如：D:/user/common-jiazhifei-coder/src/main/java/com/jaizhifei/coder/Test.java
     */
    public static String javaProjectPath(String packageInfo, String javaName) {
        String rootPath = CoderUtil.class.getResource("/").getPath().substring(1);
        boolean notNeedJavaSuffix = javaName.endsWith(JAVA_FILE_SUFFIX) || javaName.endsWith(JAVA_FILE_SUFFIX.toUpperCase(Locale.ROOT));
        if (!notNeedJavaSuffix) {
            javaName += JAVA_FILE_SUFFIX;
        }
        return rootPath.replace(TARGET_PATH,
                SRC_PATH) +
                packageInfo.replace(".", File.separator)
                + File.separator + javaName;
    }


    /**
     * 移除路径中带有的classpath，
     *
     * @param path 路径 例如:classpath:/MET-INF/enum.txt
     * @return 去除后的路径，例如：MET-INF/enum.txt
     */
    public static String removeClassPath(String path) {
        if (CoderUtil.isEmpty(path)) {
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
}
