package top.zywork.exception;

/**
 * 应用异常类，是DAOException和ServiceException的父类<br />
 * 创建于2017-09-08
 *
 * @author 王振宇
 * @version 1.0
 */
public class AppException extends RuntimeException {
    private static final long serialVersionUID = -7950025963157139258L;

    private Integer code;

    public AppException() {
        super();
    }

    public AppException(String message) {
        super(message);
    }

    public AppException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
        if (cause instanceof AppException) {
            setCode(((AppException) cause).getCode());
        }
    }

    public AppException(Integer code, Throwable cause) {
        super(cause);
        this.code = code;
    }

    public AppException(Throwable cause) {
        super(cause);
        if (cause instanceof AppException) {
            setCode(((AppException) cause).getCode());
        }
    }

    public AppException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    protected AppException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        if (cause instanceof AppException) {
            setCode(((AppException) cause).getCode());
        }
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
