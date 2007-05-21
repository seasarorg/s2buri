/*
 * 作成日: 2005/12/31
 *
 */
package org.escafe.buri.bao;

import java.util.List;

public interface BaoFunctionMetadata {

    List<String> getArgName();

    void setArgName(List<String> args);

    BaoMetadata getBaoMetadata();

    void setActivityName(String activityName);

    String getActivityName();

    List<String> getActivityNames();

    void setActivityNames(List<String> activityNames);

    void addActivityNames(String actName);

    void setBaoMetadata(BaoMetadata metadata);

    List<String> getValidateAction();

    void setValidateAction(List<String> validateAction);
}
