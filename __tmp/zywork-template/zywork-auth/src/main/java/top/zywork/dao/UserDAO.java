package top.zywork.dao;

import org.springframework.stereotype.Repository;
import top.zywork.dos.UserDO;
import top.zywork.query.UserAccountPasswordQuery;

/**
 * UserDAO接口<br />
 * 创建于2017-09-05
 *
 * @author 王振宇
 * @version 1.0
 */
@Repository
public interface UserDAO extends BaseDAO<UserDO> {

    /**
     * 根据用户账号及密码查询用户
     * @param userAccountPasswordQuery 用户账号及密码组成的查询对象，账号可以是邮箱，手机号，账户名
     * @return 指定账号及密码的用户对象
     */
    UserDO getByAccountPassword(UserAccountPasswordQuery userAccountPasswordQuery);

}