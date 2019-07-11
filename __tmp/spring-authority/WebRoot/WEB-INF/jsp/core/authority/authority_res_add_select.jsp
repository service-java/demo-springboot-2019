<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<table id="res_group_datagrid_select" class="easyui-treegrid"
       data-options="treeField: 'resourceName',idField: 'id',toolbar:'#sys_resman_tools',striped:true,url:'admin/resouces/list',rownumbers:true,border:false,method:'post',singleSelect:false"
       pagination="true"   fitColumns="true" fit="true" style="width: 100%;height: 100%;" pageSize="30">
    <thead style="width: 100%;">
    <tr fit="true">
        <th data-options="checkbox:true,width:0.1"></th>
        <th data-options="field:'resourceName',width:0.3">资源名词</th>
        <th data-options="field:'resourceIndex',width:0.1">序号</th>
        <th data-options="field:'resourcePath',width:0.3">资源路径</th>
        <th data-options="field:'resourceDesc',width:0.3">资源描述</th>
    </tr>
    </thead>
</table>
