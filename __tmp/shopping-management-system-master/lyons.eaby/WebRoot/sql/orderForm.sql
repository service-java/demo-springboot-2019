-- orderForm������ --

create table orderForm 
(
     id number(10) primary key,
     username varchar2(255) not null,
     orderDate DATE DEFAULT SYSDATE NOT NULL,
     commodity_name varchar2(255) not null,
     commodity_price NUMBER(18,2) not null,
     sum number(10) 
);

--��������

CREATE SEQUENCE orderForm_seq
       START WITH      1
       INCREMENT BY    1
       MINVALUE        1
       MAXVALUE     100000
       NOCYCLE
       CACHE           10;
       
--������

CREATE TRIGGER  orderForm_trigger
       BEFORE INSERT ON orderForm
       FOR EACH ROW 
       BEGIN
           SELECT orderForm_seq.nextval into :new.id FROM dual;
       END;
commit;
