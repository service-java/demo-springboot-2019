package org.pan.web;

import java.sql.*;
import java.util.Vector;
import org.pan.util.*;
import javax.servlet.http.HttpServletRequest;
import org.pan.web.book.shopuser;
import org.pan.web.book.goods;


public class usermn extends DataBase {
	private shopuser user = new shopuser();	//�µ��û�����
	private javax.servlet.http.HttpServletRequest request; //����ҳ������
	private Vector userlist;				//��ʾ�û��б���������
	private int page = 1;					//��ʾ��ҳ��
	private int pageSize=8;					//ÿҳ��ʾ��ͼ����
	private int pageCount =0;				//ҳ������
	private long recordCount =0;			//��ѯ�ļ�¼����
	private String message = "";			//������Ϣ��ʾ
	private String username = "";			//ע��󷵻ص��û���
	private long userid = 0;				//ע��󷵻ص��û�ID

	
	public usermn() throws Exception{
		super();
	}

	public Vector getUserlist() {
		return userlist;
	}

	public String getGbk( String str) {
		try
		{
			return new String(str.getBytes("ISO8859-1"));
		}
		catch (Exception e)
		{
			return str;
		}
	}
	
	//��ҳ������������Ϸֽ�
	public boolean getRequest(javax.servlet.http.HttpServletRequest newrequest) {
		boolean flag = false;
		try
		{	
			request = newrequest;
			String ID = request.getParameter("userid");
			if (ID!=null )
			{
				userid = 0;
				try
				{
					userid = Long.parseLong(ID);
					user.setId(userid);
				}
				catch (Exception e)
				{
					message = message + "��Ҫ�޸ĵ��û��ų���";
				}
			}			
			
			username = request.getParameter("username");
			if (username==null || username.equals(""))
			{
				username = "";
				message = message + "�û���Ϊ��!";
			}
			user.setUserName(getGbk(username)); 
			String password = request.getParameter("passwd");
			if (password==null || password.equals(""))
			{	
				password = "";
				message = message + "����Ϊ��!";
			}
			String pwdconfirm = request.getParameter("passconfirm");
			if (!password.equals(pwdconfirm))
			{
				message = message + "ȷ�����벻��ͬ!";
			}
			user.setPassWord(getGbk(password));
			String names = request.getParameter("names");;
			if (names==null)
			{
				names = "";
			}
			user.setNames(getGbk(names));
			String sex = request.getParameter("sex");				
			user.setSex(getGbk(sex));
			String address = request.getParameter("address");
			if (address == null)
			{
				address = "";
			}
			user.setAddress(getGbk(address));
			String post = request.getParameter("post");
			if (post == null)
			{
				post = "";
			}
			user.setPost(getGbk(post));
			String phone = request.getParameter("phone");
			if (phone== null)
			{
				phone = "";
			}
			user.setPhone(phone);
			String email = request.getParameter("email");
			if (email == null)
			{	
				email = "";
			}
			user.setEmail(getGbk(email));
			String IP = request.getRemoteAddr();
			user.setRegIpAddress(IP);
			if (message.equals(""))
			{
				flag = true;
			}
			return flag;					
		}
		catch (Exception e)
		{
			return flag;
		}
	}

	public String getSql() {
		sqlStr = "select * from my_users order by id";
		return sqlStr;
	}


	public boolean execute() throws Exception {
		sqlStr = "select count(*) from my_users";    //ȡ����¼��
		int rscount = pageSize;
		try
		{
			ResultSet rs1 = stmt.executeQuery(sqlStr);
			if (rs1.next()) recordCount = rs1.getInt(1);				
			rs1.close();
		}
		catch (SQLException e)
		{
			close();
			return false;
		}
		//�趨�ж���pageCount
		if (recordCount < 1)
            pageCount = 0;
        else
            pageCount = (int)(recordCount - 1) / pageSize + 1;
		//���鿴��ҳ�����Ƿ��ڷ�Χ��
		if (page < 1)  
            page = 1;
        else if (page > pageCount)
            page = pageCount;
		
		rscount = (int) recordCount % pageSize;	 // ���һҳ��¼��        

		//sqlΪ����ȡֵ
		sqlStr = "select * from My_users ";
		if (page == 1)
		{
			sqlStr = sqlStr + " order by Id desc";
		} else {
			sqlStr = sqlStr + " where Id not in ( select Id from My_users ORDER BY id) and Id in " +
			"(select Id from My_users ORDER BY ID) " + " order by Id desc";
		}
		try
		{
			rs = stmt.executeQuery(sqlStr);
			userlist = new Vector();
			while (rs.next()){	
				shopuser user = new shopuser();
				user.setId(rs.getLong("Id"));
				user.setUserName(rs.getString("UserName"));
				user.setPassWord(rs.getString("PassWord"));
				user.setNames(rs.getString("Names"));
				user.setSex(rs.getString("Sex"));
				user.setAddress(rs.getString("Address"));
				user.setPhone(rs.getString("Phone"));
				user.setPost(rs.getString("Post"));
				user.setEmail(rs.getString("Email"));
				user.setRegTime(rs.getString("RegTime"));
				user.setRegIpAddress(rs.getString("RegIpAddress"));
				userlist.addElement(user);			
			}
			rs.close();
			return true;
		}
		catch (SQLException e)
		{
			System.out.println(e);
			return false;
		}
		

	}

