package org.yffd.easy.app.system.service;

import org.yffd.easy.app.system.model.SysUser;
import org.yffd.easy.common.core.page.PageParam;
import org.yffd.easy.common.core.page.PageResult;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年9月13日 上午10:44:41 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public interface SysUserService {

	PageResult<SysUser> findList(SysUser sysUser, PageParam pageParam);
	
	SysUser findByCode(String userCode);
	
	void add(SysUser sysUser);
	
	void editByCode(SysUser sysUser);
	
	void delByCode(String userCode);
	
}

