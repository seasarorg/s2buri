CREATE TABLE BuriStateUser (
       StateUserID          INTEGER NOT NULL,
       StateID              INTEGER  ,
       BuriUserID           INTEGER  ,
       insertDate           TIMESTAMP NOT NULL,
       deleteDate           TIMESTAMP NOT NULL,
       PRIMARY KEY (StateUserID)
);

CREATE SEQUENCE BuriStateUserID
START WITH 1
INCREMENT BY 1
;

CREATE INDEX XIF1BuriStateUser ON BuriStateUser
(
       StateID                        ASC
);

CREATE INDEX XIF2BuriStateUser ON BuriStateUser
(
       BuriUserID                     ASC
);


CREATE TABLE BuriState (
       StateID              INTEGER NOT NULL,
       PathID               INTEGER  ,
       DataID               INTEGER  ,
       BranchID             INTEGER  ,
       UserIDVal            VARCHAR(20),
       UserIDNum            INTEGER,
       ParticipantName      VARCHAR(200),
       ParticipantType      VARCHAR(200),
       Btid                 INTEGER NOT NULL,
       insertDate           TIMESTAMP NOT NULL,
       autoRunTime          TIMESTAMP NOT NULL,
       processDate          TIMESTAMP NOT NULL,
       abortDate            TIMESTAMP NOT NULL,
       versionNo            INTEGER NOT NULL,
       PRIMARY KEY (StateID)
);

CREATE SEQUENCE BuriStateID
START WITH 1
INCREMENT BY 1
;

CREATE INDEX XIF1BuriState ON BuriState
(
       PathID                         ASC
);

CREATE INDEX XIF2BuriState ON BuriState
(
       DataID                         ASC
);

CREATE INDEX XIF4BuriState ON BuriState
(
       BranchID                       ASC
);

CREATE INDEX XIF5BuriState ON BuriState
(
       processDate                       ASC
);
CREATE INDEX XIF6BuriState ON BuriState
(
       autoRunTime                       ASC
);

