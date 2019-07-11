-- ��Ʒ����� --

create table classify
(
       gid   number(11) primary key,
       gname varchar2(200) not null
);
-- Ϊclassify������gid����Ψһ ����
create sequence classify_seq
       start with   1
       increment by 1
       minvalue     1
       maxvalue     10000
       nocycle
       cache        10
       
--����������
create trigger classify_tigger
       before insert on classify
       for each row 
         begin
           select classify_seq.nextval into :new.gid from dual;
         end;
       
       
