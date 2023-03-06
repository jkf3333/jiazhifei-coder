package com.jiazhifei.coder.core.coder;

import com.jiazhifei.coder.core.CoderUtil;
import com.jiazhifei.coder.core.config.JavaConfig;

import java.io.InputStream;

/**
 * 从classpath路径下加载模板的coder
 *
 * @author jkf
 */
public class ClassPathFileTemplateJavaCoder<T extends JavaConfig> extends AbstractFileTemplateJavaCoder<T> {
    private String templateFilePath;

    @Override
    public InputStream sourceInputStream() {
        InputStream is = this.getClass().getResourceAsStream(templateFilePath);
        if (is == null) {
            is = ClassLoader.getSystemResourceAsStream(templateFilePath);
        }

        return is;
    }

    /**
     * Enum模板文件的路径
     *
     * @param templateFilePath 模板文件的相对路径，默认从classpath路径下
     */
    public ClassPathFileTemplateJavaCoder(String templateFilePath) {
        //移除class path
        templateFilePath = CoderUtil.removeClassPath(templateFilePath);

        this.templateFilePath = templateFilePath;
    }
}
