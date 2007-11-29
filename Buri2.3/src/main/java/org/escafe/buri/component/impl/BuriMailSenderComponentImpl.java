package org.escafe.buri.component.impl;

import java.util.Iterator;
import java.util.Set;

import org.escafe.buri.component.BuriComponent;
import org.escafe.buri.oouo.internal.structure.BuriExtendedAttributeType;
import org.escafe.buri.oouo.internal.structure.BuriToolType;

/**
 * MailSender用BuriComponent
 * @author rokugen
 *
 */
public class BuriMailSenderComponentImpl implements BuriComponent {
	private static final String FROM = "from";
	private static final String TO = "to";
	private static final String ONT_TO = "ontTo";
	private static final String CC = "cc";
	private static final String BCC = "bcc";
	private static final String CONTENT = "content";
	private static final String SUBJECT = "subject";
	private static final String HEADER = "header";
	
	
	/* (non-Javadoc)
	 * @see org.escafe.buri.component.BuriComponent#getBuriExecuteSource(java.lang.String, org.escafe.buri.oouo.internal.structure.BuriToolType)
	 */
	public String getBuriExecuteSource(String tgtObjName, BuriToolType tool) {
		StringBuffer sb = new StringBuffer();
		sb.append("org.escafe.buri.mail.MailAttributes mail = new org.escafe.buri.mail.MailAttributes();\n");
		Iterator ite = tool.getExtendedAttribute().iterator();
        while (ite.hasNext()) {
            BuriExtendedAttributeType attri = (BuriExtendedAttributeType) ite.next();
            appendSourceByAttribute(attri, sb);            
        }        
        
        sb.append("    org.escafe.buri.mail.BuriMailSendProcessor processor =\n");
        sb.append("        org.escafe.buri.mail.util.BuriMailUtil.getSendProcessor(sysContext);\n");
        sb.append("    processor.sendMail(mail, sysContext.getUserContext());\n\n");
		return sb.toString();
	}
	
	protected void appendSourceByAttribute(BuriExtendedAttributeType attri, StringBuffer sb){
		String name = attri.getName();
        String value = attri.getValue();
        value = convertTextToSource(value);
        
        if(FROM.equalsIgnoreCase(name)){
        	sb.append("    mail.setFrom(\"").append(value).append("\");\n");
        }else if(TO.equalsIgnoreCase(name)){
        	sb.append("    mail.addTo(\"").append(value).append("\");\n");
        }else if(ONT_TO.equalsIgnoreCase(name)){
        	sb.append("    mail.addOntTo(\"").append(value).append("\");\n");
        }else if(CC.equalsIgnoreCase(name)){
        	sb.append("    mail.addCc(\"").append(value).append("\");\n");
        }else if(BCC.equalsIgnoreCase(name)){
        	sb.append("    mail.addBcc(\"").append(value).append("\");\n");
        }else if(CONTENT.equalsIgnoreCase(name)){
        	sb.append("    mail.setContent(\"").append(value).append("\");\n");
        }else if(SUBJECT.equalsIgnoreCase(name)){
        	sb.append("    mail.setSubject(\"").append(value).append("\");\n");
        }else if(HEADER.equalsIgnoreCase(name)){
        	sb.append("    mail.setHeader(\"").append(value).append("\");\n");
        }        
        		
	}


	public Set appendImportName(BuriToolType tool) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public String convertTextToSource(String text) {
        text = text.replaceAll("\\\\", "\\\\\\\\");
        text = text.replaceAll("\"", "\\\\\"");
        text = text.replaceAll("\n", "\\\\n");
        return text;
    }

	public String setupSource() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

}
