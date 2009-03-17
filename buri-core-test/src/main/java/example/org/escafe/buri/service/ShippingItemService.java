package example.org.escafe.buri.service;

import example.org.escafe.buri.entity.ShippingItem;
import java.util.List;

import static example.org.escafe.buri.names.ShippingItemNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

/**
 * {@link ShippingItem}のサービスクラスです。
 * 
 * @author S2JDBC-Gen
 * @suppresshack com.google.code.hack.ej2.ToStringRewriter
 */
public class ShippingItemService extends AbstractService<ShippingItem> {

    /**
     * 識別子でエンティティを検索します。
     * 
     * @param shippingItemId
     *            識別子
     * @return エンティティ
     */
    public ShippingItem findById(Long shippingItemId) {
        return select().id(shippingItemId).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<ShippingItem> findAllOrderById() {
        return select().orderBy(asc(shippingItemId())).getResultList();
    }
}