package top.zywork.service;

import top.zywork.dto.UserDTO;
import top.zywork.query.UserAccountPasswordQuery;

/**
 * 用户Service接口<br />
 * 创建于2017-09-05
 *
 * @author 王振宇
 * @version 1.0
 */
public interface UserRedisService extends BaseService<UserDTO> {

    /**
     * 根据用户账号及密码查询用户
     * @param userAccountPasswordQuery 用户账号及密码组成的查询对象，账号可以是邮箱，手机号，账户名
     * @return 指定账号及密码的用户对象
     */
    UserDTO getByAccountPassword(UserAccountPasswordQuery userAccountPasswordQuery);
}
