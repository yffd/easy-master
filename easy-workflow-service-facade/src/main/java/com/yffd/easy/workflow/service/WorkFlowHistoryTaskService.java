package com.yffd.easy.workflow.service;

import java.util.List;

import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;
import com.yffd.easy.workflow.model.dto.WorkFlowHistoryTaskDTO;
import com.yffd.easy.workflow.model.dto.WorkFlowTaskCandidatorDTO;
import com.yffd.easy.workflow.model.dto.WorkFlowTaskDTO;
import com.yffd.easy.workflow.model.dto.WorkFlowVariableDTO;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年12月13日 下午5:33:36 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public interface WorkFlowHistoryTaskService {

	/**
	 * 流程任务：分页查询
	 * @Date	2017年12月21日 下午2:22:54 <br/>
	 * @author  zhangST
	 * @param model
	 * @param pageParam
	 * @return
	 */
	PageResult<WorkFlowHistoryTaskDTO> findListPage(WorkFlowHistoryTaskDTO model, PageParam pageParam);
	
	/**
	 * 流程任务：查询任务的参数
	 * @Date	2018年1月2日 上午11:54:39 <br/>
	 * @author  zhangST
	 * @param instanceId
	 * @param taskId
	 * @return
	 */
	List<WorkFlowVariableDTO> findVariables(String instanceId, String taskId);
	
	/**
	 * 流程任务：获取任务的候选人和候选角色
	 * @Date	2018年1月4日 下午4:26:29 <br/>
	 * @author  zhangST
	 * @param taskId
	 * @return
	 */
	WorkFlowTaskCandidatorDTO findCandidators(String taskId);
	
}

