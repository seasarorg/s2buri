select count(*)
from BuriPathData
where
	dataType = /*dataType*/'className'
	/*IF pkeyNums != null*/and pkeyNum in /*pkeyNums*/() /*END*/
	/*IF pkeyVals != null*/and pkeyVal in /*pkeyVals*/() /*END*/
	and PathName = /*pathName*/'package.process.act'
	/*IF pathType != null*/and pathType = /*pathType*/1 /*END*/
