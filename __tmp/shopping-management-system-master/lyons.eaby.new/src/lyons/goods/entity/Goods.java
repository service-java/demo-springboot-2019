package lyons.goods.entity;

import java.util.ArrayList;
import java.util.List;
import lyons.goods.entity.Goods;

/**
 * 
 * ��Ʒʵ����
 * @author lyons(zhanglei)
 *
 */
public class Goods
{
    private List<Goods>     goodsList;
    private List<GoodsClassify>  goodsClassifyList;    //�������ʱʹ��

    private int commodity_number = -1; //��Ʒ���
    private String commodity_name;     //��Ʒ����
    private String commodity_made;     //����
    private Double commodity_price;    //��Ʒ�۸�
    private int commodity_balance;     //��Ʒ����
    private String commodity_pic;      //��ƷͼƬ
    private int commodity_id = -1;     //��Ʒ�������
    
    public Goods()
    {
        goodsList = new ArrayList<Goods>();
        goodsClassifyList = new ArrayList<GoodsClassify>();
    }

    public List<Goods> getGoodsList()
    {
        return goodsList;
    }
    public void setGoodsList(List<Goods> goodsList)
    {
        this.goodsList = goodsList;
    }

    public int getCommodity_number()
    {
        return commodity_number;
    }

    public void setCommodity_number(int commodity_number)
    {
        this.commodity_number = commodity_number;
    }

    public String getCommodity_name()
    {
        return commodity_name;
    }

    public void setCommodity_name(String commodity_name)
    {
        this.commodity_name = commodity_name;
    }

    public String getCommodity_made()
    {
        return commodity_made;
    }

    public void setCommodity_made(String commodity_made)
    {
        this.commodity_made = commodity_made;
    }

    public Double getCommodity_price()
    {
        return commodity_price;
    }

    public void setCommodity_price(Double commodity_price)
    {
        this.commodity_price = commodity_price;
    }

    public int getCommodity_balance()
    {
        return commodity_balance;
    }

    public void setCommodity_balance(int commodity_balance)
    {
        this.commodity_balance = commodity_balance;
    }

    public String getCommodity_pic()
    {
        return commodity_pic;
    }

    public void setCommodity_pic(String commodity_pic)
    {
        this.commodity_pic = commodity_pic;
    }

    public int getCommodity_id()
    {
        return commodity_id;
    }

    public void setCommodity_id(int commodity_id)
    {
        this.commodity_id = commodity_id;
    }

    public List<GoodsClassify> getgoodsClassifyList()
    {
        return goodsClassifyList;
    }

    public void setgoodsClassifyList(List<GoodsClassify> goodsClassifyList)
    {
        this.goodsClassifyList = goodsClassifyList;
    }

   

}

