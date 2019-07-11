package top.zywork.common;

import java.io.*;
import java.util.Properties;

/**
 * 配置工具类，由此类读取指定路径的配置文件
 * 创建于2017-08-15
 *
 * @author WangZhenyu
 * @version 1.0
 */
public class ConfigUtils {

    protected Properties properties;

    public ConfigUtils() {

    }

    /**
     * 根据指定的配置文件路径读取文件内容到Properties实例中
     * @param location 配置文件的路径，包含两种形式，classpath:/和/WEB-INF的两个位置
     * @return Properties对象
     * @see top.zywork.common.FileUtils
     */
    public Properties build(String location) {
        properties = new Properties();
        try {
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(
                            new BufferedInputStream(
                                    new FileInputStream(FileUtils.getResourcePath(location)))));
            properties.load(bufferedReader);
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    /**
     * 通过指定的key值去获取properties文件中整数值
     * @param key properties文件中的key
     * @return 返回key值对应的整数值，当且仅当此值是一个整数类型值时
     */
    public int getInt(String key) {
        return Integer.valueOf(properties.getProperty(key));
    }

    /**
     * 通过指定的key值去获取properties文件中字符串值
     * @param key properties文件中的key
     * @return 返回key值对应的字符串值
     */
    public String getString(String key) {
        return properties.getProperty(key);
    }

    /**
     * 通过指定的key值去获取properties文件中double值
     * @param key properties文件中的key
     * @return 返回key值对应的double值，当且仅当此值是一个数值类型值时
     */
    public double getDouble(String key) {
        return Double.valueOf(properties.getProperty(key));
    }

    /**
     * 通过指定的key值去获取properties文件中长整型值
     * @param key properties文件中的key
     * @return 返回key值对应的长整型值，当且仅当此值是一个整数类型值时
     */
    public long getLong(String key) {
        return Long.valueOf(properties.getProperty(key));
    }

}
