update
	BuriState
set
	processDate = CURRENT_TIMESTAMP
where
	processDate > CURRENT_TIMESTAMP
	and stateID = /*savingStateID*/1
;
