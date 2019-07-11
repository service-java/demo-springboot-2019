function addUser() {
    $("#add_user_record").dialog("open");
}
//  修改用户信息
function editUser() {
    var user = $("#user_datagrid").datagrid("getSelected");
    if (user && user.id) {
        customerShowDialog({url:'admin/user/edit/'+user.id,id:'add_user_record',title:'用户信息',width:620,height:260});
//        $("#edit_user_record").dialog("refresh","admin/user/edit/"+user.id).dialog("open");
    } else {
        $.messager.alert("错误", "请选择要修改的记录！", "error");
    }
}
function addUserSubmit() {
    $('#addUserForm').form("submit", {
        url: "admin/user/add",
        success: function (data) {
            var data = eval('(' + data + ')');  // change the JSON string to javascript object
            if (data.success) {
                $('#addUserForm').form("clear");
                $("#add_user_record").dialog("close");
                $("#user_datagrid").datagrid("reload");
                showMessager("操作成功！");
            } else {
                $.messager.alert('提示', '操作失败！', "warning");
            }
        }
    });
}

function editUserSubmit() {
    $('#editUserForm').form("submit", {
        url: "admin/user/add",
        success: function (data) {
            var data = eval('(' + data + ')');  // change the JSON string to javascript object
            if (data.success) {
                $('#addUserForm').form("clear");
                $("#add_user_record").dialog("close");
                $("#user_datagrid").datagrid("reload");
                showMessager("操作成功！");
            } else {
                $.messager.alert('提示', '操作失败！', "warning");
            }
        }
    });
}
function delUser() {
    var user = $("#user_datagrid").datagrid("getSelected");
    if (user && user.id) {
        $.messager.confirm('警告', '确定要删除选中的记录吗?', function (r) {
            if (r) {
                $.ajax({
                    url: 'admin/user/delete',
                    dataType: 'json',
                    type:'POST',
                    data: {userid: user.id},
                    success: function (data) {
//                            var data = eval('(' + data + ')');  // change the JSON string to javascript object
//                            alert(data.success);
                        if (data.success) {
                            showMessager("操作成功！");
                            $("#user_datagrid").datagrid("reload", {});
                        } else {
                            $.messager.alert('提示', '操作失败！', "warning");
                        }
                    }
                })
            } else {
                return;
            }
        });
    } else {
        $.messager.alert("错误", "请选择要删除的记录！", "error");
    }
}

//添加选中角色给用户
function addSelectRoleToUser() {
    var selected = $("#user_role_select_datagrid").datagrid("getSelections");
    if (selected) {
        var ids = "";
        for (var n = 0; n < selected.length; n++) {
            if (ids.length > 0) {
                ids += "," + selected[n].id;
            } else {
                ids += selected[n].id;
            }
        }
        var _userId = $("#user_datagrid").datagrid("getSelected").id;
        $.ajax({
            url: 'admin/user/role/add',
            dataType: 'json',
            type: 'POST',
            data: {userId: _userId, roleIds: ids},
            success: function (data) {
                if (data.success) {
                    showMessager("操作成功！");
                    $("#user_role_select_dialog").dialog("close");
                    $("#user_role_datagrid").datagrid("reload");
                } else {
                    $.messager.alert('提示', '操作失败', "warning");
                }
            }
        });

    } else {
        $.messager.alert("错误","请选择要添加的角色！");
    }
}

//显示系统角色
function addUserRole() {
    $("#user_role_select_dialog").dialog("open");
//    $("#user_role_select_datagrid").datagrid("load");
}
//删除用户角色
function delUserRole() {
    var selected = $("#user_role_datagrid").datagrid("getSelections");
    if (selected) {
        var ids = "";
        for (var n = 0; n < selected.length; n++) {
            if (ids.length > 0) {
                ids += "," + selected[n].id;
            } else {
                ids += selected[n].id;
            }
        }
        var _userId = $("#user_datagrid").datagrid("getSelected").id;
        $.ajax({
            url: 'admin/user/role/delete',
            dataType: 'json',
            type: 'POST',
            data: {userId: _userId, roleIds: ids},
            success: function (data) {
                if (data.success) {
                    showMessager("操作成功！");
                    $("#user_role_datagrid").datagrid("reload");
                } else {
                    $.messager.alert('提示', '操作失败', "warning");
                }
            }
        });
    } else {
        $.messager.alert('提示', '请选择要删除的角色', "warning");
    }
}
//显示用户角色
function showUserRole() {
    var _userid = $("#user_datagrid").datagrid("getSelected");
    if (_userid) {
        var url = 'admin/user/role/list/'+_userid.id
        $("#user_role_dialog").dialog({
            onLoad:function(){
                $("#user_role_datagrid").datagrid({
                    url:url
                });
                $("#user_role_datagrid").datagrid("reload");
            }
        });
        $("#user_role_dialog").dialog("open");
        try{
            $("#user_role_datagrid").datagrid({
                url:url
            });
            $("#user_role_datagrid").datagrid("reload");
        }catch (e){

        }
    } else {
        $.messager.alert("提示", "请先选择用户", "warning");
        return;
    }
}

function lockUser(){
    var _userid = $("#user_datagrid").datagrid("getSelected");
    if(_userid && _userid.id){
        showProgress();
        $.ajax({
            url: 'admin/user/lock/'+_userid.id,
            dataType: 'json',
            type: 'POST',
            success: function (data) {
                if (data.success) {
                    closeProgress();
                    showMessager("操作成功！");
                    $("#user_datagrid").datagrid("reload");
                } else {
                    $.messager.alert('提示', '操作失败', "warning");
                }
            }
        });
    }else{
        $.messager.alert("提示","请先选择用户!");
    }
}

function enableUser(){
    var _userid = $("#user_datagrid").datagrid("getSelected");
    if(_userid&&_userid.id){
        showProgress();
        $.ajax({
            url: 'admin/user/enable/'+_userid.id,
            dataType: 'json',
            type: 'POST',
            success: function (data) {
                if (data.success) {
                    closeProgress();
                    showMessager("操作成功！");
                    $("#user_datagrid").datagrid("reload");
                } else {
                    $.messager.alert('提示', '操作失败', "warning");
                }
            }
        });
    }else{
        $.messager.alert("提示","请先选择用户!");
    }
}
function sys_user_find(){
    var username = $("#s_sys_user_name").val();
    var account = $("#s_sys_user_account").val();
    var enable = $("#s_sys_user_enable").val();
    var lock = $("#s_sys_user_lock").val();
    var params = {
        params:{
            userName:username,
            userAccount:account,
            isEnabled:enable,
            isAccountNonLocked:lock
        }
    };
    $("#user_datagrid").datagrid({
        queryParams:params
    });
}

$.extend($.fn.validatebox.defaults.rules, {
    equals: {
        validator: function (value, param) {
            return value == $(param[0]).val();
        },
        message: '两次密码输入不一致'
    }
});

