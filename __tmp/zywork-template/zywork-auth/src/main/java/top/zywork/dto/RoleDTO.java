package top.zywork.dto;

import java.util.Date;

/**
 * 角色DTO<br />
 * 创建于2017-09-11
 *
 * @author 王振宇
 * @version 1.0
 */
public class RoleDTO extends BaseDTO {
    private static final long serialVersionUID = -796344353596026350L;

    private String title;
    private String description;
    private Date createTime;
    private Integer isActive;

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
}
