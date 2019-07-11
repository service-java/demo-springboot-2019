<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="easyui-layout" style="width: 100%;height: 100%;" fit="true" border="false">
    <div  region="north" style="height:70px;" title="查询条件" border="false">
        <form  id="sys_loginlog_search_form">
            <%--<p>--%>
                <span>登陆账号：<input id="search_userAccount"></span>

            <%--</p>--%>
            <%--<p>--%>
                <span>登陆时间：从<input id="search_loginTimeStart" readonly="readonly" class="Wdate"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" ></span>

            <%--</p>--%>
            <%--<p>--%>
                <span>登陆时间：到<input id="search_loginTimeEnd" readonly="readonly" class="easyui-datebox"></span>

            <%--</p>--%>
            <%--<p>--%>
                <span>登陆IP：<input id="search_loginIp"></span>

            <%--</p>--%>
            <%--<p>--%>
               <span>客户端：<input id="search_loginClient"></span>

            <%--</p>--%>
            <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-find',plain:true"
               onclick="loginLogSearch();">查找</a>
        </form>
    </div>
    <div  region="center" border="false">
        <table class="easyui-datagrid" id="sys_loginlog_datagrid"
               data-options="idField: 'id',iconCls:'icon-log',striped:true,url:'admin/loginlog/list',method:'POST',rownumbers:true,striped:true,border:false,singleSelect:true"
               fitColumns="true" fit="true" style="width: 100%;height: 100%;" title="用户登陆日志"  pagination="true" pageSize="30">
            <thead>
            <tr fit="true">
                <th data-options="field:'userName',width:0.1,sortable:true">用户名</th>
                <th data-options="field:'loginTime',width:0.1,sortable:true">登陆时间</th>
                <th data-options="field:'logoutTime',width:0.1,sortable:true">退出时间</th>
                <th data-options="field:'loginIp',width:0.1,sortable:true">登陆IP</th>
                <th data-options="field:'loginPort',width:0.1,sortable:true">登陆端口</th>
                <th data-options="field:'loginLocalPort',width:0.1,sortable:true">本地端口</th>
                <th data-options="field:'userAgent',width:0.4,sortable:true">客户端</th>
            </tr>
            </thead>
        </table>
    </div>
</div>

<script type="text/javascript">
    var params;
    function loginLogSearch(){
        var userAccount = $("#search_userAccount").val();
        var loginTimeStart = $("#search_loginTimeStart").val();
        var loginTimeEnd = $("#search_loginTimeEnd").val();
        var loginClient = $("#search_loginClient").val();
        var loginIp = $("#search_loginIp").val();
        params = {
            params:{
                username:userAccount,
                logintimestart:loginTimeStart,
                logintimeend:loginTimeEnd,
                loginclient:loginClient,
                loginIp:loginIp
            }
        };
        $("#sys_loginlog_datagrid").datagrid({
            queryParams:params
        });
        $("#sys_loginlog_datagrid").datagrid("load");
    }
</script>