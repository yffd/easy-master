package com.yffd.easy.workflow.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipInputStream;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.ActivitiObjectNotFoundException;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.impl.bpmn.behavior.UserTaskActivityBehavior;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.delegate.ActivityBehavior;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.TransitionImpl;
import org.activiti.engine.impl.task.TaskDefinition;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yffd.easy.common.core.exception.EasyBizException;
import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;
import com.yffd.easy.workflow.activiti.service.ActivitiBaseService;
import com.yffd.easy.workflow.dao.WorkFlowDefinitionDao;
import com.yffd.easy.workflow.model.dto.WorkFlowActivityNodeDTO;
import com.yffd.easy.workflow.model.dto.WorkFlowDefinitionDTO;
import com.yffd.easy.workflow.service.WorkFlowDefinitionService;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年12月14日 下午3:25:06 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Service("workFlowDefinitionService")
public class WorkFlowDefinitionServiceImpl extends ActivitiBaseService implements WorkFlowDefinitionService {
	@Autowired
	private WorkFlowDefinitionDao processDefinitionDao;

	@Override
	public PageResult<WorkFlowDefinitionDTO> findListPage(WorkFlowDefinitionDTO model, PageParam pageParam) {
		Map<String, Object> paramMap = this.model2map(model, null);
		PageResult<WorkFlowDefinitionDTO> pageResult = this.processDefinitionDao.selectPageBy(paramMap, pageParam);
		return pageResult;
	}

	@Override
	public WorkFlowDefinitionDTO findDefinitionByPK(String id) {
		return this.processDefinitionDao.selectByPK(id);
	}

	@Override
	public InputStream loadResource(String deploymentId, String resourceName) {
		InputStream inputStream = null;
		try {
			inputStream = this.getRepositoryService().getResourceAsStream(deploymentId, resourceName);
		} catch (ActivitiObjectNotFoundException e) {
			throw EasyBizException.newInstance("流程定义资源不存在或未发布，deploymentId=" + deploymentId + ", resourceName=" + resourceName, e);
		}
		return inputStream;
	}

	@Override
	public int activateDefinition(String definitionId) {
		try {
			this.getRepositoryService().activateProcessDefinitionById(definitionId);
			return 0;
		} catch (ActivitiObjectNotFoundException e) {
			return 1;
		}
	}

	@Override
	public int suspendDefinition(String definitionId) {
		try {
			this.getRepositoryService().suspendProcessDefinitionById(definitionId);
			return 0;
		} catch (ActivitiObjectNotFoundException e) {
			return 1;
		} catch (ActivitiException e) {
			return 2;
		}
	}

	@Override
	public String deployDefinitionByClasspath(String bpmnClasspath, String pngClasspath) {
		if(null==bpmnClasspath) {
			throw EasyBizException.newInstance("参数为空错误：【bpmnClasspath is null】");
		}
		DeploymentBuilder deploymentBuilder = this.getRepositoryService().createDeployment();
		deploymentBuilder.addClasspathResource(bpmnClasspath);
		if(null!=pngClasspath) {
			deploymentBuilder.addClasspathResource(pngClasspath);
		}
		Deployment deployment = deploymentBuilder.deploy();
		return deployment.getId();
	}

	@Override
	public String deployDefinitionByInputStream(String resourceName, InputStream inputStream) {
		if(null==resourceName || "".equals(resourceName) || null==inputStream) {
			throw EasyBizException.newInstance("参数为空错误：【resourceName is null, inputStream is null】");
		}
		DeploymentBuilder deploymentBuilder = this.getRepositoryService().createDeployment();
		deploymentBuilder.addInputStream(resourceName, inputStream);
		Deployment deployment = deploymentBuilder.deploy();
		return deployment.getId();
	}

