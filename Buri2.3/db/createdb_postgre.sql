drop view BuriPathDataUser;

drop view BuriPathData;


DROP TABLE BuriStateUser;

CREATE TABLE BuriStateUser (
       StateUserID          INTEGER NOT NULL,
       StateID              INTEGER  ,
       BuriUserID           INTEGER  ,
       insertDate           TIMESTAMP NOT NULL,
       deleteDate           TIMESTAMP NOT NULL,
       PRIMARY KEY (StateUserID)
);

DROP INDEX XIF1BuriStateUser;
DROP INDEX XIF2BuriStateUser;

CREATE INDEX XIF1BuriStateUser ON BuriStateUser(StateID);
CREATE INDEX XIF2BuriStateUser ON BuriStateUser(BuriUserID);


DROP SEQUENCE BuriStateUserID;
CREATE SEQUENCE BuriStateUserID
 START WITH 1
 INCREMENT BY 1
;


DROP TABLE BuriState;

CREATE TABLE BuriState (
       StateID              INTEGER NOT NULL,
       PathID               INTEGER  ,
       DataID               INTEGER  ,
       BranchID             INTEGER  ,
       UserIDVal            VARCHAR(20),
       UserIDNum            INTEGER,
       Btid                 INTEGER NOT NULL,
       insertDate           TIMESTAMP NOT NULL,
       autoRunTime          TIMESTAMP,
       processDate          TIMESTAMP,
       abortDate            TIMESTAMP NOT NULL,
       versionNo            INTEGER NOT NULL,
       PRIMARY KEY (StateID)
);

DROP INDEX XIF1BuriState;
DROP INDEX XIF2BuriState;
DROP INDEX XIF4BuriState;
DROP INDEX XIF5BuriState;
DROP INDEX XIF6BuriState;

CREATE INDEX XIF1BuriState ON BuriState(PathID);
CREATE INDEX XIF2BuriState ON BuriState(DataID);
CREATE INDEX XIF4BuriState ON BuriState(BranchID);
CREATE INDEX XIF5BuriState ON BuriState(processDate);
CREATE INDEX XIF6BuriState ON BuriState(autoRunTime);


DROP SEQUENCE BuriStateID;
CREATE SEQUENCE BuriStateID
 START WITH 1
 INCREMENT BY 1
;


DROP TABLE BuriStateUndoLog;

CREATE TABLE BuriStateUndoLog (
       StateUndoLogID       INTEGER NOT NULL,
	   StateID				INTEGER NOT NULL,
       BranchID             INTEGER  ,
       PathID               INTEGER  ,
       DataID               INTEGER  ,
       UserIDVal            VARCHAR(20),
       autoRunTime          TIMESTAMP,
       UserIDNum            INTEGER,
       insertDate           TIMESTAMP NOT NULL,
       Btid                 INTEGER NOT NULL,
       processDate          TIMESTAMP,
       abortDate            TIMESTAMP NOT NULL,
       versionNo            INTEGER NOT NULL,
       createBtid           INTEGER NOT NULL,
       PRIMARY KEY (StateUndoLogID)
);

DROP INDEX XIF1BuriStateUndoLog;

CREATE INDEX XIF1BuriStateUndoLog ON BuriStateUndoLog(StateID);


DROP SEQUENCE BuriStateUndoLogID;
CREATE SEQUENCE BuriStateUndoLogID
 START WITH 1
 INCREMENT BY 1
;


DROP TABLE BuriTransaction;

CREATE TABLE BuriTransaction (
       Btid                 INTEGER NOT NULL,
       insertDate           TIMESTAMP NOT NULL,
       versionNo            INTEGER NOT NULL,
       PRIMARY KEY (Btid)
	
);

DROP INDEX XIF1BuriTransaction;

CREATE INDEX XIF1BuriTransaction ON BuriTransaction(Btid);

DROP SEQUENCE BuriTransactionID;
CREATE SEQUENCE BuriTransactionID
 START WITH 1
 INCREMENT BY 1
;


DROP TABLE BuriBranch;

CREATE TABLE BuriBranch (
       BranchID             INTEGER NOT NULL,
       parentBranchID       INTEGER  ,
       PathID               INTEGER  ,
       DataID               INTEGER  ,
       processDate          TIMESTAMP,
       Btid                 INTEGER NOT NULL,
       versionNo            INTEGER NOT NULL,
       PRIMARY KEY (BranchID)
);

DROP INDEX XIF1BuriBranch;
DROP INDEX XIF2BuriBranch;
DROP INDEX XIF3BuriBranch;

CREATE INDEX XIF1BuriBranch ON BuriBranch(PathID);
CREATE INDEX XIF2BuriBranch ON BuriBranch(parentBranchID);
CREATE INDEX XIF3BuriBranch ON BuriBranch(DataID);

DROP SEQUENCE BuriBranchID;
CREATE SEQUENCE BuriBranchID
 START WITH 1
 INCREMENT BY 1
;


DROP TABLE BuriDataPathHistory;

