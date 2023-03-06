package com.jiazhifei.coder.core.config;

import java.io.Serializable;

/**
 * 基础配置
 *
 * @author jiakunfeng
 */
public class JavaConfig implements Serializable {
    /**
     * 作者
     */
    private String author;
    /**
     * 描述
     */
    private String desc;
    /**
     * java名称
     */
    private String javaName;
    /**
     * 包路径
     */
    private String packagePath;

    /**
     * 是否覆盖
     */
    private boolean override;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getJavaName() {
        return javaName;
    }

    public void setJavaName(String javaName) {
        this.javaName = javaName;
    }

    public String getPackagePath() {
        return packagePath;
    }

    public void setPackagePath(String packagePath) {
        this.packagePath = packagePath;
    }

    public boolean isOverride() {
        return override;
    }

    public void setOverride(boolean override) {
        this.override = override;
    }
}
