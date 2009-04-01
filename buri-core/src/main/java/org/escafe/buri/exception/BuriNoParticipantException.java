/*
 * 作成日: 2005/11/22
 *
 */
package org.escafe.buri.exception;

import org.escafe.buri.engine.BuriPath;

public class BuriNoParticipantException extends BuriException {

    private static final long serialVersionUID = -1838775680955093461L;

    public BuriNoParticipantException(BuriPath buriPath, String name) {
        super("EBRI0018", new Object[] { buriPath, name });
    }

}
