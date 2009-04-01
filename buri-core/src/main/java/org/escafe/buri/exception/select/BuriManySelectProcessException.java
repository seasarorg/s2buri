/*
 * 作成日: 2005/05/15
 *
 */
package org.escafe.buri.exception.select;

import org.escafe.buri.engine.BuriPath;
import org.escafe.buri.exception.BuriXpdlPathException;

/**
 * @author makotan
 *
 */
public class BuriManySelectProcessException extends BuriXpdlPathException {
    /**
     * 
     */
    private static final long serialVersionUID = -3982856125128375730L;

    public BuriManySelectProcessException(BuriPath path) {
        super("EBRI0102", path);
    }

    public BuriManySelectProcessException(BuriPath path, String target) {
        super("EBRI0020", path, target);
    }

}
