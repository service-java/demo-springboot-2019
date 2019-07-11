package lyons.goods.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lyons.goods.service.GoodsServiceImpl;
import lyons.user.entity.User;
import lyons.user.service.UserService;
import lyons.util.Iconst;

@SuppressWarnings("serial")
public class BuyGoodsAction extends HttpServlet
{
    String userName, backNews;
    
    LinkedList<String> car;
    
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
        
        // �ж��Ƿ��½
        userName = (new UserService()).isLogin(request, response).trim();
        if ("".equals(userName) || userName == null){return;}
        
        // ��ģ����ֱ����ȡ���ﳵ��Ϣ
        HttpSession session = request.getSession(true);
        User loginBean = (User)session.getAttribute("loginBean");
        car = loginBean.getCar();
        GoodsServiceImpl goodsService = new GoodsServiceImpl();// ��ȡ��Ʒ�������
        
        String backNews = goodsService.BuyGoods(userName,car);// ��������
        messShopping(request,response,backNews);     // ��ӡ������
        car.clear(); //������ɣ�������չ��ﳵ

    }
  
    /**
     * 
     * ��ӡ��Ʒ��������
     * @param request
     * @param response
     * @param mess  ���صĴ�����Ϣ
     * @throws IOException
     */
    public static void messShopping(HttpServletRequest request, HttpServletResponse response, String mess)
        throws IOException
    {
        PrintWriter out = response.getWriter();
        
        out.print(Iconst.buy_goods_success_1 + mess + Iconst.buy_goods_success_2);
    }
}
