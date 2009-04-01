package org.escafe.buri.mail.mai;

/**
 * S2Maiの機能を使ってメール送信するインタフェースです。
 * @author rokugen
 *
 */
public interface BuriMai {
	/**
	 * メールを送信します。
	 * @param dto 送信内容・メール設定を保持するDto。
	 */
	void sendMail(BuriMaiDto dto);

}
