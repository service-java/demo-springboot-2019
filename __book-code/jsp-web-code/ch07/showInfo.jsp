<%@ page contentType="text/html;charset=gb2312" language="java"  %>
<% request.setCharacterEncoding("gb2312");%> 
<html>
<head>
  <title>��ʾ�û���Ϣ</title>
</head>
<body>
<jsp:useBean id="user" scope="session" class="jsp.test.show" />
<jsp:setProperty name="user" property="*" />
<% if(request.getParameter("name")==null){%>
<form name="Example" method="post" action="">
<p> ������<input type="text" name="name" size="15" maxlength="15"></p>
<p> ���룺<input type="password" name="password" size="15" maxlength="15"></p>
<p> �Ա�<input type="radio" name="sex" value="Male" checked>�� 
         <input type="radio" name="sex" value="Female">Ů
</p>
<p>���䣺 
    <select name="age">
      <option value="10">10 ~ 20</option>
      <option value="20" selected>21 ~ 30</option>
      <option value="30">31 ~ 40</option>
      <option value="40">41 ~ 65</option>
    </select>
</p>
<p>��Ȥ�� 
    <input type="checkbox" name="habit" value="Read">
    ���� 
    <input type="checkbox" name="habit" value="Football">
    ����
    <input type="checkbox" name="habit" value="Travel">
    ���� 
    <input type="checkbox" name="habit" value="Music">
    ������ 
    <input type="checkbox" name="habit" value="Tv">
    ������</p>
<p> 
<input type="submit" value="����">
<input type="reset" value="���">
</p>
</form>
<%}else{%>
������<%=user.getName()%><br>
���룺<%=user.getPassword() %><br>
�Ա�<%=user.getSex() %><br>
���䣺<%=user.getAge() %><br>
����: <%=user.getHobby() %>
<%} %>
</body>
</html>

