package com.yffd.easy.workflow.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;
import com.yffd.easy.workflow.WorkFlowBaseTestCase;
import com.yffd.easy.workflow.model.dto.WorkFlowHistoryInstanceDTO;
import com.yffd.easy.workflow.model.dto.WorkFlowInstanceDTO;
import com.yffd.easy.workflow.service.WorkFlowHistoryInstanceService;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年12月21日 下午5:47:41 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class WorkFlowHistoryInstanceServiceImplTest extends WorkFlowBaseTestCase {

	@Autowired
	private WorkFlowHistoryInstanceService workFlowHistoryInstanceService;
	
	@Test
	public void findListPageTest() {
		PageParam pageParam = new PageParam(1L, 10L);
		WorkFlowInstanceDTO model = new WorkFlowInstanceDTO();
		PageResult<WorkFlowHistoryInstanceDTO> pageResult = this.workFlowHistoryInstanceService.findListPage(model, pageParam);
		Assert.assertNotNull(pageResult);
		System.out.println(pageResult.getPageParam());
		System.out.println(pageResult.getRecordList().size());
		for(WorkFlowHistoryInstanceDTO dto : pageResult.getRecordList()) {
			System.out.println(dto.getDefinitionId());
			System.out.println(dto.getId());
			System.out.println(dto.getInstanceId());
			System.out.println(dto.getStartTime());
			System.out.println(dto.getEndTime());
			System.out.println(dto.getStartUserId());
			System.out.println(">>>>>>>>>>>>>>>>>>");
		}
	}
	
	
}

