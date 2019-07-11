package top.zywork.enums;

/**
 * 编码格式枚举
 * 创建于2017-08-15
 *
 * @author 王振宇
 * @version 1.0
 */
public enum CharsetEnum {

    UTF8("UTF-8"),
    GBK("GBK"),
    GB2312("GB2312"),;

    private String value;

    CharsetEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
