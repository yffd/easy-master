package com.yffd.easy.workflow.manager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.activiti.engine.delegate.Expression;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.bpmn.behavior.UserTaskActivityBehavior;
import org.activiti.engine.impl.el.UelExpressionCondition;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmActivity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.delegate.ActivityBehavior;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.ScopeImpl;
import org.activiti.engine.impl.pvm.process.TransitionImpl;
import org.activiti.engine.impl.task.TaskDefinition;
import org.springframework.stereotype.Component;

import com.yffd.easy.workflow.activiti.service.ActivitiBaseService;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年12月22日 下午5:05:08 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Component
public class WorkFlowActivityNodeManager extends ActivitiBaseService {

	public void getNode(String definitionId) {
		ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) this.getRepositoryService())
                .getDeployedProcessDefinition(definitionId);
		// 流程定义相关信息
		String definitionCategory = processDefinition.getCategory();
		String definitionDescription = processDefinition.getDescription();
		System.out.println("definitionCategory:"+definitionCategory);
		System.out.println("definitionDescription:"+definitionDescription);

		List<ActivityImpl> activitiList = processDefinition.getActivities(); // 获得流程定义中的所有节点
		for(ActivityImpl activity : activitiList) {
			String activityId = activity.getId();	// 节点ID，即流程图中某个节点的ID
			int activityHeight = activity.getHeight();
	    	int activityWidth = activity.getWidth();
	    	int activityX = activity.getX();
	    	int activityY = activity.getY();
	    	
	    	System.out.println("activityId:"+activityId);
	    	System.out.println("activityHeight:"+activityHeight);
	    	System.out.println("activityWidth:"+activityWidth);
	    	System.out.println("activityX:"+activityX);
	    	System.out.println("activityY:"+activityY);
	    	
	    	Map<String, Object> properties = activity.getProperties();
	    	// 节点任务类型：
	    	// userTask=用户任务、serviceTask=系统任务、startEvent=开始节点、endEvent=结束节点、
	    	// exclusiveGateway=条件判断节点(系统自动根据条件处理)、inclusiveGateway=并行处理任务、callActivity", "子流程
	    	String activityType = (String) properties.get("type");
	    	String activityName = (String) properties.get("name");	// 节点ID，即流程图中某个节点的name
	        String activityDocumentation = (String) properties.get("documentation");
	        System.out.println("activityType:"+activityType);
	        System.out.println("activityName:"+activityName);
	        System.out.println("activityDocumentation:"+activityDocumentation);
	        
	        ActivityImpl parent = activity.getParentActivity();
	        ScopeImpl parent1 =activity.getParent();
	        System.out.println("parent"+parent);
	        System.out.println("parent1"+parent1);
	        List<PvmTransition> pvmTransitions = activity.getOutgoingTransitions();	// 取出节点的所有出去的线
	        System.out.println("pvmTransitions:====" + pvmTransitions.size());
	        for(PvmTransition pvmTransition : pvmTransitions) {
	        	TransitionImpl transitionImpl = (TransitionImpl) pvmTransition;
	        	Map<String, Object> propertiess = transitionImpl.getProperties();
	        	System.out.println(propertiess);
	        	String outgoingName = (String) propertiess.get("name");
	        	String outgoingConditionText = (String) propertiess.get("conditionText");
	        	UelExpressionCondition aa = (UelExpressionCondition) propertiess.get("condition");
	        	PvmActivity source = pvmTransition.getSource();
	        	PvmActivity destination = pvmTransition.getDestination();
	        	System.out.println("source:"+source.getId());
	        	System.out.println("destination:"+destination.getId());
	        }
	        
	        ActivityBehavior activityBehavior = activity.getActivityBehavior();
	        if(activityBehavior instanceof UserTaskActivityBehavior) {	// 节点任务类型：用户任务节点
	        	UserTaskActivityBehavior userTaskActivityBehavior = (UserTaskActivityBehavior) activityBehavior;
	        	TaskDefinition taskDefinition = userTaskActivityBehavior.getTaskDefinition();
	        	String activityAssignee = null;
	        	Set<String> activityCandidateUsers = null;
	        	Set<String> activityCandidateGroups = null;
	        	Expression assigneeExpression = taskDefinition.getAssigneeExpression();
	    		if(null!=assigneeExpression) {
	    			activityAssignee = assigneeExpression.getExpressionText();
	    		}
	    		Set<Expression> candidateUserIds = taskDefinition.getCandidateUserIdExpressions();
	    		if(null!=candidateUserIds && candidateUserIds.size()>0) {
	    			Set<String> users = new HashSet<String>();
	    			for(Expression expression : candidateUserIds) {
	    				users.add(expression.getExpressionText());
	    			}
	    			activityCandidateUsers = users;
	    		}
	            Set<Expression> candidateGroupIds = taskDefinition.getCandidateGroupIdExpressions();
	            if(null!=candidateGroupIds && candidateGroupIds.size()>0) {
	            	Set<String> groups = new HashSet<String>();
	    			for(Expression expression : candidateGroupIds) {
	    				groups.add(expression.getExpressionText());
	    			}
	    			activityCandidateGroups = groups;
	    		}
	            System.out.println("activityAssignee:"+activityAssignee);
		        System.out.println("activityCandidateUsers:"+activityCandidateUsers);
		        System.out.println("activityCandidateGroups:"+activityCandidateGroups);
	        }
	        System.out.println("========================");
		}
		
	}
	public List<WorkFlowActivityNodeInfo> getAllNode(String definitionId) {
		ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) this.getRepositoryService())
                .getDeployedProcessDefinition(definitionId);
		// 流程定义相关信息
		String definitionCategory = processDefinition.getCategory();
		String definitionDescription = processDefinition.getDescription();
		// 每个节点相关信息
		List<ActivityImpl> activitiList = processDefinition.getActivities(); // 获得流程定义中的所有节点
		List<WorkFlowActivityNodeInfo> activityNodeInfoList = new ArrayList<WorkFlowActivityNodeInfo>();
		for(ActivityImpl activity : activitiList) {
            WorkFlowActivityNodeInfo activityWrapInfo = this.activityNodeWrap(activity, definitionCategory, definitionDescription);
            activityNodeInfoList.add(activityWrapInfo);
        }
		return activityNodeInfoList;
	}
	
	/**
	 * 包装活动节点信息，当前节点的X、Y坐标、变量信息、任务类型、任务描述
	 * @Date	2017年12月22日 下午3:55:02 <br/>
	 * @author  zhangST
	 * @param activity
	 * @return
	 */
	public WorkFlowActivityNodeInfo activityNodeWrap(ActivityImpl activity, String definitionCategory, String definitionDescription) {
		WorkFlowActivityNodeInfo nodeInfo = new WorkFlowActivityNodeInfo();
		nodeInfo.setDefinitionCategory(definitionCategory);			// 流程定义类别
		nodeInfo.setDefinitionDescription(definitionDescription);	// 流程定义描述
        this.setPosition(activity, nodeInfo);
        this.setWidthAndHeight(activity, nodeInfo);
        
        String nodeId = activity.getId();	// 节点ID，即流程图中某个节点的ID
        nodeInfo.setNodeId(nodeId);
        
        Map<String, Object> properties = activity.getProperties();
        // 节点任务类型：
        // userTask=用户任务、serviceTask=系统任务、startEvent=开始节点、endEvent=结束节点、
        // exclusiveGateway=条件判断节点(系统自动根据条件处理)、inclusiveGateway=并行处理任务、callActivity", "子流程
        String nodeTaskType = (String) properties.get("type");
        this.setNodeTaskType(nodeTaskType, nodeInfo);
        
        String nodeName = (String) properties.get("name");	// 节点ID，即流程图中某个节点的name
        nodeInfo.setNodeName(nodeName);
        
        String nodeDocumentation = (String) properties.get("documentation");
        nodeInfo.setNodeDocumentation(nodeDocumentation);	// 节点说明，即流程图中某个节点的documentation
        
        ActivityBehavior activityBehavior = activity.getActivityBehavior();
        if(activityBehavior instanceof UserTaskActivityBehavior) {	// 节点任务类型：用户任务节点
        	UserTaskActivityBehavior userTaskActivityBehavior = (UserTaskActivityBehavior) activityBehavior;
            this.processUserTaskActivityBehavior(userTaskActivityBehavior, nodeInfo);
        }
        
		return nodeInfo;
	}
	
	/**
	 * 用户任务
	 * @Date	2017年12月22日 下午6:25:50 <br/>
	 * @author  zhangST
	 * @param userTaskActivityBehavior
	 * @param nodeInfo
	 */
	private void processUserTaskActivityBehavior(UserTaskActivityBehavior userTaskActivityBehavior, WorkFlowActivityNodeInfo nodeInfo) {
		TaskDefinition taskDefinition = userTaskActivityBehavior.getTaskDefinition();
		System.out.println(taskDefinition.getAssigneeExpression());
		System.out.println(taskDefinition.getCandidateUserIdExpressions());
		System.out.println(taskDefinition.getCandidateGroupIdExpressions());
		Expression assigneeExpression = taskDefinition.getAssigneeExpression();
		if(null!=assigneeExpression) {
			nodeInfo.setAssignee(assigneeExpression.getExpressionText());
		}
		Set<Expression> candidateUserIds = taskDefinition.getCandidateUserIdExpressions();
		if(null!=candidateUserIds && candidateUserIds.size()>0) {
			Set<String> users = new HashSet<String>();
			for(Expression expression : candidateUserIds) {
				users.add(expression.getExpressionText());
			}
			nodeInfo.setCandidateUsers(users);
		}
        Set<Expression> candidateGroupIds = taskDefinition.getCandidateGroupIdExpressions();
        if(null!=candidateGroupIds && candidateGroupIds.size()>0) {
        	Set<String> groups = new HashSet<String>();
			for(Expression expression : candidateGroupIds) {
				groups.add(expression.getExpressionText());
			}
			nodeInfo.setCandidateGroups(groups);
		}
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
    
    private void setNodeTaskType(String nodeTaskType, WorkFlowActivityNodeInfo nodeInfo) {
    	for(WorkFlowTaskEnum type : WorkFlowTaskEnum.values()) {
    		if(type.getValue().equals(nodeTaskType)) {
    			nodeInfo.setNodeTaskType(type);
    			return;
    		}
    	}
    	nodeInfo.setNodeTaskType(WorkFlowTaskEnum.UNKNOWN_TASK);
    }
}

