/*
 * çÏê¨ì˙: 2005/10/02
 *
 */
package org.seasar.buri.xpdl.util.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.xmlbeans.XmlObject;
import org.seasar.buri.rule.ToolExecuteRule;
import org.seasar.buri.xpdl.util.ActivityTagSelect;
import org.seasar.buri.xpdl.util.ToolTagSelect;
import org.seasar.buri.xpdl.util.ToolUtil;
import org.seasar.buri.xpdl.util.XPathUtil;
import org.seasar.framework.container.S2Container;
import org.wfmc.x2002.xpdl10.ToolDocument.Tool;

public class ToolUtilImpl implements ToolUtil {
    private S2Container container;
    private XPathUtil pathUtil;
    private ToolExecuteRule toolExecuteRule;
    
    public void processActivity(ActivityTagSelect tagSelect,S2Container container,Object root,Map contextData) {
        List tools = getTools(tagSelect);
        Iterator ite = tools.iterator();
        while(ite.hasNext()) {
            ToolTagSelect tool = (ToolTagSelect)ite.next();
            toolExecuteRule.executeTool(container,tagSelect,tool,root,contextData);
        }
    }

    public List getTools(ActivityTagSelect tagSelect) {
        String xpath = getToolPath();
        XmlObject[] tools = (XmlObject[])pathUtil.getXmlObjArrayFromXPath(tagSelect.getActivity(),xpath);
        List resultList = convToolArrayToLost(tagSelect,tools);
        return resultList;
    }
    
    protected List convToolArrayToLost(ActivityTagSelect activityTagSelect,XmlObject[] tools) {
        List toolTagList = new ArrayList();
        for(int i=0; i < tools.length ; i++) {
            ToolTagSelect tagSelect = convToolToToolTagSelect(activityTagSelect,(Tool)tools[i]);
            toolTagList.add(tagSelect);
        }
        return toolTagList;
    }
    
    protected ToolTagSelect convToolToToolTagSelect(ActivityTagSelect activityTagSelect,Tool tool) {
        ToolTagSelect tagSelect = (ToolTagSelect)container.getComponent(ToolTagSelect.class);
        tagSelect.setActivityTagSelect(activityTagSelect);
        tagSelect.setTool(tool);
        return tagSelect;
    }
    
    protected String getToolPath() {
        String xpath = "./xpdl:Implementation/xpdl:Tool";
        return xpath;
    }

    public S2Container getContainer() {
        return container;
    }

    public void setContainer(S2Container container) {
        this.container = container;
    }

    public XPathUtil getPathUtil() {
        return pathUtil;
    }

    public void setPathUtil(XPathUtil pathUtil) {
        this.pathUtil = pathUtil;
    }

    public ToolExecuteRule getToolExecuteRule() {
        return toolExecuteRule;
    }

    public void setToolExecuteRule(ToolExecuteRule toolExecuteRule) {
        this.toolExecuteRule = toolExecuteRule;
    }

}
