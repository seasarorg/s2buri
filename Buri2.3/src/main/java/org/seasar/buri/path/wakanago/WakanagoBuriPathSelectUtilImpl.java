/*
 * 作成日: 2006/04/02
 *
 */
package org.seasar.buri.path.wakanago;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.seasar.buri.engine.BuriPath;
import org.seasar.buri.engine.BuriSystemContext;
import org.seasar.buri.oouo.internal.structure.BuriActivityType;
import org.seasar.buri.oouo.internal.structure.BuriPackageType;
import org.seasar.buri.oouo.internal.structure.BuriWorkflowProcessType;
import org.seasar.buri.path.BuriPathSelectUtil;

public class WakanagoBuriPathSelectUtilImpl implements BuriPathSelectUtil {

    public List selectBuriPath(BuriPackageType packagesType,BuriPath path,BuriSystemContext sysContext) {
        List pathList = new ArrayList();
        List processList = packagesType.getProcessByName(path.getWorkflowPackage());
        processList = filterBuriPath(processList,sysContext);
        Iterator ite = processList.iterator();
        while(ite.hasNext()) {
            BuriWorkflowProcessType process = (BuriWorkflowProcessType)ite.next();
            String pathName = packagesType.getId() + "." + process.getId();
            List pathNameList = getProcessToActivityNameList(pathName,process,path.getActivityName(),0,sysContext);
            pathList.addAll(pathNameList);
        }
        List result = new ArrayList();
        //TODO PATHを作る
        return result;
    }
    
    protected List getProcessToActivityNameList(String pathName,BuriWorkflowProcessType process,List activityList,int pos,BuriSystemContext sysContext) {
        List result = new ArrayList();
        //TODO 条件は実装クラスに任せる
        if(activityList == null || pos == activityList.size()) {
            result.add(pathName);
            return result;
        }
        String actName = (String)activityList.get(pos);
        List actList = process.getActivityByName(actName); //TODO 取得は実装クラスに任せる
        Iterator ite = actList.iterator();
        while(ite.hasNext()) {
            BuriActivityType actType = (BuriActivityType)ite.next();
            actType = filterBuriActivityType(actType,sysContext);
            if(actType==null) continue;
            //TODO XPDLはプロセスの再選択とか条件によって色々あるので再帰処理を書くのを延期
            result.add(pathName + "." + actType.getName());
        }
        return result;
    }
    
    protected BuriActivityType filterBuriActivityType(BuriActivityType actType,BuriSystemContext sysContext) {
        return actType;
    }
    
    protected List filterBuriPath(List processList,BuriSystemContext sysContext) {
        List result = new ArrayList(processList);
        return result;
    }
    
}
