<%--
  Created by IntelliJ IDEA.
  User: tony
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<table id="sys_authority_datagrid_select" class="easyui-datagrid"
       data-options="idField: 'id',url:'admin/authority/list',method:'post',striped:true,rownumbers:true,border:false,singleSelect:false"
       pagination="true"  fitColumns="true" fit="true" style="width: 100%;height: 100%;" pageSize="30">
    <thead style="width: 100%;">
    <tr fit="true">
        <th data-options="checkbox:true,width:0.1"></th>
        <th data-options="field:'authorityName',width:0.2">权限名称</th>
        <th data-options="field:'authorityCode',width:0.2">权限编码</th>
        <th data-options="field:'username',width:0.2">创建人</th>
        <th data-options="field:'createDate',width:0.2">添加时间</th>
    </tr>
    </thead>
</table>