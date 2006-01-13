/*
 * çÏê¨ì˙: 2005/12/26
 *
 */
package jp.starlogic.svm;

import java.util.ArrayList;

import jp.starlogic.servicemanager.service.CronTypeService;
import jp.starlogic.util.datetime.UtilCalendar;
import junit.framework.TestCase;

public class CronTypeServiceTest extends TestCase {
    public void testSecond() {
        CronTypeService service = new CronTypeService();
        service.setExeSecond(30);
        UtilCalendar calendar = (UtilCalendar)UtilCalendar.getInstance();
        calendar.setSecond(10);
        calendar.setMinute(10);
        calendar = (UtilCalendar)service.createNext(calendar);
        assertEquals(calendar.getSecond(),30);
        assertEquals(calendar.getMinute(),10);
        calendar = (UtilCalendar)service.createNext(calendar);
        assertEquals(calendar.getSecond(),30);
        assertEquals(calendar.getMinute(),11);
        calendar.setSecond(40);
        calendar = (UtilCalendar)service.createNext(calendar);
        assertEquals(calendar.getSecond(),30);
        assertEquals(calendar.getMinute(),12);
    }
    public void testMinute() {
        CronTypeService service = new CronTypeService();
        service.setExeMinute(30);
        UtilCalendar calendar = (UtilCalendar)UtilCalendar.getInstance();
        calendar.setMinute(10);
        calendar.setHourOfDay(12);
        calendar.setDayOfMonth(5);
        calendar = (UtilCalendar)service.createNext(calendar);
        assertEquals(calendar.getMinute(),30);
        assertEquals(calendar.getHourOfDay(),12);
        assertEquals(calendar.getDayOfMonth(),5);
        calendar = (UtilCalendar)service.createNext(calendar);
        assertEquals(calendar.getMinute(),30);
        assertEquals(calendar.getHourOfDay(),13);
        assertEquals(calendar.getDayOfMonth(),5);
        calendar.setHourOfDay(23);
        calendar = (UtilCalendar)service.createNext(calendar);
        assertEquals(calendar.getMinute(),30);
        assertEquals(calendar.getHourOfDay(),0);
        assertEquals(calendar.getDayOfMonth(),6);
    }
    public void testHourOfDay1() {
        CronTypeService service = new CronTypeService();
        service.setExeHour(12);
        UtilCalendar calendar = (UtilCalendar)UtilCalendar.getInstance();
        calendar.setHourOfDay(10);
        calendar.setDayOfMonth(5);
        calendar = (UtilCalendar)service.createNext(calendar);
        assertEquals(calendar.getHourOfDay(),12);
        assertEquals(calendar.getDayOfMonth(),5);
        calendar = (UtilCalendar)service.createNext(calendar);
        assertEquals(calendar.getHourOfDay(),12);
        assertEquals(calendar.getDayOfMonth(),6);
    }
    public void testHourOfDay2() {
        CronTypeService service = new CronTypeService();
        service.setExeHour(13);
        UtilCalendar calendar = (UtilCalendar)UtilCalendar.getInstance();
        calendar.setHourOfDay(10);
        calendar.setDayOfMonth(5);
        calendar = (UtilCalendar)service.createNext(calendar);
        assertEquals(calendar.getHourOfDay(),13);
        assertEquals(calendar.getDayOfMonth(),5);
        calendar = (UtilCalendar)service.createNext(calendar);
        assertEquals(calendar.getHourOfDay(),13);
        assertEquals(calendar.getDayOfMonth(),6);
    }
    public void testDay() {
        CronTypeService service = new CronTypeService();
        service.setExeDay(12);
        UtilCalendar calendar = (UtilCalendar)UtilCalendar.getInstance();
        calendar.setDayOfMonth(5);
        calendar.setMonth(5);
        calendar = (UtilCalendar)service.createNext(calendar);
        assertEquals(calendar.getDayOfMonth(),12);
        assertEquals(calendar.getMonth(),5);
        calendar = (UtilCalendar)service.createNext(calendar);
        assertEquals(calendar.getDayOfMonth(),12);
        assertEquals(calendar.getMonth(),6);
    }
    public void testLastDayOfMonth() {
        CronTypeService service = new CronTypeService();
        service.setLastDayOfMonth(Boolean.TRUE);
        UtilCalendar calendar = (UtilCalendar)UtilCalendar.getInstance();
        calendar.setDayOfMonth(5);
        calendar.setNaturalMonth(1);
        calendar.setYear(2005);
        calendar = (UtilCalendar)service.createNext(calendar);
        assertEquals(calendar.getDayOfMonth(),31);
        assertEquals(calendar.getNaturalMonth(),1);
        calendar = (UtilCalendar)service.createNext(calendar);
        assertEquals(calendar.getDayOfMonth(),28);
        assertEquals(calendar.getNaturalMonth(),2);
        calendar = (UtilCalendar)service.createNext(calendar);
        assertEquals(calendar.getDayOfMonth(),31);
        assertEquals(calendar.getNaturalMonth(),3);
    }
    public void testDayOfMonth() {
        CronTypeService service = new CronTypeService();
        service.setFirstDayOfMonth(Boolean.TRUE);
        UtilCalendar calendar = (UtilCalendar)UtilCalendar.getInstance();
        calendar.setDayOfMonth(5);
        calendar.setNaturalMonth(1);
        calendar.setYear(2005);
        calendar = (UtilCalendar)service.createNext(calendar);
        assertEquals(calendar.getDayOfMonth(),1);
        assertEquals(calendar.getNaturalMonth(),2);
        calendar = (UtilCalendar)service.createNext(calendar);
        assertEquals(calendar.getDayOfMonth(),1);
        assertEquals(calendar.getNaturalMonth(),3);
        calendar.setNaturalMonth(12);
        calendar = (UtilCalendar)service.createNext(calendar);
        assertEquals(calendar.getDayOfMonth(),1);
        assertEquals(calendar.getNaturalMonth(),1);
        assertEquals(calendar.getYear(),2006);
    }
    public void testNearestWeekday() {
        CronTypeService service = new CronTypeService();
        service.setNearestWeekday(Boolean.TRUE);
        UtilCalendar calendar = (UtilCalendar)UtilCalendar.getInstance();
        calendar.setDayOfMonth(23);
        calendar.setNaturalMonth(12);
        calendar.setYear(2005);
        calendar = (UtilCalendar)service.createNext(calendar);
        assertEquals(calendar.getDayOfMonth(),26);
        assertEquals(calendar.getNaturalMonth(),12);
    }
    public void testNearestHoliday() {
        CronTypeService service = new CronTypeService();
        service.setNearestHoliday(Boolean.TRUE);
        UtilCalendar calendar = (UtilCalendar)UtilCalendar.getInstance();
        calendar.setDayOfMonth(19);
        calendar.setNaturalMonth(12);
        calendar.setYear(2005);
        calendar = (UtilCalendar)service.createNext(calendar);
        assertEquals(calendar.getDayOfMonth(),23);
        assertEquals(calendar.getNaturalMonth(),12);
    }
    public void testWeek1() {
        CronTypeService service = new CronTypeService();
        ArrayList weeks = new ArrayList();
        weeks.add(new Integer(1));
        service.setExeWeek(weeks);
        UtilCalendar calendar = (UtilCalendar)UtilCalendar.getInstance();
        calendar.setDayOfMonth(16);
        calendar.setNaturalMonth(12);
        calendar.setYear(2005);
        calendar = (UtilCalendar)service.createNext(calendar);
        assertEquals(calendar.getDayOfMonth(),18);
        assertEquals(calendar.getNaturalMonth(),12);
        calendar = (UtilCalendar)service.createNext(calendar);
        assertEquals(calendar.getDayOfMonth(),25);
        assertEquals(calendar.getNaturalMonth(),12);
    }
    public void testWeek2() {
        CronTypeService service = new CronTypeService();
        ArrayList weeks = new ArrayList();
        weeks.add(new Integer(2)); //åé
        weeks.add(new Integer(4)); //êÖ
        weeks.add(new Integer(6)); //ã‡
        service.setExeWeek(weeks);
        UtilCalendar calendar = (UtilCalendar)UtilCalendar.getInstance();
        calendar.setDayOfMonth(16);
        calendar.setNaturalMonth(12);
        calendar.setYear(2005);
        calendar = (UtilCalendar)service.createNext(calendar);
        assertEquals(calendar.getDayOfMonth(),19);
        assertEquals(calendar.getNaturalMonth(),12);
        calendar = (UtilCalendar)service.createNext(calendar);
        assertEquals(calendar.getDayOfMonth(),21);
        assertEquals(calendar.getNaturalMonth(),12);
        calendar = (UtilCalendar)service.createNext(calendar);
        assertEquals(calendar.getDayOfMonth(),23);
        assertEquals(calendar.getNaturalMonth(),12);
    }
    public void testMonth() {
        CronTypeService service = new CronTypeService();
        service.setExeMonth(6);
        UtilCalendar calendar = (UtilCalendar)UtilCalendar.getInstance();
        calendar.setNaturalMonth(1);
        calendar.setYear(2005);
        calendar = (UtilCalendar)service.createNext(calendar);
        assertEquals(calendar.getMonth(),6);
        assertEquals(calendar.getYear(),2005);
        calendar = (UtilCalendar)service.createNext(calendar);
        assertEquals(calendar.getMonth(),6);
        assertEquals(calendar.getYear(),2006);
    }
    
