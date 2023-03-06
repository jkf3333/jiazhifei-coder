package com.jiazhifei.coder.core.formater;

import com.jiazhifei.coder.core.config.JavaConfig;

/**
 * @author jkf
 * @date 2023-03-06 23:43:00
 * @description
 */
public abstract class AbstractFormatter<T extends JavaConfig> implements ParamFormatter<T> {
    @Override
    public boolean hasParam(String templateInfo) {
        return templateInfo.contains(support());
    }
}
