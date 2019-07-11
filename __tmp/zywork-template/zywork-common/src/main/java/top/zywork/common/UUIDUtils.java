package top.zywork.common;

import java.util.UUID;

/**
 * UUID工具类
 * 创建于2017-08-15
 *
 * @author 王振宇
 * @version 1.0
 */
public class UUIDUtils {

    /**
     * 生成带有-的UUID字符串
     * @return 带有-的UUID字符串
     */
    public static String uuid() {
        return UUID.randomUUID().toString();
    }

    /**
     * 生成不带有-的UUID字符串
     * @return 不带有-的UUID字符串
     */
    public static String uuidWithoutLine() {
        return uuid().replaceAll("-", "");
    }

}
