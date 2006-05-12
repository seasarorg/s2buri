/*
 * çÏê¨ì˙: 2005/05/15
 *
 */
package org.seasar.buri.exception.select;

import org.seasar.buri.engine.BuriParticipant;
import org.seasar.buri.engine.BuriPath;
import org.seasar.buri.exception.BuriXpdlPathException;



/**
 * @author makotan
 *
 */
public class BuriNotSelectedActivityException extends BuriXpdlPathException {
    /**
     * 
     */
    private static final long serialVersionUID = -8358547778982675056L;

    public BuriNotSelectedActivityException(BuriPath path,BuriParticipant participant) {
        super("EBRI0007", path,participant);
    }

}
