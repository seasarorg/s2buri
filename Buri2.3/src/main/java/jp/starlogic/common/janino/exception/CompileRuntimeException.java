/*
 * 作成日: 2006/02/07
 *
 */
package jp.starlogic.common.janino.exception;

import org.codehaus.janino.CompileException;
import org.seasar.framework.exception.SRuntimeException;

public class CompileRuntimeException extends SRuntimeException {

    private static final long serialVersionUID = 1L;

    public CompileRuntimeException(CompileException ex) {
        super("", null, ex);
    }

}
