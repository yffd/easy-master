package com.yffd.easy.auth.service;

import java.util.List;

import com.yffd.easy.auth.model.AuthOrganization;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年9月13日 上午10:44:41 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public interface AuthOrganizationService {

	List<AuthOrganization> findAll();
	
	List<AuthOrganization> findByParentCode(String parentCode);
	
	AuthOrganization findByCode(String orgCode);
	
	void add(AuthOrganization organization);
	
	void editByCode(AuthOrganization organization);
	
	void delByCode(String orgCode);
	
}

