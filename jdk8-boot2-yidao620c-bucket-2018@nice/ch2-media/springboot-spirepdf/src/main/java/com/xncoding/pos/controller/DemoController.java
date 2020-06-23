package com.xncoding.pos.controller;

import com.spire.pdf.PdfDocument;
import com.spire.pdf.PdfPageBase;
import com.spire.pdf.graphics.PdfImage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DemoController {

    @RequestMapping("/demo")
    @ResponseBody
    public String demo3() {
        // 创建文档
        PdfDocument pdf = new PdfDocument();

        // 添加一页
        PdfPageBase page = pdf.getPages().add();

        // 加载图片，并获取图片高宽
        PdfImage image = PdfImage.fromFile("d:/upload/demo.jpg");
        int width = image.getWidth() / 2;
        int height = image.getHeight() / 2;

        // 绘制图片到PDF
        page.getCanvas().drawImage(image, 50, 50, width, height);

        // 保存文档
        pdf.saveToFile("d:/upload/result.pdf");
        pdf.dispose();

        return "我准备好了";
    }


}
