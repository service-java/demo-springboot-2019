package com.authority.utils;

import java.util.ArrayList;
import java.util.Collection;

public class MessageBox {
    private Boolean success=false;
    private Collection rows = new ArrayList();
    private Object total=0l;
    private String result;
    private Object msg;

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

//    @JsonSerialize(using = JsonDateSerializer.class)
    public Collection getRows() {
        return rows;
    }

    public void setRows(Collection rows) {
        if(rows != null && rows.size()>0)
        {
            this.rows = rows;
        }
    }

    public Object getTotal() {
        return total;
    }

    public void setTotal(Object total) {
        if(total != null)
        {
            this.total = total;
        }
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
