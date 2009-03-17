/*
 * 作成日: 2006/03/21
 *
 */
package org.escafe.buri.engine;

import java.util.ArrayList;
import java.util.List;

import org.seasar.framework.container.S2Container;

/**
 * フローを実行する上で必要なシステムのコンテキスト情報を保持するクラスです。
 * 
 * @author $Author: nobeans $
 */
public class BuriSystemContext {

    private static final long serialVersionUID = 1L;

    private BuriUserContext userContext;
    private Class targetDtoClass;
    private BuriPath callPath;
    private Long dataID;
    private S2Container container;
    private IdentityInfo appUserId;
    private Long buriUserID;
    private Long statusID;
    private String startParticipantName;
    private List<String> activityNames;
    private List<String> afterCallMethods = new ArrayList<String>();
    private RuntimeException exception;

    /**
     * 現在のコンテキスト上でのメインのDIコンテナを返します。
     * @return
     */
    public S2Container getContainer() {
        return container;
    }

    /**
     * 現在のコンテキスト上でのメインのDIコンテナを設定します。
     * @param container
     */
    public void setContainer(S2Container container) {
        this.container = container;
    }

    /**
     * ユーザコンテキストを返します。
     * @return
     */
    public BuriUserContext getUserContext() {
        return userContext;
    }

    /**
     * ユーザコンテキストを設定します。
     * @param userContext
     */
    public void setUserContext(BuriUserContext userContext) {
        this.userContext = userContext;
    }

    /**
     * 処理対象のアクティビティのパスを返します。
     * @return
     */
    public BuriPath getCallPath() {
        return callPath;
    }

    /**
     * 処理対象のアクティビティのパスを返します。
     * @return
     */
    public void setCallPath(BuriPath callPath) {
        this.callPath = callPath;
    }

    /**
     * 現在のコンテキストで対象としているデータIDを返します。
     * @return
     */
    public Long getDataID() {
        return dataID;
    }

    /**
     * 現在のコンテキストで対象としているデータIDを設定します。
     * @param dataID
     */
    public void setDataID(Long dataID) {
        this.dataID = dataID;
    }

    /**
     * 現在のコンテキストで対象としているデータのステータスIDを返します。
     * @return
     */
    public Long getStatusID() {
        return statusID;
    }

    /**
     * 現在のコンテキストで対象としているデータのステータスIDを設定します。
     * @param statusID
     */
    public void setStatusID(Long statusID) {
        this.statusID = statusID;
    }

    /**
     * 現在のコンテキストで対象としているデータのDTOクラス型を返します。
     * @return
     */
    public Class getTargetDtoClass() {
        return targetDtoClass;
    }

    /**
     * 現在のコンテキストで対象としているデータのDTOクラス型を設定します。
     * @param targetDtoClass
     */
    public void setTargetDtoClass(Class targetClass) {
        this.targetDtoClass = targetClass;
    }

    /**
     * アプリケーション側で定義されるユーザIDを返します。
     * @return
     */
    public IdentityInfo getAppUserId() {
        return appUserId;
    }

    /**
     * アプリケーション側で定義されるユーザIDを設定します。
     * @param appUserID
     */
    public void setAppUserId(IdentityInfo appUserId) {
        this.appUserId = appUserId;
    }

    /**
     * ぶり側で定義されるユーザIDを返します。
     * <p>
     * ぶりではアプリケーション側で定義されているユーザをぶり側でも独自に管理します。
     * 本メソッドではこの独自管理上のIDを返します。
     * </p>
     * @return
     */
    public Long getBuriUserID() {
        return buriUserID;
    }

    /**
     * ぶり側で定義されるユーザIDを設定します。
     * <p>
     * ぶりではアプリケーション側で定義されているユーザをぶり側でも独自に管理します。
     * 本メソッドではこの独自管理上のIDを設定します。
     * </p>
     * @param buriUserID
     */
    public void setBuriUserID(Long buriUserID) {
        this.buriUserID = buriUserID;
    }

    /**
     * 現在のコンテキストで対象としているデータの開始アクティビティの権限主体名を返します。
     * <p>
     * 1度の実行で複数のアクティビティが実行される場合に、その1つ目のアクティビティが
     * 所属するスイムレーンの権限主体名を返します。
     * </p>
     * <p>
     * 「開始アクティビティ」とは以下のアクティビティを指します。
     * <ul>
     * <li>フローに初めてデータを投入した場合は、開始アクティビティそのもの</li>
     * <li>既にフロー中にあるデータの場合は、フローを再開させたときの最初のアクティビティ</li>
     * </ul>
     * </p>
     * @return
     */
    public String getStartParticipantName() {
        return startParticipantName;
    }

    /**
     * 現在のコンテキストで対象としているデータの開始アクティビティの権限主体名を設定します。
     * <p>
     * 1度の実行で複数のアクティビティが実行される場合に、その1つ目のアクティビティが
     * 所属するスイムレーンの権限主体名を返します。
     * </p>
     * <p>
     * 「開始アクティビティ」とは以下のアクティビティを指します。
     * <ul>
     * <li>フローに初めてデータを投入した場合は、開始アクティビティそのもの</li>
     * <li>既にフロー中にあるデータの場合は、フローを再開させたときの最初のアクティビティ</li>
     * </ul>
     * </p>
     * @param startParticipantName
     */
    public void setStartParticipantName(String startParticipantName) {
        this.startParticipantName = startParticipantName;
    }

    /**
     * 実行時例外を返します。
     * <p>
     * {@code WakanagoProcess}での例外処理の実現で使用されます。
     * </p>
     * @return
     */
    public RuntimeException getException() {
        return exception;
    }

    /**
     * 実行時例外を設定します。
     * <p>
     * {@code WakanagoProcess}での例外処理の実現で使用されます。
     * </p>
     * @param exception
     */
    public void setException(RuntimeException exception) {
        this.exception = exception;
    }

    /**
     * アクティビティ名群を返します
     * <p>
     * BAOのアノテーションで指定したアクティビティ名が含まれます。
     * </p>
     * @return
     */
    public List<String> getActivityNames() {
        return activityNames;
    }

    /**
     * アクティビティ名群を設定します。
     * <p>
     * BAOのアノテーションで指定したアクティビティ名が含まれます。
     * </p>
     * @param activityNames
     */
    public void setActivityNames(List<String> actNames) {
        this.activityNames = actNames;
    }

    public List<String> getAfterCallMethods() {
        return afterCallMethods;
    }

    public void setAfterCallMethods(List<String> afterCallMethods) {
        this.afterCallMethods = afterCallMethods;
    }

    public void addAfterCallMethods(String afterCallName) {
        this.afterCallMethods.add(afterCallName);
    }

    @Override
    public String toString() {
        StringBuffer buff = new StringBuffer("[");
        buff.append("userContext=").append(userContext);
        buff.append("/callPath=").append(callPath);
        buff.append("/dataID=").append(dataID);
        buff.append("/buriUserID=").append(buriUserID);
        buff.append("/appUserId=").append(appUserId);
        buff.append("/statusID=").append(statusID);
        buff.append("/targetDtoClass=").append(targetDtoClass);
        buff.append("/startParticipantName=").append(startParticipantName);
        buff.append("/activityNames=").append(activityNames);
        buff.append("/afterCallMethods=").append(afterCallMethods);
        buff.append("/exception=").append(exception);
        buff.append("]");
        return buff.toString();
    }

}
