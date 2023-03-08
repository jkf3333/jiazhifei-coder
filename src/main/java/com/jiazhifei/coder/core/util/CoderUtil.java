package com.jiazhifei.coder.core.util;

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

    public static boolean isEmpty(String str) {
        return str == null || "".equals(str.trim());
    }

    public static boolean isEmpty(Collection coll) {
        return coll == null || coll.isEmpty();
    }

    public static boolean isNotEmpty(String str) {
        return str != null && !"".equals(str.trim());
    }
}