CREATE TABLE BuriDataPathHistory (
       historyID            INTEGER NOT NULL,
       PathID               INTEGER  ,
       PathName             VARCHAR(200),
       DataID               INTEGER  ,
       action               VARCHAR(50),
       BuriUserID           INTEGER,
       Btid                 INTEGER NOT NULL,
       insertDate           TIMESTAMP NOT NULL,
       PRIMARY KEY (historyID)
);

DROP INDEX XIF3BuriDataPathHi;
DROP INDEX XIF4BuriDataPathHi;

CREATE INDEX XIF3BuriDataPathHi ON BuriDataPathHistory(PathID);
CREATE INDEX XIF4BuriDataPathHi ON BuriDataPathHistory(DataID);


DROP SEQUENCE BuriDataPathHistoryID;
CREATE SEQUENCE BuriDataPathHistoryID
 START WITH 1
 INCREMENT BY 1
;


DROP TABLE BuriData;

CREATE TABLE BuriData (
       DataID               INTEGER NOT NULL,
       pkeyVal              VARCHAR(250),
       pkeyNum              INTEGER,
       dataType             VARCHAR(200) NOT NULL,
       InsertUserID         INTEGER  ,
       PRIMARY KEY (DataID)
);

DROP INDEX XIF1BuriData;
DROP INDEX XIE1BuriData;
DROP INDEX XIE2BuriData;

CREATE INDEX XIF1BuriData ON BuriData(InsertUserID);
CREATE INDEX XIE1BuriData ON BuriData(pkeyVal,dataType);
CREATE INDEX XIE2BuriData ON BuriData(pkeyNum,dataType);


DROP SEQUENCE BuriDataID;
CREATE SEQUENCE BuriDataID
 START WITH 1
 INCREMENT BY 1
;


DROP TABLE BuriPath;

CREATE TABLE BuriPath (
       PathID               INTEGER NOT NULL,
       PathName             VARCHAR(100) NOT NULL,
       RealPathName         VARCHAR(100) NOT NULL,
       pathType             INTEGER,
       PRIMARY KEY (PathID)
);

DROP INDEX XIE0BuriPath;
DROP INDEX XIE1BuriPath;
DROP INDEX XIE2BuriPath;
DROP INDEX XIE3BuriPath;

CREATE INDEX XIE0BuriPath ON BuriPath(PathID);
CREATE INDEX XIE1BuriPath ON BuriPath(PathName);
CREATE INDEX XIE2BuriPath ON BuriPath(RealPathName);
CREATE INDEX XIE3BuriPath ON BuriPath(pathType);

DROP SEQUENCE BuriPathID;
CREATE SEQUENCE BuriPathID
 START WITH 1
 INCREMENT BY 1
;



DROP TABLE BuriUser;

CREATE TABLE BuriUser (
       BuriUserID           INTEGER NOT NULL,
       UserIDVal            VARCHAR(100),
       UserIDNum            INTEGER,
       PRIMARY KEY (BuriUserID)
);

DROP INDEX XIE2BuriUser;

CREATE INDEX XIE2BuriUser ON BuriUser(UserIDVal,UserIDNum);

DROP SEQUENCE BuriUserID;
CREATE SEQUENCE BuriUserID
 START WITH 1
 INCREMENT BY 1
;


create view BuriPathData as 
select 
	BuriPath.pathID as pathID
	,BuriPath.PathName as PathName
	,BuriPath.RealPathName as RealPathName
	,BuriPath.pathType as pathType
	,BuriData.pkeyNum as pkeyNum
	,BuriData.pkeyVal as pkeyVal
	,BuriData.dataType as dataType
	,BuriData.dataID as dataID
	,BuriState.StateID as StateID
	,BuriState.autoRunTime as autoRunTime
from
	BuriPath
	,BuriState
	,BuriData
where
	BuriPath.pathID = BuriState.pathID
	and BuriData.dataID = BuriState.dataID
	and BuriState.processDate > CURRENT_TIMESTAMP
;


create view BuriPathDataUser as 
select
	BuriPathData.pathID as pathID
	,BuriPathData.PathName as PathName
	,BuriPathData.RealPathName as RealPathName
	,BuriPathData.pathType as pathType
	,BuriPathData.pkeyNum as pkeyNum
	,BuriPathData.pkeyVal as pkeyVal
	,BuriPathData.dataType as dataType
	,BuriPathData.dataID as dataID
	,BuriPathData.StateID as StateID
	,BuriPathData.autoRunTime as autoRunTime
	,BuriUser.BuriUserID as BuriUserID
	,BuriUser.UserIDVal as UserIDVal
	,BuriUser.UserIDNum as UserIDNum
from
	BuriPathData
	,BuriStateUser
	,BuriUser
where
	BuriPathData.StateID = BuriStateUser.StateID
	and BuriUser.BuriUserID = BuriStateUser.BuriUserID
	and BuriStateUser.deleteDate > CURRENT_TIMESTAMP
;


	
