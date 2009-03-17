select
	count(*)
from
	BURI_PATH_DATA
where
	DATA_TYPE = /*dataType*/'className'
	/*IF pkeyNums != null*/and PKEY_NUM in /*pkeyNums*/() /*END*/
	/*IF pkeyVals != null*/and PKEY_VAL in /*pkeyVals*/() /*END*/
	and PATH_NAME = /*pathName*/'package.process.act'
	/*IF pathType != null*/and PATH_TYPE = /*pathType*/1 /*END*/
