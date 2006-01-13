/*
 * çÏê¨ì˙: 2005/09/15
 *
 */
package org.seasar.buri.component.impl;

import java.io.File;
import java.util.Map;

import org.seasar.buri.common.util.ScriptProcessor;
import org.seasar.buri.common.util.template.TextTemplate;
import org.seasar.buri.component.BuriComponent3;
import org.seasar.buri.component.OgnlInvoker;
import org.seasar.buri.context.ContextUtil;
import org.seasar.buri.xpdl.util.ExtendedAttributeUtil;
import org.seasar.buri.xpdl.util.ToolTagSelect;
import org.seasar.framework.log.Logger;

import com.ozacc.mail.Mail;
import com.ozacc.mail.SendMail;

public class MailSenderImpl implements BuriComponent3 {
    private TextTemplate textTemplate;
    private SendMail sendMail;
    private OgnlInvoker ognlInvoker;
    private static Logger logger = Logger.getLogger(OgnlInvokerImpl.class);
    private ExtendedAttributeUtil attributeUtil;
    private ContextUtil contextUtil;

    public void buriExecute(ScriptProcessor processor, ToolTagSelect tool,Map contextData) {
        Map context = attributeUtil.get(tool);
        contextUtil.getContext().put("container",contextUtil.getLocalContext().getContainer());
        processOgnl("pre",context);
        Mail mail = createMail(context);
        if(logger.isDebugEnabled()) {
            logger.debug("send Mail " + mail.toString());
        }
        sendMail.send(mail);
        processOgnl("after",context);
    }
    
    protected void processOgnl(String key,Map context) {
        String ognls = (String)context.get(key);
        if(ognls == null) {
            return;
        }
        if(logger.isDebugEnabled()) {
            logger.debug("Mail " + key + " Processed");
        }
        ScriptProcessor processor = new ScriptProcessor();
        ognlInvoker.processOgnls(ognls,processor,contextUtil.getContext());
    }
    
    protected Mail createMail(Map context) {
        Mail mail = new Mail();
        setFrom(mail,context);
        setTo(mail,context);
        setCc(mail,context);
        setBcc(mail,context);
        setHeader(mail,context);
        setAttachFile(mail,context);
        setSubject(mail,context);
        setBody(mail,context);
        return mail;
    }
    
    protected String[] convStrtoAdress(String adressAndName) {
        String result[] = new String[2];
        int pos = adressAndName.indexOf(" ");
        if(pos == -1) {
            result[0] = adressAndName;
            result[1] = null;
        } else {
            result[0] = adressAndName.substring(0,pos);
            result[1] = adressAndName.substring(pos);;
        }
        return result;
    }
    
    protected String getContextToProcessedStr(Map context,String key) {
        String processedStr = (String)context.get(key);
        if(processedStr != null) {
            processedStr = convertTemplateToStr(processedStr);
        }
        return processedStr;
    }
    
    protected String convertTemplateToStr(String template) {
        return textTemplate.process( template , contextUtil.getContext().getData() );
    }
    
    protected void setSubject(Mail mail,Map context) {
        String subjectStr = getContextToProcessedStr(context,"subject");
        mail.setSubject(subjectStr);
    }
    
    protected void setBody(Mail mail,Map context) {
        String bodyStr = getContextToProcessedStr(context,"body");
        mail.setText(bodyStr);
    }
    
    protected void setFrom(Mail mail,Map context) {
        String fromStr = getContextToProcessedStr(context,"from");
        String[] froms = convStrtoAdress(fromStr);
        mail.setFrom(froms[0],froms[1]);
    }
    
    protected void setTo(Mail mail,Map context) {
        MultiDataAddMailAdress addAdress = new MultiDataAddMailAdress() {
            protected void add(Mail mail,String splitStrs[]) {
                mail.addTo(splitStrs[0],splitStrs[1]);
            }
        };
        addAdress.setStr(mail,context,"to");
    }
    
    protected void setCc(Mail mail,Map context) {
        MultiDataAddMailAdress addAdress = new MultiDataAddMailAdress() {
            protected void add(Mail mail,String splitStrs[]) {
                mail.addCc(splitStrs[0],splitStrs[1]);
            }
        };
        addAdress.setStr(mail,context,"cc");
    }
    
    protected void setBcc(Mail mail,Map context) {
        MultiDataAddMailAdress addAdress = new MultiDataAddMailAdress() {
            protected void add(Mail mail,String splitStrs[]) {
                mail.addBcc(splitStrs[0]);
            }
        };
        addAdress.setStr(mail,context,"bcc");
    }
    
    protected void setHeader(Mail mail,Map context) {
        MultiDataAddMailAdress addAdress = new MultiDataAddMailAdress() {
            protected void add(Mail mail,String splitStrs[]) {
                mail.addXHeader(splitStrs[0],splitStrs[1]);
            }
        };
        addAdress.setStr(mail,context,"headers");
    }
    
    protected void setAttachFile(Mail mail,Map context) {
        MultiDataAddMailAdress addAdress = new MultiDataAddMailAdress() {
            protected void add(Mail mail,String splitStrs[]) {
                File attach = new File(splitStrs[1]);
                mail.addFile(attach,splitStrs[0]);
            }
        };
        addAdress.setStr(mail,context,"attachFile");
    }
    
    private abstract class MultiDataAddMailAdress {
        protected abstract void add(Mail mail,String splitStrs[]);
        public void setStr(Mail mail,Map context,String key) {
            String strs = getContextToProcessedStr(context,key);
            if(strs == null) {
                return;
            }
            String dataStrLines[] = strs.split("\n");
            for(int i=0 ; i < dataStrLines.length ; i++) {
                String splitStrs[] = convStrtoAdress(dataStrLines[i]);
                add(mail,splitStrs);
            }
        }
    }

    public SendMail getSendMail() {
        return sendMail;
    }

    public void setSendMail(SendMail sendMail) {
        this.sendMail = sendMail;
    }

    public TextTemplate getTextTemplate() {
        return textTemplate;
    }

    public void setTextTemplate(TextTemplate textTemplate) {
        this.textTemplate = textTemplate;
    }

    public OgnlInvoker getOgnlInvoker() {
        return ognlInvoker;
    }

    public void setOgnlInvoker(OgnlInvoker ognlInvoker) {
        this.ognlInvoker = ognlInvoker;
    }

    public ExtendedAttributeUtil getAttributeUtil() {
        return attributeUtil;
    }

    public void setAttributeUtil(ExtendedAttributeUtil attributeUtil) {
        this.attributeUtil = attributeUtil;
    }

    public ContextUtil getContextUtil() {
        return contextUtil;
    }

    public void setContextUtil(ContextUtil contextUtil) {
        this.contextUtil = contextUtil;
    }
    

}
