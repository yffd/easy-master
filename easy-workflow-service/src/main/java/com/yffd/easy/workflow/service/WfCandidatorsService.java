package com.yffd.easy.workflow.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.activiti.engine.history.HistoricIdentityLink;
import org.activiti.engine.task.IdentityLink;
import org.springframework.stereotype.Service;

import com.yffd.easy.workflow.model.dto.WfTaskCandidatorDTO;

/**
 * @Description  流程候选人相关.
 * @Date		 2018年1月17日 下午3:56:24 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Service
public class WfCandidatorsService extends WfCommonService {

	/**
	 * 获取任务的候选人和候选角色
	 * @Date	2018年1月17日 下午3:58:02 <br/>
	 * @author  zhangST
	 * @param taskId
	 * @return
	 */
	public WfTaskCandidatorDTO findTaskCandidators(String taskId) {
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
			WfTaskCandidatorDTO dto = new WfTaskCandidatorDTO();
			dto.setUsers(userIds);
			dto.setRoles(roleIds);
			return dto;
		}
		return null;
	}
	
	/**
	 * 获取历史任务的候选人和候选角色
	 * @Date	2018年1月17日 下午4:04:19 <br/>
	 * @author  zhangST
	 * @param taskId
	 * @return
	 */
	public WfTaskCandidatorDTO findHistoryTaskCandidators(String taskId) {
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
			WfTaskCandidatorDTO dto = new WfTaskCandidatorDTO();
			dto.setUsers(userIds);
			dto.setRoles(roleIds);
			return dto;
		}
		return null;
	}
}

