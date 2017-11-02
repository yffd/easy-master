package com.yffd.easy.auth.service;

import java.util.List;

import org.yffd.easy.common.core.page.PageParam;
import org.yffd.easy.common.core.page.PageResult;

import com.yffd.easy.auth.model.AuthRole;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年10月23日 上午11:18:35 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public interface AuthRoleService {
	
	PageResult<AuthRole> findPage(AuthRole role, PageParam pageParam);
	
	List<AuthRole> findList(AuthRole role);
	
	AuthRole findByCode(String roleCode);
	
	void add(AuthRole role);
	
	void editByCode(AuthRole role);
	
	void delByCode(String role);
	
	void saveRoleResource(String roleCode, List<String> rsCodes);
	
	void saveRoleUser(String userCode, List<String> roleCodes);
	
	List<AuthRole> findByUser(String userCode);
	
}

