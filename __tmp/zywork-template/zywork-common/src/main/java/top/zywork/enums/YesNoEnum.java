package top.zywork.enums;

/**
 * 是与否的枚举<br />
 * 创建于2017-10-14
 *
 * @author 王振宇
 * @version 1.0
 */
public enum YesNoEnum {

    YES(0, "是"),
    NO(1, "否");

    private Integer value;
    private String des;

    YesNoEnum(Integer value, String des) {
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
