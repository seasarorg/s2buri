/*
 * çÏê¨ì˙: 2005/12/24
 *
 */
package jp.starlogic.util.datetime;

import junit.framework.TestCase;

public class UtilCalendarTest extends TestCase {
    public void testUtilCalendar() {
        UtilCalendar calendar = (UtilCalendar)UtilCalendar.getInstance();
        assertNotNull(calendar);
        calendar.clearTime();
        calendar.set(2005,11,24);
        assertEquals(calendar.getYear(),2005);
        assertEquals(calendar.getNaturalMonth(),12);
        assertEquals(calendar.getDayOfMonth(),24);
        assertEquals(calendar.getDayOfWeek(),7);
        assertEquals(calendar.getWeekString(),"ìy");
        assertEquals(calendar.getHolidayName(),"");
        calendar.prevDay();
        assertEquals(calendar.getNaturalMonth(),12);
        assertEquals(calendar.getDayOfMonth(),23);
        assertEquals(calendar.getDayOfWeek(),6);
        assertEquals(calendar.getWeekString(),"ã‡");
        assertEquals(calendar.getHolidayName(),"ìVçcíaê∂ì˙");
        calendar.nextWeekday();
        assertEquals(calendar.getDayOfMonth(),26);
        calendar.subWeekday(2);
        assertEquals(calendar.getDayOfMonth(),21);
        calendar.addWeekday(2);
        assertEquals(calendar.getDayOfMonth(),26);
    }
    
    public void te1stPrintCalendar() {
        UtilCalendar calendar = (UtilCalendar)UtilCalendar.getInstance();
        calendar.set(2005,0,1);
        while(calendar.getYear()==2005) {
            System.out.println(calendar.getYear() + "/" + calendar.getNaturalMonth() + "/" + calendar.getDayOfMonth() + "(" + calendar.getWeekString() + "ójì˙) " + calendar.getHolidayName());
            calendar.nextDay();
        }
        
    }
    
    public void te1stPrintWorkCalendar() {
        UtilCalendar calendar = (UtilCalendar)UtilCalendar.getInstance();
        calendar.set(2005,0,1);
        while(calendar.getYear()==2005) {
            calendar.nextWeekday();
            System.out.println(calendar.getYear() + "/" + calendar.getNaturalMonth() + "/" + calendar.getDayOfMonth() + "(" + calendar.getWeekString() + "ójì˙) " + calendar.getHolidayName());
        }
        
    }
    
    public void te1stPrintWorkCalendarPrev() {
        UtilCalendar calendar = (UtilCalendar)UtilCalendar.getInstance();
        calendar.set(2005,11,31);
        while(calendar.getYear()==2005) {
            calendar.prevWeekday();
            System.out.println(calendar.getYear() + "/" + calendar.getNaturalMonth() + "/" + calendar.getDayOfMonth() + "(" + calendar.getWeekString() + "ójì˙) " + calendar.getHolidayName());
        }
        
    }
    
    public void testComp() {
        UtilCalendar cal1 = (UtilCalendar)UtilCalendar.getInstance();
        UtilCalendar cal2 = (UtilCalendar)UtilCalendar.getInstance();
        assertTrue(DateUtil.compare(cal1,cal2) == 0);
        cal1.clearTime();
        assertTrue(DateUtil.compare(cal1,cal2) < 0);
        cal1.nextDay();
        assertTrue(DateUtil.compare(cal1,cal2) > 0);
    }
}
