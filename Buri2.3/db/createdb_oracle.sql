drop view BuriPathDataUser;

drop view BuriPathData;


DROP TABLE BuriStateUser;

CREATE TABLE BuriStateUser (
       StateUserID          NUMBER(19,0) NOT NULL,
       StateID              NUMBER(19,0)  ,
       BuriUserID           NUMBER(19,0)  ,
       insertDate           TIMESTAMP NOT NULL,
       deleteDate           TIMESTAMP NOT NULL,
       PRIMARY KEY (StateUserID)
);

CREATE INDEX XIF1BuriStateUser ON BuriStateUser(StateID);
CREATE INDEX XIF2BuriStateUser ON BuriStateUser(BuriUserID);


DROP SEQUENCE BuriStateUserID;
CREATE SEQUENCE BuriStateUserID
 START WITH 1
 INCREMENT BY 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
;


DROP TABLE BuriState;

CREATE TABLE BuriState (
       StateID              NUMBER(19,0) NOT NULL,
       PathID               NUMBER(19,0)  ,
       DataID               NUMBER(19,0)  ,
       BranchID             NUMBER(19,0)  ,
       UserIDVal            VARCHAR(20),
       UserIDNum            NUMBER(19,0),
       Btid                 NUMBER(19,0) NOT NULL,
       insertDate           TIMESTAMP NOT NULL,
       autoRunTime          TIMESTAMP,
       processDate          TIMESTAMP,
       abortDate            TIMESTAMP NOT NULL,
       versionNo            INTEGER NOT NULL,
       PRIMARY KEY (StateID)
);

CREATE INDEX XIF1BuriState ON BuriState(PathID);
CREATE INDEX XIF2BuriState ON BuriState(DataID);
CREATE INDEX XIF4BuriState ON BuriState(BranchID);
CREATE INDEX XIF5BuriState ON BuriState(processDate);
CREATE INDEX XIF6BuriState ON BuriState(autoRunTime);


DROP SEQUENCE BuriStateID;
CREATE SEQUENCE BuriStateID
 START WITH 1
 INCREMENT BY 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
;


DROP TABLE BuriStateUndoLog;

CREATE TABLE BuriStateUndoLog (
       StateUndoLogID       NUMBER(19,0) NOT NULL,
	   StateID				NUMBER(19,0) NOT NULL,
       BranchID             NUMBER(19,0)  ,
       PathID               NUMBER(19,0)  ,
       DataID               NUMBER(19,0)  ,
       UserIDVal            VARCHAR(20),
       autoRunTime          TIMESTAMP,
       UserIDNum            NUMBER(19,0),
       insertDate           TIMESTAMP NOT NULL,
       Btid                 NUMBER(19,0) NOT NULL,
       processDate          TIMESTAMP,
       abortDate            TIMESTAMP NOT NULL,
       versionNo            INTEGER NOT NULL,
       createBtid           NUMBER(19,0) NOT NULL,
       PRIMARY KEY (StateUndoLogID)
);

CREATE INDEX XIF1BuriStateUndoLog ON BuriStateUndoLog(StateID);


DROP SEQUENCE BuriStateUndoLogID;
CREATE SEQUENCE BuriStateUndoLogID
 START WITH 1
 INCREMENT BY 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
;


DROP TABLE BuriTransaction;

CREATE TABLE BuriTransaction (
       Btid                 NUMBER(19,0) NOT NULL,
       insertDate           TIMESTAMP NOT NULL,
       versionNo            INTEGER NOT NULL,
       PRIMARY KEY (Btid)
	
);


DROP SEQUENCE BuriTransactionID;
CREATE SEQUENCE BuriTransactionID
 START WITH 1
 INCREMENT BY 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
;


DROP TABLE BuriBranch;

CREATE TABLE BuriBranch (
       BranchID             NUMBER(19,0) NOT NULL,
       parentBranchID       NUMBER(19,0)  ,
       PathID               NUMBER(19,0)  ,
       DataID               NUMBER(19,0)  ,
       processDate          TIMESTAMP,
       Btid                 INTEGER NOT NULL,
       versionNo            INTEGER NOT NULL,
       PRIMARY KEY (BranchID)
);

CREATE INDEX XIF1BuriBranch ON BuriBranch(PathID);
CREATE INDEX XIF2BuriBranch ON BuriBranch(parentBranchID);
CREATE INDEX XIF3BuriBranch ON BuriBranch(DataID);

DROP SEQUENCE BuriBranchID;
CREATE SEQUENCE BuriBranchID
 START WITH 1
 INCREMENT BY 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
;


DROP TABLE BuriDataPathHistory;

CREATE TABLE BuriDataPathHistory (
       historyID            NUMBER(19,0) NOT NULL,
       PathID               NUMBER(19,0)  ,
       PathName             VARCHAR(200),
       DataID               NUMBER(19,0)  ,
       action               VARCHAR(50),
       BuriUserID           NUMBER(19,0),
       Btid                 NUMBER(19,0) NOT NULL,
       insertDate           TIMESTAMP NOT NULL,
       PRIMARY KEY (historyID)
);

CREATE INDEX XIF3BuriDataPathHi ON BuriDataPathHistory(PathID);
CREATE INDEX XIF4BuriDataPathHi ON BuriDataPathHistory(DataID);


DROP SEQUENCE BuriDataPathHistoryID;
CREATE SEQUENCE BuriDataPathHistoryID
 START WITH 1
 INCREMENT BY 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
;


DROP TABLE BuriData;

CREATE TABLE BuriData (
       DataID               NUMBER(19,0) NOT NULL,
       pkeyVal              VARCHAR(250),
       pkeyNum              NUMBER(19,0),
       dataType             VARCHAR(200) NOT NULL,
       InsertUserID         NUMBER(19,0)  ,
       PRIMARY KEY (DataID)
);

CREATE INDEX XIF1BuriData ON BuriData(InsertUserID);
CREATE INDEX XIE1BuriData ON BuriData(pkeyVal,dataType);
CREATE INDEX XIE2BuriData ON BuriData(pkeyNum,dataType);


DROP SEQUENCE BuriDataID;
CREATE SEQUENCE BuriDataID
 START WITH 1
 INCREMENT BY 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
;


DROP TABLE BuriPath;

CREATE TABLE BuriPath (
       PathID               NUMBER(19,0) NOT NULL,
       PathName             VARCHAR(100) NOT NULL,
       RealPathName         VARCHAR(100) NOT NULL,
       pathType             INTEGER,
       PRIMARY KEY (PathID)
);

CREATE INDEX XIE1BuriPath ON BuriPath(PathName);
CREATE INDEX XIE2BuriPath ON BuriPath(RealPathName);
CREATE INDEX XIE3BuriPath ON BuriPath(pathType);

DROP SEQUENCE BuriPathID;
CREATE SEQUENCE BuriPathID
 START WITH 1
 INCREMENT BY 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
;



DROP TABLE BuriUser;

CREATE TABLE BuriUser (
       BuriUserID           NUMBER(19,0) NOT NULL,
       UserIDVal            VARCHAR(100),
       UserIDNum            NUMBER(19,0),
       PRIMARY KEY (BuriUserID)
);

CREATE INDEX XIE2BuriUser ON BuriUser(UserIDVal,UserIDNum);

DROP SEQUENCE BuriUserID;
CREATE SEQUENCE BuriUserID
 START WITH 1
 INCREMENT BY 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
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
	,BuriData.tableName AS tableName
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
	,BuriPathData.tableName AS tableName
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


	
