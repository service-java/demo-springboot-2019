<%@ page contentType="text/html; charset=gb2312" %>
<%@ page session="true" %>
<%@ page import="org.pan.web.book.goods" %>
<%@ page import="org.pan.util.strFormat" %>
<jsp:useBean id="book" scope="page" class="org.pan.web.goodsmn" />
<%
String mesg = "";
int Id=0;
if (request.getParameter("bookid")==null || request.getParameter("bookid").equals("")) {
		mesg = "��Ҫ�鿴����Ʒ�����ڣ�";
} else {
	try {
		Id = Integer.parseInt(request.getParameter("bookid"));
		if (!book.getOnebook(Id)){
			mesg = "��Ҫ�鿴����Ʒ�����ڣ�";
		}
	} catch (Exception e){
		mesg = "��Ҫ�鿴����Ʒ�����ڣ�";
	}
}

%>

<html>
<head>
<title>�������񡡲鿴��Ʒ����</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<script language="javascript">
function check()
{
	if (document.form1.amount.value<1){
		alert("��Ĺ�������������");
		document.form1.amount.focus();
		return false;
	}
	return true;
}


</script>
<link rel="stylesheet" href="books.css" type="text/css">
</head>

<body  text="#000000" >
<div align="center">
  <p>��������ӭ��<font color="#CC0066">ѡ����Ʒ</font>��</p>
  <% if(!mesg.equals("")){
		out.println(mesg);
	  } else {
		goods bk = (goods) book.getGoodslist().elementAt(0);			  
	%>
  <table width="90%" border="0" cellspacing="2" cellpadding="1">
      <tr> 
        <td align="right" width="120">��Ʒ����</td>
        <td><%= bk.getGoodsName() %></td>
      </tr>
      <tr> 
        <td align="right" width="120">����Ա��</td>
        <td><%= bk.getAuthor() %></td>
      </tr>
      <tr> 
        <td align="right" width="120">�������</td>
        <td><%= bk.getClassname() %></td>
      </tr>
      <tr> 
        <td align="right" width="120">�����̣�</td>
        <td><%= bk.getPublish() %></td>
      </tr>
      <tr> 
        <td align="right" width="120">��Ʒ��ţ�</td>
        <td><%= bk.getGoodsNo() %></td>
      </tr>
      <tr> 
        <td align="right" width="120">��ۣ�</td>
        <td><%= bk.getPrince() %></td>
      </tr>
      <tr> 
        <td align="right" width="120" valign="top">���ݽ��ܣ�</td>
        <td><%= strFormat.toHtml(bk.getContent()) %></td>
      </tr>
      <tr> 
        <td align="right" width="120" valign="top">������</td>
        <td><%= bk.getAmount() %></td>
      </tr> 
	  <tr> 
        <td align="right" width="120" valign="top">ʣ������</td>
        <td><%= bk.getLeav_number() %></td>
      </tr> 
	  <tr> 
        <td align="right" width="120" valign="top">��¼ʱ��</td>
        <td><%= bk.getRegTime() %></td>
      </tr>
      
  </table>
<% } %>
  <br><p><a href="javascript:window.close()">�رմ���</a></p>
  <jsp:include flush="true" page="..\bottom.jsp"></jsp:include>
  
</div>
</body>
</html>
