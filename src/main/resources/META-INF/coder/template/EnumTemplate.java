package PACKAGE_PATH;

/**
 * DESC
 *
 * @author AUTHOR
 */
public enum StatusEnum {
ENUM_ITEM_LIST

    private final KEY_TYPE code;
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
    public static StatusEnum parse(KEY_TYPE code) {
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
    public static boolean isExist(KEY_TYPE code) {
        return parse(code) != null;
    }

    /**
     * 根据code获取name
     */
    public static String format(KEY_TYPE code) {
        StatusEnum codeEnum = parse(code);
        if (codeEnum == null) {
            return "";
        } else {
            return codeEnum.getName();
        }
    }

    StatusEnum(KEY_TYPE code, String name, String desc) {
        this.name = name;
        this.code = code;
        this.desc = desc;
    }

    public KEY_TYPE getCode() {
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
        return "DESC:" + sb.substring(1);
    }
}
