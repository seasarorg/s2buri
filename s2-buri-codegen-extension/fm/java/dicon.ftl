<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE components PUBLIC "-//SEASAR2.1//DTD S2Container//EN"
	"http://www.seasar.org/dtd/components21.dtd">
<components>
	<include path="dao.dicon"/>
	<include path="aop.dicon"/>

	<#list tableList as table>

	<component name="${table.tableName?cap_first}Dao" class="${package}.dao.${table.tableName?cap_first}Dao">
		<aspect>aop.traceInterceptor</aspect>
		<aspect>dao.interceptor</aspect>
	</component>
	</#list>

	<#list tableList as table>
	<#if table.parentTable?size != 0 >
	<component name="${table.tableName?cap_first}PackDao" class="${package}.dao.${table.tableName?cap_first}PackDao">
		<aspect>aop.traceInterceptor</aspect>
		<aspect>dao.interceptor</aspect>
	</component>
	
	</#if>
	</#list>
	
</components>
