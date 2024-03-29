DROP TABLE FurnitureItem;

CREATE TABLE FurnitureItem (
       FurnitureID			INTEGER NOT NULL generated by default 
				AS IDENTITY( 
					START WITH 1,
					INCREMENT BY 1),
       Type					VARCHAR(100) NOT NULL,
       Name					VARCHAR(100) NOT NULL,
       Acquisition			DATE NOT NULL,
       acquisitionType		INTEGER NOT NULL,
       versionNo            INTEGER NOT NULL,
       PRIMARY KEY (FurnitureID)
);


DROP TABLE BuriTestINT;

CREATE TABLE BuriTestINT (
       TestID              INTEGER NOT NULL generated by default 
				AS IDENTITY( 
					START WITH 1,
					INCREMENT BY 1),
       Value				VARCHAR(100) NOT NULL,
       versionNo            INTEGER NOT NULL,
       PRIMARY KEY (TestID)
);


DROP TABLE BuriTestCHAR ;

CREATE TABLE BuriTestCHAR (
       TestID              VARCHAR(100) NOT NULL,
       Value				VARCHAR(100) NOT NULL,
       versionNo            INTEGER NOT NULL,
       PRIMARY KEY (TestID)
);

DROP TABLE BuriTestMany ;

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

