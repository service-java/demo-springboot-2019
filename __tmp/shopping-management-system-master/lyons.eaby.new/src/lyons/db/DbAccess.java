package lyons.db;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * 
 *�������ݿ���
 *
 */
public class DbAccess
{
    /*
     * �������ݿ�
     */
    public SqlSession getSqlSession() throws IOException
    {
        //��ȡMybatis�������ݿ���Ϣ-�����ļ�
        Reader reader = Resources.getResourceAsReader("resources/MybatisConfiguration.xml");//��·����src��·������
        
        //����sqlSessionFactory
        SqlSessionFactory sqlSF = new SqlSessionFactoryBuilder().build(reader);
        
        //��һ�����ݿ�Ự
        SqlSession sqlSession = sqlSF.openSession();
        
        return sqlSession;
    }
    
}
