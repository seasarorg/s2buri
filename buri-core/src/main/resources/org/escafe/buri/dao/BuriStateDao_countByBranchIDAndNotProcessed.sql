select
	count(*)
from
	BuriState
	,BuriBranch
where
	BuriBranch.branchID = BuriState.BranchID
	and BuriBranch.parentBranchID = /*branchID*/1
	and BuriState.processDate > CURRENT_TIMESTAMP
;
