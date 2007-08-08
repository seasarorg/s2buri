delete from customer;
insert into customer(customerid,customerName) values(nextval('customerid'),'Customer1');
insert into customer(customerid,customerName) values(nextval('customerid'),'Customer2');
insert into customer(customerid,customerName) values(nextval('customerid'),'Customer3');
insert into customer(customerid,customerName) values(nextval('customerid'),'Customer4');

delete from item;
insert into item(itemid,itemName,price) values(nextval('itemid'),'PS2',19800);
insert into item(itemid,itemName,price) values(nextval('itemid'),'Wii',29800);
insert into item(itemid,itemName,price) values(nextval('itemid'),'Nintendo DS',5000);
insert into item(itemid,itemName,price) values(nextval('itemid'),'Super Famicon',9800);
