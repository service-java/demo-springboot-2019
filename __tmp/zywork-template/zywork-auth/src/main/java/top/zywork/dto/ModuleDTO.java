package top.zywork.dto;

import java.util.Date;

/**
 * 模块DTO<br />
 * 创建于2017-09-12
 *
 * @author 王振宇
 * @version 1.0
 */
public class ModuleDTO extends BaseDTO {
    private static final long serialVersionUID = 2224788515504693596L;

    private Long id;
    private String title;
    private String description;
    private Date createTime;
    private Integer isActive;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

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
