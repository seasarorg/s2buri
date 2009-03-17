/*
 * 作成日: 2006/03/06
 *
 */
package org.escafe.buri.oouo.internal.structure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.escafe.buri.common.util.MultiValueMap;

public class BuriPackageType {

    private Map<String, BuriParticipantType> participantsById = new HashMap<String, BuriParticipantType>();
    private MultiValueMap participantsByName = new MultiValueMap();
    private Map<String, BuriWorkflowProcessType> processesById = new HashMap<String, BuriWorkflowProcessType>();
    private MultiValueMap processName = new MultiValueMap();
    private Map<String, BuriDataFieldType> datasById = new HashMap<String, BuriDataFieldType>();
    private Map<String, BuriApplicationType> applicationsById = new HashMap<String, BuriApplicationType>();
    private List<BuriExtendedAttributeType> extentedAttributes = new ArrayList<BuriExtendedAttributeType>();

    private String id;
    private String name;

    public static final String OOUOTHIS = "Package";

    public static final String addParticipant_ELEMENT = "Participant";

    public void addParticipant(BuriParticipantType participant) {
        participantsById.put(participant.getId(), participant);
        participantsByName.put(participant.getName(), participant);
    }

    public List getParticipantsByName(String participantName) {
        return participantsByName.get(participantName);
    }

    public MultiValueMap getParticipantByName() {
        return participantsByName;
    }

    public BuriParticipantType getParticipantById(String participantId) {
        return participantsById.get(participantId);
    }

    public static final String addProcess_ELEMENT = "WorkflowProcess";

    public void addProcess(BuriWorkflowProcessType wkfprocess) {
        processesById.put(wkfprocess.getId(), wkfprocess);
        processName.put(wkfprocess.getName(), wkfprocess);
    }

    public List getProcessByName(String name) {
        return processName.get(name);
    }

    public MultiValueMap getProcessByName() {
        return processName;
    }

    public BuriWorkflowProcessType getProcessById(String id) {
        return processesById.get(id);
    }

    public Map<String, BuriWorkflowProcessType> getProcessById() {
        return processesById;
    }

    public static final String addApplication_ELEMENT = "Application";

    public void addApplication(BuriApplicationType app) {
        applicationsById.put(app.getId(), app);
    }

    public BuriApplicationType getApplicationById(String id) {
        return applicationsById.get(id);
    }

    public Map<String, BuriApplicationType> getApplication() {
        return applicationsById;
    }

    public static final String addDataField_ELEMENT = "DataField";

    public void addDataField(BuriDataFieldType dataField) {
        datasById.put(dataField.getId(), dataField);
    }

    public BuriDataFieldType getDataFieldById(String dataId) {
        return datasById.get(dataId);
    }

    public Map<String, BuriDataFieldType> getDataField() {
        return datasById;
    }

    public static final String addExtendedAttribute_ELEMENT = "ExtendedAttribute";

    public void addExtendedAttribute(BuriExtendedAttributeType attri) {
        extentedAttributes.add(attri);
    }

    public List<BuriExtendedAttributeType> getExtendedAttribute() {
        return extentedAttributes;
    }

    public String getId() {
        return id;
    }

    public static final String setId_ATTRI = "Id";

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public static final String setName_ATTRI = "Name";

    public void setName(String name) {
        this.name = name;
    }

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
