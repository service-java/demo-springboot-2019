package com.authority.dao;

import com.authority.model.SysMenus;
import com.authority.utils.ListResult;

public interface MenuDao extends BaseDao<SysMenus, String>{
    public ListResult<SysMenus> list(IPager pager);
    public ListResult<SysMenus> listSon(IPager pager);
    public Boolean add(SysMenus menus);
    public Boolean edit(SysMenus menus);
}
