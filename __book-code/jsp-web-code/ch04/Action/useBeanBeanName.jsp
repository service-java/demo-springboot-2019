<%@ page contentType="text/html; charset=gb2312"%>
<html>
<head>
<title>jsp:useBeanBeanName��ʾ</title>
</head>
<body>
   <h1><jsp:useBean id="clock" type="java.io.Serializable" beanName="java.util.Date"/>
   ����ʱ�䣺<%=clock %>
</body>
</html>