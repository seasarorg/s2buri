/*
 * 作成日: 2005/11/22
 *
 */
package org.seasar.buri.exception;

import org.seasar.buri.engine.BuriPath;

public class BuriRoleMismatchingOfSetup extends BuriException {

    private static final long serialVersionUID = 2943030174435773686L;

    public BuriRoleMismatchingOfSetup(BuriPath path) {
        super("EBRI0016", new Object[]{path}, null);
    }

}
