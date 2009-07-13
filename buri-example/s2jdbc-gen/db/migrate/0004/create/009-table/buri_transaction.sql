create table BURI_TRANSACTION (
    BT_ID bigint not null,
    INSERT_DATE timestamp not null,
    VERSION_NO bigint not null,
    constraint BURI_TRANSACTION_PK primary key(BT_ID)
);
