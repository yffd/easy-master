package com.yffd.easy.common.core.tree;

import java.io.Serializable;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年3月16日 下午6:15:50 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class EasyTreeNode implements Serializable {
	
	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * @since JDK 1.7+
	 */
	private static final long serialVersionUID = 7954142981272094395L;

	private Long nodeId;		// 节点编号
	private Long nodeLeft;		// 节点左编号
	private Long nodeRigh;		// 节点右编号
	private String nodeText;	// 节点文本
	
	public Long getNodeId() {
		return nodeId;
	}
	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}
	public Long getNodeLeft() {
		return nodeLeft;
	}
	public void setNodeLeft(Long nodeLeft) {
		this.nodeLeft = nodeLeft;
	}
	public Long getNodeRigh() {
		return nodeRigh;
	}
	public void setNodeRigh(Long nodeRigh) {
		this.nodeRigh = nodeRigh;
	}
	public String getNodeText() {
		return nodeText;
	}
	public void setNodeText(String nodeText) {
		this.nodeText = nodeText;
	}
	
}

