/*
 * 作成日: 2006/03/31
 *
 */
package org.seasar.buri.sample.packages.wakanagoTest;

import org.seasar.buri.engine.ParticipantProvider;
import org.seasar.buri.engine.impl.WakanagoEngineImpl;
import org.seasar.buri.util.packages.BuriExePackages;

public class WakanagoSampleEngine extends WakanagoEngineImpl {

    //TODO コンパイラを作ってprotectedへ
    public void addPackage(String packageName, BuriExePackages packageObj,
            ParticipantProvider provider) {
        packageObjs.put(packageName, packageObj);
        appUserIdMap.put(packageName, provider);
    }

}
