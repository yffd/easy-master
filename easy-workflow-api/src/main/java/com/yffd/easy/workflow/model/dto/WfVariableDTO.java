package com.yffd.easy.workflow.model.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * @Description  流程变量信息.
 * @Date		 2017年12月21日 下午4:06:16 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class WfVariableDTO implements Serializable {
	
	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * @since JDK 1.7+
	 */
	private static final long serialVersionUID = -4176199803657439577L;

	private String variableName;		// 变量名称
	private Object value;				// 变量值
	private String variableTypeName;	// 变量类型
	private Date createTime;			// 变量创建时间
	private Date lastUpdatedTime;		// 变量最后修改时间
	private String instanceId;			// 流程实例ID
	private String taskId;				// 任务ID
	private String activityNodeId;		// 当前活动节点的ID，即流程图中某个节点的id
	private String activityNodeName;	// 当前活动节点的名称，即流程图中某个节点的name
	private boolean isLocal;			// 参数作用域范围：true：节点范围、false：实例范围
	
	public String getVariableName() {
		return variableName;
	}
	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public String getVariableTypeName() {
		return variableTypeName;
	}
	public void setVariableTypeName(String variableTypeName) {
		this.variableTypeName = variableTypeName;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getLastUpdatedTime() {
		return lastUpdatedTime;
	}
	public void setLastUpdatedTime(Date lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}
	public String getInstanceId() {
		return instanceId;
	}
	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
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
	public boolean isLocal() {
		return isLocal;
	}
	public void setLocal(boolean isLocal) {
		this.isLocal = isLocal;
	}
	
	
	
	private Map<String, Object> params;			// 流程实例作用域参数
	private Map<String, Object> localParams;	// 任务节点作用域参数
	
	public Map<String, Object> getParams() {
		return params;
	}
	public void setParams(Map<String, Object> params) {
		this.params = params;
	}
	public Map<String, Object> getLocalParams() {
		return localParams;
	}
	public void setLocalParams(Map<String, Object> localParams) {
		this.localParams = localParams;
	}
	
}

