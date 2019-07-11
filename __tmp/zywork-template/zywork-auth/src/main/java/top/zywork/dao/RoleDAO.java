package top.zywork.dao;

import org.springframework.stereotype.Repository;
import top.zywork.dos.RoleDO;

import java.util.List;

/**
 * RoleDAO接口<br />
 * 创建于2017-09-11
 *
 * @author 王振宇
 * @version 1.0
 */
@Repository
public interface RoleDAO extends BaseDAO<RoleDO> {

    /**
     * 根据用户账户查找对应的所有角色
     * @param account 用户账户，可能是邮箱，手机号或账户名
     * @return 指定用户账户对应的所有角色组成的列表
     */
    List<RoleDO> listByAccount(String account);
}
