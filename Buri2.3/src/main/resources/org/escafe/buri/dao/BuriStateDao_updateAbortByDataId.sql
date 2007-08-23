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
	where DataID = /*dataId*/1
	)
;
