<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: tony
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form  id="addUserForm" class="form" method="post">
    <fieldset><legend>用户信息</legend>
        <form:hidden path="user.id" />
        <p>
            <span>登陆账号: </span>
            <form:input path="user.userName" cssClass="easyui-validatebox" data-options="required:true,validType:'length[1,10]'"/>
            <%--<input type="text"  name="userName" class="easyui-validatebox" data-options="required:true,validType:'length[1,10]'"/>--%>
        </p>
        <p>
            <span>帐号：</span>
            <c:choose>
                <c:when test="${user.id ne null}">
                    <form:input path="user.userAccount" cssClass="easyui-validatebox"  data-options="required:true,validType:['length[6,16]','remote[\"admin/user/account/${user.id}/validate\",\"account\"]'],invalidMessage:'账号长度必须为6-16个字符并且必须唯一'"/>
                </c:when>
                <c:otherwise>
                    <form:input path="user.userAccount" cssClass="easyui-validatebox"  data-options="required:true,validType:['length[6,16]','remote[\"admin/user/account/validate\",\"account\"]'],invalidMessage:'账号长度必须为6-16个字符并且必须唯一'"/>
                </c:otherwise>
            </c:choose>


            <%--<input type="text" readonly="readonly" name="userAccount"  class="easyui-validatebox" data-options="required:true,validType:'length[6,16]'"/>--%>
        </p>
        <p>
            <span>密码：</span>
            <form:password path="user.userPassword" id="edituserpwd" cssClass="easyui-validatebox"  data-options="validType:'length[6,16]'"/>
            <%--<input type="password" id="edituserpwd" name="userPassword"  class="easyui-validatebox" data-options="validType:'length[6,16]'"/>--%>
        </p>
        <p>
            <span>确认密码：</span>
            <form:password path="user.password" id="redituserpwd" cssClass="easyui-validatebox"   validType="equals['#edituserpwd']" />
            <%--<input type="password"  id="redituserpwd" class="easyui-validatebox"   validType="equals['#edituserpwd']" />--%>
        </p>
        <p>
            <span>职务：</span>
            <form:select path="user.job" >
                <form:option value="1">随访员</form:option>
                <form:option value="2">医生</form:option>
            </form:select>
        </p>
        <p>
            <span> 是否可用：</span>
            <form:select path="user.enabled">
                <form:option value="1">是</form:option>
                <form:option value="0">否</form:option>
            </form:select>
        </p>
        <%--<p>--%>
            <%--<span>用户类型：</span>--%>
            <%--<select  name="userType" >--%>
                <%--<option value="1" selected="selected">普通用户</option>--%>
                <%--<option value="0">系统用户</option>--%>
            <%--</select>--%>
        <%--</p>--%>
    </fieldset>
    <p style="text-align: center;width: 100%;padding-top: 10px;">
        <button onclick="addUserSubmit()" type="button" class="btn btn-mini btn-primary">提交</button>
    </p>
</form>