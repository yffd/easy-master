package com.yffd.easy.workflow.service.impl;

import java.util.Map;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.ActivitiObjectNotFoundException;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yffd.easy.common.core.exception.EasyBizException;
import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;
import com.yffd.easy.workflow.activiti.service.ActivitiBaseService;
import com.yffd.easy.workflow.dao.WorkFlowInstanceDao;
import com.yffd.easy.workflow.model.dto.WorkFlowInstanceDTO;
import com.yffd.easy.workflow.service.WorkFlowInstanceService;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年12月21日 下午3:40:51 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Service("workFlowInstanceService")
public class WorkFlowInstanceServiceImpl extends ActivitiBaseService implements WorkFlowInstanceService {

	@Autowired
	private WorkFlowInstanceDao workFlowInstanceDao;
	
	@Override
	public PageResult<WorkFlowInstanceDTO> findListPage(WorkFlowInstanceDTO model, PageParam pageParam) {
		Map<String, Object> paramMap = this.model2map(model, null);
		return this.workFlowInstanceDao.selectPageBy(paramMap, pageParam);
	}

	@Override
	public WorkFlowInstanceDTO findInstanceByPK(String id) {
		return this.workFlowInstanceDao.selectByPK(id);
	}

	@Override
	public WorkFlowInstanceDTO startInstanceByKey(String userId, String definitionKey, Map<String, Object> variables) {
		try {
			// 用来设置启动流程的人员ID，引擎会自动把用户ID保存到activiti:initiator中
			this.getIdentityService().setAuthenticatedUserId(userId);
			ProcessInstance instance = this.getRuntimeService().startProcessInstanceByKey(definitionKey, variables);
			return this.findInstanceByPK(instance.getId());
		} catch (ActivitiObjectNotFoundException e) {
			throw EasyBizException.newInstance("流程定义资源不存在，definitionKey=" + definitionKey, e);
		}
	}

	@Override
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

	@Override
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

