package com.yffd.easy.workflow.model.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * @Description  流程活动图节点信息.
 * @Date		 2017年12月21日 下午4:06:16 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class WfActivityNodeDTO implements Serializable {

	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * @since JDK 1.7+
	 */
	private static final long serialVersionUID = 6811314487595908593L;
	
	private String nodeId;
	private String nodeName;
	private String nodeType;
	private int width;
	private int height;
	private int x;
	private int y;
	private boolean isComplete = false;
	private boolean isFlowNode = false;
	
	private String flowCondition;
	private List<Integer> flowWaypoints;
	
	private String assignee;
	private Set<String> candidateUserIds;
	private Set<String> candidateGroupIds;
	public String getNodeId() {
		return nodeId;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	public String getNodeType() {
		return nodeType;
	}
	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
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
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public boolean isComplete() {
		return isComplete;
	}
	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}
	public boolean isFlowNode() {
		return isFlowNode;
	}
	public void setFlowNode(boolean isFlowNode) {
		this.isFlowNode = isFlowNode;
	}
	public String getFlowCondition() {
		return flowCondition;
	}
	public void setFlowCondition(String flowCondition) {
		this.flowCondition = flowCondition;
	}
	public List<Integer> getFlowWaypoints() {
		return flowWaypoints;
	}
	public void setFlowWaypoints(List<Integer> flowWaypoints) {
		this.flowWaypoints = flowWaypoints;
	}
	public String getAssignee() {
		return assignee;
	}
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	public Set<String> getCandidateUserIds() {
		return candidateUserIds;
	}
	public void setCandidateUserIds(Set<String> candidateUserIds) {
		this.candidateUserIds = candidateUserIds;
	}
	public Set<String> getCandidateGroupIds() {
		return candidateGroupIds;
	}
	public void setCandidateGroupIds(Set<String> candidateGroupIds) {
		this.candidateGroupIds = candidateGroupIds;
	}
	
}

