package com.yffd.easy.auth.model;

import org.yffd.easy.common.core.model.PersistModel;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年10月18日 下午4:42:55 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class AuthUser extends PersistModel {
	
	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * @since JDK 1.7+
	 */
	private static final long serialVersionUID = 5901803956221591134L;
	
	private String userName;
	private String userCode;
	private String orgCode;
	private String userState;
	private String userPwd;
	private String tel;
	private String email;
	private String remark;
	private AuthOrganization organization;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getUserState() {
		return userState;
	}
	public void setUserState(String userState) {
		this.userState = userState;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public AuthOrganization getOrganization() {
		return organization;
	}
	public void setOrganization(AuthOrganization organization) {
		this.organization = organization;
	}
	
}

