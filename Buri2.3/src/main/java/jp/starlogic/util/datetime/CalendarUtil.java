/*
 * 作成日: 2005/12/24
 *
 */
package jp.starlogic.util.datetime;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CalendarUtil {
    public static Calendar getCalendar() {
        return GregorianCalendar.getInstance();
    }
    public static Calendar getCalendar(Date date) {
        Calendar calendar = getCalendar();
        calendar.setTime(date);
        return calendar;
    }
    
    public static void setActualMaximum(Calendar calendar,int tgt) {
        calendar.set(tgt,calendar.getActualMaximum(tgt));
    }
    public static void setActualMinimum(Calendar calendar,int tgt) {
        calendar.set(tgt,calendar.getActualMinimum(tgt));
    }
    public static Calendar getClone(Calendar calendar) {
        return (Calendar)calendar.clone();
    }

}
