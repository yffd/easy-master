package com.yffd.easy.workflow.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yffd.easy.workflow.WorkFlowBaseTestCase;
import com.yffd.easy.workflow.model.dto.WorkFlowModelDefinitionDTO;
import com.yffd.easy.workflow.service.WorkFlowModelService;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年1月16日 下午2:39:59 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class WorkFlowModelServiceImplTest extends WorkFlowBaseTestCase {

	@Autowired
	private WorkFlowModelService workFlowModelService;
	
	@Test
	public void addOneTest() {
		WorkFlowModelDefinitionDTO dto = new WorkFlowModelDefinitionDTO();
		dto.setWorkFlowCategoryCode("xxx");
		dto.setWorkFlowCategoryName("yyy");
		dto.setWorkFlowDesc("zzz");
		dto.setWorkFlowKey("www");
		this.workFlowModelService.addOne(dto );
	}
}

