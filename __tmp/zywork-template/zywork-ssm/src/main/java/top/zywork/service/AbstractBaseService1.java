package top.zywork.service;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import top.zywork.common.DozerMapperUtils;
import top.zywork.dao.BaseDAO1;
import top.zywork.dto.PagerDTO;
import top.zywork.query.PageQuery;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBaseService1<PK extends Serializable> implements BaseService1<PK> {

    private BaseDAO1 baseDAO;
    private Class<?> doClass;
    private Class<?> dtoClass;
    private Mapper beanMapper;

    @Override
    public void save(Object dataTransferObj) {
        baseDAO.save(beanMapper.map(dataTransferObj, doClass));
    }

    @Override
    public void remove(Object dataTransferObj) {
        baseDAO.remove(beanMapper.map(dataTransferObj, doClass));
    }

    @Override
    public void removeById(PK id) {
        baseDAO.removeById(id);
    }

    @Override
    public void update(Object dataTransferObj) {
        baseDAO.update(beanMapper.map(dataTransferObj, doClass));
    }

    @Override
    public Object getById(PK id) {
        Object doObject = baseDAO.getById(id);
        Object dtoObject = null;
        if (doObject != null) {
            dtoObject = beanMapper.map(doObject, dtoClass);
        }
        return dtoObject;
    }

    @Override
    public List<Object> listAll() {
        List<Object> doObjList = baseDAO.listAll();
        List<Object> dtoObjList = new ArrayList<>();
        if (doObjList != null && doObjList.size() > 0) {
            dtoObjList = DozerMapperUtils.mapList(beanMapper, doObjList, dtoClass);
        }
        return dtoObjList;
    }

    @Override
    public PagerDTO<Object> listPage(PageQuery pageQuery) {
        List<Object> doObjList = baseDAO.listPage(pageQuery);
        PagerDTO<Object> pagerDTO = new PagerDTO<>(pageQuery.getPageNo(), pageQuery.getPageSize());
        if (doObjList != null && doObjList.size() > 0) {
            pagerDTO.setRows(DozerMapperUtils.mapList(beanMapper, doObjList, dtoClass));
            pagerDTO.setTotal(baseDAO.count());
            return pagerDTO;
        } else {
            pagerDTO.setRows(new ArrayList<>());
            pagerDTO.setTotal(0L);
        }
        return pagerDTO;
    }

    @Override
    public PagerDTO<Object> listPageByCondition(PageQuery pageQuery, Object queryObj) {
        List<Object> doObjList = baseDAO.listPageByCondition(pageQuery, queryObj);
        PagerDTO<Object> pagerDTO = new PagerDTO<>(pageQuery.getPageNo(), pageQuery.getPageSize());
        if (doObjList != null && doObjList.size() > 0) {
            pagerDTO.setRows(DozerMapperUtils.mapList(beanMapper, doObjList, dtoClass));
            pagerDTO.setTotal(baseDAO.countByCondition(queryObj));
            return pagerDTO;
        } else {
            pagerDTO.setRows(new ArrayList<>());
            pagerDTO.setTotal(0L);
        }
        return pagerDTO;
    }

    @Autowired
    public void setBeanMapper(Mapper beanMapper) {
        this.beanMapper = beanMapper;
    }

    public Mapper getBeanMapper() {
        return beanMapper;
    }

    public void setBaseDAO(BaseDAO1 baseDAO) {
        this.baseDAO = baseDAO;
    }

    public void initService(Class<?> doClass, Class<?> dtoClass) {
        this.doClass = doClass;
        this.dtoClass = dtoClass;
    }
}
