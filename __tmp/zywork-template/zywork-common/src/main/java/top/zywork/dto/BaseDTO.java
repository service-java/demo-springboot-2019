package top.zywork.dto;

import java.io.Serializable;

/**
 * 所有DTO的父类，具有所有DTO的统一标识符id属性，id属性为Long类型
 * 创建于2017-08-24
 *
 * @author 王振宇
 * @version 1.0
 */
public class BaseDTO implements Serializable {

    private static final long serialVersionUID = -6526471404848464405L;

    private Long id;

    public BaseDTO() {}

    public BaseDTO(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseDTO baseDTO = (BaseDTO) o;

        return id != null ? id.equals(baseDTO.id) : baseDTO.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
