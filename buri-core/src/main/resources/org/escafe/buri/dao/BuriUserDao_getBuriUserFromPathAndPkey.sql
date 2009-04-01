select
	BuriUserID
	,UserIDVal
	,UserIDNum
from
	BuriPathDataUser
where
	PathName = /*path*/'1'
	/*IF pkeyNum != null*/and pkeyNum = /*pkeyNum*/1/*END*/
	/*IF pkeyVal != null*/and pkeyVal = /*pkeyVal*/'1'/*END*/
