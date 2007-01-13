/*
 * 作成日: 2005/11/22
 *
 */
package org.seasar.buri.exception;

public class BuriNoDataAccessMethod extends BuriException {

    private static final long serialVersionUID = 3512232914325964894L;

    public BuriNoDataAccessMethod(Object[] args) {
        super("EBRI0015", args, null);
    }

}
