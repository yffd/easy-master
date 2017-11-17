package com.yffd.easy.admin.login.service;

import java.util.Set;

import com.yffd.easy.admin.login.model.LoginInfo;
import com.yffd.easy.admin.login.model.UsernamePasswordInfo;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年11月15日 上午10:58:41 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public interface LoginService {
	public static final String SESSION_LOGIN_INFO_KEY = "_LOGIN_INFO";
	
	LoginInfo findLoginInfo(String userCode);

	UsernamePasswordInfo findAccount(String userCode);
	
	Set<String> findRoles(String userCode);
	
	Set<String> findPermissions(String userCode);
	
}

