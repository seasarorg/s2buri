create table FURNITURE_ITEM (
    FURNITURE_ID bigint not null,
    TYPE varchar(100) not null,
    NAME varchar(100) not null,
    ACQUISITION timestamp not null,
    ACQUISITION_TYPE integer not null,
    VERSION_NO bigint not null,
    constraint FURNITURE_ITEM_PK primary key(FURNITURE_ID)
);
