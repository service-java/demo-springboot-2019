package top.zywork.dao;

import org.springframework.stereotype.Repository;
import top.zywork.dos.PermissionDO;

import java.util.List;

/**
 * 权限DAO接口<br />
 * 创建于2017-09-11
 *
 * @author 王振宇
 * @version 1.0
 */
@Repository
public interface PermissionDAO extends BaseDAO<PermissionDO> {

    /**
     * 根据模块id查找模块相关的权限
     * @param moduleId 模块编号
     * @return 指定模块下的所有权限
     */
    List<PermissionDO> listByModuleId(String moduleId);

    /**
     * 根据单个角色id查找角色相关的权限
     * @param roleId 角色编号
     * @return 指定角色下的所有权限
     */
    List<PermissionDO> listByRoleId(String roleId);

    /**
     * 根据多个角色id查找角色相关的权限
     * @param roleIds 角色编号列表
     * @return 多个角色下的所有权限，并剔除了重复的权限
     */
    List<PermissionDO> listByRoleIds(List<Long> roleIds);

    /**
     * 根据用户名查找用户相关的权限
     * @param account 用户名，可以是账户号，手机号，邮箱
     * @return 指定用户名下的所有权限，并剔除了重复的权限
     */
    List<PermissionDO> listByAccount(String account);
}