create table SHIPPING (
    SHIPPING_ID bigint not null,
    SHIPPING_DATE timestamp,
    ORDER_TITLE_I_D bigint,
    CUSTOMER_I_D bigint,
    constraint SHIPPING_PK primary key(SHIPPING_ID)
);
