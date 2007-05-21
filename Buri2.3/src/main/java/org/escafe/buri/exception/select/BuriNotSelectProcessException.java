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
public class BuriNotSelectProcessException extends BuriXpdlPathException {
    /**
     * 
     */
    private static final long serialVersionUID = -4161440074582411548L;

    public BuriNotSelectProcessException(BuriPath path) {
        super("EBRI0009", path);
    }

    public BuriNotSelectProcessException(BuriPath path,String target) {
        super("EBRI0019", path,target);
    }

}
