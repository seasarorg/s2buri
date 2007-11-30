package org.escafe.buri.mail.impl;

import javax.mail.internet.InternetAddress;

import org.escafe.buri.common.util.template.exception.TemplateRuntimeException;
import org.escafe.buri.engine.BuriUserContext;
import org.escafe.buri.mail.MailAttributes;
import org.seasar.mai.unit.S2MaiTestCase;

import com.ozacc.mail.Mail;

public class BuriMailSendProcessorImplTest extends S2MaiTestCase {
	private static final String PATH = "BuriMailTest.dicon";
	private BuriMailSendProcessorImpl processor;

	public BuriMailSendProcessorImplTest(String arg0) {
		super(arg0);
	}

	protected void setUp() throws Exception {
		include(PATH);
		super.setUp();
	}
	
	public void testメール1回送信() throws Exception{
		UserData userData = new UserData();
		userData.setFromAddress("from@example.com");
		userData.setToAddress("to1@example.com");
		userData.setCcAddress("cc1@example.com CCの人の名前 スペース含む");
		userData.setBccAddress("BCCの人の名前");
		userData.setContentText("埋め込み本文");
		userData.setSubjectText("埋め込み件名");
		
		
		BuriUserContext userContext = new BuriUserContext();
		userContext.setData(userData);
		
		MailAttributes attri = new MailAttributes();
		attri.setFrom("${data.fromAddress} from_name");
		attri.addTo("${data.toAddress} to_name");
		attri.addTo("to2@example.com");		
		attri.addCc("${data.ccAddress}");
		attri.addBcc("bcc@example.com ${data.bccAddress}");
		attri.setContent("${data.contentText}ですよ");
		attri.setSubject("${data.subjectText}である");
		
		
		Mail expected = new Mail();
		expected.setFrom(new InternetAddress("from@example.com","from_name"));
		expected.addTo(new InternetAddress("to1@example.com","to_name"));
		expected.addTo(new InternetAddress("to2@example.com",""));
		expected.addCc(new InternetAddress("cc1@example.com","CCの人の名前 スペース含む"));
		expected.addBcc(new InternetAddress("bcc@example.com","BCCの人の名前"));
		expected.setText("埋め込み本文ですよ");		
		expected.setSubject("埋め込み件名である");
		
		processor.sendMail(attri, userContext);
		
		Mail actual = getActualMail(0);
		
		assertEquals(expected, actual);
		
	}
	
	public void testAttributeに書いたFreeMarker式の値がない場合は例外(){
		UserData userData = new UserData();
		BuriUserContext userContext = new BuriUserContext();
		userContext.setData(userData);
		
		MailAttributes attri = new MailAttributes();		
		attri.setContent("${data.contentText}ですよ");		
		
		try{
			processor.sendMail(attri, userContext);
			fail();
		}catch (TemplateRuntimeException e) {
			assertTrue(true);
		}
		//!を書くとnullでもOK
		attri.setContent("${data.contentText!}ですよ");
		try{
			processor.sendMail(attri, userContext);
			assertTrue(true);
		}catch (TemplateRuntimeException e) {
			fail();
		}
		
		
	}
	
	public void testメール1回送信と連続送信混在() throws Exception{
		UserData userData = new UserData();
		userData.setFromAddress("from@example.com");
		userData.setToAddress("to1@example.com");
		userData.setCcAddress("cc1@example.com CCの人の名前 スペース含む");
		userData.setBccAddress("BCCの人の名前");
		
		BuriUserContext userContext = new BuriUserContext();
		userContext.setData(userData);
		
		MailAttributes attri = new MailAttributes();
		attri.setFrom("${data.fromAddress} from_name");
		attri.addTo("${data.toAddress} to_name");
		attri.addOntTo("to2@example.com");		
		attri.addOntTo("${data.ccAddress}");
		attri.addOntTo("bcc@example.com ${data.bccAddress}");
		
		processor.sendMail(attri, userContext);
		
		Mail expected = new Mail();
		expected.setFrom(new InternetAddress("from@example.com","from_name"));
		expected.addTo(new InternetAddress("to1@example.com","to_name"));
		expected.setText("");
		
		Mail actual = getActualMail(0);
		
		assertEquals(expected, actual);
		
	}	
	
	
	public class UserData{
		private String toAddress;
		private String fromAddress;
		private String ccAddress;
		private String bccAddress;
		private String contentText;
		private String headerText;
		private String subjectText;
		
		public String getBccAddress() {
			return bccAddress;
		}
		public void setBccAddress(String bccAddress) {
			this.bccAddress = bccAddress;
		}
		public String getCcAddress() {
			return ccAddress;
		}
		public void setCcAddress(String ccAddress) {
			this.ccAddress = ccAddress;
		}
		public String getContentText() {
			return contentText;
		}
		public void setContentText(String contentText) {
			this.contentText = contentText;
		}
		public String getFromAddress() {
			return fromAddress;
		}
		public void setFromAddress(String fromAddress) {
			this.fromAddress = fromAddress;
		}
		public String getHeaderText() {
			return headerText;
		}
		public void setHeaderText(String headerText) {
			this.headerText = headerText;
		}
		public String getSubjectText() {
			return subjectText;
		}
		public void setSubjectText(String subjectText) {
			this.subjectText = subjectText;
		}
		public String getToAddress() {
			return toAddress;
		}
		public void setToAddress(String toAddress) {
			this.toAddress = toAddress;
		}
		
		
		
	}
	
	

}
