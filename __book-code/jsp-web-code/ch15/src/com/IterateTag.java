package com;

import java.util.Iterator;
import java.util.Map;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class IterateTag extends BodyTagSupport {
		
	Map map;         //Ҫ������Map����
	String element; //ÿ�ε�����ŵ�ǰ���������
	private Iterator it; //Map����keyֵ��ɵ�Iterator����
    //����map���Ե�ֵ�������Զ�����
	public void setMap(Map map){
			this.map = map;
		}
		//����element���Ե�ֵ,�����Զ�����
		public void setElement(String element) {
			this.element = element;
		}
		//��ǩ��ʼʱ����doStartTag()����
		public int doStartTag(){
			//��ȡ��ǰmap����keyֵ��ɵ�iterator����
			it = map.keySet().iterator();
			//ȡ����ǰkey����Ӧ��valueֵ������elementΪ���Ʊ�����page��Χ��
			if(it.hasNext()){
				pageContext.setAttribute(element,it.next(),PageContext.PAGE_SCOPE);
			}
			return EVAL_BODY_INCLUDE; 
		}
		public int doAfterBody()throws JspTagException{
			if(it.hasNext()){
				//ȡ����ǰkey����Ӧ��valueֵ������elementΪ���Ʊ�����page��Χ��
				pageContext.setAttribute(element,it.next(),PageContext.PAGE_SCOPE);
				return EVAL_BODY_BUFFERED;
			}else{
				return SKIP_BODY;
			}
		}
		public int doEndTag()throws JspTagException{
			try	{
				if(bodyContent!=null)
				//������е�body����
				bodyContent.writeOut(bodyContent.getEnclosingWriter());
			}catch(java.io.IOException e){	}
			return EVAL_PAGE;
		}
	}