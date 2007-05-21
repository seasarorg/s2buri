/*
 * 作成日: 2005/07/01
 *
 */
package org.escafe.buri.exception;

import org.escafe.buri.engine.BuriPath;

public class BuriPathNotCorrectedException extends BuriException {

    /**
     * 
     */
    private static final long serialVersionUID = 1855580021689032190L;

    public BuriPathNotCorrectedException(BuriPath path) {
        super("EBRI0013", new Object[]{path});
    }

}
