package com.yffd.easy.workflow.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;
import com.yffd.easy.workflow.activiti.dao.WorkFlowBaseTestCase;
import com.yffd.easy.workflow.model.dto.WorkFlowInstanceDTO;
import com.yffd.easy.workflow.service.WorkFlowInstanceService;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年12月21日 下午5:47:41 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class WorkFlowInstanceServiceImplTest extends WorkFlowBaseTestCase {

	@Autowired
	private WorkFlowInstanceService workFlowInstanceService;
	
	@Test
	public void findListPageTest() {
		PageParam pageParam = new PageParam(1L, 10L);
		WorkFlowInstanceDTO model = new WorkFlowInstanceDTO();
		PageResult<WorkFlowInstanceDTO> pageResult = this.workFlowInstanceService.findListPage(model, pageParam);
		Assert.assertNotNull(pageResult);
		System.out.println(pageResult.getPageParam());
		System.out.println(pageResult.getRecordList().size());
		for(WorkFlowInstanceDTO dto : pageResult.getRecordList()) {
			System.out.println(dto.getActivityNodeId());
			System.out.println(dto.getDefinitionId());
			System.out.println(dto.getDefinitionKey());
			System.out.println(dto.getDeployId());
			System.out.println(dto.getId());
			System.out.println(dto.getInstanceId());
			System.out.println(dto.getVariables());
			System.out.println(">>>>>>>>>>>>>>>>>>");
		}
	}
	
	@Test
	public void findInstanceByPKTest() {
		String id = "15001";
		WorkFlowInstanceDTO dto = this.workFlowInstanceService.findInstanceByPK(id);
		System.out.println(dto.getActivityNodeId());
		System.out.println(dto.getDefinitionId());
		System.out.println(dto.getDefinitionKey());
		System.out.println(dto.getDeployId());
		System.out.println(dto.getId());
		System.out.println(dto.getInstanceId());
		System.out.println(dto.getVariables());
	}
	
	@Test
	public void startInstanceByKeyTest() {
		String userId = "lisi";
		String definitionKey = "leave";
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("userCode", "xiaohei");
		variables.put("userName", "xiaohei");
		this.workFlowInstanceService.startInstanceByKey(userId, definitionKey, variables);
	}

	@Test
	public void activateInstanceTest() {
		String instanceId = "15001";
		int result = this.workFlowInstanceService.activateInstance(instanceId);
		System.out.println(result);
	}
	
	@Test
	public void suspendInstanceTest() {
		String instanceId = "15001";
		int result = this.workFlowInstanceService.suspendInstance(instanceId);
		System.out.println(result);
	}
	
	@Test
	public void findInstanceParamsTest() {
		String instanceId = "20001";
		Map<String, Object> params = this.workFlowInstanceService.findInstanceParams(instanceId);
		System.out.println(params);
	}
	
}

