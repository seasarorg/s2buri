/*
 * 作成日: 2005/12/24
 *
 */
package jp.starlogic.util.datetime;

import java.util.Calendar;

public class TimeUtil {

    public static Calendar clearMilliSecond(Calendar calendar) {
        calendar = CalendarUtil.getClone(calendar);
        CalendarUtil.setActualMinimum(calendar, Calendar.MILLISECOND);
        return calendar;
    }

    public static Calendar setMilliSecond(Calendar calendar, int millisec) {
        calendar = CalendarUtil.getClone(calendar);
        calendar.set(Calendar.MILLISECOND, millisec);
        return calendar;
    }

    public static Calendar setMaxMilliSecond(Calendar calendar) {
        calendar = CalendarUtil.getClone(calendar);
        CalendarUtil.setActualMaximum(calendar, Calendar.MILLISECOND);
        return calendar;
    }

    public static int getMilliSecond(Calendar calendar) {
        return calendar.get(Calendar.MILLISECOND);
    }

    public static Calendar clearSecond(Calendar calendar) {
        calendar = CalendarUtil.getClone(calendar);
        CalendarUtil.setActualMinimum(calendar, Calendar.SECOND);
        return calendar;
    }

    public static Calendar setSecond(Calendar calendar, int sec) {
        calendar = CalendarUtil.getClone(calendar);
        calendar.set(Calendar.SECOND, sec);
        return calendar;
    }

    public static Calendar setMaxSecond(Calendar calendar) {
        calendar = CalendarUtil.getClone(calendar);
        CalendarUtil.setActualMaximum(calendar, Calendar.SECOND);
        return calendar;
    }

    public static int getSecond(Calendar calendar) {
        return calendar.get(Calendar.SECOND);
    }

    public static Calendar clearMinute(Calendar calendar) {
        calendar = CalendarUtil.getClone(calendar);
        CalendarUtil.setActualMinimum(calendar, Calendar.MINUTE);
        return calendar;
    }

    public static Calendar setMinute(Calendar calendar, int minute) {
        calendar = CalendarUtil.getClone(calendar);
        calendar.set(Calendar.MINUTE, minute);
        return calendar;
    }

    public static Calendar setMaxMinute(Calendar calendar) {
        calendar = CalendarUtil.getClone(calendar);
        CalendarUtil.setActualMaximum(calendar, Calendar.MINUTE);
        return calendar;
    }

    public static int getMinute(Calendar calendar) {
        return calendar.get(Calendar.MINUTE);
    }

    public static Calendar clearHour(Calendar calendar) {
        calendar = CalendarUtil.getClone(calendar);
        CalendarUtil.setActualMinimum(calendar, Calendar.HOUR_OF_DAY);
        return calendar;
    }

    public static Calendar setHour(Calendar calendar, int hour) {
        calendar = CalendarUtil.getClone(calendar);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        return calendar;
    }

    public static Calendar setMaxHour(Calendar calendar) {
        calendar = CalendarUtil.getClone(calendar);
        CalendarUtil.setActualMaximum(calendar, Calendar.HOUR_OF_DAY);
        return calendar;
    }

    public static int getHour(Calendar calendar) {
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    public static Calendar clearTime(Calendar calendar) {
        calendar = CalendarUtil.getClone(calendar);
        calendar = clearMilliSecond(calendar);
        calendar = clearSecond(calendar);
        calendar = clearMinute(calendar);
        calendar = clearHour(calendar);
        return calendar;
    }

    public static Calendar setMaxTime(Calendar calendar) {
        calendar = CalendarUtil.getClone(calendar);
        calendar = setMaxMilliSecond(calendar);
        calendar = setMaxSecond(calendar);
        calendar = setMaxMinute(calendar);
        calendar = setMaxHour(calendar);
        return calendar;
    }
}
