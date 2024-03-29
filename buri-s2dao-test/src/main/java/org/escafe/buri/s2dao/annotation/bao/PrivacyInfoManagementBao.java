/*
 * 作成日: 2006/07/17
 *
 */
package org.escafe.buri.s2dao.annotation.bao;

import java.util.List;

import org.escafe.buri.annotation.Buri;
import org.escafe.buri.annotation.BuriAction;
import org.escafe.buri.annotation.BuriActivity;
import org.escafe.buri.annotation.BuriUserInfo;

import example.org.escafe.buri.dto.ItemDto;

@Buri(process = "個人情報管理.個人情報管理", dtoClass = ItemDto.class)
@BuriUserInfo("buriTestUser")
public interface PrivacyInfoManagementBao {
	@BuriActivity("開示承認待ち")
	List<ItemDto> getWaitingIndicationRecognition();

	@BuriActivity("開示承認")
	List<ItemDto> getIndicationRecognition();

	@BuriActivity("情報破棄確認待ち")
	List<ItemDto> getWaitingAbandonmentCheck();

	@BuriActivity("情報破棄確認済み")
	List<ItemDto> getFinishingAbandonmentCheck();

	@BuriActivity("情報返却確認待ち")
	List<ItemDto> getWaitingReturnCheck();

	@BuriActivity("情報破棄確認済み")
	List<ItemDto> getFinishingReturnCheck();

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
