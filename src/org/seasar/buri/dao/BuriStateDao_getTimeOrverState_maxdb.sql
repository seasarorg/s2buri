SELECT 
	*
FROM 
	BuriState 
WHERE 
	processDate > timestamp 
	and autoRunTime < timestamp
