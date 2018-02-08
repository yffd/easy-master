package com.yffd.easy.workflow.model.dto;

import java.util.Date;

/**
 * @Description  流程历史任务信息.
 * @Date		 2017年12月21日 下午4:06:16 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class WfHistoryTaskDTO extends WfBaseDTO {
	
	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * @since JDK 1.7+
	 */
	private static final long serialVersionUID = -6089265665142204037L;
	private String id;					// 主键
	private String executionId;			// 执行ID
	private String instanceId;			// 流程实例ID
	private String definitionId;		// 流程定义ID
	private String activityNodeId;		// 活动节点的ID，即流程图中某个节点的id
	private String activityNodeName;	// 活动节点的名称，即流程图中某个节点的name
	private String assignee;			// 节点的签收人
	private Date claimTime;				// 任务的签收时间
	private Date startTime;				// 任务的开始时间
	private Date endTime;				// 任务的结束时间
	private String startUserId;			// 发起人
	
	private Date searchStartTime;
	private Date searchEndTime;
	
	public WfHistoryTaskDTO() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getExecutionId() {
		return executionId;
	}

	public void setExecutionId(String executionId) {
		this.executionId = executionId;
	}

	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}

	public String getDefinitionId() {
		return definitionId;
	}

	public void setDefinitionId(String definitionId) {
		this.definitionId = definitionId;
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

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public Date getClaimTime() {
		return claimTime;
	}

	public void setClaimTime(Date claimTime) {
		this.claimTime = claimTime;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getStartUserId() {
		return startUserId;
	}

	public void setStartUserId(String startUserId) {
		this.startUserId = startUserId;
	}

	public Date getSearchStartTime() {
		return searchStartTime;
	}

	public void setSearchStartTime(Date searchStartTime) {
		this.searchStartTime = searchStartTime;
	}

	public Date getSearchEndTime() {
		return searchEndTime;
	}

	public void setSearchEndTime(Date searchEndTime) {
		this.searchEndTime = searchEndTime;
	}

}

