package com.yffd.easy.auth.service;

import org.yffd.easy.common.core.page.PageParam;
import org.yffd.easy.common.core.page.PageResult;

import com.yffd.easy.auth.model.AuthUser;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年9月13日 上午10:44:41 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public interface AuthUserService {

	PageResult<AuthUser> findList(AuthUser user, PageParam pageParam);
	
	AuthUser findByCode(String userCode);
	
	void add(AuthUser user);
	
	void editByCode(AuthUser user);
	
	void delByCode(String userCode);

	AuthUser findByCodeAndPwd(String userCode, String userPwd);
	
}

