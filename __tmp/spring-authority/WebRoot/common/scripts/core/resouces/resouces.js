//新增
function addRes() {
    $("#add_res_record").dialog("open");
    var res = $("#res_group_datagrid").datagrid("getSelected");
    $('#addResForm').form("clear");
    if (res) {
        $("#res_parent_id").val(res.id);
        $("#res_parent_name").val(res.resourceName)
    }
}
//修改
function editRes() {
    var res = $("#res_group_datagrid").treegrid("getSelected");
    if (res) {
        $("#edit_res_record").dialog({
            onLoad:function(){
                $("#editResForm").form("load", res);
            }
        });
        $("#edit_res_record").dialog("open");
        $("#editResForm").form("load", res);
    } else {
        $.messager.alert("提示","请选择要修改的记录","warning");
    }
}
//提交表单
function addResSubmit() {
    $('#addResForm').form("submit", {
        url: "admin/resouces/add",
        method: "post",
        success: function (data) {
            var data = eval("(" + data + ")")
            if (data.success) {
                $('#addResForm').form("clear");
                $("#add_res_record").dialog("close");
                showMessager("操作成功！");
                $("#res_group_datagrid").treegrid("reload");
            } else {
                $.messager.alert("提示", "操作失败", "warning");

            }
        }
    });
}

function delRes() {
    var _res = $("#res_group_datagrid").datagrid("getSelected");
    if (_res) {
        $.messager.confirm('警告', '确定要删除选中的记录吗?', function (r) {
            if (r) {
                $.ajax({
                    url: 'admin/resouces/delete',
                    dataType: 'json',
                    type: 'POST',
                    data: {resid: _res.id},
                    success: function (data) {
                        if (data.success) {
                            $.messager.alert('提示', '操作成功');
                            $("#res_group_datagrid").treegrid("reload");
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

function editResSubmit() {
    $('#editResForm').form("submit", {
        url: "admin/resouces/add",
        method: "post",
        success: function (data) {
            var data = eval("(" + data + ")")
            if (data.success) {
                $('#editResForm').form("clear");
                $("#edit_res_record").dialog("close");
                $("#res_group_datagrid").treegrid("reload");
                $.messager.alert("提示", "操作成功");
            } else {
                $.messager.alert("提示", "操作失败", "warning");

            }
        }
    });
}
function sys_res_find(){
    var res_name = $("#s_sys_res_name").val();
    var params = {
        params:{
            resourceName:res_name
        }
    }
    $("#res_group_datagrid").treegrid({
        queryParams:params
    });
}



