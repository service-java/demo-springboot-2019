//����
function addRes() {
    $("#add_res_record").dialog("open");
    var res = $("#res_group_datagrid").datagrid("getSelected");
    $('#addResForm').form("clear");
    if (res) {
        $("#res_parent_id").val(res.id);
        $("#res_parent_name").val(res.resourceName)
    }
}
//�޸�
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
        $.messager.alert("��ʾ","��ѡ��Ҫ�޸ĵļ�¼","warning");
    }
}
//�ύ��
function addResSubmit() {
    $('#addResForm').form("submit", {
        url: "admin/resouces/add",
        method: "post",
        success: function (data) {
            var data = eval("(" + data + ")")
            if (data.success) {
                $('#addResForm').form("clear");
                $("#add_res_record").dialog("close");
                showMessager("�����ɹ���");
                $("#res_group_datagrid").treegrid("reload");
            } else {
                $.messager.alert("��ʾ", "����ʧ��", "warning");

            }
        }
    });
}

function delRes() {
    var _res = $("#res_group_datagrid").datagrid("getSelected");
    if (_res) {
        $.messager.confirm('����', 'ȷ��Ҫɾ��ѡ�еļ�¼��?', function (r) {
            if (r) {
                $.ajax({
                    url: 'admin/resouces/delete',
                    dataType: 'json',
                    type: 'POST',
                    data: {resid: _res.id},
                    success: function (data) {
                        if (data.success) {
                            $.messager.alert('��ʾ', '�����ɹ�');
                            $("#res_group_datagrid").treegrid("reload");
                        } else {
                            $.messager.alert('��ʾ', '����ʧ��', "warning");
                        }
                    }
                });
            }
            return;
        });
    } else {
        $.messager.alert("��ʾ", "��ѡ��Ҫɾ���ļ�¼", "warning");
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
                $.messager.alert("��ʾ", "�����ɹ�");
            } else {
                $.messager.alert("��ʾ", "����ʧ��", "warning");

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



