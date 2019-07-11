<%@ page contentType="text/html; charset=gb2312" %>
<%@ page session="true" %>
<%

String username = (String)session.getAttribute("username");
if ( username == null || username.equals("") ){
	response.sendRedirect("login.jsp?msg=nologin");
}

%>

<%@ page import="org.pan.web.book.shopuser" %>
<jsp:useBean id="user" scope="page" class="org.pan.web.usermn" />
<%
long userid=0;
String mesg = "";

String submit = (String)request.getParameter("submit");
if (submit!=null && !submit.equals("")){		
	if(user.update(request)){		
		mesg = "�û������޸ĳɹ���";	
	}else {
		mesg = "�Բ������ύ�Ĳ����д���!" +  user.getMessage();;
	}
}
String UserId = (String)session.getAttribute("userid");
if (UserId!=null && !UserId.equals("")) {
	try {
		userid = Long.parseLong(UserId);
		if (!user.getUserinfo(userid)) {
			mesg = "Ҫ�޸ĵ��û���Ϣ������";
		}
	} catch(Exception e) {
		mesg = "���û��Ų�����!";
	}
}


%>
<html>
<head>
<title>���޿ռ���������޸ĸ�����Ϣ</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<script language="javascript">

function checkform() {
	if (document.form1.username.value==""){
		alert("�û�������Ϊ��");
		document.form1.username.focus();
		return false;
	}
	if (document.form1.passwd.value==""){
		alert("�û����벻��Ϊ��");
		document.form1.passwd.focus();
		return false;
	}
	if (document.form1.passwd.value!=document.form1.passconfirm.value){
		alert("ȷ�����벻�����");
		document.form1.passconfirm.focus();
		return false;
	}
	
	return true;
}

</script>
<link rel="stylesheet" href="books.css" type="text/css">
</head>

<body  text="#000000">
<div align="center">
  <table width="592" border="0" cellspacing="1" cellpadding="1">
    <tr> 
     <td align="center"><img src="images/baners2.jpg" width="592" ></td>
    </tr>
  </table>

  <table width="592" border="0" cellspacing="1" cellpadding="1">
    <tr> 
      <td width="200">&nbsp;</td>
      <td width="55"><a href="index.jsp">��ҳ</a></td>
      <td width="100"><a href="goodlist.jsp">���߹���</a></td>
      <td width="100"><a href="shoperlist.jsp">�ҵĹ��ﳵ</a></td>
      <%  
if ( username == null || username.equals("") ){%>
      <td><a href="login.jsp">�û���¼</a></td>
      <%}else { %>
      <td><a href="logout.jsp">�û��˳�</a></td>
      <%} %>
      <td>&nbsp;</td>
    </tr>
  </table>
  <br>
  <table width="592" border="0" cellspacing="1" cellpadding="1">
    <tr valign="top"> 
      <td width="186" align="center"> 
        <table width="100%" border="0" cellspacing="1" cellpadding="1">
          <tr> 
            <td width="20">&nbsp;</td>
            <td><img src="images/baobei%5B1%5D.gif" width="120" height="60"></td>
          </tr>

          <tr> 
            <td width="20">&nbsp;</td>
            <td><a href="userinfo.jsp">�鿴�������</a></td>
          </tr>
          <tr> 
            <td width="20">&nbsp;</td>
            <td><a href="modimy.jsp">�޸ĸ�����Ϣ</a></td>
          </tr>
          <tr>
            <td width="20">&nbsp;</td>
            <td><a href="shoperlist.jsp">�鿴�ҵĹ��ﳵ</a></td>
          </tr>
        </table>
        <p><img src="images/v3_sun_logo.gif" width="64" height="28"></p>
        <p>&nbsp;</p>
      </td>
      <td align="center"> <br>�޸��ҵĸ�����Ϣ<br>
        		<% if (!mesg.equals("")){
			out.println("<br><font color=red>"+ mesg +"</font><br><br>"); 
		} else {
			shopuser userinfo = (shopuser)user.getUserlist().elementAt(0);
		%>
	<form name="form1" METHOD=POST ACTION="modimy.jsp">
	<table width="95%" border="1" cellspacing="1" cellpadding="1" bordercolor="#2DAE2D">
	
	  <tr><td align=right width=100>�û���</td>
	    <td><%= userinfo.getUserName() %>
		  <input type="hidden" name="username" maxlength="20" size="14" value="<%= userinfo.getUserName() %>" ></td></tr> 
	  <tr><td align=right width=100>�û�����:</td>
		<td><input type="password" name="passwd" maxlength="20" size="14" value="<%= userinfo.getPassWord() %>" ></td></tr> 
	  <tr><td align=right width=100>ȷ������:</td>
		<td><input type="password" name="passconfirm" maxlength="20" size="14" value="<%= userinfo.getPassWord() %>" ></td></tr> 
	  <tr><td align=right width=100>�û�����</td>
		<td> <input type="text" name="names" maxlength="20" size="14" value="<%= userinfo.getNames() %>" ></td></tr>
	  <tr><td align=right width=100>�Ա�</td>
	    <td><select name="sex">
            <option <% if (userinfo.getSex().equals("��")) out.print("selected"); %> >��</option>
            <option <% if (userinfo.getSex().equals("Ů")) out.print("selected"); %> >Ů</option>
          </select>
			</td></tr> 
	  <tr><td align=right width=100>��ϵ��ַ</td>
		<td><input type="text" name="address" maxlength="150" size="40" value="<%= userinfo.getAddress() %>" ></td></tr> 
	  <tr><td align=right width=100>��ϵ�绰</td>
		<td><input type="text" name="phone" maxlength="25" size="16" value="<%= userinfo.getPhone() %>" ></td></tr> 
	  <tr><td align=right width=100>�ʱ�</td>
		<td><input type="text" name="post" maxlength="8" size="8" value="<%= userinfo.getPost() %>" ></td></tr> 
	  <tr><td align=right width=100>�����ʼ�</td>
		<td><input type="text" name="email" maxlength="50" size="25" value="<%= userinfo.getEmail() %>" ></td></tr> 
	  <tr><td align=right width=100>&nbsp;</td>
		<td><INPUT TYPE="hidden" name="userid" value="<%= userinfo.getId() %>">
			<INPUT TYPE="submit" name="submit" value="�޸�" onclick="return(checkform());">
			<INPUT TYPE="reset" name="reset" value="ȡ��"></td></tr> 
	
  </table>
  </FORM>
  <br>
<%}%>          
        <p>&nbsp;</p>
        </td>
    </tr>
  </table>

 <jsp:include flush="true" page="bottom.jsp"></jsp:include>
  
</div>
</body>
</html>
