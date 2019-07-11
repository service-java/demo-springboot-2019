package top.zywork.common;

import sun.misc.BASE64Encoder;
import top.zywork.enums.AlgorithmEnum;
import top.zywork.enums.CharsetEnum;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密工具类<br />
 * 创建于2017-08-15<br />
 * 修改于2017-09-04，增加salt<br />
 *
 * @author 王振宇
 * @version 1.1
 * @see top.zywork.enums.AlgorithmEnum
 */
public class EncryptUtils {

    private static final String DEFAULT_ENCRYPT_SALT = "zywork.top&pot.krowyz-ZhenyuWang!";

    /**
     * 使用默认盐值md5加密
     * @param str 明文
     * @return 使用MD5加密算法得到的密文
     */
    public static String md5(String str) {
        String encryptStr = null;
        try {
            encryptStr = oneWayEncrypt(str, DEFAULT_ENCRYPT_SALT, AlgorithmEnum.MD5.getValue());
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encryptStr;
    }

    /**
     * md5加密
     * @param str 明文
     * @param salt 盐值
     * @return 使用md5加密算法并加入盐值加密得到的密文
     */
    public static String md5(String str, String salt) {
        String encryptStr = null;
        try {
            encryptStr = oneWayEncrypt(str, salt, AlgorithmEnum.MD5.getValue());
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encryptStr;
    }

    /**
     * 使用默认盐值sha1加密
     * @param str 明文
     * @return 使用sha1加密算法得到的密文
     */
    public static String sha1(String str) {
        String encryptStr = null;
        try {
            encryptStr = oneWayEncrypt(str, DEFAULT_ENCRYPT_SALT, AlgorithmEnum.SHA1.getValue());
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encryptStr;
    }

    /**
     * sha1加密
     * @param str 明文
     * @param salt 盐值
     * @return 使用sha1加密算法并加入盐值加密得到的密文
     */
    public static String sha1(String str, String salt) {
        String encryptStr = null;
        try {
            encryptStr = oneWayEncrypt(str, salt,  AlgorithmEnum.SHA1.getValue());
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encryptStr;
    }

    /**
     * 单向加密，使用Base64编码
     * @param str 需要加密的明文
     * @param salt 加密所使用的盐值
     * @param type 加密或消息摘要算法名称
     * @return 通过指定加密算法和盐值得到的密文
     * @throws NoSuchAlgorithmException 未知的算法
     * @throws UnsupportedEncodingException 不支持的编码方式
     */
    public static String oneWayEncrypt(String str, String salt, String type) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance(type);
        BASE64Encoder encoder = new BASE64Encoder();
        byte[] bytes = (str + salt).getBytes(CharsetEnum.UTF8.getValue());
        md.update(bytes);
        return encoder.encode(md.digest(bytes));
    }

}
