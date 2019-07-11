<%--
  Created by IntelliJ IDEA.
  User: tony
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="public/scripts/core/rolemenu/rolemenu.js"></script>
<table id="sys_rolemenu_list" class="easyui-treegrid"
       data-options="treeField: 'name',idField: 'id',iconCls:'icon-menu',striped:true,toolbar:'#sys_rolemenu_list_tools',method:'POST',rownumbers:true,striped:true,border:false,singleSelect:true"
       title="角色菜单列表" fitColumns="true" fit="true" style="width: 100%;height: 100%;"  pageSize="30"
        >
    <thead style="width: 100%;">
    <tr fit="true">
        <th data-options="field:'name',width:0.2,sortable:true">菜单名称</th>
        <th data-options="field:'url',width:0.3,sortable:true">菜单路径</th>
        <th data-options="field:'index',width:0.1,sortable:true">排序</th>
        <th data-options="field:'iconStyle',width:0.1,sortable:true">图标</th>
    </tr>
    </thead>
</table>
<div id="sys_rolemenu_list_tools" style="text-align: right;">
    <select id="sys_rolemenu_simplerolelist" class="easyui-combobox" style="width:140px;" data-options="valueField:'id',textField:'roleName',url:'admin/role/simpleList',onChange:changeRole">
    </select>
    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true"
       onclick="autoCreateRoleMenu();">自动生成</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-up',plain:true"
       onclick="roleMenuMoveUp()">上移</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-down',plain:true"
       onclick="roleMenuMoveDown()">下移</a>
</div>