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
package org.escafe.buri.oouo.internal.structure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.escafe.buri.common.util.MultiValueMap;

/**
 * XPDLの{@code Package}要素を表すクラスです。
 * <p>
 * この{@code Package}要素は、1つのファイルに定義されるXPDLのフローの最上位に位置する要素です。
 * フローのプロセスやアクティビティ、トランジションはすべてこのパッケージに内包される要素として存在しています。
 * </p>
 * <p>
 * 以下のような重要な要素を多く持つクラスです。
 * <ul>
 * <li>ステークホルダーを表す{@code Participant}要素</li>
 * <li>フローのプロセスを表す{@code WorkflowProcess}要素</li>
 * <li>DtoとDtoに対する操作を定義した{@code DataField}要素</li>
 * <li>Javaコンポーネント呼び出しの為の{@code Application}要素</li>
 * <li>追加設定を定義する{@code ExtendedAttribute}要素</li>
 * </ul>
 * </p>
 * 
 * @author makotan
 * @author nobeans
 * @author imai78(JavaDoc)
 * @since 2006/03/06
 */
public class BuriPackageType {

    /**
     * パッケージ内で使用するステークホルダーをステークホルダーIDと{@link BuriParticipantType}とで保持する{@link Map}
     */
    private Map<String, BuriParticipantType> participantsById = new HashMap<String, BuriParticipantType>();
    /**
     * パッケージ内で使用するステークホルダー名の{@link MultiValueMap}
     */
    private MultiValueMap<BuriParticipantType> participantsByName = new MultiValueMap<BuriParticipantType>();
    /**
     * フローのプロセスIDと{@link BuriWorkflowProcessType}を紐づけて保持する{@link Map}
     */
    private Map<String, BuriWorkflowProcessType> processesById = new HashMap<String, BuriWorkflowProcessType>();
    /**
     * フローのプロセス名と{@link BuriWorkflowProcessType}を紐づけて保持する{@link MultiValueMap}
     */
    private MultiValueMap<BuriWorkflowProcessType> processName = new MultiValueMap<BuriWorkflowProcessType>();
    /**
     * フローのパッケージに紐づく{@code DataField}要素を、そのIDと{@link BuriDataFieldType}とで保持する{@link Map}
     */
    private Map<String, BuriDataFieldType> datasById = new HashMap<String, BuriDataFieldType>();
    /**
     * フローのパッケージに紐づく{@code Application}要素を、そのIDと{@link BuriApplicationType}とで保持する{@link Map}
     */
    private Map<String, BuriApplicationType> applicationsById = new HashMap<String, BuriApplicationType>();
    /**
     * フローのパッケージに紐づく{@code ExtendedAttribute}要素のオブジェクト化した{@link BuriExtendedAttributeType}のリスト
     */
    private List<BuriExtendedAttributeType> extentedAttributes = new ArrayList<BuriExtendedAttributeType>();
    /**
     * {@code Package}要素に指定されたID
     */
    private String id;
    /**
     * {@code Package}要素に指定された名前
     */
    private String name;

    /**
     * {@code Package}要素の要素名
     */
    public static final String OOUOTHIS = "Package";

    /**
     * {@code Participant}要素の要素名
     */
    public static final String addParticipant_ELEMENT = "Participant";

    /**
     * パッケージ内で使用するステークホルダーを追加します。
     * 
     * @param participant 追加する{@link BuriParticipantType}
     */
    public void addParticipant(BuriParticipantType participant) {
        participantsById.put(participant.getId(), participant);
        participantsByName.put(participant.getName(), participant);
    }

    /**
     * ステークホルダー名に対応する{@link BuriParticipantType}のリストを返します。
     * 
     * @param participantName ステークホルダー名
     * @return {@link BuriParticipantType}のリスト
     */
    public List<BuriParticipantType> getParticipantsByName(String participantName) {
        return participantsByName.get(participantName);
    }

    /**
     * パッケージ内で使用するステークホルダー名の{@link MultiValueMap}を返します。
     * 
     * @return パッケージ内で使用するステークホルダー名の{@link MultiValueMap}
     */
    public MultiValueMap<BuriParticipantType> getParticipantByName() {
        return participantsByName;
    }

    /**
     * ステークホルダーのIDに対応する{@link BuriParticipantType}を返します。
     * 
     * @param participantId ステークホルダーのID
     * @return 対応する{@link BuriParticipantType}
     */
    public BuriParticipantType getParticipantById(String participantId) {
        return participantsById.get(participantId);
    }

    /**
     * フローのパッケージに紐づく{@code WorkflowProcess}要素の要素名
     */
    public static final String addProcess_ELEMENT = "WorkflowProcess";

    /**
     * フローのプロセスから得た{@link BuriWorkflowProcessType}を追加します。
     * 
     * @param wkfprocess 追加する{@link BuriWorkflowProcessType}
     */
    public void addProcess(BuriWorkflowProcessType wkfprocess) {
        processesById.put(wkfprocess.getId(), wkfprocess);
        processName.put(wkfprocess.getName(), wkfprocess);
    }

    /**
     * 指定されたフローのプロセス名に対応する{@link BuriWorkflowProcessType}のリストを返します。
     * 
     * @param name フローのプロセス名
     * @return 対応する{@link BuriWorkflowProcessType}のリスト
     */
    public List<BuriWorkflowProcessType> getProcessByName(String name) {
        return processName.get(name);
    }

