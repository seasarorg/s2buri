import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.seasar.buri.engine.BuriPath;
import org.seasar.buri.engine.BuriUserContext;
import org.seasar.buri.engine.BuriSystemContext;
import org.seasar.buri.oouo.internal.structure.BuriWorkflowProcessType;
import org.seasar.buri.util.packages.BranchWalker;
import org.seasar.buri.util.packages.abst.AbstBuriExecProcess;
import org.seasar.coffee.script.Script;


public void setup(BuriWorkflowProcessType process) {
    conditionScript = scriptFactory.getScript("ognl");
    super.setup(process);
}

<#assign activityMap = process.getActivityById()>
<#assign keys = activityMap?keys>

<#list keys as key>
	<#assign activity = activityMap[key]>
    
	<#assign activityId = activity.getId()>
//${activity.getName()}

public void ${activityId}(BuriUserContext context,BuriPath nowPath) {
    
}

public void ${activityId}_start(BuriSystemContext sysContext,BranchWalker walker) {
    walker = walker.moveNext("${activity.getName()}","${activityId}");
    ${activityId}_process(sysContext,walker);
}

public void ${activityId}_process(BuriSystemContext sysContext,BranchWalker walker) {
	<#list activity.getTools() as tool>
    {
    	<#assign script = buriComponentUtil.getJavaProcessCode(tool,activity)>
    	${script}
    }
    </#list>
    <#if activity.isFinishModeManual()>
    return;
    <#else>
    ${activityId}_next(sysContext,walker);
    </#if>
}

public void ${activityId}_next(BuriSystemContext sysContext,BranchWalker walker) {
	<#assign transition = process.getRefTransition(activityId)>
	<#if transition?size == 0>
	return;
	<#elseif transition?size == 1>
		<#assign nextActId = transition[0].getTo()>
    startActivity("${nextActId}",sysContext,walker);
    <#else>
    List results = new ArrayList();
    Boolean oneResult;
    
    	<#list transition as oneTrans>
    oneResult = conditionCheck("${oneTrans.getId()}","${oneTrans.getConditionStr()?j_string}",sysContext,walker);
    if(oneResult.booleanValue()) {
        results.add("${oneTrans.getTo()}");
    }
    	</#list>
    
    if(results.size() == 0) {
        //エラー
    } else if(results.size() == 1) {
        
    } else {
        //XORの時エラー
    }
    
    Iterator ite = results.iterator();
    while(ite.hasNext()) {
        String key = ite.next().toString();
	    startActivity(key,sysContext,walker);
    }
	</#if>
}

	<#list transition as oneTrans>
		<#if oneTrans.hasCondition() >
public Boolean ${oneTrans.getId()}(BuriUserContext context,String condition) {
    return Boolean.FALSE;
}

public Object ${oneTrans.getId()}_condition(BuriSystemContext sysContext,BranchWalker walker) {
    Object result = conditionScript.eval(null,"${oneTrans.getConditionStr()?j_string}",sysContext.getUserContext());
    return result;
}
		</#if>
	</#list>


    <#if activity.isFinishModeManual()>
public void ${activityId}_restart(BuriSystemContext sysContext,BranchWalker walker) {
    walker = walker.moveNext("${activity.getName()}","${activityId}");
    ${activityId}_next(sysContext,walker);
}
    
    </#if>
	

</#list>


