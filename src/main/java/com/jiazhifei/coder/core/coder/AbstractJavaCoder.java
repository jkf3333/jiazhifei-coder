package com.jiazhifei.coder.core.coder;

import com.jiazhifei.coder.core.CoderUtil;
import com.jiazhifei.coder.core.config.JavaConfig;
import com.jiazhifei.coder.core.dto.TemplateInfo;
import com.jiazhifei.coder.core.exception.CoderException;
import com.jiazhifei.coder.core.formater.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * 抽象coder，定义coder的相关模板方法，供子类重写
 *
 * @author jkf
 */
public class AbstractJavaCoder<T extends JavaConfig> implements JavaCoder<T> {
    /**
     * 模板参数替换的循环次数，防止formatter之间生成循环参数
     */
    public static final Integer REPLACE_MAX_NUM = 100;

    @Override
    public String printJava(T t) {
        String javaInfo = buildJavaInfo(t);
        System.out.println();
        System.out.println();
        System.out.println(javaInfo);
        System.out.println();
        System.out.println();
        return javaInfo;
    }

    @Override
    public String writeJava(T t) {
        String javaPath = CoderUtil.javaProjectPath(t.getPackagePath(), t.getJavaName());
        File file = new File(javaPath);
        if (file.exists() && file.isFile()) {
            if (!t.isOverride()) {
                throw new IllegalArgumentException("java文件已经存在，javaFile=" +
                        file.getPath());
            }
        } else {
            file.getParentFile().mkdir();
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new CoderException("创建文件失败，javaFile=" + file.getPath(), e);
            }
        }
        String javaInfo = buildJavaInfo(t);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(javaInfo.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new CoderException("创建java文件失败，路径：" + file.getPath(), e);
        }
        System.out.println("创建：" + file.getPath() + " 成功");
        return file.getPath();
    }

    /**
     * 子类重写，根据配置信息，生成java代码信息
     *
     * @param t 参数
     * @return java模板信息
     */
    protected String buildJavaInfo(T t) {
        String templateInfo = templateInfo(t);
        if (CoderUtil.isEmpty(templateInfo)) {
            throw new IllegalArgumentException("java模板信息不存在");
        }
        TemplateInfo info = new TemplateInfo(templateInfo);
        List<ParamFormatter> formatters = initFormatter(t);
        if (CoderUtil.isEmpty(formatters)) {
            return info.getTemplateInfo();
        }
        //替换模板参数
        replaceParam(0, info, t, formatters);
        return info.getTemplateInfo();
    }

    private void replaceParam(int repeatNum,
                              TemplateInfo info,
                              T t,
                              List<ParamFormatter> formatters) {
        if (repeatNum > REPLACE_MAX_NUM) {
            throw new IllegalArgumentException("存在可能循环参数生成，请检查formatter之间是否生成循环参数");
        }
        repeatNum++;
        for (ParamFormatter formatter : formatters) {
            if (formatter.hasParam(info.getTemplateInfo())) {
                info.replaceParam(formatter.support(), formatter.parse(t));
            }
        }
        boolean hasParam = false;
        for (ParamFormatter formatter : formatters) {
            if (formatter.hasParam(info.getTemplateInfo())) {
                hasParam = true;
            }
        }
        if (hasParam) {
            replaceParam(repeatNum, info, t, formatters);
        }

    }

    /**
     * 初始化模板中的参数转换器
     *
     * @return 参数转化器
     */
    protected List<ParamFormatter> initFormatter(T t) {
        List<ParamFormatter> formatters = new ArrayList<>();
        formatters.add(new AuthorFormatter());
        formatters.add(new DescFormatter());
        formatters.add(new JavaNameFormatter());
        formatters.add(new PackagePathFormatter());
        return formatters;
    }

    /**
     * 子类提供模板信息
     *
     * @param t 参数配置
     */
    protected String templateInfo(T t) {
        return "";
    }

}
