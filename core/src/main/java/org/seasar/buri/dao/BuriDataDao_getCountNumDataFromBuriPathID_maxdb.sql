select
	count(*) as pkeyNum
	,BuriData.dataType as DataType
from
	BuriState
	,BuriData
where
	BuriState.DataID = BuriData.DataID
	and PathID = /*PathID*/1
	and processDate > timestamp
	and BuriData.pkeynum is not null
	/*IF userIDVal != null */ and userIDVal = /*userIDVal*/'hoge' /*END*/
	/*IF userIDNum != null */ and userIDNum = /*userIDNum*/1 /*END*/
group by BuriData.dataType
;
