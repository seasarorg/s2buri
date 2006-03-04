/*
 * �쐬��: 2005/07/25
 *
 */
package org.seasar.buri.impl;

import java.util.Collection;

import org.seasar.buri.context.BuriLocalContext;
import org.seasar.buri.context.ContextUtil;
import org.seasar.buri.dao.BuriDataPathHistoryDao;
import org.seasar.buri.dao.BuriPathDao;
import org.seasar.buri.dao.BuriStateDao;
import org.seasar.buri.dao.datautil.DataIDListUtil;
import org.seasar.buri.dto.BuriTestINTDto;
import org.seasar.buri.engine.BuriEngine;
import org.seasar.buri.engine.BuriPath;
import org.seasar.extension.unit.S2TestCase;


public class BuriDefaultEngineTest extends S2TestCase {
    private String PATH="buri2.dicon";
    private String DAOPATH = "../dicon/allTestDao.dicon";
    private BuriEngine buriEngine;
    
    private BuriDataPathHistoryDao historyDao_;
    private BuriStateDao stateDao_;
    private BuriPathDao pathDao_;
    private DataIDListUtil listUtil_;
    private ContextUtil contextUtil_;

    public BuriDefaultEngineTest(String arg0) {
        super(arg0);
    }
    
    protected void setUp() throws Exception {
        super.setUp();
        include(PATH);
        include(DAOPATH);
        buriEngine = (BuriEngine)getComponent(BuriEngine.class);
        buriEngine.getWorkflows().readWorkFlowFromResource("basicTest","org/seasar/buri/impl/basicTest.xpdl");
        
    }
    
    protected void setupContext(Object data, Object userData,BuriPath buriPath) {
        setupContext(data,userData,buriPath,null,false);
    }
    protected void setupContext(Object data, Object userData,BuriPath buriPath,Object action,boolean notUpdateMode) {
        BuriLocalContext localContext = contextUtil_.getLocalContext();
        buriEngine.createContext(data,getContainer());
        contextUtil_.getContext().setUserData(userData);
        localContext.setContainer(getContainer());
        localContext.getUserContext().setData(data);
        localContext.setCallBuriPath(buriPath);
        localContext.setUserData(userData);
        localContext.getUserContext().setUserData(userData);
        localContext.getUserContext().put("action",action);
        localContext.getUserContext().put("callPath",buriPath);
        localContext.getUserContext().put("UserContext",localContext.getUserContext());
        localContext.setAction(action);
        localContext.setIsNotUpdateMode(notUpdateMode);
        
    }
    
    public void test01Tx() {
        BuriTestINTDto testDto = new BuriTestINTDto();
        testDto.setValue("testValue");
//        buriEngine.createContext(testDto,getContainer());
//        BuriLocalContext context = buriEngine.createContext(testDto);

        BuriPath path = new BuriPath("basicTest.test01.start");
        setupContext(testDto, null,path);
        
        int histSize = historyDao_.getAllBuriDataPathHistory().size();
        int stateSize = stateDao_.getAllBuriState().size();
        buriEngine.execute(path);
        
        assertTrue(testDto.getTestID() != 0);
        assertEquals(histSize+1,historyDao_.getAllBuriDataPathHistory().size());
        assertEquals(stateSize,stateDao_.getNoProcessBuriState().size());
    }
    
    public void test02Tx() {
        BuriTestINTDto testDto = new BuriTestINTDto();
        testDto.setValue("testValue");

        BuriPath path = new BuriPath("basicTest.test02.start");
        setupContext(testDto, null,path);
        int histSize = historyDao_.getAllBuriDataPathHistory().size();
        int stateSize = stateDao_.getAllBuriState().size();
        buriEngine.execute(path);
        
        pathDao_.getAllBuriPath();
        assertTrue(testDto.getTestID() != 0);
        assertEquals(histSize+2,historyDao_.getAllBuriDataPathHistory().size());
        assertEquals(stateSize+1,stateDao_.getNoProcessBuriState().size());
        
        System.out.println("buriEngine.getStatePathListByData");
        setupContext(testDto, null,path);
        Collection pathList = listUtil_.getStatePathListByData(new BuriPath("basicTest.test02"));
        assertEquals(pathList.size(),1);
        BuriPath tgtPath = (BuriPath)pathList.iterator().next();
        assertEquals(tgtPath.toString(),"basicTest.test02.stopThis[basicTest.basicTest_Wor2.basicTest_Wor2_Act2]");
        
        System.out.println("buriEngine.getDataListFromPath");
        setupContext(null, null,path);
        Collection dataList = listUtil_.getDataListFromPath(new BuriPath("basicTest.test02.stopThis"));
        
        assertEquals(dataList.size(),1);
        BuriTestINTDto reloadDto = (BuriTestINTDto)dataList.iterator().next();
        assertEquals(reloadDto.getValue(),testDto.getValue());

        System.out.println("buriEngine.execute");
        path = new BuriPath("basicTest.test02.stopThis");
        
        setupContext(testDto, null,path);
        histSize = historyDao_.getAllBuriDataPathHistory().size();
        stateSize = stateDao_.getAllBuriState().size();
        buriEngine.execute(path);
        
        pathDao_.getAllBuriPath();
        assertTrue(testDto.getTestID() != 0);
        assertEquals(stateSize,stateDao_.getAllBuriState().size());
        assertEquals(histSize+2,historyDao_.getAllBuriDataPathHistory().size());
    }
    
