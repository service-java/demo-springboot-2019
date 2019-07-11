package top.zywork.common;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import top.zywork.enums.CharsetEnum;
import top.zywork.enums.ContentTypeEnum;
import top.zywork.enums.MIMETypeEnum;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * Http操作类，主要用于发送get或post请求，对HttpClient的封装<br />
 * 创建于2017-09-01
 *
 * @author 王振宇
 * @version 1.0
 */
public class HttpUtils {

    private static RequestConfig requestConfig = RequestConfig.custom()
            .setSocketTimeout(15000)
            .setConnectTimeout(15000)
            .setConnectionRequestTimeout(15000)
            .build();

    /**
     * HTTP GET请求访问指定的url
     * @param url url,包括查询字符串
     * @return 指定url所返回的字符串数据
     */
    public static String httpGet(String url) {
        return send(new HttpGet(url), null, stringResponseHandler);
    }

    /**
     * HTTP POST请求访问指定的url
     * @param url url,可包含查询字符串
     * @return 指定url所返回的字符串数据
     */
    public static String httpPost(String url) {
        return send(null, new HttpPost(url), stringResponseHandler);
    }

    /**
     * HTTP POST请求访问指定的url
     * @param url url,可包含查询字符串
     * @param params post请求发送的参数，参数为param1=value1&param2=value2形式
     * @return 请求返回相应的字符串数据
     */
    public static String httpPost(String url, String params) {
        HttpPost httpPost = new HttpPost(url);
        StringEntity stringEntity = new StringEntity(params, CharsetEnum.UTF8.getValue());
        stringEntity.setContentType(ContentTypeEnum.FORM_URLENCODED.getValue());
        httpPost.setEntity(stringEntity);
        return send(null, httpPost, stringResponseHandler);
    }

    /**
     * HTTP POST请求访问指定的url
     * @param url url,可包含查询字符串
     * @param params key-value结构的参数
     * @return 指定url响应的字符串数据
     */
    public static String httpPost(String url, Map<String, String> params) {
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        Set<Map.Entry<String, String>> entrySet = params.entrySet();
        for (Map.Entry<String, String> entry : entrySet) {
            nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, CharsetEnum.UTF8.getValue()));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return send(null, httpPost, stringResponseHandler);
    }

    /**
     *  通过post请求上传单个文件
     * @param url 请求地址，可包含查询字符串
     * @param params 请求参数
     * @param inputName 上传文件的input name
     * @param filePath 上传文件的完整路径
     * @return 请求响应返回的字符串数据
     */
    public static String httpPost(String url, Map<String, String> params, String inputName, String filePath) {
        return httpPost(url, params, inputName, new File(filePath));
    }

    /**
     *  通过post请求上传单个文件
     * @param url 请求地址，可包含查询字符串
     * @param params 请求参数
     * @param inputName 上传文件的input name
     * @param file 上传的文件，为File类型
     * @return 请求响应返回的字符串数据
     */
    public static String httpPost(String url, Map<String, String> params, String inputName, File file) {
        Map<String, File> files = new HashMap<String, File>(1);
        files.put(inputName, file);
        return httpPost(url, params, files);
    }

    /**
     * 通过post请求上传多个文件
     * @param url 请求地址，可包含查询字符串
     * @param params 请求参数
     * @param files 上传的文件，为Map结构，键为上传文件时对应的input name，值为File类型的文件
     * @return 请求响应返回的字符串数据
     */
    public static String httpPost(String url, Map<String, String> params, Map<String, File> files) {
        HttpPost httpPost = new HttpPost(url);
        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        Set<Map.Entry<String, String>> paramEntrySet = params.entrySet();
        for (Map.Entry<String, String> entry : paramEntrySet) {
            multipartEntityBuilder.addPart(entry.getKey(), new StringBody(entry.getValue(), ContentType.TEXT_PLAIN));
        }
        Set<Map.Entry<String, File>> fileEntrySet = files.entrySet();
        for(Map.Entry<String, File> entry : fileEntrySet) {
            multipartEntityBuilder.addPart(entry.getKey(), new FileBody(entry.getValue()));
        }
        HttpEntity entity = multipartEntityBuilder.build();
        httpPost.setEntity(entity);
        return send(null, httpPost, stringResponseHandler);
    }

    /**
     * 提交json格式数据到指定url
     * @param url url,可包含查询字符中
     * @param json json格式的字符串数据
     * @return 指定url响应的字符串数据
     */
    public static String httpPostJSON(String url, String json) {
        return httpPost(url, json, MIMETypeEnum.JSON);
    }

    /**
     * 提交xml格式数据到指定url
     * @param url url，可以包含查询字符串
     * @param xml xml格式的字符串数据
     * @return 指定url响应的字符串数据
     */
    public static String httpPostXML(String url, String xml) {
        return httpPost(url, xml, MIMETypeEnum.XML);
    }

    /**
     * 通过指定的数据类型把数据post提交到指定的url
     * @param url url，可以包含查询字符串
     * @param data 需要提交的数据
     * @param dataTypeEnum 提交数据的MIME type
     * @return 指定url响应的字符串数据
     */
    private static String httpPost(String url, String data, MIMETypeEnum dataTypeEnum) {
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Content-type", dataTypeEnum.getMime() + "; charset=" + CharsetEnum.UTF8.getValue());
        httpPost.addHeader("Accept", dataTypeEnum.getMime());
        httpPost.setEntity(new StringEntity(data, CharsetEnum.UTF8.getValue()));
        return send(null, httpPost, stringResponseHandler);
    }

    /**
     * 发送HTTP请求，请求方式可以是GET，也可以是POST，并且需要指定相应的ResponseHandler
     * @param httpGet 如果是GET请求，则传入HttpGet对象，如果为null，表示不使用GET请求
     * @param httpPost 如果是POST请求，则传入HttpPost对象，如果为null，表示不使用POST请求
     * @param responseHandler ResponseHandler响应处理对象
     * @param <T> 响应处理对象中可通过泛型来指定返回的具体数据类型
     * @return 通过在ResponseHandler中指定的具体类型来返回响应数据
     */
    private static <T> T send(HttpGet httpGet, HttpPost httpPost, ResponseHandler<T> responseHandler) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        T t = null;
        try {
            if (httpGet != null) {
                httpGet.setConfig(requestConfig);
                t = httpClient.execute(httpGet, responseHandler);
            } else {
                httpPost.setConfig(requestConfig);
                t = httpClient.execute(httpPost, responseHandler);
            }
            httpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * 获取String字符串响应的ResponseHandler
     */
    private static ResponseHandler<String> stringResponseHandler = new ResponseHandler<String>() {

        @Override
        public String handleResponse(final HttpResponse response) throws IOException {
            int status = response.getStatusLine().getStatusCode();
            if (status >= HttpStatus.SC_OK && status < HttpStatus.SC_MULTIPLE_CHOICES) {
                HttpEntity entity = response.getEntity();
                return entity != null ? EntityUtils.toString(entity, CharsetEnum.UTF8.getValue()) : null;
            } else {
                throw new ClientProtocolException("非正常响应状态：" + status);
            }
        }

    };

}
