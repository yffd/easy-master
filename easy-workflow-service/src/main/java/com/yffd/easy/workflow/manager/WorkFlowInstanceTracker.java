package com.yffd.easy.workflow.manager;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.bpmn.behavior.UserTaskActivityBehavior;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.delegate.ActivityBehavior;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Component;

import com.yffd.easy.workflow.activiti.service.ActivitiBaseService;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年12月22日 下午3:01:08 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Component
public class WorkFlowInstanceTracker extends ActivitiBaseService {
	
	public List<WorkFlowActivityNodeInfo> traceDiagram(String instanceId) {
		// 获取当前执行节点的ID
		Execution execution = this.getRuntimeService().createExecutionQuery().executionId(instanceId).singleResult(); // 执行实例
		String activityId = execution.getActivityId();
		
		ProcessInstance processInstance = this.getRuntimeService().createProcessInstanceQuery().processInstanceId(instanceId).singleResult();
		ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) this.getRepositoryService())
                .getDeployedProcessDefinition(processInstance.getProcessDefinitionId());
		List<ActivityImpl> activitiList = processDefinition.getActivities(); // 获得流程定义中的所有节点
		
		List<WorkFlowActivityNodeInfo> activityNodeInfoList = new ArrayList<WorkFlowActivityNodeInfo>();
		for(ActivityImpl activity : activitiList) {
            String id = activity.getId();
            boolean currentActivity = false;
            if(id.equals(activityId)) {
            	currentActivity = true;
            }
            WorkFlowActivityNodeInfo activityWrapInfo = this.activityNodeWrap(activity, processInstance, currentActivity);
            activityNodeInfoList.add(activityWrapInfo);
        }
		
		return activityNodeInfoList;
	}
	
	/**
	 * 包装活动节点信息，当前节点的X、Y坐标、变量信息、任务类型、任务描述
	 * @Date	2017年12月22日 下午3:55:02 <br/>
	 * @author  zhangST
	 * @param activity
	 * @param processInstance
	 * @param currentActivity
	 * @return
	 */
	public WorkFlowActivityNodeInfo activityNodeWrap(ActivityImpl activity, ProcessInstance processInstance, boolean currentActivity) {
		WorkFlowActivityNodeInfo nodeInfo = new WorkFlowActivityNodeInfo();
		nodeInfo.setIsCurrentActivity(currentActivity);
        this.setPosition(activity, nodeInfo);
        this.setWidthAndHeight(activity, nodeInfo);
        // 节点任务类型：
        // userTask=用户任务、serviceTask=系统任务、startEvent=开始节点、endEvent=结束节点、
        // exclusiveGateway=条件判断节点(系统自动根据条件处理)、inclusiveGateway=并行处理任务、callActivity", "子流程
        String taskType = activity.getProperty("type").toString();
        this.setTaskType(taskType, nodeInfo);
        
        ActivityBehavior activityBehavior = activity.getActivityBehavior();
        if(activityBehavior instanceof UserTaskActivityBehavior) {
        	Task currentTask = null;
        }
        
		return null;
	}
	
	/**
	 * 设置宽度、高度属性
	 * @Date	2017年12月22日 下午4:03:42 <br/>
	 * @author  zhangST
	 * @param activity
	 * @param nodeInfo
	 */
    private void setWidthAndHeight(ActivityImpl activity, WorkFlowActivityNodeInfo nodeInfo) {
    	nodeInfo.setHeight(activity.getHeight());
    	nodeInfo.setWidth(activity.getWidth());
    }

    /**
     * 设置坐标位置
     * @Date	2017年12月22日 下午4:04:21 <br/>
     * @author  zhangST
     * @param activity
     * @param nodeInfo
     */
    private void setPosition(ActivityImpl activity, WorkFlowActivityNodeInfo nodeInfo) {
    	nodeInfo.setPositionX(activity.getX());
    	nodeInfo.setPositionY(activity.getY());
    }
    
    private void setTaskType(String taskType, WorkFlowActivityNodeInfo nodeInfo) {
    	for(WorkFlowTaskEnum type : WorkFlowTaskEnum.values()) {
    		if(type.getValue().equals(taskType)) {
//    			nodeInfo.setTaskType(type);
    			return;
    		}
    	}
//    	nodeInfo.setTaskType(WorkFlowTaskEnum.UNKNOWN_TASK);
    }
}

