<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<base href="<%=basePath%>">
<meta http-equiv="X-UA-Compatible" content="IE=Edge;chrome=1">
<c:set var="version" value="1.3.0"/>
<link rel="stylesheet" id="easyui_theme" type="text/css"
      href="public/style/EasyUI/v1_3_2/themes/default/easyui.css?${version}">
<link rel="stylesheet" type="text/css" href="public/style/EasyUI/v1_3_2/themes/icon.css">
<link rel="stylesheet" type="text/css" href="public/style/EasyUI/v1_3_2/themes/demo.css">
<link rel="stylesheet" type="text/css" href="public/style/EasyUI/v1_3_2/themes/color.css">


<script type="text/javascript" src="public/scripts/jquery-gantt/js/jquery-1.7.min.js"></script>
<script>
    JQuery7 = $.noConflict();
</script>
<script type="text/javascript" src="public/scripts/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="public/scripts/jquery.cookie.js"></script>

<script type="text/javascript" src="public/scripts/common/home.js?${version}"></script>
<script type="text/javascript" src="public/scripts/common/common.js?${version}"></script>
<script type="text/javascript" src="public/scripts/sys_formatter.js?${version}"></script>
<script type="text/javascript" src="public/scripts/EasyUI/v1_3_2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="public/scripts/EasyUI/v1_3_2/src/jquery.combobox.js?${version}"></script>
<script type="text/javascript" src="public/scripts/EasyUI/v1_3_2/locale/easyui-lang-zh_CN.js"></script>

<script type="text/javascript" src="public/scripts/wdatepicker/WdatePicker.js"></script>
<script type="text/javascript" src="public/scripts/jquery-gantt/js/jquery.fn.gantt.js"></script>
