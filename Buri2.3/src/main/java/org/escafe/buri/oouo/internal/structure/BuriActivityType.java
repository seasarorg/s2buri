/*
 * 作成日: 2006/03/20
 *
 */
package org.escafe.buri.oouo.internal.structure;

import java.util.ArrayList;
import java.util.List;

import org.escafe.buri.oouo.internal.structure.util.ExtentedAttributeUtil;

public class BuriActivityType {
    private String id;
    private String name;
    private BuriPerformerType performer;
    private BuriStartModeType startMode;
    private BuriFinishModeType finishMode;
    private List toolList = new ArrayList();
    private BuriSplitType split;
    private BuriJoinType join;
    private Boolean isSplitAnd = null;
    private Boolean isJoinAnd = null;
    private List ExtentedAttribute = new ArrayList();
    private BuriWorkflowProcessType process;
    private BuriActivitySetType activitySet;
    private BuriActivityLimitType limitType;
    
    private String subFlow = null;
    private String blockActivity = null;

    public final static String OOUOTHIS = "Activity";
    
    public String getId() {
        return id;
    }
    public final static String setId_ATTRI = "Id";
    public void setId(String id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    public final static String setName_ATTRI = "Name";
    public void setName(String name) {
        this.name = name;
    }
    
    public BuriPerformerType getPerformer() {
        return performer;
    }
    public String getParticipantName() {
        BuriParticipantType participantType = process.getParticipantById(performer.getId());
        String participantName = participantType.getName();
        return participantName;
    }
    public String getParticipantType() {
        BuriParticipantType participantType = process.getParticipantById(performer.getId());
        return participantType.getType();
    }
    
    public final static String setPerformer_ELEMENT = "Performer";
    public void setPerformer(BuriPerformerType performer) {
        this.performer = performer;
    }
    
    public BuriStartModeType getStartMode() {
        return startMode;
    }
    public final static String setStartMode_ELEMENT = "StartMode";
    public void setStartMode(BuriStartModeType startMode) {
        this.startMode = startMode;
    }
    public boolean isStartModeManual() {
        if(startMode==null) {
            return false;
        }
        return startMode.isManual();
    }
    
    public BuriFinishModeType getFinishMode() {
        return finishMode;
    }
    public final static String setFinishMode_ELEMENT = "FinishMode";
    public void setFinishMode(BuriFinishModeType finishMode) {
        this.finishMode = finishMode;
    }
    public boolean isFinishModeManual() {
        if(finishMode==null) {
            return false;
        }
        return finishMode.isManual();
    }

    public final static String addTool_ELEMENT = "Tool";
    public void addTool(BuriToolType tool) {
        this.toolList.add(tool);
    }
    public List getTools() {
        return toolList;
    }
    
    public static final String addExtendedAttribute_ELEMENT = "ExtendedAttribute";
    public void addExtendedAttribute(BuriExtendedAttributeType attri) {
        ExtentedAttribute.add(attri);
    }
    
    public List getExtendedAttributeList() {
        return ExtentedAttribute;
    }
    
    public BuriSplitType getSplit() {
        return split;
    }
    public static final String setSplit_ELEMENT = "Split";
    public void setSplit(BuriSplitType split) {
        this.split = split;
    }
    public boolean isSplitAnd() {
        if(isSplitAnd==null) {
            isSplitAnd = Boolean.FALSE;
            if(split != null && split.isAnd()) {
                isSplitAnd = Boolean.TRUE;
            }
        }
        return isSplitAnd.booleanValue();
    }
    
    public BuriJoinType getJoin() {
        return join;
    }
    public static final String setJoin_ELEMENT = "Join";
    public void setJoin(BuriJoinType join) {
        this.join = join;
    }
    public boolean isJoinAnd() {
        if(isJoinAnd==null) {
            isJoinAnd = Boolean.FALSE;
            if(join != null && join.isAnd()) {
                isJoinAnd = Boolean.TRUE;
            }
        }
        return isJoinAnd.booleanValue();
    }
    
    public boolean isNoJoin() {
        if(ExtentedAttributeUtil.getExtendedAttribute(ExtentedAttribute,"NOXORJOIN") == null) {
            return false;
        }
        return true;
    }
    
    public boolean isXorJoin() {
        if(ExtentedAttributeUtil.getExtendedAttribute(ExtentedAttribute,"XORJOIN") == null) {
            return false;
        }
        return true;
    }
    
    public String getSubFlow() {
        return subFlow;
    }

    public static final String setSubFlow_ELEMENT = "SubFlow";
    public static final String setSubFlow_ATTRI = "Id";
    public void setSubFlow(String subFlow) {
        this.subFlow = subFlow;
    }

    public String getBlockActivity() {
        return blockActivity;
    }

    public static final String setBlockActivity_ELEMENT = "BlockActivity";
    public static final String setBlockActivity_ATTRI = "BlockId";
    public void setBlockActivity(String blockActivity) {
        this.blockActivity = blockActivity;
    }
    
    public static final String setupEnd_OOUOFIN = "";
    public void setupEnd(Object parent) {
        if(parent instanceof BuriWorkflowProcessType) {
            this.process = (BuriWorkflowProcessType)parent;
        } else if(parent instanceof BuriActivitySetType) {
            this.activitySet = (BuriActivitySetType)parent;
        } else{
            assert false;
        }
    }
    
    public BuriWorkflowProcessType getWorkflowProcess() {
        return process;
    }
    
    public BuriActivitySetType getActivitySet() {
        return activitySet;
    }

    public String getLimit() {
        if(limitType==null) {
            return null;
        }
        return limitType.getLimit();
    }
    
    public BuriActivityLimitType getLimitType() {
        return limitType;
    }

    public static final String setLimitType_ELEMENT = "Limit";
    public void setLimitType(BuriActivityLimitType limit) {
        this.limitType = limit;
    }

    public String toString() {
        StringBuffer buff = new StringBuffer("\n[");
        buff.append("id=").append(id);
        buff.append("/name=").append(name);
        buff.append("/limitType=").append(limitType);
        buff.append("/performer=").append(performer);
        buff.append("/subFlow=").append(subFlow);
        buff.append("/blockActivity=").append(blockActivity);
        buff.append("/").append(startMode);
        buff.append("/").append(finishMode);
        buff.append("/join=").append(join);
        buff.append("\n    /split=").append(split);
        buff.append("\n    /tool=").append(toolList);
        buff.append("\n    /ExtentedAttribute=").append(ExtentedAttribute);
        buff.append("]\n");
        return buff.toString();
    }


}
