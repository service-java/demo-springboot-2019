package top.zywork.common;

import top.zywork.constant.FileConstants;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * 文件工具类，如获取WEB-INF目录根路径，获取classpath根路径，文件后缀判断等
 * 创建于2017-08-16
 *
 * @author 王振宇
 * @version 1.0
 */
public class FileUtils {

    /**
     * 获取classpath根路径
     * @return classpath根路径
     */
    public static String getClasspath() {
        return FileUtils.class.getResource("/").getPath();
    }

    /**
     * 获取WEB-INF根路径
     * @return WEB-INF根路径
     */
    public static String getWEBINFPath() {
        String classPath = getClasspath();
        return classPath.substring(0, classPath.length() - ("classes".length() + 1));
    }

    /**
     * 通过指定的配置文件路径读取配置文件，如果是在web.xml文件中配置路径，则有两种形式，
     * 第一种形式为classpath:/autoload.properties
     * 第二种形式为/WEB-INF/config/autoload.properties
     * @param configLocation 配置文件的存放位置
     * @return 配置文件的路径
     */
    public static String getResourcePath(String configLocation) {
        String resourcePath = null;
        if (configLocation.contains(FileConstants.CLASSPATH)) {
            resourcePath = getClasspath() + configLocation.substring(FileConstants.CLASSPATH.length());
        } else if (configLocation.contains(FileConstants.WEB_INF)) {
            resourcePath = getWEBINFPath() + configLocation.substring(FileConstants.WEB_INF.length());
        }
        return resourcePath;
    }

    /**
     * 在网站根路径下创建文件上传的目录，可以是多级目录
     * @param request HttpServletRequest对象
     * @param dirs 在网站根目录下要创建的用于文件上传的目录，如static/uploads
     * @return
     */
    public static String uploadPath(HttpServletRequest request, String dirs) {
        String rootPath = WebUtils.getRootPath(request);
        File uploadDir = new File(rootPath, dirs);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
        return uploadDir.getAbsolutePath();
    }

    /**
     * 获取指定文件名的后缀
     * @param fileName 完整的文件名称
     * @return 文件后缀，包括.符号，如.png
     */
    public static String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    /**
     * 获取指定文件名的后缀，不包括.符号
     * @param filename 完事的文件名
     * @return 文件后缀，不包括.符号，如png
     */
    public static String getExtensionWithoutDot(String filename) {
        return filename.substring(filename.lastIndexOf(".") + 1);
    }

    /**
     * 判断给定的文件名是否符合指定的文件后缀
     * @param fileName 完整的文件名
     * @param extensions 需要比对文的件后缀，文件后缀包括.符号，多个文件后缀用英文半角,隔开
     * @return 如果文件名符合给定的后缀，则返回true，否则返回false
     */
    public static boolean checkExtension(String fileName, String extensions) {
        String[] exts = extensions.split(",");
        for (String ext : exts) {
            if (fileName.endsWith(ext)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取src根目录下的bpmn目录路径
     * @return
     */
    public static String getBPMNDir() {
        return getClasspath() + "/bpmn/";
    }

    /**
     * 获取指定文件名的不包括后缀部分的文件名，只一个.后缀的情况
     * @param filename 指定的文件名
     * @return 去除后缀的文件名
     */
    public static String getFileNameWithoutExt(String filename) {
        return filename.substring(0, filename.lastIndexOf("."));
    }

    /**
     * 获取指定文件名的不包括后缀部分的文件名，后缀可指定
     * @param filename 指定的文件名
     * @param extension 指定的后缀
     * @return 去除后缀的文件名
     */
    public static String getFileNameWithoutExt(String filename, String extension) {
        return filename.substring(0, filename.indexOf(extension));
    }

    /**
     * 通过比较文件的最后修改时间来判断一个文件是否比另外一个文件更新
     * @param filename 判断是否为新文件的文件路径
     * @param otherFilename 用来比较的文件的路径
     * @return 如果用来判断是否为新文件的文件对应的最后修改时间比用于比较的文件的最后修改时间大，则返回true，否则返回false
     */
    public static boolean isNew(String filename, String otherFilename) {
        return isNew(new File(filename), new File(otherFilename));
    }

    /**
     * 通过比较文件的最后修改时间来判断一个文件是否比另外一个文件更新
     * @param file 判断是否为新文件的文件对象
     * @param otherFile 用来比较的文件对象
     * @return 如果用来判断是否为新文件的文件对应的最后修改时间比用于比较的文件的最后修改时间大，则返回true，否则返回false
     */
    public static boolean isNew(File file, File otherFile) {
        return file.lastModified() > otherFile.lastModified();
    }

}
