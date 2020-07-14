package com.xncoding.pos.controller;


import cn.hutool.core.util.CharsetUtil;
import cn.hutool.http.HttpUtil;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
public class HttpMimeController {

    private static final Logger _logger = LoggerFactory.getLogger(HttpMimeController.class);



    @GetMapping("/httpclient")
    @ResponseBody
    public String http() throws IOException {
        CloseableHttpClient httpClient= HttpClients.createDefault();
        HttpGet httpget = new HttpGet("http://www.baidu.com");
        httpget.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");

        // @TODO ??
        httpget.setHeader("charset", "utf-8");
        CloseableHttpResponse response = httpClient.execute(httpget);
        HttpEntity httpEntity= response.getEntity();
        String strResult= EntityUtils.toString(httpEntity);
//        Console.log(strResult);

        return strResult;
    }


    @GetMapping("/hutool")
    @ResponseBody
    public String hutool() throws IOException {
        String result = HttpUtil.get("https://www.baidu.com", CharsetUtil.CHARSET_UTF_8);
        return result;
    }

}
