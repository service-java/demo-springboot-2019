package lyons.goods.entity;

/**
 * 
 * ��Ʒ���ʵ����
 * 
 * @author  lyons(zhanglei)
 */
public class GoodsClassify
{
    private int gid = -1; //��Ʒ�������
    private String gname; //��Ʒ����
    
    public GoodsClassify(){};
    
    public int getGid()
    {
        return gid;
    }
    public void setGid(int gid)
    {
        this.gid = gid;
    }
    public String getGname()
    {
        return gname;
    }
    public void setGname(String gname)
    {
        this.gname = gname;
    }
}
