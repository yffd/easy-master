package com.yffd.easy.workflow.service.impl;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;
import com.yffd.easy.workflow.WorkFlowBaseTestCase;
import com.yffd.easy.workflow.model.dto.WorkFlowActivityNodeDTO;
import com.yffd.easy.workflow.model.dto.WorkFlowDefinitionDTO;
import com.yffd.easy.workflow.service.WorkFlowDefinitionService;


/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年12月15日 下午2:05:41 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class WorkFlowDefinitionServiceImplTest extends WorkFlowBaseTestCase {

	@Autowired
	private WorkFlowDefinitionService workFlowDefinitionService;
	
	@Test
	public void findListPageTest() {
		PageParam pageParam = new PageParam(1L, 10L);
		WorkFlowDefinitionDTO model = new WorkFlowDefinitionDTO();
		PageResult<WorkFlowDefinitionDTO> pageResult = this.workFlowDefinitionService.findListPage(model, pageParam);
		Assert.assertNotNull(pageResult);
		System.out.println(pageResult.getPageParam());
		System.out.println(pageResult.getRecordList().size());
		for(WorkFlowDefinitionDTO dto : pageResult.getRecordList()) {
			System.out.println(dto.getId());
			System.out.println(dto.getDeploymentId());
			System.out.println(dto.getResourceName());
			System.out.println(dto.getDgrmResourceName());
			System.out.println(">>>>>>>>>>>>>>>>>>");
		}
	}
	
	@Test
	public void findDefinitionByIdTest() {
		String id = "leave:1:4";
		WorkFlowDefinitionDTO dto = this.workFlowDefinitionService.findDefinitionByPK(id);
		Assert.assertNotNull(dto);
		System.out.println(dto.getId());
	}
	
	@Test
	public void getActivityNodeTest() {
		String id = "leave:1:4";
		List<WorkFlowActivityNodeDTO> nodeList = this.workFlowDefinitionService.getActivityNode(id);
		System.out.println(nodeList.size());
		for(WorkFlowActivityNodeDTO node : nodeList) {
			System.out.println(node);
		}
	}
}

