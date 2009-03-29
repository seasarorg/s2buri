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
package org.escafe.buri.engine.interceptor;

import org.aopalliance.intercept.MethodInvocation;
import org.escafe.buri.aop.impl.BuriMethodInvocation;
import org.escafe.buri.dao.BuriDataPathHistoryDao;
import org.escafe.buri.dao.util.BuriPathUtil;
import org.escafe.buri.dto.BuriDataPathHistoryEntityDto;
import org.escafe.buri.engine.BuriPath;
import org.escafe.buri.engine.BuriSystemContext;
import org.escafe.buri.util.packages.BranchWalker;
import org.escafe.buri.util.packages.BuriExecProcess;
import org.seasar.framework.aop.interceptors.AbstractInterceptor;

/**
 * 履歴を保存する為のインターセプタです。
 * <p>
 * {@link MethodInvocation}から得られる情報を元に
 * ぶり固有のテーブル{@code BuriDataPathHistory}にデータを登録します。
 * </p>
 * 
 * @author makotan
 * @author nobeans
 * @author imai78(JavaDoc)
 *
 */
public class BuriRecorderInterceptor extends AbstractInterceptor {

    /** */
    private static final long serialVersionUID = 1L;
    /**
     * ぶり固有のテーブル{@code BuriDataPathHistory}のDao
     */
    private BuriDataPathHistoryDao historyDao;
    /**
     * パス参照ユーティリティ
     */
    private BuriPathUtil pathUtil;

    /*
     * @see org.aopalliance.intercept.MethodInterceptor#invoke(org.aopalliance.intercept.MethodInvocation)
     */
    public Object invoke(MethodInvocation invocation) throws Throwable {
        // ぶりのMethodInvocationではない場合のキャスト
        if (!(invocation instanceof BuriMethodInvocation)) {
            return invocation.proceed();
        }
        // ぶりのMethodInvocationの場合のキャスト
        BuriMethodInvocation bInvo = (BuriMethodInvocation) invocation;
        if (bInvo.getThisObject() instanceof BuriExecProcess) {
            if (bInvo.getCallMethod().getName().indexOf("_start") > 0) {
                saveLog(bInvo);
            }
        }
        return invocation.proceed();
    }

    /**
     * ぶり固有のテーブル{@code BuriDataPathHistory}にデータを登録します。
     * 
     * @param bInvo 指定された{@link BuriMethodInvocation}
     */
    protected void saveLog(BuriMethodInvocation bInvo) {
        BuriSystemContext sysContext = (BuriSystemContext) bInvo.getCallArguments()[0];
        BranchWalker walker = (BranchWalker) bInvo.getCallArguments()[1];
        // BuriDataPathHistoryEntityDtoのセットアップ
        BuriDataPathHistoryEntityDto dto = new BuriDataPathHistoryEntityDto();
        dto.setAction(sysContext.getUserContext().getAction());
        dto.setBuriUserID(sysContext.getBuriUserID());
        dto.setDataID(sysContext.getDataID());
        // パス名のセットアップ
        if (walker.getNowPath() != null) {
            BuriPath realPath = pathUtil.getBuriPathFromRealPath(walker.getNowPath());
            long pathID = realPath.getBuriPathId();
            dto.setPathID(new Long(pathID));
            dto.setPathName(realPath.toString());
        } else {
            dto.setPathName(sysContext.getCallPath().toString() + "/" + bInvo.getCallMethod().getName());
        }
        historyDao.insert(dto);
    }

    /**
     * ぶり固有のテーブル{@code BuriDataPathHistory}のDaoを返します。
     * 
     * @return ぶり固有のテーブル{@code BuriDataPathHistory}のDao
     */
    public BuriDataPathHistoryDao getHistoryDao() {
        return historyDao;
    }

    /**
     * ぶり固有のテーブル{@code BuriDataPathHistory}のDaoを登録します。
     * 
     * @param historyDao ぶり固有のテーブル{@code BuriDataPathHistory}のDao
     */
    public void setHistoryDao(BuriDataPathHistoryDao historyDao) {
        this.historyDao = historyDao;
    }

    /**
     * パス参照ユーティリティを返します。
     * 
     * @return パス参照ユーティリティ
     */
    public BuriPathUtil getPathUtil() {
        return pathUtil;
    }

    /**
     * パス参照ユーティリティを登録します。
     * 
     * @param pathUtil パス参照ユーティリティ
     */
    public void setPathUtil(BuriPathUtil pathUtil) {
        this.pathUtil = pathUtil;
    }

}