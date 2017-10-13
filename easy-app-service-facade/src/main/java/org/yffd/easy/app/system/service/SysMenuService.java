package org.yffd.easy.app.system.service;

import java.util.List;

import org.yffd.easy.app.system.model.SysMenu;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年9月13日 上午10:44:41 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public interface SysMenuService {

	List<SysMenu> findAll();
	
	SysMenu findByCode(String code);
	
	List<SysMenu> findByParentCode(String parentCode);
	
	void add(SysMenu sysMenu);
	
	void editByCode(SysMenu sysMenu);
	
	void delByCode(String code);
	
}

