/*
 * �쐬��: 2004/11/07
 *
 * ���̐������ꂽ�R�����g�̑}�������e���v���[�g��ύX���邽��
 * �E�B���h�E > �ݒ� > Java > �R�[�h���� > �R�[�h�ƃR�����g
 */
package org.seasar.buri.exception;


/**
 * @author makotan
 *
 * ���̐������ꂽ�R�����g�̑}�������e���v���[�g��ύX���邽��
 * �E�B���h�E > �ݒ� > Java > �R�[�h���� > �R�[�h�ƃR�����g
 */
public class BuriOGNLRuntimeException extends BuriException {

    /**
     * 
     */
    private static final long serialVersionUID = -1950910688122464073L;
    /**
     * @param messageCode
     */
    public BuriOGNLRuntimeException(String messageCode) {
        super(messageCode);
    }
    /**
     * @param messageCode
     * @param args
     */
    public BuriOGNLRuntimeException(String messageCode, Object[] args) {
        super(messageCode, args);
    }
    /**
     * @param messageCode
     * @param args
     * @param cause
     */
    public BuriOGNLRuntimeException(String messageCode, Object[] args,
            Throwable cause) {
        super(messageCode, args, cause);
    }
}
