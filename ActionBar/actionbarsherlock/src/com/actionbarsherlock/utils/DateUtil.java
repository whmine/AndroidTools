package com.actionbarsherlock.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.actionbarsherlock.exception.AppException;
import com.actionbarsherlock.exception.SysException;

public class DateUtil {
    public static SimpleDateFormat datetimef = new SimpleDateFormat(
            "yyyy/MM/dd HH:mm:ss");
    public static SimpleDateFormat datetimehm = new SimpleDateFormat(
            "yyyy/MM/dd HH:mm");

    public static SimpleDateFormat datef = new SimpleDateFormat("yyyy/MM/dd");

    /**
     * 按指定的格式将日期对象转换为字符串
     * @param date
     * @param format
     * @return
     */
    public static String to_char(Date date, String format) {
        if (date == null)
            return null;
        DateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    /**
     * 按指定格式将字符串转换为日期对象
     * @param dateStr
     * @param format
     * @return
     * @throws SysException
     */
    public static Date to_date(String dateStr, String format)
            throws SysException {
        if (StringUtil.isBlank(dateStr))
            return null;
        DateFormat df = new SimpleDateFormat(format);
        try {
            return df.parse(dateStr);
        } catch (ParseException e) {
            throw new SysException("", "系统转换日期字符串时出错！", e);
        }
    }

    /**
     * 得到大于传入天数的日期
     * @param date
     * @param num
     * @return java.util.Date
     * @throws AppException
     */
    public static Date addDays(Date date, int num) throws AppException {
        if (null == date)
            throw new AppException("", "缺少参数！");
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(GregorianCalendar.DAY_OF_MONTH, num);
        return cal.getTime();
    }

    /**
     * 当前时间+指定时间间隔（秒为单位）
     * by baijm 2010/11/09
     * @param date
     * @param num
     * @return java.util.Date
     * @throws AppException
     */
    public static Date addSeconds(Date date, int num) throws AppException {
        if (null == date)
            throw new AppException("", "缺少参数！");
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(GregorianCalendar.SECOND, num);
        return cal.getTime();
    }

    /**
     * 获取指定日期的起始时间，如：2007/6/6 00:00:00
     * @param date
     * @return
     * @throws AppException
     */
    public static Date getStartDateTime(Date date) throws AppException {
        if (null == date)
            throw new AppException("", "缺少参数！");
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal.getTime();
    }

    /**
     * 日期（精确到秒）转字符串
     * @param date
     * @return
     */
    public static String dateTime2Str(Date date) {
        String str = "";
        if (null != date) {
            str = DateUtil.to_char(date, "yyyy/MM/dd HH:mm:ss");
        }
        return str;
    }

    /**
     * 日期（精确到日）转字符串
     * @param date
     * @return
     */
    public static String date2Str(Date date) {
        String str = "";
        if (null != date) {
            str = DateUtil.to_char(date, "yyyy/MM/dd");
        }
        return str;
    }

    /**
     * 字符串转日期（精确到秒）
     * @param str
     * @return
     * @throws AppException
     * @throws SysException
     */
    public static Date str2DateTime(String str) throws AppException,
            SysException {
        Date date = null;
        if (!StringUtil.isBlank(str)) {
            date = DateUtil.to_date(str, "yyyy/MM/dd HH:mm:ss");
        }
        return date;

    }

    /**
     * 字符串转日期（精确到分）
     * @param str
     * @return
     * @throws AppException
     * @throws SysException
     */
    public static Date str2DateTimeHM(String str) throws AppException,
            SysException {
        Date date = null;
        if (!StringUtil.isBlank(str)) {
            date = DateUtil.to_date(str, "yyyy/MM/dd HH:mm");
        }
        return date;

    }

    /**
     * 日期（精确到分）转字符串
     * @param date
     * @return
     */
    public static String getDateTimeHMStr(Date date) {
        String str = "";
        if (null != date) {
            str = DateUtil.to_char(date, "yyyy/MM/dd HH:mm");
        }
        return str;
    }

    /**
     * 字符串转日期（精确到日）
     * @param str
     * @return
     * @throws AppException
     * @throws SysException
     */
    public static Date str2Date(String str) throws AppException, SysException {
        Date date = null;
        if (!StringUtil.isBlank(str)) {
            date = DateUtil.to_date(str, "yyyy/MM/dd");
        }
        return date;

    }

    /**
     * 获取一个永久时间
     * @return
     * @throws SysException
     */
    public static Date getForeverDate() throws SysException {
        return to_date("2049/12/31 00:00:00", "yyyy/MM/dd HH:mm:ss");
    }

    /**
     * 获得数据库当前时间
     * @return
     * @throws SysException
     * @throws AppException
     */
    public static Date getDBDate() throws AppException, SysException {
        return new Date();

    }

    /**
     * 获得数据库当前时间字符串 精确到秒
     * @return
     * @throws SysException
     * @throws AppException
     */
    public static String getDBDateTimeStr() throws AppException, SysException {
        return DateUtil.dateTime2Str(new Date());

    }

    /**
     * 获得数据库当前时间字符串,精确到分
     * @return
     * @throws SysException
     * @throws AppException
     */
    public static String getDateTimeHMStr() throws AppException, SysException {
        return datetimehm.format(new Date());
    }

    /**
     * 根据日期返回星期
     * @param date
     * @return
     */
    public static String date2Week(Date date) {
        if (date == null)
            return null;
        return new SimpleDateFormat("E").format(date);
    }

    /**
     * 根据Calendar中的DAY_OF_WEEK值获得星期 1/星期日，2/星期一...
     * @param num
     * @return
     */
    public static String getDayOfWeek(int num) {
        if (num < 1 || num > 7) {
            return null;
        }
        String dayOfWeek = null;
        switch (num) {
        case 1:
            dayOfWeek = "星期日";
            break;
        case 2:
            dayOfWeek = "星期一";
            break;
        case 3:
            dayOfWeek = "星期二";
            break;
        case 4:
            dayOfWeek = "星期三";
            break;
        case 5:
            dayOfWeek = "星期四";
            break;
        case 6:
            dayOfWeek = "星期五";
            break;
        case 7:
            dayOfWeek = "星期六";
            break;
        }
        return dayOfWeek;
    }

    /**
     * 字符串转日期（只有小时和分钟）
     * @param str
     * @return
     * @throws AppException
     * @throws SysException
     */
    public static Date str2HourMiniTime(String str) throws AppException,
            SysException {
        Date date = null;
        if (!StringUtil.isBlank(str)) {
            date = DateUtil.to_date(str, "HH:mm");
        }
        return date;

    }

    public static Date TimeStampToDate(Timestamp ts) {
        Date date = null;
        String str = datetimef.format(ts);
        try {
            date = datetimef.parse(str); // Wed sep 26 00:00:00 CST 2007
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 日期转字符串（小时分钟和秒）
     * @param date
     * @return
     */
    public static String HourMiniSSTime2Str(Date date) {
        String str = "";
        if (null != date) {
            str = DateUtil.to_char(date, "HH:mm:ss");
        }
        return str;
    }

    /**
     * 日期转字符串（只有小时和分钟）
     * @param date
     * @return
     */
    public static String HourMiniTime2Str(Date date) {
        String str = "";
        if (null != date) {
            str = DateUtil.to_char(date, "HH:mm");
        }
        return str;
    }

    /**
     * 获取指定时间的小时
     * @param date
     * @return added zdw
     */
    public static int getHourOfDate(Date date) {
        if (date == null || "".equals(date)) {
            return -1;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 得到一个月有多少天
     * @param year
     * @param month
     * @return
     */
    public static int getDateOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.DATE, cal.get(Calendar.DATE) / 1);
        int dateOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        return dateOfMonth;
    }

    public static boolean validateDate(Timestamp time) throws SysException {
        try {
            Date bookingDate = new Date(time.getTime());
            Date curDate = new Date();
            Calendar ca = Calendar.getInstance();
            ca.setTime(new Date(ca.getTime().getYear(),
                    ca.getTime().getMonth(), ca.getTime().getDate()));
            int hour = curDate.getHours();
            if (hour < 12) {
                ca.add(Calendar.DATE, 1);
            } else {
                ca.add(Calendar.DATE, 2);
            }
            if (bookingDate.after(ca.getTime())
                    || bookingDate.equals(ca.getTime())) {
                return true;
            }
            return false;
        } catch (Exception e) {
            throw new SysException("9000005", "日期校验失败,输入为" + time, e);
        }
    }

    /**
     * 计算两个时间之间相隔天数
     * @param startday
     *            开始时间
     * @param endday
     *            结束时间
     * @return
     */
    public static int getIntervalDays(java.util.Date startday,
            java.util.Date endday) {
        // 确保startday在endday之前
        if (startday.after(endday)) {
            java.util.Date cal = startday;
            startday = endday;
            endday = cal;
        }
        // 分别得到两个时间的毫秒数
        long sl = startday.getTime();
        long el = endday.getTime();

        long ei = el / sl;
        // 根据毫秒数计算间隔天数&nbsp;
        return (int) (ei / (1000 * 60 * 60 * 24));
    }

    /**
     * @method_name getDBDate4Zx
     * @author lijixu
     * @date 2011/2/11 下午03:37:02
     * @description 获得数据库当前时间,格式为yyyyMMdd HH:mm:ss
     * @description 此方法供河南移动项目使用
     * @return
     * @throws AppException
     * @throws SysException
     * @reviewed_by
     */
    public static Date getDBDate4Zx() throws AppException, SysException {
        Date tempdate = getDBDate();
        String tmpStr = to_char(tempdate, "yyyyMMdd HH:mm:ss");
        Date retDate = to_date(tmpStr, "yyyyMMdd HH:mm:ss");
        return retDate;
    }

}
