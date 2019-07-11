package top.zywork.common;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.util.HashSet;
import java.util.Set;

/**
 * 使用Hibernate Validator的工具类<br />
 * 创建于2017-09-15
 *
 * @author 王振宇
 * @version 1.0
 */
public class ValidateUtils {

    private static Validator validator;

    // 静态代码块初始化Validator，并且使用单例模式
    static {
        synchronized (Object.class) {
            if (validator == null) {
                synchronized (Object.class) {
                    validator = Validation.buildDefaultValidatorFactory().getValidator();
                }
            }
        }
    }

    /**
     * 验证对象里的属性是否符合指定的数据要求
     * @param t 需要验证的对象
     * @param <T> 验证对象为泛型
     * @return 如果验证有错，则返回验证错误信息，否则返回null
     */
    public static <T> String validate(T t) {
        return validateMessage(validator.validate(t, Default.class));
    }

    /**
     * 验证对象指定的单个属性是否符合指定的数据要求
     * @param t 需要验证的对象
     * @param property 对象中的属性名
     * @param <T> 验证对象为泛型
     * @return 如果验证有错，则返回验证错误信息，否则返回null
     */
    public static <T> String validate(T t, String property) {
        return validateMessage(validator.validateProperty(t, property, Default.class));
    }

    /**
     * 验证对象指定的多个属性是否符合指定的数据要求
     * @param t 需要验证的对象
     * @param properties 对象中的多个属性名
     * @param <T> 验证对象为泛型
     * @return 如果验证有错，则返回验证错误信息，否则返回null
     */
    public static <T> String validate(T t, String[] properties) {
        Set<ConstraintViolation<T>> allConstraintViolation = new HashSet<>();
        for (String property : properties) {
            Set<ConstraintViolation<T>> constraintViolationSet = validator.validateProperty(t, property);
            allConstraintViolation.addAll(constraintViolationSet);
        }
        return validateMessage(allConstraintViolation);
    }

    /**
     * 从ConstraintViolation集合中获取数据验证的错误信息
     * @param constraintViolationSet ConstraintViolation集合
     * @param <T> 验证对象为泛型
     * @return 如果有错误消息，则组装成String字符中返回，否则返回null
     */
    private static <T> String validateMessage(Set<ConstraintViolation<T>> constraintViolationSet) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (constraintViolationSet.size() > 0) {
            for (ConstraintViolation<T> cv : constraintViolationSet) {
                stringBuilder.append(cv.getMessage()).append("\n");
            }
            return stringBuilder.toString();
        }
        return null;
    }

}
