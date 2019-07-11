package lyons.entity;

/**
 * 
 * ��ҳʵ����
 * 
 * @author  lyons(zhanglei)
 */
public class Page
{
    //������
    private int totalNumber ;
    
    //��ǰҳ��
    private int currentPage;
    
    //��ҳ��
    private int totalPage;
    
    //��ҳ��ʾ����
    private int pageNumber = 2;
    
    //�ӵڼ�����ʼȡֵ
    private int dbIndex;
    
    //�����ܼ�ȡ������
    private int dbNumber;

    
    public void count()
    {
        //������ҳ��
        int totalPageTemp = this.totalNumber/this.pageNumber;
        int plus = (this.totalNumber % this.pageNumber)==0?0:1;//��ҳ����
        totalPageTemp += plus;
        if (totalPageTemp <= 0)
        {
            totalPageTemp = 1;
        }
        this.totalPage = totalPageTemp;
        
        //���õ�ǰҳ��������ҳ��С�ڵ�ǰҳ��ʱ ��ǰҳ��=��ҳ��
        if (this.totalPage < this.currentPage)
        {
            this.currentPage = this.totalPage;
        }
    }
    
    public Page(){}
    
    public int getTotalNumber()
    {
        return totalNumber;
    }

    public void setTotalNumber(int totalNumber)
    {
        this.totalNumber = totalNumber;
    }

    public int getCurrentPage()
    {
        return currentPage;
    }

    public void setCurrentPage(int currentPage)
    {
        this.currentPage = currentPage;
    }

    public int getTotalPage()
    {
        return totalPage;
    }

    public void setTotalPage(int totalPage)
    {
        this.totalPage = totalPage;
    }

    public int getPageNumber()
    {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber)
    {
        this.pageNumber = pageNumber;
    }

    public int getDbIndex()
    {
        return dbIndex;
    }

    public void setDbIndex(int dbIndex)
    {
        this.dbIndex = dbIndex;
    }

    public int getDbNumber()
    {
        return dbNumber;
    }

    public void setDbNumber(int dbNumber)
    {
        this.dbNumber = dbNumber;
    }
}
