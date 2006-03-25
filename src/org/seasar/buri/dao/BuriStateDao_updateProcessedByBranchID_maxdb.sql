update
	BuriState
set
	processDate = timestamp
where
	processDate > timestamp
	and stateID = /*StateID*/1
;
