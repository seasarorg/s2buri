/*
 * 作成日: 2006/07/17
 *
 */
package org.escafe.buri.annotation.bao;

import java.util.List;

import org.escafe.buri.bao.BuriConvert;

import example.org.escafe.buri.entity.Item;

public interface PrivacyInfoManagementBao {
	static Class<?> TARGETDTO = Item.class;

	static String PROCESS = "個人情報管理.個人情報管理";

	static String USERINFO = "buriTestUser";

	static BuriConvert CONVERTER[] = new BuriConvert[] {};

	static String getWaitingIndicationRecognition_ACTIVITY = "開示承認待ち";

	List<Item> getWaitingIndicationRecognition();

	static String getIndicationRecognition_ACTIVITY = "開示承認";

	List<Item> getIndicationRecognition();

	static String getWaitingAbandonmentCheck_ACTIVITY = "情報破棄確認待ち";

	List<Item> getWaitingAbandonmentCheck();

	static String getFinishingAbandonmentCheck_ACTIVITY = "情報破棄確認済み";

	List<Item> getFinishingAbandonmentCheck();

	static String getWaitingReturnCheck_ACTIVITY = "情報返却確認待ち";

	List<Item> getWaitingReturnCheck();

	static String getFinishingReturnCheck_ACTIVITY = "情報返却確認済み";

	List<Item> getFinishingReturnCheck();

	static String indicationRequest_ACTIVITY = "開示依頼";

	void indicationRequest(Item dto);

	static String waitingIndicationRecognition_ACTIVITY = "開示承認待ち";

	void waitingIndicationRecognition(Item dto);

	static String indicationRecognitionAbandonment_ACTIVITY = "開示承認";

	public static String indicationRecognitionAbandonment_ACTION = "drop";

	void indicationRecognitionAbandonment(Item dto);

	static String indicationRecognitionReturn_ACTIVITY = "開示承認";

	public static String indicationRecognitionReturn_ACTION = "return";

	void indicationRecognitionReturn(Item dto);

	static String waitingAbandonmentCheck_ACTIVITY = "情報破棄確認待ち";

	void waitingAbandonmentCheck(Item dto);

	static String waitingReturnCheck_ACTIVITY = "情報返却確認待ち";

	void waitingReturnCheck(Item dto);
}
