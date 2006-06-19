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
	<#assign transition = process.getRefToTransition(activityId)>
	//${transition?size}
	<#if transition?size gt 1>
		<#if activity.isNoJoin()>
		<#else>
		    <#if activity.isJoinAnd()>
    boolean canExec = joinAndFlow(sysContext,walker,"${activity.getName()}","${activityId}");
    if(canExec == false) {
    	return;
    }
    		<#else>
    joinXorFlow(sysContext,walker,"${activity.getName()}","${activityId}");
    		</#if>
    	</#if>
    </#if>
	<#if transition?size == 1>
		<#if activity.isXorJoin()>
    joinXorFlow(sysContext,walker,"${activity.getName()}","${activityId}");
		</#if>
	</#if>
    walker = walker.moveNext("${activity.getName()}","${activityId}");
    startActivity(sysContext,walker);
    ${activityId}_process(sysContext,walker);
}

public void ${activityId}_process(BuriSystemContext sysContext,BranchWalker walker) {
	<#list activity.getTools() as tool>
	try {
	    	<#assign script = buriComponentUtil.getJavaProcessCode("sysContext.getContainer()",tool,activity)>
	    	${script}
	} catch(RuntimeException ex) {
		sysContext.setException(ex);
	}
    </#list>
    <#if activity.isFinishModeManual()>
    exitFlow(sysContext,walker);
    return;
    <#else>
    ${activityId}_next(sysContext,walker);
    </#if>
}

public void ${activityId}_next(BuriSystemContext sysContext,BranchWalker walker) {
	<#assign transition = process.getRefFromTransition(activityId)>
	<#if transition?size == 0>
	return;
	<#elseif transition?size == 1>
		<#assign nextActId = transition[0].getTo()>
    startActivity("${nextActId}",sysContext,walker);
    <#else>
    List results = new ArrayList();
    Boolean oneResult;
    String othrewise = null;
    String toException = null;
    
    	<#list transition as oneTrans>
			<#if oneTrans.hasCondition() >
				<#assign conditionType = oneTrans.getCondition() >
				<#assign transType = conditionType.getType() >
				<#if transType == "CONDITION" >
    oneResult = conditionCheck("${oneTrans.getId()}","${conditionType.getCondition()?j_string}",sysContext,walker);
    if(oneResult.booleanValue()) {
        results.add("${oneTrans.getTo()}");
    }
    			</#if>
				<#if transType == "EXCEPTION" >
	if(sysContext.getException() != null) {
		Throwable ex = sysContext.getException();
		if(ex instanceof org.seasar.coffee.script.exception.ScriptExecuteException) {
			ex = ex.getCause();
		}
		if(ex instanceof ${conditionType.getCondition()}) {
 	        toException = "${oneTrans.getTo()}";
		}
	}
    			</#if>
    			<#if transType == "OTHERWISE" >
    othrewise = "${oneTrans.getTo()}";
    			</#if>
    		<#else>
    results.add("${oneTrans.getTo()}");
    		</#if>
    	</#list>
	if( results.size()==0 && othrewise != null) {
		results.add(othrewise);
	}
	
	if(sysContext.getException() != null) {
		if(toException != null) {
			results.clear();
			results.add(toException);
		} else {
			throw sysContext.getException();
		}
	}
	
    results = filterNextActivity(sysContext,walker,results);
    
    if(results.size() == 0) {
        noSelectActivity(sysContext,walker);
    } else if(results.size() == 1) {
        <#if activity.isSplitAnd()>
        oneSelectActivitySplitAnd(sysContext,walker);
    	<#else>
        oneSelectActivitySplitXor(sysContext,walker);
	    </#if>
    } else {
    	<#if activity.isSplitAnd()>
        manySelectActivitySplitAnd(sysContext,walker);
    	<#else>
        manySelectActivitySplitXor(sysContext,walker);
    	</#if>
    }

        <#if activity.isSplitAnd()>
    BranchWalker parentBranch = splitAndPreprocess(sysContext,walker);
	    </#if>
    
    Iterator ite = results.iterator();
    while(ite.hasNext()) {
        String key = ite.next().toString();
    	<#if activity.isSplitAnd()>
        BranchWalker childWalker = getSplitAndWalker(sysContext,walker,parentBranch);
        startActivity(key,sysContext,childWalker);
    	<#else>
        
	    startActivity(key,sysContext,walker);
    	</#if>
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
    restartActivity(sysContext,walker);
    ${activityId}_next(sysContext,walker);
}
    
    </#if>
	

</#list>


