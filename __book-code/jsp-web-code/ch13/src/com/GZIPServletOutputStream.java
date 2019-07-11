package com;

import java.io.IOException;
import java.util.zip.GZIPOutputStream;
import javax.servlet.ServletOutputStream;

public class GZIPServletOutputStream extends ServletOutputStream {
	
	private GZIPOutputStream gzipoutputstream;
	public GZIPServletOutputStream(ServletOutputStream sos) throws IOException
	{
		//ʹ����Ӧ�����������GZIPOutputStream����������
		this.gzipoutputstream = new GZIPOutputStream(sos);		
	}
	@Override
	public void write(int data) throws IOException
	{
		//��д�����ί�и�GZIPOutputStream�����write()�������Ӷ�ʵ����Ӧ�������ѹ��
		gzipoutputstream.write(data);
	}
	
	/**
	 * ����GZIPOutputStream���󣬹�������Ҫ������������Ա���ɽ�ѹ������д��������Ĳ���
	 */
	public GZIPOutputStream getGZIPOutputStream()
	{
		return gzipoutputstream;
	}

}
