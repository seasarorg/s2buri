create table SHIPPING (
    SHIPPING_ID bigint not null,
    SHIPPING_DATE timestamp,
    ORDER_TITLE_ID bigint,
    CUSTOMER_ID bigint,
    constraint SHIPPING_PK primary key(SHIPPING_ID)
);
