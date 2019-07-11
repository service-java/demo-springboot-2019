<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: tony
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form  id="addUserForm" class="form" method="post">
    <fieldset><legend>用户信息</legend>
        <input type="hidden" name="id">
        <p>
            <span>登陆账号：</span>
            <form:input path="userName" class="easyui-validatebox" data-options="required:true,validType:'length[1,10]'"/>
        </p>
        <p>
            <span>帐号：</span>
            <form:input  type="text" path="userAccount"  class="easyui-validatebox" data-options="required:true,validType:'length[6,16]'"/>
        </p>
        <p>
            <span>密码：</span>
            <form:password  type="password" id="userpwd" path="userPassword"  class="easyui-validatebox" data-options="required:true,validType:'length[6,16]'"/>
        </p>
        <p>
            <span>确认密码：</span>
            <input  type="password"  id="ruserpwd" class="easyui-validatebox"  required='required' validType="equals['#userpwd']" />
        </p>
        <p>
            <span>职务：</span>
            <form:select path="job" >
                <form:option value="1" label="随访员" />
                <form:option value="2" label="医生" />
            </form:select>
        </p>
        <p>
            <span> 是否可用：</span>
            <form:select  path="enabled">
                <form:option value="1" selected="selected" label="是"/>
                <form:option value="0" label="否" />
            </form:select>
        </p>
    </fieldset>
    <p style="width: 100%;text-align: center;">

    </p>
</form>