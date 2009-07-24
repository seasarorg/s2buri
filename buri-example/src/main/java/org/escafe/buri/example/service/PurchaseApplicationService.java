package org.escafe.buri.example.service;

import static org.escafe.buri.example.names.PurchaseApplicationNames.purchaseApplicationId;
import static org.seasar.extension.jdbc.operation.Operations.asc;
import static org.seasar.extension.jdbc.operation.Operations.in;

import java.util.List;

import org.escafe.buri.example.entity.PurchaseApplication;

/**
 * {@link PurchaseApplication}のサービスクラスです。
 * 
 * @author S2JDBC-Gen
 * @suppresshack com.google.code.hack.ej2.ToStringRewriter
 */
public class PurchaseApplicationService extends
        AbstractService<PurchaseApplication> {

    public List<PurchaseApplication> findByIds(List<Long> ids) {
        return select().where(in(purchaseApplicationId(), ids)).getResultList();
    }

    public PurchaseApplication findById(Long purchaseApplicationId) {
        return select().id(purchaseApplicationId).getSingleResult();
    }

    public List<PurchaseApplication> findAllOrderById() {
        return select().orderBy(asc(purchaseApplicationId())).getResultList();
    }
}