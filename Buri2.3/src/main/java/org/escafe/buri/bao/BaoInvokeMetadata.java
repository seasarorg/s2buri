/*
 * 作成日: 2005/12/31
 *
 */
package org.escafe.buri.bao;

public interface BaoInvokeMetadata extends BaoFunctionMetadata{
    String getAction();
    void setAction(String act);
    BuriConvert getBuriConvert();
    void setBuriConvert(BuriConvert convert);
    String getResult();
    void setResult(String result);
    
}
