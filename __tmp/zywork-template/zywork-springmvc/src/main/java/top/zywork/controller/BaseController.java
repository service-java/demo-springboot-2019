package top.zywork.controller;

import org.dozer.Mapper;

import javax.annotation.Resource;

/**
 * Controller基础类<br />
 * 创建于2017-10-21
 *
 * @author 王振宇
 * @version 1.0
 */
public class BaseController {

    private Mapper dozerMapper;

    public Mapper getDozerMapper() {
        return dozerMapper;
    }

    @Resource
    public void setDozerMapper(Mapper dozerMapper) {
        this.dozerMapper = dozerMapper;
    }

}
