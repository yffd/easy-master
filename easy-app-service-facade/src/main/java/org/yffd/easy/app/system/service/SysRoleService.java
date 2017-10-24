package org.yffd.easy.app.system.service;

import java.util.List;

import org.yffd.easy.app.system.model.SysRole;
import org.yffd.easy.common.core.page.PageParam;
import org.yffd.easy.common.core.page.PageResult;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年10月23日 上午11:18:35 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public interface SysRoleService {
	
	PageResult<SysRole> findPage(SysRole sysRole, PageParam pageParam);
	
	List<SysRole> findList(SysRole sysRole);
	
	SysRole findByCode(String roleCode);
	
	void add(SysRole sysRole);
	
	void editByCode(SysRole sysRole);
	
	void delByCode(String sysRole);
	
	void saveRoleFunc(String roleCode, List<String> funcCodes);
	
	void saveRoleUser(String userCode, List<String> roleCodes);
	
	List<SysRole> findByUser(String userCode);
	
}

