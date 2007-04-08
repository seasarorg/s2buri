/*
 * 作成日: 2006/04/12
 *
 */
package org.seasar.buri.exception;

public class BuriDataFieldErrorException extends BuriException {
    private static final long serialVersionUID = 1L;
    
    public BuriDataFieldErrorException(String id,String message) {
        super("EBRI0001",new Object[]{id,message});
    }
    public BuriDataFieldErrorException(String id,String key1,String key2) {
        super("EBRI0002",new Object[]{id,key1,key2});
    }
    public BuriDataFieldErrorException(String id) {
        super("EBRI0003",new Object[]{id});
    }
    public BuriDataFieldErrorException(Class tgtClass,String propName) {
        super("EBRI0004",new Object[]{tgtClass.getName(),propName});
    }

}
