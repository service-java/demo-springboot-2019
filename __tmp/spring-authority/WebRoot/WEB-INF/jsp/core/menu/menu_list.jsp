<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="public/scripts/core/menu/menu.js" ></script>
<%--菜单列表--%>
<table id="sys_menu_list" class="easyui-treegrid"
       data-options="treeField: 'name',idField: 'id',iconCls:'icon-menu',striped:true,toolbar:'#sys_menu_list_tools',url:'admin/menu/list',method:'POST',rownumbers:true,striped:true,border:false,singleSelect:true"
       title="系统菜单列表" fitColumns="true" fit="true" style="width: 100%;height: 100%;"  pageSize="30"
        >
    <thead style="width: 100%;">
    <tr fit="true">
        <th data-options="field:'name',width:0.2">菜单名称</th>
        <th data-options="field:'resourcePath',width:0.3">菜单路径</th>
        <th data-options="field:'deep',width:0.1">深度</th>
        <th data-options="field:'childrenSize',width:0.2">子菜单数</th>
        <th data-options="field:'menuIndex',width:0.1">排序</th>
        <th data-options="field:'iconStyle',width:0.1">图标</th>
    </tr>
    </thead>
</table>
<div id="sys_menu_list_tools" style="text-align: right;">
    <security:authorize ifAllGranted="AUTH_MENU_ADD">
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true"
           onclick="addMenu();">添加</a>
    </security:authorize>
    <security:authorize ifAllGranted="AUTH_MENU_EDIT">
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true"
           onclick="editMenu();">修改</a>
    </security:authorize>
    <security:authorize ifAllGranted="AUTH_MENU_DELETE">
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-del',plain:true"
           onclick="delMenu()">删除</a>
    </security:authorize>
</div>
<%--新增菜单--%>
<div id="sys_menu_add_dialog" class="easyui-dialog" title="新增菜单"
     data-options="width:640,height:250,href:'admin/menu/add',method:'get',closed:true,buttons:[{text:'保存',handler:saveMenu},{text:'重置'}],modal:true"
>
</div>
<%--修改菜单--%>
<div id="sys_menu_edit_dialog" class="easyui-dialog" title="修改菜单" data-options="width:640,height:250,href:'admin/menu/edit',method:'get',buttons:[{text:'保存',handler:saveUpdateMenu}],closed:true">

</div>
<%--菜单图标--%>
<div id="sys_menu_icon_dialog" class="easyui-dialog" title="修改菜单" data-options="width:600,height:400,href:'resources/pages/iconsview/sys_icons.jsp',method:'get',buttons:[{text:'保存',handler:menuIconSelect}],closed:true">

</div>
<%--选择资源--%>
<div id="sys_menu_resoucse_select_dialog" class="easyui-dialog" title="系统资源" data-options="width:800,height:600,href:'admin/menu/resouces',method:'get',buttons:[{text:'选择',handler:selectedMenuResouces}],closed:true">

</div>
<%--选择资源--%>
<div id="sys_menu_resoucse_edit_select_dialog" class="easyui-dialog" title="系统资源" data-options="width:800,height:600,href:'admin/menu/resouces',method:'get',buttons:[{text:'选择',handler:editSelectedMenuResouces}],closed:true">

</div>
