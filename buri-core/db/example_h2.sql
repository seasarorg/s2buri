CREATE TABLE BILL (
       BILL_ID               BIGINT NOT NULL,
       BILL_DATE             TIMESTAMP NOT NULL,
       SHIPPING_ID           BIGINT NOT NULL ,
       OrderTitleID         BIGINT NOT NULL ,
       CustomerID           BIGINT NOT NULL 
);

CREATE SEQUENCE billID
START WITH 1
INCREMENT BY 1
;

CREATE INDEX XIF4Bill ON Bill
(
       ShippingID                     ASC
);

CREATE INDEX XIF5Bill ON Bill
(
       OrderTitleID                   ASC
);

CREATE INDEX XIF6Bill ON Bill
(
       CustomerID                     ASC
);

ALTER TABLE Bill
       ADD PRIMARY KEY (BillID);



CREATE TABLE ShippingItem (
       OrderDetailID        BIGINT NOT NULL ,
       ShippingID           BIGINT NOT NULL ,
       ShippingItemID       BIGINT NOT NULL
);

CREATE SEQUENCE shippingItemID
START WITH 1
INCREMENT BY 1
;

CREATE INDEX XIF1ShippingItem ON ShippingItem
(
       OrderDetailID                  ASC
);

CREATE INDEX XIF2ShippingItem ON ShippingItem
(
       ShippingID                     ASC
);


ALTER TABLE ShippingItem
       ADD PRIMARY KEY (ShippingItemID);


CREATE TABLE Shipping (
       ShippingID           BIGINT NOT NULL,
       ShippingDate         TIMESTAMP NOT NULL,
       OrderTitleID         BIGINT NOT NULL ,
       CustomerID           BIGINT NOT NULL 
);

CREATE SEQUENCE shippingID
START WITH 1
INCREMENT BY 1
;

CREATE INDEX XIF3Shipping ON Shipping
(
       OrderTitleID                   ASC
);

CREATE INDEX XIF4Shipping ON Shipping
(
       CustomerID                     ASC
);

ALTER TABLE Shipping
       ADD PRIMARY KEY (ShippingID);


CREATE TABLE OrderDetail (
       OrderDetailID        BIGINT NOT NULL,
       OrderCount           INTEGER NOT NULL,
       ItemID               BIGINT NOT NULL ,
       OrderTitleID         BIGINT NOT NULL 
);

CREATE SEQUENCE orderDetailID
START WITH 1
INCREMENT BY 1
;

CREATE INDEX XIF3OrderDetail ON OrderDetail
(
       ItemID                         ASC
);

CREATE INDEX XIF4OrderDetail ON OrderDetail
(
       OrderTitleID                   ASC
);

ALTER TABLE OrderDetail
       ADD PRIMARY KEY (OrderDetailID);


CREATE TABLE OrderTitle (
       OrderTitleID         BIGINT NOT NULL,
       OrderDate            TIMESTAMP NOT NULL,
       CustomerID           BIGINT NOT NULL ,
       status               INTEGER
);

CREATE SEQUENCE orderTitleID
START WITH 1
INCREMENT BY 1
;

CREATE INDEX XIF2OrderTitle ON OrderTitle
(
       CustomerID                     ASC
);

ALTER TABLE OrderTitle
       ADD PRIMARY KEY (OrderTitleID);


CREATE TABLE Item (
       ItemID               BIGINT NOT NULL,
       ItemName             VARCHAR(100) NOT NULL,
       price                BIGINT NOT NULL
);

CREATE SEQUENCE itemID
START WITH 1
INCREMENT BY 1
;

ALTER TABLE Item
       ADD PRIMARY KEY (ItemID);


CREATE TABLE Customer (
       CustomerID           BIGINT NOT NULL,
       CustomerName         VARCHAR(100) NOT NULL
);

CREATE SEQUENCE customerID
START WITH 1
INCREMENT BY 1
;

ALTER TABLE Customer
       ADD PRIMARY KEY (CustomerID);

ALTER TABLE Bill
       ADD FOREIGN KEY (CustomerID)
                             REFERENCES Customer
                             ON DELETE SET NULL;

ALTER TABLE Bill
       ADD FOREIGN KEY (OrderTitleID)
                             REFERENCES OrderTitle
                             ON DELETE SET NULL;

ALTER TABLE Bill
       ADD FOREIGN KEY (ShippingID)
                             REFERENCES Shipping
                             ON DELETE SET NULL;

ALTER TABLE ShippingItem
       ADD FOREIGN KEY (ShippingID)
                             REFERENCES Shipping
                             ON DELETE SET NULL;

ALTER TABLE ShippingItem
       ADD FOREIGN KEY (OrderDetailID)
                             REFERENCES OrderDetail
                             ON DELETE SET NULL;

ALTER TABLE Shipping
       ADD FOREIGN KEY (CustomerID)
                             REFERENCES Customer
                             ON DELETE SET NULL;

ALTER TABLE Shipping
       ADD FOREIGN KEY (OrderTitleID)
                             REFERENCES OrderTitle
                             ON DELETE SET NULL;

ALTER TABLE OrderDetail
       ADD FOREIGN KEY (OrderTitleID)
                             REFERENCES OrderTitle
                             ON DELETE SET NULL;

ALTER TABLE OrderDetail
       ADD FOREIGN KEY (ItemID)
                             REFERENCES Item
                             ON DELETE SET NULL;

ALTER TABLE OrderTitle
       ADD FOREIGN KEY (CustomerID)
                             REFERENCES Customer
                             ON DELETE SET NULL;