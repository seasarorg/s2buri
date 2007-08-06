select
	count(*)
from
	BuriPathData
where
	PathName = /*pathName*/'hoge.hoge.hoge'
	and pkeyNum = /*longKey*/1
	/*IF manyKey != null*/and pkeyVal = /*manyKey*/'arekore=are'/*END*/
	