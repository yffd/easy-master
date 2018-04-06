package com.yffd.easy.uupm.web.vo;

import com.yffd.easy.framework.web.view.vo.ComboTreeVO;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年3月9日 上午11:46:18 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class UupmResourceComboTreeVO extends ComboTreeVO {
	private String tenantCode;		//租户编号
	private String nodeLabel;		// 节点标签，目前主要用于多棵树的区分
	private Integer nodeLayer;		// 节点层号
	private Integer nodeLeft;		// 节点左编号，主要用于偏序遍历子节点
	private Integer nodeRight;		// 节点右编号，主要用于偏序遍历子节点
	private String nodeCode;		// 节点编号
	private String nodeName;		// 节点名称
	private String parentNodeCode;	// 父节点编号
	private String parentNodeName;	// 父节点名称
	private String nodeValue;		// 节点值
	private String nodeValueType;	// 节点值类型
	private String nodeStatus;		// 节点状态：A=激活、I=冻结
	private Integer seqNo;			// 序号
	private String remark;			// 备注
	
	public String getTenantCode() {
		return tenantCode;
	}
	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}
	public String getNodeLabel() {
		return nodeLabel;
	}
	public void setNodeLabel(String nodeLabel) {
		this.nodeLabel = nodeLabel;
	}
	public Integer getNodeLayer() {
		return nodeLayer;
	}
	public void setNodeLayer(Integer nodeLayer) {
		this.nodeLayer = nodeLayer;
	}
	public Integer getNodeLeft() {
		return nodeLeft;
	}
	public void setNodeLeft(Integer nodeLeft) {
		this.nodeLeft = nodeLeft;
	}
	public Integer getNodeRight() {
		return nodeRight;
	}
	public void setNodeRight(Integer nodeRight) {
		this.nodeRight = nodeRight;
	}
	public String getNodeCode() {
		return nodeCode;
	}
	public void setNodeCode(String nodeCode) {
		this.nodeCode = nodeCode;
	}
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	public String getParentNodeCode() {
		return parentNodeCode;
	}
	public void setParentNodeCode(String parentNodeCode) {
		this.parentNodeCode = parentNodeCode;
	}
	public String getParentNodeName() {
		return parentNodeName;
	}
	public void setParentNodeName(String parentNodeName) {
		this.parentNodeName = parentNodeName;
	}
	public String getNodeValue() {
		return nodeValue;
	}
	public void setNodeValue(String nodeValue) {
		this.nodeValue = nodeValue;
	}
	public String getNodeValueType() {
		return nodeValueType;
	}
	public void setNodeValueType(String nodeValueType) {
		this.nodeValueType = nodeValueType;
	}
	public String getNodeStatus() {
		return nodeStatus;
	}
	public void setNodeStatus(String nodeStatus) {
		this.nodeStatus = nodeStatus;
	}
	public Integer getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}

