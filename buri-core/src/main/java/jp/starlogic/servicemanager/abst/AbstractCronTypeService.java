/*
 * 作成日: 2005/12/25
 *
 */
package jp.starlogic.servicemanager.abst;

import jp.starlogic.util.datetime.DateUtil;
import jp.starlogic.util.datetime.UtilCalendar;

public abstract class AbstractCronTypeService extends AbstractGetRunService {
    private UtilCalendar lastRun = null;
    private UtilCalendar nextRun = null;

    @Override
    public void initService() {
        nextRunUpdate();
    }

    public boolean canService() {
        if (nextRun == null) {
            return false;
        }
        if (DateUtil.compare(UtilCalendar.getInstance(), nextRun) >= 0) {
            return true;
        }
        return false;
    }

    public void execute() {
        executeCron();
        lastRun = nextRun;
        nextRun = null;
        nextRunUpdate();
    }

    protected abstract void executeCron();

    public void nextRunUpdate() {
        if (lastRun == null) {
            lastRun = (UtilCalendar) UtilCalendar.getInstance();
            lastRun.clearMilliSecond();
        }
        UtilCalendar argCalendar = (UtilCalendar) lastRun.clone();
        nextRun = createNextRunCalender(argCalendar);
    }

    protected abstract UtilCalendar createNextRunCalender(UtilCalendar lastRun);

    public UtilCalendar getLastRun() {
        return lastRun;
    }

    public void setLastRun(UtilCalendar lastRun) {
        this.lastRun = lastRun;
    }

    public UtilCalendar getNextRun() {
        return nextRun;
    }

    public void setNextRun(UtilCalendar nextRun) {
        this.nextRun = nextRun;
    }

}
