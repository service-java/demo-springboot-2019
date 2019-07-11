package com.authority.service;

import com.authority.dao.IPager;
import com.authority.model.SysMenus;
import com.authority.utils.ListResult;

public interface MenuService extends BaseService<SysMenus,String> {
    public ListResult<SysMenus> list(IPager pager);
    public Boolean add(SysMenus menus);
    public Boolean edit(SysMenus menus);
}
