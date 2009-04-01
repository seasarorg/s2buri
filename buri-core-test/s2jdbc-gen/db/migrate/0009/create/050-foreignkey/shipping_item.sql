alter table SHIPPING_ITEM add constraint SHIPPING_ITEM_FK1 foreign key (SHIPPING_ID) references SHIPPING (SHIPPING_ID);
