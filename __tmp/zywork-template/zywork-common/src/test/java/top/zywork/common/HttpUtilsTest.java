package top.zywork.common;

import org.junit.Test;

/**
 *  HttpUtils工具测试类<br />
 *  创建于2017-09-01
 *
 *  @author 王振宇
 */
public class HttpUtilsTest {

    @Test
    public void testHttpPost() {
        System.out.println(HttpUtils.httpPost("http://wiki.open.qq.com/wiki/website/%E5%BE%AE%E5%8D%9A%E7%A7%81%E6%9C%89%E8%BF%94%E5%9B%9E%E7%A0%81%E8%AF%B4%E6%98%8E"));
    }
}
