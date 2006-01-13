/*
 * çÏê¨ì˙: 2005/12/24
 *
 */
package jp.starlogic.util.datetime;

import java.util.Calendar;
import java.util.Date;

import junit.framework.TestCase;

public class CalendarUtilTest extends TestCase {

    /*
     * Test method for 'jp.starlogic.util.datetime.CalendarUtil.getCalendar()'
     */
    public void testGetCalendar() {
        Calendar calendar = CalendarUtil.getCalendar();
        assertNotNull(calendar);
    }

    /*
     * Test method for 'jp.starlogic.util.datetime.CalendarUtil.getCalendar(Date)'
     */
    public void testGetCalendarDate() {
        Date now = new Date();
        Calendar calendar = CalendarUtil.getCalendar(now);
        assertNotNull(calendar);
        assertEquals(now,calendar.getTime());
    }

    /*
     * Test method for 'jp.starlogic.util.datetime.CalendarUtil.setActualMaximum(Calendar, int)'
     */
    public void testSetActualMaximum() {
        Calendar calendar = CalendarUtil.getCalendar();
        Calendar clone = CalendarUtil.getClone(calendar);
        CalendarUtil.setActualMaximum(calendar,Calendar.HOUR_OF_DAY);
        assertFalse(calendar.getTimeInMillis()==clone.getTimeInMillis());
    }

    /*
     * Test method for 'jp.starlogic.util.datetime.CalendarUtil.setActualMinimum(Calendar, int)'
     */
    public void testSetActualMinimum() {
        Calendar calendar = CalendarUtil.getCalendar();
        Calendar clone = CalendarUtil.getClone(calendar);
        CalendarUtil.setActualMinimum(calendar,Calendar.HOUR_OF_DAY);
        assertFalse(calendar.getTimeInMillis()==clone.getTimeInMillis());
    }

    /*
     * Test method for 'jp.starlogic.util.datetime.CalendarUtil.getClone(Calendar)'
     */
    public void testGetClone() {
        Calendar calendar = CalendarUtil.getCalendar();
        Calendar clone = CalendarUtil.getClone(calendar);
        assertEquals(calendar.getTimeInMillis(),clone.getTimeInMillis());
        calendar.set(9999,12,31);
        assertFalse(calendar.getTimeInMillis()==clone.getTimeInMillis());
    }

}
