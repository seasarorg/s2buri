/*
 * �쐬��: 2006/03/21
 *
 */
package org.seasar.buri.sample.packages.FurnitureManagement;

import java.util.HashMap;
import java.util.Map;

import org.seasar.buri.engine.BuriPath;
import org.seasar.buri.engine.BuriSystemContext;
import org.seasar.buri.util.packages.abst.AbstBuriExecProcess;

public class FurnitureManagement_Wor1 extends AbstBuriExecProcess{
    private Map pathToBuriPath = new HashMap();
    private Map nameToBuriPath = new HashMap();
    private Map idToBuriPath = new HashMap();

    public void setup() {
        super.setup();
        BuriPath path;

        path = new BuriPath("���i�Ǘ�.���i�Ǘ�.���i�o�^","���i�Ǘ�.FurnitureManagement_Wor1.FurnitureManagement_Wor1_Act1");
        path.setBuriPathID(100);
        pathToBuriPath.put("FurnitureManagement_Wor1_Act1",path);
        nameToBuriPath.put("���i�o�^",path);
        idToBuriPath.put(new Long(100),path);

        path = new BuriPath("���i�Ǘ�.���i�Ǘ�.���p��","���i�Ǘ�.FurnitureManagement_Wor1.FurnitureManagement_Wor1_Act2");
        path.setBuriPathID(101);
        pathToBuriPath.put("FurnitureManagement_Wor1_Act2",path);
        nameToBuriPath.put("���p��",path);
        idToBuriPath.put(new Long(101),path);
    }
    
    // ���i�o�^
    public void FurnitureManagement_Wor1_Act1_Start(BuriSystemContext context) {
        FurnitureManagement_Wor1_Act1_Process(context);
        FurnitureManagement_Wor1_Act1_Restart(context);
    }
    public void FurnitureManagement_Wor1_Act1_JoinAnd(BuriSystemContext context) {
        
    }
    public void FurnitureManagement_Wor1_Act1_JoinXor(BuriSystemContext context) {
        
    }

    public void FurnitureManagement_Wor1_Act1_Process(BuriSystemContext context) {
        
    }

    public void FurnitureManagement_Wor1_Act1_Restart(BuriSystemContext context) {
        FurnitureManagement_Wor1_Act2_Start(context);
    }
    public void FurnitureManagement_Wor1_Act1_SaveState(BuriSystemContext context) {
        
    }
    public void FurnitureManagement_Wor1_Act1_Pause(BuriSystemContext context) {
        FurnitureManagement_Wor1_Act1_SaveState(context);
        return;
    }
    public void FurnitureManagement_Wor1_Act1_SplitAnd(BuriSystemContext context) {
        
    }
    public void FurnitureManagement_Wor1_Act1_SplitXor(BuriSystemContext context) {
        
    }

    public BuriPath FurnitureManagement_Wor1_Act1_UpdatePath(BuriPath path) {
        return path.changePath("���i�o�^","FurnitureManagement_Wor1_Act1");
    }

//    ���p��
    public void FurnitureManagement_Wor1_Act2_Start(BuriSystemContext context) {
        FurnitureManagement_Wor1_Act2_Process(context);
        FurnitureManagement_Wor1_Act2_Pause(context);
    }
    public void FurnitureManagement_Wor1_Act2_JoinAnd(BuriSystemContext context) {
        
    }
    public void FurnitureManagement_Wor1_Act2_JoinXor(BuriSystemContext context) {
        
    }
    public void FurnitureManagement_Wor1_Act2_Process(BuriSystemContext context) {
        
    }
    public void FurnitureManagement_Wor1_Act2_Restart(BuriSystemContext context) {
        
    }
    public void FurnitureManagement_Wor1_Act2_SaveState(BuriSystemContext context) {
        
    }
    public void FurnitureManagement_Wor1_Act2_Pause(BuriSystemContext context) {
        FurnitureManagement_Wor1_Act2_SaveState(context);
    }
    public void FurnitureManagement_Wor1_Act2_SplitAnd(BuriSystemContext context) {
        
    }
    public void FurnitureManagement_Wor1_Act2_SplitXor(BuriSystemContext context) {
        
    }

//    ���ԏI��
    public void FurnitureManagement_Wor1_Act7() {
        
    }

//    ���p���ԏI��
    public void FurnitureManagement_Wor1_Act3() {
        
    }

//    ���[�X�I��
    public void FurnitureManagement_Wor1_Act4() {
        
    }

//    �ԋp�ς�
    public void FurnitureManagement_Wor1_Act5() {
        
    }

//    �p�������ς�
    public void FurnitureManagement_Wor1_Act6() {
        
    }
    
}
