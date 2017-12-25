package com.yffd.easy.workflow.service;

import java.util.Map;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年12月13日 下午5:33:36 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public interface WorkFlowTaskService {

	/**
	 * 流程任务：签收任务
	 * @Date	2017年12月21日 下午5:21:54 <br/>
	 * @author  zhangST
	 * @param taskId
	 * @param userId
	 * @return		0：签收成功，1：任务不存在，2：任务已被签收
	 */
	int claimTask(String taskId, String userId);
	
	/**
	 * 流程任务：完成任务
	 * @Date	2017年12月21日 下午5:30:21 <br/>
	 * @author  zhangST
	 * @param taskId
	 * @param variables
	 * @return		0：成功，1：任务不存在
	 */
	int completeTask(String taskId, Map<String, Object> variables);
}

