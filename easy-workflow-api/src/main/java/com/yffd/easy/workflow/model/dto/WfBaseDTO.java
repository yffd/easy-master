package com.yffd.easy.workflow.model.dto;

import java.io.Serializable;

/**
 * @Description  流程基础信息.
 * @Date		 2018年1月16日 下午5:54:11 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class WfBaseDTO implements Serializable {
	
	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * @since JDK 1.7+
	 */
	private static final long serialVersionUID = -3004991757307013302L;

	private String wfCategory;		// 工作流类别编号，即流程图中的Namespace
	private String wfName;			// 工作流名称，即流程图中的Name
	private String wfKey;			// 工作流关键字，即流程图中的Id
	private String wfDesc;			// 工作流描述，即流程图中的Documentation
	private int wfVersion;			// 流程版本号
	
	public String getWfCategory() {
		return wfCategory;
	}
	public void setWfCategory(String wfCategory) {
		this.wfCategory = wfCategory;
	}
	public String getWfName() {
		return wfName;
	}
	public void setWfName(String wfName) {
		this.wfName = wfName;
	}
	public String getWfKey() {
		return wfKey;
	}
	public void setWfKey(String wfKey) {
		this.wfKey = wfKey;
	}
	public String getWfDesc() {
		return wfDesc;
	}
	public void setWfDesc(String wfDesc) {
		this.wfDesc = wfDesc;
	}
	public int getWfVersion() {
		return wfVersion;
	}
	public void setWfVersion(int wfVersion) {
		this.wfVersion = wfVersion;
	}
	
}

