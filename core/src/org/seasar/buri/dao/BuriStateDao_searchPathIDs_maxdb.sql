select
	count(*)
from 
	BuriState
where
	DataID = /*dataID*/100
	and PathID in /*pathIDs*/(1,2,3)
	and processDate > timestamp
;
