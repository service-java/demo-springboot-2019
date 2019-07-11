package org.pan.web;

import java.sql.*;
import org.pan.util.*;



public class DataBase {
	protected Connection conn = null;		//Connection�ӿ�
	protected Statement stmt = null;		//Statement�ӿ�
	protected ResultSet rs = null;		//��¼�����
	protected PreparedStatement prepstmt = null;	//PreparedStatement�ӿ�
	protected String sqlStr;		//sql String
	protected boolean isConnect=true;	//�����ݿ����ӱ�ʶ
	
	public DataBase() {
		try
		{
			sqlStr = "";
			DBConnectionManager dcm = new DBConnectionManager();
			conn = dcm.getConnection();
			stmt = conn.createStatement();
		}
		catch (Exception e)
		{
			System.out.println(e);
			isConnect=false;
		}
		
	}

	public Statement getStatement() {
		return stmt;
	}

	public Connection getConnection() {
		return conn;
	}

	public PreparedStatement getPreparedStatement() {
		return prepstmt;
	}

	public ResultSet getResultSet() {
		return rs;
	}


	public String getSql() {
		return sqlStr;
	}

	public boolean execute() throws Exception  {
		return false;
	}

	public boolean insert() throws Exception {
		return false;
	}

	public boolean update() throws Exception  {
		return false;
	}

	public boolean delete() throws Exception  {
		return false;
	}
	public boolean query() throws Exception {
		return false;
	}

	public void close() throws SQLException {
		if ( stmt != null )
		{
			stmt.close();
			stmt = null;
		}
		conn.close();
		conn = null;
	}

};



