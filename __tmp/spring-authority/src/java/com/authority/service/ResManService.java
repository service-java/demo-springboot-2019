package com.authority.service;

import com.authority.dao.IPager;
import com.authority.model.SysResources;
import com.authority.utils.ListResult;


public interface ResManService extends BaseService<SysResources,String> {
    public ListResult<SysResources> getGroupList(IPager pager);
    public Boolean addGroup(SysResources group);
    public Boolean addRes(SysResources resources);
    public Boolean delGroup(String groupid);
    public Boolean delRes(String resid);
}
