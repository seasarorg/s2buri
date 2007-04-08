/*
 * 作成日: 2005/12/20
 *
 */
package jp.starlogic.servicemanager.exception;

import org.seasar.framework.exception.SRuntimeException;

public class InterruptedRuntimeException extends SRuntimeException {

    private static final long serialVersionUID = 5290945330605631450L;

    public InterruptedRuntimeException(String arg0, Object[] arg1, Throwable arg2) {
        super(arg0, arg1, arg2);
    }

}
