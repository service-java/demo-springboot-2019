package lyons.order.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * ����ʵ����
 * 
 * @author  lyons(zhanglei)
 * 
 */
public class Order
{
    private int id;                 //��ƷΨһ��ʶ
    private String userName;        //�û���
    private Date orderDate;         //��������
    private String commodity_name;  //��Ʒ����
    private Double commodity_price; //��Ʒ�۸�
    private int sum;                //��������
    
    private List<Order> orderList = null;
    
    private String keyWord = "";    //�ؼ��ֲ�ѯ
    
    public Order()
    {
        orderList = new ArrayList<Order>();
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public Date getOrderDate()
    {
        return orderDate;
    }

    public void setOrderDate(Date orderDate)
    {
        this.orderDate = orderDate;
    }

    public String getCommodity_name()
    {
        return commodity_name;
    }

    public void setCommodity_name(String commodity_name)
    {
        this.commodity_name = commodity_name;
    }

    public Double getCommodity_price()
    {
        return commodity_price;
    }

    public void setCommodity_price(Double commodity_price)
    {
        this.commodity_price = commodity_price;
    }

    public int getSum()
    {
        return sum;
    }

    public void setSum(int sum)
    {
        this.sum = sum;
    }

    public List<Order> getOrderList()
    {
        return orderList;
    }

    public void setOrderList(List<Order> orderList)
    {
        this.orderList = orderList;
    }

    public String getKeyWord()
    {
        return keyWord;
    }

    public void setKeyWord(String keyWord)
    {
        this.keyWord = keyWord;
    }
    
    
    
    
}
