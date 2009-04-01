/*
 * 作成日: 2005/12/24
 *
 */
package jp.starlogic.util.datetime;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import jp.ne.dion.h3.sakatsu.addinbox.KtHoliday;

public class UtilCalendar extends GregorianCalendar {
    private static final long serialVersionUID = -4210613461645866056L;

    public static Calendar getInstance() {
        return new UtilCalendar();
    }

    public static Calendar getInstance(Date date) {
        Calendar calendar = getInstance();
        calendar.setTime(date);
        return calendar;
    }

    protected UtilCalendar() {
        super();
    }

    protected UtilCalendar(TimeZone zone, Locale aLocale) {
        super(zone, aLocale);
    }

    @Override
    public Object clone() {
        Calendar calendar = getInstance();
        calendar.setTimeInMillis(getTimeInMillis());
        return calendar;
    }

    public Calendar clearTime() {
        Calendar calendar = TimeUtil.clearTime(this);
        setTimeInMillis(calendar.getTimeInMillis());
        return this;
    }

    public Calendar setMaxTime() {
        Calendar calendar = TimeUtil.setMaxTime(this);
        setTimeInMillis(calendar.getTimeInMillis());
        return this;
    }

    public static Calendar setSqlMax() {
        Calendar calendar = DateUtil.getSQLMaxCalendar();
        calendar.setTimeInMillis(calendar.getTimeInMillis());
        return calendar;
    }

    public static Calendar setSqlMin() {
        Calendar calendar = DateUtil.getSQLMinCalendar();
        calendar.setTimeInMillis(calendar.getTimeInMillis());
        return calendar;
    }

    public Calendar clearMilliSecond() {
        CalendarUtil.setActualMinimum(this, Calendar.MILLISECOND);
        return this;
    }

    public Calendar addMilliSecond(int val) {
        this.add(Calendar.MILLISECOND, val);
        return this;
    }

    public Calendar rollMilliSecond(int val) {
        this.roll(Calendar.MILLISECOND, val);
        return this;
    }

    public Calendar setMilliSecond(int millisec) {
        this.set(Calendar.MILLISECOND, millisec);
        return this;
    }

    public Calendar setMaxMilliSecond() {
        CalendarUtil.setActualMaximum(this, Calendar.MILLISECOND);
        return this;
    }

    public int getMilliSecond() {
        return this.get(Calendar.MILLISECOND);
    }

    public Calendar clearSecond() {
        CalendarUtil.setActualMinimum(this, Calendar.SECOND);
        return this;
    }

    public Calendar addSecond(int val) {
        this.add(Calendar.SECOND, val);
        return this;
    }

    public Calendar rollSecond(int val) {
        this.roll(Calendar.SECOND, val);
        return this;
    }

    public Calendar setSecond(int sec) {
        this.set(Calendar.SECOND, sec);
        return this;
    }

    public Calendar setMaxSecond() {
        CalendarUtil.setActualMaximum(this, Calendar.SECOND);
        return this;
    }

    public int getSecond() {
        return this.get(Calendar.SECOND);
    }

    public Calendar clearMinute() {
        CalendarUtil.setActualMinimum(this, Calendar.MINUTE);
        return this;
    }

    public Calendar addMinute(int val) {
        this.add(Calendar.MINUTE, val);
        return this;
    }

    public Calendar rollMinute(int val) {
        this.roll(Calendar.MINUTE, val);
        return this;
    }

    public Calendar setMinute(int minute) {
        this.set(Calendar.MINUTE, minute);
        return this;
    }

    public Calendar setMaxMinute() {
        CalendarUtil.setActualMaximum(this, Calendar.MINUTE);
        return this;
    }

    public int getMinute() {
        return this.get(Calendar.MINUTE);
    }

    public Calendar clearHourOfDay() {
        CalendarUtil.setActualMinimum(this, Calendar.HOUR_OF_DAY);
        return this;
    }

    public Calendar addHourOfDay(int val) {
        this.add(Calendar.HOUR_OF_DAY, val);
        return this;
    }

    public Calendar rollHourOfDay(int val) {
        this.roll(Calendar.HOUR_OF_DAY, val);
        return this;
    }

    public Calendar setHourOfDay(int hour) {
        this.set(Calendar.HOUR_OF_DAY, hour);
        return this;
    }

    public Calendar setMaxHourOfDay() {
        CalendarUtil.setActualMaximum(this, Calendar.HOUR_OF_DAY);
        return this;
    }

    public int getHourOfDay() {
        return this.get(Calendar.HOUR_OF_DAY);
    }

    public Calendar clearHour() {
        CalendarUtil.setActualMinimum(this, Calendar.HOUR);
        return this;
    }

    public Calendar addHour(int val) {
        this.add(Calendar.HOUR, val);
        return this;
    }

    public Calendar rollHour(int val) {
        this.roll(Calendar.HOUR, val);
        return this;
    }

    public Calendar setHour(int hour) {
        this.set(Calendar.HOUR, hour);
        return this;
    }

    public Calendar setMaxHour() {
        CalendarUtil.setActualMaximum(this, Calendar.HOUR);
        return this;
    }

    public int getHour() {
        return this.get(Calendar.HOUR);
    }

