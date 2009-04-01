UPDATE
	BURI_STATE
SET
	PROCESS_DATE = CURRENT_TIMESTAMP,
	ABORT_DATE = CURRENT_TIMESTAMP
WHERE
	PROCESS_DATE > CURRENT_TIMESTAMP
	AND STATE_ID IN 
	(
		SELECT
			DATA_ID
		FROM
			BURI_DATA
		WHERE 
			DATA_TYPE = /*dataType*/'dataType'
			/*IF pathType != null */AND PATH_TYPE = /*pathType*/1 /*END*/
			/*IF pkeyNum != null */AND PKEY_NUM = /*pkeyNum*/1 /*END*/
			/*IF pkeyVal != null */AND PKEY_VAL = /*pkeyVal*/'hogehoge' /*END*/
	)
;
