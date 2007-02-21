<#list tableList as table>


DROP TABLE ${table.tableName};

DROP SEQUENCE ${table.tableName}ID;

</#list>

