package top.zywork.enums;

/**
 * 删除状态的枚举
 * 创建于2017-08-23
 *
 * @author 王振宇
 * @version 1.0
 */
public enum DeleteStatusEnum {

    NOT_DELETED(0, "未删除"),
    DELETED(1, "已删除");

    private Integer value;
    private String des;

    DeleteStatusEnum(Integer value, String des) {
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
