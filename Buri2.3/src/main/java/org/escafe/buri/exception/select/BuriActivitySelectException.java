/*
 * 作成日: 2006/05/26
 *
 */
package org.escafe.buri.exception.select;

import org.escafe.buri.engine.BuriPath;
import org.escafe.buri.engine.ParticipantProvider;
import org.escafe.buri.exception.BuriXpdlPathException;

public class BuriActivitySelectException extends BuriXpdlPathException {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public BuriActivitySelectException(BuriPath path, ParticipantProvider participant) {
        super("EBRI0006", path, participant);
    }

}
