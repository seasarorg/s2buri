DROP SEQUENCE billID;

CREATE SEQUENCE billID
 START WITH 1
 INCREMENT BY 1
;

DROP TABLE Bill;

CREATE TABLE Bill (
       BillID               BIGINT NOT NULL,
       BillDate             TIMESTAMP NOT NULL,
       ShippingID           BIGINT NOT NULL ,
       OrderTitleID         BIGINT NOT NULL ,
       CustomerID           BIGINT NOT NULL 
);

DROP INDEX XPKBill;

DROP INDEX XIF4Bill;

DROP INDEX XIF5Bill;

DROP INDEX XIF6Bill;

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

DROP SEQUENCE shippingItemID;

CREATE SEQUENCE shippingItemID
 START WITH 1
 INCREMENT BY 1
;

DROP TABLE ShippingItem;

CREATE TABLE ShippingItem (
       OrderDetailID        BIGINT NOT NULL ,
       ShippingID           BIGINT NOT NULL ,
       ShippingItemID       BIGINT NOT NULL
);

DROP INDEX XPKShippingItem;

DROP INDEX XIF1ShippingItem;

DROP INDEX XIF2ShippingItem;

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

DROP SEQUENCE shippingID;

CREATE SEQUENCE shippingID
 START WITH 1
 INCREMENT BY 1
;

DROP TABLE Shipping;

CREATE TABLE Shipping (
       ShippingID           BIGINT NOT NULL,
       ShippingDate         TIMESTAMP NOT NULL,
       OrderTitleID         BIGINT NOT NULL ,
       CustomerID           BIGINT NOT NULL 
);

DROP INDEX XPKShipping;

DROP INDEX XIF3Shipping;

DROP INDEX XIF4Shipping;

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

DROP SEQUENCE orderDetailID;

CREATE SEQUENCE orderDetailID
 START WITH 1
 INCREMENT BY 1
;

DROP TABLE OrderDetail;

CREATE TABLE OrderDetail (
       OrderDetailID        BIGINT NOT NULL,
       OrderCount           INTEGER NOT NULL,
       ItemID               BIGINT NOT NULL ,
       OrderTitleID         BIGINT NOT NULL 
);

DROP INDEX XPKOrderDetail;

DROP INDEX XIF3OrderDetail;

DROP INDEX XIF4OrderDetail;

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

DROP SEQUENCE orderTitleID;

CREATE SEQUENCE orderTitleID
 START WITH 1
 INCREMENT BY 1
;

DROP TABLE OrderTitle;

CREATE TABLE OrderTitle (
       OrderTitleID         BIGINT NOT NULL,
       OrderDate            TIMESTAMP NOT NULL,
       CustomerID           BIGINT NOT NULL ,
       status               INTEGER
);

DROP INDEX XPKOrderTitle;

DROP INDEX XIF2OrderTitle;

CREATE INDEX XIF2OrderTitle ON OrderTitle
(
       CustomerID                     ASC
);


ALTER TABLE OrderTitle
       ADD PRIMARY KEY (OrderTitleID);

DROP SEQUENCE itemID;

CREATE SEQUENCE itemID
 START WITH 1
 INCREMENT BY 1
;

DROP TABLE Item;

CREATE TABLE Item (
       ItemID               BIGINT NOT NULL,
       ItemName             VARCHAR(100) NOT NULL,
       price                BIGINT NOT NULL
);

DROP INDEX XPKItem;


ALTER TABLE Item
       ADD PRIMARY KEY (ItemID);

DROP SEQUENCE customerID;

CREATE SEQUENCE customerID
 START WITH 1
 INCREMENT BY 1
;

DROP TABLE Customer;

CREATE TABLE Customer (
       CustomerID           BIGINT NOT NULL,
       CustomerName         VARCHAR(100) NOT NULL
);

DROP INDEX XPKCustomer;


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



