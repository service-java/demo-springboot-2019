package top.zywork.dos;

import java.io.Serializable;

/**
 * 所有DO的父类，具有所有DO的统一标识符id属性，id属性为Long类型
 * 创建于2017-08-15
 *
 * @author 王振宇
 * @version 1.0
 */
public class BaseDO implements Serializable {

    private static final long serialVersionUID = -1990701184136933177L;

    private Long id;

    public BaseDO() {}

    public BaseDO(Long id) {
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

        BaseDO baseDO = (BaseDO) o;

        return id != null ? id.equals(baseDO.id) : baseDO.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
