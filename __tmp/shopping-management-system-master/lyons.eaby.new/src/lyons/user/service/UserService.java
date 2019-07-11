package lyons.user.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lyons.dao.UserDaoImp;
import lyons.user.entity.User;

/**
 * 
 * �û�������
 * 
 * @author  lyons(zhanglei)
 */
@SuppressWarnings("serial")
public class UserService extends HttpServlet
{
    
    UserDaoImp userDao = new UserDaoImp();;
    List<User> userList = new ArrayList<User>();;
    
    /**
     * 
     * �û���¼
     * @param request
     * @param response
     * @param userMap
     * @throws ServletException
     * @throws IOException
     */
    public void userLogin(HttpServletRequest request, HttpServletResponse response, Map<String, String> userMap) 
        throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        handleCookies(request,response,userMap.get("username"),
        userMap.get("userpass"),userMap.get("isCookie"));//����cookies��Ϣ
        
        //Ϊ��ʹ��map��Ҳ������   ���⣺mapȡֵʱ�Դ�Сд����
        Map<String, Object> map = new Hashtable<String, Object>();
        Map<String, String> namePass = new Hashtable<String, String>();
        namePass.put(userMap.get("username"), userMap.get("userpass"));
        map.put("userMap", namePass);
        
        //��¼���
        userList = userDao.queryByuserNamepassWord(map);//get user Info by userName
        if (userList != null && userList.size() > 0 )
        {
            success(request,response,userMap.get("username"));
            request.getRequestDispatcher("/jsp/join/landing.jsp").forward(request, response);
        }else {
            fail(request, response, "�û������������");
        }

    }
    

    /**
     * 
     * ��֤�û����Ƿ����
     * @param username
     * @return Existence:true
     */
    public boolean isExistence(String username)
    {
        userList = userDao.queryByuserName(username);
        
        if (userList != null && userList.size() > 0)
        {
            return true;
        }
        
        return false;
    }
    
    
    /**
     * 
     * �ж��û��Ƿ��½
     * 
     * @param request
     * @param response
     * @return �û���user
     * @throws ServletException
     * @throws IOException
     */
    public String isLogin(HttpServletRequest request, HttpServletResponse response) 
           throws ServletException, IOException
    
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        User username = null;
        
        HttpSession session = request.getSession(true);
        username = (User)session.getAttribute("loginBean");
       
        if (username==null)
        {
            username = new User();
            session.setAttribute("username", username);
        }
          //�ж��û��Ƿ��½
          String user = "";
          user = username.getUsername();//��½�ߵ��û���
          if ("userNull".equals(user)||user==null)
          {
              out.print("<br>");
              out.print("<center>" +
                      		"<font color=#008B8B> �͹٣�</font>" +
                      		"<a href=/lyons.eaby.new/jsp/join/login.jsp><font color=red size=6>��½</font></a>" +
                      		"<font color=#008B8B> ֮����ܲ���Ŷ  </font>" +
              		   "</center>");
              return "";
          }
          return user;
    }
    
    
    /**
     * �����û�cookies��Ϣ
     * @param request
     * @param response
     * @param username
     * @param userpass
     */
    public void handleCookies(HttpServletRequest request,HttpServletResponse response, 
            String name,String pass,String isCookie)throws ServletException, IOException
    {
        if ("isCookie".equals(isCookie))//�û�ѡ���˼�ס����
        {
            String username = URLEncoder.encode(name,"UTF-8");//���룬���cookie�޷������ַ���������
            String userpass = URLEncoder.encode(pass,"UTF-8");
            
            Cookie nameCookie = new Cookie("username",username );//�������½ʱ��name��Ӧ�ļ�ֵ��
            Cookie passCookie = new Cookie("userpass",userpass );
            
            nameCookie.setPath("/");//���õ�cookie�Ĵ洢·������Ҫ����Ȼȡ����ֵ
            passCookie.setPath("/");
            nameCookie.setMaxAge(864000); //������������ʮ�� ��λ��
            passCookie.setMaxAge(864000);
            response.addCookie(nameCookie); //������Ϣ
            response.addCookie(passCookie); 
        }else 
            {
            //�û�δѡ���ס���룬ɾ��������п��ܴ��ڵ�cookie
                Cookie[] cookies = null;
                cookies = request.getCookies();
                if (cookies!=null&&cookies.length>0)
                {
                    for (Cookie c : cookies)
                    {
                        if ("username".equals(c.getName())||"userpass".equals(c.getName()))
                        {
                            c.setMaxAge(0);//����cookieʧЧ
                            c.setPath("/");//�������
                            response.addCookie(c);
                        }
                    }
                }
            }
    }
    
    /**
     * ��½�ɹ��������û���Ϣ
     */
    public void success(HttpServletRequest request,HttpServletResponse response, String username)
    {
        User loginBean = null;
        HttpSession session = request.getSession(true);
        
        try
        {
            loginBean = (User) session.getAttribute("loginBean");//��ȡsession�п��ܴ��ڵ�loginBean����
            if (loginBean == null)
            {
                loginBean = new User();
                session.setAttribute("loginBean", loginBean);//ע��jsp��ȡʱ��Ҫ�õ���name����������
                session.setMaxInactiveInterval(600);//ʮ���ӵĴ���� ��λ����
                loginBean = (User) session.getAttribute("loginBean");
            }
            
            String name = loginBean.getUsername();
            if (username.equals(name))
            {
                loginBean.setBackNews(username + "���ѵ�½�������ٴε�¼");
                loginBean.setUsername(username);
            } else
                {
                    loginBean.setBackNews(username + "��½�ɹ�");
                    loginBean.setUsername(username);
                }
        } catch (Exception e)
        {
            String backNews = "��¼ʧ��"+e;
            fail(request, response, backNews);
        }
    
    }
    
    /**
     * ��½ʧ��
     */
    public void fail(HttpServletRequest request,HttpServletResponse response,String backNews)
    {
        try
        {
            PrintWriter out = response.getWriter();
            out.print(backNews+"<br>");
            out.print("����"+"<a href=/lyons.eaby.new/jsp/join/login.jsp>��½����</a>");
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
