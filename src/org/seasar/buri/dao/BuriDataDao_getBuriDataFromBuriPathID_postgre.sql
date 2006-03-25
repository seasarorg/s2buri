select
	BuriData.*
from
	BuriState
	,BuriData
where
	BuriState.DataID = BuriData.DataID
	and BuriState.PathID = /*PathID*/1
	and BuriState.processDate > CURRENT_TIMESTAMP
	/*IF userIDVal != null */ and BuriState.userIDVal = /*userIDVal*/'hoge' /*END*/
	/*IF userIDNum != null */ and BuriState.userIDNum = /*userIDNum*/1 /*END*/
;
