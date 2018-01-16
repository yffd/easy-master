package com.yffd.easy.workflow.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.activiti.engine.history.HistoricIdentityLink;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.task.IdentityLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;
import com.yffd.easy.workflow.activiti.service.ActivitiBaseService;
import com.yffd.easy.workflow.dao.WorkFlowHistoryTaskDao;
import com.yffd.easy.workflow.model.dto.WorkFlowHistoryTaskDTO;
import com.yffd.easy.workflow.model.dto.WorkFlowTaskCandidatorDTO;
import com.yffd.easy.workflow.model.dto.WorkFlowVariableDTO;
import com.yffd.easy.workflow.service.WorkFlowHistoryTaskService;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年12月19日 上午11:35:09 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Service("workFlowHistoryTaskService")
public class WorkFlowHistoryTaskServiceImpl extends ActivitiBaseService implements WorkFlowHistoryTaskService {

	@Autowired
	private WorkFlowHistoryTaskDao workFlowHitoryTaslDao;
	
	@Override
	public PageResult<WorkFlowHistoryTaskDTO> findListPage(WorkFlowHistoryTaskDTO model, PageParam pageParam) {
		Map<String, Object> paramMap = this.model2map(model, null);
		return this.workFlowHitoryTaslDao.selectPageBy(paramMap, pageParam);
	}

	@Override
	public List<WorkFlowVariableDTO> findVariables(String instanceId, String taskId) {
		List<WorkFlowVariableDTO> ret = new ArrayList<WorkFlowVariableDTO>();
		List<HistoricVariableInstance> list = this.getHistoryService().createHistoricVariableInstanceQuery().processInstanceId(instanceId).list();
		for(HistoricVariableInstance historicVariableInstance : list) {
			String _taskId = historicVariableInstance.getTaskId();
			if(null!=_taskId && !taskId.equals(_taskId)) continue; //不是本节点的变量
			
			WorkFlowVariableDTO dto = new WorkFlowVariableDTO();
			dto.setVariableName(historicVariableInstance.getVariableName());
			dto.setValue(historicVariableInstance.getValue());
			dto.setVariableTypeName(historicVariableInstance.getVariableTypeName());
			dto.setCreateTime(historicVariableInstance.getCreateTime());
			dto.setLastUpdatedTime(historicVariableInstance.getLastUpdatedTime());
			dto.setTaskId(historicVariableInstance.getTaskId());
			dto.setInstanceId(historicVariableInstance.getProcessInstanceId());
			if(null==historicVariableInstance.getTaskId()) {
				dto.setLocal(false);
			} else {
				dto.setLocal(true);
			}
			ret.add(dto);
		}
		return ret;
	}

	@Override
	public WorkFlowTaskCandidatorDTO findCandidators(String taskId) {
		List<HistoricIdentityLink> list = this.getHistoryService().getHistoricIdentityLinksForTask(taskId);
		if(null!=list && list.size()>0) {
			Set<String> userIds = new HashSet<String>();
			Set<String> roleIds = new HashSet<String>();
			for(HistoricIdentityLink identityLink : list) {
				String type = identityLink.getType();
				if("candidate".equals(type)) {
					String userId = identityLink.getUserId();
					String roleId = identityLink.getGroupId();
					if(null!=userId && !"".equals(userId))
						userIds.add(userId);
					if(null!=roleId && !"".equals(roleId))
						roleIds.add(roleId);
				}
			}
			WorkFlowTaskCandidatorDTO dto = new WorkFlowTaskCandidatorDTO();
			dto.setUsers(userIds);
			dto.setRoles(roleIds);
			return dto;
		}
		return null;
	}

}

