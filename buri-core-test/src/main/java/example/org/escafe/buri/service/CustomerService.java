package example.org.escafe.buri.service;

import static example.org.escafe.buri.names.CustomerNames.customerId;
import static org.seasar.extension.jdbc.operation.Operations.in;

import java.util.List;

import example.org.escafe.buri.entity.Customer;

/**
 * {@link Customer}のサービスクラスです。
 * 
 * @author S2JDBC-Gen
 * @suppresshack com.google.code.hack.ej2.ToStringRewriter
 */
public class CustomerService extends AbstractService<Customer> {
	public Customer getCustomer(Long customerId) {
		return select().id(customerId).getSingleResult();
	}

	public List<Customer> getCustomerByIds(List<Long> customerIds) {
		return select().where(in(customerId(), customerIds)).getResultList();
	}
}