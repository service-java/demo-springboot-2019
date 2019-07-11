package top.zywork.common;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * 加密工具的测试类
 * 创建于2017-08-15
 *
 * @author 王振宇
 */
public class EncryptUtilsTest {

    @Test
    public void testMD5() {
        System.out.println(EncryptUtils.md5("123456"));
    }

    @Test
    public void testSHA() {
        System.out.println(EncryptUtils.sha1("123456"));
    }

    @Test
    public void testOneWayEncrypt() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        System.out.println(EncryptUtils.oneWayEncrypt("123456", "salt", "SHA-1"));
    }

}
