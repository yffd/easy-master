package com.yffd.easy.workflow.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.activiti.engine.ActivitiObjectNotFoundException;
import org.activiti.engine.ActivitiTaskAlreadyClaimedException;
import org.activiti.engine.task.IdentityLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;
import com.yffd.easy.workflow.activiti.service.ActivitiBaseService;
import com.yffd.easy.workflow.dao.WorkFlowTaskDao;
import com.yffd.easy.workflow.model.dto.WorkFlowTaskCandidatorDTO;
import com.yffd.easy.workflow.model.dto.WorkFlowTaskDTO;
import com.yffd.easy.workflow.model.dto.WorkFlowVariableDTO;
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

	@Autowired
	private WorkFlowTaskDao workFlowTaslDao;
	
	@Override
	public PageResult<WorkFlowTaskDTO> findListPage(WorkFlowTaskDTO model, PageParam pageParam) {
		Map<String, Object> paramMap = this.model2map(model, null);
		return this.workFlowTaslDao.selectPageBy(paramMap, pageParam);
	}

	@Override
	public List<WorkFlowVariableDTO> findVariables(String taskId) {
		Map<String, Object> params = this.getTaskService().getVariables(taskId);
		Map<String, Object> localParams = this.getTaskService().getVariablesLocal(taskId);
		List<WorkFlowVariableDTO> ret = new ArrayList<WorkFlowVariableDTO>();
		if(null!=params && params.size()>0) {
			Set<String> keys = params.keySet();
			for(String key : keys) {
				WorkFlowVariableDTO dto = new WorkFlowVariableDTO();
				dto.setVariableName(key);
				dto.setValue(params.get(key));
				dto.setLocal(false);
				ret.add(dto);
			}
		}
		if(null!=localParams && localParams.size()>0) {
			Set<String> keys = localParams.keySet();
			for(String key : keys) {
				WorkFlowVariableDTO dto = new WorkFlowVariableDTO();
				dto.setVariableName(key);
				dto.setValue(localParams.get(key));
				dto.setLocal(true);
				ret.add(dto);
			}
		}
		return ret;
	}

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

	@Override
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

	@Override
	public void changeAssignee(String taskId, String userId) {
		this.getTaskService().setAssignee(taskId, userId);
	}
	
	@Override
	public void setParam(String taskId, String paramName, Object value, boolean isLocal) {
		this.getTaskService().setVariable(taskId, paramName, value);
	}
	
	@Override
	public void setParams(String taskId, Map<String, ? extends Object> variables, boolean isLocal) {
		this.getTaskService().setVariables(taskId, variables);
	}
	
	@Override
	public PageResult<WorkFlowTaskDTO> findTodoTaskListPageBy(Set<String> userIds, Set<String> roleIds, 
			String workFlowCategoryCode, String workFlowCategoryName, String workFlowKey, 
			Date startTime, Date endTime, PageParam pageParam) {
		if((null==userIds || userIds.size()==0) && (null==roleIds || roleIds.size()==0)) return null;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("workFlowCategoryCode", workFlowCategoryCode);
		paramMap.put("workFlowCategoryName", workFlowCategoryName);
		paramMap.put("workFlowKey", workFlowKey);
		paramMap.put("startTime", startTime);
		paramMap.put("endTime", endTime);
		if(null!=userIds && userIds.size()>0) paramMap.put("userIds", userIds);
		if(null!=roleIds && roleIds.size()>0) paramMap.put("roleIds", roleIds);
		return this.workFlowTaslDao.selectTodoTaskPageBy(paramMap, pageParam);
	}

	@Override
	public List<WorkFlowTaskDTO> findTodoTaskListBy(Set<String> userIds, Set<String> roleIds,
			String workFlowCategoryCode, String workFlowCategoryName, String workFlowKey, 
			Date startTime, Date endTime) {
		if((null==userIds || userIds.size()==0) && (null==roleIds || roleIds.size()==0)) return null;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("workFlowCategoryCode", workFlowCategoryCode);
		paramMap.put("workFlowCategoryName", workFlowCategoryName);
		paramMap.put("workFlowKey", workFlowKey);
		paramMap.put("startTime", startTime);
		paramMap.put("endTime", endTime);
		if(null!=userIds && userIds.size()>0) paramMap.put("userIds", userIds);
		if(null!=roleIds && roleIds.size()>0) paramMap.put("roleIds", roleIds);
		return this.workFlowTaslDao.selectTodoTaskListBy(paramMap);
	}
	
	@Override
	public WorkFlowTaskCandidatorDTO findCandidators(String taskId) {
		List<IdentityLink> list = this.getTaskService().getIdentityLinksForTask(taskId);
		if(null!=list && list.size()>0) {
			Set<String> userIds = new HashSet<String>();
			Set<String> roleIds = new HashSet<String>();
			for(IdentityLink identityLink : list) {
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

