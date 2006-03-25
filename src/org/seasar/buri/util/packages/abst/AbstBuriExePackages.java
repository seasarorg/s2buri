/*
 * 作成日: 2006/03/21
 *
 */
package org.seasar.buri.util.packages.abst;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.seasar.buri.engine.BuriPath;
import org.seasar.buri.oouo.internal.structure.BuriApplicationType;
import org.seasar.buri.oouo.internal.structure.BuriPackageType;
import org.seasar.buri.oouo.internal.structure.BuriWorkflowProcessType;
import org.seasar.buri.util.packages.BuriExePackages;
import org.seasar.buri.util.packages.BuriExecProcess;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.util.ClassUtil;
import org.seasar.framework.util.StringUtil;

public abstract class AbstBuriExePackages implements BuriExePackages {

    protected Map applications = new HashMap();
    protected Map variables = new HashMap();
    protected Map processId = new HashMap();
    protected Map processName = new HashMap();
    protected Map participant = new HashMap();

    protected BuriPackageType buriPackage;
    protected S2Container container;
    
    public void setup(BuriPackageType buriPackage) {
        this.buriPackage = buriPackage;
        setupApplication();
        variables.putAll(buriPackage.getApplication());
        setupProcess();
        participant.putAll(buriPackage.getRoleByName());
    }
    
    protected void setupApplication() {
        Set keys = buriPackage.getApplication().keySet();
        Iterator ite = keys.iterator();
        while(ite.hasNext()) {
            String key = ite.next().toString();
            BuriApplicationType app = buriPackage.getApplicationById(key);
            applications.put(key,container.getComponent(app.getName()));
        }
    }
    
    protected void setupProcess() {
        Set keys = buriPackage.getProcessById().keySet();
        Iterator ite = keys.iterator();
        while(ite.hasNext()) {
            String keyId = ite.next().toString();
            BuriWorkflowProcessType process = buriPackage.getProcessById(keyId);
            String tgtClass = getTgtClass(process);
            processId.put(keyId,tgtClass);
            setupProcessName(keyId,process);
        }
    }
    
    private void setupProcessName(String keyId,BuriWorkflowProcessType process) {
        List nameToList = new ArrayList();
        if( ! processName.containsKey(process.getName())) {
            nameToList = (List)processName.get(process.getName());
        }
        nameToList.add(keyId);
        processName.put(process.getName(),nameToList);
    }
    
    private String getTgtClass(BuriWorkflowProcessType process) {
        String packageName = ClassUtil.getSimpleClassName(this.getClass());
        packageName = packageName.replaceAll(ClassUtil.getShortClassName(this.getClass()),"");
        String tgtClass = packageName + process.getId();
        return tgtClass;
    }
    
    
    public BuriExecProcess getProcess(BuriPath path) {
        String className = getProcessClassName(path);
        assert ! StringUtil.isEmpty(className);
        BuriExecProcess processObj = (BuriExecProcess)ClassUtil.newInstance(className);
        container.injectDependency(processObj,AbstBuriExecProcess.class); //TODO プロセスの親クラスを設定すること
        return processObj;
    }
    
    
    private String getProcessClassName(BuriPath path) {
        String className = "";
        if(path.getBuriPathID() != 0) {
            String id = path.getRealPath().getWorkflowProcess();
            assert ! StringUtil.isEmpty(id);
            className = (String)processId.get(id);
        } else {
            String name = path.getWorkflowProcess();
            assert ! StringUtil.isEmpty(name);
            List list = (List)processName.get(name);
            assert list.size() == 1 : "processNameに同名を発見！！ 未対応";
            className = (String)list.get(0);
        }
        return className;
    }
    
}
