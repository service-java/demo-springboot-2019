package top.zywork.common;

import top.zywork.enums.DatePassEnum;

import java.util.Calendar;
import java.util.Date;

/**
 * 计算多少时间前的工具类
 * 时间日期等可以考虑使用JDK1.8提供的新API，如Instant, LocalDate, LocalDatetime等
 * 创建于2018-08-16
 *
 * @author 王振宇
 * @version 1.0
 */
public class DatePassUtils {

    public static final long SIXTY_SECONDS = 60 * 1000;
    public static final long SIXTY_MINUTES = 60 * SIXTY_SECONDS;
    public static final long TWENTY_FOUR_HOURS = 24 * SIXTY_MINUTES;
    public static final long TWO_DAYS  = 2 * TWENTY_FOUR_HOURS;
    public static final long SEVEN_DAYS = 7 * TWENTY_FOUR_HOURS;

    /**
     * 用于计算前后两个时间相差的时间数，显示出x秒前，x分钟前，x小时前，昨天，x天前，或具体的时间
     * @param pastTimeMillis 过去的时间毫秒值
     * @param nowTimeMillis 现在的时间毫秒值，应大于等于pastTimeMillis参数
     * @return 计算后得到的x秒前，x分钟前，x小时前，昨天，x天前，或具体的时间
     */
    public static String passTime(long pastTimeMillis, long nowTimeMillis) {
        long pastTime = nowTimeMillis - pastTimeMillis;
        if(pastTime < SIXTY_SECONDS) {
            return pastTime / 1000 + DatePassEnum.SECONDS_PASS.getValue();
        } else if(pastTime < SIXTY_MINUTES) {
            return pastTime / 1000 / 60 + DatePassEnum.MINUTES_PASS.getValue();
        } else if(pastTime < TWENTY_FOUR_HOURS) {
            return pastTime / 1000 / 60 / 60 + DatePassEnum.HOURS_PASS.getValue();
        } else if(pastTime < TWO_DAYS) {
            return DatePassEnum.YESTERDAY.getValue();
        } else if(pastTime < SEVEN_DAYS) {
            return pastTime / 1000 / 60 / 60 / 24 + DatePassEnum.DAYS_PASS.getValue();
        } else {
            return DateFormatUtils.defaultFormat(pastTimeMillis);
        }
    }

    /**
     * 两个日历相比较的结果
     * @param past 过去时间的Calendar实例
     * @param now 现在时间的Calendar实例
     * @return 计算后得到的x秒前，x分钟前，x小时前，昨天，x天前，或具体的时间
     */
    public static String passTime(Calendar past, Calendar now) {
        return passTime(past.getTimeInMillis(), now.getTimeInMillis());
    }

    /**
     * 两个Date实例相比较的结果
     * @param past 过去时间的Date实例
     * @param now 现在时间的Date实例
     * @return 计算后得到的x秒前，x分钟前，x小时前，昨天，x天前，或具体的时间
     */
    public static String passTime(Date past, Date now) {
        return passTime(past.getTime(), now.getTime());
    }

    /**
     * 当前系统时间与过去时间比较的结果
     * @param pastTimeMillis 过去时间的毫秒值
     * @return 计算后得到的x秒前，x分钟前，x小时前，昨天，x天前，或具体的时间
     */
    public static String passTime(long pastTimeMillis) {
        return passTime(pastTimeMillis, Calendar.getInstance().getTimeInMillis());
    }

    /**
     * 当前系统时间与过去时间比较的结果
     * @param past 过去时间的Calendar实例
     * @return 计算后得到的x秒前，x分钟前，x小时前，昨天，x天前，或具体的时间
     */
    public static String passTime(Calendar past) {
        return passTime(past, Calendar.getInstance());
    }

    /**
     * 当前系统时间与过去时间比较的结果
     * @param past 过去时间的Date实例
     * @return 计算后得到的x秒前，x分钟前，x小时前，昨天，x天前，或具体的时间
     */
    public static String passTime(Date past) {
        return passTime(past.getTime(), Calendar.getInstance().getTimeInMillis());
    }

}
