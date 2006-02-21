DROP SEQUENCE BuriBranchID
//
DROP SEQUENCE BuriDataID
//
DROP SEQUENCE BuriHistoryID
//
DROP SEQUENCE BuriPathID
//
DROP SEQUENCE BuriStatusID
//
CREATE SEQUENCE BuriBranchID 
NOMAXVALUE 
NOMINVALUE 
NOCACHE  
NOCYCLE
NOORDER
//
CREATE SEQUENCE BuriDataID 
NOMAXVALUE 
NOMINVALUE 
NOCACHE  
NOCYCLE
NOORDER
//
CREATE SEQUENCE BuriHistoryID 
NOMAXVALUE 
NOMINVALUE 
NOCACHE  
NOCYCLE
NOORDER
//
CREATE SEQUENCE BuriPathID 
NOMAXVALUE 
NOMINVALUE 
NOCACHE  
NOCYCLE
NOORDER
//
CREATE SEQUENCE BuriStatusID 
NOMAXVALUE 
NOMINVALUE 
NOCACHE  
NOCYCLE
NOORDER
//


DROP TABLE BuriState
//

CREATE TABLE BuriState (
       StateID              INTEGER NOT NULL,
       PathID               INTEGER NULL,
       DataID               INTEGER NULL,
       BranchID             INTEGER NULL,
       UserIDVal            VARCHAR(20) NULL,
       UserIDNum            INTEGER NULL,
       insertDate           TIMESTAMP NOT NULL,
       processDate          TIMESTAMP NOT NULL,
       abortDate            TIMESTAMP NOT NULL,
       versionNo            INTEGER NOT NULL,
       PRIMARY KEY (StateID)
)
//

CREATE UNIQUE INDEX XPKBuriState ON BuriState
(
       StateID                        ASC
)
//

CREATE INDEX XIF1BuriState ON BuriState
(
       PathID                         ASC
)
//

CREATE INDEX XIF2BuriState ON BuriState
(
       DataID                         ASC
)
//

CREATE INDEX XIF4BuriState ON BuriState
(
       BranchID                       ASC
)
//


DROP TABLE BuriBranch
//

CREATE TABLE BuriBranch (
       BranchID             INTEGER NOT NULL,
       parentBranchID       INTEGER NULL,
       PathID               INTEGER NULL,
       DataID               INTEGER NULL,
       processDate          TIMESTAMP NULL,
       versionNo            INTEGER NOT NULL,
       PRIMARY KEY (BranchID)
)
//

CREATE UNIQUE INDEX XPKBuriBranch ON BuriBranch
(
       BranchID                       ASC
)
//

CREATE INDEX XIF1BuriBranch ON BuriBranch
(
       PathID                         ASC
)
//

CREATE INDEX XIF2BuriBranch ON BuriBranch
(
       parentBranchID                 ASC
)
//

CREATE INDEX XIF3BuriBranch ON BuriBranch
(
       DataID                         ASC
)
//


DROP TABLE BuriDataPathHistory
//

CREATE TABLE BuriDataPathHistory (
       historyID            INTEGER NOT NULL,
       PathID               INTEGER NULL,
       DataID               INTEGER NULL,
       UserIDVal            VARCHAR(20) NULL,
       UserIDNum            INTEGER NULL,
       insertDate           TIMESTAMP NOT NULL,
       PRIMARY KEY (historyID)
)
//

CREATE UNIQUE INDEX XPKBuriDataPathHistory ON BuriDataPathHistory
(
       historyID                      ASC
)
//

CREATE INDEX XIF3BuriDataPathHistory ON BuriDataPathHistory
(
       PathID                         ASC
)
//

CREATE INDEX XIF4BuriDataPathHistory ON BuriDataPathHistory
(
       DataID                         ASC
)
//


DROP TABLE BuriPath
//

CREATE TABLE BuriPath (
       PathID               INTEGER NOT NULL,
       PathName             VARCHAR(100) NOT NULL,
       RealPathName         VARCHAR(100) NOT NULL,
       PRIMARY KEY (PathID)
)
//

CREATE UNIQUE INDEX XPKBuriPath ON BuriPath
(
       PathID                         ASC
)
//


DROP TABLE BuriData
//

CREATE TABLE BuriData (
       DataID               INTEGER NOT NULL,
       pkeyVal              VARCHAR(250) NULL,
       pkeyNum              INTEGER NULL,
       dataType             VARCHAR(200) NOT NULL,
       PRIMARY KEY (DataID)
)
//

CREATE UNIQUE INDEX XPKBuriData ON BuriData
(
       DataID                         ASC
)
//

CREATE INDEX XIE1BuriData ON BuriData
(
       pkeyVal                        ASC,
       dataType                       ASC
)
//

CREATE INDEX XIE2BuriData ON BuriData
(
       pkeyNum                        ASC,
       dataType                       ASC
)
//



