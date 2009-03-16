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
package org.escafe.buri.engine.processor.util.impl;

import org.escafe.buri.dao.BuriPathDataUserDao;
import org.escafe.buri.dao.util.BuriDataUtil;
import org.escafe.buri.dao.util.BuriUserUtil;
import org.escafe.buri.dto.BuriPathDataEntityDto;
import org.escafe.buri.dto.BuriPathDataUserEntityDto;
import org.escafe.buri.engine.IdentityInfo;
import org.escafe.buri.engine.processor.BuriAutoSelectProcessor;
import org.escafe.buri.engine.processor.BuriProcessorInfo;
import org.escafe.buri.engine.processor.util.BuriTimerInvoker;
import org.seasar.coffee.dataaccess.DataAccessFactory;

/**
 * 指定時刻になった時点でエンジンを起動する為のAPIの実装です。
 * <p>
 * {@link BuriAutoSelectProcessor}のWrapperとして実装されています。
 * </p>
 * 
 * @author makotan
 * @author nobeans
 * @author imai78(JavaDoc)
 */
public class BuriTimerInvokerImpl implements BuriTimerInvoker {
    /**
     * {@link BuriAutoSelectProcessor}
     */
    private BuriAutoSelectProcessor processor;
    /**
     * データ操作ユーティリティ
     */
    private BuriDataUtil dataUtil;
    /**
     * ユーザ情報ユーティリティ
     */
    private BuriUserUtil userUtil;
    /**
     * ぶり固有のビュー{@code BuriPathDataUser}のDao
     */
    private BuriPathDataUserDao dataUserDao;

    /*
     * @see org.escafe.buri.engine.processor.util.BuriTimerInvoker#invoke(org.escafe.buri.dto.BuriPathDataEntityDto)
     */
    public void invoke(BuriPathDataEntityDto callDto) {
        DataAccessFactory accessFactory = processor.getDataAccessFactory(callDto.getPathName());
        BuriProcessorInfo info = new BuriProcessorInfo();
        info.put("autoAction", Boolean.TRUE);
        if (processor.isSimpleProcessor(callDto.getPathName())) {
            simpleCall(callDto, accessFactory, info);
        }
        if (processor.isStdProcessor(callDto.getPathName())) {
            stdCall(callDto, accessFactory, info);
        }
    }

    /**
     * 権限管理のないフローを実行します。
     * 
     * @param callDto 実行対象の{@code BuriPathDataUser}のDto
     * @param accessFactory 実行対象のパスに対応した{@link DataAccessFactory}
     * @param info プロセッサの情報
     */
    protected void simpleCall(BuriPathDataEntityDto callDto, DataAccessFactory accessFactory, BuriProcessorInfo info) {
        Object argDto = getArgDto(callDto, accessFactory);
        processor.toNextStatus(callDto.getPathName(), argDto, null, info);
    }

    /**
     * {@link BuriPathDataEntityDto}をぶりが管理するDtoのオブジェクトに変換して返します。
     * 
     * @param callDto 実行対象の{@code BuriPathDataUser}のDto
     * @param accessFactory 実行対象のパスに対応した{@link DataAccessFactory}
     * @return ぶりが管理するDtoのオブジェクト
     */
    protected Object getArgDto(BuriPathDataEntityDto callDto, DataAccessFactory accessFactory) {
        Object argDto = dataUtil.getBuriData(callDto.getDataID(), accessFactory);
        return argDto;
    }

    /**
     * ぶり固有のテーブル{@code BuriUser}からアプリケーション用のユーザ情報を取得して返します。
     * 
     * @param callDto 実行対象の{@code BuriPathDataUser}のDto
     * @param accessFactory 実行対象のパスに対応した{@link DataAccessFactory}
     * @return アプリケーション用のユーザ情報
     */
    protected Object getUserData(BuriPathDataEntityDto callDto, DataAccessFactory accessFactory) {
        BuriPathDataUserEntityDto dto = dataUserDao.getDto(callDto.getStateID());
        IdentityInfo appUserId = new IdentityInfo(dto.getUserIDNum(), dto.getUserIDVal());
        Object userData = userUtil.getUserData(accessFactory, dto.getBuriUserID(), appUserId);
        return userData;
    }

    /**
     * 権限管理のあるフローを実行します。
     * 
     * @param callDto 実行対象の{@code BuriPathDataUser}のDto
     * @param accessFactory 実行対象のパスに対応した{@link DataAccessFactory}
     * @param info プロセッサの情報
     */
    protected void stdCall(BuriPathDataEntityDto callDto, DataAccessFactory accessFactory, BuriProcessorInfo info) {
        Object argDto = getArgDto(callDto, accessFactory);
        Object userData = getUserData(callDto, accessFactory);
        processor.toNextStatus(callDto.getPathName(), argDto, userData, info);
    }

    /**
     * ぶり固有のビュー{@code BuriPathDataUser}のDaoを返します。
     * 
     * @return ぶり固有のビュー{@code BuriPathDataUser}のDao
     */
    public BuriPathDataUserDao getDataUserDao() {
        return dataUserDao;
    }

    /**
     * ぶり固有のビュー{@code BuriPathDataUser}のDaoを登録します。
     * 
     * @param dataUserDao ぶり固有のビュー{@code BuriPathDataUser}のDao
     */
    public void setDataUserDao(BuriPathDataUserDao dataUserDao) {
        this.dataUserDao = dataUserDao;
    }

    /**
     * データ操作ユーティリティを返します。
     * 
     * @return データ操作ユーティリティ
     */
    public BuriDataUtil getDataUtil() {
        return dataUtil;
    }

    /**
     * データ操作ユーティリティを登録します。
     * 
     * @param dataUtil データ操作ユーティリティ
     */
    public void setDataUtil(BuriDataUtil dataUtil) {
        this.dataUtil = dataUtil;
    }

    /**
     * {@link BuriAutoSelectProcessor}を返します。
     * 
     * @return {@link BuriAutoSelectProcessor}
     */
    public BuriAutoSelectProcessor getProcessor() {
        return processor;
    }

    /**
     * {@link BuriAutoSelectProcessor}を登録します。
     * 
     * @param processor {@link BuriAutoSelectProcessor}
     */
    public void setProcessor(BuriAutoSelectProcessor processor) {
        this.processor = processor;
    }

    /**
     * ユーザ情報ユーティリティを返します。
     * 
     * @return ユーザ情報ユーティリティ
     */
    public BuriUserUtil getUserUtil() {
        return userUtil;
    }

    /**
     * ユーザ情報ユーティリティを登録します。
     * 
     * @param userUtil ユーザ情報ユーティリティ
     */
    public void setUserUtil(BuriUserUtil userUtil) {
        this.userUtil = userUtil;
    }

}
