<%@ page language="java" contentType="text/html; charset=GB2312"%>
<html>
<head>
<title>EL������������</title>
</head>
<body>
    ����URL:${pageContext.request.requestURL}<br>
    ͨ��Э�飺${pageContext.request.protocol}<br>
    ��������${pageContext.request.queryString}<br>
    
    ${'Hello '}  
    ${param.name} 
    ${'��ӭʹ�� JSP  ��̬��ҳ !'}  <br>
    
    accept-language��${header["accept-language"]}<br>      
    host��${header["host"]}<br>
    cookie��${header["cookie"]}<br>
    
    cookie cname��${cookie.cname} <br>
    cookie cname(value)��${cookie.cname.value} <br>
    
</body>
</html>