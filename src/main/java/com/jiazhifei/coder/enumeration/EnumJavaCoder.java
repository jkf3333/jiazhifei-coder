package com.jiazhifei.coder.enumeration;

import com.jiazhifei.coder.CoderTemplateTypeEnum;
import com.jiazhifei.coder.core.coder.ClassPathFileTemplateJavaCoder;
import com.jiazhifei.coder.core.formater.ParamFormatter;
import com.jiazhifei.coder.enumeration.config.EnumConfig;
import com.jiazhifei.coder.enumeration.formatter.EnumItemFormatter;
import com.jiazhifei.coder.enumeration.formatter.KeyTypeFormatter;

import java.util.List;

/**
 * @author jkf
 * @date 2023-02-28 22:59:00
 * @description
 */
public class EnumJavaCoder extends ClassPathFileTemplateJavaCoder<EnumConfig> {
    public EnumJavaCoder(String filePath) {
        super(filePath);
    }

    @Override
    protected List<ParamFormatter> initFormatter(EnumConfig enumConfig) {
        List<ParamFormatter> formatters = super.initFormatter(enumConfig);
        //添加自己的转化
        formatters.add(new EnumItemFormatter());
        formatters.add(new KeyTypeFormatter());
        return formatters;
    }

    /**
     * 默认的的Enum枚举类的模板来源：META-INF/coder/template/EnumTemplate.txt
     * 用户可以通过EnumJavaCoder的构造函数，指定classpath路径下的模板文件，
     * 也可以通过META-INF/coder/template/EnumTemplate.txt，创建同名文件进行覆盖
     */
    public static EnumJavaCoder getInstance() {
        return new EnumJavaCoder(CoderTemplateTypeEnum.ENUM_TEMPLATE.getTemplatePath());
    }
}
