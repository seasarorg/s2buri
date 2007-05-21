/*
 * 作成日: 2005/12/31
 *
 */
package org.escafe.buri.bao;

import org.aopalliance.intercept.MethodInvocation;

public interface BaoMetadataFactory {
    BaoMetadata getBaoMetadata(MethodInvocation invoke);
    boolean isStatusMetadata(BaoMetadata metadata,MethodInvocation invoke);
    BaoInvokeMetadata getBaoInvokeMetadata(BaoMetadata metadata,MethodInvocation invoke);
    BaoStatusMetadata getBaoStatusMetadata(BaoMetadata metadata,MethodInvocation invoke);
    
}
