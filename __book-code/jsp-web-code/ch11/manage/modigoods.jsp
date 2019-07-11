<%@ page contentType="text/html; charset=gb2312" %>
<%@ page session="true" %>
<%
if (session.getAttribute("admin")==null || session.getAttribute("admin")==""){
//	response.sendRedirect("error.htm");
} %>
<%@ page import="org.pan.web.book.goodsclass" %>
<%@ page import="org.pan.web.book.goods " %>
<jsp:useBean id="classlist" scope="page" class="org.pan.web.goodsclasslist" />
<jsp:useBean id="book" scope="page" class="org.pan.web.goodsmn" />
<%
	String mesg = "";
	String submit = request.getParameter("Submit");
	int Id =0;
	if (submit!=null && !submit.equals("")){		
		if(book.getRequest(request)){
			if(book.update()){
				mesg = "��Ʒ�����޸ĳɹ���";
			} else {
				mesg = "���ݿ����ʧ��";
			}
		}else {
			mesg = "�Բ������ύ�Ĳ����д���";
		}
	}
	if (request.getParameter("id")==null || request.getParameter("id").equals("")) {
		mesg = "��Ҫ�޸ĵ����ݲ�������";
	} else {
		try {
			Id = Integer.parseInt(request.getParameter("id"));
			if (!book.getOnebook(Id)){
				mesg = "��Ҫ�޸ĵ����ݲ�����";
			}
		} catch (Exception e){
			mesg = "��Ҫ�޸ĵ����ݲ�������";
		}
	}
%>

<html>
<head>
<title>�����������ϵͳ</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<script language="javascript">
  function checkform() {
	if (document.form1.id.value=="") {
		alert("��Ҫ�޸ĵ����ݲ����ڣ�");
		return false;
	}
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
  <table width="100%" border="0" cellspacing="2" cellpadding="2">
 <tr> 
      
      <td colspan=2 align="center" ><img src="../images/backlogo.jpg" ></td>
    </tr>
    <tr>
      <td align="center" width="23%" valign="top"> 
        <jsp:include flush="true" page="list.jsp"></jsp:include>
      </td>
      <td align="center" width="77%"> 
        <p><br>
          <font size="3"><b>�޸���Ʒ����</b></font></p>
		  <% if(!mesg.equals("")){
			out.println(mesg);
		  } else {
			goods modibook = (goods) book.getGoodslist().elementAt(0);			  
			%>
        <form name="form1" method="post" action="modigoods.jsp">
          <table width="90%" border="0" cellspacing="1" cellpadding="1">
            <tr> 
              <td align="right" width="35%">��Ʒ���ƣ�</td>
              <td width="65%"> 
                <input type="text" name="bookname" maxlength="40" size="30" value="<%= modibook.getGoodsName() %>">
              </td>
            </tr>
            <tr> 
              <td align="right" width="35%">����Ա��</td>
              <td width="65%"> 
                <input type="text" name="author" maxlength="25" size="20" value="<%= modibook.getAuthor() %>">
              </td>
            </tr>
            <tr> 
              <td align="right" width="35%">�����̣�</td>
              <td width="65%"> 
                <input type="text" name="publish" size="40" maxlength="150" value="<%= modibook.getPublish() %>">
              </td>
            </tr>
            <tr> 
              <td align="right" width="35%">�������</td>
              <td width="65%"> 
                <select name="bookclass">
		<% if (classlist.excute()){
				for (int i=0;i<classlist.getClasslist().size();i++){
					goodsclass bc = (goodsclass) classlist.getClasslist().elementAt(i); %>
			      <option value="<%= bc.getId()%>" <% if (bc.getId() == modibook.getGoodsClass()) out.print("selected");%>><%= bc.getClassName() %></option>
		<%		}			
		}%>	
					
                </select>
              </td>
            </tr>
            <tr> 
              <td align="right" width="35%">��Ʒ��ţ�</td>
              <td width="65%"> 
                <input type="text" name="bookno" size="20" maxlength="30" value="<%= modibook.getGoodsNo() %>">
              </td>
            </tr>
            <tr> 
              <td align="right" width="35%">�۸�</td>
              <td width="65%"> 
                <input type="text" name="price" size="8" maxlength="10" value="<%= modibook.getPrince() %>">
                Ԫ</td>
            </tr>
            <tr> 
              <td align="right" width="35%">��������</td>
              <td width="65%"> 
                <input type="text" name="amount" size="8" maxlength="10" value="<%= modibook.getAmount() %>">
                </td>
            </tr>
            <tr> 
              <td align="right" width="35%" valign="top">��Ʒ��飺</td>
              <td width="65%"> 
                <textarea name="content" cols="40" rows="6"><%= modibook.getContent() %></textarea>
              </td>
            </tr>
            <tr> 
              <td align="right" width="35%">&nbsp;</td>
              <td width="65%"> 
				<input type="hidden" name="id" value="<%= Id %>">
                <input type="submit" name="Submit" value="�ύ" onclick="return(checkform());">
                <input type="reset" name="reset" value="����">
              </td>
            </tr>
          </table>
        </form> 
	<% } %>
        <p>&nbsp;</p>
      </td>
    </tr>
  </table>
  <br>
  <jsp:include flush="true" page="..\bottom.jsp"></jsp:include>
  
</div>
</body>
</html>
<% book.close();%>