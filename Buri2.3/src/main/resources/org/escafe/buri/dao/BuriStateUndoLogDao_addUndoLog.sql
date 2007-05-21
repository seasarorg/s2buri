insert into BuriStateUndoLog 
(
	stateID,
	pathID,
	dataID,
	branchID,
	userIDVal,
	userIDNum,
	BTID,
	createBTID,
	insertDate,
	autoRunTime,
	processDate,
	abortDate,
	versionNo
)
select 
	stateID,
	pathID,
	dataID,
	branchID,
	userIDVal,
	userIDNum,
	/*BTID*/1,
	BTID,
	insertDate,
	autoRunTime,
	processDate,
	abortDate,
	versionNo
from
	BuriState
/*BEGIN*/where
	/*IF stateID != 0 */stateID = /*stateID*/1 /*END*/
	/*IF branchID != 0 */and branchID = /*branchID*/1 /*END*/
/*END*/
