package com.jiazhifei.coder.core.dto;

import com.jiazhifei.coder.core.CoderUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
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
     * 提取模板信息中的参数，按照：${name}格式提取
     */
    public List<String> drawParam() {
        List<String> paramList = new ArrayList<>();
        if (CoderUtil.isEmpty(templateInfo)) {
            return paramList;
        }
        Matcher m = p.matcher(templateInfo);
        while (m.find()) {
            String group = m.group(0);
            paramList.add(group.substring(2, group.length() - 1));
        }
        return paramList;
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
