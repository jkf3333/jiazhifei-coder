package com.jiazhifei.coder.core.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

/**
 * Io 流相关工具类
 *
 * @author jkf
 */
public class IoUtil {
    /**
     * 缓冲大小
     */
    private static final int DEFAULT_BUFFER_SIZE = 2 << 12;


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
}
