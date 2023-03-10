package com.jiazhifei.coder.core.util;

import com.jiazhifei.coder.core.formater.ParamFormatter;

import java.util.Collection;

/**
 * 工具类
 * 其中部分工具类 copy Hutool工具类，
 * 目的：为了保证pom的干净，copy部分相关源码
 *
 * @author jkf
 */
public class CoderUtil {

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
     * 参数，添加占位符
     *
     * @param param 参数，例如：name
     * @return 格式化之后的参数，例如：$name$
     */
    public static String addPlaceholder(String param) {
        return ParamFormatter.PARAM_PLACEHOLDER + param + ParamFormatter.PARAM_PLACEHOLDER;
    }

    /**
     * 移除占位符
     *
     * @param param 带有占位符的参数，例如：$name$
     * @return 去除占位符的参数，例如：name
     */
    public static String removePlaceholder(String param) {
        if (param.startsWith(ParamFormatter.PARAM_PLACEHOLDER)) {
            param = param.substring(1);
        }
        if (param.endsWith(ParamFormatter.PARAM_PLACEHOLDER)) {
            param = param.substring(0, param.length()-1);
        }
        return param;
    }
}
