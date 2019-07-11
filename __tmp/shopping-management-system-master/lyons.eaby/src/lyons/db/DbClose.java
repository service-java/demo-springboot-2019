package lyons.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbClose
{
	/*
	 * �رյ�¼��Դ
	 */
	public static void allClose(PreparedStatement pstmt,ResultSet rs,Connection conn)
	{
		try
		{
			if (pstmt != null)
			{
				pstmt.close();
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		try
		{
			if (rs != null)
			{
				rs.close();
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		try
		{
			if (conn != null)
			{
				conn.close();
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	/*
	 * �ر�����ɾ������Դ��
	 */
	public static void close(PreparedStatement pstmt,Connection conn)
	{
		try
		{
			if (pstmt != null)
			{
				pstmt.close();
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		try
		{
			if (conn != null)
			{
				conn.close();
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

    public static void close(PreparedStatement pstmtOrder, PreparedStatement pstmtCommodity, Connection conn)
    {
        try
        {
            if (pstmtOrder != null)
            {
                pstmtOrder.close();
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        
        try
        {
            if (pstmtCommodity != null)
            {
                pstmtCommodity.close();
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }  
        
        try
        {
            if (conn != null)
            {
                conn.close();
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        
    }
}
