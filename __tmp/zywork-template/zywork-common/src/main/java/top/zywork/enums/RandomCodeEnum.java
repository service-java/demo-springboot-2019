package top.zywork.enums;

/**
 * 随机验证码枚举，包括纯数字，纯字母，数字字母混合三种模式<br />
 * 创建于2017-08-24
 *
 * @author 王振宇
 * @version 1.0
 */
public enum RandomCodeEnum {

    NUMBER_CODE("0123456789"),
    LETTER_CODE("abcdefghijkmnpqrstuvwxyABCDEFGHJKLMNPQRSTUVWXY"),
    MIX_CODE("23456789abcdefghijkmnpqrstuvwxyABCDEFGHJKLMNPQRSTUVWXY");

    private String value;

    RandomCodeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
