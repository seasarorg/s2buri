create table ORDER_DETAIL (
    ORDER_DETAIL_ID bigint not null,
    ORDER_COUNT integer,
    ITEM_ID bigint,
    ORDER_TITLE_ID bigint,
    constraint ORDER_DETAIL_PK primary key(ORDER_DETAIL_ID)
);
