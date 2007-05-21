/*
 * 作成日: 2006/03/28
 *
 */
package org.escafe.buri.sample.packages.wakanagoTest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.escafe.buri.engine.BuriPath;
import org.escafe.buri.engine.BuriSystemContext;
import org.escafe.buri.engine.BuriUserContext;
import org.escafe.buri.oouo.internal.structure.BuriWorkflowProcessType;
import org.escafe.buri.util.packages.BranchWalker;
import org.escafe.buri.util.packages.abst.AbstBuriExecProcess;
import org.seasar.coffee.script.Script;

public class wakanagoTest_wp1 extends AbstBuriExecProcess{
    
    @Override
    public void setup(BuriWorkflowProcessType process) {
        super.setup(process);
    }
    
    
    
    //開始
    
    public void wakanagoTest_wp1_act1(BuriUserContext context,BuriPath nowPath) {
        
    }
    
    public void wakanagoTest_wp1_act1_start(BuriSystemContext sysContext,BranchWalker walker) {
        walker = walker.moveNext("開始","wakanagoTest_wp1_act1");
        startActivity(sysContext,walker);
        wakanagoTest_wp1_act1_process(sysContext,walker);
    }
    
    public void wakanagoTest_wp1_act1_process(BuriSystemContext sysContext,BranchWalker walker) {
        {
            Script script = scriptFactory.getScript("ognl");
            String scriptStr = "@System@out.println(\"開始\")";
            script.run(scriptStr,null,sysContext.getUserContext());
        }
        wakanagoTest_wp1_act1_next(sysContext,walker);
    }
    
    public void wakanagoTest_wp1_act1_next(BuriSystemContext sysContext,BranchWalker walker) {
        startActivity("wakanagoTest_wp1_act2",sysContext,walker);
    }
    
    
    
    //Step1
    public void wakanagoTest_wp1_act2_start(BuriSystemContext sysContext,BranchWalker walker) {
        walker = walker.moveNext("Step1","wakanagoTest_wp1_act2");
        startActivity(sysContext,walker);
        wakanagoTest_wp1_act2_process(sysContext,walker);
    }
    
    public void wakanagoTest_wp1_act2_process(BuriSystemContext sysContext,BranchWalker walker) {
        {
            Script script = scriptFactory.getScript("ognl");
            String scriptStr = "#data.abc = \"abc\"\n@System@out.println(\"setp1\")";
            script.run(scriptStr,null,sysContext.getUserContext());
        }
        wakanagoTest_wp1_act2_next(sysContext,walker);
    }
    
    public void wakanagoTest_wp1_act2_next(BuriSystemContext sysContext,BranchWalker walker) {
        startActivity("wakanagoTest_wp1_act3",sysContext,walker);
    }
    
    
    //Stop1
    public void wakanagoTest_wp1_act3_start(BuriSystemContext sysContext,BranchWalker walker) {
        walker = walker.moveNext("Stop1","wakanagoTest_wp1_act3");
        startActivity(sysContext,walker);
        wakanagoTest_wp1_act3_process(sysContext,walker);
    }
    
    public void wakanagoTest_wp1_act3_process(BuriSystemContext sysContext,BranchWalker walker) {
        {
            Script script = scriptFactory.getScript("ognl");
            String scriptStr = "@System@out.println(\"Stop1\")";
            script.run(scriptStr,null,sysContext.getUserContext());
        }
        exitFlow(sysContext,walker);
        return;
    }
    
    public void wakanagoTest_wp1_act3_next(BuriSystemContext sysContext,BranchWalker walker) {
        startActivity("wakanagoTest_wp1_act4",sysContext,walker);
    }
    
    public void wakanagoTest_wp1_act3_restart(BuriSystemContext sysContext,BranchWalker walker) {
        walker = walker.moveNext("Stop1","wakanagoTest_wp1_act3");
        restartActivity(sysContext,walker);
        wakanagoTest_wp1_act3_next(sysContext,walker);
    }
    
    
    //Step2
    public void wakanagoTest_wp1_act4_start(BuriSystemContext sysContext,BranchWalker walker) {
        walker = walker.moveNext("Step2","wakanagoTest_wp1_act4");
        startActivity(sysContext,walker);
        wakanagoTest_wp1_act4_process(sysContext,walker);
    }
    
    public void wakanagoTest_wp1_act4_process(BuriSystemContext sysContext,BranchWalker walker) {
        {
            Script script = scriptFactory.getScript("ognl");
            String scriptStr = "@System@out.println(\"Step2\")";
            script.run(scriptStr,null,sysContext.getUserContext());
        }
        
        wakanagoTest_wp1_act4_next(sysContext,walker);
    }
    
