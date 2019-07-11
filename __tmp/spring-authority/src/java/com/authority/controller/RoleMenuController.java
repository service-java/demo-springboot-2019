package com.authority.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.authority.service.RoleMenuService;
import com.authority.utils.ListResult;
import com.authority.utils.MessageBox;
import com.authority.utils.StringUtil;

@Controller
@RequestMapping("/admin/rolemenu")
@Scope("prototype")
public class RoleMenuController  {

	
    @Autowired
    @Qualifier("roleMenuService")
    private RoleMenuService roleMenuService;

    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String roleMenuList(){
        return "core/rolemenu/rolemenu_list";
    }

    /**
     * 加载角色菜单
     * @param roleId
     * @param id
     * @return
     */
    @RequestMapping(value = "list/{roleId}",method = RequestMethod.POST)
    public @ResponseBody Object roleMenus(@PathVariable("roleId")String roleId,String id){
        MessageBox messageBox = new MessageBox();
        if(StringUtil.isNotEmpty(roleId)){
            ListResult result =roleMenuService.loadRoleMenu(roleId, id);
            if(StringUtil.isNotEmpty(id)){
                return result.getDataList();
            }else{
                messageBox.setSuccess(true);
                messageBox.setRows(result.getDataList());
            }
        }
        return messageBox;
    }

    /**
     * 创建角色菜单
     * @param roleId
     * @return
     */
    @RequestMapping(value = "autocreate/{roleId}")
    public @ResponseBody MessageBox createRoleMenu(@PathVariable("roleId")String roleId){
        MessageBox messageBox = new MessageBox();
        if(StringUtil.isNotEmpty(roleId)){
            if(roleMenuService.createRoleMenu(roleId)){
                messageBox.setSuccess(true);
            }
        }
        return messageBox;
    }

    /**
     * 菜单下移一位
     * @param roleid
     * @param menuid
     * @return
     */
    @RequestMapping(value = "/moveDown",method =RequestMethod.POST)
    public @ResponseBody MessageBox moveDown(
            @RequestParam(value = "roleid",required = true)String roleid,
            @RequestParam(value = "menuid",required = true)String menuid
    ){
        MessageBox messageBox = new MessageBox();
        if(roleMenuService.moveDown(roleid,menuid)){
            messageBox.setSuccess(true);
        }
        return messageBox;
    }

    /**
     * 菜单上移一位
     * @param roleid
     * @param menuid
     * @return
     */
    @RequestMapping(value = "/moveUp",method=RequestMethod.POST)
    public @ResponseBody MessageBox moveUp(
            @RequestParam(value = "roleid",required = true)String roleid,
            @RequestParam(value = "menuid",required = true)String menuid
    ){
        MessageBox messageBox = new MessageBox();
        if(roleMenuService.moveUp(roleid,menuid)){
            messageBox.setSuccess(true);
        }
        return messageBox;
    }

    /**
     * 加载角色菜单
     * @param id
     * @return
     */
    @RequestMapping(value = "/load",method = RequestMethod.GET)
    @ResponseBody
    public Collection getRoleMenu(String id){
        ListResult result = roleMenuService.loadRoleMenu(id);
        if(result != null){
            return result.getDataList();
        }
        return null;
    }
}
