package com;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

public class DefaultTag extends TagSupport{
    public int doStartTag() throws JspException {
        Tag parent=getParent();
        
        //�жϱ�����Ƿ����ִ�� ��
        if (!((SwitchTag) parent).getPermission())
            return SKIP_BODY;

        //���û��<case>���������������ִ��<default>��ǵı���塣
        ((SwitchTag)parent).subTagSucceeded();
        return EVAL_BODY_INCLUDE;
    }
}