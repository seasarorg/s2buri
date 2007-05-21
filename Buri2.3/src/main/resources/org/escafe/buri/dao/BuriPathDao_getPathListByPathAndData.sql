select 
	BuriPath.*
from
	BuriPath
	,BuriState
	,BuriData
where
	BuriPath.pathID = BuriState.pathID
	and BuriData.dataID = BuriState.dataID
	and pathName Like /*path*/'pathName.path.path'
	and BuriData.dataID = /*dataID*/1
	/*IF pathType != null */and BuriPath.pathType = /*pathType*/1 /*END*/
	and BuriState.processDate > CURRENT_TIMESTAMP
;
