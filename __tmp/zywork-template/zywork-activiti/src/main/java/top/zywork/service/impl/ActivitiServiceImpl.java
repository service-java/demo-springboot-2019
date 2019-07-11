package top.zywork.service.impl;

import org.activiti.engine.*;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.image.impl.DefaultProcessDiagramGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.zywork.common.ExceptionUtils;
import top.zywork.common.FileUtils;
import top.zywork.constant.BPMNConstants;
import top.zywork.service.ActivitiService;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

/**
 * Activiti服务接口实现类<br />
 * 创建于2017-10-17
 *
 * @author 王振宇
 * @version 1.0
 */
@Service
public class ActivitiServiceImpl implements ActivitiService {

    private ProcessEngine processEngine;
    private RepositoryService repositoryService;
    private RuntimeService runtimeService;
    private TaskService taskService;
    private IdentityService identityService;
    private HistoryService historyService;

    @Override
    public Deployment deploy(String processName) {
        try {
            ZipInputStream zipInputStream = new ZipInputStream(
                    new FileInputStream(
                            FileUtils.getBPMNDir() + processName + BPMNConstants.SUFFIX_ZIP));
            Deployment deployment = repositoryService.createDeployment().addZipInputStream(zipInputStream).deploy();
            zipInputStream.close();
            return deployment;
        } catch (IOException e) {
            throw ExceptionUtils.serviceException(e);
        }
    }

    @Override
    public void remove(String processName) {
        List<Deployment> deploymentList = repositoryService.createDeploymentQuery().processDefinitionKey(processName).list();
        for (Deployment deployment : deploymentList) {
            repositoryService.deleteDeployment(deployment.getId(), true);
        }
    }

    @Override
    public ProcessInstance startOneProcess(String processKey) {
        List<ProcessInstance> processInstanceList = listProcessInstances(processKey);
        return (processInstanceList != null && processInstanceList.size() > 0) ? null : runtimeService.startProcessInstanceByKey(processKey);
    }

    @Override
    public ProcessInstance startOneProcess(String processKey, Map<String, Object> variables) {
        List<ProcessInstance> processInstanceList = listProcessInstances(processKey);
        return (processInstanceList != null && processInstanceList.size() > 0) ? null : runtimeService.startProcessInstanceByKey(processKey, variables);
    }

    @Override
    public ProcessInstance startOneProcess(String userIdentity, String processKey) {
        List<ProcessInstance> processInstanceList = listProcessInstances(userIdentity, processKey);
        if (processInstanceList != null && processInstanceList.size() > 0) {
            return null;
        } else {
            identityService.setAuthenticatedUserId(String.valueOf(userIdentity));
            return runtimeService.startProcessInstanceByKey(processKey);
        }
    }

    @Override
    public ProcessInstance startOneProcess(String userIdentity, String processKey, Map<String, Object> variables) {
        List<ProcessInstance> processInstanceList = listProcessInstances(userIdentity, processKey);
        if (processInstanceList != null && processInstanceList.size() > 0) {
            return null;
        } else {
            identityService.setAuthenticatedUserId(String.valueOf(userIdentity));
            return runtimeService.startProcessInstanceByKey(processKey, variables);
        }
    }

    @Override
    public ProcessInstance startProcess(String processKey) {
        return runtimeService.startProcessInstanceByKey(processKey);
    }

    @Override
    public ProcessInstance startProcess(String processKey, Map<String, Object> variables) {
        return runtimeService.startProcessInstanceByKey(processKey, variables);
    }

    @Override
    public ProcessInstance startProcess(String userIdentity, String processKey) {
        identityService.setAuthenticatedUserId(String.valueOf(userIdentity));
        return runtimeService.startProcessInstanceByKey(processKey);
    }

    @Override
    public ProcessInstance startProcess(String userIdentity, String processKey, Map<String, Object> variables) {
        identityService.setAuthenticatedUserId(String.valueOf(userIdentity));
        return runtimeService.startProcessInstanceByKey(processKey, variables);
    }

