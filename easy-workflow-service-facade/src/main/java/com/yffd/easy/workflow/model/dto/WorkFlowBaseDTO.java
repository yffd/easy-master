package com.yffd.easy.workflow.model.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年12月21日 下午4:06:16 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class WorkFlowBaseDTO implements Serializable {
	
	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * @since JDK 1.7+
	 */
	private static final long serialVersionUID = -1336615960267535825L;

	private String workFlowCategoryCode;	// 工作流类别编号，即流程图中的Namespace
	private String workFlowCategoryName;	// 工作流类别名称，即流程图中的Name
	private String workFlowKey;				// 工作流关键字，即流程图中的Id
	private int workFlowVersion;			// 工作流版本
	private String workFlowDesc;			// 工作流描述，即流程图中的Documentation
	
	public String getWorkFlowCategoryCode() {
		return workFlowCategoryCode;
	}
	public void setWorkFlowCategoryCode(String workFlowCategoryCode) {
		this.workFlowCategoryCode = workFlowCategoryCode;
	}
	public String getWorkFlowCategoryName() {
		return workFlowCategoryName;
	}
	public void setWorkFlowCategoryName(String workFlowCategoryName) {
		this.workFlowCategoryName = workFlowCategoryName;
	}
	public String getWorkFlowKey() {
		return workFlowKey;
	}
	public void setWorkFlowKey(String workFlowKey) {
		this.workFlowKey = workFlowKey;
	}
	public int getWorkFlowVersion() {
		return workFlowVersion;
	}
	public void setWorkFlowVersion(int workFlowVersion) {
		this.workFlowVersion = workFlowVersion;
	}
	public String getWorkFlowDesc() {
		return workFlowDesc;
	}
	public void setWorkFlowDesc(String workFlowDesc) {
		this.workFlowDesc = workFlowDesc;
	}
	
}

