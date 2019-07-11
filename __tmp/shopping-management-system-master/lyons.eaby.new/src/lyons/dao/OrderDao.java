package lyons.dao;

import java.util.List;

import lyons.order.entity.Order;

/**
 * 
 * Order.xml ��Ӧ�Ľӿ�
 * 
 * @author lyons(zhanglei)
 * 
 */
public interface OrderDao
{
    /** ��ѯȫ������ **/
    public List<Order> queryOrderAllList();
    /** ��ѯ���� By �û��� **/
    public List<Order> queryOrderListByuserName(String useName);
    /** ��ѯ���ؼ���||�û�����or���ؼ���&&�û����� **/
    public List<Order> queryOrderByKeyName(Order order);
    
    /** ɾ������ By id **/
    public void deleteOrderOneById(int id);
    /** ɾ������ By id **/
    public void deleteOrderBatch(List<Integer> ids);
    
    /**������������**/
    public void insertOrderBatch(List<Order> listOrder);


}
