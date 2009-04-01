alter table ORDER_DETAIL add constraint ORDER_DETAIL_FK1 foreign key (ORDER_TITLE_ID) references ORDER_TITLE (ORDER_TITLE_ID);
