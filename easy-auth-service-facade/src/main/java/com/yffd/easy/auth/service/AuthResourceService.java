package com.yffd.easy.auth.service;

import java.util.List;

import com.yffd.easy.auth.model.AuthResource;
import com.yffd.easy.auth.model.AuthUser;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年10月18日 下午5:12:08 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public interface AuthResourceService {

	List<AuthResource> findAll();
	
	List<AuthResource> findAllMenu();
	
	List<AuthResource> findMenuByUser(AuthUser user);
	
	List<AuthResource> findByParentCode(String parentCode);
	
	AuthResource findByCode(String rsCode);
	
	void add(AuthResource resource);
	
	void editByCode(AuthResource resource);
	
	void delByCode(String rsCode);
	
	List<AuthResource> findByRole(String roleCode);
	
}

