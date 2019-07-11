package org.pan.web;

import java.sql.*;
import java.util.Vector;
import org.pan.util.*;
import javax.servlet.http.*;
import org.pan.web.book.goods;
import org.pan.web.book.indentlist;
import org.pan.web.book.indent;



public class purchase extends DataBase {
	private javax.servlet.http.HttpServletRequest request; //����ҳ������
	private HttpSession session;				//ҳ���session;
	private boolean sqlflag = true ;			//�Խ��յ��������Ƿ���ȷ
	private Vector purchaselist;				//��ʾ�б���������
	private Vector my_indent;						//�������б�
	private Vector indent_list;						//�����嵥�б�
	private int goodsnumber=0;						//������
	private float all_price=0;						//�ܼ�Ǯ
	private boolean isEmpty=false;					//���е������Ƿ񹻹������
	private int leaveGoods=0;						//�������
	private String IndentNo = "";					//�û�������
	private boolean isLogin = true;					//�û��Ƿ��¼��
	private int page = 1;					//��ʾ��ҳ��
	private int pageSize=15;				//ÿҳ��ʾ�Ķ�����
	private int pageCount =0;				//ҳ������
	private long recordCount =0;			//��ѯ�ļ�¼����


	
	public purchase() throws Exception{
		super();
	}

	public Vector getPurchaselist() {
		return purchaselist;
	}

	public Vector getIndent_list() {
		return indent_list;
	}

	public Vector getMy_indent() {
		return my_indent;
	}

	public boolean getSqlflag() {
		return sqlflag;
	}

	public void setIsEmpty(boolean flag){
		isEmpty = flag;
	}
	public boolean getIsEmpty() {
		return isEmpty;
	}

	public void setLeaveGoods(int bknum) {
		leaveGoods = bknum;
	}
	public int getLeaveGoods() {
		return leaveGoods;
	}
	
	public void setIndentNo(String newIndentNo) {
		IndentNo = newIndentNo;
	}

	public String getIndentNo() {
		return IndentNo;
	}

