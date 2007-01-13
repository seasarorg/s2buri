/*
 * 作成日: 2006/02/07
 *
 */
package jp.starlogic.common.janino.exception;

import org.codehaus.janino.Parser.ParseException;
import org.seasar.framework.exception.SRuntimeException;

public class ParseRuntimeException extends SRuntimeException {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public ParseRuntimeException(ParseException e) {
        super("",null,e);
    }
}
