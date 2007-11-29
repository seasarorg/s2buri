package org.escafe.buri.mail.impl;

import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.escafe.buri.engine.BuriUserContext;
import org.escafe.buri.mail.BuriMailSendProcessor;
import org.escafe.buri.mail.MailAttributes;
import org.escafe.buri.mail.mai.BuriMai;
import org.escafe.buri.mail.mai.BuriMaiDto;

/**
 * @author rokugen
 *
 */
public class BuriMailSendProcessorImpl implements BuriMailSendProcessor {
	private BuriMai buriMai;	

	
	/* (non-Javadoc)
	 * @see org.escafe.buri.mail.BuriMailSendProcessor#sendMail(org.escafe.buri.mail.MailAttributes, org.escafe.buri.engine.BuriUserContext)
	 */
	public void sendMail(MailAttributes attr, BuriUserContext userContext) {
		BuriMaiDto dto = createDto(attr);
		buriMai.sendMail(dto);		
	}

	
	protected BuriMaiDto createDto(MailAttributes attr){
		BuriMaiDto dto = new BuriMaiDto();		
		String to = attr.getTo().get(0);
		List<InternetAddress> toList = new ArrayList<InternetAddress>();
		try {
			toList.add(new InternetAddress(to));
			dto.setFrom(new InternetAddress("kei@moonfactory.co.jp"));
		} catch (AddressException e) {
			throw new RuntimeException(e);
		}
		dto.setTo(toList);
		dto.setContent(attr.getContent());
		dto.setSubject(attr.getSubject());
		return dto;
	}

	public void setBuriMai(BuriMai buriMai) {
		this.buriMai = buriMai;
	}


}
