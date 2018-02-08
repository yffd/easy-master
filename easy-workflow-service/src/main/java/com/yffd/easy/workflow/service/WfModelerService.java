package com.yffd.easy.workflow.service;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.engine.ActivitiObjectNotFoundException;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.impl.bpmn.behavior.UserTaskActivityBehavior;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.delegate.ActivityBehavior;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.TransitionImpl;
import org.activiti.engine.impl.task.TaskDefinition;
import org.activiti.engine.repository.Model;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.yffd.easy.common.core.exception.EasyBizException;
import com.yffd.easy.workflow.model.dto.WfActivityNodeDTO;
import com.yffd.easy.workflow.model.dto.WfBaseDTO;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年1月16日 下午6:00:17 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Service
public class WfModelerService extends WfCommonService {

	/**
	 * 加载流程资源
	 * @Date	2018年1月17日 下午3:54:56 <br/>
	 * @author  zhangST
	 * @param deployId
	 * @param resourceName
	 * @return
	 */
	public InputStream loadResource(String deployId, String resourceName) {
		InputStream inputStream = null;
		try {
			inputStream = this.getRepositoryService().getResourceAsStream(deployId, resourceName);
		} catch (ActivitiObjectNotFoundException e) {
			throw EasyBizException.newInstance("流程定义资源不存在或未发布，deployId=" + deployId + ", resourceName=" + resourceName, e);
		}
		return inputStream;
	}
	
	/**
	 * 获取所有流程节点
	 * @Date	2018年1月17日 下午4:09:16 <br/>
	 * @author  zhangST
	 * @param definitionId
	 * @return
	 */
	public List<WfActivityNodeDTO> getActivityNode(String definitionId) {
		List<WfActivityNodeDTO> nodeList = new ArrayList<WfActivityNodeDTO>();
		
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
	        
	        WfActivityNodeDTO activityNode = new WfActivityNodeDTO();
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
				
				WfActivityNodeDTO flowNode = new WfActivityNodeDTO();
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
	
	/**
	 * 添加工作流模型
	 * @Date	2018年1月16日 下午6:24:27 <br/>
	 * @author  zhangST
	 * @param dto
	 * @return	返回主键ID
	 */
	public String addOne(WfBaseDTO dto) {
		if(null==dto) {
			throw EasyBizException.newInstance("参数为空");
		}
		String key = StringUtils.defaultString(dto.getWfKey());
		String name = StringUtils.defaultString(dto.getWfName());
		String desc = StringUtils.defaultString(dto.getWfDesc());
		String category = StringUtils.defaultString(dto.getWfCategory());
		
		Model modelData = this.getRepositoryService().newModel();
		modelData.setKey(key);
		modelData.setName(name);
		modelData.setCategory(category);
		
		ObjectMapper objectMapper = new ObjectMapper();
		ObjectNode modelObjectNode = objectMapper.createObjectNode();
		modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, name);
        modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
        modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, desc);
		modelData.setMetaInfo(modelObjectNode.toString());
		
		this.getRepositoryService().saveModel(modelData);	// 添加：act_re_model表
		
		ObjectMapper editorObjectMapper = new ObjectMapper();
        ObjectNode editorNode = editorObjectMapper.createObjectNode();
        editorNode.put("id", "canvas");
        editorNode.put("resourceId", "canvas");
        ObjectNode stencilSetNode = editorObjectMapper.createObjectNode();
        stencilSetNode.put("namespace", category);
        editorNode.set("stencilset", stencilSetNode);
		
        if(null==modelData || "".equals(modelData)) {
        	throw EasyBizException.newInstance("modelId为空");
        }
		try {
			byte[] bytes = editorNode.toString().getBytes("utf-8");
			this.getRepositoryService().addModelEditorSource(modelData.getId(), bytes);	// 添加：act_ge_bytearray表， 更新：act_re_model表
		} catch (UnsupportedEncodingException e) {
			throw EasyBizException.newInstance("添加工作流模型错误", e);
		}
		
		return modelData.getId();
	}
}

