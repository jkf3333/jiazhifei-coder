package com.jiazhifei.coder.core.sql.config;

import java.io.Serializable;

/**
 * mysql的column信息
 *
 * @author jkf
 */

public class MysqlColumnInfo implements Serializable {
    /**
     * 字段名称
     */
    private String name;
    /**
     * 注释
     */
    private String comment;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
