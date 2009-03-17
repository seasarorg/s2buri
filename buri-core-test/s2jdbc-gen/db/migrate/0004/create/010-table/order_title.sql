create table ORDER_TITLE (
    ORDER_TITLE_ID bigint not null,
    ORDER_DATE timestamp,
    CUSTOMER_ID bigint,
    STATUS integer,
    constraint ORDER_TITLE_PK primary key(ORDER_TITLE_ID)
);
