select
	*
from
	BuriPathData
where
	PathName like /*PathName*/'hoge%'
	/*IF pathType != null */and pathType = /*pathType*/1 /*END*/
	/*IF pkeyNum != null */and pkeyNum = /*pkeyNum*/1 /*END*/
	/*IF pkeyVal != null */and pkeyVal = /*pkeyVal*/'hogehoge' /*END*/
	and dataType = /*dataType*/'dataType'
;

