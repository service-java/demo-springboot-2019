//����
function addRole() {
    $("#add_role_record").dialog("open");
    $('#addRoleForm').form("clear");
}
//    �޸�
function editRole() {

    var res = $("#role_datagrid").datagrid("getSelected");
    if (res) {
        customerShowDialog({url:'admin/role/edit/'+res.id,id:'add_role_record',title:'��ɫ��Ϣ',width:660,height:260});
//        $("#edit_role_record").dialog({
//            onLoad:function(){
//                $("#editRoleForm").form("load", res);
//            }
//        });
//        $("#edit_role_record").dialog("open");
    } else {
        alert("��ѡ��Ҫ�޸ĵļ�¼");
    }
}
//�ύ��
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
                $.messager.alert("��ʾ", "�����ɹ�");
            } else {
                $.messager.alert("��ʾ", "����ʧ��", "warning");
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
                $.messager.alert("��ʾ", "�����ɹ�");
            } else {
                $.messager.alert("��ʾ", "����ʧ��", "warning");
            }
        }
    });
}
//           �鿴��ɫȨ��
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
        $.messager.alert("��ʾ","��ѡ��Ҫ�鿴�ļ�¼")
    }

}
function delRole() {
    var role = $("#role_datagrid").datagrid("getSelected");
    if (role && role.id) {
        $.messager.confirm('����', 'ȷ��Ҫɾ��ѡ�еļ�¼��?', function (r) {
            if (r) {
                $.ajax({
                    url: 'admin/role/delete',
                    dataType: 'json',
                    type: "POST",
                    data: {roleId: role.id},
                    success: function (data) {
                        if (data.success) {
                           showMessager("�����ɹ���");
                            $("#role_datagrid").datagrid("reload");
                        } else {
                            $.messager.alert('��ʾ', '����ʧ��', "warning");
                        }
                    }
                })
            } else {
                return;
            }
        });
    } else {
        $.messager.alert("����", "��ѡ��Ҫɾ���ļ�¼", "error");
    }
}
//    ��ʾ��ӽ�ɫȨ��
function addRoleAuthority() {
    $("#role_authority_list_select").dialog("open");
    $("#sys_authority_datagrid_select").datagrid("load")
}

//��ӽ�ɫȨ��
function addSelectAuthToRole() {
    //��ȡѡ��Ȩ��
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
        //��ȡѡ�н�ɫ
        var _roleId = $("#role_datagrid").datagrid("getSelected").id;
        $.ajax({
            url: 'admin/role/authority/add',
            dataType: 'json',
            type: 'POST',
            data: {authIds: ids, roleId: _roleId},
            success: function (data) {
                if (data.success) {
                   showMessager("�����ɹ���");
                    $("#role_authority_datagrid").datagrid("reload");
                    $("#role_authority_list_select").dialog("close");

                } else {
                    $.messager.alert('��ʾ', '����ʧ��', "warning");
                }
            }
        });

    } else {
        alert("��ѡ����Դ");
    }
}
//ɾ����ɫȨ��
function delRoleAuthority() {
    //��ȡѡ��Ȩ��
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
        //��ȡѡ�н�ɫ
        var _roleId = $("#role_datagrid").datagrid("getSelected").id;
        $.ajax({
            url: 'admin/role/authority/delete',
            dataType: 'json',
            type: 'POST',
            data: {authIds: ids, roleId: _roleId},
            success: function (data) {
                if (data.success) {
                   showMessager("�����ɹ���");
                    $("#role_authority_datagrid").datagrid("reload");
                } else {
                    $.messager.alert('��ʾ', '����ʧ��', "warning");
                }
            }
        });

    } else {
        alert("��ѡ����Դ");
    }
}

