package com.yffd.easy.workflow.activiti.test;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.junit.Test;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年11月17日 下午5:23:03 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class MyTest extends AbstractActivitiTest {

	@Test
	public void deploymentTest() {
		RepositoryService repositoryService = this.repositoryService;
		Deployment deployment = repositoryService.createDeployment()
		  .addClasspathResource("diagrams/leave.bpmn")
		  .deploy();
		
		System.out.println(deployment.getId());

	}
	
	@Test
	public void queryProcessDefinitionTest() {
		RepositoryService repositoryService = this.repositoryService;
		ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery();
//		List<ProcessDefinition> list = query.processDefinitionKey("leave_3").list();
		ProcessDefinition processDefinition = query.deploymentId("37501").singleResult();
		
		System.out.println("流程定义ID=" + processDefinition.getId());
		System.out.println("部署ID=" + processDefinition.getDeploymentId());
		System.out.println("流程定义名称=" + processDefinition.getName());
		System.out.println("流程定义KEY=" + processDefinition.getKey());
		System.out.println("版本号=" + processDefinition.getVersion());
		System.out.println("XML资源名称=" + processDefinition.getResourceName());
		System.out.println("图片资源名称=" + processDefinition.getDiagramResourceName());
	}
	
	@Test
	public void startProcessTest() {
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("applayUser", "employee1");
		variables.put("days", new Integer(3));
		
		RuntimeService runtimeService = this.runtimeService;
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("leave_3", variables);
		
		System.out.println(processInstance.getId() + " >> " + processInstance.getProcessDefinitionKey() + " >> " +processInstance.getProcessDefinitionId());
	}
	
	@Test
	public void queryTaskTest() {
		TaskService taskService = this.taskService;
		TaskQuery query = taskService.createTaskQuery();
//		Task task = query.processInstanceId("5001").taskCandidateGroup("deptLeader").singleResult();
		Task task = query.processDefinitionKey("leave_3").taskCandidateGroup("deptLeader").singleResult();
		if(null==task) {
			System.out.println(">>>>>>>>>>>>>>>>");
		} else {
			System.out.println(task.getId() + " >> " + task.getName());
		}
		
	}
	
	@Test
	public void claimTest() {
		TaskService taskService = this.taskService;
		// 签收任务
		String taskId = "2506";
		String userId = "leaderuser";
		taskService.claim(taskId, userId);
	}
	
	@Test
	public void completeTaskTest() {
		TaskService taskService = this.taskService;
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("approved", true);
		taskService.complete("27506", variables);
	}
	
	@Test
	public void queryHistoryTaskTest() {
		HistoryService historyService = this.historyService;
		HistoricProcessInstanceQuery query = historyService.createHistoricProcessInstanceQuery();
		System.out.println(query.finished().count());
	}
	
}

