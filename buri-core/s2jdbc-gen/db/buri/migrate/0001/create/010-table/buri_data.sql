create table BURI_DATA (
    DATA_ID bigint not null,
    PKEY_VAL varchar(250),
    PKEY_NUM bigint,
    DATA_TYPE varchar(200) not null,
    TABLE_NAME varchar(200),
    INSERT_USER_ID bigint,
    constraint BURI_DATA_PK primary key(DATA_ID)
);
