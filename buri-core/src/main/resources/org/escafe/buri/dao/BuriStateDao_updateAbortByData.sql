update
	BuriState
set
	processDate = CURRENT_TIMESTAMP
	,abortDate = CURRENT_TIMESTAMP
where
	processDate > CURRENT_TIMESTAMP
	and stateID in 
	(
	select dataId
	from Buridata
	where 
	dataType = /*dataType*/'dataType'
	/*IF pathType != null */and pathType = /*pathType*/1 /*END*/
	/*IF pkeyNum != null */and pkeyNum = /*pkeyNum*/1 /*END*/
	/*IF pkeyVal != null */and pkeyVal = /*pkeyVal*/'hogehoge' /*END*/
	)
;
