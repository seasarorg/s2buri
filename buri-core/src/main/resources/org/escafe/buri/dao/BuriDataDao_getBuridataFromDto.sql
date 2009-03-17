select
	*
from
	Buridata
where
	dataType = /*dto.dataType*/'test.jp.starlogic.makotan.buri.dto.DtoName'
	/*IF dto.pkeyVal != null*/and pkeyVal = /*dto.pkeyVal*/'AAAA'/*END*/
	/*IF dto.pkeyNum != null*/and pkeyNum = /*dto.pkeyNum*/0/*END*/
;