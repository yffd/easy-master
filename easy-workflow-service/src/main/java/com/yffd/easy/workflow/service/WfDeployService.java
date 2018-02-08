package com.yffd.easy.workflow.service;

import java.io.InputStream;
import java.util.zip.ZipInputStream;

import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.springframework.stereotype.Service;

import com.yffd.easy.common.core.exception.EasyBizException;

/**
 * @Description  流程发布相关.
 * @Date		 2018年1月17日 下午4:17:22 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Service
public class WfDeployService extends WfCommonService {

	/**
	 * 流程发布-类路径
	 * @Date	2018年1月17日 下午4:18:34 <br/>
	 * @author  zhangST
	 * @param bpmnClasspath
	 * @param pngClasspath
	 * @return
	 */
	public String deployByClasspath(String bpmnClasspath, String pngClasspath) {
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

	/**
	 * 流程发布-输入流
	 * @Date	2018年1月17日 下午4:18:59 <br/>
	 * @author  zhangST
	 * @param resourceName
	 * @param inputStream
	 * @return
	 */
	public String deployByInputStream(String resourceName, InputStream inputStream) {
		if(null==resourceName || "".equals(resourceName) || null==inputStream) {
			throw EasyBizException.newInstance("参数为空错误：【resourceName is null, inputStream is null】");
		}
		DeploymentBuilder deploymentBuilder = this.getRepositoryService().createDeployment();
		deploymentBuilder.addInputStream(resourceName, inputStream);
		Deployment deployment = deploymentBuilder.deploy();
		return deployment.getId();
	}

	/**
	 * 流程发布-zip输入流
	 * @Date	2018年1月17日 下午4:19:20 <br/>
	 * @author  zhangST
	 * @param ziInputStream
	 * @return
	 */
	public String deployByZipInputStream(ZipInputStream ziInputStream) {
		if(null==ziInputStream) {
			throw EasyBizException.newInstance("参数为空错误：【ziInputStream is null】");
		}
		DeploymentBuilder deploymentBuilder = this.getRepositoryService().createDeployment();
		deploymentBuilder.addZipInputStream(ziInputStream);
		Deployment deployment = deploymentBuilder.deploy();
		return deployment.getId();
	}
	
}

