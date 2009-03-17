create table BURI_TEST_USER (
    USER_ID bigint not null,
    USER_NAME varchar(100) not null,
    ROLE_NAME varchar(100) not null,
    PARENT_USER_ID bigint not null,
    constraint BURI_TEST_USER_PK primary key(USER_ID)
);
