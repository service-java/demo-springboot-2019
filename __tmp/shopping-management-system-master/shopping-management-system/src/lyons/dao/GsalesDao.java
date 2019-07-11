package lyons.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import lyons.db.DbClose;
import lyons.db.DbConn;
import lyons.entity.Gsales;

/**
 * ���ݿ�gSales�����
 * @author lyons(zhanglei)
 */
public final class GsalesDao
{
	
	Connection        conn  = null;
	PreparedStatement pstmt = null;
	ResultSet 		  rs    = null;
	
	/**
	 * 1.������������Ʒ
	 * @return ArrayList<Gsales> ��Ʒ��Ϣ,���� allSum (������Ʒ�������ܺ�)
	 */
	public ArrayList<Gsales> dailyGsales()
	{
		ArrayList<Gsales> GsalesList = new ArrayList<Gsales>(); 
		conn = DbConn.getconn();

		//����ʱ��=��ǰʱ�� trunc(sdate) =trunc(sysdate) ��λ����
		//sql�����ͼ�files/sql/java_sql.sql
		String sql = "select gname,gprice,gnum, allSum from goods, (select gid as salesid,sum(snum) as allSum from gsales where trunc(sdate) =trunc(sysdate) group by gid) where gid = salesid"; 
		try
		{
			pstmt = conn.prepareStatement(sql);
			rs 	  = pstmt.executeQuery();
			while (rs.next())
			{
				String gName = rs.getString(1);
				double gPrice = rs.getDouble(2);
				int gNum = rs.getInt(3);
				int allSnum = rs.getInt("allSum");
				
				Gsales Gsales = new Gsales(gName,gPrice,gNum,allSnum);
				GsalesList.add(Gsales);						
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}finally
				{
					DbClose.queryClose(pstmt,rs,conn);
				}
		return GsalesList;
	}
	
	/**
	 *2.�������-��sales���в�����Ʒ���ݣ�
	 *@param gSales ������Ʒ����
	 *@return boolean
	 */
	public boolean shoppingSettlement(Gsales gSales)
	{
		boolean bool = false;
		conn = DbConn.getconn();
		String sql = "INSERT INTO GSALES(GID,SID,SNUM) VALUES(?,?,?)";
		
		try
		{
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,gSales.getGId());
			pstmt.setInt(2,gSales.getSId());
			pstmt.setInt(3,gSales.getSNum());
			
			int rs = pstmt.executeUpdate();
			if (rs > 0)
			{
				bool = true;
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}finally
		{
			DbClose.addClose(pstmt,conn);
		}
		return bool;
	}
	
}
