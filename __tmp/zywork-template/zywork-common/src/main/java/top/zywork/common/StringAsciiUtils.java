package top.zywork.common;

import top.zywork.enums.RadixEnum;

/**
 * 字符串与ASCII码相互转换的类
 * 创建于2017-08-15
 *
 * @author 王振宇
 * @version 1.0
 * @see top.zywork.enums.RadixEnum
 */
public class StringAsciiUtils {

    /**
     * 字符串转换成ASCII码，可以指定分割符连接转换后的ASCII码，并且可以指定进制数来转换
     * @param str 需要转换成ASCII码的字符串
     * @param separator 分割符，如0x
     * @param radixEnum 进制枚举
     * @return 通过指定分割符及进制转换的ASCII码字符串
     */
    public static String strToAscii(String str, String separator, RadixEnum radixEnum) {
        char[] chars = str.toCharArray();
        StringBuilder ascii = new StringBuilder("");
        for (char c : chars) {
             ascii.append(separator);
             String cAscii = "";
             if (radixEnum == RadixEnum.BINARY) {
                 cAscii = Integer.toBinaryString(c);
             } else if (radixEnum == RadixEnum.OCTAL) {
                 cAscii = Integer.toOctalString(c);
             } else if (radixEnum == RadixEnum.HEX) {
                 cAscii = Integer.toHexString(c);
             } else {
                 cAscii = Integer.toString(c);
             }
             ascii.append(cAscii);
        }
        return ascii.toString();
    }

    /**
     * ASCII码转换成字符串，可以指定分割符，如果未指定分割符，将没法正确拆分原始ASCII码字符串。并可以指定进制数
     * @param ascii 需要转换成普通字符串的ASCII码字符串
     * @param separator 分割符，如0x
     * @param radixEnum 进制枚举
     * @return 通过指定分割符及进制转换的普通字符串
     */
    public static String asciiToStr(String ascii, String separator, RadixEnum radixEnum) {
        String[] chars = ascii.split(separator);
        StringBuilder str = new StringBuilder("");
        for (String c : chars) {
            if (!c.equals("")) {
                str.append((char) Integer.parseInt(c, radixEnum.getValue()));
            }
        }
        return str.toString();
    }

}
