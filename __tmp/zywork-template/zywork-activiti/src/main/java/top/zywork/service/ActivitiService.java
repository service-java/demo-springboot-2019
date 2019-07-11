package top.zywork.service;

import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * Activiti服务接口<br />
 * 创建于2017-10-17
 *
 * @author 王振宇
 * @version 1.0
 */
public interface ActivitiService {

    /**
     * 部署指定流程
     * @param processName 流程名
     * @return 流程定义对象
     */
    Deployment deploy(String processName);

    /**
     * 根据流程名删除流程部署
     * @param processName 流程名
     */
    void remove(String processName);

    /**
     * 启动流程，并且只能启动一个，如果已经有启动的流程，则不能再次启动流程
     *
     * @param processKey 流程key
     * @return 流程实例
     */
    ProcessInstance startOneProcess(String processKey);

    /**
     * 启动流程，并且只能启动一个，如果已经有启动的流程，则不能再次启动流程
     * @param processKey 流程key
     * @param variables 流程变量
     * @return 流程实例
     */
    ProcessInstance startOneProcess(String processKey, Map<String, Object> variables);

    /**
     * 用户启动流程，并且只能启动一个，如果已经有启动的流程，则不能再次启动流程
     * @param userIdentity 用户标识
     * @param processKey 流程key
     * @return 流程实例
     */
    ProcessInstance startOneProcess(String userIdentity, String processKey);

    /**
     * 用户启动流程，可附加流程变量，并且只能启动一个，如果已经有启动的流程，则不能再次启动流程
     * @param userIdentity 用户标识
     * @param processKey 流程key
     * @param variables 流程变量
     * @return 流程实例
     */
    ProcessInstance startOneProcess(String userIdentity, String processKey, Map<String, Object> variables);

    /**
     * 启动流程
     *
     * @param processKey 流程key
     * @return 流程实例
     */
    ProcessInstance startProcess(String processKey);

    /**
     * 启动流程
     * @param processKey 流程key
     * @param variables 流程变量
     * @return 流程实例
     */
    ProcessInstance startProcess(String processKey, Map<String, Object> variables);

    /**
     * 用户启动流程
     * @param userIdentity 用户标识
     * @param processKey 流程key
     * @return 流程实例
     */
    ProcessInstance startProcess(String userIdentity, String processKey);

    /**
     * 用户启动流程，可附加流程变量
     * @param userIdentity 用户标识
     * @param processKey 流程key
     * @param variables 流程变量
     * @return 流程实例
     */
    ProcessInstance startProcess(String userIdentity, String processKey, Map<String, Object> variables);

    /**
     * 列出所有流程定义，按照版本降序排列
     * @return 所有流程定义对象列表
     */
    List<ProcessDefinition> listProcessDefinitions();

    /**
     * 列出所有流程定义的最新版本
     * @return 所有流程定义对象列表
     */
    List<ProcessDefinition> listLatestProcessDefinitions();

    /**
     * 列出指定流程的流程定义，按照版本降序排列
     * @param processName 流程名
     * @return 指定流程的流程定义对象列表
     */
    List<ProcessDefinition> listProcessDefinitions(String processName);

    /**
     * 获取指定流程名的最新版本的流程定义
     * @param processName 流程名
     * @return 指定流程名的最新版本的流程定义对象
     */
    ProcessDefinition getLatestProcessDefinition(String processName);

    /**
     * 根据流程名称获取已经启动的流程实例
     * @param processName 流程名称
     * @return 流程实例列表
     */
    List<ProcessInstance> listProcessInstances(String processName);

    /**
     * 根据用户标识和流程名称获取已经启动的流程实例
     * @param userIdentity 用户标识
     * @param processName 流程名称
     * @return 流程实例列表
     */
    List<ProcessInstance> listProcessInstances(String userIdentity, String processName);

    /**
     * 根据指派的用户对象列出所有任务
     * @param userIdentity 用户标识
     * @return 指定用户的所有任务组成的列表
     */
    List<Task> listAssigneeTasks(String userIdentity);

    /**
     * 根据候选人列出所有任务
     * @param userIdentity 用户标识
     * @return 指定候选人的所有任务
     */
    List<Task> listCandidateUserTasks(String userIdentity);

    /**
     * 根据候选角色列出所有任务
     * @param roleIdentity 角色或组标识
     * @return 指定候选角色或组的的所有任务
     */
    List<Task> listCandidateGroupTasks(String roleIdentity);

    /**
     * 根据流程名获取流程定义对应的png图片
     * @param processName 流程名
     * @return 原始流程图片
     */
    InputStream getDiagramPNG(String processName);

    /**
     * 根据流程实例编号和流程名称生成流程图片
     * @param processInstanceId 流程实例编号
     * @param processName 流程名
     * @return 标识出当前任务节点的流程图
     */
    InputStream generateDiagramPNG(String processInstanceId, String processName);

    /**
     * 查看所有历史流程实例
     * @return 所有历史流程实例
     */
    List<HistoricProcessInstance> listHistoricProcessInstances();

    /**
     * 查看指定流程名的所有历史流程实例
     * @param processName 流程名
     * @return 所有历史流程实例
     */
    List<HistoricProcessInstance> listHistoricProcessInstances(String processName);

}
