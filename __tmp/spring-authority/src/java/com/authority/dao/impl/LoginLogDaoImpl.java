/*
 * 
 */
package com.authority.dao.impl;

import org.springframework.stereotype.Repository;

import com.authority.dao.IPager;
import com.authority.dao.LoginLogDao;
import com.authority.model.UserLoginLog;
import com.authority.utils.ListResult;
import com.authority.utils.StringUtil;


@Repository("loginLogDao")
public class LoginLogDaoImpl extends BaseDaoImpl<UserLoginLog,String> implements LoginLogDao{

    @Override
    public ListResult<UserLoginLog> list(IPager pager) {
    	ListResult<UserLoginLog> result = new ListResult<UserLoginLog>();

        String hql = " where 1=1 ";

        if(StringUtil.isNotEmpty(pager.getParams().get("username"))){
            hql += " and userName like '%"+pager.getParams().get("username")+"%' ";
        }

        if(StringUtil.isNotEmpty(pager.getParams().get("logintimestart"))){
            hql += " and loginTime>='"+pager.getParams().get("logintimestart")+"' ";
        }

        if(StringUtil.isNotEmpty(pager.getParams().get("logintimeend"))){
            hql += " and loginTime<='"+pager.getParams().get("logintimeend")+"' ";
        }

        if(StringUtil.isNotEmpty(pager.getParams().get("loginIp"))){
            hql += " and loginIp like '%"+pager.getParams().get("loginIp")+"%' ";
        }

        if(StringUtil.isNotEmpty(pager.getParams().get("userClient"))){
            hql += " and userAgent like '%"+pager.getParams().get("userClient")+"%' ";
        }

        if(StringUtil.isNotEmpty(pager.getOrder()) && StringUtil.isNotEmpty(pager.getSort())){
            hql += " order by "+pager.getSort()+" "+pager.getOrder()+" ";
        }

        Long count = (Long) getSession().createQuery("select count(*) from UserLoginLog "+hql).uniqueResult();
        if(count != null && count>0)
        {
            result.setDataList(getSession().createQuery("from UserLoginLog " + hql)
                    .setFirstResult(pager.getStart())
                    .setMaxResults(pager.getLimit())
                    .list());
            result.setCount(count);
        }
        return result;
    }
}
