/*
 * çÏê¨ì˙: 2005/10/02
 *
 */
package org.seasar.buri.xpdl.util.impl;

import java.util.List;
import java.util.Map;


import org.apache.xmlbeans.XmlObject;
import org.seasar.buri.engine.BuriPath;
import org.seasar.buri.xpdl.util.ActivityTagSelect;
import org.seasar.buri.xpdl.util.ExtendedAttributeUtil;
import org.seasar.buri.xpdl.util.TransitionUtil;
import org.wfmc.x2002.xpdl10.ActivityDocument.Activity;
import org.wfmc.x2002.xpdl10.FinishModeDocument.FinishMode;

public class ActivityTagSelectImpl extends AbstractTagSelect implements ActivityTagSelect{
    
    private Activity act;
    private int level;
    private TransitionUtil transitionUtil;
    private ExtendedAttributeUtil extendedAttributeUtil;

    protected final String split = "Split";
    protected final String join = "Join";
    protected final String and = "AND";
    protected final String xor = "XOR";
    
    private BuriPath buriPath;

    public Activity getActivity() {
        return act;
    }

    public void setActivity(Activity act) {
        this.act = act;
        
    }

    public void setActivityLevel(int level) {
        this.level = level;
    }

    public int getActivityLevel() {
        return level;
    }
    /* (îÒ Javadoc)
     * @see org.seasar.buri.xpdl.util.ActivityTagSelect#getXorSplitTransitions(org.wfmc.x2002.xpdl10.ActivityDocument.Activity)
     */
    public List getXorSplitTransitions() {
        return transitionUtil.getTransitions(this,split , xor);
    }

    /* (îÒ Javadoc)
     * @see org.seasar.buri.xpdl.util.ActivityTagSelect#getAndSplitTransitions(org.wfmc.x2002.xpdl10.ActivityDocument.Activity)
     */
    public List getAndSplitTransitions() {
        return transitionUtil.getTransitions(this,split , and);
    }

    /* (îÒ Javadoc)
     * @see org.seasar.buri.xpdl.util.ActivityTagSelect#getXorJoinTransitions(org.wfmc.x2002.xpdl10.ActivityDocument.Activity)
     */
    public List getXorJoinTransitions() {
        return transitionUtil.getJoinTransitions(this,xor);
    }

    /* (îÒ Javadoc)
     * @see org.seasar.buri.xpdl.util.ActivityTagSelect#getAndJoinTransitions(org.wfmc.x2002.xpdl10.ActivityDocument.Activity)
     */
    public List getAndJoinTransitions() {
        return transitionUtil.getJoinTransitions(this,and);
    }
    
    
    /* (îÒ Javadoc)
     * @see org.seasar.buri.xpdl.util.ActivityTagSelect#isSplitAnd()
     */
    public boolean isSplitAnd() {
        return transitionUtil.isSplitAnd(this);
    }

    /* (îÒ Javadoc)
     * @see org.seasar.buri.xpdl.util.ActivityTagSelect#isSplitXor()
     */
    public boolean isSplitXor() {
        return transitionUtil.isSplitXor(this);
    }

    /* (îÒ Javadoc)
     * @see org.seasar.buri.xpdl.util.ActivityTagSelect#isJoinAnd()
     */
    public boolean isJoinAnd() {
        return transitionUtil.isJoinAnd(this);
    }

    /* (îÒ Javadoc)
     * @see org.seasar.buri.xpdl.util.ActivityTagSelect#isJoinXor()
     */
    public boolean isJoinXor() {
        return transitionUtil.isJoinXor(this);
    }
    
    public boolean isProcessedXorJoin() {
        Map attr = extendedAttributeUtil.get(this);
        if(attr.containsKey("NOJOIN")) {
            return false;
        }
        return true;
    }
    
    public boolean isEmulateXorJoin() {
        Map attr = extendedAttributeUtil.get(this);
        if(attr.containsKey("XORJOIN")) {
            return true;
        }
        return false;
    }


    public boolean isFinishAuto() {
        FinishMode finish = act.getFinishMode();
        if(finish.getAutomatic() != null) {
            return true;
        }
        return false;
    }

    public boolean isFinishManual() {
        FinishMode finish = act.getFinishMode();
        if(finish.getManual() != null) {
            return true;
        }
        return false;
    }

    public BuriPath getBuriPath() {
        return buriPath;
    }

    public void setBuriPath(BuriPath buriPath) {
        this.buriPath = buriPath;
    }
    
    public XmlObject getXmlObject() {
        return getActivity();
    }
    
    public XmlObject getCurrentXmlObject() {
        return getActivity();
    }

    public ExtendedAttributeUtil getExtendedAttributeUtil() {
        return extendedAttributeUtil;
    }

    public void setExtendedAttributeUtil(ExtendedAttributeUtil extendedAttributeUtil) {
        this.extendedAttributeUtil = extendedAttributeUtil;
    }

    public TransitionUtil getTransitionUtil() {
        return transitionUtil;
    }

    public void setTransitionUtil(TransitionUtil transitionUtil) {
        this.transitionUtil = transitionUtil;
    }
    
    public String toString() {
        return "ActivityTagSelectImpl"+buriPath;
    }
}
