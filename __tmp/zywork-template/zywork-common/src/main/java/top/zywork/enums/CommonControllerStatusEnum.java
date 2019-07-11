package top.zywork.enums;

/**
 * 项目控制器响应状态枚举<br />
 * 创建于2017-09-12
 *
 * @author 王振宇
 * @version 1.0
 */
public enum CommonControllerStatusEnum {

    // 用户登录
    USER_LOGIN_OK(1001, "登录成功"),
    USER_LOGIN_ERROR(1002, "登录名或密码错误"),

    // 用户注册
    USER_REG_OK(1003, "注册成功"),
    USER_REG_ERROR(1004, "注册失败"),
    USER_REG_ACCOUNT_EXIST(1005, "账号已存在"),

    // 流程管理
    PROCESS_SAVE_OK(1101, "上传流程定义文件成功"),
    PROCESS_DEPLOY_OK(1102, "流程部署成功"),
    PROCESS_REMOVE_OK(1103, "流程删除成功");

    private Integer code;
    private String message;

    CommonControllerStatusEnum(Integer code, String message) {
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
