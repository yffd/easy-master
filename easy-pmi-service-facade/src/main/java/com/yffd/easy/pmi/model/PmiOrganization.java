package com.yffd.easy.pmi.model;

import org.yffd.easy.common.core.model.PersistModel;

/**
 * @Description  系统模块：组织机构.
 * @Date		 2017年9月27日 下午3:41:11 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class PmiOrganization extends PersistModel {
	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * @since JDK 1.7+
	 */
	private static final long serialVersionUID = 8507597194623600620L;

	private String orgName;
	private String orgCode;
	private String parentCode;
	private String firstManagerCode;
	private String secondManagerCode;
	private String remark;
	
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getParentCode() {
		return parentCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	public String getFirstManagerCode() {
		return firstManagerCode;
	}
	public void setFirstManagerCode(String firstManagerCode) {
		this.firstManagerCode = firstManagerCode;
	}
	public String getSecondManagerCode() {
		return secondManagerCode;
	}
	public void setSecondManagerCode(String secondManagerCode) {
		this.secondManagerCode = secondManagerCode;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}

