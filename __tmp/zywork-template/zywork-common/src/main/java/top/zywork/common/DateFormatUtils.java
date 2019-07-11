package top.zywork.common;

import top.zywork.enums.DatePatternEnum;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * 时间格式化工具
 * 创建于2017-08-16
 *
 * @author 王振宇
 * @version 1.0
 */
public class DateFormatUtils {

    public static Map<String, ThreadLocal<DateFormat>> dfMap = new HashMap<String, ThreadLocal<DateFormat>>();

    /**
     * 通过指定的格式获取SimpleDateFormat类的实例，考虑线程安全
     * @param pattern 时间格式
     * @return SimpleDateFormat类的实例
     */
    public static DateFormat getDateFormat(final String pattern) {
        ThreadLocal<DateFormat> dfThreadLocal = dfMap.get(pattern);
        if (dfThreadLocal == null) {
            synchronized (DateFormatUtils.class) {
                dfThreadLocal = ThreadLocal.withInitial(new Supplier<DateFormat>() {
                    @Override
                    public DateFormat get() {
                        return new SimpleDateFormat(pattern);
                    }
                });
                dfMap.put(pattern, dfThreadLocal);
            }
        }
        return dfThreadLocal.get();
    }

    /**
     * 通过指定格式把Calendar格式化
     * @param cal Calendar类的实例指定的时间
     * @param pattern 时间格式
     * @return 按时间格式格式化后的时间字符串
     */
    public static String format(Calendar cal, String pattern) {
        return getDateFormat(pattern).format(cal.getTime());
    }

    /**
     * 通过指定格式把豪秒值格式化
     * @param millis 时间毫秒值
     * @param pattern 时间格式
     * @return 按时间格式格式化后的时间字符串
     */
    public static String format(long millis, String pattern) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(millis);
        return format(cal, pattern);
    }

    /**
     * 通过指定格式把Date格式化
     * @param date Date实例
     * @param pattern 时间格式
     * @return 按时间格式格式化后的时间字符串
     */
    public static String format(Date date, String pattern) {
        return getDateFormat(pattern).format(date);
    }

    /**
     * 通过指定格式把Timestamp格式化
     * @param timestamp Timestamp实例
     * @param pattern 时间格式
     * @return 按时间格式格式化后的时间字符串
     */
    public static String format(Timestamp timestamp, String pattern) {
        return getDateFormat(pattern).format(timestamp);
    }

    /**
     * 针对JDK1.8提供的新的时间日期类的格式化
     * @param localDateTime LocalDateTime实例，JDK1.8中提供
     * @param pattern 时间格式
     * @return 按时间格式格式化后的时间字符串
     */
    public static String format(LocalDateTime localDateTime, String pattern) {
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 通过指定格式把Instant格式化
     * @param instant Instant实例
     * @param pattern 时间格式
     * @return 按时间格式格式化后的时间字符串
     */
    public static String format(Instant instant, String pattern) {
        return format(instant.toEpochMilli(), pattern);
    }

    /**
     * 根据默认的格式把Date实例格式化
     * @param date Date实例
     * @return 按默认的时间格式格式化后的时间字符串
     */
    public static String defaultFormat(Date date) {
        return format(date, DatePatternEnum.DATETIME.getValue());
    }

    /**
     * 根据默认的时间格式把Calendar实例格式化
     * @param cal Calendar类实例
     * @return 按默认的时间格式格式化后的时间字符串
     */
    public static String defaultFormat(Calendar cal) {
        return format(cal, DatePatternEnum.DATETIME.getValue());
    }

    /**
     * 根据默认的时间格式把毫秒值时间格式化
     * @param millis 时间毫秒值
     * @return 按默认的时间格式格式化后的时间字符串
     */
    public static String defaultFormat(long millis) {
        return format(millis, DatePatternEnum.DATETIME.getValue());
    }

    /**
     * 根据默认的时候格式把LocalDateTime时间格式化
     * @param localDateTime LocalDateTime实例
     * @return 按默认的时间格式格式化后的时间字符串
     */
    public static String defaultFormat(LocalDateTime localDateTime) {
        return format(localDateTime, DatePatternEnum.DATETIME.getValue());
    }

    /**
     * 根据默认的时候格式把Instant时间格式化
     * @param instant Instant实例
     * @return 按默认的时间格式格式化后的时间字符串
     */
    public static String defaultFormat(Instant instant) {
        return format(instant, DatePatternEnum.DATETIME.getValue());
    }

    /**
     * 根据默认的时间格式把Timestamp格式化
     * @param timestamp Timestamp实例
     * @return 按默认的时间格式格式化后的时间字符串
     */
    public static String defaultFormat(Timestamp timestamp) {
        return format(timestamp, DatePatternEnum.DATETIME.getValue());
    }

}
