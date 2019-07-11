<%@ page contentType="text/html; charset=gb2312" language="java" %>
<%@ page import="java.sql.*"%>
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.ch10.*" %>
<jsp:useBean id="pages" scope="page" class="com.ch10.splitPage"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%!	
	//ÿҳ��ʾ�ļ�¼��
	int pageSize = 3;
	String sqlStr="";
	//��ǰҳ
	int showPage=1;
	//���ݿ��û���
	String userName="root";
	//���ݿ�����
	String userPassword="root";
    //���ݿ��URL�������������ݿ���ʹ�õı����ʽ
	String url="jdbc:mysql://localhost:3306/ch10?useUnicode=true&characterEncoding=gb2312";
	//�������Ӷ���
	Connection dbcon;
%>
<%
	try 
	{
		//������������
		Class.forName("com.mysql.jdbc.Driver");
		//������ݿ�����Ӷ���
		dbcon= DriverManager.getConnection(url,userName,userPassword);				
	}
	catch(SQLException ex)
	{
		//��ӡ���쳣��Ϣ
		System.out.println(ex.toString());		
	}
	catch(ClassNotFoundException ex)
	{
		//��ӡ���쳣��Ϣ
		System.out.println(ex.toString());	
	}

	//��pages�в���con��ֵ
	pages.setCon(dbcon);	
	sqlStr = "select * from words order by WordsID";
	//��ѯ���ݱ���ò�ѯ���	
	String strPage=null;
	//��ȡ��ת����Ŀ��ҳ��
	strPage=request.getParameter("showPage");
	if (strPage==null)
	{
		showPage=1;
	}
	else
	{
		try
		{
			showPage=Integer.parseInt(strPage);
		}
		catch(NumberFormatException e)
		{
			showPage = 1;
		}
		if(showPage<1) 
		{
			showPage=1;
		}
	}
pages.initialize(sqlStr,pageSize,showPage);
	//��ȡҪ��ʾ�����ݼ���
	Vector vData=pages.getPage();
%>
<html> 
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<title>��ҳ��ʾ</title>
	</head>
	<body bgcolor="#FFFFFF" text="#000000">
		<h1 align=center>���Բ�</h1>
		<div align=center>
		<table border="1" cellspacing="0" cellpadding="0" width="80%"> 
			<tr> 
			<th width="20%">���</th> 
			<th width="50%">���Ա���</th> 
			<th width="30%">����ʱ��</th> 
			</tr>
		<% 
			for(int i=0;i<vData.size();i++)
			{ 
				//��ʾ����
				String[] sData=(String[])vData.get(i);
		%>
				<tr> 
					<td><%=sData[0]%></td> 
					<td align=left><%=sData[1]%></td> 
					<td align=left>
					<%
						//��ʾ����ʱ�䣬ʡȥʱ�䴮��"."������ַ�
						String str_WordsTime = sData[3];
						if(str_WordsTime.indexOf(".")>-1)
						{

str_WordsTime=str_WordsTime.substring(0,str_WordsTime.indexOf("."));
						}
						out.println(str_WordsTime);
					%>			
					</td> 
				</tr> 		
		<%
			}
		%>
		</table>
		<form action="words_list_javabean.jsp" method="get" target="_self">	
			��<font color=red><%=pages.getRowCount()%></font>��&nbsp;
			<%=pageSize%>��/ҳ&nbsp;
			��<font color=red><%=showPage%></font>ҳ/��<font 
color=red><%=pages.getPageCount()%></font>ҳ &nbsp;
			<a href="words_list_javabean.jsp?showPage=1" target="_self">[��
ҳ]</a>&nbsp;			
			<%
				//�ж�"��һҳ"�����Ƿ�Ҫ��ʾ
				if(showPage > 1)
				{				
			%>
					<a 
href="words_list_javabean.jsp?showPage
=<%=showPage-1%>" target="_self">[��һҳ]</a>&nbsp;
			<%
				}
				else
				{
			%>
					[��һҳ]&nbsp;
			<%
				}
				//�ж�"��һҳ"�����Ƿ�Ҫ��ʾ
				if(showPage < pages.getPageCount())
				{				
			%>
					<a 
href="words_list_javabean.jsp?showPage
=<%=showPage+1%>" target="_self">[��һҳ]</a>&nbsp;
			<%
				}
				else
				{
			%>
					[��һҳ]&nbsp;
			<%
				}
			%> 
			<a 
href="words_list_javabean.jsp?showPage
=<%=pages.getPageCount()%>" target="_self">[βҳ]</a>&nbsp;
			ת��
			<select name="showPage">
			<%
				for(int x=1;x<=pages.getPageCount();x++)
				{
			%>
					<option value="<%=x%>" <%if(showPage==x) 
out.println("selected");%> ><%=x%></option>
			<%
				}
			%>
			</select>			
			ҳ&nbsp;
			<input type="submit" name="go" value="�ύ"/>
		</form>
		<%
			//�ر����ݿ�����
			dbcon.close();		
		%>
		</div>
	</body>
</html>
