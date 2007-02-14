/*
 * 作成日: 2006/03/21
 *
 */
package org.seasar.buri.sample.packages.FurnitureManagement;

import java.util.HashMap;
import java.util.Map;

import org.seasar.buri.engine.BuriPath;
import org.seasar.buri.engine.BuriSystemContext;
import org.seasar.buri.util.packages.abst.AbstBuriExecProcess;

public class FurnitureManagement_Wor1 extends AbstBuriExecProcess {
    private Map pathToBuriPath = new HashMap();
    private Map nameToBuriPath = new HashMap();
    private Map idToBuriPath = new HashMap();

    public void setup() {
        //        super.setup();
        BuriPath path;

        path = new BuriPath("備品管理.備品管理.備品登録",
            "備品管理.FurnitureManagement_Wor1.FurnitureManagement_Wor1_Act1");
        path.setBuriPathId(100);
        pathToBuriPath.put("FurnitureManagement_Wor1_Act1", path);
        nameToBuriPath.put("備品登録", path);
        idToBuriPath.put(new Long(100), path);

        path = new BuriPath("備品管理.備品管理.利用中",
            "備品管理.FurnitureManagement_Wor1.FurnitureManagement_Wor1_Act2");
        path.setBuriPathId(101);
        pathToBuriPath.put("FurnitureManagement_Wor1_Act2", path);
        nameToBuriPath.put("利用中", path);
        idToBuriPath.put(new Long(101), path);
    }

    // 備品登録
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
        //        return path.changePath("備品登録","FurnitureManagement_Wor1_Act1");
        return null;
    }

    //    利用中
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

    //    期間終了
    public void FurnitureManagement_Wor1_Act7() {

    }

    //    償却期間終了
    public void FurnitureManagement_Wor1_Act3() {

    }

    //    リース終了
    public void FurnitureManagement_Wor1_Act4() {

    }

    //    返却済み
    public void FurnitureManagement_Wor1_Act5() {

    }

    //    廃棄処分済み
    public void FurnitureManagement_Wor1_Act6() {

    }

}
