/*
 * 
 */
package com.authority.dao.impl;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Repository;

import com.authority.dao.UserInfoDao;
import com.authority.model.SysRoles;
import com.authority.model.SysUsers;
import com.authority.model.UserLoginLog;

@Repository("userInfoDao")
public class UserInfoDaoImpl implements UserInfoDao {

	private Session session;

	@Autowired(required = true)
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	@Override
	public SysUsers getUserByName(String username) {
		 try{
	            SysUsers users = (SysUsers) getSession().createQuery("from SysUsers where userAccount=:username").setString("username",username.trim()).uniqueResult();
	            if(users==null){
	            	 return null;
	            }
	            Object level = getSession().createQuery("select max(r.dataLevel) from SysRoles r left join r.role_users u where u.userAccount=:username").setString("username",username.trim()).uniqueResult();
	            if(level!=null&&level instanceof Integer){
	                users.setMaxDataLevel(new Integer(level.toString()));
	            }else{
	                users.setMaxDataLevel(0);
	            }
	            if(users!=null){
	                users.setAuthorities((Collection<GrantedAuthority>) this.grantedAuthorityCollection(users));
	                session.evict(users);
	                session.close();
	                return users;
	            }
	        }catch (Exception e){
	            e.printStackTrace();
	        }
	        return null;
	}

	private Collection<GrantedAuthority> grantedAuthorityCollection(
			SysUsers users) {
		  Set<GrantedAuthority> anthoritiesList = new HashSet<GrantedAuthority>();
	        if(users!=null){
	            Collection<SysRoles> rolesCollection =users.getUser_roles();
	            Iterator it = rolesCollection.iterator();
	            if(it.hasNext()){
	                Object o = it.next();
	                if(o instanceof SysRoles){
	                    SysRoles mSysRoles = (SysRoles) o;
	                    anthoritiesList.addAll(mSysRoles.getRole_authorities());
	                }
	            }
	        }

	        return anthoritiesList;
	}

	@Override
	public String addLoginLog(UserLoginLog log) {
		Session session1 = sessionFactory.getCurrentSession();
        return session1.save(log).toString();
	}

	@Override
	public void addLogoutLog(String logid) {
		Session session1 = sessionFactory.getCurrentSession();

        UserLoginLog log = (UserLoginLog) session1.get(UserLoginLog.class,logid);
        if(log != null){
            log.setLogoutTime(new Date());
            session1.update(log);
        }
	}
	
	private Session getSession(){
        if(session !=  null){
            System.out.println("isConnected:"+session.isConnected()+"@isOpen:"+session.isOpen());
        }
        if(session==null || !session.isConnected() || !session.isOpen()){
            session = sessionFactory.openSession();
        }
        return session;
    }
	

}
