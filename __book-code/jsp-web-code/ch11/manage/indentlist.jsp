<%@ page contentType="text/html; charset=gb2312" %>
<%@ page session="true" %>
<%
if (session.getAttribute("admin")==null || session.getAttribute("admin")==""){
	response.sendRedirect("error.htm");
} %>

<%@ page import="org.pan.web.book.*" %>
<%@ page import="org.pan.util.strFormat" %>
<jsp:useBean id="myIndentlist" scope="page" class="org.pan.web.purchase" />
<jsp:useBean id="mybook" scope="page" class="org.pan.web.goodsmn" />
<jsp:useBean id="user" scope="page" class="org.pan.web.usermn" />

<%
String mesg = "";
long Id=0;
String submit= request.getParameter("submit");
if (submit!=null && !submit.equals("")){
	if (myIndentlist.update(request))
		mesg = "���¶���״̬�ɹ���";
	else 
		mesg = "���¶���״̬����!";
}

if (request.getParameter("indentid")==null || request.getParameter("indentid").equals("")) {
		mesg = "��Ҫ�鿴�Ķ����嵥�����ڣ�";
} else {
	try {
		Id = Long.parseLong(request.getParameter("indentid"));
		if (!myIndentlist.getOneIndent(Id) || !myIndentlist.getIndentList(Id)){
			mesg = "��Ҫ�鿴�Ķ����嵥�����ڣ�";
		}
	} catch (Exception e){
		mesg = "��Ҫ�鿴�Ķ����嵥�����ڣ�";
	}
}



%>

<html>
<head>
<title>�����������ϵͳ���鿴�����嵥����</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<script language="javascript">

  function openScript(url,name, width, height){
	var Win = window.open(url,name,'width=' + width + ',height=' + height + ',resizable=1,scrollbars=yes,menubar=no,status=yes' );
}
</script>

<link rel="stylesheet" href="books.css" type="text/css">
</head>

<body  text="#000000" onload="javascript:window.focus();" >
<div align="center">
  
  <% if(!mesg.equals("")){
		out.println(mesg);
	  } else {
		indent Ident = (indent) myIndentlist.getMy_indent().elementAt(0);
	%>
		<p>����������Ʒ����<%= Ident.getIndentNo() %>&nbsp;�嵥:</p>
          <table width="95%" border="1" cellspacing="1" cellpadding="1" bordercolor="#CC9966">
          <tr align="center"> 
            <td>��Ʒ����</td>
            <td>����Ա</td>
            <td>��Ʒ���</td>
            <td>����(Ԫ)</td>
            <td>����</td>
          </tr>
	<%
	float totalprice =0;
	int totalamount = 0;
	for (int i=0; i<myIndentlist.getIndent_list().size();i++){
		indentlist idList = (indentlist) myIndentlist.getIndent_list().elementAt(i);
		if (mybook.getOnebook((int)idList.getGoodsNo()) ){
			goods bk = (goods) mybook.getGoodslist().elementAt(0);		
	%>	  
	      <tr align="center"> 
            <td><%= bk.getGoodsName() %></td>
            <td><%= bk.getAuthor() %></td>
            <td><%= bk.getClassname() %></td>
            <td><%= bk.getPrince() %></td>
            <td><%= idList.getAmount() %></td>
          </tr>
	<%	totalprice = totalprice + bk.getPrince() * idList.getAmount();
		totalamount = totalamount + idList.getAmount();
		} %>
	
<%   }%><tr align="center"> 
            <td colspan=5 >������ܽ���ǣ�<%= totalprice %>Ԫ,��������<%= totalamount%></td> </tr>
		</table>
  <br>
  	<form name="form1" method="post" action="indentlist.jsp" >
  <table width="95%" border="1" cellspacing="2" cellpadding="1" bordercolor="#CC9966">
    <tr> 
      <td width="40%" align="right">�µ��û���</td>
      <td width="60%"><%
				if (user.getUserinfo(Ident.getUserId()) ) {
					shopuser userinfo = (shopuser)user.getUserlist().elementAt(0); %>
				<a href="#" onclick="openScript('showuser.jsp?userid=<%= Ident.getUserId() %>','showuser',450,500)"><%= userinfo.getUserName() %></a>
			<%} else {
					out.println("���û��ѱ�ɾ��");
				}			
			%></td>
    </tr>
    <tr> 
      <td width="40%" align="right">�µ�ʱ�䣺</td>
      <td width="60%"><%= Ident.getSubmitTime() %></td>
    </tr>
    <tr> 
      <td width="40%" align="right">����ʱ�䣺</td>
      <td width="60%"><%= Ident.getConsignmentTime() %>&nbsp;</td>
    </tr>
    <tr> 
      <td width="40%" align="right">�ܽ�</td>
      <td width="60%"><%= Ident.getTotalPrice() %>&nbsp;</td>
    </tr>
    <tr> 
      <td width="40%" align="right">�µ�ʱIP��</td>
      <td width="60%"><%= Ident.getIPAddress() %>&nbsp;</td>
    </tr>
    <tr> 
      <td width="40%" align="right">�û���ע��</td>
      <td width="60%"><%= strFormat.toHtml(Ident.getContent()) %>&nbsp;</td>
    </tr>

    <tr> 
      <td width="40%" align="right">�Ƿ񸶿</td>
      <td width="60%"> 
        <input type="radio" name="payoff" value="1" <%if (!Ident.getIsPayoff()) out.print("checked") ; %>>
        ��<input type="radio" name="payoff" value="2" <%if (Ident.getIsPayoff()) out.print("checked") ; %>>
        ��</td>
    </tr>
    <tr> 
      <td width="40%" align="right">�Ƿ񽻻���</td>
      <td width="60%"> 
        <input type="radio" name="sales" value="1" <%if (!Ident.getIsSales()) out.print("checked") ; %>>
        ��<input type="radio" name="sales" value="2" <%if (Ident.getIsSales()) out.print("checked") ; %>>
        ��</td>
    </tr>
    <tr>
      <td width="40%" align="right">&nbsp;</td>
      <td width="60%">
		<input type="hidden" name="indentid" value="<%= Id %>">
        <input type="submit" name="submit" value="����">
      </td>
    </tr>

  </table>
  	</form>
  <br>

<% } %>
  <br><p><a href="javascript:window.close()">�رմ���</a></p>
   <jsp:include flush="true" page="..\bottom.jsp"></jsp:include>
  
</div>
</body>
</html>
<%mybook.close();
myIndentlist.close();
user.close();%>