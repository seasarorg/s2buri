DROP SEQUENCE BuriTestID ;
DROP SEQUENCE FurnitureID ;

CREATE SEQUENCE BuriTestID 
NOMAXVALUE 
NOMINVALUE 
NOCACHE  
NOCYCLE
NOORDER
;
CREATE SEQUENCE FurnitureID 
NOMAXVALUE 
NOMINVALUE 
NOCACHE  
NOCYCLE
NOORDER
;

DROP TABLE FurnitureItem CASCADE CONSTRAINTS;

CREATE TABLE FurnitureItem (
       FurnitureID			INTEGER NOT NULL,
       Type					VARCHAR(100) NOT NULL,
       Name					VARCHAR(100) NOT NULL,
       Acquisition			DATE NOT NULL,
       acquisitionType		INTEGER NOT NULL,
       versionNo            INTEGER NOT NULL
);

ALTER TABLE FurnitureItem ADD PRIMARY KEY 
(
	FurnitureID
);

CREATE UNIQUE INDEX XPKFurnitureItem ON FurnitureItem
(
       FurnitureID                   ASC
);

DROP TABLE BuriTestINT CASCADE CONSTRAINTS;

CREATE TABLE BuriTestINT (
       TestID              INTEGER NOT NULL,
       Value				VARCHAR(100) NOT NULL,
       versionNo            INTEGER NOT NULL
);

ALTER TABLE BuriTestINT ADD PRIMARY KEY 
(
	TestID
);

CREATE UNIQUE INDEX XPKBuriTestINT ON BuriTestINT
(
       TestID                        ASC
);

DROP TABLE BuriTestCHAR CASCADE CONSTRAINTS;

CREATE TABLE BuriTestCHAR (
       TestID              VARCHAR(100) NOT NULL,
       Value				VARCHAR(100) NOT NULL,
       versionNo            INTEGER NOT NULL
);

ALTER TABLE BuriTestCHAR ADD PRIMARY KEY 
(
	TestID
);

CREATE UNIQUE INDEX XPKBuriTestCHAR ON BuriTestCHAR
(
       TestID                        ASC
);

DROP TABLE BuriTestMany CASCADE CONSTRAINTS;

CREATE TABLE BuriTestMany (
       TestID01              INTEGER NOT NULL,
       TestID02              INTEGER NOT NULL,
       Value				VARCHAR(100) NOT NULL,
       versionNo            INTEGER NOT NULL
);

ALTER TABLE BuriTestMany ADD PRIMARY KEY 
(
	TestID01,
	TestID02
);

CREATE UNIQUE INDEX XPKBuriTestMany ON BuriTestMany
(
       TestID01                        ASC,
       TestID02                        ASC
);


