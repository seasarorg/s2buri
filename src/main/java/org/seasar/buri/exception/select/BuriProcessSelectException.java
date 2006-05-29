/*
 * çÏê¨ì˙: 2006/05/29
 *
 */
package org.seasar.buri.exception.select;

import org.seasar.buri.engine.BuriPath;
import org.seasar.buri.engine.ParticipantProvider;
import org.seasar.buri.exception.BuriXpdlPathException;

public class BuriProcessSelectException extends BuriXpdlPathException {
    private static final long serialVersionUID = 1L;

    public BuriProcessSelectException(BuriPath path,ParticipantProvider participant) {
        super("EBRI0006", path,participant);
    }

}
