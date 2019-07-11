<%--
  Created by IntelliJ IDEA.
  User: tony
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<table id="user_role_select_datagrid" class="easyui-datagrid"
       data-options="idField: 'id',url:'admin/role/list',method:'post',striped:true,rownumbers:true,border:false,singleSelect:false"
       pagination="true" fitColumns="true" fit="true" style="width: 100%;height: 100%;" pageSize="30">
    <thead style="width: 100%;">
    <tr fit="true">
        <th data-options="checkbox:true,width:0.1"></th>
        <th data-options="field:'roleName',width:0.2">角色名称</th>
        <th data-options="field:'roleDesc',width:0.2">角色说明</th>
        <th data-options="field:'authorityCount',width:0.2">权限数量</th>
        <th data-options="field:'enabled',width:0.3,formatter:roleenabledReader">是否有效</th>
        <th data-options="field:'createDate',width:0.2">创建日期</th>
    </tr>
    </thead>
</table>