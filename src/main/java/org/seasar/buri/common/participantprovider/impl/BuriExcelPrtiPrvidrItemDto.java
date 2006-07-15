/*
 * çÏê¨ì˙: 2006/07/12
 *
 */
package org.seasar.buri.common.participantprovider.impl;

import java.util.HashSet;
import java.util.Set;

public class BuriExcelPrtiPrvidrItemDto {
    private Long id = new Long(0);
    private String name="";
    private Set roles = new HashSet();
    
    private Set lefts = new HashSet();
    private Set rights = new HashSet();
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Set getLefts() {
        return lefts;
    }
    public void setLefts(Set lefts) {
        this.lefts = lefts;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Set getRights() {
        return rights;
    }
    public void setRights(Set rights) {
        this.rights = rights;
    }
    
    public Set getRoles() {
        return roles;
    }
    public void setRoles(Set roles) {
        this.roles = roles;
    }
    public String getItemKey() {
        return id + "/" + name;
    }
    
    public String toString() {
        StringBuffer buff = new StringBuffer("[");
        buff.append("id=").append(id);
        buff.append("/name=").append(name);
        buff.append("/roles=").append(roles);
//        buff.append("/lefts=").append(lefts);
//        buff.append("/rights=").append(rights);
        buff.append("]");
        return buff.toString();
    }
    
}
