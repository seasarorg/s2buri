/*
 * 作成日: 2006/07/17
 *
 */
package org.escafe.buri.bao;

import java.util.List;

import example.org.escafe.buri.dto.ItemDto;

public interface PrivacyInfoManagementBao {
	static Class TARGETDTO = ItemDto.class;

	static String PROCESS = "個人情報管理.個人情報管理";

	static String USERINFO = "buriTestUser";

	static BuriConvert CONVERTER[] = new BuriConvert[] {};

	static String getWaitingIndicationRecognition_ACTIVITY = "開示承認待ち";

	List getWaitingIndicationRecognition();

	static String getIndicationRecognition_ACTIVITY = "開示承認";

	List getIndicationRecognition();

	static String getWaitingAbandonmentCheck_ACTIVITY = "情報破棄確認待ち";

	List getWaitingAbandonmentCheck();

	static String getFinishingAbandonmentCheck_ACTIVITY = "情報破棄確認済み";

	List getFinishingAbandonmentCheck();

	static String getWaitingReturnCheck_ACTIVITY = "情報返却確認待ち";

	List getWaitingReturnCheck();

	static String getFinishingReturnCheck_ACTIVITY = "情報返却確認済み";

	List getFinishingReturnCheck();

	static String indicationRequest_ACTIVITY = "開示依頼";

	void indicationRequest(ItemDto dto);

	static String waitingIndicationRecognition_ACTIVITY = "開示承認待ち";

	void waitingIndicationRecognition(ItemDto dto);

	static String indicationRecognitionAbandonment_ACTIVITY = "開示承認";

	public static String indicationRecognitionAbandonment_ACTION = "drop";

	void indicationRecognitionAbandonment(ItemDto dto);

	static String indicationRecognitionReturn_ACTIVITY = "開示承認";

	public static String indicationRecognitionReturn_ACTION = "return";

	void indicationRecognitionReturn(ItemDto dto);

	static String waitingAbandonmentCheck_ACTIVITY = "情報破棄確認待ち";

	void waitingAbandonmentCheck(ItemDto dto);

	static String waitingReturnCheck_ACTIVITY = "情報返却確認待ち";

	void waitingReturnCheck(ItemDto dto);
}
