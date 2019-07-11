package top.zywork.common;

import org.apache.poi.POIXMLDocumentPart;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFPicture;
import org.apache.poi.hssf.usermodel.HSSFShape;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTMarker;
import top.zywork.enums.MIMETypeEnum;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Excel文档工具类<br />
 * 创建于2017-10-30
 *
 * @author 王振宇
 * @version 1.0
 */
public class ExcelUtils {

    public static final CellStyle NONE_CELL_STYLE = null;
    public static final Object NONE_CELL_VALUE = null;

    private Workbook workbook;

    /**
     * 判断指定路径的文件是否为excel文件
     * @param path 文件路径
     * @return 如果是.xls或.xlsx后缀，则返回true，否则返回false
     */
    public static boolean isExcel(String path) {
        return FileUtils.checkExtension(path, MIMETypeEnum.XLS.getExt() + "," + MIMETypeEnum.XLSX.getExt());
    }

    /**
     * 从指定的输入流中读取excel文件
     * @param inputStream 输入流
     * @param mimeTypeEnum 文件类型枚举，可以是xls或xlsx文件
     * @return Excel文档对应的Workbook对象
     */
    public Workbook readExcel(InputStream inputStream, MIMETypeEnum mimeTypeEnum) {
        try {
            if (mimeTypeEnum == MIMETypeEnum.XLS) {
                workbook = new HSSFWorkbook(inputStream);
            } else if (mimeTypeEnum == MIMETypeEnum.XLSX) {
                workbook = new XSSFWorkbook(inputStream);
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return workbook;
    }

    /**
     * 根据指定的文件路径读取excel文件
     * @param path 文件路径
     * @param mimeTypeEnum 文件类型枚举，可以是xls或xlsx文件
     * @return Excel文档对应的Workbook对象
     */
    public Workbook readExcel(String path, MIMETypeEnum mimeTypeEnum) {
        try {
            readExcel(new FileInputStream(new File(path)), mimeTypeEnum);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return workbook;
    }

    /**
     * 根据指定的文件路径读取excel文件
     * @param path 文件路径
     * @return Excel文档对应的Workbook对象
     */
    public Workbook readExcel(String path) {
        String type = FileUtils.getExtensionWithoutDot(path);
        if (type.equals(MIMETypeEnum.XLS.getValue())) {
            return readExcel(path, MIMETypeEnum.XLS);
        } else if (type.equals(MIMETypeEnum.XLSX.getValue())) {
            return readExcel(path, MIMETypeEnum.XLSX);
        }
        return null;
    }

    /**
     * 创建新的excel文档
     * @param mimeTypeEnum 文件类型枚举，指定是创建xls文档还是xlsx文档
     * @return 新创建的excel文档对应的Workbook对象
     */
    public Workbook newExcel(MIMETypeEnum mimeTypeEnum) {
        if (mimeTypeEnum == MIMETypeEnum.XLS) {
            workbook = new HSSFWorkbook();
        } else if (mimeTypeEnum == MIMETypeEnum.XLSX) {
            workbook = new XSSFWorkbook();
        }
        return workbook;
    }

    /**
     * 把excel文档重新保存到指定的文件
     * @param path 新的文件路径
     */
    public void writeExcel(String path) {
        try {
            FileOutputStream outputStream = new FileOutputStream(new File(path));
            workbook.write(outputStream);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取指定工作表指定行指定列的布尔数据
     * @param sheet 工作表对象
     * @param rowNo 指定行
     * @param columnNo 指定列
     * @return true或false
     */
    public boolean getBooleanCellValueAt(Sheet sheet, int rowNo, int columnNo) {
        return sheet.getRow(rowNo).getCell(columnNo).getBooleanCellValue();
    }

    /**
     * 获取指定工作表指定行指定列的字符串数据
     * @param sheet 工作表对象
     * @param rowNo 指定行
     * @param columnNo 指定列
     * @return 字符串
     */
    public String getStringCellValueAt(Sheet sheet, int rowNo, int columnNo) {
        return sheet.getRow(rowNo).getCell(columnNo).getStringCellValue();
    }

    /**
     * 获取指定工作表指定行指定列的整数数据
     * @param sheet 工作表对象
     * @param rowNo 指定行
     * @param columnNo 指定列
     * @return 整数
     */
    public int getIntegerCellValueAt(Sheet sheet, int rowNo, int columnNo) {
        return (int) getDoubleCellValueAt(sheet, rowNo, columnNo);
    }

    /**
     * 获取指定工作表指定行指定列的浮点数数据
     * @param sheet 工作表对象
     * @param rowNo 指定行
     * @param columnNo 指定列
     * @return 浮点数
     */
    public double getDoubleCellValueAt(Sheet sheet, int rowNo, int columnNo) {
        return sheet.getRow(rowNo).getCell(columnNo).getNumericCellValue();
    }

    /**
     * 获取指定工作表指定行指定列的时间数据
     * @param sheet 工作表对象
     * @param rowNo 指定行
     * @param columnNo 指定列
     * @return 时间
     */
    public Date getDateCellValueAt(Sheet sheet, int rowNo, int columnNo) {
        return sheet.getRow(rowNo).getCell(columnNo).getDateCellValue();
    }

    /**
     * 设置指定工作表指定行指定列的布尔数据
     * @param sheet 工作表对象
     * @param rowNo 指定行
     * @param columnNo 指定列
     * @param value 数据
     */
    public void setBooleanCellValueAt(Sheet sheet, int rowNo, int columnNo, boolean value) {
        sheet.getRow(rowNo).getCell(columnNo).setCellValue(value);
    }

    /**
     * 设置指定工作表指定行指定列的字符串数据
     * @param sheet 工作表对象
     * @param rowNo 指定行
     * @param columnNo 指定列
     * @param value 数据
     */
    public void setStringCellValueAt(Sheet sheet, int rowNo, int columnNo, String value) {
        sheet.getRow(rowNo).getCell(columnNo).setCellValue(value);
    }

    /**
     * 设置指定工作表指定行指定列的整数数据
     * @param sheet 工作表对象
     * @param rowNo 指定行
     * @param columnNo 指定列
     * @param value 数据
     */
    public void setIntegerCellValueAt(Sheet sheet, int rowNo, int columnNo, int value) {
        sheet.getRow(rowNo).getCell(columnNo).setCellValue(value);
    }

    /**
     * 设置指定工作表指定行指定列的浮点数数据
     * @param sheet 工作表对象
     * @param rowNo 指定行
     * @param columnNo 指定列
     * @param value 数据
     */
    public void setDoubleCellValueAt(Sheet sheet, int rowNo, int columnNo, double value) {
        sheet.getRow(rowNo).getCell(columnNo).setCellValue(value);
    }

    /**
     * 设置指定工作表指定行指定列的时间数据
     * @param sheet 工作表对象
     * @param rowNo 指定行
     * @param columnNo 指定列
     * @param value 数据
     */
    public void setDateCellValueAt(Sheet sheet, int rowNo, int columnNo, Date value) {
        sheet.getRow(rowNo).getCell(columnNo).setCellValue(value);
    }

    /**
     * 设置指定工作表的指定行指定列的值
     * @param sheet 工作表
     * @param rowNo 指定行
     * @param columnNo 指定列
     * @param cellValue 数据值，此值必须是excel单元格可接收的数据类型，如boolean, numeric, String, Date, Calendar
     */
    public void setCellValueAt(Sheet sheet, int rowNo, int columnNo, Object cellValue) {
        setCellValue(sheet.getRow(rowNo).getCell(columnNo), cellValue);
    }

    /**
     * 把值设置到单元格
     * @param cell 单元格对象
     * @param cellValue 数据值，此值必须是excel单元格可接收的数据类型，如boolean, numeric, String, Date, Calendar
     */
    public void setCellValue(Cell cell, Object cellValue) {
        if (cellValue instanceof Double) {
            cell.setCellValue((double) cellValue);
        } else if (cellValue instanceof Boolean) {
            cell.setCellValue((boolean) cellValue);
        } else if (cellValue instanceof String) {
            cell.setCellValue((String) cellValue);
        } else if (cellValue instanceof Integer) {
            cell.setCellValue((Integer) cellValue);
        } else if (cellValue instanceof Date) {
            cell.setCellValue((Date) cellValue);
        } else if (cellValue instanceof Calendar) {
            cell.setCellValue((Calendar) cellValue);
        } else if (cellValue instanceof Float) {
            cell.setCellValue((float) cellValue);
        }
    }

    /**
     * 在指定工作表创建行，如果行已经存在，则直接返回，否则创建新行
     * @param sheet 工作表
     * @param rowNo 指定行
     * @return 已经存在的或新建的Row行对象
     */
    public Row createRow(Sheet sheet, int rowNo) {
        Row row = sheet.getRow(rowNo);
        return row == null ? sheet.createRow(rowNo) : row;
    }

    /**
     * 在指定工作表的指定行指定列创建单元格
     * @param sheet 工作表
     * @param rowNo 指定行
     * @param colNo 指定列
     * @return 创建好的单元格对象
     */
    public Cell createCell(Sheet sheet, int rowNo, int colNo) {
        return createRow(sheet, rowNo).createCell(colNo);
    }

    /**
     * 在指定工作表的指定行指定列创建单元格，如果cellStyle不为空，则设置单元格样式，如果cellValue不为空，则设置单元格的值
     * @param sheet 工作表
     * @param rowNo 指定行
     * @param colNo 指定列
     * @param cellStyle 样式，可以传递null或ExcelUtils.NONE_CELL_STYLE
     * @param cellValue 数据值，可以传递null或ExcelUtils.NONE_CELL_VALUE，如果此值不为空，
     *                  则必须是excel单元格可接收的数据类型，如boolean, numeric, String, Date, Calendar
     * @return 创建好的单元格对象
     */
    public Cell createCell(Sheet sheet, int rowNo, int colNo, CellStyle cellStyle, Object cellValue) {
        Cell cell = createRow(sheet, rowNo).createCell(colNo);
        if (cellStyle != NONE_CELL_STYLE) {
            cell.setCellStyle(cellStyle);
        }
        if (cellValue != NONE_CELL_VALUE) {
            setCellValue(cell, cellValue);
        }
        return cell;
    }

    /**
     * 合并工作表单元格
     * @param sheet 工作表
     * @param beginRow 开始行
     * @param beginCol 开始列
     * @param endRow 结束行
     * @param endCol 结束列
     */
    public void mergeCells(Sheet sheet, int beginRow, int beginCol, int endRow, int endCol) {
        sheet.addMergedRegion(new CellRangeAddress(beginRow, endRow, beginCol, endCol));
    }

    /**
     * 在指定工作表的指定位置插入图片
     * @param sheet 工作表
     * @param imagePath 图片路径
     * @param leftDX 图片在单元格中离左上角的x距离
     * @param topDY 图片在单元格中离左上角的y距离
     * @param widthDX 图片的宽度
     * @param heightDY 图片的高度
     * @param beginRow 图片开始的行
     * @param beginCol 图片开始的列
     * @param endRow 图片结束的行
     * @param endCol 图片结束的列
     */
    public void insertPicture(Sheet sheet, String imagePath,
                           int leftDX, int topDY, int widthDX ,int heightDY,
                           int beginRow, int beginCol, int endRow, int endCol) {
        Drawing drawing = sheet.createDrawingPatriarch();
        ClientAnchor clientAnchor = workbook.getCreationHelper().createClientAnchor();
        clientAnchor.setDx1(leftDX);
        clientAnchor.setDy1(topDY);
        clientAnchor.setDx2(widthDX);
        clientAnchor.setDy2(heightDY);
        clientAnchor.setRow1(beginRow);
        clientAnchor.setCol1(beginCol);
        clientAnchor.setRow2(endRow);
        clientAnchor.setCol2(endCol);
        drawing.createPicture(clientAnchor,
                workbook.addPicture(ImageUtils.getImageData(imagePath), getImageType(imagePath)));
    }

    /**
     * 在指定工作表的指定位置插入图片
     * @param sheet 工作表
     * @param imageInputStream 图片输入流
     * @param imageType 图片文件类型枚举，推荐使用png或jpg图片
     * @param leftDX 图片在单元格中离左上角的x距离
     * @param topDY 图片在单元格中离左上角的y距离
     * @param widthDX 图片的宽度
     * @param heightDY 图片的高度
     * @param beginRow 图片开始的行
     * @param beginCol 图片开始的列
     * @param endRow 图片结束的行
     * @param endCol 图片结束的列
     */
    public void insertPicture(Sheet sheet, InputStream imageInputStream, MIMETypeEnum imageType,
                              int leftDX, int topDY, int widthDX ,int heightDY,
                              int beginRow, int beginCol, int endRow, int endCol) {
        Drawing drawing = sheet.createDrawingPatriarch();
        ClientAnchor clientAnchor = workbook.getCreationHelper().createClientAnchor();
        clientAnchor.setDx1(leftDX);
        clientAnchor.setDy1(topDY);
        clientAnchor.setDx2(widthDX);
        clientAnchor.setDy2(heightDY);
        clientAnchor.setRow1(beginRow);
        clientAnchor.setCol1(beginCol);
        clientAnchor.setRow2(endRow);
        clientAnchor.setCol2(endCol);
        drawing.createPicture(clientAnchor,
                workbook.addPicture(ImageUtils.getImageData(imageInputStream, imageType), getImageType(imageType)));
    }

    /**
     * 在指定工作表的指定位置插入图片
     * @param sheet 工作表
     * @param imagePath 图片路径
     * @param beginRow 图片开始的行
     * @param beginCol 图片开始的列
     */
    public void insertPicture(Sheet sheet, String imagePath,
                              int beginRow, int beginCol) {
        Drawing drawing = sheet.createDrawingPatriarch();
        ClientAnchor clientAnchor = workbook.getCreationHelper().createClientAnchor();
        clientAnchor.setRow1(beginRow);
        clientAnchor.setCol1(beginCol);
        Picture picture = drawing.createPicture(clientAnchor,
                workbook.addPicture(ImageUtils.getImageData(imagePath), getImageType(imagePath)));
        picture.resize();
    }

    /**
     * 在指定工作表的指定位置插入图片
     * @param sheet 工作表
     * @param imageInputStream 图片输入流
     * @param imageType 图片文件类型枚举，推荐使用png或jpg图片
     * @param beginRow 图片开始的行
     * @param beginCol 图片开始的列
     */
    public void insertPicture(Sheet sheet, InputStream imageInputStream, MIMETypeEnum imageType,
                              int beginRow, int beginCol) {
        Drawing drawing = sheet.createDrawingPatriarch();
        ClientAnchor clientAnchor = workbook.getCreationHelper().createClientAnchor();
        clientAnchor.setRow1(beginRow);
        clientAnchor.setCol1(beginCol);
        Picture picture = drawing.createPicture(clientAnchor,
                workbook.addPicture(ImageUtils.getImageData(imageInputStream, imageType), getImageType(imageType)));
        picture.resize();
    }

    /**
     * 获取指定工作表里的所有图片数据，xls和xlsx通用
     * @param sheet 工作表
     * @return 工作表里所有图片Picture对象组成的列表
     */
    public List<Picture> getAllPictures(Sheet sheet) {
        if (workbook instanceof HSSFWorkbook) {
            return getXlsPictures(sheet);
        } else if (workbook instanceof XSSFWorkbook) {
            return getXlsxPictures(sheet);
        }
        return null;
    }

    /**
     * 获取指定工作表里的所有图片数据，只能处理xls文档
     * @param sheet 工作表
     * @return 工作表里所有图片Picture对象组成的列表
     */
    public List<Picture> getXlsPictures(Sheet sheet) {
        HSSFPatriarch patriarch = (HSSFPatriarch) sheet.getDrawingPatriarch();
        List<HSSFShape> shapeList = patriarch.getChildren();
        List<Picture> pictureList = new ArrayList<>();
        for (HSSFShape shape : shapeList) {
            if (shape instanceof HSSFPicture) {
                Picture picture = (HSSFPicture) shape;
                pictureList.add(picture);
            }
        }
        return pictureList;
    }

    /**
     * 获取指定工作表里的所有图片数据，只能处理xlsx文档
     * @param sheet 工作表
     * @return 工作表里所有图片Picture对象组成的列表
     */
    public List<Picture> getXlsxPictures(Sheet sheet) {
        XSSFSheet xssfSheet = (XSSFSheet) sheet;
        List<POIXMLDocumentPart> documentPartList = xssfSheet.getRelations();
        List<Picture> pictureList = new ArrayList<>();
        for (POIXMLDocumentPart documentPart : documentPartList) {
            if (documentPart instanceof XSSFDrawing) {
                XSSFDrawing drawing = (XSSFDrawing) documentPart;
                List<XSSFShape> shapeList = drawing.getShapes();
                for (XSSFShape shape : shapeList) {
                    if (shape instanceof XSSFPicture) {
                        Picture picture = (XSSFPicture) shape;
                        pictureList.add(picture);
                    }
                }
            }
        }
        return pictureList;
    }

    /**
     * 获取指定工作表里指定行指定列上的图片数据，xls和xlsx通用。
     * 此方法每次调用都会重复获取图片数据，影响性能，建议使用@see指定的方法
     * @param sheet 工作表
     * @param rowNo 指定行
     * @param colNo 指定列
     * @return POI中的PictureData对象
     * @see #getPictureData(List, int, int)
     */
    public PictureData getPictureData(Sheet sheet, int rowNo, int colNo) {
        if (workbook instanceof HSSFWorkbook) {
            return getXlsPictureData(sheet, rowNo, colNo);
        } else if (workbook instanceof XSSFWorkbook) {
            return getXlsxPictureData(sheet, rowNo, colNo);
        }
        return null;
    }

    /**
     * 获取指定工作表里指定行指定列上的图片数据，只能处理xls文档。
     * 此方法每次调用都会重复获取图片数据，影响性能，建议使用@see指定的方法
     * @param sheet 工作表
     * @param rowNo 指定行
     * @param colNo 指定列
     * @return POI中的PictureData对象
     * @see #getXlsPictureData(List, int, int)
     */
    public PictureData getXlsPictureData(Sheet sheet, int rowNo, int colNo) {
       return getXlsPictureData(getXlsPictures(sheet), rowNo, colNo);
    }

    /**
     * 获取指定工作表里指定行指定列上的图片数据，只能处理xlsx文档。
     * 此方法每次调用都会重复获取图片数据，影响性能，建议使用@see指定的方法
     * @param sheet 工作表
     * @param rowNo 指定行
     * @param colNo 指定列
     * @return POI中的PictureData对象
     * @see #getXlsxPictureData(List, int, int)
     */
    public PictureData getXlsxPictureData(Sheet sheet, int rowNo, int colNo) {
        return getXlsxPictureData(getXlsxPictures(sheet), rowNo, colNo);
    }

    /**
     * 获取指定工作表里指定行指定列上的图片数据，xls和xlsx通用。
     * 此方法提升了获取图片的性能
     * @param pictureList Picture列表
     * @param rowNo 指定行
     * @param colNo 指定列
     * @return POI中的PictureData对象
     */
    public PictureData getPictureData(List<Picture> pictureList, int rowNo, int colNo) {
        if (workbook instanceof HSSFWorkbook) {
            return getXlsPictureData(pictureList, rowNo, colNo);
        } else if (workbook instanceof XSSFWorkbook) {
            return getXlsxPictureData(pictureList, rowNo, colNo);
        }
        return null;
    }

    /**
     * 获取指定工作表里指定行指定列上的图片数据，只能处理xls文档。
     * 此方法提升了获取图片的性能
     * @param pictureList Picture列表
     * @param rowNo 指定行
     * @param colNo 指定列
     * @return POI中的PictureData对象
     */
    public PictureData getXlsPictureData(List<Picture> pictureList, int rowNo, int colNo) {
        for (Picture picture : pictureList) {
            ClientAnchor anchor = picture.getClientAnchor();
            if (anchor.getRow1() == rowNo && anchor.getCol1() == colNo) {
                return picture.getPictureData();
            }
        }
        return null;
    }

    /**
     * 获取指定工作表里指定行指定列上的图片数据，只能处理xlsx文档。
     * 此方法提升了获取图片的性能
     * @param pictureList Picture列表
     * @param rowNo 指定行
     * @param colNo 指定列
     * @return POI中的PictureData对象
     */
    public PictureData getXlsxPictureData(List<Picture> pictureList, int rowNo, int colNo) {
        for (Picture picture : pictureList) {
            XSSFClientAnchor anchor = (XSSFClientAnchor) picture.getPreferredSize();
            CTMarker marker = anchor.getFrom();
            if (marker.getRow() == rowNo && marker.getCol() == colNo) {
                return picture.getPictureData();
            }
        }
        return null;
    }


    /**
     * 根据图片路径获取到POI中对应的图片类型整型常量
     * @param imagePath
     * @return POI中图片类型对应的整型常量
     */
    private int getImageType(String imagePath) {
        return getImageType(ImageUtils.getImageType(imagePath));
    }

    /**
     * 根据图片类型枚举获取到POI中对应的图片类型整型常量
     * @param imageType
     * @return POI中图片类型对应的整型常量
     */
    private int getImageType(MIMETypeEnum imageType) {
        if (imageType == MIMETypeEnum.JPG) {
            return Workbook.PICTURE_TYPE_JPEG;
        } else if (imageType == MIMETypeEnum.PNG) {
            return Workbook.PICTURE_TYPE_PNG;
        }
        return -1;
    }

    /**
     * 关闭Workbook对象
     */
    public void close() {
        if (workbook != null) {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
