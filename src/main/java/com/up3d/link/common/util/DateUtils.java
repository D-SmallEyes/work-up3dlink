package com.up3d.link.common.util;

import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

/**
 * 时间工具类
 */
public class DateUtils {

    /** 系统默认 日期类型 yyyy-MM-dd */
    public static final String DATE_PATTERN_DEFAULT = "yyyy-MM-dd";

    /** 系统默认 日期类型 yyyyMMdd */
    public static final String DATE_PATTERN = "yyyyMMdd";

    /** 时间 日期类型 HH:mm:ss */
    public static final String DATE_PATTERN_TIME = "HH:mm:ss";

    /** 日期时间 日期类型 yyyy-MM-dd HH:mm:ss */
    public static final String DATE_PATTERN_DATETIME = "yyyy-MM-dd HH:mm:ss";

    /** 日期时间 日期类型 yyyy-MM-dd HH:mm:ss */
    public static final String DATE_PATTERN_M = "yyyy/MM/dd HH:mm:ss";

    /** 时期格式 yyyy-MM-dd */
    public static DateFormat dateformater;

    /** 时间格式 HH:mm:ss */
    public static DateFormat timeformater;

    /** 日期时间格式 yyyy-MM-dd HH:mm */
    public static DateFormat dateTimeformater;

    static {
        if (DateUtils.dateformater == null) {
            DateUtils.dateformater = new SimpleDateFormat(DateUtils.DATE_PATTERN_DEFAULT);
        }
        if (DateUtils.timeformater == null) {
            DateUtils.timeformater = new SimpleDateFormat(DateUtils.DATE_PATTERN_TIME);
        }

        if (DateUtils.dateTimeformater == null) {
            DateUtils.dateTimeformater = new SimpleDateFormat(DateUtils.DATE_PATTERN_DATETIME);
        }
    }

    /** 一天毫秒数 */
    public static final long DAY_IN_MILLISECOND = 1000 * 3600 * 24;

    /** 一小时毫秒数 */
    public static final long HOUR_IN_MILLISECOND = 1000 * 3600;

    /** 构造方法私有化 */
    private DateUtils() {
    }

    /**
     * 得到现在时间
     *
     * @return 字符串 yyyyMMdd HHmmss
     */
    public static String getStringToday() {
        final Date currentTime = new Date();
        final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final String dateString = formatter.format(currentTime);
        return dateString;
    }