	public boolean insert(HttpServletRequest req) throws Exception {
		if (getRequest(req)) {
			sqlStr = "select * from My_users where username = '" + user.getUserName() +"'";
			rs = stmt.executeQuery(sqlStr);
			if (rs.next())
			{
				message = message + "���û����Ѵ���!";
				rs.close();
				return false;
			}
			sqlStr = "insert into my_users (username,password,Names,sex,Address,Phone,Post,Email,RegTime,RegIpaddress) values ('";
			sqlStr = sqlStr + strFormat.toSql(user.getUserName()) + "','";
			sqlStr = sqlStr + strFormat.toSql(user.getPassWord()) + "','";
			sqlStr = sqlStr + strFormat.toSql(user.getNames()) + "','";
			sqlStr = sqlStr + strFormat.toSql(user.getSex()) + "','";
			sqlStr = sqlStr + strFormat.toSql(user.getAddress()) + "','";
			sqlStr = sqlStr + strFormat.toSql(user.getPhone()) + "','";
			sqlStr = sqlStr + strFormat.toSql(user.getPost()) + "','";
			sqlStr = sqlStr + strFormat.toSql(user.getEmail()) + "',now(),'";
			sqlStr = sqlStr + user.getRegIpAddress() + "')";
			try
			{
				stmt.execute(sqlStr);
				sqlStr = "select max(id) from My_users where username = '" +user.getUserName()+ "'";
				rs = stmt.executeQuery(sqlStr);
				while (rs.next())
				{
					userid = rs.getLong(1);
				}
				rs.close();
				return true;
			}
			catch (SQLException sqle)
			{
				return false;
			}
		} else	{
			return false;
		}
		
	}

	public boolean update(HttpServletRequest req) throws Exception {
		if (getRequest(req)){
			sqlStr = "update my_users set ";
			sqlStr = sqlStr + "username = '" + strFormat.toSql(user.getUserName()) + "',";
			sqlStr = sqlStr + "password = '" + strFormat.toSql(user.getPassWord()) + "',";
			sqlStr = sqlStr + "Names = '" + strFormat.toSql(user.getNames()) + "',";
			sqlStr = sqlStr + "sex = '" + strFormat.toSql(user.getSex()) + "',";
			sqlStr = sqlStr + "address = '" + strFormat.toSql(user.getAddress()) + "',";
			sqlStr = sqlStr + "phone = '" + strFormat.toSql(user.getPhone()) + "',";
			sqlStr = sqlStr + "post = '" + strFormat.toSql(user.getPost()) + "',";
			sqlStr = sqlStr + "Email = '" + strFormat.toSql(user.getEmail()) + "' ";
			sqlStr = sqlStr + " where id = '" + user.getId() + "'";
			try
			{		
				stmt.execute(sqlStr);
				return true;
			}
			catch (SQLException e)
			{
				return false;
			}
		} else {
			return false;
		}
	
	}

	public boolean delete( long aid ) throws Exception {

		sqlStr = "delete from My_users where id = "  + aid ;
		try
		{
			stmt.execute(sqlStr);
			return true;
		}
		catch (SQLException e)
		{
			System.out.println(e);
			return false;
		}
	}

	public boolean getUserinfo(long newid ) throws Exception {
		try
		{
			sqlStr="select  * from My_users where Id = " + newid ;
			rs = stmt.executeQuery(sqlStr);			
			userlist = new Vector();
			while (rs.next()){				
				user.setId(rs.getLong("Id"));
				user.setUserName(rs.getString("UserName"));
				user.setPassWord(rs.getString("PassWord"));
				user.setNames(rs.getString("Names"));
				user.setSex(rs.getString("Sex"));
				user.setAddress(rs.getString("Address"));
				user.setPhone(rs.getString("Phone"));
				user.setPost(rs.getString("Post"));
				user.setEmail(rs.getString("Email"));
				user.setRegTime(rs.getString("RegTime"));
				user.setRegIpAddress(rs.getString("RegIpAddress"));
				userlist.addElement(user);			
			}
			rs.close();
			return true;
		}
		catch (SQLException e)
		{
			return false;
		}
		
	}

	public int getPage() {				//��ʾ��ҳ��
		return page;
	}
	public void setPage(int newpage) {
		page = newpage;
	}

	public int getPageSize(){			//ÿҳ��ʾ��ͼ����
		return pageSize;
	}
	public void setPageSize(int newpsize) {
		pageSize = newpsize;
	}

	public int getPageCount() {				//ҳ������
		return pageCount;
	}
	public void setPageCount(int newpcount) {
		pageCount = newpcount;
	}

	public long getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(long newrcount) {
		recordCount= newrcount;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String msg) {
		message = msg;
	}

	public void setUserid(long uid) {
		userid = uid;
	}
	public long getUserid() {
		return userid;
	}

	public void setUserName(String uName) {
		username = uName;
	}

	public String getUserName() {
		return username;
	}
};

