package top.zywork.dos;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * 角色DO类，角色基础信息<br />
 * 创建于2017-09-11
 *
 * @author 王振宇
 * @version 1.0
 */
@Entity
@Table(name = "t_role")
public class RoleDO extends BaseDO {

    private static final long serialVersionUID = -4615193831961074264L;

    private Long id;
    private String title;
    private String description;
    private Date createTime;
    private Integer isActive;

    /**
     * 一个角色对应于多个用户角色关系
     */
    private Set<UserRoleDO> userRoleDOSet;
    /**
     * 一个角色对应于多个角色权限关系
     */
    private Set<RolePermissionDO> rolePermissionDOSet;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "is_active")
    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    @OneToMany
    @JoinColumn(name = "role_id")
    public Set<UserRoleDO> getUserRoleDOSet() {
        return userRoleDOSet;
    }

    @OneToMany
    @JoinColumn(name = "role_id")
    public void setUserRoleDOSet(Set<UserRoleDO> userRoleDOSet) {
        this.userRoleDOSet = userRoleDOSet;
    }

    @Transient
    public Set<RolePermissionDO> getRolePermissionDOSet() {
        return rolePermissionDOSet;
    }

    public void setRolePermissionDOSet(Set<RolePermissionDO> rolePermissionDOSet) {
        this.rolePermissionDOSet = rolePermissionDOSet;
    }
}
