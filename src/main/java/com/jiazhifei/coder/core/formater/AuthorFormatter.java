package com.jiazhifei.coder.core.formater;

import com.jiazhifei.coder.core.config.JavaConfig;

/**
 * 处理AUTHOR参数
 *
 * @author jkf
 */
public class AuthorFormatter<T extends JavaConfig> implements ParamFormatter<T> {

    @Override
    public String param() {
        return "AUTHOR";
    }

    @Override
    public String parse(T t) {
        return t.getAuthor();
    }
}
