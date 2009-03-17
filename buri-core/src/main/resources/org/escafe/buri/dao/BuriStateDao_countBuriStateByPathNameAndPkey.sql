select
	count(*)
from
	BuriPathData
where
	PathName = /*pathName*/'hoge.hoge.hoge'
	/*IF longKey != null*/and pkeyNum = /*longKey*/1/*END*/
	/*IF manyKey != null*/and pkeyVal = /*manyKey*/'arekore=are'/*END*/
	