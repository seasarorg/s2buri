/*
 * �쐬��: 2006/03/31
 *
 */
package org.seasar.buri.sample.packages.wakanagoTest;

import org.seasar.buri.engine.ParticipantProvider;
import org.seasar.buri.engine.wakanago.WakanagoEngineImpl;

public class WakanagoSampleEngine extends WakanagoEngineImpl{
    
    
    
    //TODO �R���p�C���������protected��
    public void addPackage(String packageName,Object packageObj,ParticipantProvider provider) {
        packageObjs.put(packageName,packageObj);
        roleMap.put(packageName,provider);
    }
    
}
