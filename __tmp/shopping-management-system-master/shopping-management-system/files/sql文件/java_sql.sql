select * from gsales
select * from goods
select * from salesman

--չʾ������Ʒ�б����ö��������sql���ͣ�
------------------------------------------------------
select gname,gprice,gnum, allSum                        --�����±����Ŀ

from goods, (select gid as salesid,sum(snum) as allSum  --�����������������һ�ű�
            from gsales                                   --��gid��ͬ��������
            where trunc(sdate) = trunc(sysdate)            --ʱ���ϵͳ��ȡ�������������sdateʱ����ͬ
            group by gid)
where gid = salesid                                      --goods���������������ű�ȡgid��salessid�Ľ���
------------------------------------------------------

--֪ʶ��磺
select trunc(sysdate) from dual;
select trunc(sysdate) + 1 from dual;
select sysdate from dual;       --��ϵͳ��ȡʱ��

select sid,to_char(sdate,'yyyy/mm/dd') from gsales --ת��ʱ�������ʽ��ע�⣺�ֶεĸ�ʽ���ܱ�ת��




