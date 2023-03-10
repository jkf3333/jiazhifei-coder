package com.jiazhifei.coder.core.sql.config;

import java.io.Serializable;
import java.util.List;

/**
 * @author jkf
 */
public class MysqlTableInfo implements Serializable {
    /**
     * 表名
     */
    private String name;
    /**
     * 主键key的名称
     */
    private String keyColumnName;
    /**
     * 字段信息
     */
    private List<MysqlColumnInfo> columns;
    /**
     * 表的注释
     */
    private String comment;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKeyColumnName() {
        return keyColumnName;
    }

    public void setKeyColumnName(String keyColumnName) {
        this.keyColumnName = keyColumnName;
    }

    public List<MysqlColumnInfo> getColumns() {
        return columns;
    }

    public void setColumns(List<MysqlColumnInfo> columns) {
        this.columns = columns;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
