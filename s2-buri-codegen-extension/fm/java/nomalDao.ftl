package ${package}.dao;

import java.util.List;

import ${package}.dto.${table.tableName?cap_first}Dto;
import ${package}.finddto.${table.tableName?cap_first}FindDto;

public interface ${table.tableName?cap_first}Dao {
    public Class BEAN = ${table.tableName?cap_first}Dto.class;

    public List getAll${table.tableName?cap_first}();

	<#assign primarys=table.primaryKey>
	<#if primarys?size = 1 >
	<#assign primaryField=primarys?first>
	<#assign primaryName=primaryField.field.fieldName?uncap_first>
	<#assign javaTypeName=primaryField.field.dataType.langDataType.className>
    public String get${table.tableName?cap_first}_QUERY = "${primaryName} = ?";
    public ${table.tableName?cap_first}Dto get${table.tableName?cap_first}(${javaTypeName} ${primaryName});
	</#if>

	<#if primarys?size = 1 >
	<#assign primaryField=primarys?first>
	<#assign primaryName=primaryField.field.fieldName?uncap_first>
	<#assign javaTypeName=primaryField.field.dataType.langDataType.className>
    public String get${table.tableName?cap_first}ByIds_ARGS = "${primaryName}s";
    public String get${table.tableName?cap_first}ByIds_QUERY = "${primaryName} in /*${primaryName}s*/(1)";
    public List get${table.tableName?cap_first}ByIds(List ${primaryName}s);
	</#if>
    
    public String find_ARGS = "dto,paths";
    public List find(${table.tableName?cap_first}FindDto dto,List paths);
    
    public String findAndUser_ARGS = "dto,paths,userIDs";
    public List findAndUser(${table.tableName?cap_first}FindDto dto,List paths,List userIDs);

    //public String soleMatch_ARGS = "dto";
    //public ${table.tableName?cap_first}Dto soleMatch(${table.tableName?cap_first}FindDto dto);
    
    public void insert(${table.tableName?cap_first}Dto dto);
    
    public void update(${table.tableName?cap_first}Dto dto);
    
    public void delete(${table.tableName?cap_first}Dto dto);
}
