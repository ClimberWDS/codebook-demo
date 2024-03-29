package com.wds.codebook.common.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * 日期工具类
 */
public class DateUtil {

    private static Logger log = LoggerFactory.getLogger(DateUtil.class);

    // 默认日期格式
    public static final String DATE_DEFAULT_FORMAT = "yyyy-MM-dd";

    // 默认时间格式
    public static final String DATETIME_DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";

    // 默认时间格式
    public static final String PAY_FORMAT = "yyyyMMddHHmmss";

    public static final String TIME_DEFAULT_FORMAT = "HH:mm:ss";

    // 默认日期格式
    public static final String DATE_MONTH_FORMAT = "yyyy-MM";

    // 默认日期格式
    public static final String DATE_YEAR_FORMAT = "yyyy";

    // 特殊格式：年-周
    public static final String DATE_YEAR_WEEK = "yyyy-ww";

    // 特殊格式：年-季
    public static final String DATE_YEAR_QUARTER = "yyyy-qq";

    // 默认日期格式(中文)
    public static final String DATE_DEFAULT_FORMAT_ZH = "yyyy年MM月dd日";

    // 日期格式化
    private static DateFormat dateFormat = null;

    // 时间格式化
    private static DateFormat dateTimeFormat = null;

    private static DateFormat timeFormat = null;

    static {
        dateFormat = new SimpleDateFormat(DATE_DEFAULT_FORMAT);
        dateTimeFormat = new SimpleDateFormat(DATETIME_DEFAULT_FORMAT);
        timeFormat = new SimpleDateFormat(TIME_DEFAULT_FORMAT);
    }

    /**
     * 比较2个时间戳是否为同一天
     *
     * @param thisTime
     * @param thatTime
     * @return
     */
    public static boolean isTheSameDay(long thisTime, long thatTime) {
        Date thisDate = new Date(thisTime);
        Date thatDate = new Date(thatTime);
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formatThisDate = sd.format(thisDate);
        String formatThatDate = sd.format(thatDate);
        return formatThisDate.equals(formatThatDate);
    }

    /**
     * 日期格式化yyyy-MM-dd
     *
     * @param date
     * @return
     */
    public static Date formatDate(String date, String format) {
        try {
            return new SimpleDateFormat(format).parse(date);
        } catch (ParseException e) {
            log.error("[DateUtil 异常] formatDate异常,日期:{},格式化:{}", date, format, e);
        }
        return null;
    }

    /**
     * 日期格式化指定
     *
     * @param date
     * @return
     */
    public static String formatDateToString(Date date, String format) {
        return new SimpleDateFormat(format).format(date);
    }

    /**
     * 日期格式化yyyy-MM-dd
     *
     * @param date
     * @return
     */
    public static String getDateFormat(Date date) {
        return dateFormat.format(date);
    }

    /**
     * 日期格式化yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return
     */
    public static String getDateTimeFormat(Date date) {
        return dateTimeFormat.format(date);
    }

    /**
     * 时间格式化
     *
     * @param date
     * @return HH:mm:ss
     */
    public static String getTimeFormat(Date date) {
        return timeFormat.format(date);
    }

    /**
     * 日期格式化
     *
     * @param date
     * @param formatStr 格式类型
     * @return
     */
    public static String getDateFormat(Date date, String formatStr) {
        if (StringUtils.isNotEmpty(formatStr)) {
            return new SimpleDateFormat(formatStr).format(date);
        }
        return null;
    }

