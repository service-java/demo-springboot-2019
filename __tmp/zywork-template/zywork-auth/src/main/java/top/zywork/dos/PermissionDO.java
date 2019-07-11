package top.zywork.dos;

import java.util.Date;
import java.util.Set;

/**
 * 权限DO<br />
 * 创建于2017-09-11
 *
 * @author 王振宇
 * @version 1.0
 */
public class PermissionDO extends BaseDO {
    private static final long serialVersionUID = -6442922602180183519L;

    private String title;
    private String permission;
    private String description;
    private Date createTime;
    private Integer isActive;

    /**
     * 一个权限属于一个模块
     */
    private ModuleDO moduleDO;
    /**
     * 一个权限对应于多个角色权限关系
     */
    private Set<RolePermissionDO> rolePermissionDOSet;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

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

    public ModuleDO getModuleDO() {
        return moduleDO;
    }

    public void setModuleDO(ModuleDO moduleDO) {
        this.moduleDO = moduleDO;
    }

    public Set<RolePermissionDO> getRolePermissionDOSet() {
        return rolePermissionDOSet;
    }

    public void setRolePermissionDOSet(Set<RolePermissionDO> rolePermissionDOSet) {
        this.rolePermissionDOSet = rolePermissionDOSet;
    }
}
