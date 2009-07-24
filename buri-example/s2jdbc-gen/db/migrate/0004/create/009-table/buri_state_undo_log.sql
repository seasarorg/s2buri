create table BURI_STATE_UNDO_LOG (
    STATE_UNDO_LOG_ID bigint not null,
    STATE_ID bigint,
    PATH_ID bigint,
    DATA_ID bigint,
    BRANCH_ID bigint,
    USER_ID_VAL varchar(20),
    USER_ID_NUM bigint,
    BT_ID bigint not null,
    CREATE_BT_ID bigint not null,
    INSERT_DATE timestamp not null,
    AUTO_RUN_TIME timestamp,
    PROCESS_DATE timestamp,
    ABORT_DATE timestamp not null,
    VERSION_NO integer not null,
    constraint BURI_STATE_UNDO_LOG_PK primary key(STATE_UNDO_LOG_ID)
);
