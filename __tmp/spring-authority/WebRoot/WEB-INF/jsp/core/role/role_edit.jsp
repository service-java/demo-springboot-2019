<%--
  Created by IntelliJ IDEA.
  User: tony
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form id="editRoleForm" class="form"  method="post" enctype="application/x-www-form-urlencoded">
    <fieldset><legend>角色信息</legend>
        <input type="hidden" name="id">
        <p>
            <span>角色名称：</span><input name="roleName" class="easyui-validatebox" data-options="required:true"/>
        </p>
        <p>
            <span>是否有效：</span>
            <select  name="enabled" >
                <option value="true">是</option>
                <option value="false">否</option>
            </select>
        </p>
        <p>
            <span>数据级别：</span>
            <select  name="dataLevel" >
                <option value="0" >所属数据</option>
                <option value="1">全部数据</option>
            </select>
        </p>
        <p  style="width: 560px;">
            <span>角色说明：</span>
            <textarea name="roleDesc" rows="" cols=""  style="width: 446px;" />
        </p>
    </fieldset>
</form>