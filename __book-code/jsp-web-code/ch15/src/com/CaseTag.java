package com;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

public class CaseTag extends TagSupport{
    private boolean cond;
    
    public CaseTag(){
        cond=false;
    }
    
    public void release(){
        cond=false;
    }
    
    public void setCond(boolean cond){
        this.cond=cond;
    }
    
    public int doStartTag() throws JspException{
        Tag parent=getParent();

       //�ж��Ƿ����ִ������ı���塣
        if (!((SwitchTag) parent).getPermission())
            return SKIP_BODY;

        //�������Ϊtrue����֪ͨ����ǣ��Ѿ���һ���ӱ�����������ˡ�
        //���򣬺��Ա���塣
        if (cond) {
            ((SwitchTag)parent).subTagSucceeded();
           
            return EVAL_BODY_INCLUDE;
        } else{
            return SKIP_BODY;
        }
    }
}