package com.encrypt;

import com.quick.encrypt.EncryptApplication;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author vector
 * @date: 2018/11/9 0009 16:43
 */
@SpringBootTest(classes = EncryptApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TestMain {
    @Autowired
    StringEncryptor stringEncryptor;//密码解码器自动注入

    @Value("${app.key}")
    private String key;

    @Test
    public void test() {
		String decrypt = stringEncryptor.decrypt("Er1K3WxM3M8o2Ynazkwkbg==");
		// app.key读取到的时候自动解密??
        System.out.println(decrypt); // 123456
        System.out.println(key); // 123456

        // 加密
        String encrypt1 = stringEncryptor.encrypt("123456");
        System.out.println(encrypt1);

        // 再解密
        String decrypt1 = stringEncryptor.decrypt(encrypt1);// @eg WXX5OJcpUb2lVNUC3BPtQQ==
        System.out.println(decrypt1); // 123456
    }
}
