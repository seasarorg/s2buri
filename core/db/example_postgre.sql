/*-----------------------------------------------------------------------------*/
DROP TABLE Bill;
CREATE TABLE Bill (
       BillID               INTEGER NOT NULL,
       BillDate             TIMESTAMP(4) NOT NULL,
       ShippingID           INTEGER NOT NULL ,
       OrderTitleID         INTEGER NOT NULL ,
       CustomerID           INTEGER NOT NULL 
);

DROP INDEX XIF4Bill;
DROP INDEX XIF5Bill;
DROP INDEX XIF6Bill;

CREATE INDEX XIF4Bill ON Bill(ShippingID);
CREATE INDEX XIF5Bill ON Bill(OrderTItleID);
CREATE INDEX XIF6Bill ON Bill(CustomerID);

ALTER TABLE Bill
 ADD PRIMARY KEY (BillID);

DROP SEQUENCE billID;
CREATE SEQUENCE billID
 START WITH 1
 INCREMENT BY 1
;

/*-----------------------------------------------------------------------------*/
DROP TABLE ShippingItem;
CREATE TABLE ShippingItem (
       OrderDetailID        INTEGER NOT NULL ,
       ShippingID           INTEGER NOT NULL ,
       ShippingItemID       INTEGER NOT NULL
);

DROP INDEX XIF1ShippingItem;
DROP INDEX XIF2ShippingItem;

CREATE INDEX XIF1ShippingItem ON ShippingItem(OrderDetailID);
CREATE INDEX XIF2ShippingItem ON ShippingItem(ShippingID);

ALTER TABLE ShippingItem
 ADD PRIMARY KEY (ShippingItemID);

DROP SEQUENCE shippingItemID;
CREATE SEQUENCE shippingItemID
 START WITH 1
 INCREMENT BY 1
;

/*-----------------------------------------------------------------------------*/
DROP TABLE Shipping;
CREATE TABLE Shipping (
       ShippingID           INTEGER NOT NULL,
       ShippingDate         TIMESTAMP(4) NOT NULL,
       OrderTitleID         INTEGER NOT NULL ,
       CustomerID           INTEGER NOT NULL 
);

DROP INDEX XIF3Shipping;
DROP INDEX XIF4Shipping;

CREATE INDEX XIF3Shipping ON Shipping(OrderTitleID);
CREATE INDEX XIF4Shipping ON Shipping(CustomerID);

ALTER TABLE Shipping
 ADD PRIMARY KEY (ShippingID);

DROP SEQUENCE shippingID;
CREATE SEQUENCE shippingID
 START WITH 1
 INCREMENT BY 1
;

/*-----------------------------------------------------------------------------*/
DROP TABLE OrderDetail;
CREATE TABLE OrderDetail (
       OrderDetailID        INTEGER NOT NULL,
       OrderCount           INTEGER NOT NULL,
       ItemID               INTEGER NOT NULL ,
       OrderTitleID         INTEGER NOT NULL 
);

DROP INDEX XIF3OrderDetail;
DROP INDEX XIF4OrderDetail;

CREATE INDEX XIF3OrderDetail ON OrderDetail(ItemID);
CREATE INDEX XIF4OrderDetail ON OrderDetail(OrderTitleID);

ALTER TABLE OrderDetail
 ADD PRIMARY KEY (OrderDetailID);

DROP SEQUENCE orderDetailID;
CREATE SEQUENCE orderDetailID
 START WITH 1
 INCREMENT BY 1
;

/*-----------------------------------------------------------------------------*/
DROP TABLE OrderTitle;
CREATE TABLE OrderTitle (
       OrderTitleID         INTEGER NOT NULL,
       OrderDate            TIMESTAMP(4) NOT NULL,
       CustomerID           INTEGER NOT NULL ,
       status               INTEGER
);

DROP INDEX XIF2OrderTitle;

CREATE INDEX XIF2OrderTitle ON OrderTitle(CustomerID);

ALTER TABLE OrderTitle
 ADD PRIMARY KEY (OrderTitleID);

DROP SEQUENCE orderTitleID;
CREATE SEQUENCE orderTitleID
 START WITH 1
 INCREMENT BY 1
;

/*-----------------------------------------------------------------------------*/
DROP TABLE Item;
CREATE TABLE Item (
       ItemID               INTEGER NOT NULL,
       ItemName             VARCHAR(100) NOT NULL,
       price                INTEGER NOT NULL
);

ALTER TABLE Item
 ADD PRIMARY KEY (ItemID);

DROP SEQUENCE itemID;
CREATE SEQUENCE itemID
 START WITH 1
 INCREMENT BY 1
;

/*-----------------------------------------------------------------------------*/
DROP TABLE Customer;
CREATE TABLE Customer (
       CustomerID           INTEGER NOT NULL,
       CustomerName         VARCHAR(100) NOT NULL
);

DROP SEQUENCE customerID;
CREATE SEQUENCE customerID
 START WITH 1
 INCREMENT BY 1
;

/*-----------------------------------------------------------------------------*/
ALTER TABLE Customer
 ADD PRIMARY KEY (CustomerID);

ALTER TABLE Bill
 ADD FOREIGN KEY (CustomerID)
  REFERENCES Customer ON DELETE SET NULL;

ALTER TABLE Bill
 ADD FOREIGN KEY (OrderTitleID)
  REFERENCES OrderTitle ON DELETE SET NULL;

ALTER TABLE Bill
 ADD FOREIGN KEY (ShippingID)
  REFERENCES Shipping ON DELETE SET NULL;


ALTER TABLE ShippingItem
 ADD FOREIGN KEY (ShippingID)
  REFERENCES Shipping ON DELETE SET NULL;

ALTER TABLE ShippingItem
 ADD FOREIGN KEY (OrderDetailID)
  REFERENCES OrderDetail ON DELETE SET NULL;


ALTER TABLE Shipping
 ADD FOREIGN KEY (CustomerID)
  REFERENCES Customer ON DELETE SET NULL;


ALTER TABLE Shipping
 ADD FOREIGN KEY (OrderTitleID)
  REFERENCES OrderTitle ON DELETE SET NULL;


ALTER TABLE OrderDetail
 ADD FOREIGN KEY (OrderTitleID)
  REFERENCES OrderTitle ON DELETE SET NULL;


ALTER TABLE OrderDetail
 ADD FOREIGN KEY (ItemID)
  REFERENCES Item ON DELETE SET NULL;


ALTER TABLE OrderTitle
 ADD FOREIGN KEY (CustomerID)
  REFERENCES Customer ON DELETE SET NULL;
