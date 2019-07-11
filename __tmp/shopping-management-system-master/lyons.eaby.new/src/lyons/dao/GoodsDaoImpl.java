package lyons.dao;

import java.io.IOException;
import java.util.List;

import lyons.db.DbAccess;
import lyons.goods.entity.Goods;

import org.apache.ibatis.session.SqlSession;

/**
 * ��Ʒά����-ѧϰ����ӿڱ��
 * 
 * commodity.sql
 */
public class GoodsDaoImpl implements GoodsDao
{

    GoodsDao goodsDao;
	DbAccess dbAccess;
	SqlSession sqlSession;
	
	public GoodsDaoImpl()
    {
	    dbAccess = new DbAccess();
    }
	/**
     * 
     * ��ѯ��Ʒ�б�-commodity.sql
     * ���ݣ��ؼ���||���ࣩor���ؼ���&&���ࣩ
     * 
     * �˲�ѯ�������ٸ�Ϊ�ӿ�ʽ��̣���Ϊ֪ʶ����
     * 
     * @return ��Ʒ�б�����
     * 
     */
    public List<Goods> queryGoodsByKeyClassify(Goods goodsList)
    {
        try
        {
            sqlSession = dbAccess.getSqlSession();
            return sqlSession.selectList("lyons.dao.GoodsDao.queryGoodsByKeyClassify", goodsList);//�ռ���.��ѯ���id 
//                                      (���˷�ʽĿ�����֪ʶ��ϵ�������ľ�ʹ��mapper)
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
	 * ��ѯ��Ʒ�б�-commodity.sql
	 * @return ��Ʒ�б�����
	 * 
	 */
	public List<Goods> goodsAllList()
    {
	    try
        {
            sqlSession = dbAccess.getSqlSession();
            goodsDao = sqlSession.getMapper(GoodsDao.class);
            return goodsDao.goodsAllList();
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
     * ��ѯ��Ʒ�б�-commodity.sql
     * ���ݹؼ��ֲ�ѯ
     * @return ��Ʒ�б�����
     * 
     */
	public List<Goods> queryGoodsByKey(String keyWord)
	{
	    try
        {
            sqlSession = dbAccess.getSqlSession();
            goodsDao =  sqlSession.getMapper(GoodsDao.class);
            return goodsDao.queryGoodsByKey(keyWord);
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
     * ɾ��������Ʒ
     * By Goods ID
     */
    public void deleteOneGoodsById(int goodsId)
    {
        try
        {
            sqlSession = dbAccess.getSqlSession();
            
            sqlSession.getMapper(GoodsDao.class).deleteOneGoodsById(goodsId);
            sqlSession.commit();//Ĭ�ϲ��Զ��ύ����Ҫ�ֹ��ύ
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
     * ����ɾ����Ʒ
     */
    public void deleteGoodsByMany()
    {
        
    }
    
    /**
     * ������Ʒ
     */
    public void addGoods()
    {
        
    }
    
    /**
     * ������Ʒ
     * ������Ʒ
     */
    public void updateGoods(List<Goods> listgoods)
    {
        try
        {
            sqlSession = dbAccess.getSqlSession();
            sqlSession.getMapper(GoodsDao.class).updateGoods(listgoods);
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
	 */
	private void sqlSesionClose()
    {
	    if (sqlSession != null)
        {
            sqlSession.close();
        }
    }
	
}