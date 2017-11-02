package com.yffd.easy.web.auth.vo;

import org.yffd.easy.common.core.view.tree.TreeNode;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年9月25日 下午2:26:00 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class AuthResourceTreeGridVO extends TreeNode {
	
    private String state;
    private String iconCls;
    
    private String rsName;
	private String rsCode;
	private String parentCode;
	private String inUrl;
	private String rsType;
	private String rsState;
	private Integer rsNum;
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
	public String getRsName() {
		return rsName;
	}
	public void setRsName(String rsName) {
		this.rsName = rsName;
	}
	public String getRsCode() {
		return rsCode;
	}
	public void setRsCode(String rsCode) {
		this.rsCode = rsCode;
	}
	public String getParentCode() {
		return parentCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	public String getInUrl() {
		return inUrl;
	}
	public void setInUrl(String inUrl) {
		this.inUrl = inUrl;
	}
	public String getRsType() {
		return rsType;
	}
	public void setRsType(String rsType) {
		this.rsType = rsType;
	}
	public String getRsState() {
		return rsState;
	}
	public void setRsState(String rsState) {
		this.rsState = rsState;
	}
	public Integer getRsNum() {
		return rsNum;
	}
	public void setRsNum(Integer rsNum) {
		this.rsNum = rsNum;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
    
}

