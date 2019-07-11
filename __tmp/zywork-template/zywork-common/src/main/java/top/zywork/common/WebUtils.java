package top.zywork.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Web工具类
 * 创建于2017-08-16
 *
 * @author 王振宇
 * @version 1.0
 */
public class WebUtils {

    /**
     * 获取网站根路径
     * @param request HttpServletRequest对象
     * @return 网站根路径
     */
    public static String getRootPath(HttpServletRequest request) {
        return request.getServletContext().getRealPath("/");
    }

    /**
     * 获取HTTP请求URL的最后/部分的内容
     * @param request HttpServletRequest对象
     * @return 请求URL中最后/部分的内容
     */
    public static String getReqMethod(HttpServletRequest request) {
        String uri = request.getRequestURI();
        return uri.substring(uri.lastIndexOf("/") + 1);
    }

    /**
     * 把指定的输入流通过response对象的输出流输出
     * @param in 输入流
     * @param out 输出流
     */
    public static void outResponse(InputStream in, OutputStream out) {
        IOUtils.inputToOutput(in, out);
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 把指定的输入流通过response对象的输出流输出
     * @param response HttpServletResponse响应对象
     * @param contentType 响应对象的内容类型
     * @param in 输入流
     */
    public static void outResponse(HttpServletResponse response, String contentType, InputStream in) {
        response.setContentType(contentType);
        try {
            outResponse(in, response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
