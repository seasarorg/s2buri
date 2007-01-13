/*
 * 作成日: 2005/11/22
 *
 */
package org.seasar.buri.exception;

public class BuriNoDataFieldException extends BuriException {

    public BuriNoDataFieldException(Object[] args) {
        super("EBRI0017", args, null);
    }

    private static final long serialVersionUID = -7899947473644916082L;

}
