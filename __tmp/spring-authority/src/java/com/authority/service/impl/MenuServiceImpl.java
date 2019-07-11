package com.authority.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.authority.dao.BaseDao;
import com.authority.dao.IPager;
import com.authority.dao.MenuDao;
import com.authority.model.SysMenus;
import com.authority.service.MenuService;
import com.authority.utils.ListResult;
import com.authority.utils.StringUtil;

@Service("menuService")
public class MenuServiceImpl extends BaseSerivceImpl<SysMenus,String> implements MenuService {

    private MenuDao menuDao;
    @Override
    public ListResult<SysMenus> list(IPager pager) {
        if(pager != null && pager.getParams() != null && StringUtil.isNotEmpty(pager.getParams().get("parentid"))){
            return menuDao.listSon(pager);
        }
        return menuDao.list(pager);
    }

    @Override
    public Boolean add(SysMenus menus) {
        return menuDao.add(menus);
    }

    @Override
    public Boolean edit(SysMenus menus) {
        return menuDao.add(menus);
    }


    @Autowired(required = true)
    @Qualifier("menuDao")
    @Override
    public void setBaseDao(BaseDao<SysMenus, String> baseDao) {
        this.baseDao = baseDao;
        this.menuDao = (MenuDao) baseDao;
    }



    public MenuDao getMenuDao() {
        return menuDao;
    }

}
