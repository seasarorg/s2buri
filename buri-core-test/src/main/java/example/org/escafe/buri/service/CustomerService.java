package example.org.escafe.buri.service;

import example.org.escafe.buri.entity.Customer;
import java.util.List;

import static example.org.escafe.buri.names.CustomerNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

/**
 * {@link Customer}のサービスクラスです。
 * 
 * @author S2JDBC-Gen
 * @suppresshack com.google.code.hack.ej2.ToStringRewriter
 */
public class CustomerService extends AbstractService<Customer> {

    /**
     * 識別子でエンティティを検索します。
     * 
     * @param customerId
     *            識別子
     * @return エンティティ
     */
    public Customer findById(Long customerId) {
        return select().id(customerId).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<Customer> findAllOrderById() {
        return select().orderBy(asc(customerId())).getResultList();
    }
}