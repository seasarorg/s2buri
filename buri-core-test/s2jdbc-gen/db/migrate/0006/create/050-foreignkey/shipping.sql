alter table SHIPPING add constraint SHIPPING_FK1 foreign key (ORDER_TITLE_ID) references ORDER_TITLE (ORDER_TITLE_ID);
