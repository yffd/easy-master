package org.yffd.easy.app.permission.service;

import java.util.List;

import org.yffd.easy.app.permission.entity.PmsMenu;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年9月13日 上午10:44:41 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public interface PmsMenuService {

	/**
	 * 获取所有菜单，并组装成父子结构
	 * @Date	2017年9月20日 下午2:59:39 <br/>
	 * @author  zhangST
	 * @return
	 */
	List<PmsMenu> findAll();
	
	PmsMenu findByMenuCode(String menuCode);
	
	PmsMenu findMenuWithParent(String menuCode);
	
	void add(PmsMenu pmsMenu);
	
	void update(PmsMenu pmsMenu);
	
	void deleteByMenuCode(String menuCode);
}

