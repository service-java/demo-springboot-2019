<%@ page language="java" contentType="text/html;charset=GB2312"%>

<html>
  <head>
    <title>�ɹ�ͨ��������У��</title>
    <meta http-equiv="Content-Type" content="text/html;charset=GB2312">
  </head>
  <body>
  <center>

  <h2>���ύ�����ݣ�</h2>
  
  <form>
	<table>
	  <tr>
		<td>������</td>
		<td>
		  <input name="name" type="text" value=<%=request.getParameter("name")%> readonly="true">
		</td>
	  </tr>
	  <tr>
		<td>���䣺</td>
		<td>
		  <input name="age" type="text" value=<%=request.getParameter("age")%> readonly="true">
		</td>
	  </tr>
	</table>
  </form>
  
  <h2><font color="#FF0000">���ݳɹ�ͨ���˹�����У�飡</font></h2>
  
  </center>
  </body>
</html>
