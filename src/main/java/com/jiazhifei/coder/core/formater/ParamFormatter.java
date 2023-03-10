package com.jiazhifei.coder.core.formater;

import com.jiazhifei.coder.core.config.JavaConfig;
import com.jiazhifei.coder.core.util.CoderUtil;

/**
 * @author jkf
 * @date 2023-02-24 22:34:00
 * @description
 */
public interface ParamFormatter<T extends JavaConfig> {
    /**
     * 参数的占位符
     */
    String PARAM_PLACEHOLDER = "$";

    /**
     * 支持的参数，前后必须带有$$，这样可以提取参数
     *
     * @return 参数，例如：$name$
     */
    default String support() {
        return CoderUtil.addPlaceholder(param());
    }

    /**
     * 返回参数
     *
     * @return 支持的参数，例如:name
     */
    String param();

    /**
     * 根据配置信息，返回参数的替代
     *
     * @param t 配置信息
     * @return 替代数据
     */
    String parse(T t);


}
