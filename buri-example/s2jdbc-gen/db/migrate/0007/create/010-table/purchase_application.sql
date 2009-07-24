create table PURCHASE_APPLICATION (
    PURCHASE_APPLICATION_ID bigint not null,
    APPLICANT_NAME varchar(255) not null,
    APPLICATION_DATE timestamp not null,
    PRODUCT_NAME varchar(255) not null,
    AMOUNT integer not null,
    CREATE_DATE timestamp not null,
    UPDATE_DATE timestamp not null,
    VERSION_NO bigint not null,
    constraint PURCHASE_APPLICATION_PK primary key(PURCHASE_APPLICATION_ID)
);
