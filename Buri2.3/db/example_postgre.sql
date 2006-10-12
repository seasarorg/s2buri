
DROP TABLE Bill;

CREATE TABLE Bill (
       BillID               BIGINT NOT NULL,
       BillDate             TIMESTAMP NOT NULL,
       ShippingID           BIGINT NOT NULL ,
       OrderTitleID         BIGINT NOT NULL ,
       CustomerID           BIGINT NOT NULL ,
       PRIMARY KEY (BillID)
);

CREATE INDEX XIF4Bill ON Bill(ShippingID);
CREATE INDEX XIF5Bill ON Bill(OrderTitleID);
CREATE INDEX XIF6Bill ON Bill(CustomerID);

DROP SEQUENCE BillID;
CREATE SEQUENCE BillID
 START WITH 1
 INCREMENT BY 1
;


DROP TABLE ShippingItem;

CREATE TABLE ShippingItem (
       OrderDetailID        BIGINT NOT NULL ,
       ShippingID           BIGINT NOT NULL ,
       ShippingItemID       BIGINT NOT NULL ,
       PRIMARY KEY (ShippingItemID)
);

CREATE INDEX XIF1ShippingItem ON ShippingItem(OrderDetailID);
CREATE INDEX XIF2ShippingItem ON ShippingItem(ShippingID);


DROP SEQUENCE ShippingItemID;
CREATE SEQUENCE ShippingItemID
 START WITH 1
 INCREMENT BY 1
;


DROP TABLE Shipping;

CREATE TABLE Shipping (
       ShippingID           BIGINT NOT NULL,
       ShippingDate         TIMESTAMP NOT NULL,
       OrderTitleID         BIGINT NOT NULL ,
       CustomerID           BIGINT NOT NULL ,
       PRIMARY KEY (ShippingID)
);

CREATE INDEX XIF3Shipping ON Shipping(OrderTitleID);
CREATE INDEX XIF4Shipping ON Shipping(CustomerID);

DROP SEQUENCE ShippingID;
CREATE SEQUENCE ShippingID
 START WITH 1
 INCREMENT BY 1
;


DROP TABLE OrderDetail;

CREATE TABLE OrderDetail (
       OrderDetailID        BIGINT NOT NULL,
       OrderCount           INTEGER NOT NULL,
       ItemID               BIGINT NOT NULL ,
       OrderTitleID         BIGINT NOT NULL ,
       PRIMARY KEY (OrderDetailID)
);

CREATE INDEX XIF3OrderDetail ON OrderDetail(ItemID);
CREATE INDEX XIF4OrderDetail ON OrderDetail(OrderTitleID);

DROP SEQUENCE OrderDetailID;
CREATE SEQUENCE OrderDetailID
 START WITH 1
 INCREMENT BY 1
;


DROP TABLE OrderTitle;

CREATE TABLE OrderTitle (
       OrderTitleID         BIGINT NOT NULL,
       OrderDate            TIMESTAMP NOT NULL,
       CustomerID           BIGINT NOT NULL ,
       status               INTEGER ,
       PRIMARY KEY (OrderTitleID)
);

CREATE INDEX XIF2OrderTitle ON OrderTitle(CustomerID);

DROP SEQUENCE OrderTitleID;
CREATE SEQUENCE OrderTitleID
 START WITH 1
 INCREMENT BY 1
;


DROP TABLE Item;

CREATE TABLE Item (
       ItemID               BIGINT NOT NULL,
       ItemName             VARCHAR(100) NOT NULL,
       price                BIGINT NOT NULL,
       PRIMARY KEY (ItemID)
);

DROP SEQUENCE ItemID;
CREATE SEQUENCE ItemID
 START WITH 1
 INCREMENT BY 1
;


DROP TABLE Customer;

CREATE TABLE Customer (
       CustomerID           BIGINT NOT NULL,
       CustomerName         VARCHAR(100) NOT NULL
);

DROP SEQUENCE CustomerID;
CREATE SEQUENCE CustomerID
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