    public Calendar getFirstDayOfMonth() {
        CalendarUtil.setActualMinimum(this, Calendar.DAY_OF_MONTH);
        return this;
    }

    public Calendar addDayOfMonth(int val) {
        this.add(Calendar.DAY_OF_MONTH, val);
        return this;
    }

    public Calendar rollDayOfMonth(int val) {
        this.roll(Calendar.DAY_OF_MONTH, val);
        return this;
    }

    public Calendar setDayOfMonth(int val) {
        this.set(Calendar.DAY_OF_MONTH, val);
        return this;
    }

    public Calendar getLastDayOfMonth() {
        CalendarUtil.setActualMaximum(this, Calendar.DAY_OF_MONTH);
        return this;
    }

    public int getDayOfMonth() {
        return this.get(Calendar.DAY_OF_MONTH);
    }

    public Calendar prevDay() {
        return addDayOfMonth(-1);
    }

    public Calendar nextDay() {
        return addDayOfMonth(1);
    }

    public Calendar nextWeekday() {
        UtilCalendar checkDay = (UtilCalendar) clone();
        do {
            checkDay.nextDay();
        } while (checkDay.isHoliday() || checkDay.isWeekEnd());
        setTimeInMillis(checkDay.getTimeInMillis());
        return this;
    }

    public Calendar prevWeekday() {
        UtilCalendar checkDay = (UtilCalendar) clone();
        do {
            checkDay.prevDay();
        } while (checkDay.isHoliday() || checkDay.isWeekEnd());
        setTimeInMillis(checkDay.getTimeInMillis());
        return this;
    }

    public Calendar addWeekday(int val) {
        for (int i = 0; i < val; i++) {
            nextWeekday();
        }
        return this;
    }

    public Calendar subWeekday(int val) {
        for (int i = 0; i < val; i++) {
            prevWeekday();
        }
        return this;
    }

    public Calendar nextHoliday() {
        UtilCalendar checkDay = (UtilCalendar) clone();
        do {
            checkDay.nextDay();
        } while (!(checkDay.isHoliday() || checkDay.isWeekEnd()));
        setTimeInMillis(checkDay.getTimeInMillis());
        return this;
    }

    public Calendar prevHoliday() {
        UtilCalendar checkDay = (UtilCalendar) clone();
        do {
            checkDay.prevDay();
        } while (!(checkDay.isHoliday() || checkDay.isWeekEnd()));
        setTimeInMillis(checkDay.getTimeInMillis());
        return this;
    }

    public Calendar addHoliday(int val) {
        for (int i = 0; i < val; i++) {
            nextHoliday();
        }
        return this;
    }

    public Calendar subHoliday(int val) {
        for (int i = 0; i < val; i++) {
            prevHoliday();
        }
        return this;
    }

    public Calendar getFirstMonth() {
        CalendarUtil.setActualMinimum(this, Calendar.MONTH);
        return this;
    }

    public Calendar addMonth(int val) {
        this.add(Calendar.MONTH, val);
        return this;
    }

    public Calendar rollMonth(int val) {
        this.roll(Calendar.MONTH, val);
        return this;
    }

    public Calendar setMonth(int val) {
        this.set(Calendar.MONTH, val);
        return this;
    }

    public Calendar setNaturalMonth(int val) {
        this.set(Calendar.MONTH, val - 1);
        return this;
    }

    public Calendar getLastMonth() {
        CalendarUtil.setActualMaximum(this, Calendar.MONTH);
        return this;
    }

    public int getMonth() {
        return this.get(Calendar.MONTH);
    }

    public int getNaturalMonth() {
        return this.get(Calendar.MONTH) + 1;
    }

    public Calendar prevMonth() {
        return addMonth(-1);
    }

    public Calendar nextMonth() {
        return addMonth(1);
    }

    public Calendar getFirstYear() {
        CalendarUtil.setActualMinimum(this, Calendar.YEAR);
        return this;
    }

    public Calendar addYear(int val) {
        this.add(Calendar.YEAR, val);
        return this;
    }

    public Calendar rollYear(int val) {
        this.roll(Calendar.YEAR, val);
        return this;
    }

    public Calendar setYear(int val) {
        this.set(Calendar.YEAR, val);
        return this;
    }

    public Calendar getLastYear() {
        CalendarUtil.setActualMaximum(this, Calendar.YEAR);
        return this;
    }

    public int getYear() {
        return this.get(Calendar.YEAR);
    }

    public Calendar prevYear() {
        return addYear(-1);
    }

    public Calendar nextYear() {
        return addYear(1);
    }

    public boolean isHoliday() {
        boolean isHoliday = getHolidayName().length() > 0;
        return isHoliday;
    }

    public String getHolidayName() {
        String holidayName = KtHoliday.getHolidayName(this);
        return holidayName;
    }

    public int getDayOfWeek() {
        return this.get(Calendar.DAY_OF_WEEK);
    }

    public String getWeekString() {
        final String weekStr[] = new String[] { "", "日", "月", "火", "水", "木", "金", "土" };
        String ret = weekStr[getDayOfWeek()];
        return ret;
    }

    public boolean isWeekEnd() {
        int weekNo = getDayOfWeek();
        if (weekNo == 1) {
            return true;
        }
        if (weekNo == 7) {
            return true;
        }
        return false;
    }
}
