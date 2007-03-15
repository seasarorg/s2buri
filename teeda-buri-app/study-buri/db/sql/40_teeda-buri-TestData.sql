delete from customer;
insert into customer(customerid,customerName) values(nextval('customerid'),'客1');
insert into customer(customerid,customerName) values(nextval('customerid'),'客2');
insert into customer(customerid,customerName) values(nextval('customerid'),'客3');
insert into customer(customerid,customerName) values(nextval('customerid'),'客4');

delete from item;
insert into item(itemid,itemName,price) values(nextval('itemid'),'PS2',19800);
insert into item(itemid,itemName,price) values(nextval('itemid'),'Wii',29800);
insert into item(itemid,itemName,price) values(nextval('itemid'),'ワンダースワン',5000);
insert into item(itemid,itemName,price) values(nextval('itemid'),'ゲームボーイアドバンス',9800);
