package com.yffd.easy.workflow.enums;

/**
 * @Description  流程实例状态枚举.
 * @Date		 2018年1月18日 下午6:18:48 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public enum WfInstanceStatusEnum {
	RUNNING("running", "运行中"),
	ACTIVATE("activate", "已激活"),
	SUSPEND("suspend", "已挂起"),
	END("end", "已结束"),
	;
	
	/** 值 */
	private String value;
	/** 描述 */
	private String desc;
	private WfInstanceStatusEnum(String value, String desc) {
		this.value = value;
		this.desc = desc;
	}
	public String getValue() {
		return value;
	}
	public String getDesc() {
		return desc;
	}
	
}

