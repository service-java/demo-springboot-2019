function addUser() {
    $("#add_user_record").dialog("open");
}
//  �޸��û���Ϣ
function editUser() {
    var user = $("#user_datagrid").datagrid("getSelected");
    if (user && user.id) {
        customerShowDialog({url:'admin/user/edit/'+user.id,id:'add_user_record',title:'�û���Ϣ',width:620,height:260});
//        $("#edit_user_record").dialog("refresh","admin/user/edit/"+user.id).dialog("open");
    } else {
        $.messager.alert("����", "��ѡ��Ҫ�޸ĵļ�¼��", "error");
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
                showMessager("�����ɹ���");
            } else {
                $.messager.alert('��ʾ', '����ʧ�ܣ�', "warning");
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
                showMessager("�����ɹ���");
            } else {
                $.messager.alert('��ʾ', '����ʧ�ܣ�', "warning");
            }
        }
    });
}
function delUser() {
    var user = $("#user_datagrid").datagrid("getSelected");
    if (user && user.id) {
        $.messager.confirm('����', 'ȷ��Ҫɾ��ѡ�еļ�¼��?', function (r) {
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
                            showMessager("�����ɹ���");
                            $("#user_datagrid").datagrid("reload", {});
                        } else {
                            $.messager.alert('��ʾ', '����ʧ�ܣ�', "warning");
                        }
                    }
                })
            } else {
                return;
            }
        });
    } else {
        $.messager.alert("����", "��ѡ��Ҫɾ���ļ�¼��", "error");
    }
}

//���ѡ�н�ɫ���û�
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
                    showMessager("�����ɹ���");
                    $("#user_role_select_dialog").dialog("close");
                    $("#user_role_datagrid").datagrid("reload");
                } else {
                    $.messager.alert('��ʾ', '����ʧ��', "warning");
                }
            }
        });

    } else {
        $.messager.alert("����","��ѡ��Ҫ��ӵĽ�ɫ��");
    }
}

//��ʾϵͳ��ɫ
function addUserRole() {
    $("#user_role_select_dialog").dialog("open");
//    $("#user_role_select_datagrid").datagrid("load");
}
//ɾ���û���ɫ
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
                    showMessager("�����ɹ���");
                    $("#user_role_datagrid").datagrid("reload");
                } else {
                    $.messager.alert('��ʾ', '����ʧ��', "warning");
                }
            }
        });
    } else {
        $.messager.alert('��ʾ', '��ѡ��Ҫɾ���Ľ�ɫ', "warning");
    }
}
//��ʾ�û���ɫ
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
        $.messager.alert("��ʾ", "����ѡ���û�", "warning");
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
                    showMessager("�����ɹ���");
                    $("#user_datagrid").datagrid("reload");
                } else {
                    $.messager.alert('��ʾ', '����ʧ��', "warning");
                }
            }
        });
    }else{
        $.messager.alert("��ʾ","����ѡ���û�!");
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
                    showMessager("�����ɹ���");
                    $("#user_datagrid").datagrid("reload");
                } else {
                    $.messager.alert('��ʾ', '����ʧ��', "warning");
                }
            }
        });
    }else{
        $.messager.alert("��ʾ","����ѡ���û�!");
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
        message: '�����������벻һ��'
    }
});