    public static String getTimestamp() {
        final Date currentTime = new Date();
        final SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        final String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     *
     * 根据 yyyy-MM-dd 字符串解析成相应的日期
     *
     * @param strDate
     *            yyyy-MM-dd 格式的日期
     *
     * @return 转换后的日期
     *
     */
    public static Date parseDate(String strDate) {
        Date date = null;
        if (StringUtils.isNotBlank(strDate)) {
            try {
                date = DateUtils.dateformater.parse(strDate);
            }
            catch (final Exception e) {
                e.printStackTrace();
                return date;
            }
        }
        return date;
    }

    /**
     *
     * 根据传入的日期格式 将字符串解析成 日期类型
     *
     * @param strDate
     *            日期字符串
     *
     * @param pattern
     *            日期格式 如果传入格式为空，则为默认格式 yyyy-MM-dd
     *
     * @return 日期类型
     *
     */
    public static Date parseDate(String strDate, String pattern) {
        Date date = null;
        try {
            final DateFormat format = DateUtils.getDateFormat(pattern);
            date = format.parse(strDate);
        }
        catch (final Exception e) {
            e.printStackTrace();
            return date;
        }
        return date;
    }

    /**
     *
     * 根据 HH:mm:ss 字符串解析成相应的时间格式
     *
     * @param strTime
     *            HH:mm:ss 格式的时间格式
     *
     * @return 转换后的时间
     *
     */
    public static Date parseTime(String strTime) {
        Date date = null;
        try {
            date = DateUtils.timeformater.parse(strTime);
        }
        catch (final Exception e) {
            e.printStackTrace();
            return date;
        }
        return date;
    }

    /**
     *
     * 根据yyyy-MM-dd HH:mm字符串解析成相应的日期时间
     * @param strDateTime 以"yyyy-MM-dd HH:mm:ss"为格式的时间字符串
     * @return 转换后的日期
     *
     */
    public static Date parseDateTime(String strDateTime) {
        Date date = new Date();
        try {
            date = DateUtils.dateTimeformater.parse(strDateTime);
        }
        catch (final Exception e) {
            e.printStackTrace();
            return date;
        }
        return date;
    }

    /**
     *
     * 获取系统当前时间
     *
     * @return 系统当前时间
     *
     */
    public static Date getCurrentDate() {
        final Calendar gc = Calendar.getInstance();
        return gc.getTime();
    }

    /**
     *
     * 得到日期的起始日期，例如2004-1-1 15:12，转换后为 2004-1-1 00:00
     *
     * @param date
     *            需要转换的日期
     *
     * @return 该日期的零点
     *
     */
    public static Date getTodayStart(Date date) {
        final Calendar gc = Calendar.getInstance();
        gc.setTime(date);
        gc.set(Calendar.HOUR_OF_DAY, 0);
        gc.set(Calendar.MINUTE, 0);
        gc.set(Calendar.SECOND, 0);
        gc.set(Calendar.MILLISECOND, 0);
        return gc.getTime();
    }

    /**
     *
     * 得到日期的结束日期，例如2004-1-1 15:12，转换后为2004-1-2 00:00，注意为第二天的0点整
     *
     * @param date
     *            所要转换的日期
     *
     * @return 为第二天的零点整
     *
     */
    public static Date getTodayEnd(Date date) {
        final Calendar gc = Calendar.getInstance();
        gc.setTime(DateUtils.dateDayAdd(date, 1));
        return DateUtils.getTodayStart(gc.getTime());
    }

    /**
     *
     * 得到日期所在月份的开始日期（第一天的开始日期），例如2004-1-15 15:10，转换后为2004-1-1 00:00
     *
     * @param date
     *            需要转换的日期
     *
     * @return 日期所在月份的开始日期
     *
     */
    public static Date getMonthBegin(Date date) {
        final Calendar gc = Calendar.getInstance();
        gc.setTime(date);
        final int year = gc.get(Calendar.YEAR);
        final int mon = gc.get(Calendar.MONTH);
        final Calendar gCal = new GregorianCalendar(year, mon, 1);
        return gCal.getTime();
    }

    /**
     *
     * 根据年、月返回由年、月构成的日期的月份开始日期
     *
     * @param year
     *            所在的年
     *
     * @param month
     *            所在的月份，从1月到12月
     *
     * @return 由年、月构成的日期的月份开始日期
     *
     */
    public static Date getMonthBegin(int year, int month) {
        if ((month <= 0) || (month > 12)) {
            throw new IllegalArgumentException("month must from 1 to 12");
        }
        final Calendar gc = new GregorianCalendar(year, month - 1, 1);
        return gc.getTime();
    }

    /**
     *
     * 根据日期所在的月份，得到下个月的第一天零点整
     *
     * @param date
     *            需要转换的日期
     *
     * @return 对应日期的下个月的第一天零点整
     *
     */
    public static Date getMonthEnd(Date date) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        final int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        return DateUtils.getTodayEnd(cal.getTime());
    }

    /**
     *
     * 根据日期所在的星期，得到这个星期的开始日期，注意，每周从星期日开始计算
     *
     * @param date
     *            需要转换的日期
     *
     * @return 传入日期所在周的第一天的零点整
     *
     */
    public static Date getWeekBegin(Date date) {
        final Calendar gCal = Calendar.getInstance();
        gCal.setTime(date);
        final int startDay = gCal.getActualMinimum(Calendar.DAY_OF_WEEK);
        gCal.set(Calendar.DAY_OF_WEEK, startDay);
        return gCal.getTime();
    }

