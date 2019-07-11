<%@ page contentType="text/html; charset=gb2312" %>
<%@ page import="java.util.*" %>
<%@ page import="org.pan.web.book.goodsclass" %>
<%@ page session="true" %>
<%@ page import="org.pan.web.book.goods"%>
<jsp:useBean id="book" scope="page" class="org.pan.web.book.goods" />
<jsp:useBean id="book_list" scope="page" class="org.pan.web.goodsmn" />
<jsp:useBean id="classlist" scope="page" class="org.pan.web.goodsclasslist" />
<%
int pages=1;
String mesg = "";

if (request.getParameter("page")!=null && !request.getParameter("page").equals("")) {
	String requestpage = request.getParameter("page");	
	try {
		pages = Integer.parseInt(requestpage);
	} catch(Exception e) {
		mesg = "��Ҫ�ҵ�ҳ�����!";
	}
	book_list.setPage(pages);
}
String classid = request.getParameter("classid");
String classname ="";
String keyword = request.getParameter("keyword");
if (classid==null) classid="";
if (keyword==null) keyword="";
keyword = book_list.getGbk(keyword);
%>

<html>
<head>
<title>��������ѡ����Ʒ</title>
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
  <table width="750" border="0" cellspacing="1" cellpadding="1">
    <tr> 
      <td align="center"><img src="images/baners2.jpg" width="592" ><br></td>    
   
    </tr>
  </table>

  <table width="592" border="0" cellspacing="1" cellpadding="1">
    <tr> 
      <td width="100">&nbsp;</td>
      <td width="55"><a href="index.jsp">��ҳ</a></td>
      <td width="100"><a href="goodslist.jsp">���߹���</a></td>
      <td width="100"><a href="shoperlist.jsp">�ҵĹ��ﳵ</a></td>
      <td width="100"><a href="userinfo.jsp">�û���Ϣ</a></td>
      <%  String username = (String)session.getAttribute("username");
if ( username == null || username.equals("") ){%>
      <td><a href="login.jsp">�û���¼</a></td>
      <%}else { %>
      <td><a href="logout.jsp">�û��˳�</a></td>
      <%} %>
    </tr>
  </table>
  <table width="592" border="0" cellspacing="1" cellpadding="1">
    <tr valign="top"> 
      <td width="186"> 
        <table width="100%" border="0" cellspacing="1" cellpadding="1">
          <tr> 
           
            <td>������Ʒ���ࣺ</td>
          </tr>
          <% if (classlist.excute()){
				for (int i=0;i<classlist.getClasslist().size();i++){
					goodsclass bc = (goodsclass) classlist.getClasslist().elementAt(i); 
					if ( classid.equals(Integer.toString(bc.getId())) ) classname=bc.getClassName();
					%>
          <tr> 
          
            <td><a href="goodslist.jsp?classid=<%= bc.getId()%>"><%= bc.getClassName() %></a></td>
          </tr>
          <%		}			
		} %>
          <tr> 
           
            <td>&nbsp;</td>
          </tr>
        </table>
		 <p>�������ӣ�</p>
       <p><img src="images/icon1.jpg"  ></p>
         <p><img src="images/icon2.jpg" ></p>
      </td>
      <td align="center"> 
      <form name=form1 METHOD=POST ACTION="goodslist.jsp">
      <table width="100%" border="0" cellspacing="1" cellpadding="1">
		  
          <tr> 
            <td align=center><b>��Ʒ��ѯ��</b></td>
          </tr>
		  <tr> 
            <td>�ؼ��֣�<INPUT TYPE="text" NAME="keyword" size=8 maxlength=40 value="<%= keyword %>">
        
		 ���:
		    <SELECT NAME="classid">
				<option value="">�������</option>			
		 
		  <%
				for (int i=0;i<classlist.getClasslist().size();i++){
					goodsclass bc = (goodsclass) classlist.getClasslist().elementAt(i); %>		    
            <option value="<%= bc.getId()%>"><%= bc.getClassName() %></option>
          <%		}			
%></SELECT>
		   <INPUT TYPE="submit" name="submit" value="��ѯ" ></td>
          </tr>		  		 
        </table>
         </form>
        <p><br>
        
          <b><font color="#0000FF">����������Ʒ<%= classname %>�б�</font></b></p>
		  <%if (!keyword.equals("")) out.println("<p ><font color=#ff0000>��Ҫ���ҹ���&nbsp;" + keyword + "&nbsp;����Ʒ����</font></p>"); %>
        <table width="100%" border="1" cellspacing="1" cellpadding="1" bordercolor="#CC9966">
          <tr align="center"> 
            <td>��Ʒ����</td>
            <td>����Ա</td>
            <td>��Ʒ���</td>
            <td>������</td>
            <td>����</td>
            <td width=110>ѡ��</td>
          </tr>
<% if (book_list.execute(request)) {
	if (book_list.getGoodslist().size()>0 ){
		for (int i=0;i<book_list.getGoodslist().size();i++){
			goods bk = (goods) book_list.getGoodslist().elementAt(i);%>
          <tr>
            <td><%= bk.getGoodsName() %></td>
            <td align="center"><%= bk.getAuthor() %></td>
            <td align="center"><%= bk.getClassname() %></td>
            <td align="center"><%= bk.getPublish() %></td>
            <td align="center"><%= bk.getPrince() %>Ԫ</td>
            <td align="center"><a href="#" onclick="openScript('purchase.jsp?bookid=<%= bk.getId() %>','pur',300,250)" >����</a>&nbsp;
			<a href="#" onclick="openScript('showgoods.jsp?bookid=<%= bk.getId() %>','show',400,450)" >��ϸ����</a></td>
          </tr>
<%		} 
	}else { 
		if (keyword.equals("")){
			out.println("<tr><td align='center' colspan=6>&nbsp;��ʱû�д�����Ʒ����</td></tr>");
		} else {
			out.println("<tr><td align='center' colspan=6>&nbsp;û����Ҫ���ҵ�&nbsp;" + keyword + "&nbsp;�����Ʒ</td></tr>")	;
		}
	}
} else {%>
          <tr>            
            <td align="center" colspan=6>&nbsp;���ݿ�������Ժ�</td>
            
          </tr>
<% } %>

        </table>
        <table width="90%" border="0" cellspacing="1" cellpadding="1">
          <tr>
            <td align="right">�ܼƽ��Ϊ<%= book_list.getRecordCount() %>������ǰҳ��<%= book_list.getPage() %>ҳ��<a href="goodslist.jsp?classid=<%= classid%>&keyword=<%= keyword %>">��ҳ</a>&nbsp; 
              <% if (book_list.getPage()>1) {%>
              <a href="goodslist.jsp?page=<%= book_list.getPage()-1 %>&classid=<%= classid%>&keyword=<%= keyword %>">��һҳ</a>&nbsp; 
              <% } %>
              <% if (book_list.getPage()<book_list.getPageCount()-1) {%>
              <a href="goodslist.jsp?page=<%= book_list.getPage()+1 %>&classid=<%= classid%>&keyword=<%= keyword %>">��һҳ</a>&nbsp; 
              <% } %>
              <a href="goodslist.jsp?page=<%= book_list.getPageCount() %>&classid=<%= classid%>&keyword=<%= keyword %>">δҳ</a>&nbsp;</td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
 <jsp:include flush="true" page="bottom.jsp"></jsp:include>
  
</div>
</body>
</html>
<% book_list.close();%>