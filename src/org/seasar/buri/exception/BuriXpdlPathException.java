/*
 * çÏê¨ì˙: 2005/05/15
 *
 */
package org.seasar.buri.exception;

import org.seasar.buri.engine.BuriPath;
import org.seasar.buri.oouo.internal.structure.BuriParticipantType;

/**
 * @author makotan
 *
 */
public class BuriXpdlPathException extends BuriException {

    /**
     * 
     */
    private static final long serialVersionUID = -8767956392741611134L;
    public BuriXpdlPathException(String messageCode,BuriPath path,String taeget) {
        super(messageCode, new Object[]{path.getPlainName(),taeget});
    }
    public BuriXpdlPathException(String messageCode,BuriPath path) {
        super(messageCode, new Object[]{path.getPlainName()});
    }
    public BuriXpdlPathException(String messageCode,BuriPath path,BuriParticipantType participant) {
        super(messageCode, new Object[]{path.getPlainName(),participant});
    }

}
