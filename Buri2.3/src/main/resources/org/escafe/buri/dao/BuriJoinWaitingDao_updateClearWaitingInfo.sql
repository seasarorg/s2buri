update
	BuriJoinWaiting
set
	processDate = CURRENT_TIMESTAMP
	,abortDate = CURRENT_TIMESTAMP
where
	processDate > CURRENT_TIMESTAMP
	and BranchID in ( 
		select BranchID
		from BuriBranch
		where
			parentBranchID = /*branchID*/1
		)
	