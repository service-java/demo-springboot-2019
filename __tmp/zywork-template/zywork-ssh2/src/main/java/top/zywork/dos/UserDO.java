package top.zywork.dos;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * 用户DO类，包含有用户的基础信息<br />
 * 创建于2017-09-05
 *
 * @author 王振宇
 * @version 1.0
 */
@Entity
@Table(name = "t_user")
public class UserDO extends BaseDO {
    private static final long serialVersionUID = -1567329898252094278L;

    private Long id;
    private String email;
    private String phone;
    private String accountName;
    private String password;
    private Date createTime;
    private Integer isActive;

    /**
     * 一个用户对应多个用户角色对象
     */
    private Set<UserRoleDO> userRoleDOSet;

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

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(name = "account_name")
    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
    @JoinColumn(name = "user_id")
    public Set<UserRoleDO> getUserRoleDOSet() {
        return userRoleDOSet;
    }

    public void setUserRoleDOSet(Set<UserRoleDO> userRoleDOSet) {
        this.userRoleDOSet = userRoleDOSet;
    }
}
