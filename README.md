# Coder

通过代码，生成指定的代码模板。

## Enum类自动生成工具

### 背景
可以自动创建标准的Enum类（枚举类）

枚举类的创建过程中，存在如下几个缺陷：

1. 枚举类的属性定义不一致，例如：状态枚举，有的定义private Integer code，有的定义private Integer status。
2. 部分枚举变量没有相关描述说明。
3. 枚举变量获取值时，返回基本数据类型，而不是包装类型，导致无法使用equals方法。
4. 当枚举变量与数据库字段关联时，变更枚举变量时，需要通过修改数据库字段说明，比较麻烦。
5. 部分枚举对象不提供值与枚举之间的转换和比较方法，导致使用比较麻烦。

为了解决上述的缺陷，因此设计枚举类生成工具，同时提高开发效率。

### 示例

具体使用方法，请参考：com.jkf.util.enumeration.EnumExample。

只需要如下使用：

```java
EnumJavaCoder.getInstance()
                .printJava(EnumConfig
                        .integerBuilder()
                        .enumConfig("NORMAL", 1, "正常","默认状态")
                        .enumConfig("DELETE", -1, "删除")
                        .author("jkf")
                        .override(true)
                        .packagePath("com.jiazhifei.coder.example")
                        .build("StatusEnum", "状态枚举"));
```

即可生成EnvironmentEnum枚举类，具体枚举类效果如下：

```java
package com.jiazhifei.coder.example;

/**
 * 状态枚举
 *
 * @author jkf
 */
public enum StatusEnum {
    NORMAL(1, "正常", "默认状态"),
    DELETE(-1, "删除", "");

    private final Integer code;
    private final String name;
    private final String desc;

    /**
     * 输出枚举类的格式化信息
     */
    public static void main(String[] args) {
        //输出枚举类的格式化信息
        System.out.println(toFormatString());
    }

    /**
     * 根据code获取枚举
     */
    public static StatusEnum parse(Integer code) {
        for (StatusEnum codeEnum : StatusEnum.values()) {
            if (codeEnum.getCode().equals(code)) {
                return codeEnum;
            }
        }
        return null;
    }

    /**
     * 判断code是否存在
     *
     * @return true=存在
     */
    public static boolean isExist(Integer code) {
        return parse(code) != null;
    }

    /**
     * 根据code获取name
     */
    public static String format(Integer code) {
        StatusEnum codeEnum = parse(code);
        if (codeEnum == null) {
            return "";
        } else {
            return codeEnum.getName();
        }
    }

    StatusEnum(Integer code, String name, String desc) {
        this.name = name;
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    /**
     * 枚举类信息格式化
     *
     * @return 枚举类格式化后的信息
     */
    public static String toFormatString() {
        StringBuilder sb = new StringBuilder();
        for (StatusEnum codeEnum : StatusEnum.values()) {
            sb.append("," + codeEnum.getCode() + "=" + codeEnum.getName());
            if (codeEnum.getDesc() != null && !"".equals(codeEnum.getDesc())) {
                sb.append("(" + codeEnum.getDesc() + ")");
            }
        }
        return "状态枚举:" + sb.substring(1);
    }
}


```

### 下一步

1. EnvironmentEnum并没有实际生成，而是通过控制台数据，需要手动复制，粘贴到指定包路径下即可，因此没有开发自动创建java文件功能，后续会继续补充。
2. 没有与sql进行互动，例如更新字段时，需要先提供表名和字段的属性，才能生成相关sql，可以根据mysql驱动获取相关的字段属性，后续会补充。



