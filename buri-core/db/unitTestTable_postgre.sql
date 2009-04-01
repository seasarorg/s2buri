CREATE TABLE FurnitureItem (
       FurnitureID			BIGINT NOT NULL,
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
 MINVALUE 1
 MAXVALUE 9223372036854775807
;


CREATE TABLE BuriTestINT (
       TestID               BIGINT NOT NULL,
       Value				VARCHAR(100) NOT NULL,
       versionNo            INTEGER NOT NULL,
       PRIMARY KEY (TestID)
);


CREATE SEQUENCE BuriTestINTID
 START WITH 1
 INCREMENT BY 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
;


CREATE TABLE BuriTestCHAR (
       TestID              VARCHAR(100) NOT NULL,
       Value				VARCHAR(100) NOT NULL,
       versionNo            INTEGER NOT NULL,
       PRIMARY KEY (TestID)
);

CREATE TABLE BuriTestMany (
       TestID01              BIGINT NOT NULL,
       TestID02              BIGINT NOT NULL,
       Value				VARCHAR(100) NOT NULL,
       versionNo            INTEGER NOT NULL
);

ALTER TABLE BuriTestMany ADD PRIMARY KEY (
	TestID01,
	TestID02
);

CREATE TABLE BuriTestUser (
       UserID              BIGINT NOT NULL ,
       UserName VARCHAR(100) NOT NULL,
	   RoleName	VARCHAR(100) NOT NULL,
	   ParentUserID		INTEGER,
       PRIMARY KEY (UserID)
);

CREATE SEQUENCE BuriTestUserID
 START WITH 1
 INCREMENT BY 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
;


	