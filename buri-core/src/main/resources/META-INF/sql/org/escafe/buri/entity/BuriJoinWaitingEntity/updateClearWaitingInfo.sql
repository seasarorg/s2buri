UPDATE
	BURI_JOIN_WAITING
SET
	PROCESS_DATE = CURRENT_TIMESTAMP,
	ABORT_DATE = CURRENT_TIMESTAMP
WHERE
	PROCESS_DATE > CURRENT_TIMESTAMP
	AND BRANCH_ID = /*branchId*/1
