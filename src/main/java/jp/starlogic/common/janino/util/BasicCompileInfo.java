/*
 * çÏê¨ì˙: 2006/05/05
 *
 */
package jp.starlogic.common.janino.util;

public class BasicCompileInfo {
    private String baseObjectName;
    private Object baseObject;
    
    private String templateFileName;
    
    private String outputClassName;
    
    private Class baseClass;
    private Class interfaceClass[];
    
    
    public Class getBaseClass() {
        return baseClass;
    }
    public void setBaseClass(Class baseClass) {
        this.baseClass = baseClass;
    }
    public Object getBaseObject() {
        return baseObject;
    }
    public void setBaseObject(Object baseObject) {
        this.baseObject = baseObject;
    }
    public String getBaseObjectName() {
        return baseObjectName;
    }
    public void setBaseObjectName(String baseObjectName) {
        this.baseObjectName = baseObjectName;
    }
    public Class[] getInterfaceClass() {
        return interfaceClass;
    }
    public void setInterfaceClass(Class[] interfaceClass) {
        this.interfaceClass = interfaceClass;
    }
    public String getOutputClassName() {
        return outputClassName;
    }
    public void setOutputClassName(String outputClassName) {
        this.outputClassName = outputClassName;
    }
    public String getTemplateFileName() {
        return templateFileName;
    }
    public void setTemplateFileName(String templateFileName) {
        this.templateFileName = templateFileName;
    }
    
    
}
