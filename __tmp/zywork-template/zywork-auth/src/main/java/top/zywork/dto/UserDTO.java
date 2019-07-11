package top.zywork.dto;

import java.util.Date;

/**
 * 用户DTO<br />
 * 创建于2017-09-05
 *
 * @author 王振宇
 * @version 1.0
 */
public class UserDTO extends BaseDTO {
    private static final long serialVersionUID = 1996923794612096948L;

    private String email;
    private String phone;
    private String accountName;
    private Date createTime;
    private Integer isActive;

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
}