	@Override
	public String deployDefinitionByZipInputStream(ZipInputStream ziInputStream) {
		if(null==ziInputStream) {
			throw EasyBizException.newInstance("参数为空错误：【ziInputStream is null】");
		}
		DeploymentBuilder deploymentBuilder = this.getRepositoryService().createDeployment();
		deploymentBuilder.addZipInputStream(ziInputStream);
		Deployment deployment = deploymentBuilder.deploy();
		return deployment.getId();
	}

	@Override
	public List<WorkFlowActivityNodeDTO> getActivityNode(String definitionId) {
		List<WorkFlowActivityNodeDTO> nodeList = new ArrayList<WorkFlowActivityNodeDTO>();
		
		ProcessDefinitionEntity definitionEntity = (ProcessDefinitionEntity)this.getRepositoryService()
        		.getProcessDefinition(definitionId);
		List<ActivityImpl> activityImpls = definitionEntity.getActivities();
		for(ActivityImpl activityImpl : activityImpls) {
			String id = activityImpl.getId();
			int height = activityImpl.getHeight();
			int width = activityImpl.getWidth();
			int x = activityImpl.getX();
			int y = activityImpl.getY();
			
			Map<String, Object> properties = activityImpl.getProperties();
			String type = (String) properties.get("type");
			String name = (String) properties.get("name");
			
			String assignee = null;
			Set<String> candidateUserIds = new HashSet<String>();
			Set<String> candidateGroupIds = new HashSet<String>();
	        ActivityBehavior activityBehavior = activityImpl.getActivityBehavior();
	        if(activityBehavior instanceof UserTaskActivityBehavior) {
	        	UserTaskActivityBehavior userTaskActivityBehavior = (UserTaskActivityBehavior) activityBehavior;
	        	TaskDefinition taskDefinition = userTaskActivityBehavior.getTaskDefinition();
	        	Expression assigneeExpression = taskDefinition.getAssigneeExpression();
	        	assignee = null==assigneeExpression?null:assigneeExpression.getExpressionText();
	        	Set<Expression> candidateUserIdExpressions = taskDefinition.getCandidateUserIdExpressions();
	        	for(Expression expression : candidateUserIdExpressions) {
	        		candidateUserIds.add(expression.getExpressionText());
	        	}
	        	Set<Expression> candidateGroupIdExpressions = taskDefinition.getCandidateGroupIdExpressions();
	        	for(Expression expression : candidateGroupIdExpressions) {
	        		candidateGroupIds.add(expression.getExpressionText());
	        	}
	        }
	        
	        WorkFlowActivityNodeDTO activityNode = new WorkFlowActivityNodeDTO();
	        activityNode.setNodeId(id);
	        activityNode.setNodeName(name);
	        activityNode.setNodeType(type);
	        activityNode.setWidth(width);
	        activityNode.setHeight(height);
	        activityNode.setX(x);
	        activityNode.setY(y);
	        activityNode.setAssignee(assignee);
	        activityNode.setCandidateUserIds(candidateUserIds);
	        activityNode.setCandidateGroupIds(candidateGroupIds);
	        
	        nodeList.add(activityNode);
	        
	        List<PvmTransition> pvmTransitions = activityImpl.getOutgoingTransitions();	// 取出节点的所有出去的线
	        for(PvmTransition pvmTransition : pvmTransitions) {
	            String flowId = pvmTransition.getId();
	            String flowName = (String) pvmTransition.getProperty("name");
				String conditionText = (String) pvmTransition.getProperty("conditionText");
				
				WorkFlowActivityNodeDTO flowNode = new WorkFlowActivityNodeDTO();
				flowNode.setNodeId(flowId);
				flowNode.setNodeName(flowName);
				flowNode.setFlowCondition(conditionText);
				flowNode.setFlowNode(true);
		        
				TransitionImpl transitionImpl = (TransitionImpl) pvmTransition;
				List<Integer> waypoints = transitionImpl.getWaypoints();
				flowNode.setFlowWaypoints(waypoints);
				
		        nodeList.add(flowNode);
	        }
	        
		}
		return nodeList;
	}

}

