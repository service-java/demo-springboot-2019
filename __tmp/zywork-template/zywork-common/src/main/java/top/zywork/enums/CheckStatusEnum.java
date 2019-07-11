package top.zywork.enums;

/**
 * 审核状态的枚举
 * 创建于2017-08-23
 *
 * @author 王振宇
 * @version 1.0
 */
public enum CheckStatusEnum {


    CHECKING(0, "审核中"),
    CHECKED(1, "已通过"),
    UNCHECKED(2, "未通过");

    private Integer value;
    private String des;

    CheckStatusEnum(Integer value, String des) {
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
