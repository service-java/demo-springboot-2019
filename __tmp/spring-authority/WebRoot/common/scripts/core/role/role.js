//新增
function addRole() {
    $("#add_role_record").dialog("open");
    $('#addRoleForm').form("clear");
}
//    修改
function editRole() {

    var res = $("#role_datagrid").datagrid("getSelected");
    if (res) {
        customerShowDialog({url:'admin/role/edit/'+res.id,id:'add_role_record',title:'角色信息',width:660,height:260});
//        $("#edit_role_record").dialog({
//            onLoad:function(){
//                $("#editRoleForm").form("load", res);
//            }
//        });
//        $("#edit_role_record").dialog("open");
    } else {
        alert("请选择要修改的记录");
    }
}
//提交表单
function addRoleSubmit() {
    $('#addRoleForm').form("submit", {
        url: "admin/role/add",
        method: "post",
        success: function (data) {
            var data = eval("(" + data + ")");
            if (data.success) {
                $('#addRoleForm').form("clear");
                $("#add_res_record").dialog("close");
                $("#role_datagrid").datagrid("reload");
                $.messager.alert("提示", "操作成功");
            } else {
                $.messager.alert("提示", "操作失败", "warning");
            }
        }
    });
}

function editRoleSubmit() {
    $('#editRoleForm').form("submit", {
        url: "admin/role/add",
        method: "post",
        success: function (data) {
            var data = eval("(" + data + ")");
            if (data.success) {
                $('#addRoleForm').form("clear");
                $("#add_res_record").dialog("close");
                $("#role_datagrid").datagrid("reload");
                $.messager.alert("提示", "操作成功");
            } else {
                $.messager.alert("提示", "操作失败", "warning");
            }
        }
    });
}
//           查看角色权限
function showRoleAuthorities() {
    var _role = $("#role_datagrid").datagrid("getSelected");
    if(_role){
        var url = "admin/role/authority/list/"+_role.id;
        $("#role_authority_list").dialog({
            onLoad:function(){
                $("#role_authority_datagrid").datagrid({url:url});
                $("#role_authority_datagrid").datagrid("load");
            }
        });
        try{
            $("#role_authority_datagrid").datagrid({url:url});
        }catch(err){

        }
        $("#role_authority_list").dialog("open");

    }else{
        $.messager.alert("提示","请选择要查看的记录")
    }

}
function delRole() {
    var role = $("#role_datagrid").datagrid("getSelected");
    if (role && role.id) {
        $.messager.confirm('警告', '确定要删除选中的记录吗?', function (r) {
            if (r) {
                $.ajax({
                    url: 'admin/role/delete',
                    dataType: 'json',
                    type: "POST",
                    data: {roleId: role.id},
                    success: function (data) {
                        if (data.success) {
                           showMessager("操作成功！");
                            $("#role_datagrid").datagrid("reload");
                        } else {
                            $.messager.alert('提示', '操作失败', "warning");
                        }
                    }
                })
            } else {
                return;
            }
        });
    } else {
        $.messager.alert("错误", "请选择要删除的记录", "error");
    }
}
//    显示添加角色权限
function addRoleAuthority() {
    $("#role_authority_list_select").dialog("open");
    $("#sys_authority_datagrid_select").datagrid("load")
}

//添加角色权限
function addSelectAuthToRole() {
    //获取选中权限
    var selected = $("#sys_authority_datagrid_select").datagrid("getSelections");
    if (selected) {
        var ids = "";
        for (var n = 0; n < selected.length; n++) {
            if (ids.length > 0) {
                ids += "," + selected[n].id;
            } else {
                ids += selected[n].id;
            }
        }
        //获取选中角色
        var _roleId = $("#role_datagrid").datagrid("getSelected").id;
        $.ajax({
            url: 'admin/role/authority/add',
            dataType: 'json',
            type: 'POST',
            data: {authIds: ids, roleId: _roleId},
            success: function (data) {
                if (data.success) {
                   showMessager("操作成功！");
                    $("#role_authority_datagrid").datagrid("reload");
                    $("#role_authority_list_select").dialog("close");

                } else {
                    $.messager.alert('提示', '操作失败', "warning");
                }
            }
        });

    } else {
        alert("请选择资源");
    }
}
//删除角色权限
function delRoleAuthority() {
    //获取选中权限
    var selected = $("#role_authority_datagrid").datagrid("getSelections");
    if (selected) {
        var ids = "";
        for (var n = 0; n < selected.length; n++) {
            if (ids.length > 0) {
                ids += "," + selected[n].id;
            } else {
                ids += selected[n].id;
            }
        }
        //获取选中角色
        var _roleId = $("#role_datagrid").datagrid("getSelected").id;
        $.ajax({
            url: 'admin/role/authority/delete',
            dataType: 'json',
            type: 'POST',
            data: {authIds: ids, roleId: _roleId},
            success: function (data) {
                if (data.success) {
                   showMessager("操作成功！");
                    $("#role_authority_datagrid").datagrid("reload");
                } else {
                    $.messager.alert('提示', '操作失败', "warning");
                }
            }
        });

    } else {
        alert("请选择资源");
    }
}

