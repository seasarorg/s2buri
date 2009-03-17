/*
 * 作成日: 2005/12/25
 *
 */
package jp.starlogic.servicemanager.service;

import java.util.Calendar;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import jp.starlogic.servicemanager.abst.AbstractCronTypeService;
import jp.starlogic.util.datetime.CalendarUtil;
import jp.starlogic.util.datetime.DateUtil;
import jp.starlogic.util.datetime.UtilCalendar;

import org.escafe.buri.common.util.ScriptProcessor;
import org.seasar.framework.container.S2Container;

public class CronTypeService extends AbstractCronTypeService {
    private String executeStr = "";
    private S2Container container;

    private Integer exeYear = null;
    private Integer exeMonth = null;
    private Integer exeDay = null;
    private TreeSet<Integer> exeWeek = new TreeSet<Integer>();
    private Integer exeHour = null;
    private Integer exeMinute = null;
    private Integer exeSecond = null;

    private Boolean lastDayOfMonth = Boolean.FALSE;
    private Boolean nearestWeekday = Boolean.FALSE;
    private Boolean nearestHoliday = Boolean.FALSE;
    private Boolean firstDayOfMonth = Boolean.FALSE;

    public Calendar createNext(Calendar now) {
        UtilCalendar nowCal = (UtilCalendar) UtilCalendar.getInstance();
        nowCal.setTimeInMillis(now.getTimeInMillis());
        return createNextRunCalender(nowCal);
    }

    @Override
    protected UtilCalendar createNextRunCalender(UtilCalendar lastRun) {
        UtilCalendar result = (UtilCalendar) lastRun.clone();
        UtilCalendar prev = (UtilCalendar) result.clone();
        result.clearMilliSecond();
        boolean changeSecToHour = false;
        if (exeSecond != null) {
            result.setSecond(exeSecond.intValue());
            if (DateUtil.compare(prev, result) >= 0) {
                result.addMinute(1);
            }
            changeSecToHour = true;
        }
        if (exeMinute != null) {
            result.setMinute(exeMinute.intValue());
            if (DateUtil.compare(prev, result) >= 0) {
                result.addHourOfDay(1);
            }
            changeSecToHour = true;
        }
        if (exeHour != null) {
            result.setHourOfDay(exeHour.intValue());
            if (DateUtil.compare(prev, result) >= 0) {
                result.addDayOfMonth(1);
            }
            changeSecToHour = true;
        }

        boolean isChangeDayOfMonth = false;
        if (exeWeek.size() > 0) {
            isChangeDayOfMonth = updateWeek(result);
        } else if (lastDayOfMonth.booleanValue()) {
            CalendarUtil.setActualMaximum(result, Calendar.DAY_OF_MONTH);
            if (DateUtil.compare(prev, result) >= 0) {
                result.addMonth(1);
                CalendarUtil.setActualMaximum(result, Calendar.DAY_OF_MONTH);
            }
            isChangeDayOfMonth = true;
        } else if (firstDayOfMonth.booleanValue()) {
            CalendarUtil.setActualMinimum(result, Calendar.DAY_OF_MONTH);
            if (DateUtil.compare(prev, result) >= 0) {
                result.addMonth(1);
            }
            isChangeDayOfMonth = true;
        } else if (nearestWeekday.booleanValue()) {
            result.setDayOfMonth(prev.getDayOfMonth());
            result.nextWeekday();
            isChangeDayOfMonth = true;
        } else if (nearestHoliday.booleanValue()) {
            result.setDayOfMonth(prev.getDayOfMonth());
            result.nextHoliday();
            isChangeDayOfMonth = true;
        } else if (exeDay != null) {
            result.setDayOfMonth(exeDay.intValue());
            isChangeDayOfMonth = true;
        } else if ((changeSecToHour == true) && (DateUtil.compare(prev, result) >= 0)) {
            result.addDayOfMonth(1);
        }
        if ((isChangeDayOfMonth == true) && (DateUtil.compare(prev, result) >= 0)) {
            result.addMonth(1);
        }

        if (exeMonth != null) {
            result.setMonth(exeMonth.intValue());
            if (DateUtil.compare(prev, result) >= 0) {
                result.addYear(1);
            }
        }

        if (exeYear != null) {
            result.setMonth(exeYear.intValue());
        }

        return result;
    }

