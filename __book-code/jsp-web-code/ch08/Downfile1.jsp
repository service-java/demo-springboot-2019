<%@ page contentType="text/html;charset=GB2312" %><%@ page import="java.io.*" %><%  response.reset();
  try{
   String str=request.getParameter("name1");   //�����Ӧ�ͻ����������
   str=new String(str.getBytes("iso8859-1"),"gb2312");
   String path = request.getRealPath("/");
   path = path.substring(0,path.lastIndexOf("\\")); 
   path = path + "\\upload\\"; 
   File fileLoad=new File(path,str);//�����ļ�λ�ã�
   response.reset();
   OutputStream o=response.getOutputStream();
   BufferedOutputStream bos=new BufferedOutputStream(o);
   byte b[]=new byte[500];  //����ļ��õ��ֽ�����,ÿ�η���500���ֽڵ��������
    response.setHeader("Content-disposition","attachment;filename="+new String(str.getBytes("gb2312"),"iso8859-1")); //�ͻ�ʹ�ñ����ļ��ĶԻ���   
   response.setContentType("application/x-tar");//֪ͨ�ͻ��ļ���MIME���ͣ�
    long fileLength=fileLoad.length();//֪ͨ�ͻ��ļ��ĳ��ȣ�
    String length=String.valueOf(fileLength);
    response.setHeader("Content_Length",length);
   FileInputStream in=new FileInputStream(fileLoad);//��ȡ�ļ�,�����͸��ͻ�����:
    int n=0;
   while((n=in.read(b))!=-1)
      { bos.write(b,0,n);
      }
    bos.close();
     }catch(Exception e){System.out.print(e);}
  response.reset();
%>