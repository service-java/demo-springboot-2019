package top.zywork.common;

import top.zywork.enums.MIMETypeEnum;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * 图片处理相关工具类<br />
 * 创建于2017-10-31
 *
 * @author 王振宇
 * @version 1.0
 */
public class ImageUtils {

    /**
     * 根据指定的图片路径，获取缓冲图片
     * @param imagePath 图片路径
     * @return 缓冲图对象
     */
    public static BufferedImage getBufferedImage(String imagePath) {
        try {
            InputStream inputStream = new FileInputStream(new File(imagePath));
            BufferedImage bufferedImage = ImageIO.read(inputStream);
            inputStream.close();
            return bufferedImage;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据图片输入流获取缓冲图片
     * @param inputStream 图片输入流
     * @return 缓冲图对象
     */
    public static BufferedImage getBufferedImage(InputStream inputStream) {
        try {
            BufferedImage bufferedImage = ImageIO.read(inputStream);
            inputStream.close();
            return bufferedImage;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据图片的字节数组数据生成缓冲图
     * @param imageData 图片的字节数组数据
     * @return 缓冲图对象
     */
    public static BufferedImage getBufferedImage(byte[] imageData) {
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(imageData);
            BufferedImage bufferedImage = ImageIO.read(inputStream);
            inputStream.close();
            return bufferedImage;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 把指定路径的图片填充到指定的输出流
     * @param imagePath 图片路径
     * @param out 输出流
     */
    public static void writeToOut(String imagePath, OutputStream out) {
        BufferedImage bufferedImage = getBufferedImage(imagePath);
        if (bufferedImage != null) {
            try {
                ImageIO.write(bufferedImage, FileUtils.getExtensionWithoutDot(imagePath), out);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 根据输入流把图片内容读填充到指定的输出流
     * @param inputStream 图片输入流
     * @param out 输出流
     * @param imageType 图片类型枚举
     */
    public static void writeToOut(InputStream inputStream, OutputStream out, MIMETypeEnum imageType) {
        BufferedImage bufferedImage = getBufferedImage(inputStream);
        if (bufferedImage != null) {
            try {
                ImageIO.write(bufferedImage, imageType.getValue(), out);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 根据指定的图片路径，获取图片对应的字节数组数据
     * @param imagePath 图片路径
     * @return 图片对应的字节数组数据
     */
    public static byte[] getImageData(String imagePath) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        writeToOut(imagePath, out);
        byte[] bytes = out.toByteArray();
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    /**
     * 根据图片输入流获取图片的字节数组数据
     * @param inputStream 图片输入流
     * @param imageType 图片类型枚举
     * @return 图片对应的字节数组数据
     */
    public static byte[] getImageData(InputStream inputStream, MIMETypeEnum imageType) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        writeToOut(inputStream, out, imageType);
        byte[] bytes = out.toByteArray();
        try {
            inputStream.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    /**
     * 根据图片路径获取图片的类型枚举
     * @param imagePath 图片路径
     * @return 图片类型枚举
     */
    public static MIMETypeEnum getImageType(String imagePath) {
        if (imagePath.endsWith(MIMETypeEnum.PNG.getExt())) {
            return MIMETypeEnum.PNG;
        } else if (imagePath.endsWith(MIMETypeEnum.JPG.getExt())) {
            return MIMETypeEnum.JPG;
        } else if (imagePath.endsWith(MIMETypeEnum.JPEG.getExt())) {
            return MIMETypeEnum.JPEG;
        } else if (imagePath.endsWith(MIMETypeEnum.GIF.getExt())) {
            return MIMETypeEnum.GIF;
        } else if (imagePath.endsWith(MIMETypeEnum.BMP.getExt())) {
            return MIMETypeEnum.BMP;
        }
        return null;
    }

    /**
     * 把字节数组的图片数据保存到指定路径的图片
     * @param imageData 图片数据
     * @param imagePath 图片路径
     * @param imageType 图片类型枚举
     */
    public static void saveImage(byte[] imageData, String imagePath, MIMETypeEnum imageType) {
        saveImage(imageData, imagePath, imageType.getValue());
    }

    /**
     * 把字节数组的图片数据保存到指定路径的图片
     * @param imageData 图片数据
     * @param imagePath 图片路径
     * @param imageType 图片类型字符串，如png
     */
    public static void saveImage(byte[] imageData, String imagePath, String imageType) {
        File imageFile = new File(imagePath);
        BufferedImage bufferedImage = getBufferedImage(imageData);
        if (bufferedImage != null) {
            try {
                ImageIO.write(bufferedImage, imageType, imageFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
