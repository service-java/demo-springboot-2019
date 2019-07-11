<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: tony
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form id="addRoleForm" class="form"  method="post" enctype="application/x-www-form-urlencoded">
    <fieldset><legend>角色信息</legend>
    <form:hidden path="role.id"/>
        <p>
            <span>角色名称：</span>
            <form:input path="role.roleName" cssClass="easyui-validatebox" cssStyle="width: 140px;" data-options="required:true" />
        </p>
        <p>
            <span>是否有效：</span>
            <form:select path="role.enabled" >
                <form:option value="true" label="是"/>
                <form:option value="false" label="否"/>
            </form:select>
        </p>
        <p>
            <span>数据级别：</span>
            <form:select path="role.dataLevel"  >
                <form:option value="0" label="所属数据"/>
                <form:option value="1" label="全部数据"/>
            </form:select>
        </p>
        <p style="width: 100%;">
            <span>角色说明：</span>
            <form:textarea path="role.roleDesc" cssStyle="width: 360px"/>
        </p>

    </fieldset>
    <p style="text-align: center;width: 100%;padding-top: 10px;">
        <button onclick="addRoleSubmit()" type="button" class="btn btn-mini btn-primary">提交</button>
    </p>
</form>