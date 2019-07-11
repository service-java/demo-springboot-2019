package top.zywork.dao;

import org.springframework.stereotype.Repository;
import top.zywork.dos.ProcessDO;
import top.zywork.query.ProcessDeployQuery;

/**
 * ProcessDAO接口<br />
 * 创建于2017-10-14
 *
 * @author 王振宇
 * @version 1.0
 */
@Repository
public interface ProcessDAO extends BaseDAO<ProcessDO> {

    ProcessDO getByName(String name);
    void updateDeploy(ProcessDeployQuery processDeployQuery);

}
