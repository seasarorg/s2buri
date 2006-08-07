select count(*)
from BuriPathDataUser
where
	dataType = /*dataType*/'className'
	/*if pkeyNums != null*/and pkeyNum in /*pkeyNums*/() /*END*/
	/*if pkeyVals != null*/and pkeyVal in /*pkeyVals*/() /*END*/
	and PathName = /*pathName*/'package.process.act'
	/*if pathType != null*/and pathType = /*pathType*/1 /*END*/
	/*if userID != null*/and BuriUserID = /*userID*/() /*END*/
	