create table PRODUCT_ORDER (
    PRODUCT_ORDER_ID bigint not null,
    ORDERS_NUMBER integer not null,
    ORDER_DATE timestamp not null,
    VERSION_NO bigint not null,
    constraint PRODUCT_ORDER_PK primary key(PRODUCT_ORDER_ID)
);
