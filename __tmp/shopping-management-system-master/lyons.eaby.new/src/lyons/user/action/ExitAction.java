package lyons.user.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class ExitAction extends HttpServlet 
{

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{

		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		HttpSession session = request.getSession(true);//trueһ��Ҫд�����򣬻�ȡ������ǰsessionʱ���Զ�����һ��
		session.invalidate();
		response.sendRedirect("/lyons.eaby.new/jsp/join/login.jsp");
		
	}

	public void init() throws ServletException
	{
		// Put your code here
	}

}
