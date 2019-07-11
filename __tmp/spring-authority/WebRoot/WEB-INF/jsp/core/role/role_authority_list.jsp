<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: tony
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<table id="role_authority_datagrid" class="easyui-datagrid"
       data-options="toolbar:'#role_authority_res_tools',rownumbers:true,border:false,striped:true,singleSelect:true"
       pagination="true"  fitColumns="true" fit="true" style="width: 100%;height: 100%;"  pageSize="30">
    <thead style="width: 100%;">
    <tr fit="true">
        <th data-options="checkbox:true,width:0.1"></th>
        <th data-options="field:'authorityName',width:0.2">权限名称</th>
        <th data-options="field:'authorityCode',width:0.2">权限编码</th>
        <th data-options="field:'authorityDesc',width:0.2">角色说明</th>
        <th data-options="field:'createDate',width:0.2">添加时间</th>
    </tr>
    </thead>

</table>
<div id="role_authority_res_tools" style="text-align: right;">
    <security:authorize ifAllGranted="AUTH_ROLE_AUTHORITY_ADD">
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true"
           onclick="addRoleAuthority();">添加</a>
    </security:authorize>
    <security:authorize ifAllGranted="AUTH_ROLE_AUTHORITY_DELETE">
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-del',plain:true"
           onclick="delRoleAuthority()">删除</a>
    </security:authorize>


</div>