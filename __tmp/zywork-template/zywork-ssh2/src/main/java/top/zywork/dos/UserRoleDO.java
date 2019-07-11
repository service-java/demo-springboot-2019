package top.zywork.dos;

import javax.persistence.*;
import java.util.Date;

/**
 * 用户角色关系DO<br />
 * 创建于2017-09-11
 *
 * @author 王振宇
 * @version 1.0
 */
@Entity
@Table(name = "t_user_role")
public class UserRoleDO extends BaseDO {

    private static final long serialVersionUID = 3252944515496189529L;

    private Long id;
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

    @ManyToOne
    @JoinColumn(name = "user_id")
    public UserDO getUserDO() {
        return userDO;
    }

    public void setUserDO(UserDO userDO) {
        this.userDO = userDO;
    }

    @ManyToOne
    @JoinColumn(name = "role_id")
    public RoleDO getRoleDO() {
        return roleDO;
    }

    public void setRoleDO(RoleDO roleDO) {
        this.roleDO = roleDO;
    }
}
