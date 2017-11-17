package com.yffd.easy.admin.login.model;

import java.io.Serializable;
import java.util.Set;

/**
 * @Description  主要用于登录信息的session缓存定制.
 * @Date		 2017年11月14日 下午5:05:51 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class LoginInfo implements Serializable {
	
	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * @since JDK 1.7+
	 */
	private static final long serialVersionUID = 1645439423100883042L;

	private String userCode;
	private String nickName;
	private Set<String> roles;
	private Set<String> permissions;
	
	public LoginInfo() {
		
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	public Set<String> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<String> permissions) {
		this.permissions = permissions;
	}
	
}

