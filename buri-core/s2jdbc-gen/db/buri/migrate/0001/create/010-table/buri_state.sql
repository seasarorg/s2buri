create table BURI_STATE (
    STATE_ID bigint not null,
    PATH_ID bigint,
    DATA_ID bigint,
    BRANCH_ID bigint,
    USER_ID_VAL varchar(20),
    PARTICIPANT_NAME varchar(200),
    PARTICIPANT_TYPE varchar(200),
    USER_ID_NUM bigint,
    BT_ID bigint not null,
    INSERT_DATE timestamp not null,
    AUTO_RUN_TIME timestamp not null,
    PROCESS_DATE timestamp not null,
    ABORT_DATE timestamp not null,
    VERSION_NO integer not null,
    constraint BURI_STATE_PK primary key(STATE_ID)
);
