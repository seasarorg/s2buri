select
	BuriPath.PathID
	,BuriPath.PathName
	,BuriPath.RealPathName
from
	BuriState
	,BuriPath
where
	BuriState.PathID = BuriPath.PathID
	and BuriState.DataID = /*DataID*/1
	and processDate > timestamp
;
