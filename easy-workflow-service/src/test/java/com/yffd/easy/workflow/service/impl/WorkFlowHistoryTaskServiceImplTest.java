package com.yffd.easy.workflow.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;
import com.yffd.easy.workflow.WorkFlowBaseTestCase;
import com.yffd.easy.workflow.model.dto.WorkFlowHistoryTaskDTO;
import com.yffd.easy.workflow.service.WorkFlowHistoryTaskService;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年12月21日 下午5:24:09 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class WorkFlowHistoryTaskServiceImplTest extends WorkFlowBaseTestCase {

	@Autowired
	private WorkFlowHistoryTaskService workFlowHistoryTaskService;
	
	@Test
	public void findTaskParamsTest() {
		String instanceId = "15008";
		String taskId = "15008";
		this.workFlowHistoryTaskService.findVariables(instanceId, taskId);
	}
	
	@Test
	public void findListPageTest() {
		PageParam pageParam = new PageParam(1L, 10L);
		WorkFlowHistoryTaskDTO model = new WorkFlowHistoryTaskDTO();
		PageResult<WorkFlowHistoryTaskDTO> page = this.workFlowHistoryTaskService.findListPage(model, pageParam);
		System.out.println(page.getPageParam());
		System.out.println(page.getRecordList().size());
	}
	
	
	
}

