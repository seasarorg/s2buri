create table BURI_BRANCH (
    BRANCH_ID bigint not null,
    PARENT_BRANCH_ID bigint,
    PATH_ID bigint,
    DATA_ID bigint,
    BT_ID bigint,
    PROCESS_DATE timestamp not null,
    VERSION_NO bigint not null,
    constraint BURI_BRANCH_PK primary key(BRANCH_ID)
);
