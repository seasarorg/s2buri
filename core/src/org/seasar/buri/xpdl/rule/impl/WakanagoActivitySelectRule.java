/*
 * ì¬“ú: 2005/06/02
 *
 */
package org.seasar.buri.xpdl.rule.impl;

import java.util.List;

import org.seasar.buri.engine.BuriParticipant;
import org.seasar.buri.engine.BuriPath;
import org.seasar.buri.exception.select.BuriManySelectActivityException;
import org.seasar.buri.exception.select.BuriNotSelectedActivityException;
import org.seasar.buri.xpdl.rule.ActivitySelectRule;
import org.seasar.buri.xpdl.util.ActivityTagSelect;
import org.seasar.buri.xpdl.util.ActivityTagSelectUtil;
import org.seasar.buri.xpdl.util.ParticipantUtil;
import org.wfmc.x2002.xpdl10.ActivityDocument.Activity;


/**
 * @author makotan
 *
 */
public class WakanagoActivitySelectRule implements ActivitySelectRule {
    private ParticipantUtil participantUtil;
    private ActivityTagSelectUtil selectUtil;

    /* (”ñ Javadoc)
     * @see org.seasar.buri.xpdl.rule.ActivitySelectRule#selectActivity(org.wfmc.x2002.xpdl10.ActivityDocument.Activity[], org.seasar.buri.engine.BuriPath, org.seasar.buri.engine.BuriParticipant)
     */
    public ActivityTagSelect selectActivity(Activity[] activitys, BuriPath path, BuriParticipant participant) {
        Activity resultActivity = null;
        if(activitys!=null) { 
	        if(activitys.length==1) {
	            resultActivity = selectOneActivity(activitys[0], path, participant);
	        }else if(activitys.length > 1) {
	            resultActivity = selectManyActivity(activitys, path, participant);
	        }
        }
        if(resultActivity==null) {
            throw new BuriNotSelectedActivityException(path,participant);
        }
        return selectUtil.convTagSelect(resultActivity,path);
    }
    
    protected Activity selectOneActivity(Activity activity, BuriPath path, BuriParticipant participant) {
        return activity;
    }
    
    protected Activity selectManyActivity(Activity[] activitys, BuriPath path, BuriParticipant participant) {
        Activity resultActivity = null;
        List actList = selectUtil.convTagSelectList(activitys);
        List findList = participantUtil.findParticipant(path,actList,participant);
        if(findList.size() == 1 ) {
            ActivityTagSelect select = (ActivityTagSelect)findList.get(0);
            resultActivity = selectOneActivity(select.getActivity(), path, participant);
        } else if(findList.size() > 1){
            throw new BuriManySelectActivityException(path,participant);
        }
        return resultActivity;
    }

    public ParticipantUtil getParticipantUtil() {
        return participantUtil;
    }

    public void setParticipantUtil(ParticipantUtil participantUtil) {
        this.participantUtil = participantUtil;
    }

    public ActivityTagSelectUtil getSelectUtil() {
        return selectUtil;
    }

    public void setSelectUtil(ActivityTagSelectUtil selectUtil) {
        this.selectUtil = selectUtil;
    }

}
