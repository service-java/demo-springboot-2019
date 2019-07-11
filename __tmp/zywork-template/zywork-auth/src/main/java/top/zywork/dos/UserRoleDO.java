package top.zywork.dos;

import java.util.Date;

/**
 * 用户角色关系DO<br />
 * 创建于2017-09-11
 *
 * @author 王振宇
 * @version 1.0
 */
public class UserRoleDO extends BaseDO {

    private static final long serialVersionUID = 3252944515496189529L;

    private Date createTime;
    private Integer isActive;

    /**
     * 一个用户角色关系对应一个用户对象
     */
    private UserDO userDO;
    /**
     * 一个用户角色关系对应一个角色对象
     */
    private RoleDO roleDO;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public UserDO getUserDO() {
        return userDO;
    }

    public void setUserDO(UserDO userDO) {
        this.userDO = userDO;
    }

    public RoleDO getRoleDO() {
        return roleDO;
    }

    public void setRoleDO(RoleDO roleDO) {
        this.roleDO = roleDO;
    }
}
