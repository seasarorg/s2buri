/*
 * 作成日: 2006/04/03
 *
 */
package org.escafe.buri.oouo.internal.structure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.escafe.buri.common.util.MultiValueMap;

public class BuriActivitySetType {
    private String id;
    private Map activityById = new HashMap();
    private MultiValueMap activityByName = new MultiValueMap();
    private List transition = new ArrayList();
    private MultiValueMap transitionByFrom = new MultiValueMap();
    private Set transToId = new HashSet();
    private List startActivitys = new ArrayList();

    private BuriWorkflowProcessType processType = null;
    public final static String OOUOTHIS = "ActivitySet";
    
    public final static String setId_ATTRI = "Id";
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    

    public static final String addActivity_ELEMENT = "Activity";
    public void addActivity(BuriActivityType act) {
        activityById.put(act.getId(),act);
        activityByName.put(act.getName(),act);
    }
    
    public BuriActivityType getActivityById(String actId) {
        return (BuriActivityType)activityById.get(actId);
    }
    
    public List getActivityByName(String actName) {
        return activityByName.get(actName);
    }

    public static final String addTransition_ELEMENT = "Transition";
    public void addTransition(BuriTransitionType transition) {
        this.transition.add(transition);
        transitionByFrom.put(transition.getFrom(),transition);
        transToId.add(transition.getTo());
    }
    
    public List getRefTransition(String actId) {
        return transitionByFrom.get(actId);
    }
    
    public static String setupEnd_OOUOFIN = "";
    public void setupEnd(BuriWorkflowProcessType processType) {
        this.processType = processType;
        updateStartActivites();
    }
    
    public BuriWorkflowProcessType getProcessType() {
        return processType;
    }
    public void setProcessType(BuriWorkflowProcessType processType) {
        this.processType = processType;
    }
    
    
    protected void updateStartActivites() {
        List actIds = new ArrayList(activityById.keySet());
        actIds.removeAll(transToId);
        Iterator ite = actIds.iterator();
        while(ite.hasNext()) {
            String id = (String)ite.next();
            BuriActivityType act = getActivityById(id);
            startActivitys.add(act);
        }
    }
    
    public String toString() {
        StringBuffer buff = new StringBuffer("\n  [");
        buff.append("    id=").append(id);
        buff.append("    -----ACTIVITY----------\n");
        buff.append("    /activity=").append(activityById).append("\n");
        buff.append("    -----ACTIVITY SET transition----------\n");
        buff.append("    /transition=").append(transition).append("\n");
        buff.append("    -----ACTIVITY SET START ACTIVITY----------\n");
        buff.append("    /").append(startActivitys).append("\n");
        buff.append("  ]\n\n");
        return buff.toString();
    }
    

}
