package ${package}.dao;

import java.util.List;

import ${package}.dto.${table.tableNameForDto?cap_first}Dto;
import ${package}.finddto.${table.tableNameForDto?cap_first}FindDto;

public interface ${table.tableNameForDto?cap_first}Dao {
    public Class BEAN = ${table.tableNameForDto?cap_first}Dto.class;

    public List<${table.tableNameForDto?cap_first}Dto> getAll${table.tableNameForDto?cap_first}();

	<#assign primarys=table.primaryKey>
	<#if primarys?size = 1 >
	<#assign primaryField=primarys?first>
	<#assign javaTypeName=primaryField.field.dataType.langDataType.className>
    public String get${table.tableNameForDto?cap_first}_QUERY = "${primaryField.field.fieldName} = ?";
    public ${table.tableNameForDto?cap_first}Dto get${table.tableNameForDto?cap_first}(${javaTypeName} ${primaryField.field.fieldNameForDto});
	</#if>

	<#if primarys?size = 1 >
	<#assign primaryField=primarys?first>
	<#assign javaTypeName=primaryField.field.dataType.langDataType.className>
    public String get${table.tableNameForDto?cap_first}ByIds_ARGS = "${primaryField.field.fieldNameForDto}s";
    public String get${table.tableNameForDto?cap_first}ByIds_QUERY = "${primaryField.field.fieldName} in /*${primaryField.field.fieldNameForDto?uncap_first}s*/(1)";
    public List<${table.tableNameForDto?cap_first}Dto> get${table.tableNameForDto?cap_first}ByIds(List ${primaryField.field.fieldNameForDto?uncap_first}s);
	</#if>
    
    public String find_ARGS = "dto,keys";
    public List<${table.tableNameForDto?cap_first}Dto> find(${table.tableNameForDto?cap_first}FindDto dto,List keys);

    public ${table.tableNameForDto?cap_first}Dto soleMatch(${table.tableNameForDto?cap_first}FindDto dto);
    
    public void insert(${table.tableNameForDto?cap_first}Dto dto);
    
    public void update(${table.tableNameForDto?cap_first}Dto dto);
    
    public void delete(${table.tableNameForDto?cap_first}Dto dto);
}

