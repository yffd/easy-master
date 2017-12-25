package com.yffd.easy.workflow.service.impl;

import java.util.Map;

import org.activiti.engine.ActivitiObjectNotFoundException;
import org.activiti.engine.ActivitiTaskAlreadyClaimedException;
import org.springframework.stereotype.Service;

import com.yffd.easy.workflow.activiti.service.ActivitiBaseService;
import com.yffd.easy.workflow.service.WorkFlowTaskService;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年12月19日 上午11:35:09 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Service("workFlowTaskService")
public class WorkFlowTaskServiceImpl extends ActivitiBaseService implements WorkFlowTaskService {

	@Override
	public int claimTask(String taskId, String userId) {
		try {
			this.getTaskService().claim(taskId, userId);
		} catch (ActivitiObjectNotFoundException e) {
			return 1;
		} catch (ActivitiTaskAlreadyClaimedException e) {
			return 2;
		}
		return 0;
	}

	@Override
	public int completeTask(String taskId, Map<String, Object> variables) {
		try {
			this.getTaskService().complete(taskId, variables);
		} catch (ActivitiObjectNotFoundException e) {
			return 1;
		}
		return 0;
	}
	

}

