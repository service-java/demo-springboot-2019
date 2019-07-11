package com;

import java.io.IOException;
import java.util.zip.GZIPOutputStream;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CompressionFilter implements Filter {

	public void destroy() {}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterchain) throws IOException, ServletException {
		HttpServletRequest httpReq = (HttpServletRequest) request;
		HttpServletResponse httpResp = (HttpServletResponse) response;

		String acceptEncodings = httpReq.getHeader("Accept-Encoding");
		if (acceptEncodings != null && acceptEncodings.indexOf("gzip") > -1)
		{
			// �õ���Ӧ����ķ�װ�����
			CompressionResponseWrapper respWrapper = new CompressionResponseWrapper(
					httpResp);
			
			// ����Content-Encodingʵ�屨ͷ�����������ʵ�����Ĳ�����gzipѹ������
			respWrapper.setHeader("Content-Encoding", "gzip");
			filterchain.doFilter(httpReq, respWrapper);
			
			//�õ�GZIPOutputStream���������
			GZIPOutputStream gzipos = respWrapper.getGZIPOutputStream();
			//����GZIPOutputStream����������finish()������ɽ�ѹ������д��
			//��Ӧ������Ĳ���������ر������
			gzipos.finish();
		}
		else
		{
			filterchain.doFilter(httpReq, httpResp);
		}

	}

	public void init(FilterConfig filterConfig)  throws ServletException {}

}
