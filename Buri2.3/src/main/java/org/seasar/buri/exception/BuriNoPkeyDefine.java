/*
 * 作成日: 2005/11/22
 *
 */
package org.seasar.buri.exception;

public class BuriNoPkeyDefine extends BuriException {

    private static final long serialVersionUID = 6208074045680634385L;
    
    public BuriNoPkeyDefine(Object args) {
        super("EBRI0014", new Object[]{args}, null);
    }    

}
