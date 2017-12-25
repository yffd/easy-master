package com.yffd.easy.workflow.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yffd.easy.workflow.activiti.dao.WorkFlowBaseTestCase;
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
}

