package top.zywork.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.zywork.common.DozerMapperUtils;
import top.zywork.dao.ProcessDAO;
import top.zywork.dos.ProcessDO;
import top.zywork.dto.PagerDTO;
import top.zywork.dto.ProcessDTO;
import top.zywork.query.PageQuery;
import top.zywork.query.ProcessDeployQuery;
import top.zywork.query.StatusQuery;
import top.zywork.service.AbstractBaseService;
import top.zywork.service.ProcessService;

import java.util.List;

/**
 * 流程引擎Service服务实现类<br />
 * 创建于2017-10-15
 *
 * @author 王振宇
 * @version 1.0
 */
@Service
public class ProcessServiceImpl extends AbstractBaseService implements ProcessService {

    private ProcessDAO processDAO;

    @Override
    public void save(ProcessDTO processDTO) {
        processDAO.save(getDozerMapper().map(processDTO, ProcessDO.class));
    }

    @Override
    public void remove(ProcessDTO processDTO) {

    }

    @Override
    public void removeById(Long id) {

    }

    @Override
    public void update(ProcessDTO processDTO) {

    }

    @Override
    public void updateStatus(StatusQuery statusQuery) {

    }

    @Override
    public ProcessDTO getById(Long id) {
        return null;
    }

    @Override
    public List<ProcessDTO> listAll() {
        return null;
    }

    @Override
    public PagerDTO<ProcessDTO> listPage(PageQuery pageQuery) {
        List<ProcessDTO> processDTOList = DozerMapperUtils.map(getDozerMapper(), processDAO.listPage(pageQuery), ProcessDTO.class);
        PagerDTO<ProcessDTO> pagerDTO = new PagerDTO<>(pageQuery.getPageNo(), pageQuery.getPageSize());
        pagerDTO.setTotal(processDAO.count());
        pagerDTO.setRows(processDTOList);
        return pagerDTO;
    }

    @Override
    public ProcessDTO getByName(String name) {
        ProcessDO processDO = processDAO.getByName(name);
        return processDO == null ? null : getDozerMapper().map(processDO, ProcessDTO.class);
    }

    @Override
    public void updateDeploy(ProcessDeployQuery processDeployQuery) {
        ProcessDO processDO = processDAO.getByName(processDeployQuery.getProcessName());
        processDeployQuery.setProcessId(processDO.getId());
        processDAO.updateDeploy(processDeployQuery);
    }

    @Autowired
    public void setProcessDAO(ProcessDAO processDAO) {
        this.processDAO = processDAO;
    }
}
