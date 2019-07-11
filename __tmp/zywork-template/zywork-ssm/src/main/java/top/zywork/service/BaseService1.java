package top.zywork.service;


import top.zywork.dto.PagerDTO;
import top.zywork.query.PageQuery;
import top.zywork.query.StatusQuery;

import java.io.Serializable;
import java.util.List;

/**
 * Service接口，定义了常用的Service方法，实现业务逻辑操作并调用DAO<br />
 * 由Controller调用Service时，需要传递DTO对象进行，并返回DTO对象到Controller<br />
 * 创建于2017-08-24
 *
 * @author 王振宇
 * @version 1.0
 * @param <PK> 实体主键
 */
public interface BaseService1<PK extends Serializable> {

    /**
     * 添加数据到数据库中
     * @param dataTransferObj DTO数据传输对象
     */
    void save(Object dataTransferObj);

    /**
     * 根据DTO实体类从数据库中删除数据
     * @param dataTransferObj DTO数据传输对象
     */
    void remove(Object dataTransferObj);

    /**
     * 根据主键从数据库中删除数据
     * @param id 主键ID
     */
    void removeById(PK id);

    /**
     * 根据对象更新数据库中的数据
     * @param dataTransferObj DTO数据传输对象
     */
    void update(Object dataTransferObj);

    /**
     * 根据主键id查找数据
     * @param id 主键字段值
     * @return DTO数据传输对象
     */
    Object getById(PK id);

    /**
     * 查找所有记录数据
     * @return DTO数据传输对象组成的List列表
     */
    List<Object> listAll();

    /**
     * 根据PageQuery分页查询对象分页查找记录数据
     * @param pageQuery 分页查询对象
     * @return 分页数据DTO对象所组成的List列表
     */
    PagerDTO<Object> listPage(PageQuery pageQuery);

    PagerDTO<Object> listPageByCondition(PageQuery pageQuery, Object queryObj);
}
