/*
 * 作成日: 2006/07/17
 *
 */
package example.org.escafe.buri.annotation.bao;

import java.util.List;

import org.escafe.buri.annotation.Buri;
import org.escafe.buri.annotation.BuriAction;
import org.escafe.buri.annotation.BuriActivity;
import org.escafe.buri.annotation.BuriUserInfo;

import example.org.escafe.buri.entity.Item;

@Buri(process = "個人情報管理.個人情報管理", dtoClass = Item.class)
@BuriUserInfo("buriUser")
public interface PrivacyInfoManagementBao {
	@BuriActivity("開示承認待ち")
	List<Item> getWaitingIndicationRecognition();

	@BuriActivity("開示承認")
	List<Item> getIndicationRecognition();

	@BuriActivity("情報破棄確認待ち")
	List<Item> getWaitingAbandonmentCheck();

	@BuriActivity("情報破棄確認済み")
	List<Item> getFinishingAbandonmentCheck();

	@BuriActivity("情報返却確認待ち")
	List<Item> getWaitingReturnCheck();

	@BuriActivity("情報破棄確認済み")
	List<Item> getFinishingReturnCheck();

	@BuriActivity("開示依頼")
	void indicationRequest(Item dto);

	@BuriActivity("開示承認待ち")
	void waitingIndicationRecognition(Item dto);

	@BuriActivity("開示承認")
	@BuriAction("drop")
	void indicationRecognitionAbandonment(Item dto);

	@BuriActivity("開示承認")
	@BuriAction("return")
	void indicationRecognitionReturn(Item dto);

	@BuriActivity("情報破棄確認待ち")
	void waitingAbandonmentCheck(Item dto);

	@BuriActivity("情報返却確認待ち")
	void waitingReturnCheck(Item dto);
}