    public void testSetHHMMSS() {
        CronTypeService service = new CronTypeService();
        service.setExeHour(6);
        service.setExeMinute(0);
        service.setExeSecond(0);
        UtilCalendar calendar = (UtilCalendar)UtilCalendar.getInstance();
        calendar.setHourOfDay(1);
        calendar.setMinute(1);
        calendar.setSecond(1);
        calendar.setDayOfMonth(16);
        calendar.setNaturalMonth(6);
        calendar = (UtilCalendar)service.createNext(calendar);
        assertEquals(calendar.getHourOfDay(),6);
        assertEquals(calendar.getMinute(),0);
        assertEquals(calendar.getSecond(),0);
        assertEquals(calendar.getDayOfMonth(),16);
        assertEquals(calendar.getNaturalMonth(),6);
        calendar.setHourOfDay(5);
        calendar.setMinute(1);
        calendar.setSecond(1);
        calendar.setDayOfMonth(16);
        calendar.setNaturalMonth(6);
        calendar = (UtilCalendar)service.createNext(calendar);
        assertEquals(calendar.getHourOfDay(),6);
        assertEquals(calendar.getMinute(),0);
        assertEquals(calendar.getSecond(),0);
        assertEquals(calendar.getDayOfMonth(),16);
        assertEquals(calendar.getNaturalMonth(),6);
        calendar = (UtilCalendar)service.createNext(calendar);
        assertEquals(calendar.getHourOfDay(),6);
        assertEquals(calendar.getMinute(),0);
        assertEquals(calendar.getSecond(),0);
        assertEquals(calendar.getDayOfMonth(),17);
        assertEquals(calendar.getNaturalMonth(),6);
        
    }
    public void testSetWeekDayHH() {
        CronTypeService service = new CronTypeService();
        service.setExeHour(6);
        service.setNearestWeekday(Boolean.TRUE);
        UtilCalendar calendar = (UtilCalendar)UtilCalendar.getInstance();
        calendar.setHourOfDay(1);
        calendar.setDayOfMonth(21);
        calendar.setNaturalMonth(12);
        calendar.setYear(2005);
        calendar = (UtilCalendar)service.createNext(calendar);
        assertEquals(calendar.getHourOfDay(),6);
        assertEquals(calendar.getDayOfMonth(),22);
        assertEquals(calendar.getNaturalMonth(),12);
        assertEquals(calendar.getYear(),2005);
        calendar = (UtilCalendar)service.createNext(calendar);
        assertEquals(calendar.getHourOfDay(),6);
        assertEquals(calendar.getDayOfMonth(),26);
        assertEquals(calendar.getNaturalMonth(),12);
        assertEquals(calendar.getYear(),2005);
    }
        
    
}
