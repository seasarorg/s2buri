/*
 * çÏê¨ì˙: 2005/05/15
 *
 */
package org.seasar.buri.exception.select;

import org.seasar.buri.engine.BuriPath;
import org.seasar.buri.exception.BuriXpdlPathException;



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
