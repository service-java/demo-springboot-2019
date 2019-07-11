
package com.authority.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.authority.dao.IPager;
import com.authority.dao.impl.Pager;
import com.authority.model.SysUsers;
import com.authority.service.UserService;
import com.authority.utils.ListResult;
import com.authority.utils.MessageBox;
import com.authority.utils.StringUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class UserManagerCtrl.
 */
@Controller
@RequestMapping("/admin/user")
@Scope(value = "prototype")
public class UserManagerController {
    
    /** The user manager service. */
    @Autowired(required = false)
    @Qualifier("userService")
    private UserService userManagerService;

    
    /**
     * List page.
     *
     * @return the string
     */
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String ListPage(){
        return "core/user/user_list";
    }

    /**
     * User role list page.
     *
     * @return the string
     */
    @RequestMapping(value = "user_role_list",method = RequestMethod.GET)
    public String UserRoleListPage(){
        return "core/user/user_role_list";
    }

    /**
     * User role select page.
     *
     * @return the string
     */
    @RequestMapping(value = "user_role_select",method = RequestMethod.GET)
    public String UserRoleSelectPage(){
        return "core/user/user_role_select";
    }

    /**
     * New account validate.
     *
     * @param account the account
     * @return the object
     */
    @RequestMapping(value = "account/validate",method = RequestMethod.POST)
    @ResponseBody
    public Object NewAccountValidate(String account){
        if(StringUtil.isNotEmpty(account)){
            return userManagerService.accountUniqueValidate(null,account);
        }
        return null;
    }
    
    /**
     * Modify account validate.
     *
     * @param userid the userid
     * @param account the account
     * @return the object
     */
    @RequestMapping(value = "account/{id}/validate",method = RequestMethod.POST)
    @ResponseBody
    public Object ModifyAccountValidate(@PathVariable("id")String userid ,String account){
        if(StringUtil.isNotEmpty(account)){
            return userManagerService.accountUniqueValidate(userid,account);
        }
        return null;
    }

    /**
     * Adds the page.
     *
     * @param model the model
     * @return the string
     */
    @RequestMapping(value = "add",method = RequestMethod.GET)
    public String addPage(Model model){
        model.addAttribute("user",new SysUsers());
        return "core/user/user_edit";
    }

//    @RequestMapping(value = "edit/{id}",method = RequestMethod.GET)
//    public String editPage(@PathVariable("id")String id,Model model){
//        MSysUsers users = userManagerService.get(id);
//        if(users!=null){
//            users.setPassword(null);
//
//        }
//        return "core/user/user_add";
//    }

    /**
 * Change password page.
 *
 * @return the string
 */
@RequestMapping(value = "/modifypassword",method = RequestMethod.GET)
    public String changePasswordPage(){
        return "core/user/user_modifypassword";
    }

    /**
     * Change pass word.
     *
     * @param oldpassword the oldpassword
     * @param newpassword the newpassword
     * @return the message box
     */
    @RequestMapping(value = "/modifypassword",method = RequestMethod.POST)
    public @ResponseBody MessageBox changePassWord(String oldpassword,String newpassword){
        MessageBox messageBox = new MessageBox();
        if(StringUtil.isNotEmpty(oldpassword) && StringUtil.isNotEmpty(newpassword)){
            if(userManagerService.saveUserPassword(oldpassword,newpassword)){
                messageBox.setSuccess(true);
            }
        }
        return messageBox;
    }

    /**
     * User role view.
     *
     * @param view the view
     * @param model the model
     * @return the string
     */
    @RequestMapping(value = "/role/{view}",method = RequestMethod.GET)
    public String UserRoleView(@PathVariable("view")String view,Model model){
        if(StringUtil.isNotEmpty(view)){
            if("list".equals(view)){
                return "core/user/user_role_list";
            }else if("add".equals(view)){
                return "core/user/user_role_select";
            }
        }
        return "page_not_found_error";
    }

    /**
     * 加载用户信息列表.
     *
     * @param pager the pager
     * @return the user data
     */
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public @ResponseBody MessageBox getUserData(Pager pager
    ){
        MessageBox messageBox = new MessageBox();
        if(pager != null && pager.getLimit() != null){
           if(userManagerService!=null){
               ListResult result = userManagerService.getUserData(pager);
               if(result != null){
                   messageBox.setTotal(result.getCount());
                   messageBox.setRows(result.getDataList());
                   messageBox.setSuccess(true);
               }
           }
        }
        return messageBox;
    }
    
