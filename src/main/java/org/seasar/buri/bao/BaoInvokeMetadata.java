/*
 * �쐬��: 2005/12/31
 *
 */
package org.seasar.buri.bao;

public interface BaoInvokeMetadata extends BaoFunctionMetadata{
    String getAction();
    void setAction(String act);
    BuriConvert getBuriConvert();
    void setBuriConvert(BuriConvert convert);
    String getResult();
    void setResult(String result);
    
}