/**
 * Created with IntelliJ IDEA.
 * User: Tim
 * Date: 12-12-22
 * Time: ����2:39
 * To change this template use File | Settings | File Templates.
 */
function autoCreateRoleMenu(){
    var selected = $("#sys_rolemenu_simplerolelist").combobox("getValue");
    if(selected){
        showProgress();
        $.ajax({
            url: 'admin/rolemenu/autocreate/'+selected,
            dataType: 'json',
            type: "POST",
            success: function (data) {
                closeProgress();
                if (data.success) {
                    showMessager("�����ɹ���");
                    $("#sys_rolemenu_list").treegrid({
                        url:"admin/rolemenu/list/"+selected
                    });
                    $("#sys_rolemenu_list").treegrid("reload");
                } else {
                    $.messager.alert('��ʾ', '����ʧ��', "warning");
                }
            }
        })
    }else{
        $.messager.alert("��ʾ","��ѡ��Ҫ�����˵��Ľ�ɫ!");
    }
}

function changeRole(newValue,oldValue){
    if(newValue){
        try{
            $("#sys_rolemenu_list").treegrid("remove", $("#sys_rolemenu_list").treegrid("getRoot"));
        }catch (err){

        }
        $("#sys_rolemenu_list").treegrid({
            url:"admin/rolemenu/list/"+newValue
        });
        $("#sys_rolemenu_list").treegrid("reload");
    }
}

function roleMenuMoveUp(){
    var selected = $("#sys_rolemenu_simplerolelist").combobox("getValue");
    var selTreeNode = $("#sys_rolemenu_list").treegrid("getSelected");
    if(selected && selTreeNode){
        showProgress();
        $.ajax({
            url: 'admin/rolemenu/moveUp/',
            dataType: 'json',
            type: "POST",
            data:{roleid:selected,menuid:selTreeNode.id},
            success: function (data) {
                if (data.success) {
                    closeProgress();
                    showMessager("�����ɹ���");
                    reloadRoleMenuRoot();
                } else {
                    $.messager.alert('��ʾ', '����ʧ��', "warning");
                }
            }
        })
    }else{
        $.messager.alert("��ʾ","��ѡ��Ҫ�����˵��Ľ�ɫ!");
    }
}

function roleMenuMoveDown(){
    var selected = $("#sys_rolemenu_simplerolelist").combobox("getValue");
    var selTreeNode = $("#sys_rolemenu_list").treegrid("getSelected");
    if(selected && selTreeNode){
        showProgress();
        $.ajax({
            url: 'admin/rolemenu/moveDown/',
            dataType: 'json',
            type: "POST",
            data:{roleid:selected,menuid:selTreeNode.id},
            success: function (data) {
                if (data.success) {
                    closeProgress();
                    showMessager("�����ɹ���");
                    reloadRoleMenuRoot();
                } else {
                    $.messager.alert('��ʾ', '����ʧ��', "warning");
                }
            }
        })
    }else{
        $.messager.alert("��ʾ","��ѡ��Ҫ�����˵��Ľ�ɫ!");
    }
}

 function reloadRoleMenuRoot(){
     var selected = $("#sys_rolemenu_simplerolelist").combobox("getValue");
     if(selected){
         showProgress();

         $.ajax({
             url: 'admin/rolemenu/list/'+selected,
             dataType: 'json',
             type: "POST",
             success: function (data) {
                 closeProgress();
                 if (data) {
                      $("#sys_rolemenu_list").treegrid("loadData",data);
                 } else {
                     $.messager.alert('��ʾ', '����ʧ��', "warning");
                 }
             }
         })
     }


 }
