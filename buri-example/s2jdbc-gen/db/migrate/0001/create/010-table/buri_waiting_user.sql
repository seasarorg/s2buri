create table BURI_WAITING_USER (
    WAITING_USER_ID bigint not null,
    WAITING_ID bigint not null,
    BURI_USER_ID bigint not null,
    INSERT_DATE timestamp not null,
    DELETE_DATE timestamp not null,
    constraint BURI_WAITING_USER_PK primary key(WAITING_USER_ID)
);
