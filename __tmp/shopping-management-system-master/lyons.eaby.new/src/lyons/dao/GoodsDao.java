package lyons.dao;

import java.util.List;

import lyons.goods.entity.Goods;

/**
 * 
 * Goods.xml ��Ӧ�Ľӿ�
 * 
 * @author lyons(zhanglei)
 * 
 * 1.�ӿ����������ļ�namespace��ֵ��ͬ(�Զ�������֣�ֻҪһ�¾���)
 * 2.���������ѯ����id��ͬ
 * 3.����������parameterType�ķ���������ͬ
 * 4.����ֵ��������resultMap������ͬ
 */
public interface GoodsDao
{
    /** ��ѯ������Ʒ�б�**/
    public List<Goods> goodsAllList();
    /** ��Ʒ���ؼ��ֲ�ѯ��**/
    public List<Goods> queryGoodsByKey(String keyWord);
    /** ���ؼ���||���ࣩor���ؼ���&&���ࣩ ��ѯ  **/
    public List<Goods> queryGoodsByKeyClassify(Goods goods);
    
    /** ͨ����ƷΨһ��ʶ����ɾ������ **/
    public void deleteOneGoodsById(int goodsId);
    
    /** ������Ʒ��Ϣ**/
    public void updateGoods(List<Goods> listgoods);
    
}
