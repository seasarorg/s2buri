select
	BuriState.*
from
	BuriState
	,BuriBranch
where
	BuriBranch.parentBranchID = /*branchID*/1
	and BuriState.branchID = BuriBranch.branchID
;