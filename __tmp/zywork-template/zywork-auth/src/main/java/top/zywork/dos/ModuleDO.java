package top.zywork.dos;

import java.util.Date;
import java.util.Set;

/**
 * 模块DO<br />
 * 创建于2017-09-11
 *
 * @author 王振宇
 * @version 1.0
 */
public class ModuleDO extends BaseDO {
    private static final long serialVersionUID = 7446402517629565141L;

    private String title;
    private String description;
    private Date createTime;
    private Integer isActive;

    /**
     * 一个模块包含有多个权限
     */
    private Set<PermissionDO> permissionDOSet;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Set<PermissionDO> getPermissionDOSet() {
        return permissionDOSet;
    }

    public void setPermissionDOSet(Set<PermissionDO> permissionDOSet) {
        this.permissionDOSet = permissionDOSet;
    }
}
