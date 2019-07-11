package top.zywork.service;

import top.zywork.dto.ProcessDTO;
import top.zywork.query.ProcessDeployQuery;

/**
 * 流程引擎Service接口<br />
 * 创建于2017-10-14
 *
 * @author 王振宇
 * @version 1.0
 */
public interface ProcessService extends BaseService<ProcessDTO> {

    ProcessDTO getByName(String name);
    void updateDeploy(ProcessDeployQuery processDeployQuery);

}
