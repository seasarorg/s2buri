create table BURI_PATH (
    PATH_ID bigint not null,
    PATH_NAME varchar(100) not null,
    REAL_PATH_NAME varchar(100) not null,
    PATH_TYPE bigint,
    constraint BURI_PATH_PK primary key(PATH_ID)
);
