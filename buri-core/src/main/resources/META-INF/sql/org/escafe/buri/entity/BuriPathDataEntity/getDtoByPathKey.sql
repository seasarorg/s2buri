select
	*
from
	BURI_PATH_DATA
where
	PATH_NAME like /*pathName*/'hoge%'
	/*IF pathType != null */and PATH_TYPE = /*pathType*/1 /*END*/
	/*IF pkeyNum != null */and PKEY_NUM = /*pkeyNum*/1 /*END*/
	/*IF pkeyVal != null */and PKEY_VAL = /*pkeyVal*/'hogehoge' /*END*/
	and DATA_TYPE = /*dataType*/'dataType'
;

