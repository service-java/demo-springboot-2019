<%@ page contentType="text/html;charset=gb2312"%>
<html>
<body>
<p>������Բ�İ뾶:</p>
<form action="dec-method.jsp" method="get" name="form"><input
	type="text" name="radius"> <input type="submit" name="submit"
	value="��ʼ����"></form>

<%!double area(double r) {
		return Math.PI * r * r;
	}

	double perimeter(double r) {
		return Math.PI * 2 * r;
	}%>
<%
	String str = request.getParameter("radius");
	if (str != null) {
		try {
			double r;
			r = Double.parseDouble(str);
%>

<p>Բ������ǣ�<%=area(r)%>
<p>Բ���ܳ��ǣ�<%=perimeter(r)%> <%
 			} catch (Exception e) {
 			out.print(e.getMessage());
 		}
 	}
 %>

</body>
</html>
