
CREATE TABLE BuriStateUser (
       StateUserID          bigint NOT NULL,
       StateID              bigint  ,
       BuriUserID           bigint  ,
       insertDate           TIMESTAMP NOT NULL,
       deleteDate           TIMESTAMP NOT NULL,
       PRIMARY KEY (StateUserID)
);


CREATE INDEX XIF1BuriStateUser ON BuriStateUser(StateID);
CREATE INDEX XIF2BuriStateUser ON BuriStateUser(BuriUserID);

CREATE SEQUENCE BuriStateUserID
 START WITH 1
 INCREMENT BY 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
;


CREATE TABLE BuriState (
       StateID              bigint NOT NULL,
       PathID               bigint  ,
       DataID               bigint  ,
       BranchID             bigint  ,
       UserIDVal            VARCHAR(20),
       UserIDNum            bigint,
       ParticipantName      VARCHAR(200),
       ParticipantType      VARCHAR(200),
       Btid                 bigint NOT NULL,
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

CREATE SEQUENCE BuriStateID
 START WITH 1
 INCREMENT BY 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
;



CREATE TABLE BuriStateUndoLog (
       StateUndoLogID       bigint NOT NULL,
	   StateID				bigint NOT NULL,
       BranchID             bigint  ,
       PathID               bigint  ,
       DataID               bigint  ,
       UserIDVal            VARCHAR(20),
       autoRunTime          TIMESTAMP,
       UserIDNum            bigint,
       insertDate           TIMESTAMP NOT NULL,
       Btid                 bigint NOT NULL,
       processDate          TIMESTAMP,
       abortDate            TIMESTAMP NOT NULL,
       versionNo            INTEGER NOT NULL,
       createBtid           bigint NOT NULL,
       PRIMARY KEY (StateUndoLogID)
);

CREATE INDEX XIF1BuriStateUndoLog ON BuriStateUndoLog(StateID);

CREATE SEQUENCE BuriStateUndoLogID
 START WITH 1
 INCREMENT BY 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
;


CREATE TABLE BuriTransaction (
       Btid                 bigint NOT NULL,
       insertDate           TIMESTAMP NOT NULL,
       versionNo            INTEGER NOT NULL,
       PRIMARY KEY (Btid)
	
);

CREATE INDEX XIF1BuriTransaction ON BuriTransaction(Btid);

CREATE SEQUENCE BuriTransactionID
 START WITH 1
 INCREMENT BY 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
;


CREATE TABLE BuriBranch (
       BranchID             bigint NOT NULL,
       parentBranchID       bigint  ,
       PathID               bigint  ,
       DataID               bigint  ,
       processDate          TIMESTAMP,
       Btid                 bigint NOT NULL,
       versionNo            INTEGER NOT NULL,
       PRIMARY KEY (BranchID)
);

CREATE INDEX XIF1BuriBranch ON BuriBranch(PathID);
CREATE INDEX XIF2BuriBranch ON BuriBranch(parentBranchID);
CREATE INDEX XIF3BuriBranch ON BuriBranch(DataID);

CREATE SEQUENCE BuriBranchID
 START WITH 1
 INCREMENT BY 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
;


CREATE TABLE BuriDataPathHistory (
       historyID            bigint NOT NULL,
       PathID               bigint  ,
       PathName             VARCHAR(200),
       DataID               bigint  ,
       action               VARCHAR(50),
       BuriUserID           bigint,
       Btid                 bigint NOT NULL,
       insertDate           TIMESTAMP NOT NULL,
       PRIMARY KEY (historyID)
);

CREATE INDEX XIF3BuriDataPathHi ON BuriDataPathHistory(PathID);
CREATE INDEX XIF4BuriDataPathHi ON BuriDataPathHistory(DataID);


CREATE SEQUENCE BuriDataPathHistoryID
 START WITH 1
 INCREMENT BY 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
;


CREATE TABLE BuriData (
       DataID               bigint NOT NULL,
       pkeyVal              VARCHAR(250),
       pkeyNum              bigint,
       dataType             VARCHAR(200) NOT NULL,
       tableName			VARCHAR(200) ,
       InsertUserID         bigint  ,
       PRIMARY KEY (DataID)
);

CREATE INDEX XIF1BuriData ON BuriData(InsertUserID);
CREATE INDEX XIE1BuriData ON BuriData(pkeyVal,dataType);
CREATE INDEX XIE2BuriData ON BuriData(pkeyNum,dataType);

CREATE SEQUENCE BuriDataID
 START WITH 1
 INCREMENT BY 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
;


CREATE TABLE BuriPath (
       PathID               bigint NOT NULL,
       PathName             VARCHAR(100) NOT NULL,
       RealPathName         VARCHAR(100) NOT NULL,
       pathType             INTEGER,
       PRIMARY KEY (PathID)
);

CREATE INDEX XIE0BuriPath ON BuriPath(PathID);
CREATE INDEX XIE1BuriPath ON BuriPath(PathName);
CREATE INDEX XIE2BuriPath ON BuriPath(RealPathName);
CREATE INDEX XIE3BuriPath ON BuriPath(pathType);

CREATE SEQUENCE BuriPathID
 START WITH 1
 INCREMENT BY 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
;



CREATE TABLE BuriUser (
       BuriUserID           bigint NOT NULL,
       UserIDVal            VARCHAR(100),
       UserIDNum            bigint,
       PRIMARY KEY (BuriUserID)
);

CREATE INDEX XIE2BuriUser ON BuriUser(UserIDVal,UserIDNum);

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
	,BuriState.ParticipantName AS ParticipantName
	,BuriState.ParticipantType AS ParticipantType
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
	,BuriPathData.ParticipantName AS ParticipantName
	,BuriPathData.ParticipantType AS ParticipantType
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


create view BuriPathHistoryData as 
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
    ,BuriState.insertDate as insertDate
    ,BuriState.processDate as processDate
    ,BuriState.abortDate as abortDate
	,BuriState.ParticipantName AS ParticipantName
	,BuriState.ParticipantType AS ParticipantType
from
	BuriPath
	,BuriState
	,BuriData
where
	BuriPath.pathID = BuriState.pathID
	and BuriData.dataID = BuriState.dataID
;


create view BuriPathHistoryDataUser as 
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
    ,BuriPathData.insertDate as insertDate
    ,BuriPathData.processDate as processDate
    ,BuriPathData.abortDate as abortDate
	,BuriPathData.ParticipantName AS ParticipantName
	,BuriPathData.ParticipantType AS ParticipantType
	,BuriUser.BuriUserID as BuriUserID
	,BuriUser.UserIDVal as UserIDVal
	,BuriUser.UserIDNum as UserIDNum
from
	BuriPathHistoryData as BuriPathData
	,BuriStateUser
	,BuriUser
where
	BuriPathData.StateID = BuriStateUser.StateID
	and BuriUser.BuriUserID = BuriStateUser.BuriUserID
	and BuriStateUser.deleteDate > CURRENT_TIMESTAMP
;

