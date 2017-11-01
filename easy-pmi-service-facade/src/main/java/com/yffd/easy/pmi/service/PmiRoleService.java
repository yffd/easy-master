package com.yffd.easy.pmi.service;

import java.util.List;

import org.yffd.easy.common.core.page.PageParam;
import org.yffd.easy.common.core.page.PageResult;

import com.yffd.easy.pmi.model.PmiRole;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年10月23日 上午11:18:35 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public interface PmiRoleService {
	
	PageResult<PmiRole> findPage(PmiRole role, PageParam pageParam);
	
	List<PmiRole> findList(PmiRole role);
	
	PmiRole findByCode(String roleCode);
	
	void add(PmiRole role);
	
	void editByCode(PmiRole role);
	
	void delByCode(String role);
	
	void saveRoleResource(String roleCode, List<String> rsCodes);
	
	void saveRoleUser(String userCode, List<String> roleCodes);
	
	List<PmiRole> findByUser(String userCode);
	
}

