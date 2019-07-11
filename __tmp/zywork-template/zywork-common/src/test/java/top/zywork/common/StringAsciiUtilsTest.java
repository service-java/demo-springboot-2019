package top.zywork.common;

import org.junit.Test;
import top.zywork.enums.RadixEnum;

/**
 * 字符串与ASCII码相互转换工具测试类
 * 创建于2017-08-15
 *
 * @author 王振宇
 */
public class StringAsciiUtilsTest {
    @Test
    public void testStrToAscii() {
        System.out.println(StringAsciiUtils.strToAscii("赣州之游科技", "0x", RadixEnum.HEX));
    }

    @Test
    public void testAsciiToStr() {
        System.out.println(StringAsciiUtils.asciiToStr(StringAsciiUtils.strToAscii("赣州之游科技", "0x", RadixEnum.HEX),
                "0x", RadixEnum.HEX));
    }
}
