<%@ page contentType="text/html; charset=gb2312" %>
<%@ page session="true" %>
<jsp:useBean id="alogin" scope="page" class="org.pan.web.login" />
<%
String mesg = "";

if( request.getParameter("username")!=null && !request.getParameter("username").equals("")){
	String username =request.getParameter("username");
	String passwd = request.getParameter("passwd");
	username = new String(username.getBytes("ISO8859-1"));
	passwd = new String(passwd.getBytes("ISO8859-1"));
	alogin.setUsername(username);
	alogin.setPasswd(passwd);
	alogin.setIsadmin(true);
	if (alogin.excute()){
		session.setAttribute("admin","admin"); %>
		<jsp:forward page="main.jsp" />			
<%	
	}else {
	mesg = "��¼����"	;
	}
}
%>

<html>
<head>
<title>�����������ϵͳ</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<script language="javascript">
  function checkform() {
	if (document.form1.username.value=="" || document.form1.passwd.value==""){
		alert("�û���������Ϊ�գ�");
		return false;
	}
	return true;

  }
</script>
</head>

<body  text="#000000">
<div align="center">
  <table width=592 border="0" height="100%" cellspacing="0" cellpadding="0" >
    <tr> 
      <td align="center"> 
        <form name="form1" method="post" action="login.jsp">
       
          <table width="360" border="1" cellspacing="2" cellpadding="2"  bordercolor="#CC33CC" style="font-size:9pt">
            <tr align="center"> 
              <td colspan="2"> 
                <h3><br>
                  <font color="#FF0000" size=6 face="����_gb2312">�����������ϵͳ</font></h3>
                <p>&nbsp;<% if (!mesg.equals("")){
						out.println(mesg);}%></p>
              </td>
            </tr>
            <tr> 
              <td align="right" width="100">����Ա��</td>
              <td> 
                <input type="text" name="username" size="12" maxlength="20">
              </td>
            </tr>
            <tr> 
              <td align="right" width="100">�� �룺</td>
              <td> 
                <input type="password" name="passwd" size="12" maxlength="20">
              </td>
            </tr>
            <tr align="center"> 
              <td colspan="2"> 
                <input type="submit" name="Submit" value="��¼" onclick="javascript:return(checkform());">
                <input type="reset" name="Submit2" value="ȡ��">
              </td>
            </tr>
            <tr align="center">
              <td colspan="2"><A HREF="../index.jsp">������ҳ</A></td>
            </tr>
          </table>
		 </form>
        <p>&nbsp;</p>
         <jsp:include flush="true" page="..\bottom.jsp"></jsp:include>
      </td>
    </tr>
  </table>
</div>
</body>
</html>
