function submitCustomerForm(config){
    if(config){
        var form = null;
        if(config.ele &&$(config.ele).closest("form")){
            form = $(config.ele).closest("form");
        }else if($.trim(config.formId)){
            form = $("#"+config.form);
        }
        if(form && $.trim(config.url)){
            if(!$(form).form("validate")){
                return;
            }
            showMask();
            $(form).form("submit",{
                url:config.url,
                success:function(data){
                    hideMask();
                    if($.trim(config.dialog)){
                        $("#"+config.dialog).panel("close");
//                                $(form).form("reset");
                    }
                    try{
                        var json = eval("("+data+")");
                        if(json &&json.success){
                            if($.trim(config.grid)){
                                if($("#"+config.grid).attr("class")=="easyui-treegrid"){
                                    var options = $("#"+config.grid).treegrid("options");
                                    if(options && options['queryParams']){
                                        var node = $("#"+config.grid).treegrid("getSelected");
                                        if(node&&node._parentId){
                                            $("#"+config.grid).treegrid("reload",node._parentId);
                                        }else{
                                            $("#"+config.grid).treegrid("reload");
                                        }
                                    }
                                }else{
                                    $("#"+config.grid).datagrid("reload");
                                }
                            }
                            $.messager.show({
                                title:'提示',
                                msg:'保存记录成功',
                                timeout:2500,
                                showType:'show',
                                style:{
                                    right:'',
                                    top:document.body.scrollTop+document.documentElement.scrollTop,
                                    bottom:''
                                }
                            })

                        }else{
                            $.messager.show("提示","操作失败");
                        }
                    }catch (e){

                    }
                }
            })
        }
    }
}
function submitCustomerFormWithTree(config){
    if(config){
        var form = null;
        if(config.ele &&$(config.ele).closest("form")){
            form = $(config.ele).closest("form");
        }else if($.trim(config.formId)){
            form = $("#"+config.form);
        }
        if(form && $.trim(config.url)){
            if(!$(form).form("validate")){
                return;
            }
            showMask();
            $(form).form("submit",{
                url:config.url,
                success:function(data){
                    hideMask();
                    try{
                        var json = eval("("+data+")");
                        if(json &&json.success){
                            if($.trim(config.dialog)){
                                $("#"+config.dialog).dialog("close");
//                                $(form).form("reset");
                            }
                            if($.trim(config.grid)){
                                if($("#"+config.grid).attr("class")=="easyui-treegrid"){
                                    if(config.target&&config.target=='self'){
                                        var options = $("#"+config.grid).treegrid("options");
                                        if(options && options['queryParams']){
                                            var node = $("#"+config.grid).treegrid("getSelected");
                                            var child = $('#'+config.grid).treegrid('getChildren', node.id);
//                                          如果不是叶子节点则刷新本身
                                            if(child&&child.length>0){
                                                $("#"+config.grid).treegrid("reload",node.id);
//                                          如果是叶子几点则刷新父节点
                                            }else{
                                                if(node&&node._parentId){
                                                    $("#"+config.grid).treegrid("reload",node._parentId);
                                                }else{
                                                    $("#"+config.grid).treegrid("reload");
                                                }
                                            }
                                        }
                                    }else{
                                        var options = $("#"+config.grid).treegrid("options");
                                        if(options && options['queryParams']){
                                            var node = $("#"+config.grid).treegrid("getSelected");
                                            if(node&&node._parentId){
                                                $("#"+config.grid).treegrid("reload",node._parentId);
                                            }else{
                                                $("#"+config.grid).treegrid("reload");
                                            }
                                        }
                                    }
                                }else{
                                    $("#"+config.grid).datagrid("reload");
                                }
                            }
                            $.messager.show({
                                title:'提示',
                                msg:'保存记录成功',
                                timeout:2500,
                                showType:'show',
                                style:{
                                    right:'',
                                    top:document.body.scrollTop+document.documentElement.scrollTop,
                                    bottom:''
                                }
                            })
                        }else{
                            $.messager.show("提示","操作失败");
                        }
                    }catch (e){

                    }
                }
            })
        }
    }
}
function searchCustomerGrid(config){
    if(config && config.ele){
        var params = {};
        $(config.ele).closest("form").find("input").each(function(){
            if($(this).attr("name") &&$.trim($(this).val())){
                if(params[$(this).attr("name")]){
                    params[$(this).attr("name")]=params[$(this).attr("name")]+","+$(this).val();
                }else
                {
                    params[$(this).attr("name")]=$(this).val();
                }
            }
        });
        $(config.ele).closest("form").find("select").each(function(){
//            if($(this).attr("name") &&$.trim($(this).val())){
//                params[$(this).attr("name")]=$(this).val();
//            }
            if(params[$(this).attr("name")]){
                params[$(this).attr("name")]=params[$(this).attr("name")]+","+$(this).val();
            }else
            {
                params[$(this).attr("name")]=$(this).val();
            }
        });
        if(config.grid){
            $("#"+config.grid).datagrid({
                queryParams:{params:params},
                pageNumber:1
            })
        }
    }
}
function resetSearchCustomerGrid(config){
    if(config&&config.ele){
        $($(config.ele).closest("form")).form("reset");
        $("#"+config.grid).datagrid({
            queryParams:{},
            pageNumber:1
        })
    }
}
function customerDelRow(config){
    if(config&& $.trim(config.url).length>0&& $.trim(config.grid).length>0){
        var row = $("#"+config.grid).datagrid("getSelected");
        if(row&&row.id){
            $.messager.confirm("提示","确定要删除所选记录吗",function(r){
                if(r){
                    showMask();
                    $.ajax({
                        type:'post',
                        dataType:'json',
                        url:config.url+"/"+row.id,
                        success:function(data){
                            if(data && data.success){
                                hideMask();
                                $("#"+config.grid).datagrid("reload");
                                $.messager.show({
                                    title:'提示',
                                    msg:'删除记录成功',
                                    timeout:2500,
                                    showType:'show',
                                    style:{
                                        right:'',
                                        top:document.body.scrollTop+document.documentElement.scrollTop,
                                        bottom:''
                                    }
                                })
                            }else{
                                $.messager.alert("提示","操作失败");
                            }
                        }
                    })
                }else{
                    return;
                }

            })
        }else{
            $.messager.alert("提示","请选择要删除的记录")
        }

    }
}function customerDelTreeRow(config){
    if(config&& $.trim(config.url).length>0&& $.trim(config.grid).length>0){
        var row = $("#"+config.grid).treegrid("getSelected");
        if(row&&row.id){
            $.messager.confirm("提示","确定要删除所选记录吗",function(r){
                if(r){
                    showMask();
                    $.ajax({
                        type:'post',
                        dataType:'json',
                        url:config.url+"/"+row.id,
                        success:function(data){
                            if(data && data.success){
                                hideMask();
                                if(row._parentId){
                                    $("#"+config.grid).treegrid("reload",row._parentId);
                                }else{
                                    $("#"+config.grid).treegrid("reload");
                                }
                                $.messager.show({
                                    title:'提示',
                                    msg:'删除记录成功',
                                    timeout:2500,
                                    showType:'show',
                                    style:{
                                        right:'',
                                        top:document.body.scrollTop+document.documentElement.scrollTop,
                                        bottom:''
                                    }
                                })
                            }else{
                                $.messager.alert("提示","操作失败");
                            }
                        }
                    })
                }else{
                    return;
                }
            })
        }else{
            $.messager.alert("提示","请选择要删除的记录")
        }

    }
}
function customerShowDialog(config){
    if(config.url&&config.title,config.id){
        if(config.width == null){
            config.width=500;
        }
        if(config.height==null){
            config.height==600;
        }
        if(config.padding==null){
            config.padding=10;
        }
        if(config.buttons==null){
            config.buttons=[];
        }
        var dialog = $("#"+config.id);
        if(dialog.length==0){
            $(document.body).append("<div style='padding: "+config.padding+"px' id='"+config.id+"' ></div>");
        }
        $("#"+config.id).dialog({
            title:config.title,
            href:config.url,
            modal:true,
            style:{padding:5},
            width:config.width,
            height:config.height,
            buttons:config.buttons,
            onClose:function(){
                var panel = $("#"+config.id).closest(".panel");
                $(panel).next(".window-shadow").remove();
                $(panel).next(".window-mask").remove();
                $(panel).nextAll(".panel").remove();
                $(panel).remove();
                $("#"+config.id).remove();
            },
            cache:false
        }).dialog("open");
    }
}
function customerShowWindow(config){
    if(config.url&&config.title,config.id){
        if(config.width == null){
            config.width=500;
        }
        if(config.height==null){
            config.height==600;
        }
        if(config.padding==null){
            config.padding=10;
        }
        if(config.buttons==null){
            config.buttons=[];
        }
        var dialog = $("#"+config.id);
        if(dialog.length==0){
            $(document.body).append("<div style='padding: "+config.padding+"px' id='"+config.id+"' ></div>");
        }
        $("#"+config.id).dialog({
            title:config.title,
            href:config.url,
            modal:true,
            style:{padding:5},
            width:config.width,
            height:config.height,
            buttons:config.buttons,
            onClose:function(){
                var panel = $("#"+config.id).closest(".panel");
                $(panel).next(".window-shadow").remove();
                $(panel).next(".window-mask").remove();
                $(panel).nextAll(".panel").remove();
                $(panel).remove();
                $("#"+config.id).remove();
            },
            cache:false
        }).dialog("open").dialog("maximize");
    }
}
function customerShowTempDialog(config){
    if(config&&config.url&&config.title){
        if(config.width==null){
            config.width=600;
        }
        if(config.height==null){
            config.height=400;
        }
        if(config.buttons==null){
            config.buttons=[];
        }
        var id = new Date().getTime();
        $(document.body).append("<div id='"+id+"'></div>");
        $("#"+id).dialog({
            title:config.title,
            href:config.url,
            modal:true,
            width:config.width,
            height:config.height,
            onClose:function(){
                var panel = $("#"+id).closest(".panel");
                $(panel).next(".window-shadow").remove();
                $(panel).next(".window-mask").remove();
                $(panel).remove();
            },
            cache:false,
            buttons:config.buttons
        })
        return id;
    }

}