    /**
     * フローのプロセス名の{@link MultiValueMap}を返します。
     * 
     * @return フローのプロセス名の{@link MultiValueMap}
     */
    public MultiValueMap<BuriWorkflowProcessType> getProcessByName() {
        return processName;
    }

    /**
     * フローのプロセスIDに対応した{@link BuriWorkflowProcessType}を返します。
     * 
     * @param id フローのプロセスID
     * @return フローのプロセスIDに対応した{@link BuriWorkflowProcessType}
     */
    public BuriWorkflowProcessType getProcessById(String id) {
        return processesById.get(id);
    }

    /**
     * フローのプロセスIDと{@link BuriWorkflowProcessType}を紐づけて保持する{@link Map}を返します。
     * 
     * @return フローのプロセスIDと{@link BuriWorkflowProcessType}を紐づけて保持する{@link Map}
     */
    public Map<String, BuriWorkflowProcessType> getProcessById() {
        return processesById;
    }

    /**
     * フローのパッケージに紐づく{@code Application}要素の要素名
     */
    public static final String addApplication_ELEMENT = "Application";

    /**
     * {@code Application}要素から得た{@link BuriApplicationType}を追加します。
     * 
     * @param app 追加する{@link BuriApplicationType}
     */
    public void addApplication(BuriApplicationType app) {
        applicationsById.put(app.getId(), app);
    }

    /**
     * 指定された{@code Application}要素のIDに対応する{@link BuriApplicationType}を返します。
     * 
     * @param id {@code APplication}要素のID
     * @return 対応する{@link BuriApplicationType}
     */
    public BuriApplicationType getApplicationById(String id) {
        return applicationsById.get(id);
    }

    /**
     * フローのパッケージに紐づく{@code Application}要素を、そのIDと{@link BuriApplicationType}とで保持する{@link Map}を返します。
     * 
     * @return フローのパッケージに紐づく{@code Application}要素を、そのIDと{@link BuriApplicationType}とで保持する{@link Map}
     */
    public Map<String, BuriApplicationType> getApplication() {
        return applicationsById;
    }

    /**
     * フローのパッケージに紐づく{@code DataField}要素の要素名
     */
    public static final String addDataField_ELEMENT = "DataField";

    /**
     * {@code DataField}から得た{@link BuriDataFieldType}を追加します。
     * 
     * @param dataField 追加する{@link BuriDataFieldType}
     */
    public void addDataField(BuriDataFieldType dataField) {
        datasById.put(dataField.getId(), dataField);
    }

    /**
     * 指定された{@code DataField}のIDに対応した{@link BuriDataFieldType}を返します。
     * 
     * @param dataId {@code DataFiled}のID
     * @return 対応した{@link BuriDataFieldType}
     */
    public BuriDataFieldType getDataFieldById(String dataId) {
        return datasById.get(dataId);
    }

    /**
     * フローのパッケージに紐づく{@code DataField}要素を、そのIDと{@link BuriDataFieldType}とで保持する{@link Map}を返します。
     * 
     * @return フローのパッケージに紐づく{@code DataField}要素を、そのIDと{@link BuriDataFieldType}とで保持する{@link Map}
     */
    public Map<String, BuriDataFieldType> getDataField() {
        return datasById;
    }

    /**
     * フローのパッケージに紐づく{@code ExtendedAttribute}要素の要素名
     */
    public static final String addExtendedAttribute_ELEMENT = "ExtendedAttribute";

    /**
     * フローのパッケージに紐づく{@code ExtendedAttribute}要素の{@link BuriExtendedAttributeType}を追加します。
     * 
     * @param attri 追加する{@link BuriExtendedAttributeType}
     */
    public void addExtendedAttribute(BuriExtendedAttributeType attri) {
        extentedAttributes.add(attri);
    }

    /**
     * フローのパッケージに紐づく{@link BuriExtendedAttributeType}のリストを返します。
     * 
     * @return {@link BuriExtendedAttributeType}のリスト
     */
    public List<BuriExtendedAttributeType> getExtendedAttribute() {
        return extentedAttributes;
    }

    /**
     * フローのパッケージのIDを返します。
     * 
     * @return フローのパッケージのID
     */
    public String getId() {
        return id;
    }

    /**
     * フローのパッケージの{@code Id}の属性名
     */
    public static final String setId_ATTRI = "Id";

    /**
     * フローのパッケージのIDを登録します。
     * 
     * @param id フローのパッケージのID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * フローのパッケージ名を返します。
     * 
     * @return フローのパッケージ名
     */
    public String getName() {
        return name;
    }

    /**
     * フローのパッケージの{@code Name}の属性名
     */
    public static final String setName_ATTRI = "Name";

    /**
     * フローのパッケージ名を登録します。
     * 
     * @param name フローのパッケージ名
     */
    public void setName(String name) {
        this.name = name;
    }

    /*
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuffer buff = new StringBuffer("[");
        buff.append("id=").append(id);
        buff.append("/name=").append(name).append("\n");
        buff.append("-----PARTICIPANT----------\n");
        buff.append("/participants=").append(participantsById).append("\n");
        buff.append("-----PROCESS----------\n");
        buff.append("/processes=").append(processesById).append("\n");
        buff.append("-----DATA----------\n");
        buff.append("/datas=").append(datasById).append("\n");
        buff.append("-----APPLICATION----------\n");
        buff.append("/applications=").append(applicationsById).append("\n");
        buff.append("-----EXTENDED ATTRIBUTE----------\n");
        buff.append("/extentedAttributes=").append(extentedAttributes).append("\n");
        buff.append("]");
        return buff.toString();

    }
}
