package com.xkcoding.upload.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.qiniu.http.Response;
import com.xkcoding.upload.service.IQiNiuService;
import com.xkcoding.upload.util.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * <p>
 * 文件上传 Controller
 * </p>
 *
 * @package: com.xkcoding.upload.controller
 * @description: 文件上传 Controller
 * @author: yangkai.shen
 * @date: Created in 2018/11/6 16:33
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@RestController
@Slf4j
@RequestMapping("/upload")
public class UploadController {
	@Value("${spring.servlet.multipart.location}")
	private String fileTempPath;

	@Value("${qiniu.prefix}")
	private String prefix;

	private final IQiNiuService qiNiuService;

	@Autowired
	public UploadController(IQiNiuService qiNiuService) {
		this.qiNiuService = qiNiuService;
	}

	@PostMapping(value = "/local", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public Dict local(@RequestParam("file") MultipartFile file) {
		if (file.isEmpty()) {
			return Dict.create().set("code", 400).set("message", "文件内容为空");
		}
		String fileName = file.getOriginalFilename();
		// 文件名 (最后一个".")
		String rawFileName = StrUtil.subBefore(fileName, ".", true);
		// 文件后缀格式
		String fileType = StrUtil.subAfter(fileName, ".", true);
		// 加上时间戳
		String localFilePath = StrUtil.appendIfMissing(fileTempPath, "/") + rawFileName + "-" + DateUtil.current(false) + "." + fileType;
		try {
			file.transferTo(new File(localFilePath));
		} catch (IOException e) {
			log.error("【文件上传至本地】失败，绝对路径：{}", localFilePath);
			return Dict.create().set("code", 500).set("message", "文件上传失败");
		}

		log.info("【文件上传至本地】绝对路径：{}", localFilePath);
		return Dict.create().set("code", 200).set("message", "上传成功").set("data", Dict.create().set("fileName", fileName).set("filePath", localFilePath));
	}

    @GetMapping("/download")
    public void downloadTemplate(HttpServletResponse response, HttpServletRequest request, String url) {
	    if (StrUtil.isEmpty(url)) {
            log.error("文件路径不能为空！");
            return;
        }

        try {
            // String filePath = ResourceUtils.getFile("classpath:excelTemplate/").getAbsolutePath();
            String filePath = fileTempPath + url;

            response.setCharacterEncoding("utf-8");
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition",
                    "attachment;fileName=" + FileUtils.setFileDownloadHeader(request, "签约单位-导入模板.xlsx"));
            FileUtils.writeBytes(filePath, response.getOutputStream());

        } catch (Exception e) {
            log.error("下载模板失败", e);
        }
    }

	@PostMapping(value = "/yun", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public Dict yun(@RequestParam("file") MultipartFile file) {
		if (file.isEmpty()) {
			return Dict.create().set("code", 400).set("message", "文件内容为空");
		}
		String fileName = file.getOriginalFilename();
		String rawFileName = StrUtil.subBefore(fileName, ".", true);
		String fileType = StrUtil.subAfter(fileName, ".", true);
		String localFilePath = StrUtil.appendIfMissing(fileTempPath, "/") + rawFileName + "-" + DateUtil.current(false) + "." + fileType;
		try {
			// 先存到本地??
		    file.transferTo(new File(localFilePath));
			Response response = qiNiuService.uploadFile(new File(localFilePath));
			if (response.isOK()) {
				JSONObject jsonObject = JSONUtil.parseObj(response.bodyString());

				String yunFileName = jsonObject.getStr("key");
				String yunFilePath = StrUtil.appendIfMissing(prefix, "/") + yunFileName;

				// 删除本地文件
				FileUtil.del(new File(localFilePath));

				log.info("【文件上传至七牛云】绝对路径：{}", yunFilePath);
				return Dict.create().set("code", 200).set("message", "上传成功").set("data", Dict.create().set("fileName", yunFileName).set("filePath", yunFilePath));
			} else {
				log.error("【文件上传至七牛云】失败，{}", JSONUtil.toJsonStr(response));
				FileUtil.del(new File(localFilePath));
				return Dict.create().set("code", 500).set("message", "文件上传失败");
			}
		} catch (IOException e) {
			log.error("【文件上传至七牛云】失败，绝对路径：{}", localFilePath);
			return Dict.create().set("code", 500).set("message", "文件上传失败");
		}
	}
}
