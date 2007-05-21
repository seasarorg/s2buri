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
public class BuriNotSelecteedPackageException extends BuriXpdlPathException {
    /**
     * 
     */
    private static final long serialVersionUID = 2387595193717917852L;

    public BuriNotSelecteedPackageException(BuriPath path) {
        super("EBRI0010", path);
    }
}
