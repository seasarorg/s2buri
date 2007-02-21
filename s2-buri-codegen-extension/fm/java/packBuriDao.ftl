package ${package}.dao;

import java.util.List;

import ${package}.dto.${table.tableNameForDto?cap_first}PackDto;
import ${package}.finddto.${table.tableNameForDto?cap_first}PackFindDto;

public interface ${table.tableNameForDto?cap_first}PackDao {
    public Class BEAN = ${table.tableNameForDto?cap_first}PackDto.class;

    public List getAll${table.tableNameForDto?cap_first}();

	<#assign primarys=table.primaryKey>
	<#if primarys?size = 1 >
	<#assign primaryField=primarys?first>
	<#assign javaTypeName=primaryField.field.dataType.langDataType.className>
    public String get${table.tableNameForDto?cap_first}_QUERY = "${primaryField.field.fieldName?cap_first}  = ?";
    public ${table.tableNameForDto?cap_first}PackDto get${table.tableNameForDto?cap_first}(${javaTypeName} ${primaryField.field.fieldNameForDto?uncap_first});
	</#if>

	<#if primarys?size = 1 >
	<#assign primaryField=primarys?first>
	<#assign primaryName=primaryField.field.fieldName?uncap_first>
	<#assign javaTypeName=primaryField.field.dataType.langDataType.className>
    public String get${table.tableNameForDto?cap_first}ByIds_ARGS = "${primaryField.field.fieldNameForDto?uncap_first}s";
    public String get${table.tableNameForDto?cap_first}ByIds_QUERY = "${primaryField.field.fieldName} in /*${primaryField.field.fieldNameForDto?uncap_first}s*/(1)";
    public List get${table.tableNameForDto?cap_first}ByIds(List ${primaryField.field.fieldNameForDto?uncap_first}s);
	</#if>
    
    public String find_ARGS = "dto,paths";
    public List find(${table.tableName?cap_first}FindDto dto,List paths);
    
    public String findAndUser_ARGS = "dto,paths,userIDs";
    public List findAndUser(${table.tableName?cap_first}FindDto dto,List paths,List userIDs);
    
    public String find_ARGS = "dto";
    public List find(${table.tableNameForDto?cap_first}PackFindDto dto);

    public ${table.tableNameForDto?cap_first}PackDto soleMatch(${table.tableNameForDto?cap_first}PackFindDto dto);
    
    public void insert(${table.tableNameForDto?cap_first}PackDto dto);
    
    public void update(${table.tableNameForDto?cap_first}PackDto dto);
    
    public void delete(${table.tableNameForDto?cap_first}PackDto dto);
}
