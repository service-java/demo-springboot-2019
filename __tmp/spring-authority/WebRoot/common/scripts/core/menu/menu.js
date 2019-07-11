var sys_menu_now_action = "";
function addMenu(){
    var menu = $("#sys_menu_list").treegrid("getSelected");
    sys_menu_now_action = "ADD";
        $("#sys_menu_add_dialog").dialog({
            onLoad:function(){
                if(menu){
                    $("#menu_parent_name").val(menu.name);
                    $("#menu_parent_id").val(menu.id);
                }
            }
        });
        $("#sys_menu_add_form").form("clear");
        try{
            if(menu){
                $("#menu_parent_name").val(menu.name);
                $("#menu_parent_id").val(menu.id);
            }
        }catch(err){

        }
        $("#sys_menu_add_dialog").dialog("open");

}

function saveMenu(){
    $('#sys_menu_add_form').form("submit", {
        url: "admin/menu/add",
        method: "post",
        success: function (data) {
            var data = eval("(" + data + ")");
            if (data.success) {
                $('#sys_menu_add_form').form("clear");
                $("#sys_menu_add_dialog").dialog("close");
                $("#sys_menu_list").treegrid("reload");
                showMessager("操作成功！");
            } else {
                $.messager.alert("提示", "操作失败", "warning");
            }
        }
    });
}

function saveUpdateMenu(){
    $('#sys_menu_edit_form').form("submit", {
        url: "admin/menu/add",
        method: "post",
        success: function (data) {
            var data = eval("(" + data + ")");
            if (data.success) {
                $('#sys_menu_edit_form').form("clear");
                $("#sys_menu_edit_dialog").dialog("close");
                $("#sys_menu_list").treegrid("reload");
                $.messager.alert("提示", "操作成功");
            } else {
                $.messager.alert("提示", "操作失败", "warning");
            }
        }
    });
}


function editMenu(){
    var menu = $("#sys_menu_list").treegrid("getSelected");
    sys_menu_now_action = "EDIT";
    if(menu){
        $("#sys_menu_edit_dialog").dialog({
            onLoad:function(){
                $("#sys_menu_edit_form").form("load",menu);
                $("#sys_menu_edit_form input[name='resouces_name']").val(menu.resourcePath);
                $("#sys_menu_edit_form input[name='rescources.id']").val(menu.resourceId);
            }
        });
        $("#sys_menu_edit_dialog").dialog("open");
        try{
            $("#sys_menu_edit_form").form("load",menu);
            $("#sys_menu_edit_form input[name='resouces_name']").val(menu.resourcePath);
            $("#sys_menu_edit_form input[name='rescources.id']").val(menu.resourceId);

        }catch (err){

        }
    }else{
        $.messager.alert("提示","请选择要编辑的记录");
    }
}



function selectedMenuResouces(){
    var data = $("#menu_resouces_select_list").treegrid("getSelected");
    if(data){
        $("#sys_menu_add_form input[name='resouces_name']").val(data.resourcePath);
        $("#sys_menu_add_form input[name='rescources.id']").val(data.id);
    }
    $("#sys_menu_resoucse_select_dialog").dialog("close");
}

function editSelectedMenuResouces(){
    var data = $("#menu_resouces_select_list").treegrid("getSelected");
    if(data){
        $("#sys_menu_edit_form input[name='resouces_name']").val(data.resourcePath);
        $("#sys_menu_edit_form input[name='rescources.id']").val(data.id);
    }
    $("#sys_menu_resoucse_edit_select_dialog").dialog("close");
}
function addMenuResouces(){
    $("#sys_menu_resoucse_select_dialog").dialog("open");
}
function menuIconSelect(){
    var iconClss = $("input[name='sysiconStyle']:radio:checked").val();
    if(sys_menu_now_action == "EDIT"){
        $("#sys_menu_edit_form input[name='iconStyle']").val(iconClss);
    }else{
        $("#sys_menu_add_form input[name='iconStyle']").val(iconClss);
    }
    $("#sys_menu_icon_dialog").dialog("close");
}

function addEditMenuResouces(){
    $("#sys_menu_resoucse_edit_select_dialog").dialog("open");
}

function addMenuIcon(){
 $("#sys_menu_icon_dialog").dialog("open");
}

function delMenu(){
    var menu = $("#sys_menu_list").treegrid("getSelected");
    if(menu){
        $.ajax({
            url: 'admin/menu/delete',
            dataType: 'json',
            type: 'POST',
            data: {menuid: menu.id},
            success: function (data) {
                if (data.success) {
                    $.messager.alert('提示', '操作成功');
                    $("#role_authority_datagrid").treegrid("reload");
                } else {
                    $.messager.alert('提示', '操作失败', "warning");
                }
            }
        });
    }else{
        $.messager.alert("提示","请选择要删除的记录");
    }


}
