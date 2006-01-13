/*
 * çÏê¨ì˙: 2005/12/24
 *
 */
package jp.starlogic.util.datetime;

import java.util.Calendar;

import junit.framework.TestCase;

public class TimeUtilTest extends TestCase {

    /*
     * Test method for 'jp.starlogic.util.datetime.TimeUtil.clearMilliSecond(Calendar)'
     */
    public void testClearMilliSecond() {
        Calendar calendar = CalendarUtil.getCalendar();
        Calendar calendar2 = TimeUtil.clearMilliSecond(calendar);
        int org = TimeUtil.getMilliSecond(calendar);
        int proc = TimeUtil.getMilliSecond(calendar2);
        assertFalse(org==proc);
    }

    /*
     * Test method for 'jp.starlogic.util.datetime.TimeUtil.setMilliSecond(Calendar, int)'
     */
    public void testSetMilliSecond() {
        Calendar calendar = CalendarUtil.getCalendar();
        Calendar calendar2 = TimeUtil.setMilliSecond(calendar,100);
        int org = TimeUtil.getMilliSecond(calendar);
        int proc = TimeUtil.getMilliSecond(calendar2);
        assertFalse(org==proc);
        assertEquals(100,proc);
    }

    /*
     * Test method for 'jp.starlogic.util.datetime.TimeUtil.setMaxMilliSecond(Calendar)'
     */
    public void testSetMaxMilliSecond() {
        Calendar calendar = CalendarUtil.getCalendar();
        Calendar calendar2 = TimeUtil.setMaxMilliSecond(calendar);
        int org = TimeUtil.getMilliSecond(calendar);
        int proc = TimeUtil.getMilliSecond(calendar2);
        assertFalse(org==proc);
    }

    /*
     * Test method for 'jp.starlogic.util.datetime.TimeUtil.clearSecond(Calendar)'
     */
    public void testClearSecond() {
        Calendar calendar = CalendarUtil.getCalendar();
        Calendar calendar2 = TimeUtil.clearSecond(calendar);
        int org = TimeUtil.getSecond(calendar);
        int proc = TimeUtil.getSecond(calendar2);
        assertFalse(org==proc);
    }

    /*
     * Test method for 'jp.starlogic.util.datetime.TimeUtil.setSecond(Calendar, int)'
     */
    public void testSetSecond() {
        Calendar calendar = CalendarUtil.getCalendar();
        Calendar calendar2 = TimeUtil.setSecond(calendar,35);
        int org = TimeUtil.getSecond(calendar);
        int proc = TimeUtil.getSecond(calendar2);
        assertFalse(org==proc);
        assertEquals(35,proc);
    }

    /*
     * Test method for 'jp.starlogic.util.datetime.TimeUtil.setMaxSecond(Calendar)'
     */
    public void testSetMaxSecond() {
        Calendar calendar = CalendarUtil.getCalendar();
        Calendar calendar2 = TimeUtil.setMaxSecond(calendar);
        int org = TimeUtil.getSecond(calendar);
        int proc = TimeUtil.getSecond(calendar2);
        assertFalse(org==proc);
    }

    /*
     * Test method for 'jp.starlogic.util.datetime.TimeUtil.clearMinute(Calendar)'
     */
    public void testClearMinute() {
        Calendar calendar = CalendarUtil.getCalendar();
        Calendar calendar2 = TimeUtil.clearMinute(calendar);
        int org = TimeUtil.getMinute(calendar);
        int proc = TimeUtil.getMinute(calendar2);
        assertFalse(org==proc);
    }

    /*
     * Test method for 'jp.starlogic.util.datetime.TimeUtil.setMinute(Calendar, int)'
     */
    public void testSetMinute() {
        Calendar calendar = CalendarUtil.getCalendar();
        Calendar calendar2 = TimeUtil.setMinute(calendar,35);
        int org = TimeUtil.getMinute(calendar);
        int proc = TimeUtil.getMinute(calendar2);
        assertFalse(org==proc);
        assertEquals(35,proc);
    }

    /*
     * Test method for 'jp.starlogic.util.datetime.TimeUtil.setMaxMinute(Calendar)'
     */
    public void testSetMaxMinute() {
        Calendar calendar = CalendarUtil.getCalendar();
        Calendar calendar2 = TimeUtil.setMaxMinute(calendar);
        int org = TimeUtil.getMinute(calendar);
        int proc = TimeUtil.getMinute(calendar2);
        assertFalse(org==proc);
    }

    /*
     * Test method for 'jp.starlogic.util.datetime.TimeUtil.clearHour(Calendar)'
     */
    public void testClearHour() {
        Calendar calendar = CalendarUtil.getCalendar();
        Calendar calendar2 = TimeUtil.clearHour(calendar);
        int org = TimeUtil.getHour(calendar);
        int proc = TimeUtil.getHour(calendar2);
        assertFalse(org==proc);
    }

    /*
     * Test method for 'jp.starlogic.util.datetime.TimeUtil.setHour(Calendar, int)'
     */
    public void testSetHour() {
        Calendar calendar = CalendarUtil.getCalendar();
        Calendar calendar2 = TimeUtil.setHour(calendar,5);
        int org = TimeUtil.getHour(calendar);
        int proc = TimeUtil.getHour(calendar2);
        assertFalse(org==proc);
        assertEquals(5,proc);
    }

    /*
     * Test method for 'jp.starlogic.util.datetime.TimeUtil.setMaxHour(Calendar)'
     */
    public void testSetMaxHour() {
        Calendar calendar = CalendarUtil.getCalendar();
        Calendar calendar2 = TimeUtil.setMaxHour(calendar);
        int org = TimeUtil.getHour(calendar);
        int proc = TimeUtil.getHour(calendar2);
        assertFalse(org==proc);
    }

    /*
     * Test method for 'jp.starlogic.util.datetime.TimeUtil.clearTime(Calendar)'
     */
    public void testClearTime() {
        Calendar calendar = CalendarUtil.getCalendar();
        Calendar calendar2 = TimeUtil.clearTime(calendar);
        System.out.println("ClearTime"+calendar2.getTime());
    }

    /*
     * Test method for 'jp.starlogic.util.datetime.TimeUtil.setMaxTime(Calendar)'
     */
    public void testSetMaxTime() {
        Calendar calendar = CalendarUtil.getCalendar();
        Calendar calendar2 = TimeUtil.setMaxTime(calendar);
        System.out.println("SetMaxTime"+calendar2.getTime());
    }

}
