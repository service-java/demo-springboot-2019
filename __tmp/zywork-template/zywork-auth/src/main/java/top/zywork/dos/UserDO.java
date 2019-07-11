package top.zywork.dos;

import java.util.Date;
import java.util.Set;

/**
 * 用户DO类，包含有用户的基础信息<br />
 * 创建于2017-09-05
 *
 * @author 王振宇
 * @version 1.0
 */
public class UserDO extends BaseDO {
    private static final long serialVersionUID = -1567329898252094278L;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Set<UserRoleDO> getUserRoleDOSet() {
        return userRoleDOSet;
    }

    public void setUserRoleDOSet(Set<UserRoleDO> userRoleDOSet) {
        this.userRoleDOSet = userRoleDOSet;
    }
}