    public void test03Tx() {
        BuriTestINTDto testDto = new BuriTestINTDto();
        testDto.setValue("testValue");

        BuriPath path = new BuriPath("basicTest.test03.start");
        setupContext(testDto, null,path);
        
        int histSize = historyDao_.getAllBuriDataPathHistory().size();
        int stateSize = stateDao_.getAllBuriState().size();
        buriEngine.execute(path);
        
        pathDao_.getAllBuriPath();
        assertTrue(testDto.getTestID() != 0);
        assertEquals(stateSize+2,stateDao_.getNoProcessBuriState().size());
        assertEquals(histSize+3,historyDao_.getAllBuriDataPathHistory().size());
    }
    
    public void test04Tx() {
        BuriTestINTDto testDto = new BuriTestINTDto();
        testDto.setValue("testValue");

        BuriPath path = new BuriPath("basicTest.test04.start");
        setupContext(testDto, null,path);
        
        int histSize = historyDao_.getAllBuriDataPathHistory().size();
        int stateSize = stateDao_.getAllBuriState().size();
        buriEngine.execute(path);
        
        pathDao_.getAllBuriPath();
        assertTrue(testDto.getTestID() != 0);
        assertEquals(stateSize+2,stateDao_.getNoProcessBuriState().size());
        assertEquals(histSize+3,historyDao_.getAllBuriDataPathHistory().size());


        path = new BuriPath("basicTest.test04.continue1");
        setupContext(testDto, null,path);
        
        histSize = historyDao_.getAllBuriDataPathHistory().size();
        stateSize = stateDao_.getNoProcessBuriState().size();
        buriEngine.execute(path);
        
        pathDao_.getAllBuriPath();
        assertEquals(stateSize-2,stateDao_.getNoProcessBuriState().size());
        assertEquals(0,stateDao_.getNoProcessBuriState().size());
        assertEquals(histSize+2,historyDao_.getAllBuriDataPathHistory().size());
    
    
    }
    
    public void test05Tx() {
        BuriTestINTDto testDto = new BuriTestINTDto();
        testDto.setValue("testValue");

        BuriPath path = new BuriPath("basicTest.test05.�J�n");
        setupContext(testDto, null,path);
        
        int histSize = historyDao_.getAllBuriDataPathHistory().size();
        int stateSize = stateDao_.getNoProcessBuriState().size();
        buriEngine.execute(path);
        
        pathDao_.getAllBuriPath();
        assertEquals(stateSize+2,stateDao_.getNoProcessBuriState().size());
        assertTrue(testDto.getTestID() != 0);
        assertEquals(histSize+3,historyDao_.getAllBuriDataPathHistory().size());


        path = new BuriPath("basicTest.test05.����2");
        setupContext(testDto, null,path);
        
        histSize = historyDao_.getAllBuriDataPathHistory().size();
        stateSize = stateDao_.getNoProcessBuriState().size();
        buriEngine.execute(path);
        
        pathDao_.getAllBuriPath();
        assertEquals(stateSize+1,stateDao_.getNoProcessBuriState().size());
        assertEquals(histSize+3,historyDao_.getAllBuriDataPathHistory().size());

        path = new BuriPath("basicTest.test05.�́[�ǂP");
        setupContext(testDto, null,path);
        
        histSize = historyDao_.getAllBuriDataPathHistory().size();
        stateSize = stateDao_.getNoProcessBuriState().size();
        buriEngine.execute(path);
        
        pathDao_.getAllBuriPath();
        stateDao_.getAllBuriState();
        assertEquals(stateSize-1,stateDao_.getNoProcessBuriState().size());
        assertEquals(histSize+2,historyDao_.getAllBuriDataPathHistory().size());
    

        path = new BuriPath("basicTest.test05.����I��");
        setupContext(testDto, null,path);
        
        histSize = historyDao_.getAllBuriDataPathHistory().size();
        stateSize = stateDao_.getNoProcessBuriState().size();
        buriEngine.execute(path);
        
        pathDao_.getAllBuriPath();
        stateDao_.getAllBuriState();
        assertEquals(stateSize-1,stateDao_.getNoProcessBuriState().size());
        assertEquals(histSize+3,historyDao_.getAllBuriDataPathHistory().size());
    
    }
    
