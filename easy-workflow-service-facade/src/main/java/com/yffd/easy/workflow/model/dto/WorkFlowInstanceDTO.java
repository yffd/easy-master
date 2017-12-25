package com.yffd.easy.workflow.model.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年12月21日 下午4:06:16 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class WorkFlowInstanceDTO implements Serializable {

	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * @since JDK 1.7+
	 */
	private static final long serialVersionUID = -3997145533921174200L;
	
	
	private String id;					// 主键
	private String instanceId;			// 流程实例ID
	private String activityNodeId;		// 当前活动节点的ID，即流程图中某个几点的id
	private String instanceState;		// 流程实例状态，1：激活，2：挂起
	private String definitionId;		// 流程定义ID
	private String definitionKey;		// 流程定义关键字，即流程图中的id
	private String definitionName;		// 流程定义名称，即流程图中的name
	private String definitionCategory;	// 流程定义类别，即流程图中的namespace
	private String definitionVersion;	// 流程定义版本
	private String deployId;			// 流程发布ID
	private Map<String, Object> variables;	// 流程实例作用域范围的参数变量
	
	public WorkFlowInstanceDTO() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}

	public String getActivityNodeId() {
		return activityNodeId;
	}

	public void setActivityNodeId(String activityNodeId) {
		this.activityNodeId = activityNodeId;
	}

	public String getInstanceState() {
		return instanceState;
	}

	public void setInstanceState(String instanceState) {
		this.instanceState = instanceState;
	}

	public String getDefinitionId() {
		return definitionId;
	}

	public void setDefinitionId(String definitionId) {
		this.definitionId = definitionId;
	}

	public String getDefinitionKey() {
		return definitionKey;
	}

	public void setDefinitionKey(String definitionKey) {
		this.definitionKey = definitionKey;
	}

	public String getDefinitionName() {
		return definitionName;
	}

	public void setDefinitionName(String definitionName) {
		this.definitionName = definitionName;
	}

	public String getDefinitionCategory() {
		return definitionCategory;
	}

	public void setDefinitionCategory(String definitionCategory) {
		this.definitionCategory = definitionCategory;
	}

	public String getDefinitionVersion() {
		return definitionVersion;
	}

	public void setDefinitionVersion(String definitionVersion) {
		this.definitionVersion = definitionVersion;
	}

	public String getDeployId() {
		return deployId;
	}

	public void setDeployId(String deployId) {
		this.deployId = deployId;
	}

	public Map<String, Object> getVariables() {
		return variables;
	}

	public void setVariables(Map<String, Object> variables) {
		this.variables = variables;
	}
}

