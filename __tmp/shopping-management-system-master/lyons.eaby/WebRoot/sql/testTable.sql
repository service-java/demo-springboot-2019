

-- insert into classify
insert into classify(gname) values('Ь��');
insert into classify(gname) values('����');
insert into classify(gname) values('�ֻ�');
insert into classify(gname) values('���Ӳ�Ʒ');
commit;

drop trigger classify_tigger
-- insert into commodity

INSERT INTO commodity(commodity_name,commodity_made,commodity_price,commodity_balance,commodity_pic,commodity_id) VALUES ('��������Ь', '��ɽ', 180, 500, '001.jpg',1);
INSERT INTO commodity(commodity_name,commodity_made,commodity_price,commodity_balance,commodity_pic,commodity_id) VALUES ('��̤�˶�Ь', '����', 120, 800, '002.jpg',1);
INSERT INTO commodity(commodity_name,commodity_made,commodity_price,commodity_balance,commodity_pic,commodity_id) VALUES ('�Ϳ��˶�Ь', '����', 500, 1000, '003.jpg',1);
INSERT INTO commodity(commodity_name,commodity_made,commodity_price,commodity_balance,commodity_pic,commodity_id) VALUES ('���ϴ�˹TѪ��', '�Ϻ�', 388,600,'004.jpg',2);
INSERT INTO commodity(commodity_name,commodity_made,commodity_price,commodity_balance,commodity_pic,commodity_id) VALUES ('�����Ļ���', '����', 180, 900, '005.jpg',2);
INSERT INTO commodity(commodity_name,commodity_made,commodity_price,commodity_balance,commodity_pic,commodity_id) VALUES ('С��3', '����', 1999, 3000, '006.jpg',3);
INSERT INTO commodity(commodity_name,commodity_made,commodity_price,commodity_balance,commodity_pic,commodity_id) VALUES ('С��2S', '����', 1299, 1000, '007.jpg',3);
INSERT INTO commodity(commodity_name,commodity_made,commodity_price,commodity_balance,commodity_pic,commodity_id) VALUES ('thinkpad�ʼǱ�', '����', 6999, 500, '008.jpg',4);
INSERT INTO commodity(commodity_name,commodity_made,commodity_price,commodity_balance,commodity_pic,commodity_id) VALUES ('dell�ʼǱ�', '����', 3900, 500, '009.jpg',4);
INSERT INTO commodity(commodity_name,commodity_made,commodity_price,commodity_balance,commodity_pic,commodity_id) VALUES ('ipad5', '����', 5900, 500, '010.jpg',4);

--Drop all table ע��ɾ��˳��--
drop trigger commodity_trigger;
commit;
drop sequence commodity_seq;
commit;
drop table commodity;
commit;

drop trigger classify_trigger;
commit;
drop sequence classify_seq;
commit;
drop table classify;
commit;

drop trigger orderForm_trigger;
commit;
drop sequence orderForm_seq;
commit;
drop table orderForm ;
commit;

 
