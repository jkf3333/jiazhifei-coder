package com.jiazhifei.coder.core.formater;

import com.jiazhifei.coder.core.config.JavaConfig;

/**
 * 处理DESC参数
 *
 * @author jkf
 */
public class DescFormatter<T extends JavaConfig> implements ParamFormatter<T> {

    @Override
    public String param() {
        return "DESC";
    }

    @Override
    public String parse(T t) {
        return t.getDesc();
    }
}
