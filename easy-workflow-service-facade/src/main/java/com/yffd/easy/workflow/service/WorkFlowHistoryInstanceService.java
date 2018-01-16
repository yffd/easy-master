package com.yffd.easy.workflow.service;

import java.util.List;

import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;
import com.yffd.easy.workflow.model.dto.WorkFlowHistoryInstanceDTO;
import com.yffd.easy.workflow.model.dto.WorkFlowInstanceDTO;
import com.yffd.easy.workflow.model.dto.WorkFlowVariableDTO;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年12月21日 下午3:41:10 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public interface WorkFlowHistoryInstanceService {
	
	/**
	 * 历史流程实例：分页查询
	 * @Date	2017年12月21日 下午2:22:54 <br/>
	 * @author  zhangST
	 * @param model
	 * @param pageParam
	 * @return
	 */
	PageResult<WorkFlowHistoryInstanceDTO> findListPage(WorkFlowInstanceDTO model, PageParam pageParam);
	
	/**
	 * 历史流程实例：根据流程实例ID，获取响应的变量参数信息
	 * @Date	2018年1月5日 上午11:15:05 <br/>
	 * @author  zhangST
	 * @param instanceId
	 * @return
	 */
	List<WorkFlowVariableDTO> findVariables(String instanceId);
}

