/*
 * 
 */
package com.authority.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.authority.dao.impl.Pager;
import com.authority.model.SysMenus;
import com.authority.service.MenuService;
import com.authority.utils.ListResult;
import com.authority.utils.MessageBox;
import com.authority.utils.StringUtil;

@Controller
@Scope(value ="prototype")
@RequestMapping("/admin/menu")
public class MenuController {
    @Autowired(required = true)
    @Qualifier("menuService")
    private MenuService menuService;
    @RequestMapping(value ="{view}",method = RequestMethod.GET)
    public String view(@PathVariable("view")String view){
        if(StringUtil.isNotEmpty(view)){
            if("list".equals(view)){
                return "core/menu/menu_list";
            }else if("add".equals(view)){
                return "core/menu/menu_add";
            }else if("edit".equals(view)){
                return "core/menu/menu_edit";
            }else if("resouces".equals(view)){
                return "core/menu/menu_resouces_select";
            }
        }
        return "page_not_found_error";
    }
    @RequestMapping(value = "list",method = RequestMethod.POST)
    public @ResponseBody Object list(
            Pager pager,
            String id
            ){
        MessageBox messageBox = new MessageBox();
        if(StringUtil.isNotEmpty(id)){
            if(pager.getParams() == null){
                pager.setParams(new HashMap());
            }
            pager.getParams().put("parentid",id);

        }
        ListResult result = menuService.list(pager);
        if(result != null){
            if(!StringUtil.isNotEmpty(id)){
                messageBox.setTotal(result.getCount());
                messageBox.setSuccess(true);
                messageBox.setRows(result.getDataList());
                return messageBox;
            }else{
                return result.getDataList();
            }
        }
        return messageBox;
    }

    @RequestMapping(value = "add",method = RequestMethod.POST)
    public @ResponseBody MessageBox add(SysMenus menus){
        MessageBox messageBox = new MessageBox();
        if(menuService.add(menus)){
            messageBox.setSuccess(true);
        }
        return messageBox;
    }

    @RequestMapping(value = "edit",method = RequestMethod.POST)
    public @ResponseBody MessageBox edit(SysMenus menus){
        MessageBox messageBox = new MessageBox();
        if(menuService.edit(menus)){
            messageBox.setSuccess(true);
        }
        return messageBox;
    }
    @RequestMapping(value = "delete",method = RequestMethod.POST)
    public @ResponseBody MessageBox delete(String menuid){
        MessageBox messageBox = new MessageBox();
        menuService.delete(menuid);
        messageBox.setSuccess(true);
        return messageBox;
    }

}
