package lyons.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import lyons.db.DbClose;
import lyons.db.DbConn;
import lyons.entity.Goods;
import lyons.tools.ScannerChoice;

/**
 * ���ݿ�goods�����
 * @author lyons(zhanglei)
 */
public final class GoodsDao 
{
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet 		  rs    = null;
	
	/**
	 * 1.�����Ʒ�����ݿ�goods��
	 * @param goods ��Ʒ����
	 * @return boolean
	 */
	public boolean addGoods(Goods goods)
	{
		boolean bool = false;
		conn = DbConn.getconn();
		String sql = "INSERT INTO GOODS(GNAME,GPRICE,GNUM) VALUES(?,?,?)";
			
			try
			{
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,goods.getGname());
				pstmt.setDouble(2,goods.getGprice());
				pstmt.setInt(3,goods.getGnum());
				
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

	/**
	 * 2.������Ʒ��Ϣ�����ݿ�goods��
	 * @param key   ѡ��Ҫ������Ʒ��Ϣ
	 * @param goods ��Ʒ����
	 * @return boolean
	 */
	public boolean updateGoods(int key,Goods goods)
	{
		boolean bool = false;
		conn = DbConn.getconn();
			switch (key)
			{
			case 1:		//	key=1,������Ʒ����
						String sqlName = "UPDATE GOODS SET GNAME=? WHERE GID=?";
						
					    try
						{
							pstmt = conn.prepareStatement(sqlName);
							pstmt.setString(1, goods.getGname());
							pstmt.setInt(2,goods.getGid());
							
							int rs = pstmt.executeUpdate();
							if (rs > 0)
							{
								bool = true;
							}
						} catch (SQLException e)
						{
							e.printStackTrace();
						}finally{
									DbClose.addClose(pstmt,conn);
								}
				break;
			case 2:		//	key=2,������Ʒ�۸�
						String sqlPrice = "UPDATE GOODS SET GPRICE=? WHERE GID=?";
						
						try
						{
							pstmt = conn.prepareStatement(sqlPrice);
							pstmt.setDouble(1, goods.getGprice());
							pstmt.setInt(2,goods.getGid());
							
							int rs = pstmt.executeUpdate();
							if (rs > 0)
							{
								bool = true;
							}
						} catch (SQLException e)
						{
							e.printStackTrace();
						}finally{
									DbClose.addClose(pstmt,conn);
								}
				break;
			case 3:		//	key=3,������Ʒ����
						String sqlNum = "UPDATE GOODS SET GNUM=? WHERE GID=?";
							
						try
						{
							pstmt = conn.prepareStatement(sqlNum);
							pstmt.setInt(1, goods.getGnum());
							pstmt.setInt(2,goods.getGid());
							
							int rs = pstmt.executeUpdate();
							if (rs > 0)
							{
								bool = true;
							}
						} catch (SQLException e)
						{
							e.printStackTrace();
						}finally{
									DbClose.addClose(pstmt,conn);
								}
				break;
			default:
				break;
			}
		return bool;
	}
	
	/**
	 * 3.�����ݿ�goods����-�h����Ʒ
	 * @param gid ��Ʒ���
	 * @return boolean
	 */
	public boolean deleteGoods(int gid)
	{
		boolean bool = false;
		conn = DbConn.getconn();
		String sql = "DELETE FROM GOODS WHERE GID=?";
		
			try
			{
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1,gid);
				int rs = pstmt.executeUpdate();
				if (rs > 0)
				{
					bool = true;
				}
			} catch (SQLException e)
			{
				e.printStackTrace();
			}finally{
						DbClose.addClose(pstmt,conn);
					}
		return bool;
	}
	
	/**
	 *4.��ѯ��Ʒ��Ϣ
	 * @param key ��ѯ��ʽ
	 * @return ArrayList<Goods>
	 */
	public ArrayList<Goods> queryGoods(int key) 
	{										
		ArrayList<Goods> goodsList = new ArrayList<Goods>();
		conn = DbConn.getconn();	

		switch (key)
		{
			case 1:
					//	key=1��Ʒ ���� �����ѯ
					String sqlGnum = "SELECT * FROM GOODS ORDER BY GNUM ASC";
					try
					{
						pstmt = conn.prepareStatement(sqlGnum);
						rs = pstmt.executeQuery();
						while (rs.next())
						{
							int gid = rs.getInt("gid");
							String gname = rs.getString(2);
							double gprice = rs.getDouble(3);
							int gnum = rs.getInt(4);
							
							Goods goods = new Goods(gid,gname,gprice,gnum);
							goodsList.add(goods);
						}
					} catch (SQLException e)
					{
						e.printStackTrace();
					}finally
							{
								DbClose.queryClose(pstmt, rs, conn);
							}
				break;
			case 2:
				 	//	key=2��Ʒ �۸� �����ѯ
					String sqlGprice = "SELECT * FROM GOODS ORDER BY GPRICE ASC";
					try
					{
						pstmt = conn.prepareStatement(sqlGprice);
						rs = pstmt.executeQuery();
						while (rs.next())
						{
							int gid = rs.getInt("gid");
							String gname = rs.getString(2);
							double gprice = rs.getDouble(3);
							int gnum = rs.getInt(4);
							
							Goods goods = new Goods(gid,gname,gprice,gnum);
							goodsList.add(goods);
						}
					} catch (SQLException e)
					{
						e.printStackTrace();
					}finally
							{
								DbClose.queryClose(pstmt, rs, conn);
							}
				break;
			case 3:
					//	key=3��Ʒ �ؼ��� ��ѯ
					String nameGet = ScannerChoice.ScannerInfoString();
					String sqlGname = "SELECT * FROM GOODS WHERE GNAME LIKE '%'||?||'%'";
					try
					{
						pstmt = conn.prepareStatement(sqlGname);
						pstmt.setString(1, nameGet);
						rs = pstmt.executeQuery();
						while (rs.next())
						{
							int gid = rs.getInt("gid");
							String gname = rs.getString(2);
							double gprice = rs.getDouble(3);
							int gnum = rs.getInt(4);
							
							Goods goods = new Goods(gid,gname,gprice,gnum);
							goodsList.add(goods);
						}
					} catch (SQLException e)
					{
						e.printStackTrace();
					}finally
					{
						DbClose.queryClose(pstmt, rs, conn);
					}
					break;
			default:
				break;
		}
		return goodsList;
	}

	/**
	 *5.��ʾ������Ʒ��Ϣ
	 * @return ArrayList<Goods>
	 */
	public ArrayList<Goods> displayGoods()
	{
		ArrayList<Goods> goodsList = new ArrayList<Goods>(); 
		conn = DbConn.getconn();
		String sql = "SELECT * FROM GOODS";
		
		try
		{
			pstmt = conn.prepareStatement(sql);
			rs 	  = pstmt.executeQuery();
				
				while (rs.next())
				{
					int gid = rs.getInt(1);
					String gname = rs.getString(2);
					double gprice = rs.getDouble("gprice"); 		//˫����+������,Ҳ�������ֱ�ʾ.
					int gnum = rs.getInt(4);
					
					Goods goods = new Goods(gid,gname,gprice,gnum);	//����Goods���󣬲���ֵ.
					goodsList.add(goods);							//�����Ϣ����̬������.
				}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}finally
				{
					DbClose.queryClose(pstmt, rs, conn);
				}
		return goodsList;
		
	}
		
}