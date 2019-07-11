package top.zywork.common;

import java.io.*;

/**
 * 输入输出工具类<br />
 * 创建于2017-11-01
 *
 * @author 王振宇
 * @version 1.0
 */
public class IOUtils {

    /**
     * 获取指定路径文件的字节数组
     * @param path 文件路径
     * @return 文件对应的字节数组数据
     */
    public static byte[] getData(String path) {
        try {
            return getData(new FileInputStream(new File(path)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取输入流对应的字节数组
     * @param inputStream 输入流
     * @return 输入流对应的字节数组数据
     */
    public static byte[] getData(InputStream inputStream) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        inputToOutput(inputStream, outputStream);
        byte[] bytes = outputStream.toByteArray();
        try {
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    /**
     * 把指定路径文件的数据放入到指定输出流
     * @param path 文件路径
     * @param outputStream 输出流对象
     */
    public static void inputToOutput(String path, OutputStream outputStream) {
        try {
            inputToOutput(new FileInputStream(new File(path)), outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 把输入流的数据放入到指定输出流
     * @param inputStream 输入流
     * @param outputStream 输出流
     */
    public static void inputToOutput(InputStream inputStream, OutputStream outputStream) {
        byte[] bytes = new byte[1024];
        try {
            for (int length = -1; (length = inputStream.read(bytes)) != -1;) {
                outputStream.write(bytes, 0, length);
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
