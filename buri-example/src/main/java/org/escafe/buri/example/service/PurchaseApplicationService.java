package org.escafe.buri.example.service;

import java.util.List;
import javax.annotation.Generated;
import org.escafe.buri.example.entity.PurchaseApplication;

import static org.escafe.buri.example.names.PurchaseApplicationNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

/**
 * {@link PurchaseApplication}のサービスクラスです。
 * 
 * @author S2JDBC-Gen
 * @suppresshack com.google.code.hack.ej2.ToStringRewriter
 */
public class PurchaseApplicationService extends AbstractService<PurchaseApplication> {

    /**
     * 識別子でエンティティを検索します。
     * 
     * @param purchaseApplicationId
     *            識別子
     * @return エンティティ
     */
    public PurchaseApplication findById(Long purchaseApplicationId) {
        return select().id(purchaseApplicationId).getSingleResult();
    }

    /**
     * 識別子とバージョン番号でエンティティを検索します。
     * 
     * @param purchaseApplicationId
     *            識別子
     * @param versionNo
     *            バージョン番号
     * @return エンティティ
     */
    public PurchaseApplication findByIdVersion(Long purchaseApplicationId, Long versionNo) {
        return select().id(purchaseApplicationId).version(versionNo).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<PurchaseApplication> findAllOrderById() {
        return select().orderBy(asc(purchaseApplicationId())).getResultList();
    }
}