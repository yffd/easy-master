package com.yffd.easy.workflow.enums;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年1月3日 上午9:54:00 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public enum WorkFlowNodeTypeEnum {
	USER_TASK("userTask", "用户任务"),
	SERVICE_TASK("serviceTask", "系统任务"),
	START_EVENT("startEvent", "开始节点"),
	END_EVENT("endEvent", "结束节点"),
	EXCLUSIVE_GATEWAY("exclusiveGateway", "条件判断节点(系统自动根据条件处理)"),
	INCLUSIVE_GATEWAY("inclusiveGateway", "并行处理任务"),
	CALL_ACTIVITY("callActivity", "子流程");

	/** 值 */
	private String value;
	/** 描述 */
	private String desc;
	
	private WorkFlowNodeTypeEnum(String value, String desc) {
		this.value = value;
		this.desc = desc;
	}
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}

