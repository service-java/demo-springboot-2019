package top.zywork.common;

import org.hibernate.Session;
import org.hibernate.query.Query;
import top.zywork.dos.BaseDO;
import top.zywork.query.BaseQuery;
import top.zywork.query.PageQuery;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Hibernate工具类<br />
 * 创建于2017-11-27
 *
 * @author 王振宇
 * @version 1.0
 */
public class HibernateUtils {

    /**
     * 根据条件查询对象和分页对象进行分页模糊搜索，此方法只能针对单个表
     * @param session Hibernate session
     * @param pageQuery 分页查询对象
     * @param doClass 搜索目标的类
     * @param queryObj 条件查询对象
     * @param <DO> 搜索目标的类必须是BaseDO的子类
     * @param <QO> 条件查询对象必须是BaseQuery的子类
     * @return 分页且模糊查询的结果组成的列表
     */
    @SuppressWarnings({"unchecked"})
    public static <DO extends BaseDO, QO extends BaseQuery> List<DO> pagerCondition(Session session,
                                                                                     PageQuery pageQuery,
                                                                                     Class<DO> doClass,
                                                                                     QO queryObj) {
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<DO> criteriaQuery = criteriaBuilder.createQuery(doClass);
        Root<DO> root = criteriaQuery.from(doClass);
        criteriaQuery = rebuildRowsCriteriaQuery(criteriaQuery, criteriaBuilder, root, queryObj);
        Query query = session.createQuery(criteriaQuery);
        query.setFirstResult(pageQuery.getBeginIndex());
        query.setMaxResults(pageQuery.getPageSize());
        return query.list();
    }

    /**
     * 根据条件查询对象计数
     * @param session Hibernate session
     * @param doClass 搜索目标的类
     * @param queryObj 条件查询对象
     * @param fieldCount 计数的字段名
     * @param <DO> 搜索目标的类必须是BaseDO的子类
     * @param <QO> 条件查询对象必须是BaseQuery的子类
     * @return 模糊搜索对应的结果计数
     */
    public static <DO extends BaseDO, QO extends BaseQuery> Long countCondition(Session session,
                                                                                Class<DO> doClass,
                                                                                QO queryObj,
                                                                                String fieldCount) {
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<DO> root = criteriaQuery.from(doClass);
        if (fieldCount == null) {
            fieldCount = "id";
        }
        criteriaQuery.select(criteriaBuilder.count(root.get(fieldCount)));
        criteriaQuery = rebuildCountCriteriaQuery(criteriaQuery, criteriaBuilder, root, queryObj);
        return session.createQuery(criteriaQuery).uniqueResult();
    }

    @SuppressWarnings({"unchecked"})
    private static <DO extends BaseDO, QO extends BaseQuery> CriteriaQuery<DO> rebuildRowsCriteriaQuery(CriteriaQuery<DO> criteriaQuery,
                                                                                 CriteriaBuilder criteriaBuilder,
                                                                                 Root<DO> root,
                                                                                 QO queryObj) {
        return rebuildCriteriaQuery(criteriaQuery, criteriaBuilder, root, queryObj);
    }

    @SuppressWarnings({"unchecked"})
    private static <DO extends BaseDO, QO extends BaseQuery> CriteriaQuery<Long> rebuildCountCriteriaQuery(CriteriaQuery<Long> criteriaQuery,
                                                            CriteriaBuilder criteriaBuilder,
                                                            Root<DO> root,
                                                            QO queryObj) {
        return rebuildCriteriaQuery(criteriaQuery, criteriaBuilder, root, queryObj);
    }

    /**
     * 重新由查询对象封装JPA中的CriteriaQuery对象，只要是字符串，则支持模糊搜索。此方法暂时不支持时间，数字等的比较，如果需要使用
     * 时间或数字等的比较，可以使用注解来扩展。
     * @param criteriaQuery JPA中的CriteriaQuery对象
     * @param criteriaBuilder JPA中的CriteriaBuilder对象
     * @param root JPA中的Root对象
     * @param queryObj 条件查询对象
     * @param <DO> 搜索目标的类，必须是BaseDO的子类
     * @param <QO> 查询对象必须是BaseQuery的子类
     * @return 重新构建的CriteriaQuery对象
     */
    private static <DO extends BaseDO, QO extends BaseQuery> CriteriaQuery rebuildCriteriaQuery(CriteriaQuery criteriaQuery,
                                                                    CriteriaBuilder criteriaBuilder,
                                                                    Root<DO> root,
                                                                    QO queryObj) {
        Field[] fields = queryObj.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String fieldName = field.getName();
            try {
                Object value = field.get(queryObj);
                if (value != null && value instanceof String) {
                    criteriaQuery.where(criteriaBuilder.like(root.get(fieldName), "%" + (String) value + "%"));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return criteriaQuery;
    }

    /**
     * 把在Hibernate中使用原生SQL生成的对象数组列表转化成JavaBean列表。
     * 由于可能是多个表的数据字段的组合，故不使用DO对象，而是把多个表的字段的组合封装成DTO对象
     * 此方法仅供参考使用，可以使用Hibernate自生提供的addEntity方法实现结果到JavaBean的转化。
     * @param data 由原生SQL查询得到的对象数组列表
     * @param fieldArray 按照原生SQL返回字段的顺序排列的JavaBean中的属性名
     * @param dtoClass 需要转换成的目标对象类型
     * @param <DTO> 转换的目标对象，由于可能是多个表的数据字段的组合，故不使用DO对象，而是把多个表的字段的组合封装成DTO对象
     * @return 由原生SQL得到的对象数组列表转化成的JavaBean列表对象
     */
    public static <DTO> List<DTO> resultToBean(List<Object[]> data, String[] fieldArray, Class<DTO> dtoClass) {
        List<DTO> list = new ArrayList<>();
        try {
            for (Object[] row : data) {
                DTO dto = dtoClass.newInstance();
                for (int i = 0, len = row.length; i < len; i++) {
                    Object value = row[i];
                    String fieldName = fieldArray[i];
                    Field field = dtoClass.getDeclaredField(fieldName);
                    field.setAccessible(true);
                    if (value != null && value instanceof BigInteger) {
                        field.set(dto, ((BigInteger) value).longValue());
                    } else {
                        field.set(dto, value);
                    }
                }
                list.add(dto);
            }
        } catch (InstantiationException | IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        return list;
    }

}
