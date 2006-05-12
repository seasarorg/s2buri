update
	BuriState
set
	processDate = CURRENT_TIMESTAMP
	,abortDate = CURRENT_TIMESTAMP
where
	processDate > CURRENT_TIMESTAMP
	and BranchID = /*branchID*/1
	and stateID != /*savingStateID*/1
;
