package com.yffd.easy.workflow.service;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;
import com.yffd.easy.workflow.WorkFlowBaseTestCase;
import com.yffd.easy.workflow.model.dto.WfDefinitionDTO;
import com.yffd.easy.workflow.model.dto.WfHistoryInstanceDTO;
import com.yffd.easy.workflow.model.dto.WfHistoryTaskDTO;
import com.yffd.easy.workflow.model.dto.WfInstanceDTO;
import com.yffd.easy.workflow.model.dto.WfTaskDTO;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年1月17日 下午4:49:46 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class WfQueryServiceTest extends WorkFlowBaseTestCase {

	@Autowired
	private WfQueryService wfQueryService;
	
	@Test
	public void findDeployListPageTest() {
		PageParam pageParam = new PageParam(1L, 10L);
		WfDefinitionDTO model = new WfDefinitionDTO();
		PageResult<WfDefinitionDTO> pageResult = this.wfQueryService.findDefinitionListPage(model, pageParam);
		Assert.assertNotNull(pageResult);
		System.out.println(pageResult.getPageParam());
		System.out.println(pageResult.getRecordList().size());
		for(WfDefinitionDTO dto : pageResult.getRecordList()) {
			System.out.println(dto.getId());
			System.out.println(dto.getDeploymentId());
			System.out.println(dto.getResourceName());
			System.out.println(dto.getDgrmResourceName());
			System.out.println(">>>>>>>>>>>>>>>>>>");
		}
	}
	
	@Test
	public void findInstanceListPageTest() {
		PageParam pageParam = new PageParam(1L, 10L);
		WfInstanceDTO model = new WfInstanceDTO();
		PageResult<WfInstanceDTO> pageResult = this.wfQueryService.findInstanceListPage(model, pageParam);
		Assert.assertNotNull(pageResult);
		System.out.println(pageResult.getPageParam());
		System.out.println(pageResult.getRecordList().size());
		for(WfInstanceDTO dto : pageResult.getRecordList()) {
			System.out.println(dto.getActivityNodeId());
			System.out.println(dto.getDefinitionId());
			System.out.println(dto.getId());
			System.out.println(dto.getInstanceId());
			System.out.println(">>>>>>>>>>>>>>>>>>");
		}
	}
	
	@Test
	public void findTaskListPageTest() {
		PageParam pageParam = new PageParam(1L, 10L);
		WfTaskDTO model = new WfTaskDTO();
		PageResult<WfTaskDTO> pageResult = this.wfQueryService.findTaskListPage(model, pageParam);
		Assert.assertNotNull(pageResult);
		System.out.println(pageResult.getPageParam());
		System.out.println(pageResult.getRecordList().size());
	}
	
	@Test
	public void findHistoryInstanceListPageTest() {
		PageParam pageParam = new PageParam(1L, 10L);
		WfHistoryInstanceDTO model = new WfHistoryInstanceDTO();
		PageResult<WfHistoryInstanceDTO> pageResult = this.wfQueryService.findHistoryInstanceListPage(model, pageParam);
		Assert.assertNotNull(pageResult);
		System.out.println(pageResult.getPageParam());
		System.out.println(pageResult.getRecordList().size());
		for(WfHistoryInstanceDTO dto : pageResult.getRecordList()) {
			System.out.println(dto.getDefinitionId());
			System.out.println(dto.getId());
			System.out.println(dto.getInstanceId());
			System.out.println(dto.getStartTime());
			System.out.println(dto.getEndTime());
			System.out.println(dto.getStartUserId());
			System.out.println(">>>>>>>>>>>>>>>>>>");
		}
	}
	
	@Test
	public void findHistoryTaskListPageTest() {
		PageParam pageParam = new PageParam(1L, 10L);
		WfHistoryTaskDTO model = new WfHistoryTaskDTO();
		PageResult<WfHistoryTaskDTO> page = this.wfQueryService.findHistoryTaskListPage(model, pageParam);
		System.out.println(page.getPageParam());
		System.out.println(page.getRecordList().size());
	}
	
	
	
	@Test
	public void findTodoTaskListPageByTest() {
		PageParam pageParam = new PageParam(1L, 10L);
		WfTaskDTO model = new WfTaskDTO();
		Set<String> userIds = new HashSet<String>();
		userIds.add("a2");
		userIds.add("a3");
		Set<String> roleIds = new HashSet<String>();
		roleIds.add("b2");
		roleIds.add("deptLeader");
		PageResult<WfTaskDTO> page = this.wfQueryService.findTodoTaskListPage(userIds, roleIds, model, pageParam);
		System.out.println(page.getPageParam());
		System.out.println(page.getRecordList().size());
	}
}

