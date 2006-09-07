/*
 * �쐬��: 2004/06/20
 *
 * ���̐������ꂽ�R�����g�̑}�������e���v���[�g��ύX���邽��
 * �E�B���h�E > �ݒ� > Java > �R�[�h���� > �R�[�h�ƃR�����g
 */
package org.seasar.buri.exception;

import org.seasar.framework.message.MessageFormatter;

/**
 * ��O
 * 
 * @author makoto
 */
public class BuriException extends RuntimeException {
	/**
     * 
     */
    private static final long serialVersionUID = -4766036207471069745L;
    private String messageCode_;
	private Object[] args_;
	private String message_;
	private String simpleMessage_;

	public BuriException(String messageCode) {
		this(messageCode, null, null);
	}

	public BuriException(String messageCode, Object[] args) {
		this(messageCode, args, null);
	}

	public BuriException(
		String messageCode,
		Object[] args,
		Throwable cause) {

		super(cause);
		messageCode_ = messageCode;
		args_ = args;
		simpleMessage_ = MessageFormatter.getSimpleMessage(messageCode_, args_);
		message_ = "[" + messageCode + "]" + simpleMessage_;
	}

	public final String getMessageCode() {
		return messageCode_;
	}

	public final Object[] getArgs() {
		return args_;
	}

	public final String getMessage() {
		return message_;
	}
	
	protected void setMessage(String message) {
		message_ = message;
	}
	
	public final String getSimpleMessage() {
		return simpleMessage_;
	}

}