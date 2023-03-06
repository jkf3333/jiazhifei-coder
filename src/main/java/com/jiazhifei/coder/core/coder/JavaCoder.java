package com.jiazhifei.coder.core.coder;

import com.jiazhifei.coder.core.config.JavaConfig;

/**
 * 生成java类
 *
 * @author jiakunfeng
 */

public interface JavaCoder<T extends JavaConfig> {
    /**
     * 根据配置信息，输出java代码
     */
    String printJava(T t);

    /**
     * 根据配置信息，生成java文件
     */
    String writeJava(T t);
}
