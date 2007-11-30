package org.escafe.buri.component.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.escafe.buri.component.BuriComponent;
import org.escafe.buri.oouo.internal.structure.BuriExtendedAttributeType;
import org.escafe.buri.oouo.internal.structure.BuriToolType;
import org.seasar.framework.util.StringUtil;

/**
 * MailSender用BuriComponent
 * @author rokugen
 *
 */
public class BuriMailSenderComponentImpl implements BuriComponent {
	private static final String FROM = "from";
	private static final String TO = "to";
	private static final String CONT_TO = "contTo";
	private static final String CC = "cc";
	private static final String BCC = "bcc";
	private static final String CONTENT = "content";
	private static final String SUBJECT = "subject";
	private static final String HEADER = "header";
	
	private Map<String, String> writeMethodMap;
	
	public BuriMailSenderComponentImpl(){
		writeMethodMap = new HashMap<String, String>();
		writeMethodMap.put(FROM, "setFrom");
		writeMethodMap.put(TO, "addTo");
		writeMethodMap.put(CONT_TO, "addContTo");
		writeMethodMap.put(CC, "addCc");
		writeMethodMap.put(BCC, "addBcc");
		writeMethodMap.put(CONTENT, "setContent");
		writeMethodMap.put(SUBJECT, "setSubject");
		writeMethodMap.put(HEADER, "setHeader");		
		
	}
	
	
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
        
        String writeMethod = writeMethodMap.get(name);
        if(StringUtil.isEmpty(writeMethod) == false){
        	sb.append("    mail.").append(writeMethod).append("(\"").append(value).append("\");\n");
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
