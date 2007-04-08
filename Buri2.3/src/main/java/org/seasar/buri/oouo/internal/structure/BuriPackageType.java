/*
 * 作成日: 2006/03/06
 *
 */
package org.seasar.buri.oouo.internal.structure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.seasar.buri.common.util.MultiValueMap;

public class BuriPackageType {
    
    private Map rolesId = new HashMap();
    private MultiValueMap rolesName = new MultiValueMap();
    private Map processId = new HashMap();
    private MultiValueMap processName = new MultiValueMap();
    private Map data = new HashMap();
    private Map application = new HashMap();
    private List ExtentedAttribute = new ArrayList();
    
    private String id;
    private String name;
    
    
    public static final String OOUOTHIS = "Package";
    
    public static final String addRoles_ELEMENT = "Participant";
    public void addRoles(BuriParticipantType participant) {
        rolesId.put(participant.getId(),participant);
        rolesName.put(participant.getName(),participant);
    }
    
    public List getRolesByName(String roleName) {
        return rolesName.get(roleName);
    }
    
    public MultiValueMap getRoleByName() {
        return rolesName;
    }
    
    public BuriParticipantType getRolesById(String roleId) {
        return (BuriParticipantType)rolesId.get(roleId);
    }
    
    public static final String addProcess_ELEMENT = "WorkflowProcess";
    public void addProcess(BuriWorkflowProcessType wkfprocess) {
        processId.put(wkfprocess.getId(),wkfprocess);
        processName.put(wkfprocess.getName(),wkfprocess);
    }
    
    public List getProcessByName(String name) {
        return processName.get(name);
    }
    
    public MultiValueMap getProcessByName() {
        return processName;
    }
    
    public BuriWorkflowProcessType getProcessById(String id) {
        return (BuriWorkflowProcessType)processId.get(id);
    }
    
    public Map getProcessById() {
        return processId;
    }
    
    public static final String addApplication_ELEMENT = "Application";
    public void addApplication(BuriApplicationType app) {
        application.put(app.getId(),app);
    }
    public BuriApplicationType getApplicationById(String id) {
        return (BuriApplicationType )application.get(id);
    }
    
    public Map getApplication() {
        return application;
    }
    
    public static final String addDataField_ELEMENT = "DataField";
    public void addDataField(BuriDataFieldType dataField) {
        data.put(dataField.getId(),dataField);
    }
    
    public BuriDataFieldType getDataFieldById(String dataId) {
        return (BuriDataFieldType)data.get(dataId);
    }
    
    public Map getDataField() {
        return data;
    }
    
    public static final String addExtendedAttribute_ELEMENT = "ExtendedAttribute";
    public void addExtendedAttribute(BuriExtendedAttributeType attri) {
        ExtentedAttribute.add(attri);
    }
    
    public List getExtendedAttribute() {
        return ExtentedAttribute;
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
    
    
    public String toString() {
        StringBuffer buff = new StringBuffer("[");
        buff.append("id=").append(id);
        buff.append("/name=").append(name).append("\n");
        buff.append("-----ROLE ID----------\n");
        buff.append("/roles=").append(rolesId).append("\n");
        buff.append("-----PROCESS----------\n");
        buff.append("/process=").append(processId).append("\n");
        buff.append("-----DATA----------\n");
        buff.append("/data=").append(data).append("\n");
        buff.append("-----APPLICATION----------\n");
        buff.append("/application=").append(application).append("\n");
        buff.append("-----ExtentedAttribute----------\n");
        buff.append("/ExtentedAttribute=").append(ExtentedAttribute).append("\n");
        buff.append("]");
        return buff.toString();
        
    }
}
