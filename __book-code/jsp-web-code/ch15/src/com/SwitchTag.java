package com;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

public class SwitchTag extends TagSupport{
	//boolean���͵ı����������ж��ӱ���Ƿ��Ѿ�ִ�С�
	private boolean subTagvalue;

	public SwitchTag(){
		subTagvalue=false;
	}

	public int doStartTag() throws JspException{
		//������<switch>����ʼ���ʱ���ӱ�ǻ�û�п�ʼִ�У�
		//���Խ�subTagExecuted����Ϊfalse��
		subTagvalue = false;
		return EVAL_BODY_INCLUDE;
	}

	// ����������ӱ�Ǵ�����������ã������ж��Ƿ����ִ������ı���塣
	public synchronized boolean getPermission(){
		return (!subTagvalue);
	}

	// �������һ���ӱ����������������������������֪ͨ����ǡ�
	// �������������ӱ�ǽ��������ǵı���壬�Ӷ�ʵ��switch...case���ܡ�
	public synchronized void subTagSucceeded() {	
		subTagvalue = true;
	}

	public void release(){
		subTagvalue=false;
	}
}