package top.zywork.dao;


import top.zywork.dos.BaseDO;
import top.zywork.query.PageQuery;
import top.zywork.query.StatusQuery;

import java.util.List;

/**
 * DAO接口，定义了常用的DAO方法，实现CRUD操作<br />
 * 由Service调用DAO时，需要传递DO对象进行，并返回DO对象到Service<br />
 * 创建于2017-08-23
 *
 * @author 王振宇
 * @version 1.0
 * @param <DO> DAO操作的实体类型，具体的DO类
 */
public interface BaseDAO<DO extends BaseDO> {

    /**
     * 添加数据到数据库中
     * @param dataObj DO实体类
     */
    void save(DO dataObj);

    /**
     * 根据DO实体类从数据库中删除数据
     * @param dataObj DO实体类
     */
    void remove(DO dataObj);

    /**
     * 根据主键从数据库中删除数据
     * @param id 主键ID
     */
    void removeById(Long id);

    /**
     * 根据对象更新数据库中的数据
     * @param dataObj DO实体类
     */
    void update(DO dataObj);

    /**
     * 根据StatusQuery查询对象更新is_active状态值
     * @param statusQuery 状态查询对象，包括id和status
     */
    void updateStatus(StatusQuery statusQuery);

    /**
     * 根据主键id查找数据
     * @param id 主键字段值
     * @return 主键字段对应记录的DO对象
     */
    DO getById(Long id);

    /**
     * 查找所有记录数据
     * @return 所有记录数据DO对象组成的List列表
     */
    List<DO> listAll();

    /**
     * 根据PageQuery分页查询对象分页查找记录数据
     * @param pageQuery 分页查询对象
     * @return 分页数据DO对象所组成的List列表
     */
    List<DO> listPage(PageQuery pageQuery);

    /**
     * 返回记录数
     * @return 记录数
     */
    Long count();
}
