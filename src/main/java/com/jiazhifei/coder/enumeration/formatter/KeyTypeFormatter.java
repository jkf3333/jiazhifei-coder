package com.jiazhifei.coder.enumeration.formatter;

import com.jiazhifei.coder.core.formater.AbstractFormatter;
import com.jiazhifei.coder.enumeration.config.EnumConfig;
import com.jiazhifei.coder.enumeration.config.EnumItemConfig;

import java.util.List;

/**
 * 解决KEY_TYPE
 * @author jkf
 */
public class KeyTypeFormatter extends AbstractFormatter<EnumConfig> {

    @Override
    public String support() {
        return "KEY_TYPE";
    }

    @Override
    public String parse(EnumConfig enumConfig) {
        return enumConfig.getCodeType().getName();
    }
}
