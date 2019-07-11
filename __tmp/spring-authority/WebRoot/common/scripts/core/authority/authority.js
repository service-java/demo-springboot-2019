/**
 * Created with IntelliJ IDEA.
 * User: Tim
 * Date: 12-12-14
 * Time: 下午1:11
 * To change this template use File | Settings | File Templates.
 */
//    显示表单
function addAuthority() {
    $("#addAuthorityDialog").dialog("open");
}
//    保存表单
function addAuthoritySubmit() {
    $('#addAuthorityForm').form("submit", {
        url: "admin/authority/add",
        method: "post",
        success: function (data) {
            var data = eval("(" + data + ")")
            if (data.success) {
                $('#addAuthorityForm').form("clear");
                $("#addAuthorityDialog").dialog("close");
                $("#sys_authority_datagrid").datagrid("reload");
                $.messager.alert("提示", "操作成功");
            } else {
                $.messager.alert("提示", "操作失败", "warning");

            }
        }
    });
}

//添加权限自娱那
function editAuthority() {
    var authority = $("#sys_authority_datagrid").datagrid("getSelected");
    if (authority) {
        $("#editAuthorityDialog").dialog({
            onLoad: function () {
                $('#editAuthorityForm').form("load", authority);
            }
        });
        $("#editAuthorityDialog").dialog("open");
        try{
            $('#editAuthorityForm').form("load", authority);
        }catch(e){

        }
    } else {
        $.messager.alert("提示", "请选择要修改的记录", "warning");
        return;
    }
}

function editAuthoritySubmit() {
    $('#editAuthorityForm').form("submit", {
        url: "admin/authority/add",
        method: "post",
        success: function (data) {
            var data = eval("(" + data + ")")
            if (data.success) {
                $('#editAuthorityForm').form("clear");
                $("#editAuthorityDialog").dialog("close");
                $("#sys_authority_datagrid").datagrid("reload");
                $.messager.alert("提示", "操作成功");
            } else {
                $.messager.alert("提示", "操作失败", "warning");

            }
        }
    });
}

function addAuthorityRes() {
    $("#res_group_datagrid_select_dialog").dialog("open");
}

//    删除记录
function delAuthority() {
    var _authority = $("#sys_authority_datagrid").datagrid("getSelected");
    if (_authority) {
        $.messager.confirm('警告', '确定要删除选中的记录吗?', function (r) {
            if (r) {
                $.ajax({
                    url: 'admin/authority/delete',
                    dataType: 'json',
                    type: 'POST',
                    data: {authorityId: _authority.id},
                    success: function (data) {
                        if (data.success) {
                            $.messager.alert('提示', '操作成功');
                            $("#sys_authority_datagrid").datagrid("reload");
                        } else {
                            $.messager.alert('提示', '操作失败', "warning");
                        }
                    }
                });
            }
            return;
        });
    } else {
        $.messager.alert("提示", "请选择要删除的记录", "warning");
    }

}
//    查看权限资源
function showAuthorityRes() {
    var _authority = $("#sys_authority_datagrid").datagrid("getSelected");

    if (_authority) {
        var url = 'admin/authority/resouces/list/' + _authority.id;
        $("#sys_authority_res_dialog").dialog({
            onLoad: function () {
                $("#sys_authority_res_datagrid").datagrid({url: url,cache:false});
                $("#sys_authority_res_datagrid").datagrid("load");
            }
        });
        try{
            $("#sys_authority_res_datagrid").datagrid({url: url,cache:false});
        }catch (err){

        }
        $("#sys_authority_res_dialog").dialog("open");

    } else {
        $.messager.alert("提示", "请选择要查看的记录", "warning");
        return;
    }

}


function addSelectResToAuthority() {
    var selected = $("#res_group_datagrid_select").treegrid("getSelections");
    if (selected) {
        var ids = "";
        for (var n = 0; n < selected.length; n++) {
            if (ids.length > 0) {
                ids += "," + selected[n].id;
            } else {
                ids += selected[n].id;
            }
        }
        var _authorityId = $("#sys_authority_datagrid").datagrid("getSelected").id;
        $.ajax({
            url: 'admin/authority/resouces/add',
            dataType: 'json',
            type: 'POST',
            data: {authorityId: _authorityId, resIds: ids},
            success: function (data) {
                if (data.success) {
                    $.messager.alert('提示', '操作成功');
                    $("#sys_authority_res_datagrid").datagrid("reload");
                    $("#res_group_datagrid_select_dialog").dialog("close");
                } else {
                    $.messager.alert('提示', '操作失败', "warning");
                }
            }
        });

    } else {
        alert("请选择资源");
    }
}
function delAuthorityRes() {
    var selected = $("#sys_authority_res_datagrid").datagrid("getSelections");
    if (selected) {
        var ids = "";
        for (var n = 0; n < selected.length; n++) {
            if (ids.length > 0) {
                ids += "," + selected[n].id;
            } else {
                ids += selected[n].id;
            }
        }
        var _authorityId = $("#sys_authority_datagrid").datagrid("getSelected").id;
        $.ajax({
            url: 'admin/authority/resouces/delete',
            dataType: 'json',
            type: 'POST',
            data: {authorityId: _authorityId, resIds: ids},
            success: function (data) {
                if (data.success) {
                    $.messager.alert('提示', '操作成功');
                    $("#sys_authority_res_datagrid").datagrid("reload");
                } else {
                    $.messager.alert('提示', '操作失败', "warning");
                }
            }
        });

    } else {
        alert("请选择资源");
    }
}

function sys_authority_find(){
    var authority_name = $("#s_sys_authority_name").val();
    var authority_code = $("#s_sys_authority_code").val();
    var params = {
        params:{
            authorityName:authority_name,
            authorityCode:authority_code
        }
    }
    $("#sys_authority_datagrid").datagrid({
        queryParams:params
    });
}

