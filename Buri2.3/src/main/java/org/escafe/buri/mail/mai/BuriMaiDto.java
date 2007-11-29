package org.escafe.buri.mail.mai;

import java.util.List;

import javax.mail.internet.InternetAddress;

/**
 * メールの設定・送信内容を保持するDtoです。
 * @author rokugen
 *
 */
public class BuriMaiDto {	
	private InternetAddress from;
	private List<InternetAddress> to;
	private List<InternetAddress> cc;
	private List<InternetAddress> bcc;
	private String subject;
	private String content;
	
	public List<InternetAddress> getBcc() {
		return bcc;
	}
	public void setBcc(List<InternetAddress> bcc) {
		this.bcc = bcc;
	}
	public List<InternetAddress> getCc() {
		return cc;
	}
	public void setCc(List<InternetAddress> cc) {
		this.cc = cc;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public InternetAddress getFrom() {
		return from;
	}
	public void setFrom(InternetAddress from) {
		this.from = from;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public List<InternetAddress> getTo() {
		return to;
	}
	public void setTo(List<InternetAddress> to) {
		this.to = to;
	}

}
