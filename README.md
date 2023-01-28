# 个人工具

主要为解决个人开发过程中，存在的一些工具类。主要有：枚举类的自动生成工具。

## Enum类自动生成工具

### 背景

枚举类的创建过程中，存在如下几个缺陷：

1. 枚举类的属性不一致，例如：状态枚举，有的定义private Integer code，有的定义private Integer status。
2. 部分枚举变量没有相关描述说明。
3. 枚举变量获取值时，返回基本数据类型，而不是包装类型，导致无法使用equals方法。
4. 当枚举变量与数据库字段关联时，变更枚举变量时，需要通过修改数据库字段说明，比较麻烦。
5. 部分枚举对象不提供值与枚举之间的转换和比较方法，导致使用比较麻烦。

为了解决上述的缺陷，因此设计枚举类生成工具，同时提高开发效率。

### 示例

具体使用方法，请参考：com.jkf.util.enumeration.EnumExample。

只需要如下使用：

```java
EnumBuilder.stringBuilder()
        .enumConfig("test", "测试", "测试环境")
        .enumConfig("dev", "开发环境")
        .print("EnvironmentEnum", "环境枚举", "jkf");
```

即可生成EnvironmentEnum枚举类，具体枚举类效果如下：

```java
/**
 * @author jkf
 * @date 2023-01-28 22:46:24
 * @description 环境枚举
 */
public enum EnvironmentEnum {
    TEST("test", "测试", "测试环境"),
    DEV("dev", "开发环境", "");

    private final String code;
    private final String name;
    private final String desc;

   /**
     * main自动生成更新字段的comment信息，当然如果需要的话
     * 例如：tableName=user_info
     * columnInfo = name varchar(32) not null default ''
     */
    public static void main(String[] args) {
        String sql = mkSql("", "");
        System.out.println(sql);
    }

    /**
     * 根据code获取枚举
     */
    public static EnvironmentEnum of(String code) {
        for (EnvironmentEnum codeEnum : EnvironmentEnum.values()) {
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
    public static boolean isExist(String code) {
        EnvironmentEnum codeEnum = of(code);
        return codeEnum != null;
    }

    /**
     * 根据code获取name
     */
    public static String format(String code) {
        EnvironmentEnum codeEnum = of(code);
        if (codeEnum == null) {
            return "";
        } else {
            return codeEnum.getName();
        }
    }

    EnvironmentEnum(String code, String name, String desc) {
        this.name = name;
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    private static String mkSql(String tableName, String columnInfo) {
        StringBuilder sb = new StringBuilder();
        for (EnvironmentEnum codeEnum : EnvironmentEnum.values()) {
            sb.append("," + codeEnum.getCode() + "=" + codeEnum.getName());
            if (codeEnum.getDesc() != null && !"".equals(codeEnum.getDesc())) {
                sb.append("(" + codeEnum.getDesc() + ")");
            }
        }
        return "alter table " + tableName + " modify " + columnInfo + " comment \" 环境枚举:" + sb.substring(1) + "\";";
    }

}

```

### 缺陷

1. EnvironmentEnum并没有实际生成，而是通过控制台数据，需要手动复制，粘贴到指定包路径下即可，因此没有开发自动创建java文件功能，因为自动创建java文件，需要指定根目录和相对路径，复制粘贴比较快捷，就没有开发该功能。
2. 没有与sql进行互动，例如更新字段时，需要先提供表名和字段的属性，才能生成相关sql，可以根据mysql驱动获取相关的字段属性，如同1一样，太过笨重，因此也没有开发相关功能。



