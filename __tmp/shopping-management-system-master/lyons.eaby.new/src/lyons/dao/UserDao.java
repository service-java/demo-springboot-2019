package lyons.dao;

import java.util.List;
import java.util.Map;

import lyons.user.entity.User;


/**
 * 
 * user.xml ��Ӧ�Ľӿ�
 * 
 * @author lyons(zhanglei)
 * 
 */
public interface UserDao
{
    /**�����û�����ѯ��Ϣ**/
    public List<User> queryByuserNamepassWord(Map<String, Object> map);

    /**ע�����û�**/
    public void insertUser(Map<String, String> registerMap);

    /**��֤�û��Ƿ����**/
    public List<User> queryByuserName(String username);
}
