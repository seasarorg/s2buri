/*
 * 作成日: 2005/05/16
 *
 */
package org.seasar.buri.exception;

/**
 * @author makotan
 *
 */
public class IllegalArgumentRuntimeException extends BuriOGNLRuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = -6610608293142526300L;

    /**
     * @param messageCode
     */
    public IllegalArgumentRuntimeException(String messageCode) {
        super(messageCode);
    }

    /**
     * @param messageCode
     * @param args
     */
    public IllegalArgumentRuntimeException(String messageCode, Object[] args) {
        super(messageCode, args);
    }

    /**
     * @param messageCode
     * @param args
     * @param cause
     */
    public IllegalArgumentRuntimeException(String messageCode, Object[] args,
            Throwable cause) {
        super(messageCode, args, cause);
    }

}
