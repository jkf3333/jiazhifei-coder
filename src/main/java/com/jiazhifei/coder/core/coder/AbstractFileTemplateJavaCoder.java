package com.jiazhifei.coder.core.coder;

import com.jiazhifei.coder.core.CoderUtil;
import com.jiazhifei.coder.core.config.JavaConfig;

import java.io.IOException;
import java.io.InputStream;

/**
 * 通过模板文件生成代码
 *
 * @author jkf
 */
public abstract class AbstractFileTemplateJavaCoder<T extends JavaConfig> extends AbstractJavaCoder<T> {

    @Override
    protected String templateInfo(T t) {
        InputStream is = sourceInputStream();
        try {
            return CoderUtil.readUtf8(is);
        } catch (IOException e) {
            throw new IllegalArgumentException("文件读取失败，可能原因：项目还未构建，请构建。", e);
        }
    }


    /**
     * 返回模板文件的字节流
     * 子类重写
     */
    abstract InputStream sourceInputStream();
}
