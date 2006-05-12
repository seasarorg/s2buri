/*
 * çÏê¨ì˙: 2005/11/18
 *
 */
package org.seasar.buri.xpdl.util.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.seasar.buri.engine.BuriPath;
import org.seasar.buri.xpdl.util.ActivityTagSelect;
import org.seasar.buri.xpdl.util.ActivityTagSelectUtil;
import org.seasar.framework.container.S2Container;
import org.wfmc.x2002.xpdl10.ActivityDocument.Activity;

public class ActivityTagSelectUtilImpl implements ActivityTagSelectUtil {
    private S2Container container;
    
    public ActivityTagSelect convTagSelect(Activity activity) {
        ActivityTagSelect tagSelect = (ActivityTagSelect)container.getComponent(ActivityTagSelect.class);
        tagSelect.setActivityLevel(0);
        tagSelect.setActivity(activity);
        return tagSelect;
    }

    public ActivityTagSelect convTagSelect(Activity activity, BuriPath path) {
        ActivityTagSelect tagSelect = convTagSelect(activity);
        tagSelect.setBuriPath(path);
        return tagSelect;
    }
    
    public List convTagSelectList(List actList) {
        List actTagList = new ArrayList();
        Iterator ite = actList.iterator();
        while(ite.hasNext()) {
            actTagList.add(convTagSelect((Activity)ite.next()));
        }
        return actTagList;
    }
    
    public List convTagSelectList(Activity[] activitys) {
        List actTagList = new ArrayList();
        for(int i=0; i < activitys.length ; i++) {
            actTagList.add(convTagSelect(activitys[i]));
        }
        return actTagList;
    }


    public S2Container getContainer() {
        return container;
    }

    public void setContainer(S2Container container) {
        this.container = container;
    }

}