    @Override
    public List<ProcessDefinition> listProcessDefinitions() {
        return repositoryService.createProcessDefinitionQuery().orderByProcessDefinitionVersion().desc().list();
    }

    @Override
    public List<ProcessDefinition> listLatestProcessDefinitions() {
        return repositoryService.createProcessDefinitionQuery().latestVersion().list();
    }

    @Override
    public List<ProcessDefinition> listProcessDefinitions(String processName) {
        return repositoryService.createProcessDefinitionQuery().processDefinitionName(processName)
                .orderByProcessDefinitionVersion().desc().list();
    }

    @Override
    public ProcessDefinition getLatestProcessDefinition(String processName) {
        List<ProcessDefinition> processDefinitionList = listProcessDefinitions(processName);
        return (processDefinitionList != null && processDefinitionList.size() > 0) ? processDefinitionList.get(0) : null;
    }

    @Override
    public List<ProcessInstance> listProcessInstances(String processName) {
        return runtimeService.createProcessInstanceQuery().processDefinitionName(processName).list();
    }

    @Override
    public List<ProcessInstance> listProcessInstances(String userIdentity, String processName) {
        return runtimeService.createProcessInstanceQuery().processDefinitionName(processName).startedBy(userIdentity).list();
    }

    @Override
    public List<Task> listAssigneeTasks(String userIdentity) {
        return taskService.createTaskQuery().taskAssignee(userIdentity).orderByTaskCreateTime().asc().list();
    }

    @Override
    public List<Task> listCandidateUserTasks(String userIdentity) {
        return taskService.createTaskQuery().taskCandidateUser(userIdentity).orderByTaskCreateTime().asc().list();
    }

    @Override
    public List<Task> listCandidateGroupTasks(String roleIdentity) {
        return taskService.createTaskQuery().taskCandidateGroup(roleIdentity).orderByTaskCreateTime().asc().list();
    }

    @Override
    public InputStream getDiagramPNG(String processName) {
        ProcessDefinition processDefinition = getLatestProcessDefinition(processName);
        if (processDefinition != null) {
            return repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), processDefinition.getDiagramResourceName());
        } else {
            return null;
        }
    }

    @Override
    public InputStream generateDiagramPNG(String processInstanceId, String processName) {
        List<Execution> executions = runtimeService.createExecutionQuery().processInstanceId(processInstanceId).list();
        List<String> activityIds = new ArrayList<>();
        for (Execution execution : executions) {
            activityIds.addAll(runtimeService.getActiveActivityIds(execution.getId()));
        }
        if (activityIds.size() > 0) {
            ProcessEngineConfiguration processEngineConfiguration = processEngine.getProcessEngineConfiguration();
            return new DefaultProcessDiagramGenerator().generateDiagram(
                    repositoryService.getBpmnModel(getLatestProcessDefinition(processName).getId()),
                    "png",
                    activityIds,
                    Collections.emptyList(),
                    processEngineConfiguration.getActivityFontName(),
                    processEngineConfiguration.getLabelFontName(),
                    processEngineConfiguration.getAnnotationFontName(),
                    processEngineConfiguration.getClassLoader(),
                    1.0
                    );
        } else {
            return null;
        }
    }

    @Override
    public List<HistoricProcessInstance> listHistoricProcessInstances() {
        return historyService.createHistoricProcessInstanceQuery().list();
    }

    @Override
    public List<HistoricProcessInstance> listHistoricProcessInstances(String processName) {
        return historyService.createHistoricProcessInstanceQuery().processDefinitionName(processName).list();
    }

    @Autowired
    public void setProcessEngine(ProcessEngine processEngine) {
        this.processEngine = processEngine;
    }

    @Autowired
    public void setRepositoryService(RepositoryService repositoryService) {
        this.repositoryService = repositoryService;
    }

    @Autowired
    public void setRuntimeService(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }

    @Autowired
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    @Autowired
    public void setIdentityService(IdentityService identityService) {
        this.identityService = identityService;
    }

    @Autowired
    public void setHistoryService(HistoryService historyService) {
        this.historyService = historyService;
    }
}
