<%@ page contentType="text/html; charset=gb2312" %>
<%@ page session="true" %>
<%
if (session.getAttribute("admin")==null || session.getAttribute("admin")==""){
	response.sendRedirect("error.htm");
} %>
<%@ page import="org.pan.web.book.goodsclass"%>
<jsp:useBean id="classlist" scope="page" class="org.pan.web.goodsclasslist" />
<jsp:useBean id="good" scope="page" class="org.pan.web.goodsmn" />
<%
	String mesg = "";
	String submit = request.getParameter("Submit");
	if (submit!=null && !submit.equals("")){		
		if(good.getRequest(request)){
			if(good.insert()){
				mesg = "����Ʒ�����ύ�ɹ���";
			} else {
				mesg = "���ݿ����ʧ��";
			}
		}else {
			mesg = "�Բ������ύ�Ĳ����д���";
		}
	}
%>

<html>
<head>
<title>�����������ϵͳ</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<script language="javascript">
  function checkform() {
	if (document.form1.bookname.value=="") {
		document.form1.bookname.focus();
		alert("��Ʒ��Ϊ��!");
		return false;
	}
	if (document.form1.author.value=="") {
		alert("����Ա��Ϊ��!");
		document.form1.author.focus();
		return false;
	}

	return true;

  }
</script>
<link rel="stylesheet" href="books.css" type="text/css">
</head>

<body  text="#000000">
<div align="center">
  <table width="592" border="0" cellspacing="2" cellpadding="2" >
    <tr > 
     
      <td colspan="2" align="center"><img src="../images/backlogo.jpg"  ></td>
    </tr>
    <tr>
      <td align="center" width="23%" valign="top"> 
       <jsp:include flush="true" page="list.jsp"></jsp:include>
      </td>
      <td align="center" width="77%"> 
        <p><br>
          <font size="3"><b>����µ���Ʒ����</b></font></p>
		  <% if(!mesg.equals("")){
			out.println(mesg);
		  }%>
        <form name="form1" method="post" action="addgoods.jsp">
          <table width="90%" border="0" cellspacing="1" cellpadding="1">
            <tr> 
              <td align="right" width="35%">��Ʒ���ƣ�</td>
              <td width="65%"> 
                <input type="text" name="bookname" maxlength="40" size="30">
              </td>
            </tr>
            <tr> 
              <td align="right" width="35%">����Ա��</td>
              <td width="65%"> 
                <input type="text" name="author" maxlength="25" size="20">
              </td>
            </tr>
            <tr> 
              <td align="right" width="35%">�����̣�</td>
              <td width="65%"> 
                <input type="text" name="publish" size="40" maxlength="150">
              </td>
            </tr>
            <tr> 
              <td align="right" width="35%">�������</td>
              <td width="65%"> 
                <select name="bookclass">
		<% if (classlist.excute()){
				for (int i=0;i<classlist.getClasslist().size();i++){
					goodsclass bc = (goodsclass) classlist.getClasslist().elementAt(i); %>
			      <option value="<%= bc.getId()%>"><%= bc.getClassName() %></option>
		<%		}			
		}%>	
					
                </select>
              </td>
            </tr>
            <tr> 
              <td align="right" width="35%">��Ʒ��ţ�</td>
              <td width="65%"> 
                <input type="text" name="bookno" size="20" maxlength="30">
              </td>
            </tr>
            <tr> 
              <td align="right" width="35%">�۸�</td>
              <td width="65%"> 
                <input type="text" name="price" size="8" maxlength="10">
                Ԫ</td>
            </tr>
            <tr> 
              <td align="right" width="35%">��������</td>
              <td width="65%"> 
                <input type="text" name="amount" size="8" maxlength="10">
                </td>
            </tr>
            <tr> 
              <td align="right" width="35%" valign="top">��Ʒ��飺</td>
              <td width="65%"> 
                <textarea name="content" cols="40" rows="6"></textarea>
              </td>
            </tr>
            <tr> 
              <td align="right" width="35%">&nbsp;</td>
              <td width="65%"> 
                <input type="submit" name="Submit" value="�ύ" onclick="return(checkform());">
                <input type="reset" name="reset" value="����">
              </td>
            </tr>
          </table>
        </form>
        <p>&nbsp;</p>
      </td>
    </tr>
  </table>
  <br>
 <jsp:include flush="true" page="..\bottom.jsp"></jsp:include>
  
</div>
</body>
</html>
<% good.close();%>