package lyons.interceptor;

import java.util.Properties;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;

import com.sun.corba.se.pept.transport.Connection;

/**
 * 
 * ��ҳ������
 * 
 * @author  lyons(zhanglei)
 */
//����jdbc��                        ����������                      ����               ����
@Intercepts({@Signature(type=StatementHandler.class,method="prepare",args={Connection.class})})
public class PageInterceptor implements Interceptor //ʵ��mybatis���������ӿ�
{

    @Override
    public Object intercept(Invocation arg0)
        throws Throwable
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object plugin(Object arg0)
    {
        return null;
    }

    @Override
    public void setProperties(Properties arg0)
    {
        // TODO Auto-generated method stub
        
    }
    
}
