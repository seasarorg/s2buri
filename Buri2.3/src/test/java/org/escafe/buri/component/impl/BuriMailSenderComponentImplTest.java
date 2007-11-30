package org.escafe.buri.component.impl;

import junit.framework.TestCase;

import org.escafe.buri.oouo.internal.structure.BuriExtendedAttributeType;
import org.escafe.buri.oouo.internal.structure.BuriToolType;

public class BuriMailSenderComponentImplTest extends TestCase {
	private BuriMailSenderComponentImpl componentImpl;

	public BuriMailSenderComponentImplTest(String name) {		
		super(name);
	}

	protected void setUp() throws Exception {
		componentImpl = new BuriMailSenderComponentImpl();
		super.setUp();
	}

	public void testソース吐き出し(){
		BuriToolType tool = new BuriToolType();
		BuriExtendedAttributeType attri = new BuriExtendedAttributeType();
		attri.setName("from");
		attri.setValue("from@example.com");
		tool.addExtendedAttribute(attri);
		attri = new BuriExtendedAttributeType();
		attri.setName("to");
		attri.setValue("to1@example.com");
		tool.addExtendedAttribute(attri);
		attri = new BuriExtendedAttributeType();
		attri.setName("to");
		attri.setValue("to2@example.com");
		tool.addExtendedAttribute(attri);
		attri = new BuriExtendedAttributeType();
		attri.setName("ontTo");
		attri.setValue("ontTo1@example.com");
		tool.addExtendedAttribute(attri);
		attri = new BuriExtendedAttributeType();
		attri.setName("ontTo");
		attri.setValue("ontTo2@example.com");
		tool.addExtendedAttribute(attri);
		attri = new BuriExtendedAttributeType();
		attri.setName("cc");
		attri.setValue("cc1@example.com");
		tool.addExtendedAttribute(attri);
		attri = new BuriExtendedAttributeType();
		attri.setName("cc");
		attri.setValue("cc2@example.com");
		tool.addExtendedAttribute(attri);
		attri = new BuriExtendedAttributeType();
		attri.setName("bcc");
		attri.setValue("bcc1@example.com");
		tool.addExtendedAttribute(attri);
		attri = new BuriExtendedAttributeType();
		attri.setName("content");
		attri.setValue("本文だー");
		tool.addExtendedAttribute(attri);
		attri = new BuriExtendedAttributeType();
		attri.setName("subject");
		attri.setValue("件名です");
		tool.addExtendedAttribute(attri);
		attri = new BuriExtendedAttributeType();
		attri.setName("header");
		attri.setValue("X-Mailer: hogeMail\nX-Hoge: hogehoge");
		tool.addExtendedAttribute(attri);
		
		
		StringBuffer sb = new StringBuffer();
		sb.append("org.escafe.buri.mail.MailAttributes mail = new org.escafe.buri.mail.MailAttributes();\n");
		sb.append("    mail.setFrom(\"from@example.com\");\n");
		sb.append("    mail.addTo(\"to1@example.com\");\n");
		sb.append("    mail.addTo(\"to2@example.com\");\n");
		sb.append("    mail.addOntTo(\"ontTo1@example.com\");\n");
		sb.append("    mail.addOntTo(\"ontTo2@example.com\");\n");
		sb.append("    mail.addCc(\"cc1@example.com\");\n");
		sb.append("    mail.addCc(\"cc2@example.com\");\n");
		sb.append("    mail.addBcc(\"bcc1@example.com\");\n");
		sb.append("    mail.setContent(\"本文だー\");\n");
		sb.append("    mail.setSubject(\"件名です\");\n");
		sb.append("    mail.setHeader(\"X-Mailer: hogeMail\\nX-Hoge: hogehoge\");\n");
		sb.append("    org.escafe.buri.mail.BuriMailSendProcessor processor =\n");
        sb.append("        org.escafe.buri.mail.util.BuriMailUtil.getSendProcessor(sysContext);\n");
        sb.append("    processor.sendMail(mail, sysContext.getUserContext());\n\n");
        
        String expected = sb.toString();
		
		String actual = componentImpl.getBuriExecuteSource("sysContext.getContainer()", tool);
		
		assertEquals(expected, actual);
		
		
	}
}
