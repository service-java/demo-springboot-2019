package top.zywork.query;

import java.util.Date;

/**
 * 流程部署更新查询对象<br />
 * 创建于2017-10-17
 *
 * @author 王振宇
 * @version 1.0
 */
public class ProcessDeployQuery {
    private Long processId;
    private String processName;
    private Integer isDeploy;
    private Date deployTime;

    public ProcessDeployQuery() {}

    public ProcessDeployQuery(Long processId, String processName, Integer isDeploy, Date deployTime) {
        this.processId = processId;
        this.processName = processName;
        this.isDeploy = isDeploy;
        this.deployTime = deployTime;
    }

    public Long getProcessId() {
        return processId;
    }

    public void setProcessId(Long processId) {
        this.processId = processId;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public Integer getIsDeploy() {
        return isDeploy;
    }

    public void setIsDeploy(Integer isDeploy) {
        this.isDeploy = isDeploy;
    }

    public Date getDeployTime() {
        return deployTime;
    }

    public void setDeployTime(Date deployTime) {
        this.deployTime = deployTime;
    }
}
