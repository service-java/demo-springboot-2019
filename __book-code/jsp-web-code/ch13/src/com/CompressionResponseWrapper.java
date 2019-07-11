package com;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.zip.GZIPOutputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class CompressionResponseWrapper extends HttpServletResponseWrapper{

	private GZIPServletOutputStream gzipsos;
	private PrintWriter printwriter;
	
	public CompressionResponseWrapper(HttpServletResponse response) throws IOException 
	{
		super(response);
		
		//����Ӧ���������GZIPServletOutputStream����
		gzipsos = new GZIPServletOutputStream(response.getOutputStream());
		//��GZIPServletOutputStream������Ϊ����������PrintWriter����
		printwriter = new PrintWriter(gzipsos);
	}
	
	//��дsetContentLength()�������Ա���Content-Lengthʵ�屨ͷ��ָ���ĳ��Ⱥ�ѹ�����ʵ�����ĳ��Ȳ�ƥ��
	
	@Override
	public void setContentLength(int len){}

	@Override
	public ServletOutputStream getOutputStream() throws IOException
	{
		return gzipsos;
	}

	@Override
	public PrintWriter getWriter() throws IOException
	{
		return printwriter;
	}
	
	// ��������������������õ�GZIPOutputStream�����Ա���ɽ�ѹ������д��������Ĳ���	
	public GZIPOutputStream getGZIPOutputStream()
	{
		return gzipsos.getGZIPOutputStream();
	}
}
