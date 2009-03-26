select
	PKEY_NUM
from
	BURI_PATH_DATA
where
	DATA_TYPE = /*className*/'aaa'
	/*IF pathName != null*/and PATH_NAME = /*pathName*/'aaa' /*END*/
	/*IF pathType != null*/and PATH_NAME = /*pathType*/1 /*END*/
	