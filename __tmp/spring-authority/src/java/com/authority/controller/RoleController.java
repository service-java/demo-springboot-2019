package com.authority.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.authority.dao.impl.Pager;
import com.authority.model.SysRoles;
import com.authority.service.RoleService;
import com.authority.utils.ListResult;
import com.authority.utils.MessageBox;
import com.authority.utils.StringUtil;

@Controller
@RequestMapping("/admin/role")
@Scope(value = "prototype")
public class RoleController {
    @Autowired(required = true)
    @Qualifier("roleSerivce")
    private RoleService roleService;

    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String listPage(){
        return "core/role/role_list";
    }

    @RequestMapping(value = "add",method = RequestMethod.GET)
    public String addPage(Model model){
        model.addAttribute("role",new SysRoles());
        return "core/role/role_add";
    }

    @RequestMapping(value = "edit/{id}",method = RequestMethod.GET)
    public String editPage(@PathVariable("id")String id,Model model){
        SysRoles roles = roleService.get(id);
        model.addAttribute("role",roles);
        return "core/role/role_add";
    }


    @RequestMapping(value = "/authority/{view}")
    public String getRoleAuthority(@PathVariable("view")String view){
        if(StringUtil.isNotEmpty(view)){
            if("list".equals(view)){
                return "core/role/role_authority_list";
            }else if("add".equals(view)){
                return "core/role/role_authority_select";
            }
        }
        return "page_not_found_error";
    }

    /**
     * 获取角色列表
     * @param pager
     * @return
     */
    @RequestMapping(value = "list",method=RequestMethod.POST)
    public @ResponseBody MessageBox getSysRoleList(Pager pager
                                    ){
        MessageBox messageBox = new MessageBox();
        if(pager != null && pager.getPage() != null && pager.getRows() != null){
            if(roleService != null){
                ListResult result = roleService.getSysRoleList(pager);
                if(result != null){
                    messageBox.setSuccess(true);
                    messageBox.setTotal(result.getCount());
                    messageBox.setRows(result.getDataList());
                }
            }
        }
        return messageBox;
    }

    /**
     * 添加角色
     * @param roles
     * @return
     */
    @RequestMapping(value = "add",method = RequestMethod.POST)
    public @ResponseBody MessageBox addRole(SysRoles roles){
        MessageBox messageBox = new MessageBox();
        if(roles != null){
            if(roleService.addRole(roles)){
                messageBox.setSuccess(true);
            }
        }
        return  messageBox;
    }

    /**
     *删除角色
     * @param roleId
     * @return
     */
    @RequestMapping(value = "delete",method = RequestMethod.POST)
    public @ResponseBody MessageBox delRole(String roleId){
        MessageBox messageBox = new MessageBox();
        if(StringUtil.isNotEmpty(roleId)){
            if(roleService.delRole(roleId)){
                messageBox.setSuccess(true);
            }
        }
        return messageBox;
    }

    @RequestMapping(value = "simpleList",method = RequestMethod.POST)
    public @ResponseBody Object getSimpleRoleList(){
        MessageBox messageBox = new MessageBox();
        ListResult result = roleService.getSimpleRoleList();
        if(result != null){
           return result.getDataList();
        }
        return messageBox;
    }

    /**
     * 获取角色权限列表
     * @param roleId
     * @param pager
     * @return
     */
    @RequestMapping(value = "/authority/list/{roleid}",method = RequestMethod.POST)
    public @ResponseBody MessageBox getRoleAuthorities(
            Pager pager,
            @PathVariable("roleid")String roleId
           )
    {
        MessageBox messageBox = new MessageBox();
        if(pager != null && roleId != null){
            if(pager.getParams() == null){
                pager.setParams(new HashMap());
            }
            pager.getParams().put("roleId",roleId);
            ListResult result = roleService.getRoleAuthorities(pager);
            if(result != null){
                messageBox.setRows(result.getDataList());
                messageBox.setSuccess(true);
                messageBox.setTotal(result.getCount());
            }
        }
        return messageBox;
    }
    @RequestMapping(value = "/authority/add",method = RequestMethod.POST)
    public @ResponseBody MessageBox addRoleAuthority(String authIds,String roleId){
        MessageBox messageBox = new MessageBox();
        if(StringUtil.isNotEmpty(authIds) && StringUtil.isNotEmpty(roleId)){
            if(roleService.addRoleAuthority(authIds,roleId)){
                messageBox.setSuccess(true);
            }
        }
        return messageBox;
    }

    @RequestMapping(value = "/authority/delete")
    public @ResponseBody MessageBox delRoleAuthority(String authIds,String roleId){
        MessageBox messageBox = new MessageBox();
        if(StringUtil.isNotEmpty(authIds) && StringUtil.isNotEmpty(roleId)){
            if(roleService.delRoleAuthority(authIds,roleId)){
                messageBox.setSuccess(true);
            }
        }
        return messageBox;
    }

    public RoleService getRoleService() {
        return roleService;
    }

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }
}
