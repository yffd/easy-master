package com.yffd.easy.workflow.app.service;

import java.util.Map;

import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;
import com.yffd.easy.workflow.app.model.OaLeave;
import com.yffd.easy.workflow.model.dto.WorkFlowInstanceDTO;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年12月19日 上午10:33:40 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public interface OaLeaveService {

	PageResult<OaLeave> findPage(Map<String, Object> paramMap, PageParam pageParam);
	
	void add(OaLeave model);
	
	void update(OaLeave model);
	
	void delete(String id);
	
	void apply(OaLeave model);
	
	WorkFlowInstanceDTO startTask(String userId, String definitionKey, Map<String, Object> variables);
	
	void completeTask(String taskId, Map<String, Object> variables);
	
	void claimTask(String userId, String taskId);
	
	void claimAndCompleteTask(String userId, String taskId, Map<String, Object> variables);
}

