package com;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import java.util.Hashtable;
import java.io.Writer;
import java.io.IOException;

public class BodyTag extends BodyTagSupport{
	int counts;//countsΪ�����Ĵ�����
	public BodyTag()	{
		super();
	}
	
	/**
	 *����counts���ԡ���������������Զ����á�
	 */
	public void setCounts(int c){
		this.counts=c;
	}
	
	/**
	 *����doStartTag����
	 */
	 public int doStartTag() throws JspTagException  {   
	     System.out.println("doStartTag");
	     if(counts>0) { 
	         return EVAL_BODY_TAG;
	     } 
	     else { 
	          return SKIP_BODY;
	     } 
    }
    
    /**
     *����doAfterBody����
     */
    public int doAfterBody() throws JspTagException { 
        System.out.println("doAfterBody"+counts);
        if(counts>1){
        	counts--;
        	return EVAL_BODY_TAG; 
         }  
         else{ 
            return SKIP_BODY; 
         } 
    }
    
   /**
     *����doEndTag����
     */
    public int doEndTag() throws JspTagException {
         System.out.println("doEndTag");
        try {  
             if(bodyContent != null) {
             	bodyContent.writeOut(bodyContent.getEnclosingWriter()); 
             }
        } 
        catch(java.io.IOException e){
        	throw new JspTagException("IO Error: " + e.getMessage());  
        }   
        return EVAL_PAGE;  
    }
    
     public void doInitBody() throws JspTagException{
      System.out.println("doInitBody");
     }
     public void setBodyContent(BodyContent bodyContent) {   
          System.out.println("setBodyContent");
          this.bodyContent=bodyContent; 
          
     }
}
