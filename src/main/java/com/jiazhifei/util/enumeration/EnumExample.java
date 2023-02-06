package com.jiazhifei.util.enumeration;

import com.jiazhifei.util.enumeration.core.EnumBuilder;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author jkf
 * @date 2023-01-15 21:59:00
 * @description 生成枚举类的样例
 */
public class EnumExample {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        stringPrintExample();
        stringWriteExample();
        stringRewriteExample();
        integerPrintExample();
        integerWriteExample();
        integerRewriteExample();
        longPrintExample();
        longWriteExample();
        longRewriteExample();
    }

    private static void printClassInfo(Class cla) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method[] declaredMethods = cla.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            if ("mkSql".equals(declaredMethod.getName())) {
                declaredMethod.setAccessible(true);
                Object invoke = declaredMethod.invoke(null, "", "");
                System.out.println(invoke);
            }
        }
    }

    /**
     * 生成字符串枚举类
     */
    private static void stringPrintExample() {
        EnumBuilder.stringBuilder("EnvironmentEnum", "环境枚举")
                .enumConfig("test", "测试", "测试环境")
                .enumConfig("dev", "开发环境")
                .print();

    }

    /**
     * 生成字符串枚举类
     */
    private static void stringWriteExample() {
        EnumBuilder.stringBuilder("EnvironmentEnum", "环境枚举")
                .enumConfig("test", "测试", "测试环境")
                .enumConfig("dev", "开发环境")
                .write("F:\\IdeaProjects\\common-jkf-util", "com.jiazhifei");

    }

    private static void stringRewriteExample() {
        EnumBuilder.stringBuilder("EnvironmentEnum", "环境枚举")
                .enumConfig("test", "测试", "测试环境")
                .enumConfig("dev", "开发环境")
                .rewrite("F:\\IdeaProjects\\common-jkf-util", "com.jiazhifei");

    }


    public static void integerWriteExample() {
        EnumBuilder.integerBuilder("StatusEnum", "用户状态枚举")
                .enumConfig("NORMAL", 1, "正常")
                .enumConfig("DELETE", -1, "删除")
                .author("jkf")
                .write("F:\\IdeaProjects\\common-jkf-util", "com.jiazhifei");
    }

    public static void integerRewriteExample() {
        EnumBuilder.integerBuilder("StatusEnum", "用户状态枚举")
                .enumConfig("NORMAL", 1, "正常")
                .enumConfig("DELETE", -1, "删除")
                .author("jkf")
                .rewrite("F:\\IdeaProjects\\common-jkf-util", "com.jiazhifei");
    }

    public static void integerPrintExample() {
        EnumBuilder.integerBuilder("StatusEnum", "用户状态枚举")
                .enumConfig("NORMAL", 1, "正常")
                .enumConfig("DELETE", -1, "删除")
                .author("jkf")
                .print();
    }

    public static void longPrintExample() {
        EnumBuilder.longBuilder("ErrorCodeEnum", "错误码")
                .enumConfig("OK", 1000L, "成功")
                .enumConfig("SYSTEM_ERROR", 5000L, "系统异常")
                .enumConfig("NETWORK_ERROR", 3000L, "网络异常")
                .print();
    }

    public static void longRewriteExample() {
        EnumBuilder.longBuilder("ErrorCodeEnum", "错误码")
                .enumConfig("OK", 1000L, "成功")
                .enumConfig("SYSTEM_ERROR", 5000L, "系统异常")
                .enumConfig("NETWORK_ERROR", 3000L, "网络异常")
                .datetimeAnnotation("#datetime")
                .descAnnotation("@desc")
                .authorAnnotation("#author")
                .rewrite("F:\\IdeaProjects\\common-jkf-util", "com.jiazhifei");
    }

    public static void longWriteExample() {
        EnumBuilder.longBuilder("ErrorCodeEnum", "错误码")
                .enumConfig("OK", 1000L, "成功")
                .enumConfig("SYSTEM_ERROR", 5000L, "系统异常")
                .enumConfig("NETWORK_ERROR", 3000L, "网络异常")
                .datetimeAnnotation("#datetime")
                .descAnnotation("@desc")
                .authorAnnotation("#author")
                .write("F:\\IdeaProjects\\common-jkf-util", "com.jiazhifei");
    }
}
