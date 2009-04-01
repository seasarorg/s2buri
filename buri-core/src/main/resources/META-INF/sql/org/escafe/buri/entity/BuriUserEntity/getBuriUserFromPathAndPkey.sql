SELECT
	BURI_USER_ID,
	USER_ID_VAL,
	USER_ID_NUM
FROM
	BURI_PATH_DATA_USER
WHERE
	PATH_NAME = /*path*/'1'
	/*IF pkeyNum != null*/AND PKEY_NUM = /*pkeyNum*/1/*END*/
	/*IF pkeyVal != null*/AND PKEY_VAL = /*pkeyVal*/'1'/*END*/
