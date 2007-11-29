package org.escafe.buri.mail;

import org.escafe.buri.engine.BuriUserContext;

/**
 * @author rokugen
 *
 */
public interface BuriMailSendProcessor {
	/**
	 * メール設定にFreeMarkerでデータ埋め込みをして、メール送信します。
	 * @param attr メール設定
	 * @param userContext ユーザーコンテキスト
	 */
	void sendMail(MailAttributes attr, BuriUserContext userContext);

}
