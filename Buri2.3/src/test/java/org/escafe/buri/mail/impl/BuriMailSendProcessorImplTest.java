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
		userData.setHeaderText("hogeMailer");
		
		
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
		attri.setHeader("X-Mailer: ${data.headerText}\nX-Hoge: hogehoge");
		
		
		Mail expected = new Mail();
		expected.setFrom(new InternetAddress("from@example.com","from_name"));
		expected.addTo(new InternetAddress("to1@example.com","to_name"));
		expected.addTo(new InternetAddress("to2@example.com",""));
		expected.addCc(new InternetAddress("cc1@example.com","CCの人の名前 スペース含む"));
		expected.addBcc(new InternetAddress("bcc@example.com","BCCの人の名前"));
		expected.setText("埋め込み本文ですよ");		
		expected.setSubject("埋め込み件名である");
		expected.addHeader("X-Mailer", "hogeMailer");
		expected.addHeader("X-Hoge", "hogehoge");
		
		processor.sendMail(attri, userContext);
		
		Mail actual = getActualMail(0);
		
		assertEquals(expected, actual);
		
		//送信件数返すの作っておけばよかった・・・
		try{
			actual = getActualMail(1);
			fail();
		}catch (IndexOutOfBoundsException e) {
			assertTrue(true);
		}

		
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
	
	public void test連続送信() throws Exception{
		UserData userData = new UserData();
		userData.setFromAddress("from@example.com");
		userData.setToAddress("to1@example.com");
		userData.setCcAddress("cc1@example.com CCの人の名前 スペース含む");		
		
		BuriUserContext userContext = new BuriUserContext();
		userContext.setData(userData);
		
		MailAttributes attri = new MailAttributes();
		attri.setFrom("${data.fromAddress} from_name");
		attri.addContTo("${data.toAddress} to_name");
		attri.addContTo("to2@example.com");
		attri.addContTo("${data.ccAddress}");		
		
		processor.sendMail(attri, userContext);
		
		//1回目
		Mail expected = new Mail();
		expected.setFrom(new InternetAddress("from@example.com","from_name"));
		expected.addTo(new InternetAddress("to1@example.com","to_name"));
		expected.setText("");
		
		Mail actual = getActualMail(0);
		assertEquals(expected, actual);
		
		//2回目		
		expected.clearTo();
		expected.addTo(new InternetAddress("to2@example.com",""));	
		
		actual = getActualMail(1);		
		assertEquals(expected, actual);

		//3回目		
		expected.clearTo();
		expected.addTo(new InternetAddress("cc1@example.com","CCの人の名前 スペース含む"));	
		
		actual = getActualMail(2);		
		assertEquals(expected, actual);
		
		//送信件数返すの作っておけばよかった・・・
		try{
			actual = getActualMail(3);
			fail();
		}catch (IndexOutOfBoundsException e) {
			assertTrue(true);
		}

	}	
	
	public void test一括連続が混在() throws Exception{
		UserData userData = new UserData();
		userData.setFromAddress("from@example.com");
		userData.setToAddress("to1@example.com");
		userData.setCcAddress("cc1@example.com CCの人の名前 スペース含む");		
		
		BuriUserContext userContext = new BuriUserContext();
		userContext.setData(userData);
		
		MailAttributes attri = new MailAttributes();
		attri.setFrom("${data.fromAddress} from_name");
		attri.addTo("${data.toAddress} to_name");
		attri.addTo("toto@example.com");
		attri.addContTo("to2@example.com");
		attri.addContTo("${data.ccAddress}");		
		
		processor.sendMail(attri, userContext);
		
		//1回目
		Mail expected = new Mail();
		expected.setFrom(new InternetAddress("from@example.com","from_name"));
		expected.addTo(new InternetAddress("to1@example.com","to_name"));
		expected.addTo(new InternetAddress("toto@example.com",""));
		expected.setText("");
		
		Mail actual = getActualMail(0);
		assertEquals(expected, actual);
	
		//2回目		
		expected.clearTo();
		expected.addTo(new InternetAddress("to2@example.com",""));	
		
		actual = getActualMail(1);		
		assertEquals(expected, actual);

		//3回目		
		expected.clearTo();
		expected.addTo(new InternetAddress("cc1@example.com","CCの人の名前 スペース含む"));	
		
		actual = getActualMail(2);		
		assertEquals(expected, actual);
		
		//送信件数返すの作っておけばよかった・・・
		try{
			actual = getActualMail(3);
			fail();
		}catch (IndexOutOfBoundsException e) {
			assertTrue(true);
		}

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
