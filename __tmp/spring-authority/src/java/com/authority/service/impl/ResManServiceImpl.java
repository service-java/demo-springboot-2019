package com.authority.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.authority.dao.BaseDao;
import com.authority.dao.IPager;
import com.authority.dao.ResManDao;
import com.authority.model.SysResources;
import com.authority.service.ResManService;
import com.authority.utils.ListResult;

@Service("resouceService")
public class ResManServiceImpl extends BaseSerivceImpl<SysResources,String> implements ResManService {
    private ResManDao resManDao;
    @Override
    public ListResult getGroupList(IPager pager) {
        return resManDao.getGroupList(pager);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Boolean addGroup(SysResources group) {
        return resManDao.addGroup(group);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Boolean addRes(SysResources resources) {
        return resManDao.addRes(resources);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Boolean delGroup(String groupid) {
        return resManDao.delGroup(groupid);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Boolean delRes(String resid) {
        return resManDao.delRes(resid);  //To change body of implemented methods use File | Settings | File Templates.
    }

    public ResManDao getResManDao() {
        return resManDao;
    }

    public void setResManDao(ResManDao resManDao) {
        this.resManDao = resManDao;
    }

    @Autowired(required = true)
    @Qualifier("resoucesDao")
    @Override
    public void setBaseDao(BaseDao<SysResources, String> baseDao) {
        this.baseDao = baseDao;
        this.resManDao = (ResManDao) baseDao;
    }
}
