package lyons.dao;

import java.io.IOException;
import java.util.List;

import lyons.db.DbAccess;
import lyons.order.entity.Order;

import org.apache.ibatis.session.SqlSession;

/**
 * ����ά����
 * 
 * orderForm.sql
 */
public class OrderDaoImpl implements OrderDao
{
    OrderDao orderDao;
    SqlSession sqlSession;
    DbAccess dbAccess = new DbAccess();
    
    
    /**
     * 
     * ��ѯ��ǰ�û�ȫ�������б�-orderForm.sql
     * 
     * @return ��Ʒ�б�����
     * 
     */
    public List<Order> queryOrderListByuserName(String userName)
    {
        try
        {
            sqlSession = dbAccess.getSqlSession();
            orderDao = sqlSession.getMapper(OrderDao.class);
            return orderDao.queryOrderListByuserName(userName);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }finally
        {
            sqlSesionClose();
        }
        
        return null;
    }
    
    /**
     * 
     * ��ѯ�����б�-orderForm.sql
     * �����û���+�ؼ��ֲ�ѯ
     * @return ��Ʒ�б�����
     * 
     */
    public List<Order> queryOrderByKeyName(Order condition)
    {
        try
        {
            sqlSession = dbAccess.getSqlSession();
            orderDao = sqlSession.getMapper(OrderDao.class);
            return orderDao.queryOrderByKeyName(condition);//�����װ���ݶ������
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }finally
        {
            sqlSesionClose();
        }
        
        return null;
    }
    
    /**
     * 
     * ��ѯ�����û������б�
     * @return
     */
    public List<Order> queryOrderAllList()
    {
        try
        {
            sqlSession = dbAccess.getSqlSession();
            orderDao = sqlSession.getMapper(OrderDao.class);
            return orderDao.queryOrderAllList();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }finally
        {
            sqlSesionClose();
        }
        
        return null;
    }
    
    
    
    
    
    /**
     * 
     * ������ƷΨһ��ʶɾ����������
     * @param id
     */
    public void deleteOrderOneById(int id)
    {
        try
        {
            sqlSession = dbAccess.getSqlSession();
            orderDao = sqlSession.getMapper(OrderDao.class);
            orderDao.deleteOrderOneById(id);
            sqlSession.commit();//ɾ����Ҫ�ύ
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }finally
        {
            sqlSesionClose();
        }
    }
    
    /**
     * 
     * ����ɾ������
     * @param ids
     */
    public void deleteOrderBatch(List<Integer> ids)
    {
        
        try
        {
            sqlSession = dbAccess.getSqlSession();
            orderDao = sqlSession.getMapper(OrderDao.class);
            orderDao.deleteOrderBatch(ids);
            sqlSession.commit();//ɾ����Ҫ�ύ
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }finally
        {
            sqlSesionClose();
        }
        
    }
    
    /**
     * 
     * insert-�������Ӷ���
     * @param map
     */
    public void insertOrderBatch(List<Order> listOrder)
    {
        try
        {
            sqlSession = dbAccess.getSqlSession();
            orderDao = sqlSession.getMapper(OrderDao.class);
            orderDao.insertOrderBatch(listOrder);
            sqlSession.commit();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }finally
        {
            sqlSesionClose();
        }
        
    }
    
    /*
     * �ر����ݿ����ӻỰ
     * ����ʵ��mybatisĬ���Զ��ر����ӣ�
     */
    private void sqlSesionClose()
    {
        if (sqlSession != null)
        {
            sqlSession.close();
        }
    }

    
}
