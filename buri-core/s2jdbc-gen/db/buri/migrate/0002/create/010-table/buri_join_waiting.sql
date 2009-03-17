create table BURI_JOIN_WAITING (
    WAITING_ID bigint not null,
    PATH_ID bigint,
    DATA_ID bigint,
    BRANCH_ID bigint,
    BT_ID bigint,
    INSERT_DATE timestamp not null,
    PROCESS_DATE timestamp not null,
    ABORT_DATE timestamp not null,
    VERSION_NO integer,
    constraint BURI_JOIN_WAITING_PK primary key(WAITING_ID)
);
