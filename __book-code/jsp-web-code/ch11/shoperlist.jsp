<%@ page contentType="text/html; charset=gb2312" %>
<%@ page import="java.util.*" %>
<%@ page import="org.pan.web.book.goodsclass" %>
<%@ page session="true" %>
<%@ page import="org.pan.web.book.goods"%>
<%@ page import="org.pan.web.goodsmn" %>
<%@ page import="org.pan.web.book.indentlist" %>
<jsp:useBean id="book_list" scope="page" class="org.pan.web.goodsmn" />
<jsp:useBean id="classlist" scope="page" class="org.pan.web.goodsclasslist" />
<jsp:useBean id="shop" scope="page" class="org.pan.web.purchase" />

<% 
String userid = (String) session.getAttribute("userid");
if ( userid == null )
	userid = "";
String modi = request.getParameter("modi");
String del = request.getParameter("del");
String payoutCar = request.getParameter("payout");
String clearCar = request.getParameter("clear");
String mesg = "";

if (modi!=null && !modi.equals("")) {
	if ( !shop.modiShoper(request) ){
		if (shop.getIsEmpty())
			mesg = "��Ҫ���޸Ĺ������Ʒ����������Ĺ�������!";
		else
			mesg = "�޸Ĺ�����������";
	} else {
		mesg = "�޸ĳɹ�";
	}

}else if ( del != null && !del.equals("") ) {
	if ( !shop.delShoper(request) ) {
		mesg = "ɾ���嵥�е���Ʒʱ����" ;
	}
}else if (payoutCar != null && !payoutCar.equals("") ) {
	if (shop.payout(request) ) {
		mesg = "��Ĺ��ﳵ�е���Ʒ���ύ�����꣬��Ķ�����Ϊ "+ shop.getIndentNo() + "<br>�뼰ʱ����Ա����Ƿ���!";
		session.removeAttribute("shopcar");
	} else {
		if(!shop.getIsLogin())
			mesg = "�㻹û�е�¼������<a href=login.jsp>��¼</a>�����ύ";
		else
			mesg = "�Բ����ύ�������Ժ�����"; 
	}	
} else if (clearCar != null && ! clearCar.equals("") ) {
	session.removeAttribute("shopcar");
	mesg = "���ﳵ�е���Ʒ�嵥�����";
}


%>

<html>
<head>
<title>��������--�ҵĹ��ﳵ</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<script language="javascript">

function openScript(url,name, width, height){
	var Win = window.open(url,name,'width=' + width + ',height=' + height + ',resizable=1,scrollbars=yes,menubar=no,status=yes' );
}
function checklogin() {
	if (document.payout.userid.value=="")
	{
		alert("�㻹û�е�¼�����¼�����ύ�����嵥�ء�");
		return false;
	}
	return true;
}

function check()
{
	if (document.change.amount.value<1){
		alert("��Ĺ�������������");
		document.change.amount.focus();
		return false;
	}
	return true;
}

</script>
<link rel="stylesheet" href="books.css" type="text/css">
</head>

<body  text="#000000">
<div align="center">
  <table width="750" border="0" cellspacing="1" cellpadding="1">
    <tr> 
      <td align="center"><img src="images/baners2.jpg" width="592" ></td>
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
      <td width="150"> 
        <table width="100%" border="0" cellspacing="1" cellpadding="1">
          <tr> 
         
            <td>������Ʒ���ࣺ</td>
          </tr>
  <% if (classlist.excute()){
		for (int i=0;i<classlist.getClasslist().size();i++){
			goodsclass bc = (goodsclass) classlist.getClasslist().elementAt(i); %>
          <tr> 
           
            <td><a href="goodslist.jsp?classid=<%= bc.getId()%>"><%= bc.getClassName() %></a></td>
          </tr>
  <%	}			
  }%>
          <tr> 
       
            <td>&nbsp;</td>
          </tr>
        </table>
        <p>�������ӣ�</p>
       <p><img src="images/icon1.jpg"  ></p>
         <p><img src="images/icon2.jpg" ></p>
      </td>
      <td align="center"> 
        <p><br>
         <b><font color="#0000FF">�ҵĹ��ﳵ��Ʒ�嵥</font></b></p>
<%
if (!mesg.equals("") )
	out.println("<p ><font color=#ff0000>" + mesg + "</font></p>");

Vector shoplist = (Vector) session.getAttribute("shopcar");
if (shoplist==null || shoplist.size()<0 ){
	if (mesg.equals(""))
		out.println("<p><font color=#ff0000>�㻹û��ѡ������Ʒ�����ȹ���</font></p>");
} else {
%>
       <table width="100%" border="1" cellspacing="1" cellpadding="1" bordercolor="#CC9966">
          <tr align="center"> 
            <td>��Ʒ����</td>
            <td>����Ա</td>
            <td>��Ʒ���</td>
            <td>����(Ԫ)</td>
            <td>����</td>
            <td colspan =2>ѡ��</td>
          </tr>
	<% 
	float totalprice =0;
	int totalamount = 0;
	for (int i=0; i<shoplist.size();i++){
		indentlist iList = (indentlist) shoplist.elementAt(i);	
		if (book_list.getOnebook((int)iList.getGoodsNo())) {
			goods bk = (goods) book_list.getGoodslist().elementAt(0);
			totalprice = totalprice + bk.getPrince() * iList.getAmount();
			totalamount = totalamount + iList.getAmount();
	%>
          <tr>
            <td><%= bk.getGoodsName() %></td>
            <td align="center"><%= bk.getAuthor() %></td>
            <td align="center"><%= bk.getClassname() %></td>
            <td align="center"><%= bk.getPrince() %></td>
		    <form name="change" method="post" action="shoperlist.jsp">
            <td align="center">
              <input type="text" name="amount" maxlength="4" size="3" value="<%= iList.getAmount() %>" >			  
            </td>
            <td align="center" width=55 >
			<input type="hidden" name="bookid" value="<%= iList.getGoodsNo() %>" >
              <input type="submit" name="modi" value="�޸�" onclick="return(check());"></td>
              </form>
			<form name="del" method="post" action="shoperlist.jsp">
			 <input type="hidden" name="bookid" value="<%= iList.getGoodsNo() %>" >
			 <td align=center width=55> <input type="submit" name="del" value="ɾ��">
            </td></form>
          </tr>
		<% } 
	} %>  <tr><td colspan=7 align="right"><br>��ѡ�����Ʒ���ܽ��:<%= totalprice%>Ԫ&nbsp;&nbsp;��������<%= totalamount%>&nbsp;</td></tr>
        </table>
       <p></p>
       <form name="payout" method="post" action="shoperlist.jsp">
          <table width="90%" border="0" cellspacing="1" cellpadding="1">
            <tr> 
              <td align="right" valign="bottom"> <a href="goodslist.jsp">��������</a>&nbsp;
                
				<input type="hidden" name="userid" value="<%= userid %>">
				<input type="hidden" name="totalprice" value="<%= totalprice %>">
				<TEXTAREA NAME="content" ROWS="3" COLS="20">���ԣ�</TEXTAREA><br>
				<input type="submit" name="payout" value="�ύ�ҵĹ��ﳵ" onclick="javascript:return(checklogin());">&nbsp;</td></form>
				<form name="form1" method="post" action="shoperlist.jsp">
			  <td valign="bottom">&nbsp;
                <input type="submit" name="clear" value="����ҵĹ��ﳵ">
              </td></form>
            </tr>
          </table>
        </form>
<% } %>
      </td>
    </tr>
  </table>
 <jsp:include flush="true" page="bottom.jsp"></jsp:include>
  
</div>
</body>
</html>
