package top.zywork.common;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期时间工具类
 * 建议使用JDK1.8提供的新时间API，如Instant, LocalDate, LocalDateTime类等
 * 创建于2017-08-16
 *
 * @author 王振宇
 * @version 1.0
 */
public class DateUtils {

    /**
     * 获取系统当前时间毫秒值
     * @return 系统当前时间毫秒值
     */
    public static long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    /**
     * 获取当前时间的Date对象
     * @return 当前时间的Date对象
     */
    public static Date currentDate() {
        return Calendar.getInstance().getTime();
    }

    /**
     * Date类型转换成Calendar
     * @param date 需要转换的Date实例
     * @return 转换后的Calendar实例
     */
    public static Calendar dateToCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    /**
     * Date实例转换成LocalDateTime实例
     * @param date 需要转换的Date实例
     * @return 转换后的LocalDateTime实例
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        return calendarToLocalDateTime(dateToCalendar(date));
    }

    /**
     * Date实例转换成Instant实例
     * @param date 需要转换的Date实例
     * @return 转换后的Instant实例
     */
    public static Instant dateToInstant(Date date) {
        return Instant.ofEpochMilli(date.getTime());
    }

    /**
     * Calendar实例转换成Date实例
     * @param calendar 需要转换的Calendar实例
     * @return 转换后的Date实例
     */
    public static Date calendarToDate(Calendar calendar) {
        return calendar.getTime();
    }

    /**
     * Calendar实例转换成LocalDateTime实例
     * @param calendar 需要转换的Calendar实例
     * @return 转换后的LocalDateTime实例
     */
    public static LocalDateTime calendarToLocalDateTime(Calendar calendar) {
        return LocalDateTime.ofInstant(millisToInstant(millis(calendar)), ZoneId.systemDefault());
    }

    /**
     * Calendar实例转换成Instant实例
     * @param calendar 需要转换的Calendar实例
     * @return 转换后的Instant实例
     */
    public static Instant calendarToInstant(Calendar calendar) {
        return Instant.ofEpochMilli(calendar.getTimeInMillis());
    }

    /**
     * Instant实例转换成LocalDateTime实例
     * @param instant 需要转换的Instant实例
     * @return 转换后的LocalDateTime实例
     */
    public static LocalDateTime instantToLocalDateTime(Instant instant) {
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    /**
     * LocalDateTime实例转换成Instant实例
     * @param localDateTime 需要转换的LocalDateTime实例
     * @return 转换后的Instant实例
     */
    public static Instant localDateTimeToInstant(LocalDateTime localDateTime) {
        return Instant.ofEpochMilli(millis(localDateTime));
    }

    /**
     * 时间毫秒值转换成Calendar实例
     * @param millis 时间毫秒值
     * @return 时间毫秒值对应的Calendar实例
     */
    public static Calendar millisToCalender(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar;
    }

    /**
     * 时间毫秒值转换成Date实例
     * @param millis 时间毫秒值
     * @return 时间毫秒值对应的Date实例
     */
    public static Date millisToDate(long millis) {
        return calendarToDate(millisToCalender(millis));
    }

    /**
     * 时间毫秒值转换成LocalDateTime实例
     * @param millis 时间毫秒值
     * @return 时间毫秒值对应的LocalDateTime实例
     */
    public static LocalDateTime millisToLocalDateTime(long millis) {
        return LocalDateTime.ofInstant(millisToInstant(millis), ZoneId.systemDefault());
    }

    /**
     * 时间毫秒值转换成Instant实例
     * @param millis 时间毫秒值
     * @return 时间毫秒值对应的Instant实例
     */
    public static Instant millisToInstant(long millis) {
        return Instant.ofEpochMilli(millis);
    }

    /**
     * 获取Date类型对应的时间毫秒值
     * @param date 需要转换的Date实例
     * @return 时间毫秒值
     */
    public static long millis(Date date) {
        return date.getTime();
    }

    /**
     * 获取Calendar类型对应的时间毫秒值
     * @param calendar 需要转换的Calendar实例
     * @return 时间毫秒值
     */
    public static long millis(Calendar calendar) {
        return calendar.getTimeInMillis();
    }

    /**
     * 获取Timestamp类型对应的时间毫秒值
     * @param timestamp 需要转换的Timestamp实例
     * @return 时间毫秒值
     */
    public static long millis(Timestamp timestamp) {
        return timestamp.getTime();
    }

    /**
     * 获取LocalDateTime类型对应的时间毫秒值
     * @param localDateTime 需要转换的LocalDateTime实例
     * @return 时间毫秒值
     */
    public static long millis(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * 获取instant类型对应的时间毫秒值
     * @param instant 需要转换的instant实例
     * @return 时间毫秒值
     */
    public static long millis(Instant instant) {
        return instant.toEpochMilli();
    }

}