    public void test05_1Tx() {
        BuriTestINTDto testDto = new BuriTestINTDto();
        testDto.setValue("testValue");

        BuriPath path = new BuriPath("basicTest.test05.�J�n");
        setupContext(testDto, null,path);
        
        int histSize = historyDao_.getAllBuriDataPathHistory().size();
        int stateSize = stateDao_.getNoProcessBuriState().size();
        buriEngine.execute(path);
        
        pathDao_.getAllBuriPath();
        assertTrue(testDto.getTestID() != 0);
        assertEquals(stateSize+2,stateDao_.getNoProcessBuriState().size());
        assertEquals(histSize+3,historyDao_.getAllBuriDataPathHistory().size());


        path = new BuriPath("basicTest.test05.����2");
        setupContext(testDto, null,path);
        
        histSize = historyDao_.getAllBuriDataPathHistory().size();
        stateSize = stateDao_.getNoProcessBuriState().size();
        buriEngine.execute(path);
        
        pathDao_.getAllBuriPath();
        assertEquals(stateSize+1,stateDao_.getNoProcessBuriState().size());
        assertEquals(histSize+3,historyDao_.getAllBuriDataPathHistory().size());

        path = new BuriPath("basicTest.test05.����1");
        setupContext(testDto, null,path);
        
        histSize = historyDao_.getAllBuriDataPathHistory().size();
        stateSize = stateDao_.getNoProcessBuriState().size();
        buriEngine.execute(path);
        
        pathDao_.getAllBuriPath();
        assertEquals(stateSize-3,stateDao_.getNoProcessBuriState().size());
        assertEquals(histSize+3,historyDao_.getAllBuriDataPathHistory().size());
    

    }

    
    public void test06_01Tx() {
        BuriTestINTDto testDto = new BuriTestINTDto();
        testDto.setValue("testValue");

        BuriPath path = new BuriPath("basicTest.test06.�J�n");
        setupContext(testDto, null,path);
        
        int histSize = historyDao_.getAllBuriDataPathHistory().size();
        int stateSize = stateDao_.getNoProcessBuriState().size();
        buriEngine.execute(path);
        
        pathDao_.getAllBuriPath();
        assertTrue(testDto.getTestID() != 0);
        assertEquals(histSize+3,historyDao_.getAllBuriDataPathHistory().size());
        assertEquals(stateSize+2,stateDao_.getNoProcessBuriState().size());


        path = new BuriPath("basicTest.test06.����2");
        setupContext(testDto, null,path);
        
        histSize = historyDao_.getAllBuriDataPathHistory().size();
        stateSize = stateDao_.getNoProcessBuriState().size();
        buriEngine.execute(path);
        
        pathDao_.getAllBuriPath();
        assertEquals(stateSize+1,stateDao_.getNoProcessBuriState().size());
        assertEquals(histSize+3,historyDao_.getAllBuriDataPathHistory().size());

        path = new BuriPath("basicTest.test06.�́[�ǂP");
        setupContext(testDto, null,path);
        
        histSize = historyDao_.getAllBuriDataPathHistory().size();
        stateSize = stateDao_.getNoProcessBuriState().size();
        buriEngine.execute(path);
        
        pathDao_.getAllBuriPath();
        assertEquals(stateSize-1,stateDao_.getNoProcessBuriState().size());
        assertEquals(histSize+1,historyDao_.getAllBuriDataPathHistory().size());

        path = new BuriPath("basicTest.test06.�́[��2");
        setupContext(testDto, null,path);
        
        histSize = historyDao_.getAllBuriDataPathHistory().size();
        stateSize = stateDao_.getNoProcessBuriState().size();
        buriEngine.execute(path);
        
        pathDao_.getAllBuriPath();
        assertEquals(stateSize,stateDao_.getNoProcessBuriState().size());
        assertEquals(histSize+2,historyDao_.getAllBuriDataPathHistory().size());

        path = new BuriPath("basicTest.test06.����1");
        setupContext(testDto, null,path);
        
        histSize = historyDao_.getAllBuriDataPathHistory().size();
        stateSize = stateDao_.getNoProcessBuriState().size();
        buriEngine.execute(path);
        
        pathDao_.getAllBuriPath();
        assertEquals(stateSize-1,stateDao_.getNoProcessBuriState().size());
        assertEquals(histSize+1,historyDao_.getAllBuriDataPathHistory().size());
    

        path = new BuriPath("basicTest.test06.����I��");
        setupContext(testDto, null,path);
        
        histSize = historyDao_.getAllBuriDataPathHistory().size();
        stateSize = stateDao_.getNoProcessBuriState().size();
        buriEngine.execute(path);
        
        pathDao_.getAllBuriPath();
        assertEquals(stateSize-1,stateDao_.getNoProcessBuriState().size());
        assertEquals(histSize+3,historyDao_.getAllBuriDataPathHistory().size());

    }

    
    public void test07Tx() {
        BuriTestINTDto testDto = new BuriTestINTDto();
        testDto.setValue("testValue");

        BuriPath path = new BuriPath("basicTest.test07.SAND");
        setupContext(testDto, null,path);
        
        int histSize = historyDao_.getAllBuriDataPathHistory().size();
        int stateSize = stateDao_.getNoProcessBuriState().size();
        buriEngine.execute(path);
        
        pathDao_.getAllBuriPath();
        assertTrue(testDto.getTestID() != 0);
        assertEquals(histSize+7,historyDao_.getAllBuriDataPathHistory().size());
        assertEquals(stateSize+4,stateDao_.getNoProcessBuriState().size());

        path = new BuriPath("basicTest.test07.FM1");
        setupContext(testDto, null,path);
        
        histSize = historyDao_.getAllBuriDataPathHistory().size();
        stateSize = stateDao_.getNoProcessBuriState().size();
        buriEngine.execute(path);
        
        pathDao_.getAllBuriPath();
        assertEquals(stateSize-1,stateDao_.getNoProcessBuriState().size());
        assertEquals(histSize+2,historyDao_.getAllBuriDataPathHistory().size());

        path = new BuriPath("basicTest.test07.FM2");
        setupContext(testDto, null,path);
        
        histSize = historyDao_.getAllBuriDataPathHistory().size();
        stateSize = stateDao_.getNoProcessBuriState().size();
        buriEngine.execute(path);
        
        pathDao_.getAllBuriPath();
        assertEquals(stateSize-1,stateDao_.getNoProcessBuriState().size());
        assertEquals(histSize+3,historyDao_.getAllBuriDataPathHistory().size());

        path = new BuriPath("basicTest.test07.FM3");
        setupContext(testDto, null,path);
        
        histSize = historyDao_.getAllBuriDataPathHistory().size();
        stateSize = stateDao_.getNoProcessBuriState().size();
        buriEngine.execute(path);
        
        pathDao_.getAllBuriPath();
        assertEquals(stateSize-1,stateDao_.getNoProcessBuriState().size());
        assertEquals(histSize+2,historyDao_.getAllBuriDataPathHistory().size());

        path = new BuriPath("basicTest.test07.FM4");
        setupContext(testDto, null,path);
        
        histSize = historyDao_.getAllBuriDataPathHistory().size();
        stateSize = stateDao_.getNoProcessBuriState().size();
        buriEngine.execute(path);
        
        pathDao_.getAllBuriPath();
        assertEquals(stateSize-1,stateDao_.getNoProcessBuriState().size());
        assertEquals(histSize+4,historyDao_.getAllBuriDataPathHistory().size());
    

    }
    
