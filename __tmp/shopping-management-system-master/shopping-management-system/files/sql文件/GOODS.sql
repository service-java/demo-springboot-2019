--�������ݿ�����飬���� GOODS �� 

CREATE TABLE GOODS
(
       gid     NUMBER(10) primary key,
       gname   VARCHAR2(20) NOT NULL UNIQUE,
       gprice  NUMBER(18,2) NOT NULL,
       gnum    NUMBER(11) NOT NULL
);

--����gid�����Զ����ɡ�����������ʵ�֣�
 
--1.ΪGOODS������Ψһ ����

CREATE SEQUENCE goods_seq  --�����������ȡ
       START WITH    1
       INCREMENT  BY 1
       MINVALUE      1
       MAXVALUE   100000 --�����ֵ�����������
       NOCYCLE           --����maxvalues�󣬲���ѭ����
       CACHE 10         --ÿ����ǰ����10�������Ч�ʣ������������
       

--2.Ϊ���� ������ �������Զ�������ȡֵ��GOODS����gid�Զ���ֵ

CREATE TRIGGER goods_trigger
       BEFORE INSERT ON goods
       FOR EACH ROW        --�м��������������ÿһ�����ݶ��ᴥ��
       BEGIN               --�������л�ȡ�µ���Ų����� ���� ��gid �ֶ�
           SELECT goods_seq.nextval INTO :new.gid FROM dual;
       END;


--truncate �ܹ�������ݣ�Ȼ��������Ĩ�����еļ��䡣
