/*
 * 作成日: 2005/12/31
 *
 */
package org.escafe.buri.bao;

import java.util.List;


public interface BaoFunctionMetadata {
    List getArgName();
    void setArgName(List args);
    BaoMetadata getBaoMetadata();
    void setActivityName(String activityName);
    String getActivityName();
    List getActivityNames();
    void setActivityNames(List activityNames);
    void addActivityNames(String actName);
    void setBaoMetadata(BaoMetadata metadata);
    List getValidateAction();
    void setValidateAction(List validateAction);
}
