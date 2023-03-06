package com.jiazhifei.coder.sql;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author jkf
 * @date 2023-02-08 23:14:00
 * @description
 */
public class MyClassLoader extends URLClassLoader {


    public MyClassLoader(URL url,ClassLoader parentClassLoader) {
        super(new URL[]{url},parentClassLoader);
    }

    public void loadJar(String jarPath) {
        URL resource = MyClassLoader.class.getResource(jarPath);
        super.addURL(resource);
    }
}
