/*
 * 作成日: 2006/07/12
 *
 */
package org.seasar.buri.common.participantprovider.impl;

import java.util.HashSet;
import java.util.Set;

public class BuriExcelPrtiPrvidrItemDto {

    private Long id;
    private String name;
    private Set<String> roleNames = new HashSet<String>();
    private Set<BuriExcelPrtiPrvidrItemDto> lefts = new HashSet<BuriExcelPrtiPrvidrItemDto>();
    private Set<BuriExcelPrtiPrvidrItemDto> rights = new HashSet<BuriExcelPrtiPrvidrItemDto>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<BuriExcelPrtiPrvidrItemDto> getLefts() {
        return lefts;
    }

    public void setLefts(Set<BuriExcelPrtiPrvidrItemDto> lefts) {
        this.lefts = lefts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<BuriExcelPrtiPrvidrItemDto> getRights() {
        return rights;
    }

    public void setRights(Set<BuriExcelPrtiPrvidrItemDto> rights) {
        this.rights = rights;
    }

    public Set<String> getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(Set<String> roleNames) {
        this.roleNames = roleNames;
    }

    public String getItemKey() {
        return id + "/" + ((name == null) ? "" : name);
    }

    @Override
    public String toString() {
        StringBuffer buff = new StringBuffer("[");
        buff.append("id=").append(id);
        buff.append("/name=").append(name);
        buff.append("/roleNames=").append(roleNames);
        //        buff.append("/lefts=").append(lefts);
        //        buff.append("/rights=").append(rights);
        buff.append("]");
        return buff.toString();
    }

}
