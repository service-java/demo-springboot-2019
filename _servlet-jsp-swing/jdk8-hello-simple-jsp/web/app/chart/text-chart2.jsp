<%@ page language="java"  pageEncoding="gb2312"%>
<%@ page import="java.io.*,com.lowagie.text.*,com.lowagie.text.pdf.*"%>
<%
	response.reset();
	response.setContentType("application/pdf");				//�����ĵ���ʽ
	Document document = new Document();						//����Documentʵ��
	//����2��5�еı��	
	PdfPTable table = new PdfPTable(5);
	for (int i = 1; i < 11; i++) {
		PdfPCell cell = new PdfPCell();
		cell.addElement(new Paragraph("N0."+String.valueOf(i)));
		table.addCell(cell);
	}
	ByteArrayOutputStream buffer = new ByteArrayOutputStream();
	PdfWriter.getInstance(document, buffer);
	document.open();										//���ĵ�
	document.add(table);		//��ӱ��
	document.close();										//�ر��ĵ�
	//����׳�IllegalStateException�쳣������
	out.clear();
	out = pageContext.pushBody();
		DataOutput output = new DataOutputStream(response.getOutputStream());

	byte[] bytes = buffer.toByteArray();
	response.setContentLength(bytes.length);

	for (int i = 0; i < bytes.length; i++) {
		output.writeByte(bytes[i]);
	}
%>
