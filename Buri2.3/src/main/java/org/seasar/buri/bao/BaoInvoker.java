/*
 * 作成日: 2006/01/03
 *
 */
package org.seasar.buri.bao;

import org.aopalliance.intercept.MethodInvocation;

public interface BaoInvoker {
    Object invoke(BaoInvokeMetadata invokeMetadata,MethodInvocation invoke);
    Object getDataFromStatus(BaoStatusMetadata statusMetadata,MethodInvocation invoke);
    Object getInvokeData(BaoInvokeMetadata invokeMetadata,MethodInvocation invoke);
}
