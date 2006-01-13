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
public class BuriManySelectActivityException extends BuriXpdlPathException {

    /**
     * 
     */
    private static final long serialVersionUID = -9029198228876089413L;

    /**
     * @param messageCode
     * @param path
     */
    public BuriManySelectActivityException(BuriPath path,BuriParticipant participant) {
        super("EBRI0006", path,participant);
    }
}
