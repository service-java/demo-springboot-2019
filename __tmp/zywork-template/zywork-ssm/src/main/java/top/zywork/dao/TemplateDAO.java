package top.zywork.dao;

import org.springframework.stereotype.Repository;
import top.zywork.dos.TemplateDO;

/**
 * TemplateDAO接口<br />
 * 创建于2017-08-24
 *
 * @author 王振宇
 * @version 1.0
 */
@Repository
public interface TemplateDAO extends BaseDAO<TemplateDO> {

    void save1(Object obj);
    
}
