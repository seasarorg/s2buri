create table BILL (
    BILL_ID bigint not null,
    BILL_DATE timestamp,
    SHIPPING_ID bigint,
    ORDER_TITLE_ID bigint,
    CUSTOMER_ID bigint,
    constraint BILL_PK primary key(BILL_ID)
);
