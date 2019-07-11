package top.zywork.enums;

/**
 * 激活状态的枚举
 * 创建于2017-08-23
 *
 * @author 王振宇
 * @version 1.0
 */
public enum ActiveStatusEnum {

    ACTIVE(0, "激活"),
    INACTIVE(1, "冻结");

    private Integer value;
    private String des;

    ActiveStatusEnum(Integer value, String des) {
        this.value = value;
        this.des = des;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
