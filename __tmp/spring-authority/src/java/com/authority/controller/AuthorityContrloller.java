package com.authority.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.authority.dao.IPager;
import com.authority.dao.impl.Pager;
import com.authority.model.SysAuthorities;
import com.authority.service.AuthorityService;
import com.authority.utils.ListResult;
import com.authority.utils.MessageBox;
import com.authority.utils.StringUtil;

@Controller
@RequestMapping("admin/authority")
@Scope(value = "prototype")
public class AuthorityContrloller {

    @Autowired
    @Qualifier("authorityService")
    private AuthorityService authorityService;

    @RequestMapping(value = "/{view}",method = RequestMethod.GET)
    public String loadAuthorityPage(@PathVariable("view")String view,String id,Map params){
        String path = "";
        if(StringUtil.isNotEmpty(view)){
            if("list".equals(view)){
                path = "core/authority/authority_list";
            }else if("add".equals(view)){
                path = "core/authority/authority_add";
            }else if("edit".equals(view)){
                path = "core/authority/authority_edit";
            }
        }
        if(StringUtil.isNotEmpty(id)){
            params.put("id",id);
        }
        return path;
    }

    @RequestMapping(value = "/resouces/{view}",method = RequestMethod.GET)
    public String loadAuthorityResuouces(@PathVariable("view")String view,String id,Map params){
        String path = "";
        if(StringUtil.isNotEmpty(view)){
            if("list".equals(view)){
                path = "core/authority/authority_res_list";
            }
            else if("add".equals(view)){
                path = "core/authority/authority_res_add_select";
            }
//            else if("edit".equals(view)){
//                path = "core/authority/authority_edit";
//            }
//            else if("res_list".equals(view)){
//                path = "core/authority/authority_res_list";
//            }else if("res_select".equals(view)){
//                path = "core/authority/authority_res_add_select";
//            }
        }
        if(StringUtil.isNotEmpty(id)){
            params.put("id",id);
        }
        return path;
    }
    @RequestMapping(value = "list",method =RequestMethod.POST)
    public @ResponseBody MessageBox getAhtorityList(Pager pager)
    {
       MessageBox messageBox = new MessageBox();
        if(pager != null && pager.getStart()!= null){
            ListResult listResult = authorityService.getAuthorityList(pager);
            if(listResult != null){
                messageBox.setRows(listResult.getDataList());
                messageBox.setTotal(listResult.getCount());
                messageBox.setSuccess(true);
            }
        }
        return messageBox;
    }

    @RequestMapping(value="add",method = RequestMethod.POST)
    public @ResponseBody MessageBox addAuthority(SysAuthorities authorities){
        MessageBox messageBox = new MessageBox();
        if(authorities != null){
            if(authorityService.addAuthority(authorities)){
                messageBox.setSuccess(true);
            }
        }
        return messageBox;
    }

    public @ResponseBody MessageBox checkAuthorityRoleSize(String authId){
        MessageBox messageBox = new MessageBox();
        if(StringUtil.isNotEmpty(authId)){

        }
        return messageBox;
    }

    @RequestMapping(value = "delete",method =RequestMethod.POST)
    public  @ResponseBody MessageBox delAuthority(String authorityId){
        MessageBox messageBox = new MessageBox();
        if(StringUtil.isNotEmpty(authorityId)){
            if(authorityService.delAuthority(authorityId)){
                messageBox.setSuccess(true);
            }
        }
        return messageBox;
    }
    @RequestMapping(value = "/resouces/list/{id}")
    public @ResponseBody MessageBox getAuthorityRes(Pager pager,
                                                      @PathVariable("id")String authorityId
                                                      ){
        MessageBox messageBox = new MessageBox();
        if(StringUtil.isNotEmpty(authorityId)&& pager != null && pager.getLimit() != null){
            if(pager.getParams() == null){
                pager.setParams(new HashMap());
            }
            pager.getParams().put("authorityId",authorityId);
            ListResult result = authorityService.getAuthorityRes(pager);
            if(result != null){
                messageBox.setSuccess(true);
                messageBox.setTotal(result.getCount());
                messageBox.setRows(result.getDataList());
            }
        }
        return  messageBox;
    }

    @RequestMapping(value = "/resouces/add",method =RequestMethod.POST)
    public @ResponseBody MessageBox addAuthorityRes(
           String resIds,
            String authorityId){
        MessageBox messageBox = new MessageBox();
        if(StringUtil.isNotEmpty(resIds)){
            if(authorityService.addAuthorityRes(resIds,authorityId)){
                messageBox.setSuccess(true);
            }
        }
        return messageBox;
    }

    @RequestMapping(value = "/resouces/delete")
    public @ResponseBody MessageBox delAuthorityRes(String resIds,
                                                    String authorityId){
        MessageBox messageBox = new MessageBox();
        if(StringUtil.isNotEmpty(resIds) && StringUtil.isNotEmpty(authorityId)){
          if(authorityService.delAuthorityRes(resIds,authorityId)){
              messageBox.setSuccess(true);
          }
        }
        return messageBox;
    }



    public AuthorityService getAuthorityService() {
        return authorityService;
    }

    public void setAuthorityService(AuthorityService authorityService) {
        this.authorityService = authorityService;
    }
}
