package com.yffd.easy.workflow.manager;

import java.io.Serializable;
import java.util.Set;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年12月22日 下午4:06:17 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class WorkFlowActivityNodeInfo implements Serializable {
	
	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * @since JDK 1.7+
	 */
	private static final long serialVersionUID = -6415080080271952824L;

	private Boolean isCurrentActivity = null;	// 是否为当前执行活动节点
	private int width;
	private int height;
	private int positionX;
	private int positionY;
	private String nodeId;				// 节点ID，即流程图中的某个活动节点的ID
	private String nodeName;			// 节点名称，即流程图中的某个活动节点的name
	private String nodeDocumentation;	// 节点说明，即流程图中的某个活动节点的documentation
	private WorkFlowTaskEnum nodeTaskType;		// 节点任务类型
	
	private String assignee;					// 用户任务类型的属性：处理人，即流程图中的某个活动节点的 assignee
	private Set<String> candidateUsers;			// 用户任务类型的属性：候选人（多个），即流程图中的某个活动节点的 candidate users
	private Set<String> candidateGroups;			// 用户任务类型的属性：候选组（多个），即流程图中的某个活动节点的 candidate groups
	
	private String definitionCategory;			// 流程定义类别
	private String definitionDescription;		// 流程定义描述
	
	
	
	
	public String getDefinitionCategory() {
		return definitionCategory;
	}
	public void setDefinitionCategory(String definitionCategory) {
		this.definitionCategory = definitionCategory;
	}
	public Boolean getIsCurrentActivity() {
		return isCurrentActivity;
	}
	public void setIsCurrentActivity(Boolean isCurrentActivity) {
		this.isCurrentActivity = isCurrentActivity;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getPositionX() {
		return positionX;
	}
	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}
	public int getPositionY() {
		return positionY;
	}
	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	public WorkFlowTaskEnum getNodeTaskType() {
		return nodeTaskType;
	}
	public void setNodeTaskType(WorkFlowTaskEnum nodeTaskType) {
		this.nodeTaskType = nodeTaskType;
	}
	public String getNodeDocumentation() {
		return nodeDocumentation;
	}
	public void setNodeDocumentation(String nodeDocumentation) {
		this.nodeDocumentation = nodeDocumentation;
	}
	public String getDefinitionDescription() {
		return definitionDescription;
	}
	public void setDefinitionDescription(String definitionDescription) {
		this.definitionDescription = definitionDescription;
	}
	public String getNodeId() {
		return nodeId;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	public String getAssignee() {
		return assignee;
	}
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	public Set<String> getCandidateUsers() {
		return candidateUsers;
	}
	public void setCandidateUsers(Set<String> candidateUsers) {
		this.candidateUsers = candidateUsers;
	}
	public Set<String> getCandidateGroups() {
		return candidateGroups;
	}
	public void setCandidateGroups(Set<String> candidateGroups) {
		this.candidateGroups = candidateGroups;
	}
	
}

