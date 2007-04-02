/*
 * 作成日: 2006/07/17
 *
 */
package org.seasar.buri.bao;

import java.util.List;

import org.seasar.buri.annotation.BuriAction;
import org.seasar.buri.annotation.BuriActivity;
import org.seasar.buri.annotation.BuriProcess;
import org.seasar.buri.annotation.BuriTargetDto;
import org.seasar.buri.annotation.BuriUserInfo;

import example.org.seasar.buri.dto.ItemDto;

@BuriProcess(name = "個人情報管理.個人情報管理")
@BuriTargetDto(dtoClass = ItemDto.class)
@BuriUserInfo(name = "buriUser")
public interface PrivacyInfoManagementBao {
	// static Class TARGETDTO = ItemDto.class;
	// static String PROCESS = "個人情報管理.個人情報管理";
	// static String USERINFO = "buriUser";

	// static BuriConvert CONVERTER[] = new BuriConvert[]{
	// };

	// static String getWaitingIndicationRecognition_ACTIVITY = "開示承認待ち";

	@BuriActivity(name = "開示承認待ち")
	List getWaitingIndicationRecognition();

	// static String getIndicationRecognition_ACTIVITY = "開示承認";

	@BuriActivity(name = "開示承認")
	List getIndicationRecognition();

	// static String getWaitingAbandonmentCheck_ACTIVITY = "情報破棄確認待ち";

	@BuriActivity(name = "情報破棄確認待ち")
	List getWaitingAbandonmentCheck();

	// static String getFinishingAbandonmentCheck_ACTIVITY = "情報破棄確認済み";

	@BuriActivity(name = "情報破棄確認済み")
	List getFinishingAbandonmentCheck();

	// static String getWaitingReturnCheck_ACTIVITY = "情報返却確認待ち";

	@BuriActivity(name = "情報返却確認待ち")
	List getWaitingReturnCheck();

	// static String getFinishingReturnCheck_ACTIVITY = "情報返却確認済み";

	@BuriActivity(name = "情報返却確認済み")
	List getFinishingReturnCheck();

	// static String indicationRequest_ACTIVITY = "開示依頼";

	@BuriActivity(name = "開示依頼")
	void indicationRequest(ItemDto dto);

	// static String waitingIndicationRecognition_ACTIVITY = "開示承認待ち";

	@BuriActivity(name = "開示承認待ち")
	void waitingIndicationRecognition(ItemDto dto);

	// static String indicationRecognitionAbandonment_ACTIVITY = "開示承認";

	// public static String indicationRecognitionAbandonment_ACTION = "drop";

	@BuriActivity(name = "開示承認")
	@BuriAction(name = "drop")
	void indicationRecognitionAbandonment(ItemDto dto);

	// static String indicationRecognitionReturn_ACTIVITY = "開示承認";

	// public static String indicationRecognitionReturn_ACTION = "return";

	@BuriActivity(name = "開示承認")
	@BuriAction(name = "return")
	void indicationRecognitionReturn(ItemDto dto);

	// static String waitingAbandonmentCheck_ACTIVITY = "情報破棄確認待ち";

	@BuriActivity(name = "情報破棄確認待ち")
	void waitingAbandonmentCheck(ItemDto dto);

	// static String waitingReturnCheck_ACTIVITY = "情報返却確認待ち";

	@BuriActivity(name = "情報返却確認待ち")
	void waitingReturnCheck(ItemDto dto);

}
