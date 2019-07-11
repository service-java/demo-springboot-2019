<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<table id="sys_authority_res_datagrid" class="easyui-datagrid"
       data-options="idField: 'id',toolbar:'#sys_authority_res_tools',striped:true,rownumbers:true,border:false,singleSelect:true"
       pagination="true"  fitColumns="true" fit="true" style="width: 100%;height: 100%;"  pageSize="30">
    <thead style="width: 100%;">
    <tr fit="true">
        <th data-options="field:'resourceName',width:0.2">资源名称</th>
        <th data-options="field:'resourcePath',width:0.3">资源路径</th>
        <th data-options="field:'resourceDesc',width:0.3">资源描述</th>
    </tr>
    </thead>
</table>
<div id="sys_authority_res_tools" style="text-align: right;">
    <security:authorize ifAllGranted="AUTH_AUTH_RESOUCES_ADD">
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true"
           onclick="addAuthorityRes();">添加</a>
    </security:authorize>
    <security:authorize ifAllGranted="AUTH_AUTH_RESOUCES_DELETE">
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-del',plain:true"
           onclick="delAuthorityRes()">删除</a>
    </security:authorize>
</div>
