<%@ page contentType="text/html;charset=GB2312" %>
<% 
 String str=request.getParameter("submit");
 if(str==null) {
 	str="";
 }
 if(str.equals("yes")) {
 	response.setContentType("application/msword;charset=GB2312");
 }
%> 
<HTML>
<head>
    <title>response����ʾ��</title>
</head>
<BODY>
<P>������ѧϰresponse����
<P>����ǰҳ�汣��Ϊword�ĵ���
<FORM method="get" name="form">
	<INPUT TYPE="submit" value="yes" name="submit">
</FORM>
</BODY>
</HTML>
