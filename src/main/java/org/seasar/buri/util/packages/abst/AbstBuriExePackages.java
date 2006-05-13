/*
 * 作成日: 2006/03/21
 *
 */
package org.seasar.buri.util.packages.abst;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.seasar.buri.common.util.MultiValueMap;
import org.seasar.buri.dataaccess.BuriDataAccessFactory;
import org.seasar.buri.engine.BuriPath;
import org.seasar.buri.engine.ParticipantProvider;
import org.seasar.buri.oouo.internal.structure.BuriPackageType;
import org.seasar.buri.oouo.internal.structure.BuriWorkflowProcessType;
import org.seasar.buri.util.packages.BuriExePackages;
import org.seasar.buri.util.packages.BuriExecProcess;
import org.seasar.coffee.dataaccess.DataAccessFactory;
import org.seasar.coffee.dataaccess.DataAccessUtilLongKey;
import org.seasar.coffee.dataaccess.DataAccessUtilManyKey;
import org.seasar.coffee.dataaccess.DataAccessUtil;
import org.seasar.coffee.dataaccess.FilterAccessUtil;
import org.seasar.coffee.dataaccess.PreprocessAccessUtil;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.util.StringUtil;

public class AbstBuriExePackages implements BuriExePackages ,BuriDataAccessFactory{

    protected Map applications = new HashMap();
    protected Map variables = new HashMap();
    protected MultiValueMap participant = new MultiValueMap();
    
    protected ParticipantProvider participantProvider;
    
    protected Map buriExecProcessMap = new HashMap();

    protected BuriPackageType buriPackage;
    protected S2Container container;
    protected BuriDataAccessFactory dataAccessFactory;
    
    public void setup(BuriPackageType buriPackage) {
        this.buriPackage = buriPackage;
//        setupApplication();
        variables.putAll(buriPackage.getApplication());
        participant.putAll(buriPackage.getRoleByName());
    }
    
    public BuriPackageType getBuriPackageType() {
        return buriPackage;
    }
    
    
//    protected void setupApplication() {
//        Set keys = buriPackage.getApplication().keySet();
//        Iterator ite = keys.iterator();
//        while(ite.hasNext()) {
//            String key = ite.next().toString();
//            BuriApplicationType app = buriPackage.getApplicationById(key);
//            applications.put(key,container.getComponent(app.getName()));
//        }
//    }
    

    public void setProcess(String procID, BuriExecProcess execProcess) {
        buriExecProcessMap.put(procID,execProcess);
    }
    
    public BuriExecProcess getProcess(BuriPath path) {
        String processId = getProcessId(path);
        assert ! StringUtil.isEmpty(processId);
        BuriExecProcess processObj = (BuriExecProcess)buriExecProcessMap.get(processId);
        return processObj;
    }
    
    
    private String getProcessId(BuriPath path) {
        BuriWorkflowProcessType process = null;
        if(path.getBuriPathID() != 0) {
            String id = path.getRealPath().getWorkflowProcess();
            assert ! StringUtil.isEmpty(id);
            process = buriPackage.getProcessById(id);
        } else {
            String name = path.getWorkflowProcess();
            assert ! StringUtil.isEmpty(name);
            List list = (List)buriPackage.getProcessByName(name);
            assert list != null;
            assert list.size() == 1 : "processNameに同名を発見！！ 未対応";
            process = (BuriWorkflowProcessType)list.get(0);
        }
        return process.getId();
    }

    public void setDataAccessUtil(Class tgtClass, DataAccessUtilLongKey utilLongKey) {
        dataAccessFactory.setDataAccessUtil(tgtClass, utilLongKey);
    }

    public void setDataAccessUtil(Class tgtClass, DataAccessUtilManyKey utilManyKey) {
        dataAccessFactory.setDataAccessUtil(tgtClass, utilManyKey);
    }

    public void setFilterAccessUtil(Class tgtClass, FilterAccessUtil accessUtil) {
        dataAccessFactory.setFilterAccessUtil(tgtClass, accessUtil);
    }

    public void setPreprocessAccessUtil(Class tgtClass, PreprocessAccessUtil accessUtil) {
        dataAccessFactory.setPreprocessAccessUtil(tgtClass, accessUtil);
    }

    public void addChildFactory(String key, DataAccessFactory factory) {
        dataAccessFactory.addChildFactory(key, factory);
    }

    public DataAccessUtil getDataAccessUtil(Class tgtClass) {
        return dataAccessFactory.getDataAccessUtil(tgtClass);
    }

    public FilterAccessUtil getFilterAccessUtil(Class tgtClass) {
        return dataAccessFactory.getFilterAccessUtil(tgtClass);
    }

    public PreprocessAccessUtil getPreprocessAccessUtil(Class tgtClass) {
        return dataAccessFactory.getPreprocessAccessUtil(tgtClass);
    }

    public S2Container getContainer() {
        return container;
    }

    public void setContainer(S2Container container) {
        this.container = container;
    }

    public BuriDataAccessFactory getDataAccessFactory() {
        return dataAccessFactory;
    }

    public void setDataAccessFactory(BuriDataAccessFactory dataAccessFactory) {
        this.dataAccessFactory = dataAccessFactory;
    }

    public void setParticipantProvider(ParticipantProvider provider) {
        participantProvider = provider;
        
    }

    public ParticipantProvider getParticipantProvider() {
        return participantProvider;
    }
    
}
