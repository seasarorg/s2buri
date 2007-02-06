/*
 * 作成日: 2006/06/09
 *
 */
package org.seasar.buri.dao.util;

import java.util.List;

import org.seasar.buri.engine.BuriSystemContext;
import org.seasar.buri.engine.RoleInfo;
import org.seasar.buri.util.packages.BranchWalker;
import org.seasar.coffee.dataaccess.DataAccessFactory;

/**
 * ユーザ情報に関するユーティリティクラスです。
 * 
 * @author $Author$
 */
public interface BuriUserUtil {

    /**
     * 対象のロール情報群を返します。
     * 
     * @param factory
     * @param systemContext
     * @param walker
     * @return
     */
    List<RoleInfo> getTargetRoleInfos(DataAccessFactory factory, BuriSystemContext systemContext,
            BranchWalker walker);

    /**
     * ぶり管理上のユーザIDに変換します。
     * <p>
     * アプリケーション側のユーザID(番号)とユーザID(文字列)を元に、
     * ぶり側で管理しているユーザIDに変換します。
     * </p>
     * @param appUserIDNumber
     * @param appUserIDString
     * @return
     */
    long convertBuriUserID(Long appUserIDNumber, String appUserIDString);

    /**
     * ロール情報群からユーザID群に変換します。
     * 
     * @param roleInfos
     * @return
     */
    List<Long> convertBuriUserIDsFromRoleInfos(List<RoleInfo> roleInfos);

    /**
     * ユーザ情報を返します。
     * 
     * @param factory
     * @param buriUserID
     * @param appUserIDNumber
     * @param appUserIDString
     * @return
     */
    Object getUserData(DataAccessFactory factory, long buriUserID, Long appUserIDNumber,
            String appUserIDString);

}
