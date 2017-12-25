package com.yffd.easy.workflow.model.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年12月11日 下午5:57:53 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class WorkFlowDefinitionDTO implements Serializable {
	
	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * @since JDK 1.7+
	 */
	private static final long serialVersionUID = 485098751459588314L;
	private String id;
	private String name;
	private String key;
	private int version;
	private String resourceName;
	private String dgrmResourceName;
	private int suspensionState;
	private String deploymentId;
	private Date deployTime;
	private String category;	// 类别
	
	private String startDate;
	private String endDate;
	
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
	public String getDgrmResourceName() {
		return dgrmResourceName;
	}
	public void setDgrmResourceName(String dgrmResourceName) {
		this.dgrmResourceName = dgrmResourceName;
	}
	public int getSuspensionState() {
		return suspensionState;
	}
	public void setSuspensionState(int suspensionState) {
		this.suspensionState = suspensionState;
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
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
}

