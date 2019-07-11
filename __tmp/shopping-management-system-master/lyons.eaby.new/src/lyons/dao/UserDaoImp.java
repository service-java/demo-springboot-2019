package lyons.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import lyons.db.DbAccess;
import lyons.user.entity.User;

/**
 * vip�û�ά����
 * 
 * orderForm.sql
 */
public class UserDaoImp implements UserDao
{
    
    static SqlSession sqlSession;
    static DbAccess dbAccess = new DbAccess();
    
    /**
     * 
     * �����û�����ѯ�û���Ϣ
     * @param map
     * @return
     */
    @Override
    public List<User> queryByuserNamepassWord(Map<String, Object> map)
    {
        try
        {
            sqlSession = dbAccess.getSqlSession();
            return sqlSession.getMapper(UserDao.class).queryByuserNamepassWord(map);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            sqlSesionClose();
        }
        
       return new ArrayList<User>();
    }
    
    

    /**
     * 
     * �û��Ƿ����
     * @param username
     */
    public List<User> queryByuserName(String username)
    {
        try
        {
            sqlSession = dbAccess.getSqlSession();
            return sqlSession.getMapper(UserDao.class).queryByuserName(username);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            sqlSesionClose();
        }
        
       return new ArrayList<User>();
        
    }
    
    
    /**
     * 
     * ���û�ע��
     * @param object 
     * @return
     */
    public void insertUser(Map<String, String> registerMap)
    {
        try
        {
            sqlSession = dbAccess.getSqlSession();
            sqlSession.getMapper(UserDao.class).insertUser(registerMap);
            sqlSession.commit();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally
        {
            sqlSesionClose();
        }
        
    }
    
    
    /*
     * �ر����ݿ����ӻỰ
     */
    private static void sqlSesionClose()
    {
        if (sqlSession != null)
        {
            sqlSession.close();
        }
    }

    
}
