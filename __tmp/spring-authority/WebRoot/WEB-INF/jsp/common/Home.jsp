<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html  lang="en-au">
<head>
    <title>首页</title>
    <meta charset="utf-8">
    <meta http-equiv="content-type" content="text/html;charset=UTF-8"/>
    <jsp:include page="header.jsp"/>
    
</head>
<body class="easyui-layout" style="width: 100%;height: 100%;" data-options="fit:true">
<div region="north" border="false" class="app-header">

    <div class="app-header-menu">

    </div>
</div>
</div>
<div region="west" title="导航栏" class="easyui-default" style="width: 240px;background: #f8f8f8;padding-left: 10px;"
     data-options="tools:'#main-tree-tools'">
    <ul class="easyui-tree" id="main_menu" style="padding-top: 15px;"
        data-options="url:'admin/rolemenu/load',method:'get',animate:true,cache:false,lines:false">
    </ul>
    <div id="main-tree-tools">
        <a href="javascript:void(0)" class="icon-arrow-in" title="展开全部" onclick="mainTreeExpandAll()"></a>
        <a href="javascript:void(0)" class="icon-arrow-out" title="收缩全部" onclick="mainTreeUnExpandAll()"></a>
    </div>
</div>
<div region="center" border="false" style="padding-left:5px;" >
    <div class="easyui-tabs" id="main_tab" fit="true">
        <div title="欢迎" iconCls='icon-home' style="width: 100%;">

        </div>
    </div>
</div>
<div region="south" style="height: 28px;text-align: center;background: #e0ecff;"
     border="false">
</div>
<div id="change_user_password_dialog" >

</div>
<div class="easyui-dialog" id="hos_user_info_dialog" data-options="
    noheader:true,
    width: 800,
    height: 500,
    closed:false,
    cache:false,
    border:false,
    maximized:true,
    closed:true,
    modal: true"
>

</div>
<div id="sys_loading_dialog" class="easyui-dialog" data-options="noheader:true,modal:true,closed:true"
     style="width: 200px;height:48px;padding: 2px;">
    <span style="line-height: 30px;text-indent:5px;vertical-align: middle;display: block;float: left;">在拼命加载中……</span>
</div>
<script type="text/javascript">
        function showMessager(content) {
            $.messager.show({
                title: '提示',
                msg: content,
                timeout: 2000,
                showType: 'fade',
                style: {
                    right: '',
                    top: document.body.scrollTop + document.documentElement.scrollTop,
                    bottom: ''
                }
            })
        }
</script>
    
</body>
</html>