    /**
     *
     * 根据日期所在的星期，得到下周开始第一天的零点整
     *
     * @param date
     *            需要转换的日期
     *
     * @return 传入日期的下周开始第一天的零点整
     *
     */
    public static Date getWeekEnd(Date date) {
        final Calendar gCal = Calendar.getInstance();
        gCal.setTime(date);
        final int lastDay = gCal.getActualMaximum(Calendar.DAY_OF_WEEK);
        gCal.set(Calendar.DAY_OF_WEEK, lastDay);
        return DateUtils.getTodayEnd(gCal.getTime());
    }

    /**
     *
     * 根据年、月返回由年、月构成的日期的下一个月第一天零点整
     *
     * @param year
     *            所在的年
     *
     * @param month
     *            所在的月份，从1月到12月
     *
     * @return 下一个月第一天零点整
     *
     */
    public static Date getMonthEnd(int year, int month) {
        final Date start = DateUtils.getMonthBegin(year, month);
        return DateUtils.getMonthEnd(start);
    }

    /**
     *
     * 根据日期所在的年份，得到当年的开始日期，为每年的1月1日零点整
     *
     * @param date
     *            需要转换的日期
     *
     * @return 当年的开始日期，为每年的1月1日零点整
     *
     */
    public static Date getYearBegin(Date date) {
        final Calendar gCal = Calendar.getInstance();
        gCal.setTime(date);
        gCal.set(Calendar.DAY_OF_YEAR, 1);
        return DateUtils.getTodayStart(gCal.getTime());
    }

