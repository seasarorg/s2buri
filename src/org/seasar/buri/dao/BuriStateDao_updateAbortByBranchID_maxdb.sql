update
	BuriState
set
	processDate = timestamp
	,abortDate = timestamp
where
	processDate > timestamp
	and BranchID = /*branchID*/1
	and stateID != /*savingStateID*/1
;
