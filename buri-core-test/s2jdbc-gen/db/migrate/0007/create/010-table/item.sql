create table ITEM (
    ITEM_ID bigint not null,
    ITEM_NAME varchar(100) not null,
    PRICE bigint,
    constraint ITEM_PK primary key(ITEM_ID)
);
