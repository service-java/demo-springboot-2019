package top.zywork.common;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.util.List;
import java.util.Map;

/**
 * 报表导出工具类，使用JasperReport<br />
 * 创建于2017-11-09
 *
 * @author 王振宇
 * @version 1.0
 */
public class ExportUtils {

    /**
     * 根据指定的参数和Bean Collection数据源导出PDF文件
     * @param jasperFile jasper文件路径
     * @param toFile 目标pdf文件的路径，可以指定或者为null
     * @param params 模板所需的参数
     * @param dataSource 模板所需的数据源
     * @param <T> 数据源泛型
     * @return 返回导出的PDF文件的路径，需要注意绝对路径和相对路径
     */
    public static <T> String exportPdf(String jasperFile, String toFile, Map<String, Object> params, List<T> dataSource) {
        String pdfFile = null;
        try {
            String jasperPrintFile = getJasperPrintFile(jasperFile, params, dataSource);
            if (toFile != null) {
                pdfFile = toFile;
                JasperExportManager.exportReportToPdfFile(jasperPrintFile, pdfFile);
            } else {
                pdfFile = JasperExportManager.exportReportToPdfFile(jasperPrintFile);
            }
        } catch (JRException e) {
            e.printStackTrace();
        }
        return pdfFile;
    }

    /**
     * 根据指定的参数和Bean Collection数据源导出HTML文件
     * @param jasperFile jasper文件路径
     * @param toFile 目标HTML文件的路径，可以指定或者为null
     * @param params 模板所需的参数
     * @param dataSource 模板所需的数据源
     * @param <T> 数据源泛型
     * @return 返回导出的HTML文件的路径，需要注意绝对路径和相对路径
     */
    public static <T> String exportHtml(String jasperFile, String toFile, Map<String, Object> params, List<T> dataSource) {
        String htmlFile = null;
        try {
            String jasperPrintFile = getJasperPrintFile(jasperFile, params, dataSource);
            if (toFile != null) {
                htmlFile = toFile;
                JasperExportManager.exportReportToHtmlFile(jasperPrintFile, htmlFile);
            } else {
                htmlFile = JasperExportManager.exportReportToHtmlFile(jasperPrintFile);
            }
        } catch (JRException e) {
            e.printStackTrace();
        }
        return htmlFile;
    }

    /**
     * 打印报表
     * @param jasperFile jasper文件
     * @param params 模板所需的参数
     * @param dataSource 模板所需的数据源
     * @param <T> 数据源泛型
     */
    public static <T> void print(String jasperFile, Map<String, Object> params, List<T> dataSource) {
        try {
            JasperPrintManager.printReport(getJasperPrintFile(jasperFile, params, dataSource), true);
        } catch (JRException e) {
            e.printStackTrace();
        }
    }

    /**
     * 填充数据源到模板中
     * @param jasperFile jasper文件
     * @param params 模板所需的参数
     * @param dataSource 模板所需的数据源
     * @param <T> 数据源泛型
     * @return 填充模板后获取的.jasperprint文件的文件名
     * @throws JRException JasperReport异常
     */
    private static <T> String getJasperPrintFile(String jasperFile, Map<String, Object> params, List<T> dataSource) throws JRException {
        return JasperFillManager.fillReportToFile(jasperFile, params, new JRBeanCollectionDataSource(dataSource));
    }

}
