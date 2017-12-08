package com.yffd.easy.workflow.activiti.services;

import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年12月4日 下午4:04:11 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Deprecated
@Component
public class ActivitiProcessDefinitionService extends ActivitiBaseService {
	private static final Logger LOG = LoggerFactory.getLogger(ActivitiProcessDefinitionService.class);
	
	/**
	 * 流程定义-分页查询
	 * @Date	2017年12月6日 下午2:28:46 <br/>
	 * @author  zhangST
	 * @param processDefinitionKey
	 * @param processDefinitionNameLike
	 * @param latestVersion
	 * @param paginationInfo		分页参数
	 * @return
	 */
	public PageResult<ProcessDefinition> findProcessDefinition(String processDefinitionKey, 
			String processDefinitionNameLike, boolean latestVersion, PageParam paginationInfo) {
		ProcessDefinitionQuery processDefinitionQuery = this.getRepositoryService().createProcessDefinitionQuery();
		if(StringUtils.isNotBlank(processDefinitionKey)) {
			processDefinitionQuery = processDefinitionQuery.processDefinitionKey(processDefinitionKey);
		}
		if(StringUtils.isNotBlank(processDefinitionNameLike)) {
			processDefinitionQuery = processDefinitionQuery.processDefinitionNameLike("%" + processDefinitionNameLike + "%");
		}
		if(latestVersion) {
			processDefinitionQuery = processDefinitionQuery.latestVersion();
		}
		// 计算总条数
		long totalRecord = processDefinitionQuery.count();
		paginationInfo.setTotalRecord(totalRecord);
		
		// 校验当前页码值的有效范围
		Long validPageNum = paginationInfo.countPageNum(paginationInfo.getPageNum(), paginationInfo.getPageLimit(), totalRecord);
		paginationInfo.setPageNum(validPageNum);
		
		processDefinitionQuery = processDefinitionQuery.orderByDeploymentId().desc().orderByProcessDefinitionName().asc();
		
		Long firstResult = paginationInfo.getPageStartRow();
		Long maxResults = paginationInfo.getPageLimit();
		
		List<ProcessDefinition> processDefinitions = processDefinitionQuery.listPage(firstResult.intValue(), maxResults.intValue());
		PageResult<ProcessDefinition> paginationResult = new PageResult<ProcessDefinition>(paginationInfo, processDefinitions);
		return paginationResult;
	}
	
	/**
	 * 根据【流程发布】ID查询【流程定义】对象{@link ProcessDefinition}
	 * @Date	2017年12月6日 上午11:37:02 <br/>
	 * @author  zhangST
	 * @param deploymentId
	 * @return
	 */
	public List<ProcessDefinition> findProcessDefinitionByDid(String deploymentId) {
		ProcessDefinitionQuery processDefinitionQuery = this.getRepositoryService().createProcessDefinitionQuery()
				.deploymentId(deploymentId);
		List<ProcessDefinition> processDefinitions = processDefinitionQuery.list();
		return processDefinitions;
	}
	
	/**
	 * 根据【流程定义】ID查询【流程定义】对象{@link ProcessDefinition}
	 * @Date	2017年12月4日 下午4:21:24 <br/>
	 * @author  zhangST
	 * @param processDefinitionId
	 * @return
	 */
	public ProcessDefinition findProcessDefinitionById(String processDefinitionId) {
		ProcessDefinitionQuery processDefinitionQuery = this.getRepositoryService().createProcessDefinitionQuery().processDefinitionId(processDefinitionId);
		ProcessDefinition processDefinition = processDefinitionQuery.singleResult();
		return processDefinition;
	}
	
	/**
	 * 根据【流程实例】ID查询【流程定义】对象{@link ProcessDefinition}
	 * @Date	2017年12月4日 下午4:12:08 <br/>
	 * @author  zhangST
	 * @param processInstanceId
	 * @return
	 */
	public ProcessDefinition findProcessDefinitionByPid(String processInstanceId) {
		HistoricProcessInstanceQuery historicProcessInstanceQuery = this.getHistoryService().createHistoricProcessInstanceQuery()
				.processInstanceId(processInstanceId);
		HistoricProcessInstance historicProcessInstance = historicProcessInstanceQuery.singleResult();
		String processDefinitionId = historicProcessInstance.getProcessDefinitionId();
		ProcessDefinition processDefinition = this.findProcessDefinitionById(processDefinitionId);
		return processDefinition;
	}
	
	/**
	 * 流程定义发布-类路径
	 * @Date	2017年12月6日 上午10:52:48 <br/>
	 * @author  zhangST
	 * @param bpmnClasspath
	 * @param pngClasspath
	 * @return
	 */
	public Deployment deployProcessDefinitionByClasspath(String bpmnClasspath, String pngClasspath) {
		if(null==bpmnClasspath) {
			LOG.warn("流程定义发布被忽略：" + bpmnClasspath);
		}
		DeploymentBuilder deploymentBuilder = this.getRepositoryService().createDeployment();
		deploymentBuilder.addClasspathResource(bpmnClasspath);
		if(null!=pngClasspath) {
			deploymentBuilder.addClasspathResource(pngClasspath);
		}
		Deployment deployment = deploymentBuilder.deploy();
		return deployment;
	}
	
	/**
	 * 流程定义发布-输入流
	 * @Date	2017年12月6日 上午11:42:42 <br/>
	 * @author  zhangST
	 * @param resourceName
	 * @param inputStream
	 * @return
	 */
	public Deployment deployProcessDefinitionByInputStream(String resourceName, InputStream inputStream) {
		if(null==resourceName || "".equals(resourceName) || null==inputStream) {
			LOG.warn("流程定义发布被忽略：" + resourceName);
		}
		DeploymentBuilder deploymentBuilder = this.getRepositoryService().createDeployment();
		deploymentBuilder.addInputStream(resourceName, inputStream);
		Deployment deployment = deploymentBuilder.deploy();
		return deployment;
	}
	
	/**
	 * 流程定义发布-zip输入流
	 * @Date	2017年12月6日 下午1:37:36 <br/>
	 * @author  zhangST
	 * @param ziInputStream
	 * @return
	 */
	public Deployment deployProcessDefinitionByZipInputStream(ZipInputStream ziInputStream) {
		if(null==ziInputStream) {
			LOG.warn("流程定义发布被忽略：");
		}
		DeploymentBuilder deploymentBuilder = this.getRepositoryService().createDeployment();
		deploymentBuilder.addZipInputStream(ziInputStream);
		Deployment deployment = deploymentBuilder.deploy();
		return deployment;
	}
}

