package com.xncoding.pos;

import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.state.PDExtendedGraphicsState;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;
import org.apache.pdfbox.util.Matrix;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PdfBoxApplication {



    public static void main(String[] args) throws IOException {
        pdf2Img("d:/upload/demo.pdf");
//        singlePagePdf2Image("d:/upload/demo.pdf", "d:/upload/download.png");
    }

    /**
     * https://blog.csdn.net/asuyunlong/article/details/79863064
     * @param srcPath
     * @param destPath
     */
//    public static void singlePagePdf2Image(String srcPath,String destPath){
//        try{
//            PDDocument document = PDDocument.load(new File(srcPath));
//            PDFRenderer pdfRenderer=new PDFRenderer(document);
//            BufferedImage bufferedImage = pdfRenderer.renderImageWithDPI(0,100);
//            ImageIOUtil.writeImage(bufferedImage,destPath,100);
//            document.close();
//        }
//        catch(Exception e){
//            System.out.println(e.getMessage());
//            e.printStackTrace();
//        }
//    }

    /**
     * https://blog.csdn.net/buqixiao/article/details/51646710
     * 水印 https://blog.csdn.net/weikaisen1/article/details/80335818
     * 使用自定义字体 https://blog.csdn.net/qq_22156459/article/details/78909644
     * @param filePath
     */
    public static void pdf2Img(String filePath) throws IOException {
        PDDocument pdf = null;
        try {
            pdf = PDDocument.load(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        PDFRenderer pdfRenderer = new PDFRenderer(pdf);
        PDPageTree pageTree = pdf.getPages();
        int pageCounter = 0;
        for(PDPage page : pageTree){
            PDPageContentStream cs = new PDPageContentStream(pdf, page, PDPageContentStream.AppendMode.APPEND, true, true);
            String ts = "http://www.zjzj.net/index";
            PDFont font = PDType1Font.HELVETICA_OBLIQUE;
            float fontSize = 50.0f;
            PDResources resources = page.getResources();
            PDExtendedGraphicsState r0 = new PDExtendedGraphicsState();
            // 透明度
            r0.setNonStrokingAlphaConstant(0.2f);
            r0.setAlphaSourceFlag(true);
            cs.setGraphicsStateParameters(r0);
            cs.setNonStrokingColor(200,0,0);//Red
            cs.beginText();
            cs.setFont(font, fontSize);
            // 获取旋转实例
            cs.setTextMatrix(Matrix.getRotateInstance(20,350f,490f));
            cs.showText(ts);
            cs.endText();
            cs.close();


//            float width = page.getCropBox().getWidth();
            float scale = 2.0f;
//            if(width > 720){
//                scale = 720 / width;
//            }

            BufferedImage bim = null;
            try {
                bim = pdfRenderer.renderImage(pageCounter, scale, ImageType.RGB);
                ImageIOUtil.writeImage(bim, "d:/upload/download/" + (pageCounter++) + ".png", 300);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
