package com.jiazhifei.coder.core.sql;

import com.jiazhifei.coder.core.exception.CoderException;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * 自定义的ClassLoader
 *
 * @author jkf
 */
public class MyClassLoader extends URLClassLoader {


    public MyClassLoader() {
        super(new URL[]{});
    }

    /**
     * 根据资源路径，加载jar包
     *
     * @param jarPath jar包的路径，
     *                可以是Resource路径，也可以是绝对路径
     */
    public void loadJar(String jarPath) {
        URL resource = MyClassLoader.class.getResource(jarPath);
        if (resource == null) {
            //文件系统
            File file = new File(jarPath);
            if (file.exists()) {
                try {
                    resource = file.toURI().toURL();
                } catch (MalformedURLException e) {
                    throw new CoderException("jar包不存在，请检查：" + jarPath, e);
                }
            }
        }
        if (resource == null) {
            throw new CoderException("jar包文件不存在，jarFIle=" + jarPath);
        }
        super.addURL(resource);
    }
}
