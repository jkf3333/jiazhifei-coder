package com.jiazhifei.coder.core.formater;

import com.jiazhifei.coder.core.config.JavaConfig;

/**
 * 处理PACKAGE_PATH参数
 *
 * @author jkf
 */
public class PackagePathFormatter<T extends JavaConfig> extends AbstractFormatter<T> {
    @Override
    public String support() {
        return "PACKAGE_PATH";
    }

    @Override
    public String parse(T t) {
        return t.getPackagePath();
    }
}
