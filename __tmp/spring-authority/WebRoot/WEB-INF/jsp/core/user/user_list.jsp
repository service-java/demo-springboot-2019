<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="public/scripts/core/user/user.js"></script>
<div class="easyui-layout" fit="true" style="width: 100%;height: 100%;">
     <div region="north" style="height:75px;" title="查询条件" border="false">
         <form class="form1">
              <span class="row1" style="width: auto">
                  <label>用户名：</label>
                  <input id="s_sys_user_name">
              </span>
              <span class="row1" style="width: auto">
                  <label>账号：</label>
                  <input id="s_sys_user_account">
              </span>
              <span class="row1" style="width: auto">
                  <label>是否启用：</label>
                  <select id="s_sys_user_enable">
                      <option value="">全部</option>
                      <option value="1">启用</option>
                      <option value="0">禁用</option>
                  </select>
              </span>
              <span class="row1" style="width: auto">
                  <label>是否锁定：</label>
                  <select id="s_sys_user_lock">
                      <option value="">全部</option>
                      <option value="0">锁定</option>
                      <option value="1">未锁定</option>
                  </select>
              </span>
             <span class="row1" style="width: auto;">
                 <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-find',plain:true"
                    onclick="sys_user_find()">查找</a>
             </span>
          </form>
     </div>
    <div region="center" border="false">
        <table id="user_datagrid" class="easyui-datagrid" title="系统用户列表"
               data-options="singleSelect:true,iconCls:'icon-user',toolbar:'#user_manager_tools',striped:true,url:'admin/user/list',method:'post',rownumbers:true,border:false,singleSelect:true"
               pagination="true" fitColumns="true" fit="true" style="width: 100%;height: 100%;"  pageSize="30">
            <thead style="width: 100%;">
            <tr fit="true">
                <th data-options="checkbox:true,width:0.1,sortable:true"></th>
                <th data-options="field:'userName',width:0.2,sortable:true">用户名</th>
                <th data-options="field:'userAccount',width:0.2,sortable:true">帐号</th>
                <th data-options="field:'userRole',width:0.1,formatter:userRoleReader,sortable:true">分配角色</th>
                <th data-options="field:'enabled',width:0.1,formatter:enabledReader,sortable:true">是否启用</th>
                <th data-options="field:'accountNonLocked',width:0.1,sortable:true,formatter:isUserLocaked">是否锁定</th>
                <th data-options="field:'createDate',width:0.1,sortable:true">创建时间</th>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
        <div id="user_manager_tools" style="text-align: right;">
            <security:authorize ifAllGranted="AUTH_USER_ENABLE">
                <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-enable',plain:true"
                   onclick="enableUser();">启用/禁用</a>
            </security:authorize>
            <security:authorize ifAllGranted="AUTH_USER_LOCK">
                <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-locked',plain:true"
                   onclick="lockUser()">锁定/解锁</a>
            </security:authorize>
            <security:authorize ifAllGranted="AUTH_USER_ADD">
                <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true"
                   onclick="customerShowDialog({url:'admin/user/add',id:'add_user_record',title:'用户信息',width:620,height:260});">添加</a>
            </security:authorize>
            <security:authorize ifAllGranted="AUTH_USER_EDIT">
                <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true"
                   onclick="editUser()">修改</a>
            </security:authorize>
            <security:authorize ifAllGranted="AUTH_USER_ROLE_LIST">
                <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-role',plain:true"
                   onclick="showUserRole()">角色管理</a>
            </security:authorize>
            <security:authorize ifAllGranted="AUTH_USER_DELETE">
                <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-del',plain:true"
                   onclick="delUser()">删除</a>
            </security:authorize>
        </div>
    </div>
</div>

<%--<div id="add_user_record" class="easyui-dialog" data-options="width:620,height:220,iconCls:'icon-user',--%>
    <%--href:'admin/user/add',--%>
     <%--buttons:[--%>
     <%--{--%>
        <%--text:'保存',--%>
        <%--handler:addUserSubmit--%>
     <%--},{--%>
        <%--text:'重置'--%>
     <%--}],--%>
    <%--title:'添加用户',--%>
    <%--closed:true,--%>
    <%--modal:true">--%>

<%--</div>--%>
<%--<div id="edit_user_record" class="easyui-dialog" data-options="width:620,height:220,iconCls:'icon-user',--%>
    <%--href:'admin/user/edit',--%>
     <%--buttons:[--%>
     <%--{--%>
        <%--text:'保存',--%>
        <%--handler:editUserSubmit--%>
     <%--}],--%>
    <%--title:'修改用户信息',--%>
    <%--closed:true,--%>
    <%--modal:true">--%>

<%--</div>--%>
<%--用户角色列表--%>
<div id="user_role_dialog" class="easyui-dialog"
     data-options="closed:true,width:600,
     iconCls:'icon-role',
     href:'admin/user/user_role_list',
     height:400,modal:true" title="用户角色列表">

</div>
<%--系统角色列表--%>
<div id="user_role_select_dialog" class="easyui-dialog"
     data-options="closed:true,width:600,height:400,
      href:'admin/user/role/add',
     modal:true,buttons:[
    {
        text:'添加',
        handler:addSelectRoleToUser
     }]"
     title="系统角色">

</div>

<%--</body>--%>
<%--</html>--%>