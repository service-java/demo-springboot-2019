--�������ݿ�˵���飬������Ʒ���۱� GSALES 
---���ڴ˱�Ľ������Բο�������oracle�����е����£�֪ʶ���е��

--oracle ��֧�ּ������£����ˣ�
--ע�⣬��������
CREATE TABLE gsales
(
       gsid  NUMBER(10) PRIMARY KEY,
                                          --����������������,
       gid   NUMBER(10) REFERENCES goods(gid)   NOT NULL,
       sid   NUMBER(10) REFERENCES salesman(sid) NOT NULL, 
       sdate DATE DEFAULT SYSDATE NOT NULL, --�������ݸĶ�ʱ�Զ���ȡϵͳʱ�䲢ͬ�������ݿ�
       snum  NUMBER(11) NOT NULL
);

--��������

CREATE SEQUENCE gsales_seq
       START WITH      1
       INCREMENT BY    1
       MINVALUE        1
       MAXVALUE     100000
       NOCYCLE
       CACHE           10
       
--������

CREATE TRIGGER  gsales_trigger
       BEFORE INSERT ON gsales
       FOR EACH ROW 
       BEGIN
           SELECT gsales_seq.nextval into :new.gsid FROM dual;
       END
commit;
       
