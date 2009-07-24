package org.escafe.buri.example.action;

import java.util.List;

import javax.annotation.Resource;

import org.escafe.buri.example.dto.PurchaseApplicationViewDto;
import org.escafe.buri.example.entity.PurchaseApplication;
import org.escafe.buri.example.form.PurchaseApplicationForm;
import org.escafe.buri.example.service.PurchaseApplicationService;
import org.seasar.framework.beans.util.BeanMap;
import org.seasar.framework.beans.util.Beans;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

public class PurchaseApplicationAction {

    @Resource
    protected PurchaseApplicationService purchaseApplicationService;

    @Resource
    public PurchaseApplicationViewDto purchaseApplicationViewDto;

    @ActionForm
    @Resource
    public PurchaseApplicationForm purchaseApplicationForm;

    @Execute(validator = false)
    public String index() {
        purchaseApplicationViewDto.initialize();
        List<PurchaseApplication> purchaseApplicationList = purchaseApplicationService
            .findAllOrderById();
        for (PurchaseApplication pa : purchaseApplicationList) {
            BeanMap bm = Beans.createAndCopy(BeanMap.class, pa).dateConverter(
                "yyyy-MM-dd",
                "applicationDate").execute();
            purchaseApplicationViewDto.purchaseApplicationItems.add(bm);
        }
        return "list.html";
    }

    @Execute(validator = false, redirect = true)
    public String doCreate() {
        purchaseApplicationForm.intialize();
        return "edit";
    }

    @Execute(validator = false)
    public String edit() {
        return "edit.html";
    }

    @Execute(validator = true, input = "edit.html", redirect = true)
    public String doConfirm() {
        return "confirm";
    }

    @Execute(validator = false)
    public String confirm() {
        return "confirm.html";
    }

    @Execute(validator = false, redirect = true)
    public String doFinish() {
        PurchaseApplication purchaseApplication = Beans.createAndCopy(
            PurchaseApplication.class,
            purchaseApplicationForm).dateConverter(
            "yyyy-MM-dd",
            "applicationDate").dateConverter(
            "yyyy-MM-dd HH:mm:ss",
            "createDate",
            "updateDate").execute();
        if (purchaseApplication != null) {
            purchaseApplicationService.insertOrUpdate(purchaseApplication);
        }
        return "index";
    }
}
