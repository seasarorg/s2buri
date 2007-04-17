/*
 * 作成日: 2006/06/09
 *
 */
package org.seasar.buri.dao.util;

import java.util.List;

import org.seasar.buri.engine.BuriSystemContext;
import org.seasar.buri.engine.IdentityInfo;
import org.seasar.buri.util.packages.BranchWalker;
import org.seasar.coffee.dataaccess.DataAccessFactory;

/**
 * ユーザ情報に関するユーティリティクラスです。
 * 
 * @author $Author$
 */
public interface BuriUserUtil {

    /**
     * ぶり管理上のユーザIDに変換します。
     * <p>
     * アプリケーション側のユーザIDを元に、ぶり側で管理しているユーザIDに変換します。
     * </p>
     * 
     * @param appUserId
     * @return
     */
    long convertBuriUserId(IdentityInfo appUserId);

    /**
     * ぶり管理上のユーザID群に変換します。
     * <p>
     * アプリケーション側のユーザID群を元に、ぶり側で管理しているユーザID群に変換します。
     * </p>
     * 
     * @param appUserIds
     * @return
     */
    List<Long> convertBuriUserIds(List<IdentityInfo> appUserIds);

    /**
     * アプリケーション管理上のユーザID群を返します。
     * 
     * @param factory
     * @param systemContext
     * @param walker
     * @return
     */
    List<IdentityInfo> getUserIds(DataAccessFactory factory,
            BuriSystemContext systemContext, BranchWalker walker);

    /**
     * アプリケーション管理上のユーザ情報を返します。
     * 
     * @param factory
     * @param buriUserId
     * @param appUserId
     * @return
     */
    Object getUserData(DataAccessFactory factory, long buriUserId, IdentityInfo appUserId);

    /**
     * 最初にデータを追加したユーザIDを返します。
     * @param sysContext
     * @return
     */
    IdentityInfo getStartUserId(BuriSystemContext sysContext);
}
