package top.zywork.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import javax.annotation.Resource;

/**
 * 让注解的SSH可以使用Spring提供的HibernateDaoSupport类<br />
 * 创建于2017-09-08
 *
 * @author 王振宇
 * @version 1.0
 */
public class AppDaoSupport extends HibernateDaoSupport {

    /**
     * 把sessionFactory组件注入到HibernateDaoSupport的sessionFactory属性
     * @param sessionFactory SessionFactory对象
     */
    @Resource(name = "sessionFactory")
    public void setAppSessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }
}
