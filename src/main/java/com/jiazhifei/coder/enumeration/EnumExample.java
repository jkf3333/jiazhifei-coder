package com.jiazhifei.coder.enumeration;

import com.jiazhifei.coder.enumeration.config.EnumConfig;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author jkf
 * @date 2023-01-15 21:59:00
 * @description 生成枚举类的样例
 */
public class EnumExample {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        EnumJavaCoder.getInstance()
                .printJava(EnumConfig
                        .integerBuilder()
                        .enumConfig("NORMAL", 1, "正常","默认状态")
                        .enumConfig("DELETE", -1, "删除")
                        .author("jkf")
                        .override(true)
                        .packagePath("com.jiazhifei.coder.example")
                        .build("StatusEnum", "状态枚举"));
    }

//    private static void printClassInfo(Class cla) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
//        Method[] declaredMethods = cla.getDeclaredMethods();
//        for (Method declaredMethod : declaredMethods) {
//            if ("mkSql".equals(declaredMethod.getName())) {
//                declaredMethod.setAccessible(true);
//                Object invoke = declaredMethod.invoke(null, "", "");
//                System.out.println(invoke);
//            }
//        }
//    }
//
//    /**
//     * 生成字符串枚举类
//     */
//    private static void stringPrintExample() {
//        EnumConfigBuilder.stringBuilder("EnvironmentEnum", "环境枚举")
//                .enumConfig("test", "测试", "测试环境")
//                .enumConfig("dev", "开发环境")
//                .print();
//
//    }
//
//    /**
//     * 生成字符串枚举类
//     */
//    private static void stringWriteExample() {
//        EnumConfigBuilder.stringBuilder("EnvironmentEnum", "环境枚举")
//                .enumConfig("test", "测试", "测试环境")
//                .enumConfig("dev", "开发环境")
//                .write("F:\\IdeaProjects\\common-jkf-util", "com.jiazhifei");
//
//    }
//
//    private static void stringRewriteExample() {
//        EnumConfigBuilder.stringBuilder("EnvironmentEnum", "环境枚举")
//                .enumConfig("test", "测试", "测试环境")
//                .enumConfig("dev", "开发环境")
//                .rewrite("F:\\IdeaProjects\\common-jkf-util", "com.jiazhifei");
//
//    }
//
//
//    public static void integerWriteExample() {
//        EnumConfigBuilder.integerBuilder("StatusEnum", "用户状态枚举")
//                .enumConfig("NORMAL", 1, "正常")
//                .enumConfig("DELETE", -1, "删除")
//                .author("jkf")
//                .write("F:\\IdeaProjects\\common-jkf-util", "com.jiazhifei");
//    }
//
//    public static void integerRewriteExample() {
//        EnumConfigBuilder.integerBuilder("StatusEnum", "用户状态枚举")
//                .enumConfig("NORMAL", 1, "正常")
//                .enumConfig("DELETE", -1, "删除")
//                .author("jkf")
//                .rewrite("F:\\IdeaProjects\\common-jkf-util", "com.jiazhifei");
//    }
//
//    public static void integerPrintExample() {
//        EnumConfigBuilder.integerBuilder("StatusEnum", "用户状态枚举")
//                .enumConfig("NORMAL", 1, "正常")
//                .enumConfig("DELETE", -1, "删除")
//                .author("jkf")
//                .print();
//    }
//
//    public static void longPrintExample() {
//        EnumConfigBuilder.longBuilder("ErrorCodeEnum", "错误码")
//                .enumConfig("OK", 1000L, "成功")
//                .enumConfig("SYSTEM_ERROR", 5000L, "系统异常")
//                .enumConfig("NETWORK_ERROR", 3000L, "网络异常")
//                .print();
//    }
//
//    public static void longRewriteExample() {
//        EnumConfigBuilder.longBuilder("ErrorCodeEnum", "错误码")
//                .enumConfig("OK", 1000L, "成功")
//                .enumConfig("SYSTEM_ERROR", 5000L, "系统异常")
//                .enumConfig("NETWORK_ERROR", 3000L, "网络异常")
//                .rewrite("F:\\IdeaProjects\\common-jkf-util", "com.jiazhifei");
//    }
//
//    public static void longWriteExample() {
//        EnumConfigBuilder.longBuilder("ErrorCodeEnum", "错误码")
//                .enumConfig("OK", 1000L, "成功")
//                .enumConfig("SYSTEM_ERROR", 5000L, "系统异常")
//                .enumConfig("NETWORK_ERROR", 3000L, "网络异常")
//                .write("F:\\IdeaProjects\\common-jkf-util", "com.jiazhifei");
//    }
}
