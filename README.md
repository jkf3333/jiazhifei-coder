

# Enum自动生成
效果：自动生成枚举类，通过Enum枚举类模板，自动生成Enum代码。

地址：https://github.com/jkf3333/common-jiazhifei-coder
## 背景
Enum类是经常用到的java类，经常用Enum类就会发现，存在很多共同点和缺陷。
示例：

```java
public enum StatusEnum {
    NORMAL(1, "正常", "用户创建后的默认值");
    private final Integer code;
    private final String name;
    private final String desc;

    StatusEnum(Integer code, String name, String desc) {
        this.code = code;
        this.name = name;
        this.desc = desc;
    }
}
```
### 共同点
枚举类的具有共同属性和方法。
1. 值： private final Integer code; code就是值。
2. 名称：NORMAL(1,"正常"); 其中“正常”可以认为是名称。
3. 描述：“用户创建默认值”，也可以作为一个注释。
4. 根据值获取枚举：根据1获取NORAML。
5. 值是否属于枚举：1是否是存在StatusEnum枚举中。

### 缺陷
实际开发中，为了简单快速，很多枚举类具有不少的缺陷，例如：
 1. 使用基础类型，而不是其包装类。例如：private int code，当进行equals比较时，就比较麻烦。
 2. 部分枚举类没有“值是否属于枚举”等通用方法。
 3. 属性名定义不统一。
 4. 部分枚举类对应数据库字段，因此一旦枚举类有变动，需要联动修改数据库的字段注释。
 
## Enum自动生成
因为Enum枚举类具有类似性，因此可以通过工具，结合java模板，可以自动生成需要的Enum对象。
### 示例
#### pom

```xml
<dependency>
    <groupId>com.jiazhifei</groupId>
    <artifactId>common-util</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

#### 示例
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
输出：

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

## 下一步
1. 自动生成java类：枚举类并没有实际生成，而是通过控制台进行输出，需要手动复制，粘贴，后续会开发自动生成。
2. 存在枚举类，需要与表中字段的注解进行互动，后续会开发自动sql生成。
3. 后续开发通用的代码生成，例如：Controller、Service、Dao、Mapper等。