    public void test08Tx() {
        BuriTestINTDto testDto = new BuriTestINTDto();
        testDto.setValue("testValue");

        //�ŏ��̎��s
        BuriPath path = new BuriPath("basicTest.test08");
        setupContext(testDto, null,path);
        
        int histSize = historyDao_.getAllBuriDataPathHistory().size();
        int stateSize = stateDao_.getNoProcessBuriState().size();
        buriEngine.execute(path);
        
        pathDao_.getAllBuriPath();
        assertTrue(testDto.getTestID() != 0);
        assertEquals(histSize+2,historyDao_.getAllBuriDataPathHistory().size());
        assertEquals(stateSize+1,stateDao_.getNoProcessBuriState().size());

        //���[�v�ɍs��
        path = new BuriPath("basicTest.test08");
        setupContext(testDto, null,path,"loop",false);
        
        histSize = historyDao_.getAllBuriDataPathHistory().size();
        stateSize = stateDao_.getNoProcessBuriState().size();
        buriEngine.execute(path);
        
        pathDao_.getAllBuriPath();
        assertTrue(testDto.getTestID() != 0);
        assertEquals(histSize+2,historyDao_.getAllBuriDataPathHistory().size());
        assertEquals(stateSize,stateDao_.getNoProcessBuriState().size());
    
        //���[�v����E�o����
        path = new BuriPath("basicTest.test08");
        setupContext(testDto, null,path);
        
        histSize = historyDao_.getAllBuriDataPathHistory().size();
        stateSize = stateDao_.getNoProcessBuriState().size();
        buriEngine.execute(path);
        
        pathDao_.getAllBuriPath();
        assertEquals(histSize+2,historyDao_.getAllBuriDataPathHistory().size());
        assertEquals(stateSize,stateDao_.getNoProcessBuriState().size());

        //���[�v�ɍs�� �������I�I
        path = new BuriPath("basicTest.test08");
        setupContext(testDto, null,path,"loop",false);
        
        histSize = historyDao_.getAllBuriDataPathHistory().size();
        stateSize = stateDao_.getNoProcessBuriState().size();
        buriEngine.execute(path);
        
        pathDao_.getAllBuriPath();
        assertTrue(testDto.getTestID() != 0);
        assertEquals(histSize+2,historyDao_.getAllBuriDataPathHistory().size());
        assertEquals(stateSize,stateDao_.getNoProcessBuriState().size());
    
        //���[�v����E�o���� �������I�I
        path = new BuriPath("basicTest.test08");
        setupContext(testDto, null,path);
        
        histSize = historyDao_.getAllBuriDataPathHistory().size();
        stateSize = stateDao_.getNoProcessBuriState().size();
        buriEngine.execute(path);
        
        pathDao_.getAllBuriPath();
        assertEquals(histSize+2,historyDao_.getAllBuriDataPathHistory().size());
        assertEquals(stateSize,stateDao_.getNoProcessBuriState().size());
        
        
        //���̃��[�v��
        path = new BuriPath("basicTest.test08");
        setupContext(testDto, null,path);
        
        histSize = historyDao_.getAllBuriDataPathHistory().size();
        stateSize = stateDao_.getNoProcessBuriState().size();
        buriEngine.execute(path);
        
        pathDao_.getAllBuriPath();
        assertEquals(histSize+2,historyDao_.getAllBuriDataPathHistory().size());
        assertEquals(stateSize,stateDao_.getNoProcessBuriState().size());


        //2�Ԗڂ̃��[�v�ɍs���A�ł����߂��Ă���
        path = new BuriPath("basicTest.test08");
        setupContext(testDto, null,path,"ENTERLOOP",false);
        
        histSize = historyDao_.getAllBuriDataPathHistory().size();
        stateSize = stateDao_.getNoProcessBuriState().size();
        buriEngine.execute(path);
        
        pathDao_.getAllBuriPath();
        assertEquals(histSize+3,historyDao_.getAllBuriDataPathHistory().size());
        assertEquals(stateSize,stateDao_.getNoProcessBuriState().size());

        //2�Ԗڂ̃��[�v�ɍs���A�ł����߂��Ă���@�������I�I
        path = new BuriPath("basicTest.test08");
        setupContext(testDto, null,path,"ENTERLOOP",false);
        
        histSize = historyDao_.getAllBuriDataPathHistory().size();
        stateSize = stateDao_.getNoProcessBuriState().size();
        buriEngine.execute(path);
        
        pathDao_.getAllBuriPath();
        assertEquals(histSize+3,historyDao_.getAllBuriDataPathHistory().size());
        assertEquals(stateSize,stateDao_.getNoProcessBuriState().size());
    

        //2�Ԗڂ̃��[�v���I���
        path = new BuriPath("basicTest.test08");
        setupContext(testDto, null,path,"EXITLOOP",false);
        
        histSize = historyDao_.getAllBuriDataPathHistory().size();
        stateSize = stateDao_.getNoProcessBuriState().size();
        buriEngine.execute(path);
        
        pathDao_.getAllBuriPath();
        assertEquals(histSize+2,historyDao_.getAllBuriDataPathHistory().size());
        assertEquals(stateSize-1,stateDao_.getNoProcessBuriState().size());
    
            
    
    }

}
