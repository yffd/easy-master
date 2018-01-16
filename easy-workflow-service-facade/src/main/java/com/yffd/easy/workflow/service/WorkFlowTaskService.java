package com.yffd.easy.workflow.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;
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
public interface WorkFlowTaskService {

	/**
	 * 流程任务：分页查询
	 * @Date	2017年12月21日 下午2:22:54 <br/>
	 * @author  zhangST
	 * @param model
	 * @param pageParam
	 * @return
	 */
	PageResult<WorkFlowTaskDTO> findListPage(WorkFlowTaskDTO model, PageParam pageParam);
	
	/**
	 * 流程任务：查询任务的参数
	 * @Date	2018年1月2日 上午11:54:39 <br/>
	 * @author  zhangST
	 * @param taskId
	 * @return
	 */
	List<WorkFlowVariableDTO> findVariables(String taskId);
	
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
	
	/**
	 * 流程任务：签收并完成任务
	 * @Date	2018年1月2日 下午4:25:50 <br/>
	 * @author  zhangST
	 * @param userId
	 * @param taskId
	 * @param variables
	 * @return		0：成功，1：任务不存在，2：任务已被签收
	 */
	int claimAndCompleteTask(String userId, String taskId, Map<String, Object> variables);
	
	/**
	 * 流程任务：变更签收人
	 * @Date	2018年1月2日 下午5:00:37 <br/>
	 * @author  zhangST
	 * @param taskId
	 * @param userId
	 */
	void changeAssignee(String taskId, String userId);

	/**
	 * 流程任务：设置变量
	 * @Date	2018年1月2日 下午5:01:03 <br/>
	 * @author  zhangST
	 * @param taskId
	 * @param paramName
	 * @param value
	 * @param isLocal	true:本地范围作用域，即只有当前任务节点可见，false:实例范围作用域，即所有节点可见
	 */
	void setParam(String taskId, String paramName, Object value, boolean isLocal);

	/**
	 * 流程任务：设置变量，实例范围作用域，即所有节点可见
	 * @Date	2018年1月2日 下午5:01:18 <br/>
	 * @author  zhangST
	 * @param taskId
	 * @param variables
	 * @param isLocal	true:本地范围作用域，即只有当前任务节点可见，false:实例范围作用域，即所有节点可见
	 */
	void setParams(String taskId, Map<String, ? extends Object> variables, boolean isLocal);

	/**
	 * 流程任务：待办任务查询
	 * @Date	2018年1月9日 上午11:12:31 <br/>
	 * @author  zhangST
	 * @param userIds				任务所属用户ID
	 * @param roleIds				任务所属角色ID
	 * @param workFlowCategoryCode	工作流类别编号，即流程图中的Namespace
	 * @param workFlowCategoryName	工作流类别名称，即流程图中的Name
	 * @param workFlowKey			工作流关键字，即流程图中的Id
	 * @param startTime				任务创建时间范围
	 * @param endTime				任务创建时间范围
	 * @param pageParam				分页
	 * @return
	 */
	PageResult<WorkFlowTaskDTO> findTodoTaskListPageBy(Set<String> userIds, Set<String> roleIds, 
			String workFlowCategoryCode, String workFlowCategoryName, String workFlowKey, 
			Date startTime, Date endTime, PageParam pageParam);
	
	/**
	 * 流程任务：待办任务查询
	 * @Date	2018年1月9日 上午11:14:25 <br/>
	 * @author  zhangST
	 * @param userIds				任务所属用户ID
	 * @param roleIds				任务所属角色ID
	 * @param workFlowCategoryCode	工作流类别编号，即流程图中的Namespace
	 * @param workFlowCategoryName	工作流类别名称，即流程图中的Name
	 * @param workFlowKey			工作流关键字，即流程图中的Id
	 * @param startTime				任务创建时间范围
	 * @param endTime				任务创建时间范围
	 * @return
	 */
	List<WorkFlowTaskDTO> findTodoTaskListBy(Set<String> userIds, Set<String> roleIds,
			String workFlowCategoryCode, String workFlowCategoryName, String workFlowKey, 
			Date startTime, Date endTime);
	
	/**
	 * 流程任务：获取任务的候选人和候选角色
	 * @Date	2018年1月4日 下午4:26:29 <br/>
	 * @author  zhangST
	 * @param taskId
	 * @return
	 */
	WorkFlowTaskCandidatorDTO findCandidators(String taskId);
	
}

