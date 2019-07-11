package lyons.order.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import lyons.order.entity.Order;
import lyons.order.service.OrderServiceImpl;
import lyons.user.service.UserService;

@SuppressWarnings("serial")
public class OrderAction extends HttpServlet
{
    int keys = -1;                     //Ĭ�ϵĲ�ѯֵ������default ��
    String user;                       //��ǰ�û�
    String key;                        //ѡ���ѯ����
    String keyWord;                    //��ѯ�Ĺؼ���
    String queryUserName;              //��ѯ���û���
    String id;                         //��ƷΨһ��ʶid 
    String ids[];                      //����ɾ����Ʒ��ids
    List<Order> orderList = new ArrayList<Order>();
    OrderServiceImpl orderService = new OrderServiceImpl();//��ȡ�����������
    
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        this.doPost(request, response);
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        //�ж��Ƿ��½
        user = (new UserService()).isLogin(request, response).trim();
        if ( "".equals(user) || user == null ){return;}
        
        /**
         *    jsp������key����˵��
         *      
         * 1����ѯ��ǰ�û����ж����б�
         * 2����ѯ�����û������б�
         * 3������������ѯ���� �û���+��Ʒ�ؼ���
         * 
         * 4��ɾ����������
         * 5������ɾ������
         * 6��
         */
        key = request.getParameter("key").trim();
        System.out.println(key+"---------");
        if (key.matches("[1-3]"))
        {
            keyWord = request.getParameter("keyWord");
            queryUserName = request.getParameter("queryUserName");
            String str[] = {key,keyWord,queryUserName,user};
            queryCondition(str,request,response);//key �����ѯ������keyWord����Ҫ��ѯ�Ĺؼ���
            
        }else if (key.matches("[4-5]")) {
            
            id = request.getParameter("id");        
            ids = request.getParameterValues("deleteId");
            String str[] = {key,id};
            deleteCondition(str,ids,request,response);
        }
        
    }
    /**
     * 
     * ��������ѡ���ѯ��ҵ��
     * @param str
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void queryCondition(String str[],HttpServletRequest request, HttpServletResponse response) 
         throws IOException, ServletException
    {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(true);
        
        key = str[0];               //ѡ���ѯ����
        keyWord = str[1];           //��ѯ�Ĺؼ���
        queryUserName = str[2];     //��ѯ���û���
        user = str[3];              //��ǰ�û�
        keys = Integer.parseInt(key);  
        orderList.clear();
        Order order = null;
//        order = new Order();
        order = (Order)session.getAttribute("order");
        if (order==null)
        {
            order = new Order();
            session.setAttribute("order", order);
        }
        
        //key=1 ��ѯ��ǰ�û����ж����б�
        //key=2 ��ѯ�����û������б�
        //key=3 ����������ѯ���� �û���+��Ʒ�ؼ���
        if ("1".equals(key)){
            orderList = orderService.orderList(user);
        }else if ("2".equals(key)) {
                    orderList = orderService.orderAllList();
                 }else if ("3".equals(key)) {
                             //����ѯ��Ϣ��װ��������
                             orderList = orderService.orderListByKeyName(queryUserName,keyWord);
                         }
        
        order.setOrderList(orderList);
        session.setAttribute("orderAllList", order);
        request.getRequestDispatcher("/jsp/manageGoods/OrderList.jsp").forward(request, response);
    }
    
    /**
     * 
     * ��������ѡ��ɾ��������ҵ��
     * 
     * @param str
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void deleteCondition(String str[],String ids[],HttpServletRequest request, HttpServletResponse response) 
    throws IOException, ServletException
    {
       response.setContentType("text/html;charset=UTF-8");
       HttpSession session = request.getSession(true);
       
       key = str[0]; 
       id = str[1];
         
       Order order = null;
       order = new Order();
       orderList.clear();
       order = (Order)session.getAttribute("order");
       if (order==null)
       {
           order = new Order();
           session.setAttribute("order", order);
       }
       
        if ("4".equals(key)){
           orderService.deleteOrderOneById(id);       //ɾ����������
           orderList = orderService.orderAllList();   //���²�ѯ�����б�(Ӧ�ø���֮ǰ�ļ���������ѯ)
        }else if ("5".equals(key)) {
                   orderService.deleteOrderBatch(ids);        //����ɾ������
                   orderList = orderService.orderAllList();   //���²�ѯ�����б�(Ӧ�ø���֮ǰ�ļ���������ѯ)
                 }
        
        //������û�в�ѯ��ֵ��Ҫ���ض����б�
        order.setOrderList(orderList);
        session.setAttribute("orderAllList", order);
        request.getRequestDispatcher("/jsp/manageGoods/OrderList.jsp").forward(request, response);
      
    }
}
