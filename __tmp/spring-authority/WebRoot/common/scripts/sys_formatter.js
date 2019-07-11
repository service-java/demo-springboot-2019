function authorityResReader(value, row, index) {
    if (row && row.authorityRes == 0) {
        return "<font color='red'>0</font> "
    } else {
        return row.authorityRes;
    }
}

function authuorityEnabledReader(value, row, index) {
    if (row && row.enabled) {
        return "有效";
    } else if (row && !row.enabled) {
        return "<font color='red'>无效</font>"
    }
    return "";
}

function enabledReader(value, row, index) {
    if (row.enabled) {
        return "已启用";
    } else {
        return "<font color='red'>已禁用</font>";
    }
}

function isUserLocaked(value, row, index) {
    if (row.accountNonLocked) {
        return "未锁定";
    } else {
        return "<font color='red'>已锁定</font>";
    }
}

function roleenabledReader(value, row, index) {
    if (row.enabled) {
        return "是";
    } else {
        return "<font color='red'>否</font>";
    }
}

function authorityCount(value, row, index) {
    if (row.authorityCount > 0) {
        return row.authorityCount;
    } else {
        return "<font color='red'>" + row.authorityCount + "</font>";
    }
}


//function enabledReader(value, row, index) {
//    if (row.enabled == 1) {
//        return "是";
//    } else {
//        return "<font color='red'>否</font>";
//    }
//}

function userTypeReader(value, row, index) {
    if (row.userType == "1") {
        return "普通用户";
    } else {
        return "<font color='red'>系统用户</font>";
    }
}

function userRoleReader(value, row, index) {
    if (row.userRole != "0") {
        return "已分配";
    } else {
        return "<font color='red'>未分配</font>";
    }
}

function sysfileSizedReader(value, row, index) {
    if (row.size) {
        var s =  (row.size/1024);
        if(s<1024){
            return s+" KB";
        }else if(s>1024 && s<1024*1024){
            return s/1024+" MB";
        }else if(s>1024*1024 && s<1024*1024*1024){
            return s/(1024*1024)+" GB";
        }
    } else {
        return "";
    }
}

function myformatter(date){
    var y = date.getFullYear();
    var m = date.getMonth()+1;
    var d = date.getDate();
    return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
}
function myparser(s){
    if (!s) return new Date();
    var ss = (s.split('-'));
    var y = parseInt(ss[0],10);
    var m = parseInt(ss[1],10);
    var d = parseInt(ss[2],10);
    if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
        return new Date(y,m-1,d);
    } else {
        return new Date();
    }
}