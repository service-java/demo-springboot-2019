package top.zywork.common;

import top.zywork.enums.RandomCodeEnum;

import java.util.Random;

/**
 * 生成随机数，随机验证码的工具类<br />
 * 创建于2017-08-24
 *
 * @author 王振宇
 * @version 1.0
 */
public class RandomUtils {

    /**
     * 获取指定范围内的随机整数
     * @param min 随机整数的最小值，必须是正整数
     * @param max 随机整数的最大值，必须是正整数，并且大于min参数
     * @return 最小值到最大值间的随机整数
     */
    public static int randomNum(int min, int max) {
        return new Random().nextInt(max - min) + min;
    }

    /**
     * 通过指定的长度从指定字符串中获取随机验证码
     * @param value 字符串
     * @param codeLength 验证码长度
     * @return 指定长度的验证码
     */
    public static String randomCode(String value, int codeLength) {
        StringBuilder code = new StringBuilder("");
        char[] codeArray = value.toCharArray();
        Random random = new Random();
        for (int i = 0; i < codeLength; i++) {
            code.append(codeArray[random.nextInt(codeArray.length)]);
        }
        return code.toString();
    }

    /**
     * 从指定字符串中获取长度为5的验证码
     * @param value 字符串
     * @return 长度为5的随机验证码
     */
    public static String randomCode(String value) {
        return randomCode(value, 5);
    }

    /**
     * 通过指定的的验证码长度和验证码模式生成验证码
     * @param randomCodeEnum 验证码模式，包括纯数字，纯字母，数字字母混合
     * @param codeLength 验证码长度
     * @return 指定长度和模式的验证码
     */
    public static String randomCode(RandomCodeEnum randomCodeEnum, int codeLength) {
        return randomCode(randomCodeEnum.getValue(), codeLength);
    }

    /**
     * 通过指定验证码模式生成验证码，验证码的长度默认为5
     * @param randomCodeEnum 验证码模式
     * @return 长度为5的指定验证码模式的验证码
     */
    public static String randomCode(RandomCodeEnum randomCodeEnum) {
        return randomCode(randomCodeEnum, 5);
    }

}