    /**
     * Adds the user.
     *
     * @param users the users
     * @return the message box
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public @ResponseBody MessageBox addUser(SysUsers users){
        MessageBox messageBox = new MessageBox();
        if(users != null){
            if(userManagerService != null && userManagerService.addUser(users)){
                messageBox.setSuccess(true);
            }
        }
        return messageBox;
    }

    /**
     * Edits the.
     *
     * @param id the id
     * @param model the model
     * @return the string
     */
    @RequestMapping(value ="edit/{id}",method = RequestMethod.GET)
    public String edit(@PathVariable("id")String id,Model model){
        if(StringUtil.isNotEmpty(id))
        {
             SysUsers user = userManagerService.get(id);
            model.addAttribute("user",user);
        }
        return "core/user/user_edit";
    }
    
    /**
     * Del user by id.
     *
     * @param userid the userid
     * @return the message box
     */
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public @ResponseBody MessageBox delUserById(String userid){
        MessageBox messageBox = new MessageBox();
        if(StringUtil.isNotEmpty(userid)){
            if(userManagerService.delUserById(userid)){
                messageBox.setSuccess(true);
            }
        }
        return messageBox;
    }

    /**
     * Adds the user role.
     *
     * @param roleIds the role ids
     * @param userId the user id
     * @return the message box
     */
    @RequestMapping(value = "/role/add",method = RequestMethod.POST)
    public @ResponseBody MessageBox addUserRole(String roleIds,
                                               String userId){
        MessageBox messageBox = new MessageBox();
        if(StringUtil.isNotEmpty(roleIds) && StringUtil.isNotEmpty(userId)){
            if(userManagerService.addUserRole(roleIds,userId)){
                messageBox.setSuccess(true);
            }
        }
        return  messageBox;
    }

    /**
     * Del user role.
     *
     * @param roleIds the role ids
     * @param userId the user id
     * @return the message box
     */
    @RequestMapping(value = "/role/delete",method = RequestMethod.POST)
    public @ResponseBody MessageBox delUserRole(String roleIds,
                                                String userId){
        MessageBox messageBox = new MessageBox();
        if(StringUtil.isNotEmpty(roleIds) && StringUtil.isNotEmpty(userId)){
            if(userManagerService.delUserRole(roleIds, userId)){
                messageBox.setSuccess(true);
            }
        }
        return  messageBox;
    }

    /**
     * Gets the sys user by type.
     *
     * @param type the type
     * @return the sys user by type
     */
    @RequestMapping(value = "find/user/{type}",method = RequestMethod.POST)
    @ResponseBody
    public List getSysUserByType(@PathVariable("type")String type){
        if(StringUtil.isNotEmpty(type))
        {
            return userManagerService.getUserByType(type);
        }
        return null;
    }

    /**
     * Gets the user role.
     *
     * @param pager the pager
     * @param userId the user id
     * @return the user role
     */
    @RequestMapping(value = "role/list/{userid}",method = RequestMethod.POST)
    public @ResponseBody MessageBox getuserRole(Pager pager,
                                                  @PathVariable("userid")String userId
                                                  ){
        MessageBox messageBox = new MessageBox();
        if(pager != null && pager.getStart() != null && StringUtil.isNotEmpty(userId)){
            if(pager.getParams() == null){
                pager.setParams(new HashMap());

            }
            pager.getParams().put("userId",userId);
            ListResult result =userManagerService.getUserRole(pager);
            if(result != null){
                messageBox.setSuccess(true);
                messageBox.setRows(result.getDataList());
                messageBox.setTotal(result.getCount());
            }
        }
        return messageBox;
    }

    /**
     * Lock user.
     *
     * @param userid the userid
     * @return the message box
     */
    @RequestMapping(value = "lock/{userid}",method = RequestMethod.POST)
    public @ResponseBody MessageBox lockUser(@PathVariable("userid")String userid){
        MessageBox messageBox = new MessageBox();
        if(StringUtil.isNotEmpty(userid)){
            if(userManagerService.updateLockUser(userid)){
                messageBox.setSuccess(true);
            }
        }
        return messageBox;
    }

    /**
     * Enable user.
     *
     * @param userid the userid
     * @return the message box
     */
    @RequestMapping(value = "enable/{userid}",method = RequestMethod.POST)
    public @ResponseBody MessageBox enableUser(@PathVariable("userid")String userid){
        MessageBox messageBox = new MessageBox();
        if(StringUtil.isNotEmpty(userid)){
            if(userManagerService.updateEnableUser(userid)){
                messageBox.setSuccess(true);
            }
        }
        return messageBox;
    }

    /**
     * Gets the user manager service.
     *
     * @return the user manager service
     */
    public UserService getUserManagerService() {
        return userManagerService;
    }

    /**
     * Sets the user manager service.
     *
     * @param userManagerService the new user manager service
     */
    public void setUserManagerService(UserService userManagerService) {
        this.userManagerService = userManagerService;
    }
}
