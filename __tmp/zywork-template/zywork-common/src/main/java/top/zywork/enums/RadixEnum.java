package top.zywork.enums;

/**
 * 常用进制枚举
 * 创建于2017-08-15
 *
 * @author 王振宇
 * @version 1.0
 */
public enum RadixEnum {
    BINARY(2),
    OCTAL(8),
    DECIMAL(10),
    HEX(16);

    private Integer value;

    RadixEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
