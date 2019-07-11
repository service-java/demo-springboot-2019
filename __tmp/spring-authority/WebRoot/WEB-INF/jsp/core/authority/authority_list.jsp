<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--列表--%>
<script type="text/javascript" src="public/scripts/core/authority/authority.js" ></script>
<div class="easyui-layout" fit="true" style="width: 100%;height: 100%;">
    <div region="north" border="false" style="height: 70px;" title="查询条件">
        <form class="form2">
            <span class="row1" style="width: auto">
                <label>权限名称：</label>
                <input id="s_sys_authority_name">
            </span>
            <span class="row1" style="width: auto;">
                <label>权限编码：</label>
                <input id="s_sys_authority_code">
             </span>
            <span class="row1" style="width: auto;">
                <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-find',plain:true"
                   onclick="sys_authority_find();">查找</a>
            </span>
        </form>
    </div>
    <div region="center" border="false">
        <table id="sys_authority_datagrid" class="easyui-datagrid"
               data-options="idField: 'id',iconCls:'icon-authority',toolbar:'#sys_authority_tools',striped:true,url:'admin/authority/list',rownumbers:true,border:false,singleSelect:true"
               pagination="true" title="系统权限列表" fitColumns="true" fit="true" style="width: 100%;height: 100%;"  pageSize="30">
            <thead style="width: 100%;">
            <tr fit="true">
                <th data-options="checkbox:true,width:0.1"></th>
                <th data-options="field:'authorityName',width:0.2,sortable:true">权限名称</th>
                <th data-options="field:'authorityCode',width:0.3,sortable:true">权限编码</th>
                <th data-options="field:'authorityRes',formatter:authorityResReader,width:0.1">资源数量</th>
                <th data-options="field:'enabled',formatter:authuorityEnabledReader,sortable:true">是否有效</th>
                <th data-options="field:'username',width:0.1">创建人</th>
                <th data-options="field:'createDate',width:0.2,sortable:true">添加时间</th>
            </tr>
            </thead>
        </table>
        <%--工具栏--%>
        <div id="sys_authority_tools" style="text-align: right;">
            <security:authorize ifAllGranted="AUTH_AUTH_ADD">
                <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true"
                   onclick="addAuthority();">添加</a>
            </security:authorize>
            <security:authorize ifAllGranted="AUTH_AUTH_EDIT">
                <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true"
                   onclick="editAuthority();">修改</a>
            </security:authorize>
            <security:authorize ifAllGranted="AUTH_AUTH_RESOUCES_LIST">
                <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-res',plain:true"
                   onclick="showAuthorityRes();">关联资源</a>
            </security:authorize>
            <security:authorize ifAllGranted="AUTH_AUTH_DELETE">
                <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-del',plain:true"
                   onclick="delAuthority();">删除</a>
            </security:authorize>
        </div>
    </div>
</div>


<div id="addAuthorityDialog" class="easyui-dialog" data-options="width:620,height:240,iconCls:'icon-authority',
     buttons:[
     {
        text:'保存',
        handler:addAuthoritySubmit
     },{
        text:'重置'
     }],
    title:'添加权限',
    closed:true,
    href:'admin/authority/add',
    modal:true">
 </div>
<%--修改权限--%>
<div id="editAuthorityDialog" class="easyui-dialog" data-options="width:620,height:240,iconCls:'icon-authority',
     buttons:[
     {
        text:'保存',
        handler:editAuthoritySubmit
     }],
    title:'添加权限',
    closed:true,
    href:'admin/authority/edit',
    modal:true">
</div>
<%--权限资源--%>
<div id="sys_authority_res_dialog" title="权限资源列表" class="easyui-dialog"
     data-options="width:800,
     height:600,
     closed:true,
     href:'admin/authority/resouces/list',
     modal:true,
     iconCls:'icon-res',">
</div>
<div id="res_group_datagrid_select_dialog" title="系统资源列表" class="easyui-dialog"
     data-options="width:800,height:600,closed:true,modal:true,
     href:'admin/authority/resouces/add',
     buttons:[
     {
        text:'添加',
        handler:addSelectResToAuthority
     }]"
    >
</div>
