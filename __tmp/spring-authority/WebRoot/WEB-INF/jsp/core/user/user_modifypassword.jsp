<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form action="admin/user/modifypassword"  class="form" method="post" id="change_user_password_form">
    <p>
        <span>原始密码：</span>
        <input name="oldpassword"  class="easyui-validatebox" type="password">
    </p>
    <p>
        <span>新密码：</span>
        <input name="newpassword" id="modify_user_password_new" type="password" class="easyui-validatebox" data-options="required:true,validType:'length[6,16]'">
    </p>
    <p>
        <span>确认密码：</span>
        <input name="repassword" class="easyui-validatebox" type="password"  required='required' validType="equals['#modify_user_password_new']" >
    </p>
</form>