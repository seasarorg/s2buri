SELECT 
	BuriState.dataid
	, BuriState.branchid
	, BuriState.pathid
	, BuriState.processdate
	, BuriState.versionno
	, BuriState.insertdate
	, BuriState.useridnum
	, BuriState.useridval
	, BuriState.stateid
	, BuriState.abortdate 
FROM 
	BuriState 
WHERE 
	processDate > CURRENT_TIMESTAMP 
	and autoRunTime < CURRENT_TIMESTAMP
