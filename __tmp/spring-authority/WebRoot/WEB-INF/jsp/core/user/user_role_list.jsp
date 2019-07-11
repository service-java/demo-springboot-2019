<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: tony
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<table id="user_role_datagrid" class="easyui-datagrid"
       data-options="idField: 'id',toolbar:'#user_role_tools',striped:true,rownumbers:true,border:false,singleSelect:true"
       pagination="true" fitColumns="true" fit="true" style="width: 100%;height: 100%;"  pageSize="30">
    <thead style="width: 100%;">
    <tr fit="true">
        <th data-options="checkbox:true,width:0.1"></th>
        <th data-options="field:'roleName',width:0.2">角色名称</th>
        <th data-options="field:'roleDesc',width:0.2">角色说明</th>
        <th data-options="field:'enabled',width:0.2,formatter:roleenabledReader">是否有效</th>
        <th data-options="field:'createDate',width:0.2">创建日期</th>
    </tr>
    </thead>
</table>
<div id="user_role_tools" style="text-align: right;" data-options="closed:true,width:600,height:400,modal:true,,iconCls:'icon-role'">
    <security:authorize ifAllGranted="AUTH_USER_ROLE_ADD">
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true"
           onclick="addUserRole();">添加</a>
    </security:authorize>
    <security:authorize ifAllGranted="AUTH_USER_ROLE_DELETE">
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-del',plain:true"
           onclick="delUserRole()">删除</a>
    </security:authorize>


</div>