package com.yffd.easy.web.admin.pms.vo;

import org.yffd.easy.common.core.view.tree.TreeNode;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年10月9日 上午11:26:11 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class PmsOrganizationTreeGridVO extends TreeNode {
	private String state = "closed";
	private String iconCls;
	
	private String orgName;
	private String orgCode;
	private String parentCode;
	private String firstManagerCode;
	private String secondManagerCode;
	private String remark;
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
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

