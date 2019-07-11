package top.zywork.common;

import top.zywork.enums.CommonExceptionEnum;
import top.zywork.exception.AppException;
import top.zywork.exception.DAOException;
import top.zywork.exception.ServiceException;

/**
 * 异常工具类<br />
 * 创建于2017-09-08
 *
 * @author 王振宇
 * @version 1.0
 */
public class ExceptionUtils {

    /**
     * 获取AppException类实例
     * @param cause 异常对象
     * @return AppException实例
     */
    public static AppException appException(Throwable cause) {
        return new AppException(CommonExceptionEnum.APP_EXCEPTION.getCode(),
                CommonExceptionEnum.APP_EXCEPTION.getMessage(),
                cause);
    }

    /**
     * 获取DAOException类实例
     * @param cause 异常对象
     * @return DAOException实例
     */
    public static DAOException daoException(Throwable cause) {
        return new DAOException(CommonExceptionEnum.DAO_EXCEPTION.getCode(),
                CommonExceptionEnum.DAO_EXCEPTION.getMessage(),
                cause);
    }

    /**
     * 获取ServiceException类实例
     * @param cause 异常对象
     * @return ServiceException实例
     */
    public static ServiceException serviceException(Throwable cause) {
        return new ServiceException(CommonExceptionEnum.SERVICE_EXCEPTION.getCode(),
                CommonExceptionEnum.SERVICE_EXCEPTION.getMessage(),
                cause);
    }

    /**
     * 获取简单异常信息，部分异常堆栈
     * @param throwable 异常对象
     * @return 异常信息组成的字符中
     */
    public static String stackTraceString(Throwable throwable) {
        StackTraceElement[] stackTraceElements = throwable.getStackTrace();
        StringBuilder stringBuilder = new StringBuilder("");
        stringBuilder.append(throwable.getMessage());
        for (StackTraceElement element : stackTraceElements) {
            stringBuilder.append(element.toString()).append("\n    ");
        }
        return stringBuilder.toString();
    }

    /**
     * 获取完整异常信息，包括引起异常的根异常信息，并完全打印出异常堆栈
     * @param stringBuilder 初始化字符串，用于接收完整的异常信息，只需要传递一个空字符串的StringBuilder对象，如new StringBuilder("")
     * @param throwable 异常对象
     * @return 完整的异常堆栈所组成的字符串
     */
    public static String stackTraceString(StringBuilder stringBuilder, Throwable throwable) {
        StackTraceElement[] stackTraceElements = throwable.getStackTrace();
        stringBuilder.append(throwable.getMessage());
        for (StackTraceElement element : stackTraceElements) {
            stringBuilder.append(element.toString()).append("\n    ");
        }
        Throwable cause = throwable.getCause();
        if (cause != null) {
            stringBuilder.append(stackTraceString(stringBuilder, cause));
        }
        return stringBuilder.toString();
    }

}
