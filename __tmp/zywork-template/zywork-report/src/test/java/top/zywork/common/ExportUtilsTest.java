package top.zywork.common;

import org.junit.Test;
import top.zywork.vo.SaleVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * ExportUtils工具测试类<br />
 * 创建于2017-11-14
 *
 * @author 王振宇
 * @version 1.0
 */
public class ExportUtilsTest {

    @Test
    public void exportPdf() {
        List<SaleVO> saleList = new ArrayList<>();
        saleList.add(new SaleVO(1, "小张", 100));
        saleList.add(new SaleVO(2, "小王", 200));
        ExportUtils.exportPdf("src/test/resources/reports/report1.jasper", null, new HashMap<String, Object>(), saleList);
    }
}

