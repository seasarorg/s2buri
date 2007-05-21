select
	pkeyNum
from
	BuriPathData
where
	dataType = /*className*/'aaa'
	/*IF pathName != null*/and pathName = /*pathName*/'aaa' /*END*/
	/*IF pathType != null*/and pathType = /*pathType*/1 /*END*/
	