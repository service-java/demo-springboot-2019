package lyons.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import lyons.db.DbClose;
import lyons.db.DbConn;
import lyons.entity.Login;

/**
 * ��½����
 * @author Lyons(zhanglei)
 *
 */

public class HandleLogin extends HttpServlet 
{
	private static final long serialVersionUID = 1L; //�������к�
	public HandleLogin()
	{
		super();
	}
	public void init() throws ServletException
	{
	}
	public void destroy()
	{
		super.destroy(); // Just puts "destroy" string in log
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
		request.setCharacterEncoding("UTF-8");//servlet��ҲҪ�������ȡֵ����
		String username = "";
		String userpass = "";
		String cookies  = "";
		username = request.getParameter("username");
		userpass = request.getParameter("userpass");
		cookies = request.getParameter("isCookie");
		handleCookies(request,response,username,userpass,cookies);//����cookies��Ϣ
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		conn = DbConn.getConn();
		
		String sql = "select * from vip where username=? and userpass=?";
		try
		{
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, userpass);
			rs = pstmt.executeQuery();
			if (rs.next())
			{
				//��½�ɹ�
				success(request,response,username);
				request.getRequestDispatcher("/jsp/join/landing.jsp").forward(request, response);
			}else 
				{
					String backNews = "�û��������������";
					fail(request, response, backNews);
				}
		} catch (SQLException e)
		{
			String backNews = "��¼ʧ��"+e;
			fail(request, response, backNews);
		}finally
			{
				DbClose.allClose(pstmt, rs, conn);
			}
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
	public void success(HttpServletRequest request,
			HttpServletResponse response, String username)
	{
		Login loginBean = null;
		HttpSession session = request.getSession(true);
		
		try
		{
			loginBean = (Login) session.getAttribute("loginBean");//��ȡsession�п��ܴ��ڵ�loginBean����
			if (loginBean == null)
			{
				loginBean = new Login();
				session.setAttribute("loginBean", loginBean);//ע��jsp��ȡʱ��Ҫ�õ���name����������
				session.setMaxInactiveInterval(600);//ʮ���ӵĴ���� ��λ����
				loginBean = (Login) session.getAttribute("loginBean");
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
	public void fail(HttpServletRequest request,
			HttpServletResponse response,String backNews)
	{
		try
		{
			PrintWriter out = response.getWriter();
			out.print(backNews+"<br>");
			out.print("����"+"<a href=/lyons.eaby/jsp/join/login.jsp>��½����</a>");
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}