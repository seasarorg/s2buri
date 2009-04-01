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
public class BuriNotSelectedActivityException extends BuriXpdlPathException {
    /**
     * 
     */
    private static final long serialVersionUID = -8358547778982675056L;

    public BuriNotSelectedActivityException(BuriPath path, ParticipantProvider participant) {
        super("EBRI0103", path, participant);
    }

}
