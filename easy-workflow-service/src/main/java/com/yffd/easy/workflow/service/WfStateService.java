package com.yffd.easy.workflow.service;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.ActivitiObjectNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @Description  流程状态相关.
 * @Date		 2018年1月17日 下午4:17:22 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Service
public class WfStateService extends WfCommonService {

	/**
	 * 流程定义：激活【流程定义】
	 * @Date	2018年1月17日 下午4:21:31 <br/>
	 * @author  zhangST
	 * @param definitionId
	 * @return				0：更新成功，1：流程实例不存在或已激活
	 */
	public int activateDefinition(String definitionId) {
		try {
			this.getRepositoryService().activateProcessDefinitionById(definitionId);
			return 0;
		} catch (ActivitiObjectNotFoundException e) {
			return 1;
		}
	}

	/**
	 * 流程定义：挂起【流程定义】，挂起的流程定义，不能创建新的流程实例（会抛出一个异常）.
	 * @Date	2018年1月17日 下午4:22:06 <br/>
	 * @author  zhangST
	 * @param definitionId
	 * @return				0：更新成功，1：流程实例不存在，2：流程实例已挂起
	 */
	public int suspendDefinition(String definitionId) {
		try {
			this.getRepositoryService().suspendProcessDefinitionById(definitionId);
			return 0;
		} catch (ActivitiObjectNotFoundException e) {
			return 1;
		} catch (ActivitiException e) {
			return 2;
		}
	}
	
	/**
	 * 流程实例:激活【流程实例】
	 * @Date	2018年1月17日 下午4:23:37 <br/>
	 * @author  zhangST
	 * @param instanceId
	 * @return				0：成功，1：流程实例不存在，2：流程实例已激活
	 */
	public int activateInstance(String instanceId) {
		try {
			this.getRuntimeService().activateProcessInstanceById(instanceId);
			return 0;
		} catch (ActivitiObjectNotFoundException e) {
			return 1;
		} catch (ActivitiException e) {
			return 2;
		}
	}

	/**
	 * 流程实例:挂起【流程实例】，挂起时，流程不能继续执行（比如，完成任务会抛出异常）， 异步操作（比如定时器）也不会执行.
	 * @Date	2018年1月17日 下午4:24:08 <br/>
	 * @author  zhangST
	 * @param instanceId
	 * @return				0：成功，1：流程实例不存在，2：流程实例已挂起
	 */
	public int suspendInstance(String instanceId) {
		try {
			this.getRuntimeService().suspendProcessInstanceById(instanceId);
			return 0;
		} catch (ActivitiObjectNotFoundException e) {
			return 1;
		} catch (ActivitiException e) {
			return 2;
		}
	}
}

