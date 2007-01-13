/*
 * 作成日: 2006/02/07
 *
 */
package jp.starlogic.common.janino.exception;

import org.codehaus.janino.Scanner.ScanException;
import org.seasar.framework.exception.SRuntimeException;

public class ScanRuntimeException extends SRuntimeException {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public ScanRuntimeException(ScanException e) {
        super("",null,e);
    }
}
