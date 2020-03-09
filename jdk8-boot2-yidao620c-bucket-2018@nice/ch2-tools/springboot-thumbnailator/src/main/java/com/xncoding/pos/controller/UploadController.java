package com.xncoding.pos.controller;

import cn.hutool.core.date.DateUtil;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
public class UploadController {

    private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

    @RequestMapping("/")
    public String demo() {
        return "index";
    }

    /**
     * java 上传图片 并压缩图片大小
     * https://www.cnblogs.com/miskis/p/5500822.html
     */
    @PostMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile imageFile, HttpServletRequest request) {

        // 校验判断
        if (imageFile == null) {
            // return new BaseResult(false, "imageFile不能为空");
        }

        if (imageFile.getSize() >= 10 * 1024 * 1024) {
            // return new BaseResult(false, "文件不能大于10M");
        }


        //拼接后台文件名称
        String uuid = UUID.randomUUID().toString();
        String fileDirectory = DateUtil.format(new Date(), "YYYY-MM-DD");
        String pathName = fileDirectory + File.separator + uuid + "."
                + FilenameUtils.getExtension(imageFile.getOriginalFilename());

        // 构建保存文件
        String realPath = "d:/upload";

        //获取服务器绝对路径 linux 服务器地址  获取当前使用的配置文件配置
        String filePathName = realPath + File.separator + pathName;
        logger.info("图片上传路径：" + filePathName);

        // 判断文件保存是否存在
        File file = new File(filePathName);
        if (file.getParentFile() != null || !file.getParentFile().isDirectory()) {
            file.getParentFile().mkdirs();
        }

        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            inputStream = imageFile.getInputStream();
            fileOutputStream = new FileOutputStream(file);

            //写出文件
            //2016-05-12 yangkang 改为增加缓存
//            IOUtils.copy(inputStream, fileOutputStream);
            byte[] buffer = new byte[2048];
            IOUtils.copyLarge(inputStream, fileOutputStream, buffer);
            buffer = null;
        } catch (IOException e) {
            filePathName = null;
            // return new BaseResult(false, "操作失败", e.getMessage());
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.flush();
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                filePathName = null;
                // return new BaseResult(false, "操作失败", e.getMessage());
            }
        }


        // 生成缩略图
        String thumbnailPathName = fileDirectory + File.separator + uuid + "small."
                + FilenameUtils.getExtension(imageFile.getOriginalFilename());
        if (thumbnailPathName.contains(".png")) {
            thumbnailPathName = thumbnailPathName.replace(".png", ".jpg");
        }
        long size = imageFile.getSize();
        double scale = 1.0d;
        if (size >= 200 * 1024) {
            if (size > 0) {
                scale = (200 * 1024f) / size;
            }
        }

        // 缩略图路径
        String thumbnailFilePathName = realPath + File.separator + thumbnailPathName;
        try {
            //added by chenshun 2016-3-22 注释掉之前长宽的方式，改用大小
//            Thumbnails.of(filePathName).size(width, height).toFile(thumbnailFilePathName);

            if (size < 200 * 1024) {
                Thumbnails.of(filePathName).scale(1f).outputFormat("jpg").toFile(thumbnailFilePathName);
            } else {
                Thumbnails.of(filePathName).scale(1f).outputQuality(scale).outputFormat("jpg").toFile(thumbnailFilePathName);
            }

        } catch (Exception e) {
            // return new BaseResult(false, "操作失败", e1.getMessage());
        }

        /**
         * 缩略图end
         */

        Map<String, Object> map = new HashMap<String, Object>();
        // 原图地址
        map.put("originalUrl", pathName);
        // 缩略图地址
        map.put("thumbnailUrl", thumbnailPathName);
        return map.toString();
    }

}
