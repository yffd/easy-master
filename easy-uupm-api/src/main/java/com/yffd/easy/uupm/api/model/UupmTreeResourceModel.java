package com.yffd.easy.uupm.api.model;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年4月3日 下午2:00:27 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class UupmTreeResourceModel extends UupmTreeNodeModel {

	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * @since JDK 1.7+
	 */
	private static final long serialVersionUID = 392536829366303556L;
	
	private String nodeStatus;		// 节点状态：A=激活、I=冻结
	private String nodeValueType;		// 节点值类型
	
	public String getNodeStatus() {
		return nodeStatus;
	}
	public void setNodeStatus(String nodeStatus) {
		this.nodeStatus = nodeStatus;
	}
	public String getNodeValueType() {
		return nodeValueType;
	}
	public void setNodeValueType(String nodeValueType) {
		this.nodeValueType = nodeValueType;
	}
	
}

