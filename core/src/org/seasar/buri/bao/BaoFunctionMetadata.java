/*
 * ì¬“ú: 2005/12/31
 *
 */
package org.seasar.buri.bao;

import java.util.List;


public interface BaoFunctionMetadata {
    List getArgName();
    void setArgName(List args);
    BaoMetadata getBaoMetadata();
    void setActivityName(String activityName);
    String getActivityName();
    void setBaoMetadata(BaoMetadata metadata);
}
