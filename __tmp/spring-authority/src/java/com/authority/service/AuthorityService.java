package com.authority.service;

import com.authority.dao.IPager;
import com.authority.model.SysAuthorities;
import com.authority.utils.ListResult;


public interface AuthorityService extends BaseService<SysAuthorities,String> {

    public ListResult<SysAuthorities> getAuthorityList(IPager pager);
    public Boolean addAuthority(SysAuthorities authorities);

    public Boolean delAuthority(String authorityId);

    public ListResult<SysAuthorities> getAuthorityRes(IPager pager);

    public Boolean addAuthorityRes(String resIds,String authorityId);

    public Boolean delAuthorityRes(String resIds,String authorityId);

    public Integer countAuthRole(String authId);
}