    protected boolean updateWeek(UtilCalendar lastRun) {
        boolean isChangeDayOfMonth = false;
        int weekNo = lastRun.get(Calendar.DAY_OF_WEEK);
        SortedSet<Integer> st = exeWeek.tailSet(new Integer(weekNo + 1));
        int updateVal = 0;
        if ((st != null) && (st.size() != 0)) {
            updateVal = st.first().intValue();
            int updateCount = ((updateVal + 7 - weekNo) % 7);
            if (updateCount == 0) {
                updateCount = 7;
            }
            lastRun.addDayOfMonth(updateCount);
            isChangeDayOfMonth = true;
        } else if (exeWeek.size() != 0) {
            updateVal = exeWeek.first().intValue();
            int updateCount = ((updateVal + 7 - weekNo) % 7);
            if (updateCount == 0) {
                updateCount = 7;
            }
            lastRun.addDayOfMonth(updateCount);
            isChangeDayOfMonth = true;
        }
        return isChangeDayOfMonth;
    }

    protected int updateNextRunField(UtilCalendar lastRun, int field, TreeSet<Integer> tgtSet) {
        lastRun = (UtilCalendar) lastRun.clone();
        int fieldVal = lastRun.get(field);
        SortedSet<Integer> st = tgtSet.tailSet(new Integer(fieldVal));
        if ((st != null) && (st.size() != 0)) {
            int updateVal = st.first().intValue();
            lastRun.set(field, updateVal);
        } else if (tgtSet.size() != 0) {
            int updateVal = tgtSet.first().intValue();
            CalendarUtil.setActualMaximum(lastRun, field);
            lastRun.add(field, 1);
            lastRun.set(field, updateVal);
        }
        return lastRun.get(field);
    }

    @Override
    protected void executeCron() {
        ScriptProcessor processor = new ScriptProcessor();
        processor.getValue(executeStr, container);
    }

    public S2Container getContainer() {
        return container;
    }

    public void setContainer(S2Container container) {
        this.container = container;
    }

    public String getExecuteStr() {
        return executeStr;
    }

    public void setExecuteStr(String executeStr) {
        this.executeStr = executeStr;
    }

    public Boolean getFirstDayOfMonth() {
        return firstDayOfMonth;
    }

    public void setFirstDayOfMonth(Boolean fastDayOfMonth) {
        this.firstDayOfMonth = fastDayOfMonth;
    }

    public Boolean getLastDayOfMonth() {
        return lastDayOfMonth;
    }

    public void setLastDayOfMonth(Boolean lastDayOfMonth) {
        this.lastDayOfMonth = lastDayOfMonth;
    }

    public Boolean getNearestHoliday() {
        return nearestHoliday;
    }

    public void setNearestHoliday(Boolean nearestHoliday) {
        this.nearestHoliday = nearestHoliday;
    }

    public Boolean getNearestWeekday() {
        return nearestWeekday;
    }

    public void setNearestWeekday(Boolean nearestWeekday) {
        this.nearestWeekday = nearestWeekday;
    }

    public int getExeDay() {
        return exeDay.intValue();
    }

    public void setExeDay(int exeDay) {
        this.exeDay = new Integer(exeDay);
    }

    public int getExeHour() {
        return exeHour.intValue();
    }

    public void setExeHour(int exeHour) {
        this.exeHour = new Integer(exeHour);
    }

    public int getExeMinute() {
        return exeMinute.intValue();
    }

    public void setExeMinute(int exeMinute) {
        this.exeMinute = new Integer(exeMinute);
    }

    public int getExeMonth() {
        return exeMonth.intValue();
    }

    public void setExeMonth(int exeMonth) {
        this.exeMonth = new Integer(exeMonth);
    }

    public int getExeSecond() {
        return exeSecond.intValue();
    }

    public void setExeSecond(int exeSecond) {
        this.exeSecond = new Integer(exeSecond);
    }

    public TreeSet<Integer> getExeWeek() {
        return exeWeek;
    }

    public void setExeWeek(List<Integer> weekList) {
        this.exeWeek.addAll(weekList);
    }

    public void setExeWeek(TreeSet<Integer> exeWeek) {
        this.exeWeek = exeWeek;
    }

    public Integer getExeYear() {
        return exeYear;
    }

    public void setExeYear(Integer exeYear) {
        this.exeYear = exeYear;
    }

}
