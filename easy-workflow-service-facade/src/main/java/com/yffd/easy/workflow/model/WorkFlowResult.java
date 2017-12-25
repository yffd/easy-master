package com.yffd.easy.workflow.model;

import java.io.Serializable;

import com.yffd.easy.common.core.model.DataTransferModel;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年12月19日 下午2:12:32 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class WorkFlowResult extends DataTransferModel implements Serializable {
	
	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * @since JDK 1.7+
	 */
	private static final long serialVersionUID = 1225545539985630616L;
	
	private String processInstanceId;

	public String getProcessInstanceId() {
		return processInstanceId;
	}
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	
}

