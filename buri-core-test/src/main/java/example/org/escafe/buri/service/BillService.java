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
	/**
	 * IDから{@link Bill}エンティティを検索し返します。
	 * 
	 * @param billId
	 *            {@link Bill}のID
	 * @return {@link Bill}
	 */
	public Bill getBill(Long billId) {
		return select().id(billId).getSingleResult();
	}

	/**
	 * 複数のIDから{@link Bill}エンティティを検索し返します。
	 * 
	 * @param billIds
	 *            {@link Bill}のIDのリスト
	 * @return {@link Bill}のリスト
	 */
	public List<Bill> getBillByIds(List<Long> billIds) {
		return select().where(in(billId(), billIds)).getResultList();
	}
}
