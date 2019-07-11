<%@ page contentType="text/html; charset=gb2312" %>
<%@ page session="true" %>
<%
if (session.getAttribute("admin")==null || session.getAttribute("admin")==""){
	response.sendRedirect("error.htm");
} %>

<%@ page import="org.pan.web.book.indent" %>
<%@ page import="org.pan.web.book.shopuser" %>
<jsp:useBean id="shop" scope="page" class="org.pan.web.purchase" />
<jsp:useBean id="user" scope="page" class="org.pan.web.usermn" />

<%
int pages=1;
String mesg = "";
if (request.getParameter("action")!=null && request.getParameter("action").equals("del")){
	try {
		long id = Long.parseLong(request.getParameter("indentid"));
		if (shop.delete(id)) {
			mesg = "ɾ���������ϳɹ���";
		} else {
			mesg = "ɾ����������";
		}
	} catch (Exception e) {
		mesg ="��Ҫɾ�������ݲ�������";
	}
}

if (request.getParameter("page")!=null && !request.getParameter("page").equals("")) {
	String requestpage = request.getParameter("page");	
	try {
		pages = Integer.parseInt(requestpage);
	} catch(Exception e) {
		mesg = "��Ҫ�ҵ�ҳ�����!";
	}
	shop.setPage(pages);
}
%>
<html>
<head>
<title>�����������ϵͳ</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<script language="javascript">

  function openScript(url,name, width, height){
	var Win = window.open(url,name,'width=' + width + ',height=' + height + ',resizable=1,scrollbars=yes,menubar=no,status=yes' );
}
</script>
<link rel="stylesheet" href="books.css" type="text/css">
</head>

<body text="#000000">
<div align="center">
  <table width="100%" border="0" cellspacing="2" cellpadding="2">
     <tr> 
      
      <td colspan=2 align="center" ><img src="../images/backlogo.jpg" ></td>
    </tr>
    <tr>
      <td align="center" width="23%" valign="top"> 
        <jsp:include flush="true" page="list.jsp"></jsp:include>
      </td>
      <td align="center" width="88%">
        <p>��ӭ�㣬��Ʒ����Ա...............</p>
        <p>�������񶩵����:</p>
		<% if (!mesg.equals("")) out.println("<font color=red>" + mesg + "</font><br>");%>
        <table width="98%" border="1" cellspacing="1" cellpadding="1" bordercolor="#336633">
          <tr align="center"> 
            <td>�������</td>
            <td>�û���</td>
            <td>�µ�ʱ��</td>
			<td>����ʱ��</td>
            <td>�ܽ��</td>
			<td>����IP</td>
			<td>����</td>
            <td>����</td>
            <td>�鿴</td>
          </tr>
<%
  if (shop.getIndent()) {
		for(int i=0 ; i<shop.getMy_indent().size(); i++){
			indent Ident = (indent) shop.getMy_indent().elementAt(i);	%>
		  <tr>
            <td><%= Ident.getIndentNo() %></td>
            <td align="center"><%
				if (user.getUserinfo(Ident.getUserId()) ) {
					shopuser userinfo = (shopuser)user.getUserlist().elementAt(0); %>
				<a href="#" onclick="openScript('showuser.jsp?userid=<%= Ident.getUserId() %>','showuser',450,500)"><%= userinfo.getUserName() %></a>
			<%} else {
					out.println("���û��ѱ�ɾ��");
				}			
			%></td>
            <td align="center"><%= Ident.getSubmitTime() %></td>
			<td align="center"><%= Ident.getConsignmentTime() %></td>
            <td align="center"><%= Ident.getTotalPrice() %></td>
			<td align="center"><%= Ident.getIPAddress() %></td>
			<td align="center">
			<% if (Ident.getIsPayoff() )
					out.print("�Ѹ���");
				else
					out.print("δ��");
			%></td>
            <td align="center">
			<% if (Ident.getIsSales())
					out.print("�ѷ���");
				else 
					out.print("δ����");
			%></td>
            <td align="center"><a href="#"  onclick="openScript('indentlist.jsp?indentid=<%= Ident.getId() %>','indent',500,500)" >��ϸ���</a>&nbsp;<a href="orderlist.jsp?action=del&indentid=<%= Ident.getId()%>&page=<%= shop.getPage() %>" onclick="return(confirm('�����Ҫɾ����'))">ɾ��</a></td>
          </tr>
<%		}
  }

%>

        </table>
		 <table width="90%" border="0" cellspacing="1" cellpadding="1">
          <tr>
            <td align="right">��ǰҳ��<%= shop.getPage() %>ҳ��<a href="orderlist.jsp">��ҳ</a>&nbsp; 
              <% if (shop.getPage()>1) {%>
              <a href="orderlist.jsp?page=<%= shop.getPage()-1 %>">��һҳ</a>&nbsp; 
              <% } %>
              <% if (shop.getPage()<shop.getPageCount()-1) {%>
              <a href="orderlist.jsp?page=<%= shop.getPage()+1 %>">��һҳ</a>&nbsp; 
              <% } %>
              <a href="orderlist.jsp?page=<%= shop.getPageCount() %>">δҳ</a>&nbsp;</td>
          </tr>
        </table>
        <p>&nbsp;</p>
      </td>
    </tr>
  </table>
  <br>
  <jsp:include flush="true" page="..\bottom.jsp"></jsp:include>
  
</div>
</body>
</html>
<% shop.close();%>