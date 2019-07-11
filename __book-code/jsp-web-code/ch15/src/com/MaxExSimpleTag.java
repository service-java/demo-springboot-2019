package com;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

public class MaxExSimpleTag extends SimpleTagSupport implements DynamicAttributes
{
    private ArrayList<String> al=new ArrayList<String>();
    
    public void setDynamicAttribute(java.lang.String uri,
                                    java.lang.String localName,
                                    java.lang.Object value)
                             throws JspException
    {
        //���������Ե�ֵ���浽ArrayList�����С�
        al.add((String)value);
    } 
    
    public void doTag() throws JspException, java.io.IOException
    {
        JspContext jspCtx=getJspContext();
        JspWriter out=jspCtx.getOut();
        
        int max=Integer.parseInt(al.get(0));
        
        int size=al.size();
        int num;
        
        //ѭ���Ƚϣ��ҳ����ֵ��
        for(int i=1;i<size;i++)
        {
            num=Integer.parseInt(al.get(i));
            max=max > num ? max : num;
        }
        //�����ֵ��Ϊ����max��ֵ�����浽ҳ�淶Χ��
        jspCtx.setAttribute("max",new Integer(max));
    }
}