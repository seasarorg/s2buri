/*
 * 作成日: 2006/07/17
 *
 */
package org.seasar.buri.bao;

import java.util.List;

import org.seasar.buri.annotation.Buri;
import org.seasar.buri.annotation.BuriAction;
import org.seasar.buri.annotation.BuriActivity;
import org.seasar.buri.annotation.BuriUserInfo;

import example.org.seasar.buri.dto.ItemDto;

@Buri(process = "個人情報管理.個人情報管理", dtoClass = ItemDto.class)
@BuriUserInfo("buriUser")
public interface PrivacyInfoManagementBao {

	@BuriActivity("開示承認待ち")
	List getWaitingIndicationRecognition();

	@BuriActivity("開示承認")
	List getIndicationRecognition();

	@BuriActivity("情報破棄確認待ち")
	List getWaitingAbandonmentCheck();

	@BuriActivity("情報破棄確認済み")
	List getFinishingAbandonmentCheck();

	@BuriActivity("情報返却確認待ち")
	List getWaitingReturnCheck();

	@BuriActivity("情報返却確認済み")
	List getFinishingReturnCheck();

	@BuriActivity("開示依頼")
	void indicationRequest(ItemDto dto);

	@BuriActivity("開示承認待ち")
	void waitingIndicationRecognition(ItemDto dto);

	@BuriActivity("開示承認")
	@BuriAction("drop")
	void indicationRecognitionAbandonment(ItemDto dto);

	@BuriActivity("開示承認")
	@BuriAction("return")
	void indicationRecognitionReturn(ItemDto dto);

	@BuriActivity("情報破棄確認待ち")
	void waitingAbandonmentCheck(ItemDto dto);

	@BuriActivity("情報返却確認待ち")
	void waitingReturnCheck(ItemDto dto);

}
