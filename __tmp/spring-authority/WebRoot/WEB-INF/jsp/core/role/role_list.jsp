<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: tony
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="public/scripts/core/role/role.js"></script>
<table id="role_datagrid" class="easyui-datagrid"
       data-options="idField: 'id',iconCls:'icon-role',striped:true,toolbar:'#sys_role_tools',url:'admin/role/list',method:'POST',rownumbers:true,striped:true,border:false,singleSelect:true"
       pagination="true" title="系统角色列表" fitColumns="true" fit="true" style="width: 100%;height: 100%;"  pageSize="30">
    <thead style="width: 100%;">
    <tr fit="true">
        <th data-options="checkbox:true,width:0.1,sortable:true"></th>
        <th data-options="field:'roleName',width:0.2,sortable:true">角色名称</th>
        <th data-options="field:'roleDesc',width:0.2,sortable:true">角色说明</th>
        <th data-options="field:'authorityCount',width:0.2,sortable:true,formatter:authorityCount">权限数量</th>
        <th data-options="field:'enabled',width:0.1,sortable:true,formatter:roleenabledReader">是否有效</th>
        <th data-options="field:'createUserName',sortable:true,width:0.1">创建用户</th>
        <th data-options="field:'createDate',sortable:true,width:0.1">创建日期</th>
    </tr>
    </thead>
</table>
<div id="sys_role_tools" style="text-align: right;">
    <security:authorize ifAllGranted="AUTH_ROLE_ADD">
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true"
           onclick="customerShowDialog({url:'admin/role/add',id:'add_role_record',title:'角色信息',width:660,height:260});">添加</a>
    </security:authorize>
    <security:authorize ifAllGranted="AUTH_ROLE_EDIT">
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true"
           onclick="editRole();">修改</a>
    </security:authorize>
    <security:authorize ifAllGranted="AUTH_ROLE_AUTHORITY_LIST">
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-authority',plain:true"
           onclick="showRoleAuthorities();">权限</a>
    </security:authorize>
    <security:authorize ifAllGranted="AUTH_ROLE_DELETE">
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-del',plain:true"
           onclick="delRole()">删除</a>
    </security:authorize>
</div>
<%--添加角色对话框--%>
<%--<div id="add_role_record" class="easyui-dialog" data-options="width:660,height:200,iconCls:'icon-role',--%>
     <%--href:'admin/role/add',--%>
     <%--buttons:[--%>
     <%--{--%>
        <%--text:'保存',--%>
        <%--handler:addRoleSubmit--%>
     <%--},{--%>
        <%--text:'重置'--%>
     <%--}],--%>
    <%--title:'添加角色',--%>
    <%--closed:true,--%>
    <%--modal:true">--%>
<%--</div>--%>
<%--修改角色对话框--%>
<%--<div id="edit_role_record" class="easyui-dialog" data-options="width:660,height:200,iconCls:'icon-role',--%>
     <%--href:'admin/role/edit',--%>
     <%--buttons:[--%>
     <%--{--%>
        <%--text:'保存',--%>
        <%--handler:editRoleSubmit--%>
     <%--}],--%>
    <%--title:'修改角色信息',--%>
    <%--closed:true,--%>
    <%--modal:true">--%>
<%--</div>--%>
<%--角色权限列表--%>
<div id="role_authority_list" class="easyui-dialog" data-options="closed:true,width:800,height:600,
         href:'admin/role/authority/list',
        modal:true,iconCls:'icon-authority'," title="角色权限列表">
</div>
<%--系统权限列表--%>
<div id="role_authority_list_select" class="easyui-dialog"
     data-options="closed:true,width:800,height:600,
       href:'admin/role/authority/add',
      iconCls:'icon-authority',modal:true,buttons:[
    {
        text:'添加',
        handler:addSelectAuthToRole
     }]" title="权限列表">

</div>
