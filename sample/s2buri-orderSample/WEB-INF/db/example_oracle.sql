/*-----------------------------------------------------------------------------*/
DROP TABLE Bill;
CREATE TABLE Bill (
       BillID               NUMBER(38,0) NOT NULL,
       BillDate             TIMESTAMP NOT NULL,
       ShippingID           NUMBER(38,0) NOT NULL ,
       OrderTitleID         NUMBER(38,0) NOT NULL ,
       CustomerID           NUMBER(38,0) NOT NULL 
);

CREATE INDEX XIF4Bill ON Bill(ShippingID ASC);
CREATE INDEX XIF5Bill ON Bill(OrderTItleID ASC);
CREATE INDEX XIF6Bill ON Bill(CustomerID ASC);

ALTER TABLE Bill
 ADD PRIMARY KEY (BillID);

DROP SEQUENCE billID;
CREATE SEQUENCE billID
 START WITH 1
 INCREMENT BY 1
 NOMAXVALUE
 NOMINVALUE
 NOCYCLE
;

/*-----------------------------------------------------------------------------*/
DROP TABLE ShippingItem;
CREATE TABLE ShippingItem (
       OrderDetailID        NUMBER(38,0) NOT NULL ,
       ShippingID           NUMBER(38,0) NOT NULL ,
       ShippingItemID       NUMBER(38,0) NOT NULL
);

CREATE INDEX XIF1ShippingItem ON ShippingItem(OrderDetailID ASC);
CREATE INDEX XIF2ShippingItem ON ShippingItem(ShippingID ASC);

ALTER TABLE ShippingItem
 ADD PRIMARY KEY (ShippingItemID);

DROP SEQUENCE shippingItemID;
CREATE SEQUENCE shippingItemID
 START WITH 1
 INCREMENT BY 1
 NOMAXVALUE
 NOMINVALUE
 NOCYCLE
;

/*-----------------------------------------------------------------------------*/
DROP TABLE Shipping;
CREATE TABLE Shipping (
       ShippingID           NUMBER(38,0) NOT NULL,
       ShippingDate         TIMESTAMP NOT NULL,
       OrderTitleID         NUMBER(38,0) NOT NULL ,
       CustomerID           NUMBER(38,0) NOT NULL 
);

CREATE INDEX XIF3Shipping ON Shipping(OrderTitleID ASC);
CREATE INDEX XIF4Shipping ON Shipping(CustomerID ASC);

ALTER TABLE Shipping
 ADD PRIMARY KEY (ShippingID);

DROP SEQUENCE shippingID;
CREATE SEQUENCE shippingID
 START WITH 1
 INCREMENT BY 1
 NOMAXVALUE
 NOMINVALUE
 NOCYCLE
;

/*-----------------------------------------------------------------------------*/
DROP TABLE OrderDetail;
CREATE TABLE OrderDetail (
       OrderDetailID        NUMBER(38,0) NOT NULL,
       OrderCount           INTEGER NOT NULL,
       ItemID               NUMBER(38,0) NOT NULL ,
       OrderTitleID         NUMBER(38,0) NOT NULL 
);

CREATE INDEX XIF3OrderDetail ON OrderDetail(ItemID ASC);
CREATE INDEX XIF4OrderDetail ON OrderDetail(OrderTitleID ASC);

ALTER TABLE OrderDetail
 ADD PRIMARY KEY (OrderDetailID);

DROP SEQUENCE orderDetailID;
CREATE SEQUENCE orderDetailID
 START WITH 1
 INCREMENT BY 1
 NOMAXVALUE
 NOMINVALUE
 NOCYCLE
;

/*-----------------------------------------------------------------------------*/
DROP TABLE OrderTitle;
CREATE TABLE OrderTitle (
       OrderTitleID         NUMBER(38,0) NOT NULL,
       OrderDate            TIMESTAMP NOT NULL,
       CustomerID           NUMBER(38,0) NOT NULL ,
       status               INTEGER
);

CREATE INDEX XIF2OrderTitle ON OrderTitle(CustomerID ASC);

ALTER TABLE OrderTitle
 ADD PRIMARY KEY (OrderTitleID);

DROP SEQUENCE orderTitleID;
CREATE SEQUENCE orderTitleID
 START WITH 1
 INCREMENT BY 1
 NOMAXVALUE
 NOMINVALUE
 NOCYCLE
;

/*-----------------------------------------------------------------------------*/
DROP TABLE Item;
CREATE TABLE Item (
       ItemID               NUMBER(38,0) NOT NULL,
       ItemName             VARCHAR(100) NOT NULL,
       price                NUMBER(38,0) NOT NULL
);

ALTER TABLE Item
 ADD PRIMARY KEY (ItemID);

DROP SEQUENCE itemID;
CREATE SEQUENCE itemID
 START WITH 1
 INCREMENT BY 1
 NOMAXVALUE
 NOMINVALUE
 NOCYCLE
;

/*-----------------------------------------------------------------------------*/
DROP TABLE Customer;
CREATE TABLE Customer (
       CustomerID           NUMBER(38,0) NOT NULL,
       CustomerName         VARCHAR(100) NOT NULL
);

DROP SEQUENCE customerID;
CREATE SEQUENCE customerID
 START WITH 1
 INCREMENT BY 1
 NOMAXVALUE
 NOMINVALUE
 NOCYCLE
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
