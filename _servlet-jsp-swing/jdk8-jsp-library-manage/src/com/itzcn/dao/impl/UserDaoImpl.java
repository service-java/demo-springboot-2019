package com.itzcn.dao.impl;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.itzcn.dao.UserDao;
import com.itzcn.entity.User;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

	public void insertUser(User user) {
		this.getHibernateTemplate().save(user);
	}

	public void delUser(User user) {
		this.getHibernateTemplate().delete(user);
	}

	public void upUser(User user) {
		this.getHibernateTemplate().update(user);
	}

	public User findByUserId(Integer userId) {
		User user = (User) this.getHibernateTemplate().get(User.class, userId);
		return user;
	}

	public List<User> findByUserName(String userName) {
		String hql = "from User user where userName = '" + userName+"'";  
		return (List<User>) this.getHibernateTemplate().find(hql);
	}

	public List<User> findByPostId(Integer postId) {
		String hql = "from User user where postId = "+
	postId +" order by user.userId ";   
        return (List<User>) getHibernateTemplate().find(hql);
	}

	public List<User> findAllUser() {
		String hql = "from User user order by user.userId ";   
        return (List<User>) getHibernateTemplate().find(hql);   
	}

	public List<User> findAllUserByPage(int pageNo, int pageSize) {	
		Session session = this.getSession();
		session.beginTransaction();
		String hql = "from User user order by user.userId";
		Query query = session.createQuery(hql);
		query.setFirstResult((pageNo-1)*pageSize);//���ô���һ�м�¼��ʼ��ȡ
		query.setMaxResults(pageSize);//���ö�ȡ���ٸ���¼
		return (List<User>) query.list();
	}

	public int getUserCount() {
		Long count = null;
		String hql = "select count(user.userId) from User user";
		Iterator iterator = this.getHibernateTemplate().find(hql).iterator();
		if (iterator.hasNext()) {
			count = (Long) iterator.next();
		}
		return count.intValue();
	}

	public int getPostUserCount(Integer postId) {
		Long count = null;
		String hql = "select count(user.post.postId) from User user where postId=" + postId;
		Iterator iterator = this.getHibernateTemplate().find(hql).iterator();
		if (iterator.hasNext()) {
			count = (Long) iterator.next();
		}
		return count.intValue();
	}

}
