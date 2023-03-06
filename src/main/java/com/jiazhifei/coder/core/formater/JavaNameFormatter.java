package com.jiazhifei.coder.core.formater;

import com.jiazhifei.coder.core.config.JavaConfig;

/**
 * 处理JAVA_NAME参数
 *
 * @author jkf
 */
public class JavaNameFormatter<T extends JavaConfig> extends AbstractFormatter<T>  {
    @Override
    public String support() {
        return "JAVA_NAME";
    }

    @Override
    public String parse(T t) {
        return t.getJavaName();
    }
}
