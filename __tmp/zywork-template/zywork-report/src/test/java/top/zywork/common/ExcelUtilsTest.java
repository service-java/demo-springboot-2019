package top.zywork.common;

import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import top.zywork.enums.MIMETypeEnum;

/**
 * Excel工具测试类<br />
 * 创建于2017-10-31
 *
 * @author 王振宇
 */
public class ExcelUtilsTest {

    @Test
    public void readExcel() {
        ExcelUtils excelUtils = new ExcelUtils();
        Workbook workbook = excelUtils.readExcel(ExcelUtils.class.getResourceAsStream("/excel/excel_test.xlsx"), MIMETypeEnum.XLSX);
        Sheet sheet = workbook.getSheetAt(0);
        System.out.println(excelUtils.getStringCellValueAt(sheet, 0, 0));
        excelUtils.close();
    }

    @Test
    public void writeExcel() {
        ExcelUtils excelUtils = new ExcelUtils();
        Workbook workbook = excelUtils.newExcel(MIMETypeEnum.XLSX);
        Sheet sheet = workbook.createSheet();
        sheet.createRow(5).createCell(5);
        excelUtils.setStringCellValueAt(sheet, 5, 5, "test");
        excelUtils.insertPicture(sheet, ExcelUtils.class.getResourceAsStream("/excel/idea.png"), MIMETypeEnum.PNG,
                0, 0, 100, 100, 5, 5, 6, 6);
//        excelUtils.insertPicture(sheet, ExcelUtils.class.getResourceAsStream("/excel/idea.png"), MIMETypeEnum.PNG,
//                5, 5);
        excelUtils.writeExcel("test.xlsx");
        excelUtils.close();
    }

    @Test
    public void testGetXlsxPicture() {
        ExcelUtils excelUtils = new ExcelUtils();
        Workbook workbook = excelUtils.readExcel(ExcelUtils.class.getResourceAsStream("/excel/test.xlsx"), MIMETypeEnum.XLSX);
        PictureData pictureData = excelUtils.getPictureData(workbook.getSheetAt(0), 5, 5);
        if (pictureData != null) {
            System.out.println(pictureData.getMimeType());
            ImageUtils.saveImage(pictureData.getData(), "a.png", MIMETypeEnum.PNG);
        } else {
            System.out.println("未读取到指定图片！");
        }
    }

    @Test
    public void testGetXlsPicture() {
        ExcelUtils excelUtils = new ExcelUtils();
        Workbook workbook = excelUtils.readExcel(ExcelUtils.class.getResourceAsStream("/excel/test.xls"), MIMETypeEnum.XLS);
        PictureData pictureData = excelUtils.getPictureData(workbook.getSheetAt(0), 5, 5);
        if (pictureData != null) {
            System.out.println(pictureData.getMimeType());
            ImageUtils.saveImage(pictureData.getData(), "b.png", MIMETypeEnum.PNG);
        } else {
            System.out.println("未读取到指定图片！");
        }
    }

    @Test
    public void testGetPicture() {
        ExcelUtils excelUtils = new ExcelUtils();
        Workbook workbook = excelUtils.readExcel(ExcelUtils.class.getResourceAsStream("/excel/test.xlsx"), MIMETypeEnum.XLSX);
        PictureData pictureData = excelUtils.getPictureData(excelUtils.getAllPictures(workbook.getSheetAt(0)),
                5, 5);
        if (pictureData != null) {
            System.out.println(pictureData.getMimeType());
            ImageUtils.saveImage(pictureData.getData(), "c.png", MIMETypeEnum.PNG);
        } else {
            System.out.println("未读取到指定图片！");
        }
    }

}
