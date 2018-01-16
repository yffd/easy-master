package com.yffd.easy.workflow.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;
import com.yffd.easy.workflow.WorkFlowBaseTestCase;
import com.yffd.easy.workflow.model.dto.WorkFlowTaskDTO;
import com.yffd.easy.workflow.model.dto.WorkFlowVariableDTO;
import com.yffd.easy.workflow.service.WorkFlowTaskService;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年12月21日 下午5:24:09 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class WorkFlowTaskServiceImplTest extends WorkFlowBaseTestCase {

	@Autowired
	private WorkFlowTaskService workFlowTaskService;
	
	@Test
	public void claimTaskTest() {
		String userId = "xiaobai";
		String taskId = "20011";
		int result = this.workFlowTaskService.claimTask(taskId, userId);
		System.out.println(result);
	}
	
	@Test
	public void completeTaskTest() {
		String taskId = "2506";
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("name_2506", "2506");
		variables.put("deptLeaderPass", "同意");
		int result = this.workFlowTaskService.completeTask(taskId, variables);
		System.out.println(result);
	}
	
	@Test
	public void findTaskParamsTest() {
		String taskId = "15009";
		List<WorkFlowVariableDTO> list = this.workFlowTaskService.findVariables(taskId);
		System.out.println(list);
	}
	
	@Test
	public void findTodoTaskListByTest() {
		Set<String> userIds = new HashSet<String>();
		userIds.add("a2");
		userIds.add("a3");
		Set<String> roleIds = new HashSet<String>();
		roleIds.add("b2");
		roleIds.add("deptLeader");
		List<WorkFlowTaskDTO> list = this.workFlowTaskService.findTodoTaskListBy(userIds, roleIds, null, null, null, null, null);
		System.out.println(list.size());
	}
	
	@Test
	public void findTodoTaskListPageByTest() {
		PageParam pageParam = new PageParam(1L, 10L);
		Set<String> userIds = new HashSet<String>();
		userIds.add("a2");
		userIds.add("a3");
		Set<String> roleIds = new HashSet<String>();
		roleIds.add("b2");
		roleIds.add("deptLeader");
		PageResult<WorkFlowTaskDTO> page = this.workFlowTaskService.findTodoTaskListPageBy(userIds, roleIds, null, null, null, null, null, pageParam);
		System.out.println(page.getPageParam());
		System.out.println(page.getRecordList().size());
	}
	
	
	
}