    /**
     *
     * 根据日期所在的年份，得到当年的结束日期，为来年的1月1日零点整
     *
     * @param date
     *            需要转换的日期
     *
     * @return 来年的1月1日零点整
     *
     */
    public static Date getYearEnd(Date date) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        final int lastday = cal.getActualMaximum(Calendar.DAY_OF_YEAR);
        cal.set(Calendar.DAY_OF_YEAR, lastday);
        return DateUtils.getTodayEnd(cal.getTime());
    }

    /**
     *
     * 转换日期为 yyyy-MM-dd 格式的字符串
     *
     * @param date
     *            需要转换的日期
     *
     * @return 转换后的日期字符串
     *
     */
    public static String formatDate(Date date) {
        String str = "";
        if (date != null) {
            str = DateUtils.dateformater.format(date);
        }
        return str;

    }

    /**
     *
     * 转换指定日期成时间格式 HH:mm:ss 格式的字符串
     *
     * @param date
     *            指定的时间
     *
     * @return 转换后的字符串
     *
     */
    public static String formatTime(Date date) {
        return DateUtils.timeformater.format(date);
    }

    /**
     *
     * 转换指定日期成 yyyy-MM-dd HH:mm:ss 格式的字符串
     *
     * @param date
     *            需要转换的日期
     *
     * @return 转换后的字符串
     *
     */
    public static String formatDateTime(Date date) {
        return DateUtils.dateTimeformater.format(date);
    }

    /**
     *
     * 根据指定的转化模式，转换日期成字符串
     *
     * @param date
     *            需要转换的日期
     *
     * @param pattern
     *            日期格式 如果传入格式为空，则为默认格式 yyyy-MM-dd
     *
     * @return 转换后的字符串
     *
     */
    public static String formatDate(Date date, String pattern) {
        final DateFormat formater = DateUtils.getDateFormat(pattern);
        return formater.format(date);
    }

    /**
     *
     * 在指定日期的基础上，增加或是减少月份信息，如1月31日，增加一个月后，则为2月28日（非闰年）
     *
     * @param date
     *            指定的日期
     *
     * @param months
     *            增加或是减少的月份数，正数为增加，负数为减少
     *
     * @return 增加或是减少后的日期
     *
     */
    public static Date dateMonthAdd(Date date, int months) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int m = cal.get(Calendar.MONTH) + months;
        if (m < 0) {
            m += -12;
        }
        cal.roll(Calendar.YEAR, m / 12);
        cal.roll(Calendar.MONTH, months);
        return cal.getTime();
    }

    /**
     *
     * 在指定的日期基础上，增加或是减少天数
     *
     * @param date
     *            指定的日期
     *
     * @param days
     *            需要增加或是减少的天数，正数为增加，负数为减少
     *
     * @return 增加或是减少后的日期
     *
     */
    public static Date dateDayAdd(Date date, int days) {
        final long now = date.getTime() + (days * DateUtils.DAY_IN_MILLISECOND);
        return new Date(now);
    }

    public static Date dateDayAddHours(Date date, int hours) {
        final long now = date.getTime() + (hours * DateUtils.HOUR_IN_MILLISECOND);
        return new Date(now);
    }

    /**
     *
     * 在指定的日期基础上，增加或是减少年数
     *
     * @param date
     *            指定的日期
     *
     * @param year
     *            需要增加或是减少的年数，正数为增加，负数为减少
     *
     * @return 增加或是减少后的日期
     *
     */
    public static Date dateYearAdd(Date date, int year) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.roll(Calendar.YEAR, year);
        return cal.getTime();
    }

    /**
     *
     * 得到日期的年数
     *
     * @param date
     *            指定的日期
     *
     * @return 返回指定日期的年数
     *
     */
    public static int getDateYear(Date date) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    /**
     *
     * 得到指定日期的月份数
     *
     * @param date
     *            指定的日期
     *
     * @return 返回指定日期的月份数
     *
     */
    public static int getDateMonth(Date date) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONDAY);
    }

    /**
     *
     * 得到制定日期在当前天数，例如2004-5-20日，返回141
     *
     * @param date
     *            指定的日期
     *
     * @return 返回的天数
     *
     */
    public static int getDateYearDay(Date date) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_YEAR);
    }

    /**
     *
     * 得到制定日期在当前月中的天数，例如2004-5-20日，返回20
     *
     * @param date
     *            指定的日期
     *
     * @return 返回天数
     *
     */
    public static int getDateMonthDay(Date date) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    /**
     *
     * 得到指定日期的年份
     *
     * @param date
     *            指定的日期
     *
     * @return 日期的年份
     *
     */
    public static int getYear(Date date) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    /**
     *
     * 得到指定日期在当在一年中的月份数，从1开始
     *
     * @param date
     *            指定的日期
     *
     * @return 指定日期在当在一年中的月份数
     *
     */
    public static int getMonth(Date date) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH) + 1;
    }

    /**
     *
     * 得到指定日期在当在一月中的号数，从1开始
     *
     * @param date
     *            指定的日期
     *
     * @return 日期在当在一月中的号数
     *
     */
    public static int getDay(Date date) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    /**
     *
     * 得到指定日期在当前星期中的天数，例如2004-5-20日，返回5，
     *
     * 每周以周日为开始按1计算，所以星期四为5
     *
     * @param date
     *            指定的日期
     *
     * @return 返回天数
     *
     */
    public static int getDateWeekDay(Date date) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK) - 1;
    }

    /**
     *
     * 得到指定日期在当前周内是第几天 (周一开始)
     *
     * @param date
     *            指定日期
     *
     * @return 周内天书
     *
     */
    public static int getWeek(Date date) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return ((cal.get(Calendar.DAY_OF_WEEK) - 1) + 7) % 7;
    }

    /**
     *
     * 根据传入的格式，获取日期格式化实例，如果传入格式为空，则为默认格式 yyyy-MM-dd
     * @param pattern 日期格式
     * @return 格式化实例
     *
     */
    public static DateFormat getDateFormat(String pattern) {
        if (StringUtils.isBlank(pattern.trim())) {
            return new SimpleDateFormat(DateUtils.DATE_PATTERN_DEFAULT);
        } else {
            return new SimpleDateFormat(pattern);
        }
    }

    /**
     *
     * 计算两个时间之间的时间差
     *
     * @param from
     *            开始
     *
     * @param to
     *            结束
     *
     * @return 时间差
     *
     */
    public static long calculateTimeInMillis(Date from, Date to) {
        final Calendar fromCal = DateUtils.getCalendar(from);
        final Calendar toCal = DateUtils.getCalendar(to);
        if (fromCal.after(toCal)) {
            fromCal.setTime(to);
            toCal.setTime(from);
        }
        return toCal.getTimeInMillis() - fromCal.getTimeInMillis();
    }

    /**
     *
     * 获取Calendar实例
     *
     * @param date
     *            日期类型
     *
     * @return
     *
     */
    public static Calendar getCalendar(Date date) {
        final Calendar gc = Calendar.getInstance();
        gc.setTime(date);
        return gc;
    }

    /**
     *
     * 根据 yyyyMMdd HH:mm 日期格式，转换成数据库使用的时间戳格式
     *
     * @param strTimestamp
     *            以 yyyyMMdd HH:mm 格式的时间字符串
     *
     * @return 转换后的时间戳
     *
     */
    public static java.sql.Timestamp parseTimestamp(String strTimestamp) {
        return new java.sql.Timestamp(DateUtils.parseDateTime(strTimestamp).getTime());
    }

    /**
     *
     * 根据出生时间计算岁数
     * Date birthday
     * @return 年龄
     *
     */
    public static int getAgeByBirth(Date birthday) {
        int age = 0;
        try {
            final Calendar now = Calendar.getInstance();
            now.setTime(new Date());// 当前时间

            final Calendar birth = Calendar.getInstance();
            birth.setTime(birthday);

            if (birth.after(now)) {// 如果传入的时间，在当前时间的后面，返回0岁
                age = 0;
            } else {
                age = now.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
                if (now.get(Calendar.DAY_OF_YEAR) > birth.get(Calendar.DAY_OF_YEAR)) {
                    age += 1;
                }
            }
            return age;
        }
        catch (final Exception e) {// 兼容性更强,异常后返回数据
            return 0;
        }
    }

    /**
     *
     * 根据出生时间计算岁数
     * Date birthday
     * @return 年龄
     *
     */
    public static int getAgeByBirthInt(long birthday) {
        String birthDayString=DateUtils.stampToDateForBirth(birthday);
        Date birthDate=DateUtils.parseDate(birthDayString);
        return getAgeByBirth(birthDate);
    }

    /**
     * 将时间转换为时间戳
     * @param time yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String dateToStamp(String time,String pattern){
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String stamp = "";
        if (!"".equals(time)) {//时间不为空
            try {
                stamp = String.valueOf(sdf.parse(time).getTime()/1000);
            } catch (Exception e) {
                System.out.println("参数为空！");
            }
        }else {    //时间为空
            long current_time = System.currentTimeMillis();  //获取当前时间
            stamp = String.valueOf(current_time/1000);
        }
        return stamp;
    }

    /**
     * 将时间戳转换为时间
     * @param time 时间戳
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String stampToDate(int time,String pattern){
        SimpleDateFormat sdf = new SimpleDateFormat( pattern);
        String time_Date = sdf.format(new Date(time * 1000L));
        return time_Date;

    }

    /**
     * 将时间戳转换为时间
     * @param time 时间戳
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String stampToDate(int time){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time_Date = sdf.format(new Date(time * 1000L));
        return time_Date;
    }


    /**
     * 将时间戳转换为时间
     * @param time 时间戳
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String stampToDateForBirth(long time){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date(time * 1000L));
    }
    /**
     * 获取时间差（到当前时间） 天数 机器使用天数
     * @param time
     * @return
     */
    public static int daysBetween(int time) {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String smdate = stampToDate(time);
        String bdate = stampToDate(nowByStamp());
        try{
            Calendar cal = Calendar.getInstance();
            cal.setTime(sdf.parse(smdate));
            long time1 = cal.getTimeInMillis();
            cal.setTime(sdf.parse(bdate));
            long time2 = cal.getTimeInMillis();
            long between_days=(time2-time1)/(1000*3600*24);
            return Integer.parseInt(String.valueOf(between_days));
        }catch (Exception e){

        }

        return 0;
    }

    /**
     * 计算两个时间差几天
     * @param start
     * @param end
     * @return
     */
    public static int daysBetween(int start,int end) {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String smdate = stampToDate(start);
        String bdate = stampToDate(end);
        try{
            Calendar cal = Calendar.getInstance();
            cal.setTime(sdf.parse(smdate));
            long time1 = cal.getTimeInMillis();
            cal.setTime(sdf.parse(bdate));
            long time2 = cal.getTimeInMillis();
            long between_days=(time2-time1)/(1000*3600*24);
            return Integer.parseInt(String.valueOf(between_days));
        }catch (Exception e){

        }

        return 0;
    }

    /**
     * 获取当前时间 时间戳
     * @return
     */
    public static int nowByStamp(){
        return (int) (System.currentTimeMillis() / 1000);
    }

    /**
     * 计算出生日期
     * @param ageYear 年龄
     * @param ageMonth 过了几个月
     * @param ageDay 几天
     * @return
     */
    public static String getBirthday(int ageYear, int ageMonth, int ageDay) {
         Calendar c = Calendar.getInstance();
         c.add(Calendar.YEAR, -ageYear);
         c.add(Calendar.MONTH, -ageMonth);
         c.add(Calendar.DAY_OF_MONTH, -ageDay);
         return String.format("%tF", c.getTime());
    }

    /**
     * 年龄转出生日期
     * @param age
     * @return
     */
    public static long ageToBirthDay(int age){
        String birthdate = getBirthday(age,0,0);
        long time =  Long.valueOf(dateToStamp(birthdate,DateUtils.DATE_PATTERN_DEFAULT));
        return time;
    }

    /**
     * 两个时间相差距离多少天多少小时多少分多少秒
     * @param starttime 时间参数 1 格式：1990-01-01 12:00:00
     * @param endtime 时间参数 2 格式：2009-01-01 12:00:00
     * @return String 返回值为：xx天xx小时xx分xx秒
     */
    public static String getDistanceTime(String starttime, String endtime) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date one;
        Date two;
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        try {
            one = df.parse(starttime);
            two = df.parse(endtime);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff ;
            if(time1<time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            day = diff / (24 * 60 * 60 * 1000);
            hour = (diff / (60 * 60 * 1000) - day * 24);
            min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
            sec = (diff/1000-day*24*60*60-hour*60*60-min*60);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //return day + "天" + hour + "小时" + min + "分" + sec + "秒";
        return day + "天" + hour + "小时" + min + "分";
    }

    /**
     * 获取精确到秒的时间戳
     * @return
     */
    public static int getSecondTimestamp(Date date){
        if (null == date) {
            return 0;
        }
        String timestamp = String.valueOf(date.getTime());
        int length = timestamp.length();
        if (length > 3) {
            return Integer.valueOf(timestamp.substring(0,length-3));
        } else {
            return 0;
        }
    }

    /**
     * 获取当月所有日期
     * @param year
     * @param month
     * @return
     */
    public static List<String> getMonthFullDay(int year , int month){
        SimpleDateFormat dateFormatYYYYMMDD = new SimpleDateFormat("yyyy-MM-dd");
        List<String> fullDayList = new ArrayList<>();
        // 获得当前日期对象
        Calendar cal = Calendar.getInstance();
        cal.clear();// 清除信息
        cal.set(Calendar.YEAR, year);
        // 1月从0开始
        cal.set(Calendar.MONTH, month-1 );
        // 当月1号
        cal.set(Calendar.DAY_OF_MONTH,1);
        int count = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int j = 1; j <= count ; j++) {
            String dateString=dateFormatYYYYMMDD.format(cal.getTime());
//            String dt=dateToStamp(dateString,"yyyy-mm-dd");
            fullDayList.add(dateString);
            cal.add(Calendar.DAY_OF_MONTH,1);
        }
        return fullDayList;
    }

    public static boolean isToday(Date date,Date today){
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        //获取今天的日期
        String nowDay = sf.format(today);
        //对比的时间
        String day = sf.format(date);
        return day.equals(nowDay);
    }

    /**
     * 获取上个月
     * @param date
     * @return
     */
    public static Date getLastMonthDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, -1);
        return cal.getTime();
    }

    /**
     * 获取昨天
     * @param date
     * @return
     */
    public static Date getLastDayDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }


    public static String getCamName(int time){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String time_Date = sdf.format(new Date(time * 1000L));
        return time_Date;
    }

    public static String getNcName(int time){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String time_Date = sdf.format(new Date(time * 1000L));
        return time_Date;
    }

    /**
     * 获取第二天零点时间戳
     *
     * @return
     */
    public static Integer getExpireTimeStamp() {
        // 获取明天零点时间戳
        LocalDate localDate = LocalDate.now();
        // 当前日期+1
        localDate = localDate.plusDays(1);
        LocalDateTime dateTime = LocalDateTime.of(localDate.getYear(), localDate.getMonth(), localDate.getDayOfMonth(), 0, 0, 0);
        Long time = dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() / 1000;
        Integer timestamp = time.intValue();
        return timestamp;
    }

    /**
     * 获取 time 后 days 的时间戳
     * @param time
     * @param days
     * @return
     */
    public static int addDay(int time,int days){
        int day = 3600 * 24 * days;
        return time+day;
    }

    public static int getDay(int time){
        int day = (time-nowByStamp())/(3600*24);
        return day;
    }

    @Data
    @Builder
    static class DayCompare{
        private int year;
        private int month;
        private int day;
    }
    /**
     * 计算2个日期之间相差的  相差多少年月日
     * 比如：2011-02-02 到  2017-03-02 相差 6年，1个月，0天
     * @param fromDate
     * @param toDate
     * @return
     */
    public static DayCompare dayComparePrecise(Date fromDate,Date toDate){
        Calendar  from  =  Calendar.getInstance();
        from.setTime(fromDate);
        Calendar  to  =  Calendar.getInstance();
        to.setTime(toDate);

        int fromYear = from.get(Calendar.YEAR);
        int fromMonth = from.get(Calendar.MONTH);
        int fromDay = from.get(Calendar.DAY_OF_MONTH);

        int toYear = to.get(Calendar.YEAR);
        int toMonth = to.get(Calendar.MONTH);
        int toDay = to.get(Calendar.DAY_OF_MONTH);
        int year = toYear  -  fromYear;
        int month = toMonth  - fromMonth;
        int day = toDay  - fromDay;
        return DayCompare.builder().day(day).month(month).year(year).build();
    }

    /**
     * 计算2个日期相差多少年
     * 列：2011-02-02  ~  2017-03-02 大约相差 6.1 年
     * @param fromDate
     * @param toDate
     * @return
     */
    public static String yearCompare(Date fromDate,Date toDate){
        DayCompare result = dayComparePrecise(fromDate, toDate);
        double month = result.getMonth();
        double year = result.getYear();

        //返回2位小数，并且四舍五入
        DecimalFormat df = new DecimalFormat("######0");
        return df.format(year + month / 12);
    }

    public static String getWorkYear(Integer time){
        int a = nowByStamp();
        int e = time;
        Date da = parseDate(stampToDate(a,DATE_PATTERN_DEFAULT),DATE_PATTERN_DEFAULT);
        Date de = parseDate(stampToDate(e,DATE_PATTERN_DEFAULT),DATE_PATTERN_DEFAULT);
        return yearCompare(de,da);
    }

    public static void main(String[] args) {
        int a = nowByStamp();
        int e = 1589617079;
        Date da = parseDate(stampToDate(a,DATE_PATTERN_DEFAULT),DATE_PATTERN_DEFAULT);
        Date de = parseDate(stampToDate(e,DATE_PATTERN_DEFAULT),DATE_PATTERN_DEFAULT);
        System.out.println(yearCompare(de,da));
    }

}
