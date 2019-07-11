package top.zywork.common;

import org.junit.Test;

import java.util.Date;

/**
 * 时间期日工具测试类
 * 创建于2017-08-16
 *
 * @author 王振宇
 */
public class DateUtilsTest {

    @Test
    public void testDateToLocalDateTime() {
        System.out.println(DateUtils.dateToLocalDateTime(new Date()));
        System.out.println(DateFormatUtils.defaultFormat(DateUtils.dateToLocalDateTime(new Date())));
    }
}
