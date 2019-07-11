package lyons.goods;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lyons.user.entity.User;

/**
 * 
 * ����ɾ�� ���ﳵ ��Ʒ
 * 
 * @author  Lyons(zhanglei)
 * @version  [�汾��, 2016 5 11]
 * @since  [Ӧ��/�汾]
 */
@SuppressWarnings("serial")
public class DeleteGoodsFromCar extends HttpServlet
{
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        
      doPost(request, response);
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        
        response.setContentType("text/html;setchar=UTF-8");
        request.setCharacterEncoding("UTF-8");
        int deleteID = -1;
        deleteID =  Integer.parseInt(request.getParameter("ID"));
        System.out.println("ɾ�������±�Ϊ��"+deleteID);
        
        HttpSession session = request.getSession(true);
        User loginBean = (User)session.getAttribute("loginBean");
        LinkedList<String> car = null;
        car = loginBean.getCar();
        car.remove(deleteID);
        loginBean.setCar(car);
        
        request.getRequestDispatcher("/jsp/shoppingCar/lookShoppingCar.jsp").forward(request, response);
    }
    
}
