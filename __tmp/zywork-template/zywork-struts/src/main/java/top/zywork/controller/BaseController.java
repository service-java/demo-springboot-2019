package top.zywork.controller;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.dozer.Mapper;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Controller基础类<br />
 * 创建于2017-10-16
 *
 * @author 王振宇
 * @version 1.0
 */
public class BaseController extends ActionSupport implements ServletRequestAware, ServletResponseAware {

    private static final long serialVersionUID = -5426718726117810066L;

    private HttpServletRequest request;
    private HttpServletResponse response;
    private Mapper dozerMapper;

    public Mapper getDozerMapper() {
        return dozerMapper;
    }

    @Resource
    public void setDozerMapper(Mapper dozerMapper) {
        this.dozerMapper = dozerMapper;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request = httpServletRequest;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    @Override
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.response = httpServletResponse;
    }
}
