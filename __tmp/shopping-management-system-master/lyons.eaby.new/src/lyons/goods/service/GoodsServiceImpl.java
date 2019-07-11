package lyons.goods.service;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import lyons.dao.GoodsDaoImpl;
import lyons.goods.entity.Goods;
import lyons.order.entity.Order;
import lyons.order.service.OrderServiceImpl;


/**
 * 
 * ��Ʒ�������߼���
 * 
 * @author  (Lyons)zhanglei
 * 
 */
public class GoodsServiceImpl
{
    Order order;
    Goods goods;
    String[] goodsArr;
    Map<String, Integer> map;
    List<Order> listOrder;
    List<Goods> Listgoods;
    GoodsDaoImpl goodsDaoImpl;
    
    public GoodsServiceImpl()
    {
        goodsDaoImpl = new GoodsDaoImpl();
    }
    
    /**
     * 
     * ��ѯ��Ʒ����ʵ����
     * ��ѯȫ����Ʒ�б�
     * @return
     */
    public List<Goods> queryList()
    {
        return goodsDaoImpl.goodsAllList();
    }
    
    /**
     * 
     * ��ѯ��Ʒ����ʵ����
     * ���ݹؼ��ֲ�ѯ
     * @return
     */
    public List<Goods> queryGoodsByKey(String keyWord)
    {
        return goodsDaoImpl.queryGoodsByKey(keyWord);
    }

    /**
     * 
     * ��ѯ��Ʒ��Ϣ 
     * ���ؼ���||���ࣩor���ؼ���&&���ࣩ
     * @return
     */
    public List<Goods> queryGoodsByKeyClassify(String keyWord, String goodsClassify)
    {
        Goods goodsList = null;
        goodsList = new Goods();
        
        if (((goodsClassify == null || "".equals(goodsClassify.trim()))
                &&(keyWord == null || "".equals(keyWord))))
        {
            return new ArrayList<Goods>(); //�û��ؼ�������඼û�������ʱ�򷵻ؿռ���
        }
        
        if (!(goodsClassify == null || "".equals(goodsClassify.trim())))
        {
            goodsList.setCommodity_id(Integer.parseInt(goodsClassify));
        }
        if (!(keyWord == null || "".equals(keyWord.trim())))
        {
            goodsList.setCommodity_name(keyWord);
        }
        
        return goodsDaoImpl.queryGoodsByKeyClassify(goodsList);
    }
    
    
    
    
    /**
     * 
     * ������Ʒ
     * <������ϸ����>
     * @param car
     * @return
     * 2-��̤�˶�Ь-����-120-800-002.jpg-1- 10-ipad5-����-5900-500-010.jpg-4- 10-ipad5-����-5900-500-010.jpg-4-
     * 
     * commodity_number = goods[0];//��Ʒ���
     * commodity_name = goods[1];
     * commodity_price = Double.parseDouble(goods[3]);
     * commodity_balance = Integer.parseInt(goods[4]) - 1; //����������Ʒ�������ﵽ�����Ч����-1������Ʒ������һ
     */
    public String BuyGoods(String userName,LinkedList<String> car)
    {
        if (car.size() <= 0){return "���ﳵΪ��";}
        
        map = new Hashtable<String, Integer>();
        listOrder = new ArrayList<Order>();
        Listgoods = new ArrayList<Goods>();
        
        String temp = null;
        map.clear();
        OrderServiceImpl orderServiceImpl = new OrderServiceImpl();//��ȡorder�������
        
        for (int i = 0; i < car.size(); i++)
        {
            order = new Order();
            goods = new Goods();
            goodsArr = car.get(i).split(",");//һ��String��Ʒ����Ϣ�ָһ��������
            for (int j = 0; j < goodsArr.length; j++)
            {
                order.setUserName(userName);
                order.setCommodity_name(goodsArr[1]);
                order.setCommodity_price(Double.parseDouble(goodsArr[3]));
                order.setSum(1);
                
//  (���ǰ��ʵ���˵�����Ʒ������ѡ�Ͳ�����Ҫmap��ʱ�����ˣ���ʱ������Ĵ��뽫��ĺܼ򵥣�������������)ѧ��js��ʡ�ܶ�����
                temp = goodsArr[0];//��ƷΨһ��ʶ�䵱map��key  
                goods.setCommodity_number(Integer.parseInt(goodsArr[0]));
                if (j == 4) //�� j=4:��������
                {
                    if (map.containsKey(temp))//����map����һ�£�Ŀ��:��������  
                    {
                        if (map.get(temp)-1 <= 0){return "���ݿ�����Ʒ��������";}
                        map.put(temp, map.get(temp)-1);//map�Ѿ����ڸ���Ʒ,�ٴγ��ֹ�ֻ�轫������һ����
                        System.out.println(i+"+�ݼ����룺"+map.get(temp));
                    }else{
                        map.put(temp, Integer.parseInt(goodsArr[4])-1);
                        if (map.get(temp) <= 0){return "���ݿ�����Ʒ��������";}
                        
                        System.out.println(i+"-�״ν��룺"+map.get(temp));
                    }
                    goods.setCommodity_balance(map.get(temp));
                }
                
            }
            listOrder.add(order);
            Listgoods.add(goods);
            
        }
        
        orderServiceImpl.insertOrderBatch(listOrder);
        goodsDaoImpl.updateGoods(Listgoods);
        
        
        return "���ѽ����ﳵ�е���Ʒ��ؼ���";
    }
    
    
}
