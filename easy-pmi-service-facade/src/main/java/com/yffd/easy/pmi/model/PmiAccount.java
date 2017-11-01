package com.yffd.easy.pmi.model;

import org.yffd.easy.common.core.model.PersistModel;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年10月18日 下午4:42:55 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class PmiAccount extends PersistModel {
	
	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * @since JDK 1.7+
	 */
	private static final long serialVersionUID = 5901803956221591134L;
	
	private String account;
	private String password;
	private String shorName;
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getShorName() {
		return shorName;
	}
	public void setShorName(String shorName) {
		this.shorName = shorName;
	}
	
}

