create table SHIPPING_ITEM (
    SHIPPING_ITEM_ID bigint not null,
    ORDER_DETAIL_ID bigint,
    SHIPPING_ID bigint,
    constraint SHIPPING_ITEM_PK primary key(SHIPPING_ITEM_ID)
);
