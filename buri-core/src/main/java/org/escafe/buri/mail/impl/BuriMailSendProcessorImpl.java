package org.escafe.buri.mail.impl;

import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.InternetAddress;

import org.escafe.buri.common.util.template.TextTemplate;
import org.escafe.buri.engine.BuriUserContext;
import org.escafe.buri.mail.BuriMailSendProcessor;
import org.escafe.buri.mail.MailAttributes;
import org.escafe.buri.mail.mai.BuriMai;
import org.escafe.buri.mail.mai.BuriMaiDto;
import org.escafe.buri.mail.util.BuriMailUtil;
import org.seasar.framework.util.StringUtil;

/**
 * @author rokugen
 * 
 */
public class BuriMailSendProcessorImpl implements BuriMailSendProcessor {
	private static final String ADDRESS_DELIMITER = " ";

	private BuriMai buriMai;

	private TextTemplate textTemplate;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.escafe.buri.mail.BuriMailSendProcessor#sendMail(org.escafe.buri.mail.MailAttributes,
	 *      org.escafe.buri.engine.BuriUserContext)
	 */
	public void sendMail(MailAttributes attr, BuriUserContext userContext) {
		BuriMaiDto dto = createDto(attr, userContext);
		sendMailAtOnce(attr, userContext, dto);		
		sendMailIndividually(attr, userContext, dto);		
	}
	
	protected void sendMailAtOnce(MailAttributes attr, BuriUserContext userContext, BuriMaiDto dto){
		if(attr.getTo() != null && attr.getTo().size() > 0){
			dto.setTo(buildInternetAddressList(attr.getTo(), userContext));
			buriMai.sendMail(dto);
		}
	}
	
	protected void sendMailIndividually(MailAttributes attr, BuriUserContext userContext, BuriMaiDto dto){
		List<InternetAddress> toList = new ArrayList<InternetAddress>();
		for(String to : attr.getContTo()){
			toList.clear();
			toList.add(buildInternetAddress(to, userContext));
			dto.setTo(toList);
			buriMai.sendMail(dto);
		}		
	}

	protected BuriMaiDto createDto(MailAttributes attr,BuriUserContext userContext) {
		BuriMaiDto dto = new BuriMaiDto();
		dto.setFrom(buildInternetAddress(attr.getFrom(), userContext));		
		dto.setCc(buildInternetAddressList(attr.getCc(), userContext));
		dto.setBcc(buildInternetAddressList(attr.getBcc(), userContext));
		dto.setContent(templateProcess(attr.getContent(), userContext));
		dto.setSubject(templateProcess(attr.getSubject(), userContext));
		dto.setXHeader(templateProcess(attr.getHeader(), userContext));		

		return dto;
	}

	protected List<InternetAddress> buildInternetAddressList(
			List<String> attrList, BuriUserContext userContext) {
		List<InternetAddress> iaList = new ArrayList<InternetAddress>();
		for (String attr : attrList) {
			iaList.add(buildInternetAddress(attr, userContext));
		}
		return iaList;
	}


	protected InternetAddress buildInternetAddress(String attr,BuriUserContext userContext) {
		String str = templateProcess(attr, userContext);
		if (StringUtil.isEmpty(str)) {
			return null;
		}
		AddressInfo addressInfo = convertToAddressInfo(str);
		
		return BuriMailUtil.createInternetAddress(addressInfo.address, addressInfo.personal);
	}
	
	protected AddressInfo convertToAddressInfo(String str){
		AddressInfo ai = new AddressInfo();
		str = str.trim();
		int idx = str.indexOf(ADDRESS_DELIMITER);
		if (idx < 0) {
			ai.address = str;
			ai.personal = "";
		} else {
			ai.address = str.substring(0, idx);
			ai.personal = str.substring(idx + ADDRESS_DELIMITER.length());
		}
		return ai;
	}


	protected String templateProcess(String templateText, BuriUserContext data) {
		if (StringUtil.isEmpty(templateText)) {
			return null;
		}
		return textTemplate.process(templateText, data);
	}

	public void setBuriMai(BuriMai buriMai) {
		this.buriMai = buriMai;
	}

	public void setTextTemplate(TextTemplate textTemplate) {
		this.textTemplate = textTemplate;
	}
	
	private class AddressInfo{
		private String address;
		private String personal;
	}

}
