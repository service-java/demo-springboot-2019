<%@ page contentType="text/html; charset=gb2312" %>
<%@ page import="java.util.*" %>
<%@ page import="org.pan.web.book.goodsclass" %>
<%@ page session="true" %>
<jsp:useBean id="classlist" scope="page" class="org.pan.web.goodsclasslist" />
<html>
<head>
<title>����������ҳ</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">

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
      <td width="100">&nbsp;</td>
      <td width="100"><a href="goodslist.jsp">���߹���</a></td>
      <td width="100"><a href="shoperlist.jsp">�ҵĹ��ﳵ</a></td> 
      <td align="center" width="80">
        <a href="manage/login.jsp">����Ա��½</a></td>
	  <td align="right">��ǰʱ��:<%= (new java.util.Date()).toLocaleString() %> &nbsp;</td>
  </tr>
</table>
  <table width="592" border="0" cellspacing="1" cellpadding="1">
    <tr valign="top"> 
      <td width="186">  ������Ʒ���ࣺ<marquee direction="up" scrollamount=3>
     
        <table width="100%" border="0" cellspacing="1" cellpadding="1">
          <tr> 
            <td width="20">&nbsp;</td>
            <td></td>
          </tr>
          <% if (classlist.excute()){
				for (int i=0;i<classlist.getClasslist().size();i++){
					goodsclass bc = (goodsclass) classlist.getClasslist().elementAt(i); %>	
          <tr> 
            <td width="20">&nbsp;</td>
            <td><a href="goodslist.jsp?classid=<%= bc.getId()%>"><%= bc.getClassName() %></a></td>
          </tr>
          <%		}	}%>
	
          <tr> 
            <td width="20">&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
        </table></marquee>
       <p>�������ӣ�</p>
       <p><img src="images/icon1.jpg"  ></p>
         <p><img src="images/icon2.jpg" ></p>
      </td>
      <td width="387">������Ʒ��Ʒ 
        <table width="95%" border="0" cellspacing="1" cellpadding="1">
          <tr> 
            <td rowspan="2" align="center" width="49%"><img src="images/ecommernce_thumb.jpg" width="126" height="97"></td>
            <td width="51%">��������Ʒ�Ƽ�</td>
          </tr>
          <tr> 
            <td width="51%"> ���ż���<br>
              ����� </td>
          </tr>
          <tr> 
            <td colspan="2"> 
              <p>���������飺

������������Ϣ���Ĳ�������˵��Internet�Ĳ��û�м��������Ͳ����е�������

��������Դ��Ӣ��ELECTRONIC COMMERCE����дΪEC������˼�壬�����ݰ����������棬һ�ǵ��ӷ�ʽ��������ó���
�й����ŵ���������ָ����TCP/IP��������ͼ������е����߽��׺�������ҵ���
�漰������������ڻ������̼ҡ�������ҵ����������ṩ�̡� �����û����������ź���ҵ��λ�ȡ�
			  </p>

            </td>
          </tr>
        </table>
        <p>&nbsp;</p>
      </td>
      <td width="210" align="center"> <br>
        <form name="form1" method="post" action="login.jsp">
          �û���¼ 
          <table width="100%" border="0" cellspacing="1" cellpadding="1">
            <tr> 
              <td align="right" width=50>�û�����</td>
              <td> 
                <input type="text" name="username" size="10" maxlength="25">
              </td>
            </tr>
            <tr> 
              <td align="right"width=50>�� �룺</td>
              <td> 
                <input type="password" name="passwd" size="10" maxlength="25">
              </td>
            </tr>
          
            <tr> 
              <td colspan="2" align="center"> 
                <input type="submit" name="Submit" value="��¼">
                <input type="reset" name="Submit2" value="ȡ��">
              </td>
            </tr>
            <tr> 
              <td colspan="2">������Ǳ�վ��Ա,���ڴ�<a href="reg.jsp">ע��</a>��</td>
            </tr>
          </table>
        </form>
        <p>&nbsp;</p>
       <p><img src="images/icon.png" width="120" height="60"></p>
        </td>
    </tr>
  </table>
 <jsp:include flush="true" page="bottom.jsp"></jsp:include>
  
</div>
</body>
</html>