	public void setIsLogin(boolean flag){
		isLogin = flag;
	}
	public boolean getIsLogin() {
		return isLogin;
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
	

	public String getSql() {
		sqlStr = "select id,classname from my_Goods order by id";
		return sqlStr;
	}

	public boolean addnew(HttpServletRequest newrequest){
		request = newrequest;
		String ID = request.getParameter("bookid");
		String Amount = request.getParameter("amount");
		long bookid = 0;
		int amount = 0;
		try
		{
			bookid = Long.parseLong(ID);
			amount = Integer.parseInt(Amount);
		}
		catch (Exception e)
		{
			return false;
		}
		if (amount<1) return false;
		session = request.getSession(false);
		if (session == null)
		{
			return false;
		}
		purchaselist = (Vector)session.getAttribute("shopcar");
		sqlStr = "select leav_number from My_Goods where id=" + bookid;
		try
		{
			rs = stmt.executeQuery(sqlStr);
			if (rs.next())
			{
				if (amount > rs.getInt(1))
				{
					leaveGoods = rs.getInt(1);
					isEmpty = true;
					return false;
				}
			}
			rs.close();
		}
		catch (SQLException e)
		{
			return false;
		}

		indentlist iList = new indentlist();
		iList.setGoodsNo(bookid);
		iList.setAmount(amount);
		boolean match = false;		//�Ƿ������ͼ��
		if (purchaselist==null)  //��һ�ι���
		{
			purchaselist = new Vector();
			purchaselist.addElement(iList);
		}
		
		else { // ���ǵ�һ�ι��� 				
			for (int i=0; i< purchaselist.size(); i++) { 
				indentlist itList= (indentlist) purchaselist.elementAt(i); 
				if ( iList.getGoodsNo() == itList.getGoodsNo() ) { 
					itList.setAmount(itList.getAmount() + iList.getAmount()); 
					purchaselist.setElementAt(itList,i); 
					match = true; 
					break;
				} //if name matches���� 				
			} // forѭ������ 
			if (!match) 
				purchaselist.addElement(iList); 
		}
		session.setAttribute("shopcar", purchaselist); 
		return true;
		
	}

	public boolean modiShoper(HttpServletRequest newrequest) {
		request = newrequest;
		String ID = request.getParameter("bookid");
		String Amount = request.getParameter("amount");
		long bookid = 0;
		int amount = 0;
		try
		{
			bookid = Long.parseLong(ID);
			amount = Integer.parseInt(Amount);
		}
		catch (Exception e)
		{
			return false;
		}
		if (amount<1) return false;
		session = request.getSession(false);
		if (session == null)
		{
			return false;
		}
		purchaselist = (Vector)session.getAttribute("shopcar");
		if (purchaselist==null)
		{
			return false;
		}
		sqlStr = "select leav_number from My_Goods where id=" + bookid;
		try
		{
			rs = stmt.executeQuery(sqlStr);
			if (rs.next())
			{
				if (amount > rs.getInt(1))
				{
					leaveGoods = rs.getInt(1);
					isEmpty = true;
					return false;
				}
			}
			rs.close();
		}
		catch (SQLException e)
		{
			return false;
		}
		for (int i=0; i< purchaselist.size(); i++) { 
			indentlist itList= (indentlist) purchaselist.elementAt(i); 
			if ( bookid == itList.getGoodsNo() ) { 
				itList.setAmount(amount); 
				purchaselist.setElementAt(itList,i); 
				break;
			} //if name matches���� 				
		} // forѭ������ 
		return true;
	}



	public boolean delShoper(HttpServletRequest newrequest) {
		request = newrequest;
		String ID = request.getParameter("bookid");
		long bookid = 0;
		try
		{
			bookid = Long.parseLong(ID);
		}
		catch (Exception e)
		{
			return false;
		}
		session = request.getSession(false);
		if (session == null)
		{
			return false;
		}
		purchaselist = (Vector)session.getAttribute("shopcar");
		if (purchaselist==null)
		{
			return false;
		}

		for (int i=0; i< purchaselist.size(); i++) { 
			indentlist itList= (indentlist) purchaselist.elementAt(i); 
			if ( bookid == itList.getGoodsNo() ) { 
				purchaselist.removeElementAt(i); 
				break;
			} //if name matches���� 				
		} // forѭ������ 
		return true;
	}

	public boolean payout(HttpServletRequest newrequest) throws Exception {
		request = newrequest;
		session = request.getSession(false);
		if (session == null)
		{
			return false;
		}
		String Userid = (String) session.getAttribute("userid");   //ȡ���û�ID��
		long userid=0;
		if (Userid==null || Userid.equals(""))
		{
			isLogin = false;
			return false;
		}else {
			try
			{
				userid = Long.parseLong(Userid);
			}
			catch (NumberFormatException e)
			{
				return false;
			}
		}

		purchaselist = (Vector)session.getAttribute("shopcar");
		if (purchaselist==null || purchaselist.size()<0)
		{
			return false;
		}
		String Content = request.getParameter("content");
		if (Content==null)
		{
			Content="";
		}
		Content = getGbk(Content);
		String IP = request.getRemoteAddr();
		String TotalPrice = request.getParameter("totalprice");

		sqlStr = "select max(id) from My_indent";
		rs = stmt.executeQuery(sqlStr);
		if (rs.next())
		{
			IndentNo = "HYD" + userid + "" + rs.getString(1);
		} else {
			IndentNo =  "HYD" + userid + "0";
		}
		rs.close();

		sqlStr = "insert into My_indent (IndentNo,UserId,SubmitTime,ConsignmentTime,TotalPrice,content,IPAddress,IsPayoff,IsSales) values ('";
		sqlStr = sqlStr + IndentNo + "','";
		sqlStr = sqlStr + userid + "',now(),now(),'";
		sqlStr = sqlStr + TotalPrice + "','";
		sqlStr = sqlStr + strFormat.toSql(Content) + "','";
		sqlStr = sqlStr + IP + "',1,1)";
		
		try
		{
			stmt.execute(sqlStr);
			sqlStr= "select max(id) from My_indent where UserId = " + userid;
			rs = stmt.executeQuery(sqlStr);
			long indentid = 0;
			while (rs.next())
			{
				indentid = rs.getLong(1);
				System.out.print(indentid);
			}
			rs.close();
			for (int i=0; i<purchaselist.size() ;i++ )
			{
				indentlist iList = (indentlist) purchaselist.elementAt(i);
				sqlStr = "insert into My_indentlist (IndentNo,GoodsNo,Amount) values (";
				sqlStr = sqlStr + indentid + ",'";
				sqlStr = sqlStr + iList.getGoodsNo() + "','";
				sqlStr = sqlStr + iList.getAmount() + "')";
				stmt.execute(sqlStr);
				sqlStr = "update My_Goods set leav_number=leav_number - " + iList.getAmount() + " where id = " + iList.getGoodsNo();
				stmt.execute(sqlStr);
			}
			return true;
		}
		catch (SQLException e)
		{
			return false;
		}
				
	}

	public boolean getIndent(long userid) {
		sqlStr = "select * from My_indent where userid = '" +userid+ "' order by id desc";
		try
		{
			rs = stmt.executeQuery(sqlStr);
			my_indent = new Vector();
			while (rs.next())
			{
				indent ind = new indent();
				ind.setId(rs.getLong("Id"));
				ind.setIndentNo(rs.getString("IndentNo"));
				ind.setUserId(rs.getLong("UserId"));
				ind.setSubmitTime(rs.getString("SubmitTime"));
				ind.setConsignmentTime(rs.getString("ConsignmentTime"));
				ind.setTotalPrice(rs.getFloat("TotalPrice"));
				ind.setContent(rs.getString("content"));
				ind.setIPAddress(rs.getString("IPAddress"));
				if (rs.getInt("IsPayoff")==1)
					ind.setIsPayoff(false);
				else 
					ind.setIsPayoff(true);
				if (rs.getInt("IsSales")==1)
					ind.setIsSales(false);
				else
					ind.setIsSales(true);
				my_indent.addElement(ind);
			}
			rs.close();
			return true;			
		}
		catch (SQLException e)
		{
			return false;
		}		
	}

	public boolean getOneIndent(long iid) {
		sqlStr = "select * from My_indent where id = '" +iid+ "' order by id desc";
		try
		{
			rs = stmt.executeQuery(sqlStr);
			my_indent = new Vector();
			while (rs.next())
			{
				indent ind = new indent();
				ind.setId(rs.getLong("Id"));
				ind.setIndentNo(rs.getString("IndentNo"));
				ind.setUserId(rs.getLong("UserId"));
				ind.setSubmitTime(rs.getString("SubmitTime"));
				ind.setConsignmentTime(rs.getString("ConsignmentTime"));
				ind.setTotalPrice(rs.getFloat("TotalPrice"));
				ind.setContent(rs.getString("content"));
				ind.setIPAddress(rs.getString("IPAddress"));
				if (rs.getInt("IsPayoff")==1)
					ind.setIsPayoff(false);
				else 
					ind.setIsPayoff(true);
				if (rs.getInt("IsSales")==1)
					ind.setIsSales(false);
				else
					ind.setIsSales(true);
				my_indent.addElement(ind);
			}
			rs.close();
			return true;			
		}
		catch (SQLException e)
		{
			return false;
		}		
	}

	public boolean getIndent() {
		sqlStr = "select count(*) from My_indent";    //ȡ����¼��
		int rscount = pageSize;
		try
		{
			ResultSet rs1 = stmt.executeQuery(sqlStr);
			if (rs1.next()) recordCount = rs1.getInt(1);				
			rs1.close();
		}
		catch (SQLException e)
		{
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
		sqlStr = "select * from My_indent ";
		if (page == 1)
		{
			sqlStr = sqlStr + " order by Id desc";
		}else {
			sqlStr = sqlStr + " where Id not in ( select  Id from My_indent order by Id ) and Id in " +
			"(select Id from My_indent order by Id ) " + " order by Id desc";
		}

		try
		{
			rs = stmt.executeQuery(sqlStr);
			my_indent = new Vector();
			while (rs.next())
			{
				indent ind = new indent();
				ind.setId(rs.getLong("Id"));
				ind.setIndentNo(rs.getString("IndentNo"));
				ind.setUserId(rs.getLong("UserId"));
				ind.setSubmitTime(rs.getString("SubmitTime"));
				ind.setConsignmentTime(rs.getString("ConsignmentTime"));
				ind.setTotalPrice(rs.getFloat("TotalPrice"));
				ind.setContent(rs.getString("content"));
				ind.setIPAddress(rs.getString("IPAddress"));
				if (rs.getInt("IsPayoff")==1)
					ind.setIsPayoff(false);
				else 
					ind.setIsPayoff(true);
				if (rs.getInt("IsSales")==1)
					ind.setIsSales(false);
				else
					ind.setIsSales(true);
				my_indent.addElement(ind);
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
	
	public boolean getIndentList(long nid) {
		sqlStr = "select * from my_indentlist where IndentNo = '" + nid + "'";
		try
		{	
			rs = stmt.executeQuery(sqlStr);
			indent_list = new Vector();
			while (rs.next())
			{				
				indentlist identlist = new indentlist();
				identlist.setId(rs.getLong("Id"));
				identlist.setIndentNo(rs.getLong("IndentNo"));
				identlist.setGoodsNo(rs.getLong("GoodsNo"));
				identlist.setAmount(rs.getInt("Amount"));
				indent_list.addElement(identlist);
			}
			rs.close();
			return true;
		}
		catch (SQLException e)
		{
			return false;
		}		
	}

	public boolean update(HttpServletRequest res) {
		request = res;
		int payoff = 1;
		int sales = 1;
		long indentid =0;
		try
		{
			payoff = Integer.parseInt(request.getParameter("payoff"));
			sales = Integer.parseInt(request.getParameter("sales"));
			indentid = Long.parseLong(request.getParameter("indentid"));
			sqlStr = "update My_indent set IsPayoff = '" + payoff + "',IsSales='"+ sales +"' where id =" + indentid;
			stmt.execute(sqlStr);
			return true;
		}
		catch (Exception e)
		{
			return false;
		}		
	}

	public boolean delete(long id) {
		try
		{
			sqlStr = "delete from My_indentlist where indentNo =" + id;
			stmt.execute(sqlStr);
			sqlStr = "delete from My_indent where id= " + id ;
			stmt.execute(sqlStr);
			return true;
		}
		catch (SQLException e)
		{
			return false;
		}
	}

};

