package top.zywork.exception;

/**
 * 统一的Service异常类，在service中不需要对每一个异常都进行细致的捕捉，而可以用catch (Exception e)
 * 或catch (RuntimeException e)的方式，并且抛出ServiceException<br/>
 * 创建于2017-08-24<br />
 * 修改于2017-09-08 添加继承自AppException
 *
 * @author 王振宇
 * @version 1.1
 */
public class ServiceException extends AppException {

    private static final long serialVersionUID = 3175276262178811936L;

    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Integer code, String message) {
        super(code, message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Integer code, Throwable cause) {
        super(code, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(Integer code, String message, Throwable cause) {
        super(code, message, cause);
    }

    protected ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
