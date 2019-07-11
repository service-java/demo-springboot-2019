package lyons.goods;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.rowset.CachedRowSetImpl;

import lyons.db.DbClose;
import lyons.db.DbConn;
import lyons.entity.Goods;
import lyons.entity.Login;

public class BuyGoods extends HttpServlet
{
    
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1132L;

    /**
     * Constructor of the object.
     */
    public BuyGoods()
    {
        super();
    }
    
    /**
     * Destruction of the servlet. <br>
     */
    public void destroy()
    {
        super.destroy(); // Just puts "destroy" string in log
        // Put your code here
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        doPost(request, response);
    }
    

    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        //��ģ����ֱ����ȡ���ﳵ��Ϣ
        HttpSession session = request.getSession(true);
        Login loginBean = (Login)session.getAttribute("loginBean");
        String userName = "myNull";
        userName = loginBean.getUsername();
        LinkedList<String> car = null;
        car = loginBean.getCar();
        
        //���ﳵ�Ƿ�Ϊ�գ������ݿ���ɾ�����������
        if (car.size()!=0)
        {
            boolean falg = false;
            Connection        conn  = null;
            PreparedStatement pstmtCommodity = null;
            PreparedStatement pstmtOrder = null;
            
            //����Ʒ�м����ݱ�����������
            for (int i = 0,m=car.size(); i < m; i++)
            {
                    String[] goods = null;
                    conn = DbConn.getConn();
                    goods = car.get(i).split(",");
                    
                    String sqlCommodity = null;
                    String sqlOrder = null;
                    sqlCommodity = "update Commodity set commodity_balance=? where commodity_number=?";
                    sqlOrder = "insert into orderForm(username,commodity_name,commodity_price,sum) values(?,?,?,?)";
    
                        try
                        {
                            pstmtCommodity = conn.prepareStatement(sqlCommodity);
                            pstmtOrder = conn.prepareStatement(sqlOrder);
                            
                            pstmtOrder.setString(1,userName);
                            pstmtOrder.setInt(4,1);//Ĭ������Ϊ1������������ѡ��������
                            
                           /* 2-��̤�˶�Ь-����-120-800-002.jpg-1-
                            10-ipad5-����-5900-500-010.jpg-4-
                            10-ipad5-����-5900-500-010.jpg-4-*/
                            
                            //����Ʒ���о������ݱ�����������,����Ӧ  �޸�sqlCommodity��д��sqlOrder ��sql���ռλ��
                            for (int j = 0,n=goods.length; j < n; j++)
                            {
                                switch (j)
                                {
                                    case 0:
                                            String commodity_number = null;
                                            commodity_number = goods[0];
                                            pstmtCommodity.setString(2,commodity_number);
                                        break;
                                    case 1:
                                            String commodity_name= null;
                                            commodity_name = goods[1];
                                            pstmtOrder.setString(2,commodity_name); 
                                        break;
                                    case 2:
                                        break;
                                    case 3:
                                            Double commodity_price = 0.00;
                                            commodity_price = Double.parseDouble(goods[3]);
                                            pstmtOrder.setDouble(3,commodity_price); 
                                        break;
                                    case 4:
                                            int commodity_balance = -1;
                                            System.out.println(Integer.parseInt(goods[4]));
                                            commodity_balance = Integer.parseInt(goods[4])-1;//Ŀǰ��Ĭ��ÿ���޸�һ��
                                            System.out.println(commodity_balance);
                                            if (commodity_balance >= 0)
                                            {
                                                pstmtCommodity.setInt(1,commodity_balance);
                                            }else 
                                                {
                                                    String failNumber = "���ݿ�����Ʒ����";
                                                    messShopping(request,response,failNumber);
                                                }
                                        break;
                                    default:
                                        System.out.println("defalut01");
                                        break;
                                }
                                System.out.println("defalut02");
                            }
                                
                            
                                int rsCommodity = pstmtCommodity.executeUpdate();
                                int rsOrder = pstmtOrder.executeUpdate();
                                if (!(rsCommodity > 0 && rsOrder > 0))//����ʧ��
                                {
                                    String failError = "�����ݿ�Խ�ʱ�����쳣";
                                    messShopping(request,response,failError);
                                }else 
                                    {
                                    System.out.println(i+"�˴�ѭ����Ʒ����ɹ�");
                                        falg = true;//�˴�ѭ����Ʒ����ɹ�
                                    }
                                
                        } catch (SQLException e)
                        {
        //                   �˵���Ҫ�ع����ݣ�����δʵ��
                           /*String backNews = "����ʧ��"+"<br>"+e;
                           loginBean.setBackNews(backNews);//Ϊ��ʡ�£�ֱ����lyons.entity/Login.java ��
*/                           System.out.println("Ī���쳣��"+e);
                           
                            PrintWriter out = response.getWriter();
                            out.print(e+"<br>");
                            out.print("����"+"");
                            out.print("<a href=/lyons.eaby/jsp/shoppingCar/lookShoppingCar.jsp>���ﳵ</a>");
                            return;
                        }finally
                        {
                            DbClose.close(pstmtOrder,pstmtCommodity, conn);
                        }
                        
                continue;
             }
                    
                if (falg==true)
                {
                    //����ɹ����������ģ���е�����
                    car.clear();
                    /*//���м��и������ݿ���Ϣ
                    request.getRequestDispatcher("/lyons.dao/GoodsDao?key=3").forward(request, response);*/
                    
                    updateInfo(request,response);
                    
                    String successBackNews = "���ѽ����ﳵ�е���Ʒ��ؼ���";
                    messShopping(request,response,successBackNews);
                }
            }
        return;
     
    }
    
    /**
     * 
     * �����ݿ��и����м�����
     * <������ϸ����>
     * @param request
     * @param response
     * @throws IOException 
     */
    private void updateInfo(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        CachedRowSetImpl rowSet = null;//�м�����
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Goods goods = null;
        
        HttpSession session = request.getSession(true);
        goods = (Goods)session.getAttribute("goods");
//      ArrayList<Goods> goodsList = new ArrayList<Goods>();
        if (goods==null)
        {
            goods = new Goods();
            session.setAttribute("goods", goods);
        }
        
        conn = DbConn.getConn();
        
        String sqlListClear= "select * from commodity";
        try
        {
            pstmt = conn.prepareStatement(sqlListClear);
            rs = pstmt.executeQuery();
            System.out.println("3ִ�����ݿ����");
            while (rs.next())
            {
                rowSet = new CachedRowSetImpl();
                rowSet.populate(rs);
                goods.setRowSet(rowSet);
                System.out.println("3�Ѿ������ݿ��л�ȡ��ֵ���������м�");
            }
        } catch (SQLException e)
        {
            System.out.println("GoodsDao.java k=3 �ٴβ�ѯʱ�����쳣��"+e);
            PrintWriter out = response.getWriter();
            out.print(e+"<br>");
            out.print("����"+"");
            out.print("<a href=/lyons.eaby/jsp/shoppingCar/lookShoppingCar.jsp>���ﳵ</a>");
        }finally
                {
                    DbClose.allClose(pstmt, rs, conn);
                }
        
    }

    /**
     * 
     * ��Ʒ��������Ϣ
     * <������ϸ����>
     * @param request
     * @param response
     * @param failNumber
     */
    public void messShopping(HttpServletRequest request, HttpServletResponse response, String mess)
    {
        try
        {
            PrintWriter out = response.getWriter();
            out.print("<br><br><br>");
            out.print("<center><font size=5 color=red><B>"+mess+"</B></font>&nbsp;");
            out.print("<br><br><br>");
            out.print("<a href=/lyons.eaby/jsp/browse/showGoods.jsp>���ؼ�������</a>");
            out.print("&nbsp;or&nbsp;");
            out.print("<a href=/lyons.eaby/lyons.dao/GoodsDao?key=3>�鿴����</a></center>");
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        
    }

    public void init()
        throws ServletException
    {
    }
    
}
