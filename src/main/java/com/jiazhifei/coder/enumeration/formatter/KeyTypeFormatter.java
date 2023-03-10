package com.jiazhifei.coder.enumeration.formatter;

import com.jiazhifei.coder.core.formater.ParamFormatter;
import com.jiazhifei.coder.enumeration.config.EnumConfig;

/**
 * 解决KEY_TYPE
 *
 * @author jkf
 */
public class KeyTypeFormatter implements ParamFormatter<EnumConfig> {

    @Override
    public String param() {
        return "KEY_TYPE";
    }

    @Override
    public String parse(EnumConfig enumConfig) {
        return enumConfig.getCodeType().getName();
    }
}