CREATE TABLE BuriStateUndoLog (
       StateUndoLogID              INTEGER NOT NULL,
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

CREATE SEQUENCE BuriStateUndoLogID
START WITH 1
INCREMENT BY 1
;

CREATE INDEX XIF1BuriStateUndoLog ON BuriStateUndoLog
(
       StateID                         ASC
);


CREATE TABLE BuriTransaction (
       Btid              INTEGER NOT NULL,
       insertDate           TIMESTAMP NOT NULL,
       versionNo            INTEGER NOT NULL,
       PRIMARY KEY (Btid)
	
);

CREATE SEQUENCE BuriTransactionID
START WITH 1
INCREMENT BY 1
;

CREATE INDEX XIF1BuriTransaction ON BuriTransaction
(
       Btid                         ASC
);

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

CREATE SEQUENCE BuriBranchID
START WITH 1
INCREMENT BY 1
;

CREATE INDEX XIF1BuriBranch ON BuriBranch
(
       PathID                         ASC
);

CREATE INDEX XIF2BuriBranch ON BuriBranch
(
       parentBranchID                 ASC
);

CREATE INDEX XIF3BuriBranch ON BuriBranch
(
       DataID                         ASC
);


CREATE TABLE BuriDataPathHistory (
       historyID            INTEGER NOT NULL,
       PathID               INTEGER  ,
       PathName             VARCHAR(200),
       DataID               INTEGER  ,
       action              VARCHAR(50),
       BuriUserID           INTEGER,
       Btid                 INTEGER NOT NULL,
       insertDate           TIMESTAMP NOT NULL,
       PRIMARY KEY (historyID)
);

CREATE SEQUENCE BuriDataPathHistoryID
START WITH 1
INCREMENT BY 1
;

CREATE INDEX XIF3BuriDataPathHi ON BuriDataPathHistory
(
       PathID                         ASC
);

CREATE INDEX XIF4BuriDataPathHi ON BuriDataPathHistory
(
       DataID                         ASC
);


CREATE TABLE BuriData (
       DataID               INTEGER NOT NULL,
       pkeyVal              VARCHAR(250),
       pkeyNum              INTEGER,
       dataType             VARCHAR(200) NOT NULL,
       tableName			VARCHAR(200) ,
       InsertUserID         INTEGER  ,
       PRIMARY KEY (DataID)
);

CREATE SEQUENCE BuriDataID
START WITH 1
INCREMENT BY 1
;

CREATE INDEX XIF1BuriData ON BuriData
(
       InsertUserID                   ASC
);

CREATE INDEX XIE1BuriData ON BuriData
(
       pkeyVal                        ASC,
       dataType                       ASC
);

CREATE INDEX XIE2BuriData ON BuriData
(
       pkeyNum                        ASC,
       dataType                       ASC
);


CREATE TABLE BuriPath (
       PathID               INTEGER NOT NULL,
       PathName             VARCHAR(100) NOT NULL,
       RealPathName         VARCHAR(100) NOT NULL,
       pathType             INTEGER,
       PRIMARY KEY (PathID)
);

CREATE SEQUENCE BuriPathID
START WITH 1
INCREMENT BY 1
;

CREATE INDEX XIE0BuriPath ON BuriPath
(
       PathID                       ASC
);

CREATE INDEX XIE1BuriPath ON BuriPath
(
       PathName                       ASC
);

CREATE INDEX XIE2BuriPath ON BuriPath
(
       RealPathName                       ASC
);

CREATE INDEX XIE3BuriPath ON BuriPath
(
       pathType                       ASC
);


CREATE TABLE BuriUser (
       BuriUserID           INTEGER NOT NULL,
       UserIDVal            VARCHAR(100),
       UserIDNum            INTEGER,
       PRIMARY KEY (BuriUserID)
);

CREATE SEQUENCE BuriUserID
START WITH 1
INCREMENT BY 1
;

CREATE INDEX XIE2BuriUser ON BuriUser
(
       UserIDVal                        ASC,
       UserIDNum                       ASC
);


CREATE VIEW BuriPathData AS
SELECT
	BuriPath.pathID AS pathID
	,BuriPath.PathName AS PathName
	,BuriPath.RealPathName AS RealPathName
	,BuriPath.pathType AS pathType
	,BuriData.pkeyNum AS pkeyNum
	,BuriData.pkeyVal AS pkeyVal
	,BuriData.dataType AS dataType
	,BuriData.tableName AS tableName
	,BuriData.dataID AS dataID
	,BuriState.StateID AS StateID
	,BuriState.autoRunTime AS autoRunTime
	,BuriState.ParticipantName AS ParticipantName
	,BuriState.ParticipantType AS ParticipantType
FROM
	BuriPath
	,BuriState
	,BuriData
WHERE
	BuriPath.pathID = BuriState.pathID
	and BuriData.dataID = BuriState.dataID
	and BuriState.processDate > CURRENT_TIMESTAMP
;


CREATE VIEW BuriPathDataUser AS
SELECT
	BuriPathData.pathID AS pathID
	,BuriPathData.PathName AS PathName
	,BuriPathData.RealPathName AS RealPathName
	,BuriPathData.pathType AS pathType
	,BuriPathData.pkeyNum AS pkeyNum
	,BuriPathData.pkeyVal AS pkeyVal
	,BuriPathData.dataType AS dataType
	,BuriPathData.tableName AS tableName
	,BuriPathData.dataID AS dataID
	,BuriPathData.StateID AS StateID
	,BuriPathData.autoRunTime AS autoRunTime
	,BuriPathData.ParticipantName AS ParticipantName
	,BuriPathData.ParticipantType AS ParticipantType
	,BuriUser.BuriUserID AS BuriUserID
	,BuriUser.UserIDVal AS UserIDVal
	,BuriUser.UserIDNum AS UserIDNum
FROM
	BuriPathData
	,BuriStateUser
	,BuriUser
WHERE
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
