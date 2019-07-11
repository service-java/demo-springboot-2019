package top.zywork.enums;

/**
 * 通用异常类信息枚举，包括异常code和异常消息<br />
 * 所有的异常消息将来都需要定义在枚举中，包括异常code和异常消息message。<br />
 * 异常消息可以统一定义在同一个枚举中，也可分模块定义在不同的枚举中<br />
 * 创建于2017-09-08
 *
 * @author 王振宇
 * @version 1.0
 */
public enum CommonExceptionEnum {
    APP_EXCEPTION(1, "应用异常"),
    DAO_EXCEPTION(2, "数据库异常"),
    SERVICE_EXCEPTION(3, "服务异常");

    private Integer code;
    private String message;

    CommonExceptionEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
