package top.zywork.dos;

import java.util.Date;

/**
 * 角色权限关系DO<br />
 * 创建于2017-09-11
 *
 * @author 王振宇
 * @version 1.0
 */
public class RolePermissionDO extends BaseDO {
    private static final long serialVersionUID = -7401436319951101550L;

    private Date createTime;
    private Integer isActive;

    /**
     * 一个角色权限关系对应一个角色对象
     */
    private RoleDO roleDO;
    /**
     * 一个角色权限关系对应一个权限对象
     */
    private PermissionDO permissionDO;

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

    public RoleDO getRoleDO() {
        return roleDO;
    }

    public void setRoleDO(RoleDO roleDO) {
        this.roleDO = roleDO;
    }

    public PermissionDO getPermissionDO() {
        return permissionDO;
    }

    public void setPermissionDO(PermissionDO permissionDO) {
        this.permissionDO = permissionDO;
    }
}
