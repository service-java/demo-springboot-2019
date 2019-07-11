<%@ page language="java"  pageEncoding="gb2312"%>
<%@ page import="java.io.*,com.lowagie.text.*,com.lowagie.text.pdf.*"%>
<%
	response.reset();
	response.setContentType("application/pdf");				//�����ĵ���ʽ
	Document document = new Document();						//����Documentʵ��
	//���б������
	Table table = new Table(3);					//��������Ϊ3�ı��
	table.setBorderWidth(2);					//�߿�������Ϊ2
	table.setPadding(3);						//���߾���Ϊ3
	table.setSpacing(3);
	Cell cell = new Cell("header");				//������Ԫ����Ϊ��ͷ
	cell.setHorizontalAlignment(Cell.ALIGN_CENTER);		
	cell.setHeader(true);						//��ʾ�õ�Ԫ����Ϊ��ͷ��Ϣ��ʾ
	cell.setColspan(3);							//�ϲ���Ԫ��ʹ�õ�Ԫ��ռ��3����
	table.addCell(cell);
	table.endHeaders();				//��ͷ�����ϣ�������ô˷����������ҳʱ����ͷ����ʾ
	cell = new Cell("cell1");//���һ��һ�����еĵ�Ԫ��
	cell.setRowspan(2);				//�ϲ���Ԫ������ռ��2��
	table.addCell(cell);
	table.addCell("cell2.1.1");
	table.addCell("cell2.2.1");
	table.addCell("cell2.1.2");
	table.addCell("cell2.2.2");
	ByteArrayOutputStream buffer = new ByteArrayOutputStream();
	PdfWriter.getInstance(document, buffer);
	document.open();										//���ĵ�
	document.add(table);		//�������
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
