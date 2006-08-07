/*
 * çÏê¨ì˙: 2005/05/16
 *
 */
package org.seasar.buri.exception;

/**
 * @author makotan
 *
 */
public class BuriNotSupportOperationException extends BuriException {

    /**
     * 
     */
    private static final long serialVersionUID = 3488624304112356196L;
    /**
     * @param messageCode
     */
    public BuriNotSupportOperationException(String messageCode) {
        super(messageCode);
    }
    /**
     * @param messageCode
     * @param args
     */
    public BuriNotSupportOperationException(String messageCode, Object[] args) {
        super(messageCode, args);
    }
    /**
     * @param messageCode
     * @param args
     * @param cause
     */
    public BuriNotSupportOperationException(String messageCode, Object[] args,
            Throwable cause) {
        super(messageCode, args, cause);
    }
}
