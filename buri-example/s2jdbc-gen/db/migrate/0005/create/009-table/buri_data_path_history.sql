create table BURI_DATA_PATH_HISTORY (
    HISTORY_ID bigint not null,
    PATH_ID bigint,
    PATH_NAME varchar(200),
    DATA_ID bigint,
    ACTION varchar(50),
    BURI_USER_ID bigint,
    BT_ID bigint not null,
    INSERT_DATE timestamp not null,
    constraint BURI_DATA_PATH_HISTORY_PK primary key(HISTORY_ID)
);
