
function addNewRecord() {
    $("#add_record").window({
        width: 600,
        height: 450,
        modal: true
    });
}
function confirmAlert() {
    $.messager.confirm('警告', '确定要删除选中的记录吗?', function (r) {
        if (r) {
            alert($("#my_datagrid").datagrid("getSelected").code);
        }
    });
}


$(document).ready(function () {

    var type = $.cookie("dataType");
        $("#hos_data_src_type").val(type);
        $.cookie("dataType",$("#hos_data_src_type").val());
    $.ajaxSetup({
        complete: function (XMLHttpRequest, textStatus) {
            var sessionStatus = XMLHttpRequest.status;
            var status = null;
            try {
                status = parseInt(sessionStatus);
            } catch (err) {
            }
            if (status) {
                switch (status) {
                /**
                 *请求错误，请求不满足要求
                 */
                    case 400:
                        $.messager.alert("错误", "请求错误", "error");
                        break;
                    case 404:
                        $.messager.alert("错误","未找到资源","error");
                        break;
                    /*
                     *未授权
                     * */
                    case 401:
                        $.messager.alert("错误", "无权访问，您没有访问该资源的权限", "error");
                        break;
                    /*
                     *禁止访问
                     * */
                    case 403:
                        $.messager.alert("提示","登陆超时，请重新登陆",null,function(){
                            window.location='j_spring_security_logout';
                        });
                        break;
                    case 406:
                        $.messager.alert("提示","您的账号已在别处登录，请重新登陆",null,function(){
                            window.location='j_spring_security_logout';
                        });
                        break;
                    /*
                     *找不到访问的资源
                     * */
                    case 405:
                        $.messager.alert("错误", "请求错误,找不到访问的资源", "error");
                        break;
                /**
                 *未登陆
                 */
                    case 407:
                        $.messager.alert("错误", "尚未登录，请重新登录", "error");
                        break;
                /**
                 *服务器内不错
                 */
                    case 500:
                        $.messager.alert("错误", "请求出错，请尝试重新登录系统", "error");
                        break;

                }
                closeProgress();
            }

        }

    });

    $.parser.auto = true;
    $("#main_menu").tree({
        onClick: function (node) {
            if(!$('#main_menu').tree('isLeaf', node.target)){
                $('#main_menu').tree('toggle', node.target)
                return;
            }

            if (node.attributes && node.attributes.url) {
                var tab = $("#main_tab").tabs("getTab", node.text);
                if (tab) {
                    $("#main_tab").tabs("select", node.text);
                } else {
                    var url = node.attributes.url;
                    if(url.substring(0,1)=="/"){
                        url = url.substring(1,url.length);
                    }
                    $("#main_tab").tabs("add", {
                        title: node.text,
                        id: node.id,
                        href:url + "?code=" + node.id,
                        selected: true,
                        closable: true,
                        fit: true

                    });
                }
            }
            return;
        }
    });
});

function changeTheme(ele){
    var theme = $(ele).val();
    if(theme){
        var $easyuiTheme = $("#easyui_theme");
        var url = $easyuiTheme.attr("href");
        var href = url.substring(0,url.indexOf('themes'))+'themes/'+theme+"/easyui.css";
        $easyuiTheme.attr("href",href);
//        $(document.body).layout("resize");
        $.cookie("themes",theme,{
            expires:7
        })
    }
}

function changeUserPassword(){
    $('#change_user_password_dialog').dialog({
        title: '修改密码',
        width: 310,
        height: 200,
        closed: false,
        cache: false,
        href:'admin/user/modifypassword',
        method:'get',
        modal: true,
        buttons:[
            {
                text:'确认',
                handler:submitModifyPassword
            },
            {
                text:'重置',
                handler:resetModfiyPassword
            }
        ]
    });
//    $('#change_user_password_dialog').dialog("refresh","admin/user/modifypassword");
}
function submitModifyPassword(){
    $("#change_user_password_form").form("submit",{
            url: "admin/user/modifypassword",
            method:"post",
            success: function (data) {
                var data = eval("(" + data + ")");
                if (data.success) {
                    $('#change_user_password_form').form("clear");
                    $("#change_user_password_dialog").dialog("close");
                    $.messager.alert("提示", "操作成功");
                } else {
                    $.messager.alert("提示", "操作失败", "warning");
                }
         }
    });
}

function addTabInMain(config){
    if(config && config.url && config.title && config.id){
        var tab = $("#main_tab").tabs("getTab",config.title);
        if (tab) {
            tab.panel('refresh', config.url);
            $("#main_tab").tabs("select", config.title);//.panel("options").tab("show",{});

        } else {
            var url = config.url;
            if(url.substring(0,1)=="/"){
                url = url.substring(1,url.length);
            }
            $("#main_tab").tabs("add", {
                title: config.title,
                id: config.id,
                href:url,
                selected: true,
                closable: true,
                fit: true

            });
        }
    }
}

function closeTabInMain(tabId){
    if(tabId)
    {
        $("#main_tab").tabs("close",tabId);
    }
}


function to2bits(flt) {
    if(flt){

        if(parseFloat(flt) == flt)
            return Math.round(flt * 100) / 100;
// 到4位小数, return Math.round(flt * 10000) / 10000;
        else
            return 0;
    }else{
        return "--";
    }
}

function numberformatter(old){
    //拷贝一份 返回去掉零的新串
    var newstr=old;
    //循环变量 小数部分长度
    var leng = old.length-old.indexOf(".")-1
    //判断是否有效数
    if(old.indexOf(".")>-1){
        //循环小数部分
        for(i=leng;i>0;i--){
            //如果newstr末尾有0
            if(newstr.lastIndexOf("0")>-1 && newstr.substr(newstr.length-1,1)==0){
                var k = newstr.lastIndexOf("0");
                //如果小数点后只有一个0 去掉小数点
                if(newstr.charAt(k-1)=="."){
                    return  newstr.substring(0,k-1);
                }else{
                    //否则 去掉一个0
                    newstr=newstr.substring(0,k);
                }
            }else{
                //如果末尾没有0
                return newstr;
            }
        }
    }
    return old;
}
function resetModfiyPassword(){
    $("#change_user_password_form").form("clear");
}

function showProgress(text){
    $("#sys_loading_dialog").show();
    $("#sys_loading_dialog").dialog("open");
}
function closeProgress(){
    $("#sys_loading_dialog").dialog("close");
    $("#sys_loading_dialog").hide();
}
function mainTreeExpandAll(){
    $("#main_menu").tree("expandAll")
}
function mainTreeUnExpandAll(){
    $("#main_menu").tree("collapseAll");
}
function closeAllTabs(){
    var allTab = $("#main_tab").tabs("tabs");
    if(allTab){
//        alert($(this).tabs("title"));
        var tab_length = allTab.length;
        for(var i=0;i<tab_length;i++){
            $("#main_tab").tabs("close",i);
        }
    }
}
