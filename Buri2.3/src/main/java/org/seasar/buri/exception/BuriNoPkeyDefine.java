/*
 * 作成日: 2005/11/22
 *
 */
package org.seasar.buri.exception;

public class BuriNoPkeyDefine extends BuriException {

    private static final long serialVersionUID = 6208074045680634385L;
    
    public BuriNoPkeyDefine(String id) {
        super("EBRI0005", new Object[]{id}, null);
    }    
    
    public BuriNoPkeyDefine(String id,String str) {
        super("EBRI0006", new Object[]{id,str}, null);
    }    

}
