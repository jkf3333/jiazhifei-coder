package com.jiazhifei.coder.core.formater;

import com.jiazhifei.coder.core.config.JavaConfig;

/**
 * @author jkf
 * @date 2023-02-24 22:34:00
 * @description
 */
public interface ParamFormatter<T extends JavaConfig> {
    /**
     * 支持的参数
     *
     * @return 参数，例如：name
     */
    String support();

    /**
     * 根据配置信息，返回参数的替代
     *
     * @param t 配置信息
     * @return 替代数据
     */
    String parse(T t);

    /**
     * 判断模板信息，是否包含待转换的参数
     *
     * @param templateInfo 模板信息，
     *                     例如：support=AUTHOR，
     *                     如果templateInfo=你好AUTHOR
     *                     返回true
     * @return true=包含
     */
    boolean hasParam(String templateInfo);
}
