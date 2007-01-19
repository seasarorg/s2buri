CREATE TABLE FurnitureItem (
       FurnitureID			INTEGER NOT NULL,
       Type					VARCHAR(100) NOT NULL,
       Name					VARCHAR(100) NOT NULL,
       Acquisition			DATE NOT NULL,
       acquisitionType		INTEGER NOT NULL,
       versionNo            INTEGER NOT NULL,
       PRIMARY KEY (FurnitureID)
);

CREATE SEQUENCE FurnitureItemID
 START WITH 1
 INCREMENT BY 1
;


CREATE TABLE BuriTestINT (
       TestID               INTEGER NOT NULL,
       Value				VARCHAR(100) NOT NULL,
       versionNo            INTEGER NOT NULL,
       PRIMARY KEY (TestID)
);


CREATE SEQUENCE BuriTestINTID
 START WITH 1
 INCREMENT BY 1
;


CREATE TABLE BuriTestCHAR (
       TestID              VARCHAR(100) NOT NULL,
       Value				VARCHAR(100) NOT NULL,
       versionNo            INTEGER NOT NULL,
       PRIMARY KEY (TestID)
);

CREATE TABLE BuriTestMany (
       TestID01              INTEGER NOT NULL,
       TestID02              INTEGER NOT NULL,
       Value				VARCHAR(100) NOT NULL,
       versionNo            INTEGER NOT NULL
);

ALTER TABLE BuriTestMany ADD PRIMARY KEY (
	TestID01,
	TestID02
);

CREATE TABLE BuriTestUser (
       UserID              INTEGER NOT NULL ,
       UserName VARCHAR(100) NOT NULL,
	   RoleName	VARCHAR(100) NOT NULL,
	   ParentUserID		INTEGER,
       PRIMARY KEY (UserID)
);

CREATE SEQUENCE BuriTestUserID
 START WITH 1
 INCREMENT BY 1
;


	