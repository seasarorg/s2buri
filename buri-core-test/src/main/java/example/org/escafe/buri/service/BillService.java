package example.org.escafe.buri.service;

import static example.org.escafe.buri.names.BillNames.billId;
import static org.seasar.extension.jdbc.operation.Operations.in;

import java.util.List;

import example.org.escafe.buri.entity.Bill;

/**
 * {@link Bill}のサービスクラスです。
 * 
 * @author S2JDBC-Gen
 * @suppresshack com.google.code.hack.ej2.ToStringRewriter
 */
public class BillService extends AbstractService<Bill> {
	public Bill getBill(Long billId) {
		return select().id(billId).getSingleResult();
	}

	public List<Bill> getBillByIds(List<Long> billIds) {
		return select().where(in(billId(), billIds)).getResultList();
	}
}
