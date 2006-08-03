SELECT 
	BuriPathData.pathid
	, BuriPathData.pathname
	, BuriPathData.dataid
	, BuriPathData.pkeyval
	, BuriPathData.pkeynum
	, BuriPathData.datatype
	, BuriPathData.realpathname
	, BuriPathData.pathtype
	, BuriPathData.stateid
	, BuriPathData.autoruntime 
FROM BuriPathData 
WHERE autoRunTime < timeofday()::Timestamp
