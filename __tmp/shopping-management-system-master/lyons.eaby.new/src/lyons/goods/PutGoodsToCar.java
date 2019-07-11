package lyons.goods;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lyons.user.entity.User;

@SuppressWarnings("serial")
public class PutGoodsToCar extends HttpServlet
{
    
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
        String goods = null;
        goods = request.getParameter("GoodsCar");
        
        if (goods==null)
        {
            response.sendRedirect("/lyons.eaby.new/index.jsp");
        }else 
            {
                String[] details = null;
                details = goods.split(",");//�����ڴ������Ϣ�����ݿ�һ�¡�
                
                //����Ʒ��Ϣ�Ž�ģ����
                HttpSession session = request.getSession(true);
                User loginBean = (User)session.getAttribute("loginBean");
                LinkedList<String> car = null;
                car = loginBean.getCar();
               /* if (request.getAttribute("clear")!=null) //���ﳵ��ɽ��㣬������ݣ�
                {
                   car = null;
                }*/
                car.add(goods);
                loginBean.setCar(car);
                
                backNews(request, response, details[1]);//����������Ʒ������
            }
     
    }
    
    
    /**
     * 
     * �����û���Ϣ
     * ��ӹ��ﳵ�ɹ��󣬷�����ʾ������Ϣ
     * @param request
     * @param response
     * @param goodsName
     * @throws IOException
     */
    private void backNews(HttpServletRequest request, HttpServletResponse response, String goodsName) throws IOException
    {
        
        PrintWriter out = response.getWriter();
        out.print("<br><br><br>");
        out.print("<center><font size=5 color=red><B>"+goodsName+"</B></font>&nbsp;�ѳɹ���ӹ��ﳵ");
        out.print("<br><br><br>");
        out.print("<a href=/lyons.eaby.new/Goods.action?key=4>���ؼ�������</a>");
        out.print("&nbsp;or&nbsp;");
        out.print("<a href=/lyons.eaby.new/jsp/shoppingCar/lookShoppingCar.jsp>�鿴���ﳵ</a></center>");
        
    }
    
}
