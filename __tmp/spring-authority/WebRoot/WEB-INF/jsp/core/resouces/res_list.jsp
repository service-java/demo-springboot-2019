<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: tony
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="public/scripts/core/resouces/resouces.js" ></script>
        <table id="res_group_datagrid" class="easyui-treegrid"
               data-options="iconCls:'icon-res',treeField: 'resourceName',idField: 'id',toolbar:'#sys_resman_tools',striped:true,url:'admin/resouces/list',method:'POST',rownumbers:true,border:false,singleSelect:true"
               pagination="true" title="系统资源列表" fitColumns="true" fit="true" style="width: 100%;height: 100%;"  pageSize="30">
            <thead style="width: 100%;">
            <tr fit="true">
                <th data-options="field:'resourceName',width:0.2">资源名词</th>
                <th data-options="field:'resourceIndex',width:0.2">序号</th>
                <th data-options="field:'resourcePath',width:0.3">资源路径</th>
                <th data-options="field:'resourceDesc',width:0.3">资源描述</th>
            </tr>
            </thead>

        </table>
        <div id="sys_resman_tools" style="text-align: right;">
            <label>资源名称：</label>
            <input id="s_sys_res_name">
            <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-find',plain:true"
               onclick="sys_res_find();">查找</a>
            <security:authorize ifAllGranted="AUTH_RESOUCES_ADD">
                <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true"
                   onclick="addRes();">添加</a>
            </security:authorize>

            <%--<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-move',plain:true"--%>
            <%--onclick="moveRes();">移动</a>--%>
            <security:authorize ifAllGranted="AUTH_RESOUCES_DELETE">
                <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true"
                   onclick="editRes();">修改</a>
            </security:authorize>
            <security:authorize ifAllGranted="AUTH_RESOUCES_ADD">
                <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-del',plain:true"
                   onclick="delRes()">删除</a>
            </security:authorize>

        </div>
    <%--</div>--%>
<%--</div> --%>

<div id="add_res_record" class="easyui-dialog" data-options="width:620,height:240,iconCls:'icon-res',
    href:'admin/resouces/add',
     buttons:[
     {
        text:'保存',
        handler:addResSubmit
     },{
        text:'重置'
     }],
    title:'添加资源',
    closed:true,
    modal:true">

</div>
<div id="edit_res_record" class="easyui-dialog" data-options="width:620,height:240,iconCls:'icon-res',
     href:'admin/resouces/edit',
     buttons:[
     {
        text:'保存',
        handler:editResSubmit
     }],
    title:'修改资源信息',
    closed:true,
    modal:true">

</div>
