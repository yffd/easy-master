package com.yffd.easy.workflow.service;

import java.util.Map;

import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;
import com.yffd.easy.workflow.model.dto.WorkFlowInstanceDTO;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年12月21日 下午3:41:10 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public interface WorkFlowInstanceService {
	
	/**
	 * 流程实例：分页查询
	 * @Date	2017年12月21日 下午2:22:54 <br/>
	 * @author  zhangST
	 * @param model
	 * @param pageParam
	 * @return
	 */
	PageResult<WorkFlowInstanceDTO> findListPage(WorkFlowInstanceDTO model, PageParam pageParam);
	
	/**
	 * 流程实例：主键查询
	 * @Date	2017年12月21日 下午2:23:15 <br/>
	 * @author  zhangST
	 * @param id
	 * @return
	 */
	WorkFlowInstanceDTO findInstanceByPK(String id);
	
	/**
	 * 流程定义：启动流程实例
	 * @Date	2017年12月21日 下午4:41:43 <br/>
	 * @author  zhangST
	 * @param userId
	 * @param definitionKey
	 * @param variables
	 * @return
	 */
	WorkFlowInstanceDTO startInstanceByKey(String userId, String definitionKey, Map<String, Object> variables);
	
	/**
	 * 流程实例:激活【流程实例】
	 * @Date	2017年12月13日 下午2:20:37 <br/>
	 * @author  zhangST
	 * @param instanceId
	 * @return  0：成功，1：流程实例不存在，2：流程实例已激活
	 */
	int activateInstance(String instanceId);
	
	/**
	 * 流程实例:挂起【流程实例】，挂起时，流程不能继续执行（比如，完成任务会抛出异常）， 异步操作（比如定时器）也不会执行。
	 * @Date	2017年12月13日 下午2:21:04 <br/>
	 * @author  zhangST
	 * @param instanceId
	 * @return	0：成功，1：流程实例不存在，2：流程实例已挂起
	 */
	int suspendInstance(String instanceId);
	
	Map<String, Object> findInstanceParams(String instanceId);
}

