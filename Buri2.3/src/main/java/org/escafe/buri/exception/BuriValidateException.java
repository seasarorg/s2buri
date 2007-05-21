/*
 * 作成日: 2006/01/31
 *
 */
package org.escafe.buri.exception;

import java.util.List;

public class BuriValidateException extends BuriException {

    private static final long serialVersionUID = 1L;


    public BuriValidateException(String messageCode,List actList,String actName) {
        super(messageCode,new Object[]{actList,actName});
    }

}
