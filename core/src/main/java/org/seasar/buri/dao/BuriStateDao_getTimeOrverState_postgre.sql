SELECT 
	*
FROM 
	BuriState 
WHERE 
	processDate > CURRENT_TIMESTAMP 
	and autoRunTime < timeofday()::Timestamp
