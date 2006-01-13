/*
 * çÏê¨ì˙: 2005/12/24
 *
 */
package jp.starlogic.util.datetime;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    
    public static Calendar getFirstDayOfMonth(Calendar calendar) {
        calendar = CalendarUtil.getClone(calendar);
        CalendarUtil.setActualMinimum(calendar,Calendar.DAY_OF_MONTH);
        return calendar;
    }
    public static Calendar setDayOfMonth(Calendar calendar,int val) {
        calendar = CalendarUtil.getClone(calendar);
        calendar.set(Calendar.DAY_OF_MONTH,val);
        return calendar;
    }
    public static Calendar getLastDayOfMonth(Calendar calendar) {
        calendar = CalendarUtil.getClone(calendar);
        CalendarUtil.setActualMaximum(calendar,Calendar.DAY_OF_MONTH);
        return calendar;
    }
    public static int getDayOfMonth(Calendar calendar) {
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    
    public static Calendar getFirstMonth(Calendar calendar) {
        calendar = CalendarUtil.getClone(calendar);
        CalendarUtil.setActualMinimum(calendar,Calendar.MONTH);
        return calendar;
    }
    public static Calendar setMonth(Calendar calendar,int val) {
        calendar = CalendarUtil.getClone(calendar);
        calendar.set(Calendar.MONTH,val);
        return calendar;
    }
    public static Calendar getLastMonth(Calendar calendar) {
        calendar = CalendarUtil.getClone(calendar);
        CalendarUtil.setActualMaximum(calendar,Calendar.MONTH);
        return calendar;
    }
    public static int getMonth(Calendar calendar) {
        return calendar.get(Calendar.MONTH);
    }

    
    public static Calendar getFirstYear(Calendar calendar) {
        calendar = CalendarUtil.getClone(calendar);
        CalendarUtil.setActualMinimum(calendar,Calendar.YEAR);
        return calendar;
    }
    public static Calendar setYear(Calendar calendar,int val) {
        calendar = CalendarUtil.getClone(calendar);
        calendar.set(Calendar.YEAR,val);
        return calendar;
    }
    public static Calendar getLastYear(Calendar calendar) {
        calendar = CalendarUtil.getClone(calendar);
        CalendarUtil.setActualMaximum(calendar,Calendar.YEAR);
        return calendar;
    }
    public static int getYear(Calendar calendar) {
        return calendar.get(Calendar.YEAR);
    }
    
    
    public static long compare(Calendar calendar1,Calendar calendar2) {
        long result = calendar1.getTimeInMillis() - calendar2.getTimeInMillis();
        return result;
    }
    
    public static long compare(Calendar calendar1,Date date) {
        Calendar calendar2 = CalendarUtil.getCalendar(date);
        return compare(calendar1,calendar2);
    }
    
    public static long compare(Date date,Calendar calendar2) {
        Calendar calendar1 = CalendarUtil.getCalendar(date);
        return compare(calendar1,calendar2);
    }
    
    public static Calendar getSQLMaxCalendar() {
        Calendar calendar = CalendarUtil.getCalendar();
        calendar = TimeUtil.setMaxTime(calendar);
        calendar.set(9999,11,31);
        return calendar;
    }
    
    public static Date getSQLMaxDate() {
        return getSQLMaxCalendar().getTime();
    }

    public static Calendar getSQLMinCalendar() {
        Calendar calendar = CalendarUtil.getCalendar();
        calendar = TimeUtil.clearTime(calendar);
        calendar.set(1900,0,1);
        return calendar;
    }
    
    public static Date getSQLMinDate() {
        return getSQLMinCalendar().getTime();
    }
    
    
}
