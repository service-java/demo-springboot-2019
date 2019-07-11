<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form id="editAuthorityForm"  class="form" METHOD="POST">
    <fieldset><legend>角色信息</legend>
        <input type="hidden" name="id">
        <p>
            <span>权限名称：</span><input name="authorityName" class="easyui-validatebox" data-options="required:true"/>
        </p>
        <p>
            <span>权限编码：</span><input name="authorityCode" class="easyui-validatebox" data-options="required:true"/>
        </p>
        <p>
            <span>是否有效：</span>
            <select name="enabled">
                <option value="1">是</option>
                <option value="0">否</option>
            </select>
        </p>
        <p style="width: 560px;">
            <span> 说明：</span><textarea name="authorityDesc" rows="" cols=""  style="width: 445px;" />
        </p>
    </fieldset>
</form>
