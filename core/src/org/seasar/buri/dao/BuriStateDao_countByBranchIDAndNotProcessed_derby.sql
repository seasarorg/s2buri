select
	count(*)
from
	BuriState
where
	BranchID = /*branchID*/1
	and processDate > CURRENT_TIMESTAMP
;
