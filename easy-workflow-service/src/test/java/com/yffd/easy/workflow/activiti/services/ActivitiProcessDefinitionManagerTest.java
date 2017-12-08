package com.yffd.easy.workflow.activiti.services;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;
import com.yffd.easy.workflow.activiti.services.ActivitiProcessDefinitionService;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年12月6日 上午10:56:49 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
    "classpath:spring/spring-context-service-bpm.xml"
})
public class ActivitiProcessDefinitionManagerTest {

	@Autowired
	private ActivitiProcessDefinitionService manager;
	
	@Test
	public void findProcessDefinitionTest() {
		String processDefinitionKey = null;//"leave";
		String processDefinitionNameLike = "请假流程";
		boolean latestVersion = false;
		PageParam paginationInfo = new PageParam(1L, 10L);
		PageResult<ProcessDefinition> pageResult = this.manager.findProcessDefinition(processDefinitionKey, processDefinitionNameLike, latestVersion, paginationInfo);
		Assert.assertNotNull(pageResult);
		System.out.println("总记录数：" + paginationInfo.getTotalRecord());
		System.out.println("总页数：" + paginationInfo.getPageTotal());
		System.out.println("当前页总条数：" + pageResult.getRecordList().size());
		System.out.println("当前页数据：" + pageResult.getRecordList());
		StringBuilder builder = new StringBuilder();
		builder.append("流程定义ID").append(" >>>>>>>>>>>>> ")
		.append("流程发布ID").append(" >>>>>>>>>>>>> ")
		.append("流程名称").append(" >>>>>>>>>>>>> ")
		.append("流程关键字").append(" >>>>>>>>>>>>> ")
		.append("版本号").append(" >>>>>>>>>>>>> ")
		.append("XML").append(" >>>>>>>>>>>>> ")
		.append("图片").append(" >>>>>>>>>>>>> ")
		.append("部署时间");
		System.out.println(builder);
		for(ProcessDefinition processDefinition : pageResult.getRecordList()) {
			StringBuilder builder1 = new StringBuilder();
			builder1.append(processDefinition.getId()).append(" >>>>>>>>>>>>> ")
			.append(processDefinition.getDeploymentId()).append(" >>>>>>>>>>>>> ")
			.append(processDefinition.getName()).append(" >>>>>>>>>>>>> ")
			.append(processDefinition.getKey()).append(" >>>>>>>>>>>>> ")
			.append(processDefinition.getVersion()).append(" >>>>>>>>>>>>> ")
			.append(processDefinition.getResourceName()).append(" >>>>>>>>>>>>> ")
			.append(processDefinition.getDiagramResourceName()).append(" >>>>>>>>>>>>> ")
			.append("");
			System.out.println(builder1);
		}
	}
	
	@Test
	public void findProcessDefinitionByDidTest() {
		String deploymentId = "70001";
		List<ProcessDefinition> processDefinitions = this.manager.findProcessDefinitionByDid(deploymentId);
		Assert.assertNotNull(processDefinitions);
		System.out.println(processDefinitions);
	}
	
	@Test
	public void findProcessDefinitionByIdTest() {
		String processDefinitionId = "dispatch:1:45027";
		ProcessDefinition processDefinition = this.manager.findProcessDefinitionById(processDefinitionId);
		Assert.assertNotNull(processDefinition);
		System.out.println(processDefinition);
	}
	
//	@Test
//	public void findProcessDefinitionByPidTest() {
//		String processInstanceId = "57501";
//		ProcessDefinition processDefinition = this.manager.findProcessDefinitionByPid(processInstanceId);
//		Assert.assertNotNull(processDefinition);
//		System.out.println(processDefinition);
//	}
	
	
	@Test
	public void deployProcessDefinitionByClasspathTest() {
		String bpmnClasspath = "diagrams/leave/leave.bpmn";
		String pngClasspath = "diagrams/leave/leave.png";
		Deployment deployment = this.manager.deployProcessDefinitionByClasspath(bpmnClasspath, pngClasspath);
		Assert.assertNotNull(deployment);
		System.out.println(deployment);
	}
	
	@Test
	public void deployProcessDefinitionByInputStreamTest() {
		String resourceName = "timerExample.bpmn";
		String filePath = "D:\\java\\git-easy\\easy-master\\easy-workflow-service\\src\\test\\resources\\diagrams\\timer\\timerExample.bpmn";
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(filePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Deployment deployment = this.manager.deployProcessDefinitionByInputStream(resourceName, inputStream);
		Assert.assertNotNull(deployment);
		System.out.println(deployment);
	}
	
	@Test
	public void deployProcessDefinitionByZipInputStreamTest() {
		String filePath = "D:\\java\\git-easy\\easy-master\\easy-workflow-service\\src\\test\\resources\\deployments\\deployments.zip";
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(filePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		ZipInputStream ziInputStream = new ZipInputStream(inputStream);
		Deployment deployment = this.manager.deployProcessDefinitionByZipInputStream(ziInputStream);
		Assert.assertNotNull(deployment);
		System.out.println(deployment);
	}
}