    /**
     * 日期格式化
     *
     * @param date
     * @return
     */
    public static Date getDateFormat(String date) {
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            log.error("[DateUtil 异常] getDateFormat异常,日期:{}", date, e);
        }
        return null;
    }

    /**
     * 时间格式化
     *
     * @param date
     * @return
     */
    public static Date getDateTimeFormat(String date) {
        try {
            return dateTimeFormat.parse(date);
        } catch (ParseException e) {
            log.error("[DateUtil 异常] getDateTimeFormat异常,日期:{}", date, e);
        }
        return null;
    }

    /**
     * 获取当前日期(yyyy-MM-dd)
     *
     * @param
     * @return
     */
    public static Date getNowDate() {
        return DateUtil.getDateFormat(dateFormat.format(new Date()));
    }

    /**
     * 获取当前日期(yyyy-MM-dd)
     *
     * @param
     * @return
     */
    public static String getNowDateToString() {
        return dateFormat.format(new Date());
    }

    /**
     * 获取当前日期(yyyy-MM-dd)
     *
     * @param
     * @return
     */
    public static String getNowDateTimeToString() {
        return dateTimeFormat.format(new Date());
    }

    /**
     * 获取当前日期星期一日期
     *
     * @return date
     */
    public static Date getFirstDayOfWeek() {
        Calendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setFirstDayOfWeek(Calendar.MONDAY);
        gregorianCalendar.setTime(new Date());
        gregorianCalendar.set(Calendar.DAY_OF_WEEK, gregorianCalendar.getFirstDayOfWeek()); // Monday
        return gregorianCalendar.getTime();
    }


    public static Date getBeginDayOfYesterday() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(getDateStart());
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return cal.getTime();
    }

    public static Date getEndDayOfYesterDay() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(getDayEnd());
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return cal.getTime();
    }

    /**
     * 获取当前日期星期日日期
     *
     * @return date
     */
    public static Date getLastDayOfWeek() {
        Calendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setFirstDayOfWeek(Calendar.MONDAY);
        gregorianCalendar.setTime(new Date());
        gregorianCalendar.set(Calendar.DAY_OF_WEEK, gregorianCalendar.getFirstDayOfWeek() + 6); // Monday
        return gregorianCalendar.getTime();
    }

    /**
     * 获取当前日期星期一日期的开始时间（即星期一的零时零分零秒零毫秒）
     *
     * @return data
     */
    public static Date getFirstDayOfStartTimeOfWeek() {
        Calendar gregorianCalendar = new GregorianCalendar();
        //按照中国人的习惯，一个星期的开始是星期一
        gregorianCalendar.setTime(new Date());
        gregorianCalendar.setFirstDayOfWeek(Calendar.MONDAY);
        gregorianCalendar.set(Calendar.DAY_OF_WEEK, gregorianCalendar.getFirstDayOfWeek());
        gregorianCalendar.set(Calendar.HOUR_OF_DAY, 0);//零时
        gregorianCalendar.set(Calendar.MINUTE, 0);//零分
        gregorianCalendar.set(Calendar.SECOND, 0);//零秒
        gregorianCalendar.set(Calendar.MILLISECOND, 0);//零毫秒
        return gregorianCalendar.getTime();
    }

    /**
     * 获取当前日期星期天的结束时间(即星期天的23点59分59秒999毫秒)
     *
     * @return data
     */
    public static Date getLastDayOfEndTimeOfWeek() {
        Calendar gregorianCalendar = new GregorianCalendar();
        Date data = getFirstDayOfStartTimeOfWeek();
        gregorianCalendar.setTime(data);
        gregorianCalendar.add(Calendar.DATE, 6);//星期一加6
        gregorianCalendar.set(Calendar.HOUR_OF_DAY, 23);//23时
        gregorianCalendar.set(Calendar.MINUTE, 59);//59分
        gregorianCalendar.set(Calendar.SECOND, 59);//59秒
        gregorianCalendar.set(Calendar.MILLISECOND, 999);//999毫秒
        return gregorianCalendar.getTime();
    }

    /**
     * 获取日期星期一日期
     *
     * @param date 指定日期
     * @return date
     */
    public static Date getFirstDayOfWeek(Date date) {
        Calendar gregorianCalendar = new GregorianCalendar();
        if (date == null) {
            return null;
        }
        gregorianCalendar.setFirstDayOfWeek(Calendar.MONDAY);
        gregorianCalendar.setTime(date);
        gregorianCalendar.set(Calendar.DAY_OF_WEEK, gregorianCalendar.getFirstDayOfWeek()); // Monday
        return gregorianCalendar.getTime();
    }

    /**
     * 获取日期星期一日期
     *
     * @param date 指定日期
     * @return date
     */
    public static Date getLastDayOfWeek(Date date) {
        Calendar gregorianCalendar = new GregorianCalendar();
        if (date == null) {
            return null;
        }
        gregorianCalendar.setFirstDayOfWeek(Calendar.MONDAY);
        gregorianCalendar.setTime(date);
        gregorianCalendar.set(Calendar.DAY_OF_WEEK, gregorianCalendar.getFirstDayOfWeek() + 6); // Monday
        return gregorianCalendar.getTime();
    }

    /**
     * 获取本周范围,格式MM-dd - MM-dd
     *
     * @param date 指定日期
     * @return date
     */
    public static String getWeekRange(Date date) {
        if (date == null) {
            return null;
        }
        Calendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setFirstDayOfWeek(Calendar.MONDAY);
        gregorianCalendar.setTime(date);
        gregorianCalendar.set(Calendar.DAY_OF_WEEK, gregorianCalendar.getFirstDayOfWeek() + 6); // Monday
        Date time = gregorianCalendar.getTime();
        String monday = formatDateToString(date, "MM-dd");
        String sunday = formatDateToString(time, "MM-dd");
        return monday + "-" + sunday;
    }

    /**
     * 获取当前月的第一天
     *
     * @return date
     */
    public static Date getFirstDayOfMonth() {
        Calendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(new Date());
        gregorianCalendar.set(Calendar.DAY_OF_MONTH, 1);
        return gregorianCalendar.getTime();
    }

    /**
     * 获取当月第一天
     *
     * @return
     */
    public static String getFirstDayOfMonthStr() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, 0);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        return format.format(cale.getTime());
    }

    /**
     * 获取当月第一天 00:00:00
     *
     * @return java.lang.Long
     */
    public static Long getFirstDayOfMonth2Long() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime().getTime();
    }

    /**
     * 获取当前月的最后一天
     *
     * @return
     */
    public static Date getLastDayOfMonth() {
        Calendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(new Date());
        gregorianCalendar.set(Calendar.DAY_OF_MONTH, 1);
        gregorianCalendar.add(Calendar.MONTH, 1);
        gregorianCalendar.add(Calendar.DAY_OF_MONTH, -1);
        return gregorianCalendar.getTime();
    }

    /**
     * 获取指定月的第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfMonth(Date date) {
        Calendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(date);
        gregorianCalendar.set(Calendar.DAY_OF_MONTH, 1);
        return gregorianCalendar.getTime();
    }

    /**
     * 获取指定月的最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfMonth(Date date) {
        Calendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(date);
        gregorianCalendar.set(Calendar.DAY_OF_MONTH, 1);
        gregorianCalendar.add(Calendar.MONTH, 1);
        gregorianCalendar.add(Calendar.DAY_OF_MONTH, -1);
        return gregorianCalendar.getTime();
    }

    /**
     * 指定年月，获取这个月的开始时间（即这个月的1号的0时0分0秒000毫秒）
     *
     * @return
     */
    public static Date getStartTimeOfYearMonth(int yyyy, int MM) {
        Calendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.set(Calendar.YEAR, yyyy);
        gregorianCalendar.set(Calendar.MONTH, MM - 1);
        gregorianCalendar.set(Calendar.DAY_OF_MONTH, 1);
        gregorianCalendar.set(Calendar.HOUR_OF_DAY, 0);
        gregorianCalendar.set(Calendar.MINUTE, 0);
        gregorianCalendar.set(Calendar.SECOND, 0);
        gregorianCalendar.set(Calendar.MILLISECOND, 0);
        return gregorianCalendar.getTime();
    }

    public static Date getEndTimeOfYearMonth(int yyyy, int MM) {
        Calendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.set(Calendar.YEAR, yyyy);
        gregorianCalendar.set(Calendar.MONTH, MM);
        gregorianCalendar.set(Calendar.DAY_OF_MONTH, 1);
        gregorianCalendar.set(Calendar.HOUR_OF_DAY, 23);
        gregorianCalendar.set(Calendar.MINUTE, 59);
        gregorianCalendar.set(Calendar.SECOND, 59);
        gregorianCalendar.set(Calendar.MILLISECOND, 999);
        gregorianCalendar.add(Calendar.DAY_OF_YEAR, -1);
        return gregorianCalendar.getTime();
    }

    //获取当天的结束时间
    public static Date getDayEnd() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }

    public static Date getEndDayOfTomorrow() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(getDayEnd());
        cal.add(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }

    /**
     * 获取日期前一天
     *
     * @param date
     * @return
     */
    public static Date getDayBefore(Date date) {
        Calendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(date);
        int day = gregorianCalendar.get(Calendar.DATE);
        gregorianCalendar.set(Calendar.DATE, day - 1);
        return gregorianCalendar.getTime();
    }

    /**
     * 获取日期后一天
     *
     * @param date
     * @return
     */
    public static Date getDayAfter(Date date) {
        Calendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(date);
        int day = gregorianCalendar.get(Calendar.DATE);
        gregorianCalendar.set(Calendar.DATE, day + 1);
        return gregorianCalendar.getTime();
    }

    /**
     * 获取当前年
     *
     * @return
     */
    public static int getNowYear() {
        Calendar d = Calendar.getInstance();
        return d.get(Calendar.YEAR);
    }

    /**
     * 获取当前月份
     *
     * @return
     */
    public static int getNowMonth() {
        Calendar d = Calendar.getInstance();
        return d.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取当月天数
     *
     * @return
     */
    public static int getNowMonthDay() {
        Calendar d = Calendar.getInstance();
        return d.getActualMaximum(Calendar.DATE);
    }

    /**
     * 获取时间段的每一天
     *
     * @param startDate 开始日期
     * @param endDate   结算日期
     * @return 日期列表
     */
    public static List<Date> getEveryDay(Date startDate, Date endDate) {
        Calendar gregorianCalendar = new GregorianCalendar();
        if (startDate == null || endDate == null) {
            return null;
        }
        // 格式化日期(yy-MM-dd)
        startDate = DateUtil.getDateFormat(DateUtil.getDateFormat(startDate));
        endDate = DateUtil.getDateFormat(DateUtil.getDateFormat(endDate));
        List<Date> dates = new ArrayList<>();
        gregorianCalendar.setTime(startDate);
        dates.add(gregorianCalendar.getTime());
        while (gregorianCalendar.getTime().compareTo(endDate) < 0) {
            // 加1天
            gregorianCalendar.add(Calendar.DAY_OF_MONTH, 1);
            dates.add(gregorianCalendar.getTime());
        }
        return dates;
    }

    /**
     * 获取时间段的每一天
     *
     * @param startDate 开始日期
     * @param endDate   结算日期
     * @return 日期列表
     */
    public static List<String> getEveryDayStr(Date startDate, Date endDate, int type) {
        Calendar gregorianCalendar = new GregorianCalendar();
        if (startDate == null || endDate == null) {
            return null;
        }

        List<String> dates = new ArrayList<>();

        // 格式化日期(yy-MM-dd)
        if (type == Calendar.DATE) {
            startDate = DateUtil.getDateFormat(DateUtil.getDateFormat(startDate));
            endDate = DateUtil.getDateFormat(DateUtil.getDateFormat(endDate));
        } else if (type == Calendar.MONTH) {
            startDate = DateUtil.formatDate(DateUtil.getDateFormat(startDate, DATE_MONTH_FORMAT), DATE_MONTH_FORMAT);
            endDate = DateUtil.formatDate(DateUtil.getDateFormat(endDate, DATE_MONTH_FORMAT), DATE_MONTH_FORMAT);
        } else {
            startDate = DateUtil.formatDate(DateUtil.getDateFormat(startDate, DATE_YEAR_FORMAT), DATE_YEAR_FORMAT);
            endDate = DateUtil.formatDate(DateUtil.getDateFormat(endDate, DATE_YEAR_FORMAT), DATE_YEAR_FORMAT);
        }
        gregorianCalendar.setTime(startDate);

        // 格式化日期(yy-MM-dd)
        if (type == Calendar.DATE) {
            dates.add(formatDateToString(gregorianCalendar.getTime(), DATE_DEFAULT_FORMAT));
        } else if (type == Calendar.MONTH) {
            dates.add(formatDateToString(gregorianCalendar.getTime(), DATE_MONTH_FORMAT));
        } else {
            dates.add(formatDateToString(gregorianCalendar.getTime(), DATE_YEAR_FORMAT));
        }

        while (gregorianCalendar.getTime().compareTo(endDate) < 0) {
            if (type == Calendar.DATE) {
                gregorianCalendar.add(Calendar.DATE, 1);
                dates.add(formatDateToString(gregorianCalendar.getTime(), DATE_DEFAULT_FORMAT));
            } else if (type == Calendar.MONTH) {
                gregorianCalendar.add(Calendar.MONTH, 1);
                dates.add(formatDateToString(gregorianCalendar.getTime(), DATE_MONTH_FORMAT));
            } else {
                gregorianCalendar.add(Calendar.YEAR, 1);
                dates.add(formatDateToString(gregorianCalendar.getTime(), DATE_YEAR_FORMAT));
            }
        }
        return dates;
    }

    /**
     * 获取提前多少个月
     *
     * @param monty
     * @return
     */
    public static Date getFirstMonth(int monty) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -monty);
        return c.getTime();
    }

    /**
     * 加一个月
     *
     * @param date
     * @return
     */
    public static Date addMonth(Date date, int count) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, count);
        return calendar.getTime();
    }

    /**
     * 加一天
     *
     * @param date
     * @return
     */
    public static Date getAddDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);
        try {
            return new SimpleDateFormat().parse(new SimpleDateFormat().format(calendar.getTime()));
        } catch (ParseException e) {
            log.error("[DateUtil 异常] getAddDay异常,日期:{}", date, e);
            return date;
        }
    }

    /**
     * 加 i 个小时
     *
     * @param date
     * @return
     */
    public static Date getAddHour(Date date, int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, 1 * i);
        try {
            return new SimpleDateFormat().parse(new SimpleDateFormat().format(calendar.getTime()));
        } catch (ParseException e) {
            log.error("[DateUtil 异常] getAddDay异常,日期:{}", date, e);
            return date;
        }
    }

    /**
     * 加一年
     *
     * @param date
     * @return
     */
    public static Date addYear(Date date, int count) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, count);
        return calendar.getTime();
    }

    /**
     * 加一天
     *
     * @param date
     * @return
     */
    public static Date getAddDay(Date date, int count) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, count);
        try {
            return new SimpleDateFormat().parse(new SimpleDateFormat().format(calendar.getTime()));
        } catch (ParseException e) {
            log.error("[DateUtil 异常] getAddDay异常,日期:{},增加天数:{}", date, count, e);
            return date;
        }
    }

    /**
     * 加day天
     *
     * @param date
     * @return
     */
    public static Date getAddDayDate(Date date, int num, int type) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(type, num);
        return calendar.getTime();
    }

    /**
     * 判断传入的时间是否属于当前系统时间的星期内的
     *
     * @param date
     * @return
     */
    public static boolean isWithinThisWeek(Date date) {
        Date startTimeOfWeek = getFirstDayOfStartTimeOfWeek();
        Date endTimeOfWeek = getLastDayOfEndTimeOfWeek();
        long time = date.getTime();
        return startTimeOfWeek.getTime() <= time && endTimeOfWeek.getTime() >= time;
    }

    /**
     * 判断传入的时间是否属于当前系统时间的月
     *
     * @param date
     * @return
     */
    public static boolean isWithinThisMonth(Date date) {
        Date startTimeOfWeek = getFirstDayOfMonth();
        Date endTimeOfWeek = getLastDayOfMonth();
        long time = date.getTime();
        return startTimeOfWeek.getTime() <= time && endTimeOfWeek.getTime() >= time;
    }

    /**
     * 获取当天的开始时间
     *
     * @return
     */
    public static Date getDateStart() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }


    /**
     * 获取当天的开始时间
     *
     * @return
     */
    public static Date getDateStart(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }


    /**
     * 获取当天的结束时间
     *
     * @return
     */
    public static Date getDateEnd(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    /**
     * 获取当天的结束时间
     *
     * @return
     */
    public static Date getDateEnd() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    /**
     * 获取上周的开始时间
     *
     * @return
     */
    @SuppressWarnings("unused")
    public static Date getBeginDayOfLastWeek(Date date) {
        //Date date = new Date();
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayofweek == 1) {
            dayofweek += 7;
        }
        cal.add(Calendar.DATE, 2 - dayofweek - 7);
        return cal.getTime();
    }

    /**
     * 获取上周的结束时间
     *
     * @return
     */
    public static Date getEndDayOfLastWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getBeginDayOfLastWeek(date));
        cal.add(Calendar.DAY_OF_WEEK, 6);
        return cal.getTime();
    }

    /**
     * 获取你设置的相对于现在的时间
     *
     * @param addDay
     * @param addHour
     * @param addSecond
     * @return
     */
    public static Date getDateRelativeNow(Integer addDay, Integer addHour, Integer addMin, Integer addSecond) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, addDay == null ? 0 : addDay.intValue());
        calendar.add(Calendar.HOUR, addHour == null ? 0 : addHour.intValue());
        calendar.add(Calendar.HOUR, addMin == null ? 0 : addMin.intValue());
        calendar.add(Calendar.SECOND, addSecond == null ? 0 : addSecond.intValue());
        return calendar.getTime();
    }

    public static Date getLastYear(Date edate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.YEAR, -1);
        return calendar.getTime();
    }

    public static List<String> getDays(String startTime, String endTime) {

        // 返回的日期集合
        List<String> days = new ArrayList<String>();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date start = dateFormat.parse(startTime);
            Date end = dateFormat.parse(endTime);

            Calendar tempStart = Calendar.getInstance();
            tempStart.setTime(start);

            Calendar tempEnd = Calendar.getInstance();
            tempEnd.setTime(end);
            tempEnd.add(Calendar.DATE, +1);// 日期加1(包含结束)
            while (tempStart.before(tempEnd)) {
                days.add(dateFormat.format(tempStart.getTime()));
                tempStart.add(Calendar.DAY_OF_YEAR, 1);
            }

        } catch (ParseException e) {
            log.error("[DateUtil 异常] getDays异常,开始日期:{},结束日期:{}", startTime, endTime, e);
        }

        return days;
    }

    public static String formatByYYYYMMddHHmmss(Date now) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(PAY_FORMAT);
        return simpleDateFormat.format(now);
    }

    public static Date addMinutes(int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, i);
        try {
            return new SimpleDateFormat().parse(new SimpleDateFormat().format(calendar.getTime()));
        } catch (ParseException e) {
            log.error("[DateUtil 异常] addMinutes异常,分钟:{}", i, e);
            return new Date();
        }
    }

    public static Date addMinutes(Date date, int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, i);
        try {
            return new SimpleDateFormat().parse(new SimpleDateFormat().format(calendar.getTime()));
        } catch (ParseException e) {
            log.error("[DateUtil 异常] addMinutes异常,日期:{},分钟:{}", date, i, e);
            return new Date();
        }
    }

    /**
     * date2比date1多的天数
     *
     * @param date1 小的时间
     * @param date2 大的时间
     * @return
     */
    public static int differentDays(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if (year1 != year2)   //同一年
        {
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0)    //闰年
                {
                    timeDistance += 366;
                } else    //不是闰年
                {
                    timeDistance += 365;
                }
            }

            return timeDistance + (day2 - day1);
        } else    //不同年
        {
            System.out.println("判断day2 - day1 : " + (day2 - day1));
            return day2 - day1;
        }
    }

    public static Date getStartDate(String startDate) {
        try {
            return dateTimeFormat.parse(startDate + " 00:00:00");
        } catch (Exception e) {
            return null;
        }
    }

    public static Date getEndDate(String endDate) {
        try {
            return dateTimeFormat.parse(endDate + " 23:59:59");
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 获取当前月的第一天
     *
     * @return
     */
    public static Date getMonthFirstDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.MONTH, 0);

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }


    /**
     * 获取当前月的最后一天
     *
     * @return
     */
    public static Date getMonthLastDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        calendar.add(Calendar.MONTH, 1);

        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    /**
     * 获取当前季度
     */
    public static long getQuarterStartDate() {
        Calendar c = Calendar.getInstance();
        int month = c.get(Calendar.MONTH) + 1;
        int quarter = 0;
        if (month >= 1 && month <= 3) {
            quarter = 1;
        } else if (month >= 4 && month <= 6) {
            quarter = 2;
        } else if (month >= 7 && month <= 9) {
            quarter = 3;
        } else {
            quarter = 4;
        }
        try {
            return dateFormat.parse(getCurrQuarter(quarter)[0]).getTime();
        } catch (Exception e) {
            return 0;
        }
    }


    /**
     * 获取当前季度
     */
    public static long getQuarterEndDate() {
        Calendar c = Calendar.getInstance();
        int month = c.get(Calendar.MONTH) + 1;
        int quarter = 0;
        if (month >= 1 && month <= 3) {
            quarter = 1;
        } else if (month >= 4 && month <= 6) {
            quarter = 2;
        } else if (month >= 7 && month <= 9) {
            quarter = 3;
        } else {
            quarter = 4;
        }
        try {
            return dateFormat.parse(getCurrQuarter(quarter)[1]).getTime();
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 获取某季度的第一天和最后一天
     *
     * @param num 第几季度
     */
    public static String[] getCurrQuarter(int num) {
        String[] s = new String[2];
        String str = "";
        // 设置本年的季
        Calendar quarterCalendar = null;
        switch (num) {
            case 1: // 本年到现在经过了一个季度，在加上前4个季度
                quarterCalendar = Calendar.getInstance();
                quarterCalendar.set(Calendar.MONTH, 3);
                quarterCalendar.set(Calendar.DATE, 1);
                quarterCalendar.add(Calendar.DATE, -1);
                str = DateUtils.formatDate(quarterCalendar.getTime(), "yyyy-MM-dd");
                s[0] = str.substring(0, str.length() - 5) + "01-01";
                s[1] = str;
                break;
            case 2: // 本年到现在经过了二个季度，在加上前三个季度
                quarterCalendar = Calendar.getInstance();
                quarterCalendar.set(Calendar.MONTH, 6);
                quarterCalendar.set(Calendar.DATE, 1);
                quarterCalendar.add(Calendar.DATE, -1);
                str = DateUtils.formatDate(quarterCalendar.getTime(), "yyyy-MM-dd");
                s[0] = str.substring(0, str.length() - 5) + "04-01";
                s[1] = str;
                break;
            case 3:// 本年到现在经过了三个季度，在加上前二个季度
                quarterCalendar = Calendar.getInstance();
                quarterCalendar.set(Calendar.MONTH, 9);
                quarterCalendar.set(Calendar.DATE, 1);
                quarterCalendar.add(Calendar.DATE, -1);
                str = DateUtils.formatDate(quarterCalendar.getTime(), "yyyy-MM-dd");
                s[0] = str.substring(0, str.length() - 5) + "07-01";
                s[1] = str;
                break;
            case 4:// 本年到现在经过了四个季度，在加上前一个季度
                quarterCalendar = Calendar.getInstance();
                str = DateUtils.formatDate(quarterCalendar.getTime(), "yyyy-MM-dd");
                s[0] = str.substring(0, str.length() - 5) + "10-01";
                s[1] = str.substring(0, str.length() - 5) + "12-31";
                break;
        }
        return s;
    }

    public static Map getWeekList(int num) {
        Map<String, Object> map = new HashMap<>(16);
        SimpleDateFormat dateFormatYear = new SimpleDateFormat("yyyy");
        List<String> list = new ArrayList<>(32);
        String week = "";
        Date date = new Date();
        for (int i = 0; i < num; i++) {
            SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

            dateFormatter.applyPattern("w");
            week = dateFormatYear.format(date) + "-" + dateFormatter.format(date);
            list.add(week);

            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            int day = cal.get(Calendar.DAY_OF_WEEK);
            cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day - 1);
            date = cal.getTime();

        }

        map.put("weekList", list);
        map.put("startTime", operDate(date, 1).getTime());
        return map;
    }

    /**
     * 指定天，格式：年-周
     *
     * @param date
     * @return
     */
    public static String getYearWeek(Date date) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dateFormatYear = new SimpleDateFormat("yyyy");
        String yearWeek = "";
        dateFormatter.applyPattern("w");
        yearWeek = dateFormatYear.format(date) + "-" + dateFormatter.format(date);
        return yearWeek;
    }

    /**
     * 指定天数加减日期
     *
     * @param date
     * @return
     */
    public static Date operDate(Date date, int num) {
        Calendar calendar = Calendar.getInstance();//日历对象
        calendar.setTime(date);//设置当前日期
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        calendar.add(Calendar.DAY_OF_MONTH, num);//加、减
        return calendar.getTime();
    }

    /**
     * 获取当前周的第一天
     *
     * @param date
     * @return
     */
    public static Date getStartTimeInterval(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int day = cal.get(Calendar.DAY_OF_WEEK);
        cal.add(Calendar.DAY_OF_MONTH, cal.getFirstDayOfWeek() - day);
        return cal.getTime();
    }

    /**
     * 获取当前周的最后一天
     *
     * @param date
     * @return
     */
    public static Date getEndTimeInterval(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int day = cal.get(Calendar.DAY_OF_WEEK);
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        cal.add(Calendar.DATE, 6);
        return cal.getTime();
    }

    /**
     * 年-季度
     *
     * @param num
     * @return
     */
    public static Map getQuarterList(int num) throws ParseException {
        Map<String, Object> map = new HashMap<>(16);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        List<String> list = new ArrayList<>(32);
        SimpleDateFormat dateFormatYear = new SimpleDateFormat("yyyy");
        Date date = new Date();
        String quarter = "";
        for (int i = 0; i < num; i++) {
            quarter = getQuarterByDate(date);
            list.add(quarter);

            date = getTimeSubMonth(getQuarterFirstDate(date), -1);
        }
        System.out.println(sdf.format(getTimeSubMonth(date, 1)));
        map.put("quarterList", list);
        map.put("startTime", getTimeSubMonth(date, 1).getTime());
        return map;
    }

    /**
     * 指定日期 格式：年-季度
     *
     * @param datePar
     * @return
     */
    public static String getQuarterByDate(Date datePar) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(datePar);
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        int mouth = datePar.getMonth() + 1;
        if (mouth >= 1 && mouth <= 3) {
            return year + "-" + "01";
        } else if (mouth >= 4 && mouth <= 6) {
            return year + "-" + "02";
        } else if (mouth >= 7 && mouth <= 9) {
            return year + "-" + "03";
        } else if (mouth >= 10 && mouth <= 12) {
            return year + "-" + "04";
        } else {
            return "";
        }
    }

    /**
     * 根据日期获取当前季度第一天
     *
     * @param datePar
     * @return
     */
    public static Date getQuarterFirstDate(Date datePar) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(datePar);
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        int mouth = datePar.getMonth() + 1;
        if (mouth >= 1 && mouth <= 3) {
            return sdf.parse(year + "-01-01");
        } else if (mouth >= 4 && mouth <= 6) {
            return sdf.parse(year + "-04-01");
        } else if (mouth >= 7 && mouth <= 9) {
            return sdf.parse(year + "-07-01");
        } else if (mouth >= 10 && mouth <= 12) {
            return sdf.parse(year + "-10-01");
        } else {
            return new Date();
        }
    }

    /**
     * 指定日期减去月份数
     *
     * @param num
     * @return
     */
    public static Date getTimeSubMonth(Date date, int num) {
        //处理日期 直接复制就可以用
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        //计算前一个月的日期
        calendar.add(Calendar.MONTH, num);
        date = calendar.getTime();
        //计算后日期startTime 为 "2019-12-01 00:00:00"
        return date;
    }

    /**
     * 注意day 必须是long类型 否者会超精度影响结果
     *
     * @param date
     * @param day
     * @return java.util.Date
     */
    public static Date addDate(Date date, long day) {
        long time = date.getTime(); // 得到指定日期的毫秒数
        day = day * 24 * 60 * 60 * 1000; // 要加上的天数转换成毫秒数
        time += day; // 相加得到新的毫秒数
        return new Date(time); // 将毫秒数转换成日期
    }

    /**
     * 校验两个时间前后
     *
     * @param dt1
     * @param dt2
     * @return
     */
    public static int compareDate(Date dt1, Date dt2) {
        if (dt1 == null || dt2 == null) {
            return 0;
        }
        try {
            if (dt1.getTime() > dt2.getTime()) {
                return 0;
            } else if (dt1.getTime() <= dt2.getTime()) {
                return 1;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    /**
     * 当天开始时间
     *
     * @return
     */
    public static Date initDateBeginDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 当天结束时间
     *
     * @return
     */
    public static Date initDateEndDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }



    public static int getCalendarEnum(int dateType) {
        if (DateType.DAY_RANGE.getCode() == dateType) {
            return Calendar.DAY_OF_MONTH;
        }
        if (DateType.MONTH_RANGE.getCode() == dateType) {
            return Calendar.MONTH;
        }
        if (DateType.YEAR_RANGE.getCode() == dateType) {
            return Calendar.YEAR;
        }
        return Calendar.DAY_OF_MONTH;
    }

    public static List<Date> getEveryMonthOnYear(Date rangeDateStart, Date rangeDateEnd) {
        List<Date> date = new ArrayList<>();
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-01");
            Date d1 = simpleDateFormat.parse(simpleDateFormat.format(rangeDateStart));//定义起始日期
            Date d2 = simpleDateFormat.parse(simpleDateFormat.format(rangeDateEnd));//定义结束日期
            Calendar dd = Calendar.getInstance();//定义日期实例
            dd.setTime(d1);//设置日期起始时间
            while (dd.getTime().before(d2)) {//判断是否到结束日期
                date.add(dd.getTime());
                dd.add(Calendar.MONTH, 1);//进行当前日期月份加1
            }
            date.add(d2);
        } catch (Exception e) {
        }
        return date;
    }


    @Getter
    @AllArgsConstructor
    public enum DateType {
        //范围单位天
        DAY_RANGE(0),

        //范围单月
        MONTH_RANGE(1),

        //范围年
        YEAR_RANGE(2),
        ;

        private final int code;
    }

    /**
     * 两个时间相差月份数
     *
     * @param d1
     * @param d2
     * @return
     */
    public static int getMonthDiff(Date d1, Date d2) {

        Calendar c1 = Calendar.getInstance();

        Calendar c2 = Calendar.getInstance();

        c1.setTime(d1);

        c2.setTime(d2);

        int year1 = c1.get(Calendar.YEAR);

        int year2 = c2.get(Calendar.YEAR);

        int month1 = c1.get(Calendar.MONTH);

        int month2 = c2.get(Calendar.MONTH);

        int day1 = c1.get(Calendar.DAY_OF_MONTH);

        int day2 = c2.get(Calendar.DAY_OF_MONTH);

        // 获取年的差值 

        int yearInterval = year1 - year2;

        // 如果 d1的 月-日 小于 d2的 月-日 那么 yearInterval-- 这样就得到了相差的年数

        if (month1 < month2 || month1 == month2 && day1 < day2) {
            yearInterval--;
        }

        // 获取月数差值

        int monthInterval = (month1 + 12) - month2;

        if (day1 < day2) {
            monthInterval--;
        }

        monthInterval %= 12;

        int monthsDiff = Math.abs(yearInterval * 12 + monthInterval);

        return monthsDiff;

    }

    /**
     * 今天剩余分钟数
     *
     * @return
     */
    public static long minutesLeftToday() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime beginOfNextDay = now.plusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
        return ChronoUnit.MINUTES.between(now, beginOfNextDay);
    }

    /**
     * LocalDate转Date
     * @param localDate
     * @return
     */
    public static Date asDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * LocalDateTime转Date
     * @param localDateTime
     * @return
     */
    public static Date asDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * Date转LocalDate
     * @param date
     * @return
     */
    public static LocalDate asLocalDate(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * Date转LocalDateTime
     * @param date
     * @return
     */
    public static LocalDateTime asLocalDateTime(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * 获取当前时间与end之间的相差月份
     * @param end
     * @return
     */
    public static long getMonthBetweenDate(LocalDate end){
        return getMonthBetweenDate(LocalDate.now(),end);
    }
    /**
     * 获取俩个时间相差的月份
     * @param begin
     * @param end
     * @return
     */
    public static long getMonthBetweenDate(Date begin,Date end){
        return getMonthBetweenDate(asLocalDate(begin),asLocalDate(end));
    }

    /**
     * 获取俩个时间相差的月份
     * @param begin
     * @param end
     * @return
     */
    public static long getMonthBetweenDate(LocalDate begin, LocalDate end){
        if(end.isBefore(begin)){
            return 0;
        }
        long years = end.getYear() - begin.getYear();
        long months = years * 12 + (end.getMonthValue() - begin.getMonthValue());
        return Math.abs(months);
    }


}