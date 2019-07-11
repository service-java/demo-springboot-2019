/**
 * Created with IntelliJ IDEA.
 * User: Tim
 * Date: 12-12-22
 * Time: 下午2:39
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
                    showMessager("操作成功！");
                    $("#sys_rolemenu_list").treegrid({
                        url:"admin/rolemenu/list/"+selected
                    });
                    $("#sys_rolemenu_list").treegrid("reload");
                } else {
                    $.messager.alert('提示', '操作失败', "warning");
                }
            }
        })
    }else{
        $.messager.alert("提示","请选择要创建菜单的角色!");
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
                    showMessager("操作成功！");
                    reloadRoleMenuRoot();
                } else {
                    $.messager.alert('提示', '操作失败', "warning");
                }
            }
        })
    }else{
        $.messager.alert("提示","请选择要创建菜单的角色!");
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
                    showMessager("操作成功！");
                    reloadRoleMenuRoot();
                } else {
                    $.messager.alert('提示', '操作失败', "warning");
                }
            }
        })
    }else{
        $.messager.alert("提示","请选择要创建菜单的角色!");
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
                     $.messager.alert('提示', '操作失败', "warning");
                 }
             }
         })
     }


 }
