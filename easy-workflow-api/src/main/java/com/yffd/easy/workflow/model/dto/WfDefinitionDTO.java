package com.yffd.easy.workflow.model.dto;

import java.util.Date;

/**
 * @Description  流程定义信息.
 * @Date		 2017年12月11日 下午5:57:53 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class WfDefinitionDTO extends WfBaseDTO {
	
	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * @since JDK 1.7+
	 */
	private static final long serialVersionUID = 485098751459588314L;
	private String id;
	private String resourceName;
	private String dgrmResourceName;
	private int definitionState;
	private String deploymentId;
	private Date deployTime;
	private boolean lastVersion;
	
	private Date searchStartTime;
	private Date searchEndTime;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public String getDgrmResourceName() {
		return dgrmResourceName;
	}
	public void setDgrmResourceName(String dgrmResourceName) {
		this.dgrmResourceName = dgrmResourceName;
	}
	public int getDefinitionState() {
		return definitionState;
	}
	public void setDefinitionState(int definitionState) {
		this.definitionState = definitionState;
	}
	public String getDeploymentId() {
		return deploymentId;
	}
	public void setDeploymentId(String deploymentId) {
		this.deploymentId = deploymentId;
	}
	public Date getDeployTime() {
		return deployTime;
	}
	public void setDeployTime(Date deployTime) {
		this.deployTime = deployTime;
	}
	public boolean isLastVersion() {
		return lastVersion;
	}
	public void setLastVersion(boolean lastVersion) {
		this.lastVersion = lastVersion;
	}
	public Date getSearchStartTime() {
		return searchStartTime;
	}
	public void setSearchStartTime(Date searchStartTime) {
		this.searchStartTime = searchStartTime;
	}
	public Date getSearchEndTime() {
		return searchEndTime;
	}
	public void setSearchEndTime(Date searchEndTime) {
		this.searchEndTime = searchEndTime;
	}
	
}

