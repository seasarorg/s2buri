package example.org.escafe.buri.service;

import example.org.escafe.buri.entity.Bill;
import java.util.List;

import static example.org.escafe.buri.names.BillNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

/**
 * {@link Bill}のサービスクラスです。
 * 
 * @author S2JDBC-Gen
 * @suppresshack com.google.code.hack.ej2.ToStringRewriter
 */
public class BillService extends AbstractService<Bill> {

    /**
     * 識別子でエンティティを検索します。
     * 
     * @param billId
     *            識別子
     * @return エンティティ
     */
    public Bill findById(Long billId) {
        return select().id(billId).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<Bill> findAllOrderById() {
        return select().orderBy(asc(billId())).getResultList();
    }
}