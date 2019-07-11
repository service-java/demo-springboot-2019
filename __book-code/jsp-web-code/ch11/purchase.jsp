<%@ page contentType="text/html; charset=gb2312" %>
<%@ page session="true" %>
<%@ page import="org.pan.web.book.goods" %>
<jsp:useBean id="book" scope="page" class="org.pan.web.goodsmn" />
<jsp:useBean id="shop" scope="page" class="org.pan.web.purchase" />

<%
String mesg = "";
String submits = request.getParameter("Submit");
int Id=0;
if (submits!=null && !submits.equals("")){
	if (shop.addnew(request)){
		mesg = "��Ҫ����Ʒ�Ѿ�������Ĺ��ﳵ�У�лл";
	} else if (shop.getIsEmpty()){
		mesg = "�����Ʒ�������㣡ֻʣ"+shop.getLeaveGoods()+"��";
	} else {
		mesg = "��ʱ���ܹ���";
	}
}else {
	if (request.getParameter("bookid")==null || request.getParameter("bookid").equals("")) {
			mesg = "��Ҫ�������Ʒ�����ڣ�";
	} else {
		try {
			Id = Integer.parseInt(request.getParameter("bookid"));
			if (!book.getOnebook(Id)){
				mesg = "��Ҫ�������Ʒ�����ڣ�";
			}
		} catch (Exception e){
			mesg = "��Ҫ�������Ʒ�����ڣ�";
		}
	}
}
%>
<html>
<head>
<title>�������񡡹�����Ʒ</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<script language="javascript">

function openScript(url,name, width, height){
	var Win = window.open(url,name,'width=' + width + ',height=' + height + ',resizable=1,scrollbars=yes,menubar=no,status=yes' );
}

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

<body  text="#000000" onload="javascript:window.focus();">
<div align="center">
  <p>��������ӭ��<font color="#CC0066">ѡ����Ʒ</font>��</p>
   <% if(!mesg.equals("")){
		out.println(mesg);
	  } else {
		goods bk = (goods) book.getGoodslist().elementAt(0);			  
	%>
 <form name="form1" method="post" action="purchase.jsp">
  <table width="90%" border="0" cellspacing="2" cellpadding="1">
      <tr> 
        <td align="center">��Ʒ����<%= bk.getGoodsName() %></td>
      </tr>
      <tr align="center"> 
        <td>����Ҫ�������� 
          <input type="text" name="amount" maxlength="4" size="3" value="1"> </td>
      </tr>
      <tr align="center"> 
        <td>
		  <input type="hidden" name="bookid" value="<%=Id %>">
          <input type="submit" name="Submit" value="�� ��" onclick="return(check());">
          <input type="reset" name="Reset" value="ȡ ��">
        </td>
      </tr>
	   <tr align="center"> 
        <td><a href="#" onclick="openScript('showgoods.jsp?bookid=<%= Id %>','show',400,450)" >�鿴��ϸ����</a> </td>
      </tr>   
  </table>
   </form>
<% } %>
  <br>
  <p><a href="javascript:window.close()">�رմ���</a></p>
 <jsp:include flush="true" page="bottom.jsp"></jsp:include>
  
</div>
</body>
</html>
