package lyons.order.service;

import java.util.ArrayList;
import java.util.List;

import lyons.dao.OrderDaoImpl;
import lyons.order.entity.Order;

/**
 * 
 * �����ӿ�ʵ����
 * @author  lyons(zhanglei)
 */
public class OrderServiceImpl
{
    OrderDaoImpl dao = new OrderDaoImpl();
    
    /**
     * ��ѯ�����û����ж����б�
     * @return
     */
    public List<Order> orderAllList()
    {
        return dao.queryOrderAllList();
    }
    
    /**
     * ��ѯ��ǰ�û����ж����б�
     * @param userName
     * @return
     */
    public List<Order> orderList(String userName)
    {
        return dao.queryOrderListByuserName(userName);
    }
    
    /**
     * �����û���+�ؼ��ֲ�ѯ����
     * @param condition
     * @return
     */
    public List<Order> orderListByKeyName(String queryUserName,String keyWord)
    {
        //�û�ʲô��û������ʱ����ȫ�������б�
        if (queryUserName==null && "".equals(queryUserName.trim())&& keyWord==null && "".equals(keyWord.trim()))
        {
            return orderAllList();
        }
        
        Order orderList = new Order();
        if (!(queryUserName==null || "".equals(queryUserName.trim())))
        {
            orderList.setUserName(queryUserName);
        }
        if (!(keyWord==null || "".equals(keyWord.trim())))
        {
            orderList.setKeyWord(keyWord);
        }
        
        return dao.queryOrderByKeyName(orderList);
        
    }
    
    
    /**
     * ɾ�����������С�����
     * @param userName
     * @return
     */
    public void deleteOrderOneById(String idstr)
    {
        if (!(idstr==null || "".equals(idstr)))
        {
           int id = Integer.parseInt(idstr);
           dao.deleteOrderOneById(id);
        }
        
        return;
        
    }
    
    /**
     * 
     * ����ɾ������ By ids
     * @param userName
     * @return
     */
    public void deleteOrderBatch(String[] ids)
    {
        List<Integer> idList = new ArrayList<Integer>();
        if (ids==null||ids.length<=0)
        {
            String idTemp[] = {"-1"};
            ids = idTemp;//��ֹ��ָ���쳣
        }
        for (String id : ids)
        {
            idList.add(Integer.valueOf(id));
        }
        dao.deleteOrderBatch(idList);
        
    }
    
    /**
     * 
     * ������Ӷ���
     * @param map
     */
    public void insertOrderBatch(List<Order> listOrder)
    {
        if (listOrder.size() >0 )
        {
            dao.insertOrderBatch(listOrder);
        }
        
    }
    
}
