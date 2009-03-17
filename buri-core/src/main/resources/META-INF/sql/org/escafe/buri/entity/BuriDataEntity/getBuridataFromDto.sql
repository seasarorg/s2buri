SELECT
	*
FROM
	BURI_DATA
WHERE
	DATA_TYPE = /*dto.dataType*/'test.jp.starlogic.makotan.buri.dto.DtoName'
	/*IF dto.pkeyVal != null*/and PKEY_VAL = /*dto.pkeyVal*/'AAAA'/*END*/
	/*IF dto.pkeyNum != null*/and PKEY_NUM = /*dto.pkeyNum*/0/*END*/
;