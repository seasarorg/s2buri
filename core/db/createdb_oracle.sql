/*-----------------------------------------------------------------------------*/
DROP TABLE BuriState;
CREATE TABLE BuriState (
       StateID              INTEGER NOT NULL,
       BranchID             INTEGER ,
       PathID               INTEGER ,
       DataID               INTEGER ,
       UserIDVal            VARCHAR(100),
       autoRunTime          TIMESTAMP,
       UserIDNum            INTEGER,
       insertDate           TIMESTAMP NOT NULL,
       processDate          TIMESTAMP NOT NULL,
       abortDate            TIMESTAMP NOT NULL,
       versionNo            INTEGER NOT NULL,
       PRIMARY KEY (StateID)
);

DROP INDEX XIF1BuriState;
DROP INDEX XIF3BuriState;
DROP INDEX XIF2BuriState;
DROP INDEX XIF4BuriState;

CREATE INDEX XIF1BuriState ON BuriState(PathID ASC);
CREATE INDEX XIF3BuriState ON BuriState(autoRunTime ASC);
CREATE INDEX XIF2BuriState ON BuriState(DataID ASC);
CREATE INDEX XIF4BuriState ON BuriState(BranchID ASC);

DROP SEQUENCE BuriStatusID;
CREATE SEQUENCE BuriStatusID
 START WITH 1
 INCREMENT BY 1
 NOMAXVALUE
 NOMINVALUE
 NOCYCLE
;

/*-----------------------------------------------------------------------------*/
DROP TABLE BuriBranch;
CREATE TABLE BuriBranch (
       BranchID             INTEGER NOT NULL,
       parentBranchID       INTEGER ,
       PathID               INTEGER ,
       DataID               INTEGER ,
       processDate          TIMESTAMP,
       versionNo            INTEGER NOT NULL,
       PRIMARY KEY (BranchID)
);

DROP INDEX XIF1BuriBranch;
DROP INDEX XIF2BuriBranch;
DROP INDEX XIF3BuriBranch;

CREATE INDEX XIF1BuriBranch ON BuriBranch(PathID ASC);
CREATE INDEX XIF2BuriBranch ON BuriBranch(parentBranchID ASC);
CREATE INDEX XIF3BuriBranch ON BuriBranch(DataID ASC);

DROP SEQUENCE BuriBranchID;
CREATE SEQUENCE BuriBranchID
 START WITH 1
 INCREMENT BY 1
 NOMAXVALUE
 NOMINVALUE
 NOCYCLE
;

/*-----------------------------------------------------------------------------*/
DROP TABLE BuriDataPathHistory;
CREATE TABLE BuriDataPathHistory (
       historyID            INTEGER NOT NULL,
       PathID               INTEGER ,
       DataID               INTEGER ,
       UserIDVal            VARCHAR(100),
       UserIDNum            INTEGER,
       insertDate           TIMESTAMP NOT NULL,
       PRIMARY KEY (historyID)
);

DROP INDEX XIF3BuriDataPathHi;
DROP INDEX XIF4BuriDataPathHi;

CREATE INDEX XIF3BuriDataPathHi ON BuriDataPathHistory(PathID ASC);
CREATE INDEX XIF4BuriDataPathHi ON BuriDataPathHistory(DataID ASC);

DROP SEQUENCE BurihistoryID;
CREATE SEQUENCE BurihistoryID
 START WITH 1
 INCREMENT BY 1
 NOMAXVALUE
 NOMINVALUE
 NOCYCLE
;

/*-----------------------------------------------------------------------------*/
DROP TABLE BuriData;
CREATE TABLE BuriData (
       DataID               INTEGER NOT NULL,
       pkeyVal              VARCHAR(250),
       pkeyNum              INTEGER,
       dataType             VARCHAR(200) NOT NULL,
       PRIMARY KEY (DataID)
);

DROP INDEX XIE1BuriData;
DROP INDEX XIE2BuriData;

CREATE INDEX XIE1BuriData ON BuriData(pkeyval ASC,dataType ASC);
CREATE INDEX XIE2BuriData ON BuriData(pkeyNum ASC,dataType ASC);

DROP SEQUENCE BuriDataID;
CREATE SEQUENCE BuriDataID
 START WITH 1
 INCREMENT BY 1
 NOMAXVALUE
 NOMINVALUE
 NOCYCLE
;

/*-----------------------------------------------------------------------------*/
DROP TABLE BuriPath;
CREATE TABLE BuriPath (
       PathID               INTEGER NOT NULL,
       PathName             VARCHAR(100) NOT NULL,
       RealPathName         VARCHAR(100) NOT NULL,
       PRIMARY KEY (PathID)
);

DROP SEQUENCE BuriPathID;
CREATE SEQUENCE BuriPathID
 START WITH 1
 INCREMENT BY 1
 NOMAXVALUE
 NOMINVALUE
 NOCYCLE
;


