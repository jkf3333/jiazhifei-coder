package com.jiazhifei.coder.enumeration.formatter;

import com.jiazhifei.coder.core.formater.ParamFormatter;
import com.jiazhifei.coder.enumeration.config.EnumConfig;
import com.jiazhifei.coder.enumeration.config.EnumItemConfig;

import java.util.List;

/**
 * 解决ENUM_ITEM_LIST
 *
 * @author jkf
 */
public class EnumItemFormatter implements ParamFormatter<EnumConfig> {
    /**
     * 枚举类的模板
     */
    private static final String ENUM_LINE_TEMPLATE = "%s(%s, \"%s\", \"%s\")";

    @Override
    public String param() {
        return "ENUM_ITEM_LIST";
    }

    @Override
    public String parse(EnumConfig enumConfig) {
        StringBuilder sb = new StringBuilder();
        List<EnumItemConfig> itemList = enumConfig.getConfigs();
        int size = itemList.size();
        for (int i = 0; i < size; i++) {
            EnumItemConfig item = itemList.get(i);
            String itemStr = String.format(ENUM_LINE_TEMPLATE,
                    item.getKey(),
                    item.getKeyType().format(item.getCode()),
                    item.getName(),
                    item.getDesc());
            if (i != 0) {
                sb.append("    ");
            }
            if (i == size - 1) {
                //最后一行
                sb.append(itemStr).append(";");
            } else {
                sb.append(itemStr).append(",\n");
            }
        }
        return sb.toString();
    }
}
