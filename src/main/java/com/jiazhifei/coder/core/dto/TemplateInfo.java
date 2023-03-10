package com.jiazhifei.coder.core.dto;

import com.jiazhifei.coder.core.exception.CoderException;
import com.jiazhifei.coder.core.formater.ParamFormatter;
import com.jiazhifei.coder.core.util.CoderUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 模板信息
 *
 * @author jiakunfeng
 */

public class TemplateInfo implements Serializable {
    private Pattern p = Pattern.compile("\\$\\{[^}]+}");
    /**
     * 模板信息
     */
    private String templateInfo;


    /**
     * 提取模板信息中的参数，按照：$name$格式提取
     */
    public List<String> drawParam() {
        List<String> paramList = new ArrayList<>();
        if (CoderUtil.isEmpty(templateInfo)) {
            return paramList;
        }
        String[] arr = templateInfo.split("\\" + ParamFormatter.PARAM_PLACEHOLDER);
        int len = arr.length;
        if (len == 1) {
            return paramList;
        }
        if (len % 2 != 1) {
            throw new CoderException("模板中的$不符合偶数，templateInfo=" + templateInfo);
        }
        for (int i = 0; i < len; i++) {
            if (i % 2 == 1) {
                paramList.add(CoderUtil.addPlaceholder(arr[i]));
            }
        }
        return paramList;
    }

    /**
     * 判断模板信息，是否有参数
     *
     * @return true=有参数
     */
    public boolean hasParam() {
        if (CoderUtil.isEmpty(templateInfo)) {
            return false;
        }
        String[] arr = templateInfo.split("\\" + ParamFormatter.PARAM_PLACEHOLDER);
        int len = arr.length;
        if (len % 2 != 0) {
            throw new CoderException("模板中的$不符合偶数，templateInfo=" + templateInfo);
        }
        return true;
    }


    public TemplateInfo(String templateInfo) {
        this.templateInfo = templateInfo;
    }

    public String getTemplateInfo() {
        return templateInfo;
    }

    public void setTemplateInfo(String templateInfo) {
        this.templateInfo = templateInfo;
    }

    public void replaceParam(String param, String pareseInfo) {
        this.templateInfo = templateInfo.replace(param, pareseInfo);
    }
}
