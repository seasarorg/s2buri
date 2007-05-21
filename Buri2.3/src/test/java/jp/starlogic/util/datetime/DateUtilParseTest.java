/*
 * 作成日: 2006/07/15
 *
 */
package jp.starlogic.util.datetime;

import java.text.SimpleDateFormat;
import java.util.Date;

import junit.framework.TestCase;

public class DateUtilParseTest extends TestCase {

    public DateUtilParseTest(String name) {
        super(name);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    public void testParse01() {
        UtilCalendar calendar = (UtilCalendar)UtilCalendar.getInstance();
        calendar.set(2006,7,15,12,41,40);
        calendar.clearMilliSecond();
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String txt = sdf.format(calendar.getTime());
        Date check = DateUtil.parse(txt);
        assertEquals(calendar.getTime(),check);

        sdf = new SimpleDateFormat("yyyyMMddHHmm");
        UtilCalendar tmp = (UtilCalendar)calendar.clone();
        tmp.clearSecond();
        txt = sdf.format(tmp.getTime());
        check = DateUtil.parse(txt);
        assertEquals(tmp.getTime(),check);

        sdf = new SimpleDateFormat("yyyyMMddHH");
        tmp.clearMinute();
        txt = sdf.format(tmp.getTime());
        check = DateUtil.parse(txt);
        assertEquals(tmp.getTime(),check);

        sdf = new SimpleDateFormat("yyyyMMdd");
        tmp.clearHourOfDay();
        txt = sdf.format(tmp.getTime());
        check = DateUtil.parse(txt);
        assertEquals(tmp.getTime(),check);

        sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        txt = sdf.format(calendar.getTime());
        check = DateUtil.parse(txt);
        assertEquals(calendar.getTime(),check);
    
        sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        tmp = (UtilCalendar)calendar.clone();
        tmp.clearSecond();
        txt = sdf.format(tmp.getTime());
        check = DateUtil.parse(txt);
        assertEquals(tmp.getTime(),check);
        
        sdf = new SimpleDateFormat("yyyy/MM/dd HH");
        tmp.clearMinute();
        txt = sdf.format(tmp.getTime());
        check = DateUtil.parse(txt);
        assertEquals(tmp.getTime(),check);
        
        sdf = new SimpleDateFormat("yyyy/MM/dd");
        tmp.clearHourOfDay();
        txt = sdf.format(tmp.getTime());
        check = DateUtil.parse(txt);
        assertEquals(tmp.getTime(),check);

        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        txt = sdf.format(calendar.getTime());
        check = DateUtil.parse(txt);
        assertEquals(calendar.getTime(),check);
    
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        tmp = (UtilCalendar)calendar.clone();
        tmp.clearSecond();
        txt = sdf.format(tmp.getTime());
        check = DateUtil.parse(txt);
        assertEquals(tmp.getTime(),check);
        
        sdf = new SimpleDateFormat("yyyy-MM-dd HH");
        tmp.clearMinute();
        txt = sdf.format(tmp.getTime());
        check = DateUtil.parse(txt);
        assertEquals(tmp.getTime(),check);
        
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        tmp.clearHourOfDay();
        txt = sdf.format(tmp.getTime());
        check = DateUtil.parse(txt);
        assertEquals(tmp.getTime(),check);
    }

}
