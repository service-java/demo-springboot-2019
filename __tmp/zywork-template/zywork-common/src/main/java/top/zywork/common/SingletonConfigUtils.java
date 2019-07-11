package top.zywork.common;

/**
 * 使用单例模式创建配置工具类
 * 创建于2017-08-16
 *
 * @author 王振宇
 * @version 1.0
 */
public final class SingletonConfigUtils extends ConfigUtils {

    private static SingletonConfigUtils config;

    private SingletonConfigUtils() {}

    public static SingletonConfigUtils getInstance() {
        if (config == null) {
            synchronized (SingletonConfigUtils.class) {
                if (config == null) {
                    config = new SingletonConfigUtils();
                }
            }
        }
        return config;
    }

}
