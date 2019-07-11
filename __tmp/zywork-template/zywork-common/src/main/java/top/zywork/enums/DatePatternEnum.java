package top.zywork.enums;

/**
 * 时间格式枚举
 * 创建于2017-08-16
 *
 * @author 王振宇
 * @version 1.0
 */
public enum DatePatternEnum {

    FULL("yyyy-MM-dd HH:mm:ss:SSS"),
    DATETIME("yyyy-MM-dd HH:mm:ss"),
    DATE("yyyy-MM-dd"),
    TIME("HH:mm:ss"),
    FULL_ZH("yyyy年MM月dd日 HH时mm分ss秒SSS毫秒"),
    DATETIME_ZH("yyyy年MM月dd日 HH时mm分ss秒"),
    DATE_ZH("yyyy年MM月dd日"),
    TIME_ZH("HH时mm分ss秒");

    private String value;

    DatePatternEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
