import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.util.ResourceUtils;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

/**
 * Description: 模块功能描述
 * <p>
 * User: luo0412
 * Date: 2020-07-01 19:19
 */
public class Test {

    public static void main(String[] args) throws IOException {
        Thumbnails.of(ResourceUtils.getFile("classpath:demo.jpg"))     //原图片位置
                .size(1600, 1600) //调整大小
                .rotate(90) //顺时针旋转90°
//                .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(ResourceUtils.getFile("classpath:demo.jpg")), 0.5f) // 水印
                .outputQuality(0.8) // 压缩倍数
                .toFile(new File("women-thumbnails-processed.jpg"));   //调整后
    }
}
