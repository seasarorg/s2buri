package org.escafe.buri.mail;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rokugen
 *
 */
public class MailAttributes {
	private String from;
	private List<String> to;
	private List<String> ontTo;
	private List<String> cc;
	private List<String> bcc;
	private String content;
	private String subject;
	private String header;	
	
	public MailAttributes(){
		to = new ArrayList<String>();
		ontTo = new ArrayList<String>();
		cc = new ArrayList<String>();
		bcc = new ArrayList<String>();
	}
	
	public void addTo(String value){
		to.add(value);		
	}
	public void addOntTo(String value){
		ontTo.add(value);		
	}
	public void addCc(String value){
		cc.add(value);		
	}
	public void addBcc(String value){
		bcc.add(value);		
	}
	
	public List<String> getBcc() {
		return bcc;
	}
	public void setBcc(List<String> bcc) {
		this.bcc = bcc;
	}
	public List<String> getCc() {
		return cc;
	}
	public void setCc(List<String> cc) {
		this.cc = cc;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	public List<String> getOntTo() {
		return ontTo;
	}
	public void setOntTo(List<String> ontTo) {
		this.ontTo = ontTo;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public List<String> getTo() {
		return to;
	}
	public void setTo(List<String> to) {
		this.to = to;
	}

}
