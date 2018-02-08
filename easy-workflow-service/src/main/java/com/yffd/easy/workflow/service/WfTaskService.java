package com.yffd.easy.workflow.service;

import java.util.Map;

import org.activiti.engine.ActivitiObjectNotFoundException;
import org.activiti.engine.ActivitiTaskAlreadyClaimedException;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yffd.easy.common.core.exception.EasyBizException;

/**
 * @Description  流程任务相关.
 * @Date		 2018年1月17日 下午4:10:29 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Service
public class WfTaskService extends WfCommonService {
	
	/**
	 * 启动任务
	 * @Date	2018年1月17日 下午4:41:10 <br/>
	 * @author  zhangST
	 * @param userId
	 * @param wfKey			流程定义Key
	 * @param variables
	 * @return				返回任务ID
	 */
	public String startTask(String userId, String wfKey, Map<String, Object> variables) {
		try {
			// 用来设置启动流程的人员ID，引擎会自动把用户ID保存到activiti:initiator中
			this.getIdentityService().setAuthenticatedUserId(userId);
			ProcessInstance instance = this.getRuntimeService().startProcessInstanceByKey(wfKey, variables);
			return instance.getId();
		} catch (ActivitiObjectNotFoundException e) {
			throw EasyBizException.newInstance("流程定义资源不存在，wfKey=" + wfKey, e);
		}
	}
	
	/**
	 * 签收任务
	 * @Date	2018年1月17日 下午4:34:16 <br/>
	 * @author  zhangST
	 * @param taskId
	 * @param userId
	 * @return			0：签收成功，1：任务不存在，2：任务已被签收
	 */
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

	/**
	 * 完成任务
	 * @Date	2018年1月17日 下午4:35:05 <br/>
	 * @author  zhangST
	 * @param taskId
	 * @param variables
	 * @return				0：成功，1：任务不存在
	 */
	public int completeTask(String taskId, Map<String, Object> variables) {
		try {
			this.getTaskService().complete(taskId, variables);
		} catch (ActivitiObjectNotFoundException e) {
			return 1;
		}
		return 0;
	}

	/**
	 * 签收并完成任务
	 * @Date	2018年1月17日 下午4:35:54 <br/>
	 * @author  zhangST
	 * @param userId
	 * @param taskId
	 * @param variables
	 * @return				0：成功，1：任务不存在，2：任务已被签收
	 */
	@Transactional(rollbackFor=Exception.class)
	public int claimAndCompleteTask(String userId, String taskId, Map<String, Object> variables) {
		try {
			this.getTaskService().claim(taskId, userId);
			this.getTaskService().complete(taskId, variables);
		} catch (ActivitiObjectNotFoundException e) {
			return 1;
		} catch (ActivitiTaskAlreadyClaimedException e) {
			return 2;
		}
		return 0;
	}
	
	/**
	 * 变更签收人
	 * @Date	2018年1月17日 下午4:36:57 <br/>
	 * @author  zhangST
	 * @param taskId
	 * @param userId
	 */
	public void changeAssignee(String taskId, String userId) {
		this.getTaskService().setAssignee(taskId, userId);
	}
}

