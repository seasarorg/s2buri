SELECT
	count(*)
FROM
	BURI_PATH_DATA
WHERE
	PATH_NAME = /*pathName*/'hoge.hoge.hoge'
	/*IF longKey != null*/AND PKEY_NUM = /*longKey*/1/*END*/
	/*IF manyKey != null*/AND PKEY_VAL = /*manyKey*/'arekore=are'/*END*/
	