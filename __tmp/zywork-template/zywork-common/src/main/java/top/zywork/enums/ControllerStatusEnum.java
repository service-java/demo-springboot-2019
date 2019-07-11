package top.zywork.enums;

/**
 * 控制器执行结果枚举<br />
 * 创建于2017-08-16
 *
 * @author 王振宇
 * @version 1.0
 */
public enum ControllerStatusEnum {

    OK(101, "ok"),
    ERROR(102, "error"),
    DATA_ERROR(103, "data-error");

    private Integer code;
    private String status;

    ControllerStatusEnum(Integer code, String status) {
        this.code = code;
        this.status = status;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
