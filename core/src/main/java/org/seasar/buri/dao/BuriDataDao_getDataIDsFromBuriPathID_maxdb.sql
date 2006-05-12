select
	BuriData.pkeyNum
from
	BuriState
	,BuriData
where
	BuriState.DataID = BuriData.DataID
	and PathID = /*PathID*/1
	and processDate > timestamp
	/*IF userIDVal != null */ and userIDVal = /*userIDVal*/'hoge' /*END*/
	/*IF userIDNum != null */ and userIDNum = /*userIDNum*/1 /*END*/
;
