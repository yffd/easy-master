package com.yffd.easy.admin.pms.service;

import java.util.List;

import com.yffd.easy.admin.pms.model.PmsRole;
import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年10月23日 上午11:18:35 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public interface PmsRoleService {
	
	PageResult<PmsRole> findPage(PmsRole role, PageParam pageParam);
	
	List<PmsRole> findList(PmsRole role);
	
	PmsRole findByCode(String roleCode);
	
	void add(PmsRole role);
	
	void editByCode(PmsRole role);
	
	void delByCode(String role);
	
	void saveRoleResource(String roleCode, List<String> rsCodes);
	
	void saveRoleUser(String userCode, List<String> roleCodes);
	
	List<PmsRole> findByUser(String userCode);
	
}

