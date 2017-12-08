package com.yffd.easy.workflow.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年12月6日 下午5:10:31 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class ProcessDefinitionInfo implements Serializable {

	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * @since JDK 1.7+
	 */
	private static final long serialVersionUID = -2549986639507167774L;
	
	private String id;
	private String name;
	private String key;
	private int version;
	private String resourceName;
	private String diagramResourceName;
	private String suspendSatus;
	private String deploymentId;
	private String deploymentTime;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public String getDiagramResourceName() {
		return diagramResourceName;
	}
	public void setDiagramResourceName(String diagramResourceName) {
		this.diagramResourceName = diagramResourceName;
	}
	public String getSuspendSatus() {
		return suspendSatus;
	}
	public void setSuspendSatus(String suspendSatus) {
		this.suspendSatus = suspendSatus;
	}
	public String getDeploymentId() {
		return deploymentId;
	}
	public void setDeploymentId(String deploymentId) {
		this.deploymentId = deploymentId;
	}
	public String getDeploymentTime() {
		return deploymentTime;
	}
	public void setDeploymentTime(String deploymentTime) {
		this.deploymentTime = deploymentTime;
	}
	

}

