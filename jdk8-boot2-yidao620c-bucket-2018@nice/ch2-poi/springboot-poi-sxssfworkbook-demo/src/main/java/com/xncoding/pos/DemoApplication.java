package com.xncoding.pos;

import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) throws FileNotFoundException, InvalidFormatException {
        long startTime = System.currentTimeMillis();
        BufferedOutputStream outPutStream = null;
        XSSFWorkbook workbook = null;
        FileInputStream inputStream = null;
        String filePath = "E:\\txt\\666.xlsx";
        try {
            workbook = getWorkBook(filePath);
            XSSFSheet sheet = workbook.getSheetAt(0);

            for (int i = 0; i < 50; i++) {
                for (int z = 0; z < 10000; z++) {
                    XSSFRow row = sheet.createRow(i * 10000 + z);
                    for (int j = 0; j < 10; j++) {
                        row.createCell(j).setCellValue("你好：" + j);
                    }
                }
                //每次要获取新的文件流对象，避免将之前写入的数据覆盖掉
                outPutStream = new BufferedOutputStream(new FileOutputStream(filePath));
                workbook.write(outPutStream);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outPutStream != null) {
                try {
                    outPutStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);

        SpringApplication.run(DemoApplication.class, args);
    }

    /**
     * 先创建一个XSSFWorkbook对象
     *
     * @param filePath
     * @return
     */
    public static XSSFWorkbook getWorkBook(String filePath) {
        XSSFWorkbook workbook = null;
        try {
            File fileXlsxPath = new File(filePath);
            BufferedOutputStream outPutStream = new BufferedOutputStream(new FileOutputStream(fileXlsxPath));
            workbook = new XSSFWorkbook();
            workbook.createSheet("测试");
            workbook.write(outPutStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return workbook;
    }


}