    public void wakanagoTest_wp1_act4_next(BuriSystemContext sysContext,BranchWalker walker) {
        List results = new ArrayList();
        
        Boolean oneResult = wakanagoTest_wp1_tra3_condition(sysContext,walker);
        if(oneResult.booleanValue()) {
            results.add("wakanagoTest_wp1_act5");
        }
        
        oneResult = conditionCheck("wakanagoTest_wp1_tra6","#data.val == 2",sysContext,walker);
        if(oneResult.booleanValue()) {
            results.add("wakanagoTest_wp1_act7");
        }
        
        results = filterNextActivity(sysContext,walker,results);
        
        if(results.size() == 0) {
            noSelectActivity(sysContext,walker);
            //エラー
        } else if(results.size() == 1) {
            oneSelectActivitySplitAnd(sysContext,walker);
            oneSelectActivitySplitXor(sysContext,walker);
        } else {
            manySelectActivitySplitAnd(sysContext,walker);
            manySelectActivitySplitXor(sysContext,walker);
            //XORの時エラー
        }
        
        BranchWalker parentBranch = splitAndPreprocess(sysContext,walker);
        
        Iterator ite = results.iterator();
        while(ite.hasNext()) {
            String key = ite.next().toString();
            BranchWalker childWalker = getSplitAndWalker(sysContext,walker,parentBranch);
            startActivity(key,sysContext,childWalker);
            
            startActivity(key,sysContext,walker);
        }
        
    }
    
    public Boolean wakanagoTest_wp1_tra3(BuriUserContext context,String condition) {
        return Boolean.FALSE;
    }
    
    public Boolean wakanagoTest_wp1_tra3_condition(BuriSystemContext sysContext,BranchWalker walker) {
        Object result = getConditionScript().eval(null,"#data.val == 1",sysContext.getUserContext());
        if(result instanceof Boolean) {
            return (Boolean)result;
        } return null; //TODO 例外に置き換える
    }
    
    public Boolean wakanagoTest_wp1_tra6_condition(BuriSystemContext sysContext,BranchWalker walker) {
        Object result = getConditionScript().eval(null,"#data.val == 2",sysContext.getUserContext());
        assert result instanceof Boolean;
        return (Boolean)result;
    }

    
    //Stop2
    public void wakanagoTest_wp1_act5_start(BuriSystemContext sysContext,BranchWalker walker) {
        walker = walker.moveNext("Stop2","wakanagoTest_wp1_act5");
        startActivity(sysContext,walker);
        wakanagoTest_wp1_act5_process(sysContext,walker);
    }
    
    public void wakanagoTest_wp1_act5_process(BuriSystemContext sysContext,BranchWalker walker) {
        {
            Script script = scriptFactory.getScript("ognl");
            String scriptStr = "@System@out.println(\"Stop2\")";
            script.run(scriptStr,null,sysContext.getUserContext());
        }
        exitFlow(sysContext,walker);
        return;
    }
    
    public void wakanagoTest_wp1_act5_next(BuriSystemContext sysContext,BranchWalker walker) {
        startActivity("wakanagoTest_wp1_act6",sysContext,walker);
    }
    
    public void wakanagoTest_wp1_act5_restart(BuriSystemContext sysContext,BranchWalker walker) {
        walker = walker.moveNext("Stop2","wakanagoTest_wp1_act5");
        wakanagoTest_wp1_act5_next(sysContext,walker);
    }
    
    
    //終了
    public void wakanagoTest_wp1_act6_start(BuriSystemContext sysContext,BranchWalker walker) {
        joinXorFlow(sysContext,walker,"終了","wakanagoTest_wp1_act6");
        joinAndFlow(sysContext,walker,"終了","wakanagoTest_wp1_act6");
        walker = walker.moveNext("終了","wakanagoTest_wp1_act6");
        startActivity(sysContext,walker);
        wakanagoTest_wp1_act6_process(sysContext,walker);
    }
    
    public void wakanagoTest_wp1_act6_process(BuriSystemContext sysContext,BranchWalker walker) {
        {
            Script script = scriptFactory.getScript("ognl");
            String scriptStr = "@System@out.println(\"終了\")";
            script.run(scriptStr,null,sysContext.getUserContext());
        }
        exitFlow(sysContext,walker);
        return;
    }
    
    public void wakanagoTest_wp1_act6_next(BuriSystemContext sysContext,BranchWalker walker) {
        
    }
    
    public void wakanagoTest_wp1_act6_restart(BuriSystemContext sysContext,BranchWalker walker) {
        walker = walker.moveNext("終了","wakanagoTest_wp1_act6");
        wakanagoTest_wp1_act6_next(sysContext,walker);
    }
    
    
    //Stop2-1
    public void wakanagoTest_wp1_act7_start(BuriSystemContext sysContext,BranchWalker walker) {
        walker = walker.moveNext("Stop2-1","wakanagoTest_wp1_act7");
        startActivity(sysContext,walker);
        wakanagoTest_wp1_act7_process(sysContext,walker);
    }
    
    public void wakanagoTest_wp1_act7_process(BuriSystemContext sysContext,BranchWalker walker) {
        {
            Script script = scriptFactory.getScript("ognl");
            String scriptStr = "@System@out.println(\"Stop2-1\")";
            script.run(scriptStr,null,sysContext.getUserContext());
        }
        exitFlow(sysContext,walker);
        return;
    }
    
    public void wakanagoTest_wp1_act7_restart(BuriSystemContext sysContext,BranchWalker walker) {
        walker = walker.moveNext("Stop2-1","wakanagoTest_wp1_act7");
        wakanagoTest_wp1_act7_next(sysContext,walker);
    }
    
    public void wakanagoTest_wp1_act7_next(BuriSystemContext sysContext,BranchWalker walker) {
        startActivity("wakanagoTest_wp1_act6",sysContext,walker);
    }
    
    
    
    
}
