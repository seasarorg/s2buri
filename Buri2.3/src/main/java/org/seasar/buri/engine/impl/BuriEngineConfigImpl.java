/*
 * �쐬��: 2006/07/17
 *
 */
package org.seasar.buri.engine.impl;

import java.util.ArrayList;
import java.util.List;

import org.seasar.buri.engine.BuriConfigDto;
import org.seasar.buri.engine.BuriEngineConfig;
import org.seasar.buri.engine.ParticipantProvider;

public class BuriEngineConfigImpl implements BuriEngineConfig {
    private List resourceConfigs = new ArrayList();
    private List fileConfigs = new ArrayList();

    public List getResourceConfigs() {
        return resourceConfigs;
    }

    public List getFileConfigs() {
        return fileConfigs;
    }
    
    public void addResourceConfig(String fileName,String packageName) {
        addResourceConfig(fileName,packageName,null);
    }
    
    public void addResourceConfig(String fileName,String packageName,ParticipantProvider provider) {
        BuriConfigDto dto = new BuriConfigDto();
        dto.setFileName(fileName);
        dto.setPackageName(packageName);
        dto.setProvider(provider);
        resourceConfigs.add(dto);
    }
    
    public void addFileConfig(String fileName,String packageName) {
        addFileConfig(fileName,packageName,null);
    }
    
    public void addFileConfig(String fileName,String packageName,ParticipantProvider provider) {
        BuriConfigDto dto = new BuriConfigDto();
        dto.setFileName(fileName);
        dto.setPackageName(packageName);
        dto.setProvider(provider);
        fileConfigs.add(dto);
    }

}
