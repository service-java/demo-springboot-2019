<%request.setCharacterEncoding("gb2312");%>
<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>Itzcn���</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
  <!-- ============= header start ============= -->
  <div id="header">
    <div id="banner">
      <h1 class="logo">Itzcn���</h1>
    </div>
    <!-- ============= �˵���(��ʼ) ============= -->
    <div id="menubar">
      <ul>
        <li class="active_menu"><a href="#">��ҳ</a></li>
        <li><a href="#">�������</a></li>
        <li><a href="#">�����б�</a></li>
        <li><a href="#">ͼ��ר��</a></li>
        <li><a href="#">�ҵ��˺�</a></li>
        <li><a href="#">�û�ע��</a></li>
        <li><a href="#">��ϵ����</a></li>
        <li><a href="#">�˲���Ƹ</a></li>
      </ul>
    </div>
    <!-- ============= �˵���(����) ============= -->
  </div>
  <!-- ============= header end ============= -->
  <div id="body" class="clear">
    <!-- ============= body_left start ============= -->
    <div id="body_left">
      <!-- ============= �����Ƽ�����ʼ�� ============= -->
      <h2><img src="imgs/bullet1.gif" />�����б�</h2>
      <div class="prod clear">
<% List<String> list=(List) request.getAttribute("list");%> 
  <table border="0" cellpadding="4" cellspacing="1" bgcolor="#CBD8AC" width="100%">
   <tr align="center" bgcolor="#FAFAF1" >
   	<td>�ļ�����</td>
   	<td>�ϴ�ʱ��</td>
   	<td>�ļ���С</td>
   	<td>��    ��</td>
   </tr>
   <%for (String str :list){%>
   <tr align="center">
   <%
   String param[] = str.split("#");
  %>
  <td align="center"><%=param[1]%></td>
  <td align="center"><%=param[2]%></td>
  <td align="center"><%=param[3]%></td>
   <td align="center"><a href ="download.jsp?path=<%=param[0]%>">����</a></td>
  </tr><%
}%></table>

      </div>
      <!-- ============= �����Ƽ��������� ============= -->

    </div>
    <!-- ============= body_left end ============= -->
    <div id="body_right">
      <div id="cart" class="clear">
        <h2><img src="imgs/cart.gif" width="21" height="18" />���ﳵ</h2>
        <div class="info">2����Ʒ | <span>�ܼƣ�123��</span><a href="#">�鿴���ﳵ</a></div>
      </div>
      <img src="imgs/border.gif" width="329" height="31" />
      <div id="about_our">
        <h2><img src="imgs/bullet3.gif" width="26" height="25" />�������</h2>
        <div class="details clear"> <img src="images/about.jpg" />
          <p>�������ǿƼ���˾��עIT������ѵ�г��ķ�չ�ͱ仯�����������߽������ƽ���ֽ����ѧ���̡���������www.itzcn.com��������������ʵ�����󣬷�����Flex��C#��Java��ASP.NET��

JSP��ϵ����Ƶ�̡̳�</p>
        </div>
      </div>
      <img src="imgs/border.gif" width="329" height="31" />
      <div id="promotions">
        <h2><img src="imgs/bullet4.gif" width="26" height="25" />ͼ�����</h2>
        <ul class="list">
          <li><a href="#">��ά����</a></li>
          <li><a href="#">.NET����</a></li>
          <li><a href="#">Java����</a></li>
          <li><a href="#">PHP����</a></li>
          <li><a href="#">��������</a></li>
          <li><a href="#">Webǰ��</a></li>
          <li><a href="#">���ݿ⼼��</a></li>
          <li><a href="#">����Ӧ��</a></li>
        </ul>
      </div>
      <div class="right_box">
        <h2><img src="imgs/bullet6.gif" width="26" height="25" />��������</h2>
        <ul class="list">
          <li><a href="#">������</a></li>
          <li><a href="#">������̳</a></li>
          <li><a href="#">Google����</a></li>
          <li><a href="#">�ٶ�����</a></li>
        </ul>
      </div>
    </div>
  </div>
  <!-- ============= footer start ============= -->
  <div id="footer">
    <div class="logo"> <img src="imgs/logo.png" /> <img src="imgs/itzcn.gif" /> </div>
    <div class="nav">
      <ul>
        <li><a href="#">��ҳ</a></li>
        <li><a href="#">��������</a></li>
        <li><a href="#">����</a></li>
        <li><a href="#">����Э��</a></li>
        <li><a href="#">��ϵ����</a></li>
      </ul>
    </div>
    <img src="imgs/footer_bg.gif" /> </div>
  <!-- ============= footer end ============= -->
</div>
</body>
</html>