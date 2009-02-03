/*
 * 作成日: 2005/05/15
 *
 */
package org.escafe.buri.exception.select;

import org.escafe.buri.engine.BuriPath;
import org.escafe.buri.engine.ParticipantProvider;
import org.escafe.buri.exception.BuriXpdlPathException;

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
    public BuriManySelectActivityException(BuriPath path, ParticipantProvider participant) {
        super("EBRI0101", path, participant);
    }
}
