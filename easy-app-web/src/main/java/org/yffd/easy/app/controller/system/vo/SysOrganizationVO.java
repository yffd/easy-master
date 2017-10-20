package org.yffd.easy.app.controller.system.vo;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年10月9日 上午11:26:11 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class SysOrganizationVO {
	private String id;
//	private String _parentId;
	private String state = "closed";
	private String orgName;
	private String orgCode;
	private String parentCode;
	private String firstManagerCode;
	private String secondManagerCode;
	private String tel;
	private String fax;
	private String remark;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
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
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}

