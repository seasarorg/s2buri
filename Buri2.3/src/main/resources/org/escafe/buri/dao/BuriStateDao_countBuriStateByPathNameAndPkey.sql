select
	count(*)
from
	BuriPathData
where
	PathName = /*pathName*/'hoge.hoge.hoge'
	and pkeyNum = /*longKey*/1
	and pkeyVal = /*manyKey*/'arekore=are'
	