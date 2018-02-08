package com.yffd.easy.workflow.model.dto;

/**
 * @Description  流程实例信息.
 * @Date		 2017年12月21日 下午4:06:16 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class WfInstanceDTO extends WfBaseDTO {

	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * @since JDK 1.7+
	 */
	private static final long serialVersionUID = -3997145533921174200L;
	
	
	private String id;					// 主键
	private String instanceId;			// 流程实例ID
	private String activityNodeId;		// 当前活动节点的ID，即流程图中某个节点的id
	private String activityNodeName;	// 当前活动节点的名称，即流程图中某个节点的name
	private String instanceState;		// 流程实例状态，1：激活，2：挂起
	private String definitionId;		// 流程定义ID
	private String startUserId;			// 发起人
	
	public WfInstanceDTO() {
		
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

	public String getActivityNodeName() {
		return activityNodeName;
	}

	public void setActivityNodeName(String activityNodeName) {
		this.activityNodeName = activityNodeName;
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

	public String getStartUserId() {
		return startUserId;
	}

	public void setStartUserId(String startUserId) {
		this.startUserId = startUserId;
	}

}

