create table BURI_STATE_USER (
    STATE_USER_ID bigint not null,
    STATE_ID bigint,
    BURI_USER_ID bigint,
    INSERT_DATE timestamp not null,
    DELETE_DATE timestamp not null,
    constraint BURI_STATE_USER_PK primary key(STATE_USER_ID)
);
