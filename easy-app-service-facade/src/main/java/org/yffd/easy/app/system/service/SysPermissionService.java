package org.yffd.easy.app.system.service;

import org.yffd.easy.app.system.model.SysPermission;
import org.yffd.easy.common.core.page.PageParam;
import org.yffd.easy.common.core.page.PageResult;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年10月12日 下午1:56:06 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public interface SysPermissionService {

	PageResult<SysPermission> findList(String name, String code, PageParam pageParam);
	
	void add(SysPermission permission);
}

