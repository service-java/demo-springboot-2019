<%--
  Created by IntelliJ IDEA.
  User: tony
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form id="addResForm" class="form"  method="post" enctype="application/x-www-form-urlencoded">
    <fieldset><legend>资源信息</legend>
        <input type="hidden" name="id">
        <input type="hidden" name="parent.id" id="res_parent_id">
        <p>
            <span>名称：</span><input name="resourceName" class="easyui-validatebox" data-options="required:true"/>
        </p>
        <p>
            <span>父节点：</span><input name="parent.resourceName" id="res_parent_name" readonly="readonly"/>
        </p>
        <p style="width: 560px;">
            <span>路径：</span><input name="resourcePath" style="width: 440px;">
        </p>
        <p style="width: 560px;">
            <span> 说明：</span><textarea name="resourceDesc" rows="" cols=""  style="width: 445px;" />
        </p>
    </fieldset>
</form>