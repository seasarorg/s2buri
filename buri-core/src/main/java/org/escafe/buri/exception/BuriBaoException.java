/*
 * 作成日: 2005/12/31
 *
 */
package org.escafe.buri.exception;

public class BuriBaoException extends BuriException {
    private static final long serialVersionUID = 4874839941396182018L;

    public BuriBaoException(String messageCode, Object[] args, Throwable cause) {
        super(messageCode, args, cause);
    }

    public BuriBaoException(String messageCode, Object[] args) {
        super(messageCode, args);
    }

    public BuriBaoException(String messageCode) {
        super(messageCode);
    }

}
