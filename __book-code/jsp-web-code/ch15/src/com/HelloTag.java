package com;

import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

public class HelloTag implements Tag
{
	private PageContext pageContext;
	private Tag parent;

	public void setPageContext(PageContext pc)
	{
		this.pageContext=pc;        
	}

	public void setParent(Tag t)
	{
		this.parent=t;        
	}

	public Tag getParent()
	{
		return parent;
	}

	public int doStartTag() throws JspException
	{        
		return SKIP_BODY;
	}

	public int doEndTag() throws JspException
	{   
		//����pageContext�����getOut()�����õ�JspWriter����     
		JspWriter out=pageContext.getOut();
		try
		{
			//����JspWriter������ͻ��������ӭ��Ϣ��
			out.print("Hello world ! ��ã����磡");
		}
		catch(IOException e)
		{
			System.err.println(e.toString());
		}
		return EVAL_PAGE;
	}

	public void release(){}
}