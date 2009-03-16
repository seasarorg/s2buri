/*
 * Copyright 2004-2009 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.escafe.buri.engine.processor.impl;

import org.escafe.buri.engine.processor.BuriAutoSelectProcessor;
import org.escafe.buri.engine.processor.BuriProcessorInfo;
import org.escafe.buri.engine.processor.util.BuriSignal;
import org.escafe.buri.exception.BuriNotSupportOperationException;

/**
 * シグナルを利用する為のAPIセットです。
 * <p>
 * 権限管理を行うフローの実行時にあえてその権限を無視したい場合、
 * という非常に限定的なシチュエーションで使用するAPIです。
 * </p>
 * 
 * @author makotan
 * @author imai78(JavaDoc)
 * @since 2007/09/27
 */
public class BuriAutoSelectAutoSignalProcessorImpl extends BuriAutoSelectProcessorImpl implements BuriAutoSelectProcessor {
    /**
     * シグナルAPI
     */
    private BuriSignal buriSignal;

    /*
     * @see org.escafe.buri.engine.processor.impl.BuriAutoSelectProcessorImpl#toNextStatus(java.lang.String, java.lang.Object, java.lang.Object)
     */
    @Override
    public void toNextStatus(String path, Object data, Object userData) {
        buriSignal.signal(path, data);
    }

    /*
     * @see org.escafe.buri.engine.processor.impl.BuriAutoSelectProcessorImpl#toNextStatusAction(java.lang.String, java.lang.Object, java.lang.Object, java.lang.Object)
     */
    @Override
    public void toNextStatusAction(String path, Object data, Object userData, Object action) {
        buriSignal.signal(path, data, action.toString());
    }

    /*
     * @see org.escafe.buri.engine.processor.impl.BuriAutoSelectProcessorImpl#toNextStatus(java.lang.String, java.lang.Object, java.lang.Object, java.lang.String)
     */
    @Override
    public Object toNextStatus(String path, Object data, Object userData, String resultExp) {
        throw new BuriNotSupportOperationException("EBRI0030");
    }

    /*
     * @see org.escafe.buri.engine.processor.impl.BuriAutoSelectProcessorImpl#toNextStatus(java.lang.String, java.lang.Object, java.lang.Object, org.escafe.buri.engine.processor.BuriProcessorInfo)
     */
    @Override
    public Object toNextStatus(String path, Object data, Object userData, BuriProcessorInfo info) {
        throw new BuriNotSupportOperationException("EBRI0030");
    }

}
