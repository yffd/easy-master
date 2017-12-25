package com.yffd.easy.workflow.service.impl;

import java.io.InputStream;
import java.util.Map;
import java.util.zip.ZipInputStream;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.ActivitiObjectNotFoundException;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yffd.easy.common.core.exception.EasyBizException;
import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;
import com.yffd.easy.workflow.activiti.service.ActivitiBaseService;
import com.yffd.easy.workflow.dao.WorkFlowDefinitionDao;
import com.yffd.easy.workflow.model.dto.WorkFlowDefinitionDTO;
import com.yffd.easy.workflow.service.WorkFlowDefinitionService;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年12月14日 下午3:25:06 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Service("workFlowDefinitionService")
public class WorkFlowDefinitionServiceImpl extends ActivitiBaseService implements WorkFlowDefinitionService {
	@Autowired
	private WorkFlowDefinitionDao processDefinitionDao;

	@Override
	public PageResult<WorkFlowDefinitionDTO> findListPage(WorkFlowDefinitionDTO model, PageParam pageParam) {
		Map<String, Object> paramMap = this.model2map(model, null);
		PageResult<Map<String, Object>> pageResult = this.processDefinitionDao.selectPageBy(paramMap, pageParam);
		return this.map2model(pageResult, WorkFlowDefinitionDTO.class, true);
	}

	@Override
	public WorkFlowDefinitionDTO findDefinitionByPK(String id) {
		Map<String, Object> mapResult = this.processDefinitionDao.selectByPK(id);
		return this.map2model(mapResult, WorkFlowDefinitionDTO.class, true);
	}

	@Override
	public InputStream loadResource(String deploymentId, String resourceName) {
		InputStream inputStream = null;
		try {
			inputStream = this.getRepositoryService().getResourceAsStream(deploymentId, resourceName);
		} catch (ActivitiObjectNotFoundException e) {
			throw EasyBizException.newInstance("流程定义资源不存在或未发布，deploymentId=" + deploymentId + ", resourceName=" + resourceName, e);
		}
		return inputStream;
	}

	@Override
	public int activateDefinition(String definitionId) {
		try {
			this.getRepositoryService().activateProcessDefinitionById(definitionId);
			return 0;
		} catch (ActivitiObjectNotFoundException e) {
			return 1;
		}
	}

	@Override
	public int suspendDefinition(String definitionId) {
		try {
			this.getRepositoryService().suspendProcessDefinitionById(definitionId);
			return 0;
		} catch (ActivitiObjectNotFoundException e) {
			return 1;
		} catch (ActivitiException e) {
			return 2;
		}
	}

	@Override
	public String deployDefinitionByClasspath(String bpmnClasspath, String pngClasspath) {
		if(null==bpmnClasspath) {
			throw EasyBizException.newInstance("参数为空错误：【bpmnClasspath is null】");
		}
		DeploymentBuilder deploymentBuilder = this.getRepositoryService().createDeployment();
		deploymentBuilder.addClasspathResource(bpmnClasspath);
		if(null!=pngClasspath) {
			deploymentBuilder.addClasspathResource(pngClasspath);
		}
		Deployment deployment = deploymentBuilder.deploy();
		return deployment.getId();
	}

	@Override
	public String deployDefinitionByInputStream(String resourceName, InputStream inputStream) {
		if(null==resourceName || "".equals(resourceName) || null==inputStream) {
			throw EasyBizException.newInstance("参数为空错误：【resourceName is null, inputStream is null】");
		}
		DeploymentBuilder deploymentBuilder = this.getRepositoryService().createDeployment();
		deploymentBuilder.addInputStream(resourceName, inputStream);
		Deployment deployment = deploymentBuilder.deploy();
		return deployment.getId();
	}

	@Override
	public String deployDefinitionByZipInputStream(ZipInputStream ziInputStream) {
		if(null==ziInputStream) {
			throw EasyBizException.newInstance("参数为空错误：【ziInputStream is null】");
		}
		DeploymentBuilder deploymentBuilder = this.getRepositoryService().createDeployment();
		deploymentBuilder.addZipInputStream(ziInputStream);
		Deployment deployment = deploymentBuilder.deploy();
		return deployment.getId();
	}

}

