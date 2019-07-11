package com;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncodingFilter implements Filter {
	
	protected String encoding = null;
	protected FilterConfig config;

	public void init(FilterConfig filterConfig) throws ServletException {
		this.config = filterConfig;

		// �õ���web.xml�����õı���
		this.encoding = filterConfig.getInitParameter("Encoding");
	}

	public void doFilter(
		ServletRequest request,
		ServletResponse response,
		FilterChain chain)
		throws IOException, ServletException {
		if (request.getCharacterEncoding() == null) {			
			// �õ�ָ���ı���
			String encode = getEncoding();
			if (encode != null) {				
				//����request�ı���
				request.setCharacterEncoding(encode);
				response.setCharacterEncoding(encode);		
			}
		}
		chain.doFilter(request, response);
	}

	protected String getEncoding() {
		return encoding;
	}

	public void destroy() {
		// TODO �Զ����ɷ������

	}
	
}
