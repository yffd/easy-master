package com.yffd.easy.admin.login.model;

import java.io.Serializable;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年11月15日 下午4:07:33 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class UsernamePasswordInfo implements Serializable {
	
	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * @since JDK 1.7+
	 */
	private static final long serialVersionUID = -7529208182143776237L;
	
	private String userName;
	private String userPwd;
	private String salt; // 加密密码的盐
    private String status = "A"; // 账户状态，A：active、I：inactive
    
    
    public UsernamePasswordInfo() {
    	
    }
    
	public UsernamePasswordInfo(String userName, String userPwd, String salt, String status) {
		this.userName = userName;
		this.userPwd = userPwd;
		this.salt = salt;
		this.status = status;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCredentialsSalt() {
        return userName + salt;
    }
}

