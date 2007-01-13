/*
 * 作成日: 2005/05/15
 *
 */
package org.seasar.buri.exception.select;

import org.seasar.buri.engine.BuriPath;
import org.seasar.buri.exception.BuriXpdlPathException;



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
        super("EBRI0008", path);
    }
    public BuriManySelectProcessException(BuriPath path,String target) {
        super("EBRI0020", path,target);
    }

}
